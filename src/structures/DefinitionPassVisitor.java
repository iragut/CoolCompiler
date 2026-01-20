package cool.structures;

import cool.AST.*;
import java.util.LinkedList;


public class DefinitionPassVisitor implements ASTVisitor<Void> {
    Scope currentScope = null;
    ClassSymbol current_class = null;

    private boolean checkForCycle(ClassSymbol classSymbol) {
        LinkedList<ClassSymbol> visited = new LinkedList<>();
        ClassSymbol current = classSymbol;

        while (current != null) {
            if (visited.contains(current)) {
                return true;
            }
            visited.add(current);
            current = current.getInherited_class();
        }
        return false;
    }

    @Override
    public Void visit(ASTNode.Program program) {
        currentScope = SymbolTable.globals;
        LinkedList<ASTNode.ClassDef> simple_classes = new LinkedList<>();
        LinkedList<ASTNode.ClassDef> inherited_classes = new LinkedList<>();

        // Check for duplicate class names
        for (ASTNode.ClassDef classDef : program.classes) {
            String name_class = classDef.type.getToken().getText();
            if (name_class.equals("SELF_TYPE")) {
                SymbolTable.error(classDef.getCtx() ,classDef.getToken(), "Class has illegal name SELF_TYPE");
                continue;
            }

            Symbol exist = currentScope.lookup(name_class);

            if (exist != null) {
                SymbolTable.error(classDef.getCtx() ,classDef.getToken(), "Class " + name_class + " is redefined");
                continue;
            }

            currentScope.add(new ClassSymbol(name_class, currentScope));

        }

        // Check for illegal parent classes
        for (ASTNode.ClassDef classDef : program.classes) {
            String class_name = classDef.type.getToken().getText();
            Symbol class_symbol = currentScope.lookup(class_name);

            if (class_symbol instanceof ClassSymbol current_class) {
                if (classDef.inheritsType != null) {
                    // Check if the parent class has an illegal parent
                    String parent_class = classDef.inheritsType.getToken().getText();
                    if (parent_class.equals("SELF_TYPE") || parent_class.equals("Int") ||
                            parent_class.equals("String") || parent_class.equals("Bool")) {
                        SymbolTable.error(classDef.getCtx(), classDef.inheritsType.getToken(), "Class " +
                                current_class.getName() + " has illegal parent " + parent_class);
                        continue;
                    }

                    // Check if the parent class exists
                    Symbol parent_symbol = currentScope.lookup(parent_class);
                    if (parent_symbol == null) {
                        SymbolTable.error(classDef.getCtx(), classDef.inheritsType.getToken(), "Class " +
                                current_class.getName() + " has undefined parent " + parent_class);
                        continue;
                    }

                    inherited_classes.add(classDef);

                    current_class.setInherited_class((ClassSymbol) parent_symbol);

                } else {
                    Symbol object_symbol = currentScope.lookup("Object");
                    if (object_symbol instanceof ClassSymbol) {
                        current_class.setInherited_class((ClassSymbol) object_symbol);
                    }
                    simple_classes.add(classDef);
                }
            }
        }


        // Check if contain a cycle in inheritance
        for (ASTNode.ClassDef classDef : program.classes) {
            String class_name = classDef.type.getToken().getText();
            Symbol class_symbol = currentScope.lookup(class_name);

            if (class_symbol instanceof ClassSymbol current_class) {
                if (checkForCycle(current_class)){
                    SymbolTable.error(classDef.getCtx(), classDef.getToken(), "Inheritance cycle for class " +
                            current_class.getName());
                }
            }
        }

        for (ASTNode.ClassDef classDef : simple_classes) {
            classDef.accept(this);
        }
        for (ASTNode.ClassDef classDef : inherited_classes) {
            classDef.accept(this);
        }

        return null;
    }

    @Override
    public Void visit(ASTNode.ClassDef classDef) {
        String className = classDef.type.getToken().getText();
        current_class = (ClassSymbol) currentScope.lookup(className);

        if (current_class == null) {
            return null;
        }
        currentScope = current_class;

        for (ASTNode.Feature feature : classDef.features) {
            feature.accept(this);
        }

        currentScope = currentScope.getParent();
        current_class = null;

        return null;
    }

