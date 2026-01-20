package cool.structures;

import cool.AST.ASTNode;
import cool.AST.ASTVisitor;
import org.antlr.v4.runtime.ParserRuleContext;
import org.antlr.v4.runtime.Token;

import java.util.ArrayList;
import java.util.List;

public class ResolutionPassVisitor implements ASTVisitor<TypeSymbol> {
    Scope currentScope = null;
    ClassSymbol current_class = null;

    private boolean checkReturnedType(TypeSymbol returned_type, TypeSymbol needed_type) {
        if (returned_type == null || needed_type == null) {
            return false;
        }

        String returned_name = returned_type.getName();
        String needed_name = needed_type.getName();

        // If they are the same type
        if (returned_name.equals(needed_name)) {
            return true;
        }

        // If we get something else, but need SELF_TYPE
        if (needed_name.equals("SELF_TYPE")) {
            return false;
        }

        // Check inheritance chain for the same type
        if (returned_name.equals("SELF_TYPE")) {
            if (current_class != null) {
                return checkReturnedType(current_class.getType(), needed_type);
            }
            return false;
        }

        // Look up the returned type in the class
        Symbol actual_symbol = SymbolTable.globals.lookup(returned_name);
        if (!(actual_symbol instanceof ClassSymbol current)) {
            return false;
        }

        // Walk up the inheritance chain
        while (current != null) {
            if (current.getName().equals(needed_name)) {
                return true;
            }
            current = current.getInherited_class();
        }

        return false;
    }

    private TypeSymbol checkFunctionCall(ClassSymbol classScope, String function_name,
                                         List<ASTNode.Expression> arguments,
                                         ParserRuleContext ctx, Token idToken) {
        // Check if the function exists
        Symbol function_symbol = classScope.lookup(function_name);

        if (!(function_symbol instanceof FunctionSymbol function)) {
            SymbolTable.error(ctx, idToken,
                    "Undefined method " + function_name + " in class " + classScope.getName());
            return null;
        }

        // Check the number of arguments
        if (arguments.size() != function.getFormals().size()) {
            SymbolTable.error(ctx, idToken,
                    "Method " + function_name + " of class " + classScope.getName() +
                            " is applied to wrong number of arguments");
            return function.getType();
        }

        // Check types of arguments
        int i = 0;
        for (Symbol formalSymbol : function.getFormals().values()) {
            IdSymbol formal = (IdSymbol) formalSymbol;
            TypeSymbol actualType = arguments.get(i).accept(this);

            if (actualType != null && formal.getType() != null && !checkReturnedType(actualType, formal.getType())) {
                SymbolTable.error(ctx,
                        arguments.get(i).getToken(),
                        "In call to method " + function_name + " of class " + classScope.getName() +
                                ", actual type " + actualType.getName() + " of formal parameter " +
                                formal.getName() + " is incompatible with declared type " +
                                formal.getType().getName());
            }
            i++;
        }

        return function.getType();
    }

    private TypeSymbol join(TypeSymbol type1, TypeSymbol type2) {
        if (type1 == null || type2 == null) {
            return TypeSymbol.OBJECT;
        }

        String t1 = type1.getName();
        String t2 = type2.getName();

        if (t1.equals(t2)) {
            return type1;
        }

        if (t1.equals("SELF_TYPE") ) {
            if (current_class != null) {
                return join(current_class.getType(), type2);
            }
        }

        if (t2.equals("SELF_TYPE")) {
            if (current_class != null) {
                return join(type1, current_class.getType());
            }
        }

        List<String> ancestors1 = getAncestors(t1);

        Symbol sym2 = SymbolTable.globals.lookup(t2);
        if (sym2 instanceof ClassSymbol current) {
            while (current != null) {
                if (ancestors1.contains(current.getName())) {
                    return current.getType();
                }
                current = current.getInherited_class();
            }
        }

        return TypeSymbol.OBJECT;
    }

    private List<String> getAncestors(String typeName) {
        List<String> ancestors = new ArrayList<>();
        Symbol sym = SymbolTable.globals.lookup(typeName);

        if (sym instanceof ClassSymbol current) {
            while (current != null) {
                ancestors.add(current.getName());
                current = current.getInherited_class();
            }
        }

        return ancestors;
    }

    private TypeSymbol resolveType(String typeName) {
        if (typeName.equals("SELF_TYPE")) {
            return TypeSymbol.SELF_TYPE;
        }

        Symbol typeSymbol = SymbolTable.globals.lookup(typeName);

        if (typeSymbol instanceof ClassSymbol) {
            return ((ClassSymbol) typeSymbol).getType();
        }

        return null;
    }