    @Override
    public Void visit(ASTNode.FunctionsFeatures functionsFeatures) {
        String function_name = functionsFeatures.id.getToken().getText();

        // Check if the function is already defined
        if (current_class.functions.containsKey(function_name)) {
            SymbolTable.error(functionsFeatures.getCtx(),
                    functionsFeatures.id.getToken(), "Class " +
                            current_class.getName() + " redefines method " + function_name);
            return null;
        }

        // Check if the function has a return type
        if (functionsFeatures.type == null) {
            SymbolTable.error(functionsFeatures.getCtx(),
                    functionsFeatures.id.getToken(), "Class " +
                            current_class.getName() + " has method " + function_name +
                            " with undefined return type");
            return null;
        }

        // Check if the function is already defined in the parent class
        if (current_class.getInherited_class() != null) {
            Symbol inherited_symbol = current_class.getInherited_class().lookup(function_name);
            if (inherited_symbol instanceof FunctionSymbol inherited_function) {
                // Has the same nr of arguments
                if (inherited_function.formals.size() != functionsFeatures.formals.size()) {
                    SymbolTable.error(functionsFeatures.getCtx(),
                            functionsFeatures.id.getToken(), "Class " +
                                    current_class.getName() + " overrides method " + function_name +
                                    " with different number of formal parameters");
                    return null;
                }
                // Types are different
                for (int i = 0; i < inherited_function.formals.size(); i++) {
                    String type_first_class = functionsFeatures.formals.get(i).type.getToken().getText();
                    String id = functionsFeatures.formals.get(i).id.getToken().getText();

                    IdSymbol inherited_formal = (IdSymbol) inherited_function.formals.values().toArray()[i];
                    String type_second_class = inherited_formal.getType().getName();

                    if (!type_first_class.equals(type_second_class)) {
                        SymbolTable.error(functionsFeatures.getCtx(),
                                functionsFeatures.formals.get(i).type.getToken(), "Class " +
                                        current_class.getName() + " overrides method " + function_name +
                                        " but changes type of formal parameter " + id +
                                        " from " + type_second_class + " to " + type_first_class);
                        return null;
                    }
                }

                // The return type is different
                if (!inherited_function.getType().getName().equals(functionsFeatures.type.getToken().getText())) {
                    SymbolTable.error(functionsFeatures.getCtx(),
                            functionsFeatures.type.getToken(), "Class " +
                                    current_class.getName() + " overrides method " + function_name +
                                    " but changes return type from " + inherited_function.getType().getName() +
                                    " to " + functionsFeatures.type.getToken().getText());
                }

            }
        }

        FunctionSymbol function_symbol = new FunctionSymbol(currentScope, function_name, current_class);

        String return_type_name = functionsFeatures.type.getToken().getText();
        if (return_type_name.equals("SELF_TYPE")) {
            function_symbol.type = TypeSymbol.SELF_TYPE;
        } else {
            // Check if the return type exists
            Symbol return_type_symbol = currentScope.lookup(return_type_name);
            if (return_type_symbol == null) {
                SymbolTable.error(functionsFeatures.getCtx(),
                        functionsFeatures.type.getToken(), "Class " +
                                current_class.getName() + " has method " + function_name +
                                " with undefined return type " + return_type_name);
                return null;
            }
            function_symbol.type = new TypeSymbol(return_type_name);
        }

        for (ASTNode.Formal formal : functionsFeatures.formals) {
            String formal_name = formal.id.getToken().getText();

            // Check if the formal parameter is called self
            if (formal_name.equals("self")) {
                SymbolTable.error(functionsFeatures.getCtx(),
                        formal.id.getToken(), "Method " + function_name +
                                " of class " + current_class.getName() + " has formal parameter with illegal name self");
                continue;
            }

            // Check if the formal parameter is already defined
            if (function_symbol.formals.containsKey(formal_name)) {
                SymbolTable.error(functionsFeatures.getCtx(),
                        formal.id.getToken(), "Method " + function_name +
                                " of class " + current_class.getName() + " redefines formal parameter " + formal_name);
                continue;
            }

            // Check if it has SELF_TYPE as an illegal type
            String type_name = formal.type.getToken().getText();
            if (type_name.equals("SELF_TYPE")) {
                SymbolTable.error(functionsFeatures.getCtx(),
                        formal.type.getToken(), "Method " + function_name +
                                " of class " + current_class.getName() + " has formal parameter " + formal_name +
                                " with illegal type SELF_TYPE");
                continue;
            }

            // Check if a function has an undefined type
            Symbol type_symbol = currentScope.lookup(type_name);
            if (type_symbol == null) {
                SymbolTable.error(functionsFeatures.getCtx(),
                        formal.type.getToken(), "Method " + function_name +
                                " of class " + current_class.getName() + " has formal parameter " + formal_name +
                                " with undefined type " + type_name);
                continue;
            }

            // Create id symbol for the formal parameter and add to the class
            IdSymbol formal_symbol = new IdSymbol(formal_name);
            formal_symbol.setType(((ClassSymbol) type_symbol).getType());
            function_symbol.add(formal_symbol);

        }

        current_class.add(function_symbol);

        Scope savedScope = currentScope;
        currentScope = function_symbol;
        functionsFeatures.body.accept(this);
        currentScope = savedScope;
        return null;
    }

    @Override
    public Void visit(ASTNode.AssignFeatures assignFeatures) {
        String attribute_name = assignFeatures.id.getToken().getText();

        // Check if the attribute is called self
        if (attribute_name.equals("self")) {
            SymbolTable.error(assignFeatures.getCtx(),
                    assignFeatures.id.getToken(), "Class " +
                            current_class.getName() + " has attribute with illegal name self");
            return null;
        }

        // Check if the attribute is already defined
        if (current_class.attributes.containsKey(attribute_name)) {
            SymbolTable.error(assignFeatures.getCtx(),
                    assignFeatures.id.getToken(), "Class " +
                            current_class.getName() + " redefines attribute " + attribute_name);
            return null;
        }

        // Check if the attribute is already defined in the parent class
        if (current_class.getInherited_class() != null) {
            Symbol existing_class = current_class.getInherited_class().lookup(attribute_name);
            if (existing_class != null) {
                SymbolTable.error(assignFeatures.getCtx(),
                        assignFeatures.id.getToken(), "Class " +
                                current_class.getName() + " redefines inherited attribute " + attribute_name);
                return null;
            }
        }

        IdSymbol new_var = new IdSymbol(attribute_name);
        String type_name = assignFeatures.type.getToken().getText();

        if (type_name.equals("SELF_TYPE")) {
            new_var.setType(TypeSymbol.SELF_TYPE);
            current_class.add(new_var);
            return null;
        }

        Symbol type_symbol = currentScope.lookup(type_name);

        if (type_symbol == null) {
            SymbolTable.error(assignFeatures.getCtx(),
                    assignFeatures.type.getToken(), "Class " +
                            current_class.getName() + " has attribute " + attribute_name +
                            " with undefined type " + type_name);
            return null;
        }

        ClassSymbol class_symbol = (ClassSymbol) type_symbol;
        new_var.setType(class_symbol.getType());
        current_class.add(new_var);

        return null;
    }

    @Override
    public Void visit(ASTNode.CaseMethodNode caseMethodNode) {
        String var_name = caseMethodNode.id.getToken().getText();

        // Check if the variable is called self
        if (var_name.equals("self")) {
            SymbolTable.error(caseMethodNode.getCtx(),
                    caseMethodNode.id.getToken(),
                    "Case variable has illegal name self");
            return null;
        }

        // Check if the case variable is called SELF_TYPE
        String type_name = caseMethodNode.type.getToken().getText();
        if (type_name.equals("SELF_TYPE")) {
            SymbolTable.error(caseMethodNode.getCtx(),
                    caseMethodNode.type.getToken(),
                    "Case variable " + var_name + " has illegal type SELF_TYPE");
            return null;
        }

        // Check if the case variable is already defined
        Symbol type_Symbol = SymbolTable.globals.lookup(type_name);
        if (type_Symbol == null) {
            SymbolTable.error(caseMethodNode.getCtx(),
                    caseMethodNode.type.getToken(),
                    "Case variable " + var_name + " has undefined type " + type_name);
            return null;
        }

        // Create a new scope for the case variable
        Scope caseScope = new DefaultScope(currentScope);
        Scope savedScope = currentScope;
        currentScope = caseScope;

        // Save the new case variable in the new scope and visit the case method body
        IdSymbol var_symbol = new IdSymbol(var_name);
        ClassSymbol type_class = (ClassSymbol) type_Symbol;
        var_symbol.setType(type_class.getType());
        currentScope.add(var_symbol);

        caseMethodNode.cases.accept(this);

        currentScope = savedScope;

        return null;
    }