    private void checkArithmetic(ASTNode node, String type, Symbol symbol, Token info){
        if (symbol != null && !symbol.getName().equals("Int")) {
            SymbolTable.error(node.getCtx(), info,
                    "Operand of " + type + " has type " + symbol.getName() + " instead of Int");
        }
    }

    @Override
    public TypeSymbol visit(ASTNode.Program program) {
        currentScope = SymbolTable.globals;

        for (ASTNode.ClassDef classDef : program.classes) {
            classDef.accept(this);
        }
        return null;
    }

    @Override
    public TypeSymbol visit(ASTNode.ClassDef classDef) {
        String className = classDef.type.getToken().getText();
        current_class = (ClassSymbol) currentScope.lookup(className);

        if (current_class == null) {
            return null;
        }

        Scope savedScope = currentScope;
        currentScope = current_class;

        for (ASTNode.Feature feature : classDef.features) {
            feature.accept(this);
        }

        currentScope = savedScope;
        current_class = null;

        return null;
    }

    @Override
    public TypeSymbol visit(ASTNode.FunctionsFeatures functionsFeatures) {
        String function_name = functionsFeatures.id.getToken().getText();
        FunctionSymbol function_symbol = current_class.functions.get(function_name);

        if (function_symbol == null) {
            return null;
        }

        Scope savedScope = currentScope;
        currentScope = function_symbol;

        TypeSymbol returned_type = functionsFeatures.body.accept(this);
        TypeSymbol declared_returned_type = function_symbol.getType();

        // Check return type compatibility
        if (declared_returned_type != null && declared_returned_type.getName().equals("SELF_TYPE")) {
            if (returned_type != null && !returned_type.getName().equals("SELF_TYPE")) {
                SymbolTable.error(functionsFeatures.getCtx(),
                        functionsFeatures.body.getToken(),
                        "Type " + returned_type.getName() +
                                " of the body of method " + function_name +
                                " is incompatible with declared return type SELF_TYPE");
            }
        } else {
            if (returned_type != null && declared_returned_type != null && !checkReturnedType(returned_type, declared_returned_type)) {
                SymbolTable.error(functionsFeatures.getCtx(),
                        functionsFeatures.body.getToken(),
                        "Type " + returned_type.getName() +
                                " of the body of method " + function_name +
                                " is incompatible with declared return type " + declared_returned_type.getName());
            }
        }

        currentScope = savedScope;

        return null;
    }

    @Override
    public TypeSymbol visit(ASTNode.AssignFeatures assignFeatures) {
        if (assignFeatures.exp == null) {
            return null;
        }

        String attr_name = assignFeatures.id.getToken().getText();
        String type_name = assignFeatures.type.getToken().getText();

        TypeSymbol declared_type = resolveType(type_name);

        Scope savedScope = currentScope;
        currentScope = current_class;

        TypeSymbol init_type = assignFeatures.exp.accept(this);

        // Check type compatibility
        if (init_type != null && declared_type != null && !checkReturnedType(init_type, declared_type)) {
            SymbolTable.error(assignFeatures.getCtx(),
                    assignFeatures.exp.getToken(),
                    "Type " + init_type.getName() +
                            " of initialization expression of attribute " + attr_name +
                            " is incompatible with declared type " + declared_type.getName());
        }

        currentScope = savedScope;

        return null;
    }

    @Override
    public TypeSymbol visit(ASTNode.EqualNode equalNode) {
        Symbol symbol1 = equalNode.left.accept(this);
        Symbol symbol2 = equalNode.right.accept(this);

        if (!(symbol1.getName().equals("Bool") || symbol1.getName().equals("Int") || symbol1.getName().equals("String"))) {
            if (!(symbol2.getName().equals("Bool") || symbol2.getName().equals("Int") || symbol2.getName().equals("String"))) {
                return TypeSymbol.BOOL;
            }
        }

        if (!symbol1.getName().equals(symbol2.getName())) {
            SymbolTable.error(equalNode.getCtx(),
                    equalNode.getToken(), "Cannot compare " + symbol1.getName() + " with " + symbol2.getName());
        }

        return TypeSymbol.BOOL;
    }

    @Override
    public TypeSymbol visit(ASTNode.LeNode leNode) {
        Symbol symbol1 = leNode.left.accept(this);
        Symbol symbol2 = leNode.right.accept(this);

        checkArithmetic(leNode, "<=", symbol1, leNode.left.getToken());
        checkArithmetic(leNode, "<=", symbol2, leNode.right.getToken());

        return TypeSymbol.BOOL;
    }

    @Override
    public TypeSymbol visit(ASTNode.LtNode ltNode) {
        Symbol symbol1 = ltNode.left.accept(this);
        Symbol symbol2 = ltNode.right.accept(this);

        checkArithmetic(ltNode, "<", symbol1, ltNode.left.getToken());
        checkArithmetic(ltNode, "<", symbol2, ltNode.right.getToken());


        return TypeSymbol.BOOL;
    }

    @Override
    public TypeSymbol visit(ASTNode.PlusNode plusNode) {
        TypeSymbol symbol1 = plusNode.left.accept(this);
        TypeSymbol symbol2 = plusNode.right.accept(this);

        checkArithmetic(plusNode, "+", symbol1, plusNode.left.getToken());
        checkArithmetic(plusNode, "+", symbol2, plusNode.right.getToken());

        return TypeSymbol.INT;
    }

    @Override
    public TypeSymbol visit(ASTNode.MinusNode minusNode) {
        TypeSymbol symbol1 = minusNode.left.accept(this);
        TypeSymbol symbol2 = minusNode.right.accept(this);

        checkArithmetic(minusNode, "-", symbol1, minusNode.left.getToken());
        checkArithmetic(minusNode, "-", symbol2, minusNode.right.getToken());

        return TypeSymbol.INT;
    }

    @Override
    public TypeSymbol visit(ASTNode.MulNode mulNode) {
        TypeSymbol symbol1 = mulNode.left.accept(this);
        TypeSymbol symbol2 = mulNode.right.accept(this);

        checkArithmetic(mulNode, "*", symbol1, mulNode.left.getToken());
        checkArithmetic(mulNode, "*", symbol2, mulNode.right.getToken());

        return TypeSymbol.INT;
    }

    @Override
    public TypeSymbol visit(ASTNode.DivNode divNode) {
        TypeSymbol symbol1 = divNode.left.accept(this);
        TypeSymbol symbol2 = divNode.right.accept(this);

        checkArithmetic(divNode, "/", symbol1, divNode.left.getToken());
        checkArithmetic(divNode, "/", symbol2, divNode.right.getToken());

        return TypeSymbol.INT;
    }

    @Override
    public TypeSymbol visit(ASTNode.NotNode notNode) {
        TypeSymbol exp_type = notNode.exp.accept(this);
        if (exp_type != TypeSymbol.BOOL) {
            SymbolTable.error(notNode.getCtx(),
                    notNode.exp.getToken(), "Operand of not has type " + exp_type.getName() + " instead of Bool");
        }
        return TypeSymbol.BOOL;
    }


    @Override
    public TypeSymbol visit(ASTNode.ParenNode parenNode) {
        return parenNode.exp.accept(this);
    }

    @Override
    public TypeSymbol visit(ASTNode.TildeNode tildeNode) {
        TypeSymbol exp_type = tildeNode.exp.accept(this);

        checkArithmetic(tildeNode, "~", exp_type, tildeNode.exp.getToken());

        return TypeSymbol.INT;
    }

    @Override
    public TypeSymbol visit(ASTNode.IDtype iDtype) {
        String id_name = iDtype.getToken().getText();

        if (id_name.equals("self")) {
            return TypeSymbol.SELF_TYPE;
        }

        Symbol symbol = currentScope.lookup(id_name);
        if (symbol == null) {
            SymbolTable.error(iDtype.getCtx(),
                    iDtype.getToken(), "Undefined identifier " + id_name);
        }

        if (symbol instanceof IdSymbol) {
            return ((IdSymbol) symbol).getType();
        }
        return null;
    }

    @Override
    public TypeSymbol visit(ASTNode.AssignNode assignNode) {
        String id_name = assignNode.id.getToken().getText();

        if (id_name.equals("self")) {
            SymbolTable.error(assignNode.getCtx(),
                    assignNode.id.getToken(), "Cannot assign to self");
            return null;
        }

        Symbol symbol = currentScope.lookup(id_name);
        if (symbol == null) {
            SymbolTable.error(assignNode.getCtx(),
                    assignNode.id.getToken(), "Undefined identifier " + id_name);
            return null;
        }

        TypeSymbol return_type = assignNode.exp.accept(this);

        if (symbol instanceof IdSymbol && return_type != null) {
            TypeSymbol declared_type = ((IdSymbol) symbol).getType();

            if (declared_type != null && !checkReturnedType(return_type, declared_type)) {
                SymbolTable.error(assignNode.getCtx(),
                        assignNode.exp.getToken(), "Type " + return_type.getName() + " of assigned expression is " +
                                "incompatible with declared type " + declared_type.getName() + " of identifier " + id_name);
            }
        }

        return return_type;
    }