    @Override
    public Void visit(ASTNode.CaseNode caseNode) {
        caseNode.condition.accept(this);

        for (ASTNode.CaseMethodNode caseMethod : caseNode.cases) {
            caseMethod.accept(this);
        }

        return null;
    }

    @Override
    public Void visit(ASTNode.LetNode letNode) {
        Scope let_scope = new DefaultScope(currentScope);
        Scope saved_scope = currentScope;
        currentScope = let_scope;

        for (ASTNode.LocalVarNode localVar : letNode.localVars) {
            localVar.accept(this);
        }

        currentScope = saved_scope;
        return null;
    }

    @Override
    public Void visit(ASTNode.LocalVarNode localVarNode) {
        String local_var_name = localVarNode.id.getToken().getText();
        String type = localVarNode.type.getToken().getText();


        // Check if the variable is called self
        if (local_var_name.equals("self")) {
            SymbolTable.error(localVarNode.getCtx(),
                    localVarNode.id.getToken(), "Let variable has illegal name self");
            return null;
        }

        if (type.equals("SELF_TYPE")) {
            currentScope.add(new IdSymbol(local_var_name));
            return null;
        }

        // Check if the variable is already defined
        Symbol type_symbol = currentScope.lookup(type);
        if (type_symbol == null) {
            SymbolTable.error(localVarNode.getCtx(),
                    localVarNode.type.getToken(), "Let variable " + local_var_name +
                            " has undefined type " + type);
            return null;
        }

        currentScope.add(new IdSymbol(local_var_name));

        return null;
    }

    @Override
    public Void visit(ASTNode.Formal formal) {
        return null;
    }

    @Override
    public Void visit(ASTNode.IDNode idNode) {
        return null;
    }

    @Override
    public Void visit(ASTNode.TypeNode typeNode) {
        return null;
    }

    @Override
    public Void visit(ASTNode.EqualNode equalNode) {
        return null;
    }

    @Override
    public Void visit(ASTNode.LeNode leNode) {
        return null;
    }

    @Override
    public Void visit(ASTNode.LtNode ltNode) {
        return null;
    }

    @Override
    public Void visit(ASTNode.NotNode notNode) {
        return null;
    }

    @Override
    public Void visit(ASTNode.PlusNode plusNode) {
        return null;
    }

    @Override
    public Void visit(ASTNode.MinusNode minusNode) {
        return null;
    }

    @Override
    public Void visit(ASTNode.MulNode mulNode) {
        return null;
    }

    @Override
    public Void visit(ASTNode.DivNode divNode) {
        return null;
    }

    @Override
    public Void visit(ASTNode.ParenNode parenNode) {
        return null;
    }

    @Override
    public Void visit(ASTNode.TildeNode tildeNode) {
        return null;
    }

    @Override
    public Void visit(ASTNode.IDtype iDtype) {
        return null;
    }

    @Override
    public Void visit(ASTNode.IntType intLiteral) {
        return null;
    }

    @Override
    public Void visit(ASTNode.StringType stringType) {
        return null;
    }

    @Override
    public Void visit(ASTNode.TrueType trueType) {
        return null;
    }

    @Override
    public Void visit(ASTNode.FalseType falseType) {
        return null;
    }

    @Override
    public Void visit(ASTNode.AssignNode assignNode) {
        return null;
    }

    @Override
    public Void visit(ASTNode.IsvoidNode isvoidNode) {
        return null;
    }

    @Override
    public Void visit(ASTNode.NewTypeNode newTypeNode) {
        return null;
    }

    @Override
    public Void visit(ASTNode.FuncCallClassNode funcCallClassNode) {
        return null;
    }

    @Override
    public Void visit(ASTNode.FuncCallNode funcCallNode) {
        return null;
    }

    @Override
    public Void visit(ASTNode.WhileNode whileNode) {
        return null;
    }

    @Override
    public Void visit(ASTNode.IfNode ifNode) {
        return null;
    }

    @Override
    public Void visit(ASTNode.BlockNode blockNode) {
        return null;
    }
}