    @Override
    public TypeSymbol visit(ASTNode.IsvoidNode isvoidNode) {
        isvoidNode.exp.accept(this);
        return TypeSymbol.BOOL;
    }

    @Override
    public TypeSymbol visit(ASTNode.NewTypeNode newTypeNode) {
        String type_name = newTypeNode.type.getToken().getText();

        if (type_name.equals("SELF_TYPE")) {
            return TypeSymbol.SELF_TYPE;
        }

        Symbol symbol = SymbolTable.globals.lookup(type_name);
        if (symbol == null) {
            SymbolTable.error(newTypeNode.getCtx(),
                    newTypeNode.type.getToken(), "new is used with undefined type " + type_name);
            return null;
        }

        if (symbol instanceof ClassSymbol) {
            return ((ClassSymbol) symbol).getType();
        }
        return null;
    }
    @Override
    public TypeSymbol visit(ASTNode.FuncCallClassNode funcCallClassNode) {
        String function_name = funcCallClassNode.id.getToken().getText();

        // Get the type of the func
        TypeSymbol function_type = funcCallClassNode.object.accept(this);

        if (function_type == null) {
            return null;
        }

        ClassSymbol lookupClass = null;

        if (funcCallClassNode.atType != null) {
            String static_type_name = funcCallClassNode.atType.getToken().getText();

            // Check for SELF_TYPE
            if (static_type_name.equals("SELF_TYPE")) {
                SymbolTable.error(funcCallClassNode.getCtx(),
                        funcCallClassNode.atType.getToken(),
                        "Type of static dispatch cannot be SELF_TYPE");
                return null;
            }

            // Check that the static type exists
            Symbol static_type_symbol = SymbolTable.globals.lookup(static_type_name);
            if (static_type_symbol == null) {
                SymbolTable.error(funcCallClassNode.getCtx(),
                        funcCallClassNode.atType.getToken(),
                        "Type " + static_type_name + " of static dispatch is undefined");
                return null;
            }

            lookupClass = (ClassSymbol) static_type_symbol;

            // Check that a static type is a superclass of an object type
            if (!checkReturnedType(function_type, lookupClass.getType())) {
                SymbolTable.error(funcCallClassNode.getCtx(),
                        funcCallClassNode.atType.getToken(),
                        "Type " + static_type_name + " of static dispatch is not a superclass of type " +
                                function_type.getName());
                return null;
            }
        } else {
            String object_type_name = function_type.getName();

            if (object_type_name.equals("SELF_TYPE")) {
                lookupClass = current_class;
            } else {
                Symbol object_class_symbol = SymbolTable.globals.lookup(object_type_name);
                if (object_class_symbol instanceof ClassSymbol) {
                    lookupClass = (ClassSymbol) object_class_symbol;
                }
            }
        }

        if (lookupClass == null) {
            return null;
        }

        TypeSymbol declared_return_type = checkFunctionCall(lookupClass, function_name, funcCallClassNode.arguments,
                funcCallClassNode.getCtx(), funcCallClassNode.id.getToken());

        if (declared_return_type == null) {
            return null;
        }

        if (declared_return_type.getName().equals("SELF_TYPE")) {
            return function_type;
        }

        return declared_return_type;
    }

    @Override
    public TypeSymbol visit(ASTNode.FuncCallNode funcCallNode) {
        String function_name = funcCallNode.id.getToken().getText();

        if (current_class == null) {
            return null;
        }

        return checkFunctionCall(current_class, function_name, funcCallNode.arguments, funcCallNode.getCtx(),
                funcCallNode.id.getToken());
    }


    @Override
    public TypeSymbol visit(ASTNode.WhileNode whileNode) {
        TypeSymbol condition_symbol= whileNode.condition.accept(this);
        if (condition_symbol != TypeSymbol.BOOL) {
            SymbolTable.error(whileNode.getCtx(),
                    whileNode.condition.getToken(), "While condition has type " +
                            condition_symbol.getName() + " instead of Bool");
        }
        whileNode.body.accept(this);
        return TypeSymbol.OBJECT;
    }

    @Override
    public TypeSymbol visit(ASTNode.IfNode ifNode) {
        TypeSymbol condition_symbol = ifNode.condition.accept(this);
        if (condition_symbol != TypeSymbol.BOOL) {
            SymbolTable.error(ifNode.getCtx(),
                    ifNode.condition.getToken(), "If condition has type " +
                            condition_symbol.getName() + " instead of Bool");
        }
        TypeSymbol then_symbol = ifNode.thenExp.accept(this);
        TypeSymbol else_symbol  = ifNode.elseExp.accept(this);

        return join(then_symbol, else_symbol);
    }

    @Override
    public TypeSymbol visit(ASTNode.CaseMethodNode caseMethodNode) {
        String var_name = caseMethodNode.id.getToken().getText();
        String type_name = caseMethodNode.type.getToken().getText();

        Symbol type_symbol = SymbolTable.globals.lookup(type_name);

        // Create a new scope for the case
        Scope caseScope = new DefaultScope(currentScope);
        Scope savedScope = currentScope;
        currentScope = caseScope;

        // Add the case variable to the scope
        if (type_symbol instanceof ClassSymbol) {
            IdSymbol var_symbol = new IdSymbol(var_name);
            var_symbol.setType(((ClassSymbol) type_symbol).getType());
            currentScope.add(var_symbol);
        }

        TypeSymbol branch_type = caseMethodNode.cases.accept(this);

        currentScope = savedScope;

        return branch_type;
    }

    @Override
    public TypeSymbol visit(ASTNode.CaseNode caseNode) {
        caseNode.condition.accept(this);

        List<TypeSymbol> branch_types = new ArrayList<>();

        for (ASTNode.CaseMethodNode caseMethod : caseNode.cases) {
            TypeSymbol branch_type = caseMethod.accept(this);
            if (branch_type != null) {
                branch_types.add(branch_type);
            }
        }

        // Return the LUB (join) of all branch types
        if (branch_types.isEmpty()) {
            return TypeSymbol.OBJECT;
        }

        TypeSymbol result = branch_types.getFirst();
        for (int i = 1; i < branch_types.size(); i++) {
            result = join(result, branch_types.get(i));
        }

        return result;
    }

    @Override
    public TypeSymbol visit(ASTNode.LetNode letNode) {
        Scope letScope = new DefaultScope(currentScope);
        Scope savedScope = currentScope;
        currentScope = letScope;

        for (ASTNode.LocalVarNode localVar : letNode.localVars) {
            localVar.accept(this);
        }

        TypeSymbol symbol = letNode.body.accept(this);

        currentScope = savedScope;
        return symbol;
    }

    @Override
    public TypeSymbol visit(ASTNode.LocalVarNode localVarNode) {
        String var_name = localVarNode.id.getToken().getText();
        String type_name = localVarNode.type.getToken().getText();

        // Look up the declared type
        TypeSymbol declared_type = resolveType(type_name);

        TypeSymbol init_type;
        if (localVarNode.initExpr != null) {
            init_type = localVarNode.initExpr.accept(this);

            if (init_type != null && declared_type != null && !checkReturnedType(init_type, declared_type)) {
                SymbolTable.error(localVarNode.getCtx(),
                        localVarNode.initExpr.getToken(),
                        "Type " + init_type.getName() +
                                " of initialization expression of identifier " + var_name +
                                " is incompatible with declared type " + declared_type.getName());
            }
        }


        if (declared_type != null) {
            IdSymbol var_symbol = new IdSymbol(var_name);
            var_symbol.setType(declared_type);
            currentScope.add(var_symbol);
        }

        return null;
    }

    @Override
    public TypeSymbol visit(ASTNode.BlockNode blockNode) {
        TypeSymbol last_type = null;
        for (ASTNode.Expression expr : blockNode.expressions) {
            last_type = expr.accept(this);
        }
        return last_type;
    }

    @Override
    public TypeSymbol visit(ASTNode.IntType intLiteral) {
        return TypeSymbol.INT;
    }

    @Override
    public TypeSymbol visit(ASTNode.StringType stringType) {
        return TypeSymbol.STRING;
    }

    @Override
    public TypeSymbol visit(ASTNode.TrueType trueType) {
        return TypeSymbol.BOOL;
    }

    @Override
    public TypeSymbol visit(ASTNode.FalseType falseType) {
        return TypeSymbol.BOOL;
    }

    @Override
    public TypeSymbol visit(ASTNode.Formal formal) {
        return null;
    }

    @Override
    public TypeSymbol visit(ASTNode.IDNode idNode) {
        return null;
    }

    @Override
    public TypeSymbol visit(ASTNode.TypeNode typeNode) {
        return null;
    }

}
