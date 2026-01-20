package cool.codegen;

import cool.AST.*;
import cool.structures.*;
import org.stringtemplate.v4.ST;
import org.stringtemplate.v4.STGroupFile;

import java.util.*;

public class CodeGenVisitor implements ASTVisitor<ST> {
    static STGroupFile templates = new STGroupFile("cool/codegen/cool.stg");

    private final Map<String, String> string_constants = new LinkedHashMap<>();
    private final Map<Integer, String> int_const = new LinkedHashMap<>();
    private final List<String> class_names = new LinkedList<>();

    private final Map<String, List<String>> inheritanceTree = new HashMap<>();

    private Map<String, Integer> letVariableOffsets = new HashMap<>();
    private Map<String, String> letVariableTypes = new HashMap<>();
    private int currentLetOffset = 0;

    ST data_section;
    ST text_section;

    int labelCounter = 0;
    int stringIndex = 0;
    int intIndex = 0;

    // Current context
    ClassSymbol currentClass;
    FunctionSymbol currentMethod;

    private void collectAllConstants(ASTNode.Program program) {
        addString("");
        addString("Object");
        addString("IO");
        addString("Int");
        addString("String");
        addString("Bool");

        for (ASTNode.ClassDef class_def : program.classes) {
            String class_name = class_def.type.getToken().getText();
            addString(class_name);
        }

        for (ASTNode.ClassDef class_def : program.classes) {
            collectConstantsFromClass(class_def);
        }
    }

    private void collectConstantsFromClass(ASTNode.ClassDef classDef) {
        for (ASTNode.Feature feature : classDef.features) {
            if (feature instanceof ASTNode.FunctionsFeatures method) {
                collectConstantsFromExpr(method.body);
            } else if (feature instanceof ASTNode.AssignFeatures attr) {
                if (attr.exp != null) {
                    collectConstantsFromExpr(attr.exp);
                }
            }
        }
    }

    private void collectConstantsFromExpr(ASTNode.Expression expr) {
        if (expr == null) return;

        switch (expr) {
            case ASTNode.IntType intNode -> {
                int value = Integer.parseInt(intNode.getToken().getText());
                addInt(value);
            }
            case ASTNode.StringType strNode -> {
                String value = strNode.getToken().getText();
                addString(value);
            }
            case ASTNode.PlusNode plus -> {
                collectConstantsFromExpr(plus.left);
                collectConstantsFromExpr(plus.right);
            }
            case ASTNode.MinusNode minus -> {
                collectConstantsFromExpr(minus.left);
                collectConstantsFromExpr(minus.right);
            }
            case ASTNode.MulNode mul -> {
                collectConstantsFromExpr(mul.left);
                collectConstantsFromExpr(mul.right);
            }
            case ASTNode.DivNode div -> {
                collectConstantsFromExpr(div.left);
                collectConstantsFromExpr(div.right);
            }
            case ASTNode.TildeNode tilde -> collectConstantsFromExpr(tilde.exp);
            case ASTNode.NotNode not -> collectConstantsFromExpr(not.exp);
            case ASTNode.LtNode lt -> {
                collectConstantsFromExpr(lt.left);
                collectConstantsFromExpr(lt.right);
            }
            case ASTNode.LeNode le -> {
                collectConstantsFromExpr(le.left);
                collectConstantsFromExpr(le.right);
            }
            case ASTNode.EqualNode eq -> {
                collectConstantsFromExpr(eq.left);
                collectConstantsFromExpr(eq.right);
            }
            case ASTNode.IfNode ifNode -> {
                collectConstantsFromExpr(ifNode.condition);
                collectConstantsFromExpr(ifNode.thenExp);
                collectConstantsFromExpr(ifNode.elseExp);
            }
            case ASTNode.WhileNode whileNode -> {
                collectConstantsFromExpr(whileNode.condition);
                collectConstantsFromExpr(whileNode.body);
            }
            case ASTNode.BlockNode block -> {
                for (ASTNode.Expression e : block.expressions) {
                    collectConstantsFromExpr(e);
                }
            }
            case ASTNode.FuncCallNode call -> {
                for (ASTNode.Expression arg : call.arguments) {
                    collectConstantsFromExpr(arg);
                }
            }
            case ASTNode.FuncCallClassNode call -> {
                String full_path = call.getToken().getInputStream().getSourceName();
                String filename = new java.io.File(full_path).getName();
                addString(filename);

                collectConstantsFromExpr(call.object);
                for (ASTNode.Expression arg : call.arguments) {
                    collectConstantsFromExpr(arg);
                }
            }
            case ASTNode.LetNode let -> {
                for (ASTNode.LocalVarNode var : let.localVars) {
                    if (var.initExpr != null) {
                        collectConstantsFromExpr(var.initExpr);
                    }
                }
                collectConstantsFromExpr(let.body);
            }
            case ASTNode.AssignNode assign -> collectConstantsFromExpr(assign.exp);
            case ASTNode.ParenNode paren -> collectConstantsFromExpr(paren.exp);
            case ASTNode.CaseNode caseNode -> {
                String full_path = caseNode.getToken().getInputStream().getSourceName();
                String filename = new java.io.File(full_path).getName();
                addString(filename);

                collectConstantsFromExpr(caseNode.condition);
                for (ASTNode.CaseMethodNode branch : caseNode.cases) {
                    collectConstantsFromExpr(branch.cases);
                }
            }
            case ASTNode.IsvoidNode isvoid -> collectConstantsFromExpr(isvoid.exp);
            default -> {}
        }
    }

    private int calculateStringSize(String s) {
        return 3 + 1 + (s.length() + 1 + 3) / 4;
    }

    private String escapeString(String s) {
        return s.replace("\\", "\\\\")
                .replace("\"", "\\\"")
                .replace("\n", "\\n")
                .replace("\t", "\\t");
    }

    private void addString(String value) {
        if (string_constants.containsKey(value)) {
            return;
        }
        String label = "str_const" + stringIndex++;
        string_constants.put(value, label);
    }

    private String addInt(int value) {
        if (int_const.containsKey(value)) {
            return int_const.get(value);
        }
        String label = "int_const" + intIndex++;
        int_const.put(value, label);
        return label;
    }

    private void addGlobalDirectives() {
        ST globals = templates.getInstanceOf("sequence");
        globals.add("e", ".globl  heap_start");
        globals.add("e", ".globl  Int_protObj");
        globals.add("e", ".globl  String_protObj");
        globals.add("e", ".globl  bool_const0");
        globals.add("e", ".globl  bool_const1");
        globals.add("e", ".globl  Main_protObj");
        globals.add("e", ".globl  _int_tag");
        globals.add("e", ".globl  _string_tag");
        globals.add("e", ".globl  _bool_tag");

        data_section.add("e", ".align  2");
        data_section.add("e", globals);
    }

    private void addClassTags() {
        addTag("int", class_names.indexOf("Int"));
        addTag("string", class_names.indexOf("String"));
        addTag("bool", class_names.indexOf("Bool"));
    }

    private void addTag(String name, int tag) {
        ST tags = templates.getInstanceOf("tag");
        tags.add("name", name);
        tags.add("number", tag);
        data_section.add("e", tags);
    }

    private void initStringConstants() {
        for (Map.Entry<String, String> entry : string_constants.entrySet()) {
            String value = entry.getKey();
            String label = entry.getValue();
            String len_label = addInt(value.length());

            ST str_const = templates.getInstanceOf("stringConst");
            str_const.add("label", label);
            str_const.add("tag", class_names.indexOf("String"));
            str_const.add("size", calculateStringSize(value));
            str_const.add("dispTab", "String_dispTab");
            str_const.add("lengthLabel", len_label);
            str_const.add("value", escapeString(value));

            data_section.add("e", str_const);
        }
    }

    private void initIntAndBoolConstants() {
        for (Map.Entry<Integer, String> entry : int_const.entrySet()) {
            int value = entry.getKey();
            String label = entry.getValue();

            ST int_const = templates.getInstanceOf("standardObject");
            int_const.add("label", label);
            int_const.add("tag", class_names.indexOf("Int"));
            int_const.add("dispTab", "Int_dispTab");
            int_const.add("value", value);

            data_section.add("e", int_const);
        }

        ST bool_const0 = templates.getInstanceOf("standardObject");
        bool_const0.add("label", "bool_const0");
        bool_const0.add("tag", class_names.indexOf("Bool"));
        bool_const0.add("dispTab", "Bool_dispTab");
        bool_const0.add("value", 0);
        data_section.add("e", bool_const0);

        ST bool_const1 = templates.getInstanceOf("standardObject");
        bool_const1.add("label", "bool_const1");
        bool_const1.add("tag", class_names.indexOf("Bool"));
        bool_const1.add("dispTab", "Bool_dispTab");
        bool_const1.add("value", 1);
        data_section.add("e", bool_const1);
    }

    private void addClassNameTab() {
        ST class_name_tab = templates.getInstanceOf("sequence");
        class_name_tab.add("e", "class_nameTab:");

        for (String class_name : class_names) {
            String label = string_constants.get(class_name);
            class_name_tab.add("e", "    .word   " + label);
        }

        data_section.add("e", class_name_tab);

        ST class_name_obj = templates.getInstanceOf("sequence");
        class_name_obj.add("e", "class_objTab:");
        data_section.add("e", class_name_obj);

        for (String class_name : class_names) {
            ST class_obj = templates.getInstanceOf("classObj");
            class_obj.add("name", class_name);
            data_section.add("e", class_obj);
        }
    }

    private void initPrototypesAndDispatchTables() {
        for (int i = 0; i < class_names.size(); i++) {
            String class_name = class_names.get(i);
            ClassSymbol cls = (ClassSymbol) SymbolTable.globals.lookup(class_name);

            int num_attrs = (cls != null) ? countAllAttributes(cls) : 0;
            int size = 3 + num_attrs;

            if (class_name.equals("String")) {
                initStringPrototype(i);
            } else if (class_name.equals("Int") || class_name.equals("Bool")) {
                ST val_attr = templates.getInstanceOf("sequence");
                val_attr.add("e", "    .word   0");
                initPrototypeWithAttrs(class_name, i, val_attr);
            } else {
                initSimplePrototype(class_name, i, size);
            }

            LinkedHashMap<String, String> dispatchMap = buildDispatchTableMap(cls);
            initDispatchTable(class_name, new ArrayList<>(dispatchMap.values()));
        }
    }

    private String getDefaultValue(String typeName) {
        switch (typeName) {
            case "Int" -> {
                addInt(0);
                return int_const.get(0);
            }
            case "String" -> {
                addString("");
                return string_constants.get("");
            }
            case "Bool" -> {
                return "bool_const0";
            }
            default -> {
                return "0";
            }
        }
    }

    private void initStringPrototype(int tag) {
        ST string_proto = templates.getInstanceOf("sequence");
        string_proto.add("e", "String_protObj:");
        string_proto.add("e", "    .word   " + tag);
        string_proto.add("e", "    .word   5");
        string_proto.add("e", "    .word   String_dispTab");
        string_proto.add("e", "    .word   int_const0");
        string_proto.add("e", "    .asciiz \"\"");
        string_proto.add("e", "    .align  2");
        data_section.add("e", string_proto);
    }

    private LinkedHashMap<String, String> buildDispatchTableMap(ClassSymbol cls) {
        LinkedHashMap<String, String> map;
        if (cls == null) return new LinkedHashMap<>();

        if (cls.getInherited_class() != null) {
            map = buildDispatchTableMap(cls.getInherited_class());
        } else {
            map = new LinkedHashMap<>();
            map.put("abort", "Object.abort");
            map.put("type_name", "Object.type_name");
            map.put("copy", "Object.copy");
        }

        if (cls.getName().equals("IO")) {
            map.put("out_string", "IO.out_string");
            map.put("out_int", "IO.out_int");
            map.put("in_string", "IO.in_string");
            map.put("in_int", "IO.in_int");
        } else if (cls.getName().equals("String")) {
            map.put("length", "String.length");
            map.put("concat", "String.concat");
            map.put("substr", "String.substr");
        }

        if (cls.functions != null) {
            for (Map.Entry<String, FunctionSymbol> entry : cls.functions.entrySet()) {
                map.put(entry.getKey(), cls.getName() + "." + entry.getKey());
            }
        }
        return map;
    }

    private void initSimplePrototype(String className, int tag, int size) {
        ClassSymbol cls = (ClassSymbol) SymbolTable.globals.lookup(className);

        ST proto = templates.getInstanceOf("sequence");
        proto.add("e", className + "_protObj:");
        proto.add("e", "    .word   " + tag);
        proto.add("e", "    .word   " + size);
        proto.add("e", "    .word   " + className + "_dispTab");

        if (cls != null && size > 3) {
            List<ClassSymbol> hierarchy = new ArrayList<>();
            ClassSymbol current = cls;
            while (current != null) {
                hierarchy.addFirst(current);
                current = current.getInherited_class();
            }

            for (ClassSymbol c : hierarchy) {
                for (Map.Entry<String, Symbol> entry : c.attributes.entrySet()) {
                    Symbol sym = entry.getValue();
                    String defaultVal = "0";
                    if (sym instanceof IdSymbol) {
                        TypeSymbol type = ((IdSymbol) sym).getType();
                        if (type != null) {
                            defaultVal = getDefaultValue(type.getName());
                        }
                    }
                    proto.add("e", "    .word   " + defaultVal);
                }
            }
        }

        proto.add("e", "");
        data_section.add("e", proto);
    }

    private void initPrototypeWithAttrs(String className, int tag, ST attrs) {
        ST proto = templates.getInstanceOf("sequence");
        proto.add("e", className + "_protObj:");
        proto.add("e", "    .word   " + tag);
        proto.add("e", "    .word   " + 4);
        proto.add("e", "    .word   " + className + "_dispTab");
        proto.add("e", attrs);
        data_section.add("e", proto);
    }

    private void initDispatchTable(String className, List<String> methods) {
        ST disp = templates.getInstanceOf("sequence");
        disp.add("e", className + "_dispTab:");
        for (String method : methods) {
            disp.add("e", "    .word   " + method);
        }
        data_section.add("e", disp);
    }

    private void initHeapStart() {
        ST heap = templates.getInstanceOf("sequence");
        heap.add("e", "    .globl  heap_start");
        heap.add("e", "heap_start:");
        heap.add("e", "    .word   0");
        data_section.add("e", heap);
    }

    private void addTextHeader() {
        ST text_header = templates.getInstanceOf("sequence");
        text_header.add("e", "    .globl  Int_init");
        text_header.add("e", "    .globl  String_init");
        text_header.add("e", "    .globl  Bool_init");
        text_header.add("e", "    .globl  Main_init");
        text_header.add("e", "    .globl  Main.main");
        text_section.add("e", text_header);
    }

    private void generatePredefinedInits() {
        generateInit("Object", null, null);
        generateInit("IO", "Object", null);
        generateInit("Int", "Object", null);
        generateInit("String", "Object", null);
        generateInit("Bool", "Object", null);
    }

    private int countAllAttributes(ClassSymbol cls) {
        if (cls == null) return 0;
        int count = 0;
        if (cls.getInherited_class() != null) {
            count += countAllAttributes(cls.getInherited_class());
        }
        count += cls.attributes.size();
        return count;
    }

    private void generateInit(String className, String parent, ASTNode.ClassDef classDef) {
        ST init = templates.getInstanceOf("sequence");
        init.add("e", className + "_init:");
        init.add("e", "    addiu   $sp $sp -12");
        init.add("e", "    sw      $fp 12($sp)");
        init.add("e", "    sw      $s0 8($sp)");
        init.add("e", "    sw      $ra 4($sp)");
        init.add("e", "    addiu   $fp $sp 4");
        init.add("e", "    move    $s0 $a0");

        if (parent != null) {
            init.add("e", "    jal     " + parent + "_init");
        }

        if (classDef != null) {
            ClassSymbol cls = currentClass;
            int base_offset = 12;

            if (cls.getInherited_class() != null) {
                base_offset += countAllAttributes(cls.getInherited_class()) * 4;
            }

            int offset = base_offset;
            for (ASTNode.Feature feature : classDef.features) {
                if (feature instanceof ASTNode.AssignFeatures attr) {
                    if (attr.exp != null) {
                        ST expr_code = attr.exp.accept(this);
                        init.add("e", expr_code);
                        init.add("e", "    sw      $a0 " + offset + "($s0)");
                    }
                    offset += 4;
                }
            }
        }

        init.add("e", "    move    $a0 $s0");
        init.add("e", "    lw      $fp 12($sp)");
        init.add("e", "    lw      $s0 8($sp)");
        init.add("e", "    lw      $ra 4($sp)");
        init.add("e", "    addiu   $sp $sp 12");
        init.add("e", "    jr      $ra");

        text_section.add("e", init);
    }

    @Override
    public ST visit(ASTNode.Program program) {
        data_section = templates.getInstanceOf("sequenceSpaced");
        text_section = templates.getInstanceOf("sequenceSpaced");

        inheritanceTree.put("Object", new ArrayList<>());
        inheritanceTree.get("Object").add("IO");
        inheritanceTree.get("Object").add("Int");
        inheritanceTree.get("Object").add("String");
        inheritanceTree.get("Object").add("Bool");

        inheritanceTree.put("IO", new ArrayList<>());
        inheritanceTree.put("Int", new ArrayList<>());
        inheritanceTree.put("String", new ArrayList<>());
        inheritanceTree.put("Bool", new ArrayList<>());

        for (ASTNode.ClassDef class_def : program.classes) {
            String name = class_def.type.getToken().getText();
            String parent = (class_def.inheritsType != null) ? class_def.inheritsType.getToken().getText() : "Object";

            inheritanceTree.putIfAbsent(name, new ArrayList<>());
            inheritanceTree.putIfAbsent(parent, new ArrayList<>());
            inheritanceTree.get(parent).add(name);
        }

        class_names.clear();
        dfs("Object");

        collectAllConstants(program);
        addGlobalDirectives();
        addClassTags();
        initStringConstants();
        initIntAndBoolConstants();
        addClassNameTab();
        initPrototypesAndDispatchTables();
        initHeapStart();

        addTextHeader();
        generatePredefinedInits();

        for (ASTNode.ClassDef class_def : program.classes) {
            class_def.accept(this);
        }

        ST program_st = templates.getInstanceOf("program");
        program_st.add("data", data_section);
        program_st.add("textFuncs", text_section);

        return program_st;
    }

    private void dfs(String current) {
        class_names.add(current);
        List<String> children = inheritanceTree.get(current);
        if (children != null) {
            for (String child : children) {
                dfs(child);
            }
        }
    }

    @Override
    public ST visit(ASTNode.ClassDef classDef) {
        currentClass = (ClassSymbol) SymbolTable.globals.lookup(classDef.type.getToken().getText());
        String class_name = classDef.type.getToken().getText();
        String parent = (classDef.inheritsType != null) ? classDef.inheritsType.getToken().getText() : "Object";
        generateInit(class_name, parent, classDef);
        for (ASTNode.Feature feature : classDef.features) {
            if (feature instanceof ASTNode.FunctionsFeatures) {
                feature.accept(this);
            }
        }
        return null;
    }

    @Override
    public ST visit(ASTNode.FunctionsFeatures functionsFeatures) {
        String class_name = currentClass.getName();
        String method_name = functionsFeatures.id.getToken().getText();

        currentMethod = currentClass.functions.get(method_name);
        int num_formals = currentMethod.getFormals().size();

        ST method = templates.getInstanceOf("sequence");
        method.add("e", class_name + "." + method_name + ":");
        method.add("e", "    addiu   $sp $sp -12");
        method.add("e", "    sw      $fp 12($sp)");
        method.add("e", "    sw      $s0 8($sp)");
        method.add("e", "    sw      $ra 4($sp)");
        method.add("e", "    addiu   $fp $sp 4");
        method.add("e", "    move    $s0 $a0");

        ST body = functionsFeatures.body.accept(this);
        method.add("e", body);

        method.add("e", "    lw      $fp 12($sp)");
        method.add("e", "    lw      $s0 8($sp)");
        method.add("e", "    lw      $ra 4($sp)");
        method.add("e", "    addiu   $sp $sp 12");
        
        if (num_formals > 0) {
            method.add("e", "    addiu   $sp $sp " + (num_formals * 4));
        }

        method.add("e", "    jr      $ra");

        text_section.add("e", method);
        return null;
    }

    @Override
    public ST visit(ASTNode.IntType intLiteral) {
        int value = Integer.parseInt(intLiteral.getToken().getText());
        String label = int_const.get(value);
        ST code = templates.getInstanceOf("sequence");
        code.add("e", "    la      $a0 " + label);
        return code;
    }

    @Override
    public ST visit(ASTNode.StringType stringType) {
        String value = stringType.getToken().getText();
        String label = string_constants.get(value);
        ST code = templates.getInstanceOf("sequence");
        code.add("e", "    la      $a0 " + label);
        return code;
    }

    @Override
    public ST visit(ASTNode.TrueType trueType) {
        ST code = templates.getInstanceOf("sequence");
        code.add("e", "    la      $a0 bool_const1");
        return code;
    }

    @Override
    public ST visit(ASTNode.FalseType falseType) {
        ST code = templates.getInstanceOf("sequence");
        code.add("e", "    la      $a0 bool_const0");
        return code;
    }

    @Override
    public ST visit(ASTNode.IDtype iDtype) {
        String var_name = iDtype.getToken().getText();
        ST code = templates.getInstanceOf("sequence");

        if (var_name.equals("self")) {
            code.add("e", "    move    $a0 $s0");
            return code;
        }

        // Check let variables first
        if (letVariableOffsets.containsKey(var_name)) {
            int offset = letVariableOffsets.get(var_name);
            code.add("e", "    lw      $a0 " + offset + "($fp)");
            return code;
        }

        // Check formal parameters
        int formalOffset = findFormalOffset(var_name);
        if (formalOffset >= 0) {
            code.add("e", "    lw      $a0 " + formalOffset + "($fp)");
            return code;
        }

        // Look up attribute
        int offset = findAttributeOffset(currentClass, var_name);
        if (offset >= 0) {
            code.add("e", "    lw      $a0 " + offset + "($s0)");
            return code;
        }

        code.add("e", "    li      $a0 0");
        return code;
    }

    private int findAttributeOffset(ClassSymbol cls, String attrName) {
        // Build a list of classes from Object down to the current class
        List<ClassSymbol> hierarchy = new ArrayList<>();
        ClassSymbol current = cls;
        while (current != null) {
            hierarchy.addFirst(current);
            current = current.getInherited_class();
        }

        int offset = 12;
        for (ClassSymbol c : hierarchy) {
            for (String attr : c.attributes.keySet()) {
                if (attr.equals(attrName)) {
                    return offset;
                }
                offset += 4;
            }
        }
        return -1;
    }

    private String inferType(ASTNode.Expression expr) {
        if (expr instanceof ASTNode.NewTypeNode nt) {
            String typeName = nt.type.getToken().getText();
            if (typeName.equals("SELF_TYPE")) return currentClass.getName();
            return typeName;
        }
        if (expr instanceof ASTNode.FuncCallNode call) {
            return getReturnType(currentClass.getName(), call.id.getToken().getText());
        }
        if (expr instanceof ASTNode.FuncCallClassNode call) {
            String objType = inferType(call.object);
            return getReturnType(objType, call.id.getToken().getText());
        }
        if (expr instanceof ASTNode.IDtype id) {
            String name = id.getToken().getText();
            if (name.equals("self")) return currentClass.getName();

            // Check let variables
            if (letVariableTypes.containsKey(name)) {
                return letVariableTypes.get(name);
            }

            // Check formals
            if (currentMethod != null) {
                Map<String, Symbol> formals = currentMethod.getFormals();
                if (formals.containsKey(name)) {
                    Symbol sym = formals.get(name);
                    if (sym instanceof IdSymbol) {
                        TypeSymbol type = ((IdSymbol) sym).getType();
                        if (type != null) return type.getName();
                    }
                }
            }

            // Check attributes
            ClassSymbol cls = currentClass;
            while (cls != null) {
                if (cls.attributes.containsKey(name)) {
                    Symbol sym = cls.attributes.get(name);
                    if (sym instanceof IdSymbol) {
                        TypeSymbol type = ((IdSymbol) sym).getType();
                        if (type != null) return type.getName();
                    }
                }
                cls = cls.getInherited_class();
            }
        }
        if (expr instanceof ASTNode.StringType) return "String";
        if (expr instanceof ASTNode.IntType) return "Int";
        if (expr instanceof ASTNode.TrueType || expr instanceof ASTNode.FalseType) return "Bool";
        if (expr instanceof ASTNode.ParenNode paren) return inferType(paren.exp);
        return "Object";
    }

    private String getReturnType(String className, String methodName) {
        if (className.equals("SELF_TYPE")) className = currentClass.getName();
        ClassSymbol cls = (ClassSymbol) SymbolTable.globals.lookup(className);
        while (cls != null) {
            if (cls.functions != null && cls.functions.containsKey(methodName)) {
                return cls.functions.get(methodName).getType().getName();
            }
            cls = cls.getInherited_class();
        }
        return "Object";
    }

    private void addArguments(ST code, List<ASTNode.Expression> arguments) {
        for (int i = arguments.size() - 1; i >= 0; i--) {
            ASTNode.Expression arg = arguments.get(i);
            code.add("e", arg.accept(this));
            code.add("e", "    sw      $a0 0($sp)");
            code.add("e", "    addiu   $sp $sp -4");
        }
    }

    @Override
    public ST visit(ASTNode.FuncCallClassNode funcCallClassNode) {
        ST code = templates.getInstanceOf("sequence");

        addArguments(code, funcCallClassNode.arguments);

        code.add("e", funcCallClassNode.object.accept(this));

        int label_id = labelCounter++;
        String full_path = funcCallClassNode.getToken().getInputStream().getSourceName();
        String filename = new java.io.File(full_path).getName();
        String filename_label = string_constants.get(filename);
        int line = funcCallClassNode.getToken().getLine();

        code.add("e", "    bne     $a0 $zero not_void_" + label_id);
        code.add("e", "    la      $a0 " + filename_label);
        code.add("e", "    li      $t1 " + line);
        code.add("e", "    jal     _dispatch_abort");
        code.add("e", "not_void_" + label_id + ":");

        String class_name;
        if (funcCallClassNode.atType != null) {
            class_name = funcCallClassNode.atType.getToken().getText();
        } else {
            class_name = inferType(funcCallClassNode.object);
        }

        String method_name = funcCallClassNode.id.getToken().getText();

        if (funcCallClassNode.atType != null) {
            code.add("e", "    la      $t0 " + class_name + "_dispTab");
            int offset = getDispatchOffset(class_name, method_name);
            code.add("e", "    lw      $t0 " + offset + "($t0)");
        } else {
            int offset = getDispatchOffset(class_name, method_name);
            code.add("e", "    lw      $t0 8($a0)");
            code.add("e", "    lw      $t0 " + offset + "($t0)");
        }

        code.add("e", "    jalr    $t0");
        return code;
    }

    private int getDispatchOffset(String className, String methodName) {
        if (className.equals("SELF_TYPE")) className = currentClass.getName();
        ClassSymbol cls = (ClassSymbol) SymbolTable.globals.lookup(className);
        if (cls == null) return 0;
        LinkedHashMap<String, String> dispatch_map = buildDispatchTableMap(cls);
        int index = 0;
        for (String key : dispatch_map.keySet()) {
            if (key.equals(methodName)) return index * 4;
            index++;
        }
        return 0;
    }

    @Override
    public ST visit(ASTNode.FuncCallNode funcCallNode) {
        ST code = templates.getInstanceOf("sequence");

        addArguments(code, funcCallNode.arguments);
        code.add("e", "    move    $a0 $s0");
        String method_name = funcCallNode.id.getToken().getText();
        int offset = getDispatchOffset(currentClass.getName(), method_name);
        code.add("e", "    lw      $t0 8($a0)");
        code.add("e", "    lw      $t0 " + offset + "($t0)");
        code.add("e", "    jalr    $t0");
        return code;
    }

    @Override
    public ST visit(ASTNode.ParenNode parenNode) {
        return parenNode.exp.accept(this);
    }

    @Override
    public ST visit(ASTNode.BlockNode blockNode) {
        ST result = templates.getInstanceOf("sequence");
        for (ASTNode.Expression expr : blockNode.expressions) {
            result.add("e", expr.accept(this));
        }
        return result;
    }

    private int findFormalOffset(String formalName) {
        if (currentMethod == null) return -1;

        Map<String, Symbol> formals = currentMethod.getFormals();
        int index = 0;

        for (String name : formals.keySet()) {
            if (name.equals(formalName)) {
                return 12 + index * 4;
            }
            index++;
        }
        return -1;
    }

    @Override
    public ST visit(ASTNode.AssignNode assignNode) {
        ST code = templates.getInstanceOf("sequence");
        String var_name = assignNode.id.getToken().getText();

        code.add("e", assignNode.exp.accept(this));

        if (letVariableOffsets.containsKey(var_name)) {
            int offset = letVariableOffsets.get(var_name);
            code.add("e", "    sw      $a0 " + offset + "($fp)");
            return code;
        }

        int formal_offset = findFormalOffset(var_name);
        if (formal_offset >= 0) {
            code.add("e", "    sw      $a0 " + formal_offset + "($fp)");
            return code;
        }

        int offset = findAttributeOffset(currentClass, var_name);
        if (offset >= 0) {
            code.add("e", "    sw      $a0 " + offset + "($s0)");
        }

        return code;
    }

    @Override
    public ST visit(ASTNode.LetNode letNode) {
        ST code = templates.getInstanceOf("sequence");

        int num_vars = letNode.localVars.size();
        int saved_let_offset = currentLetOffset;
        Map<String, Integer> saved_offsets = new HashMap<>(letVariableOffsets);
        Map<String, String> saved_types = new HashMap<>(letVariableTypes);

        for (ASTNode.LocalVarNode var : letNode.localVars) {
            String var_name = var.id.getToken().getText();
            String type_name = var.type.getToken().getText();

            currentLetOffset += 4;
            int var_offset = -currentLetOffset;
            letVariableOffsets.put(var_name, var_offset);
            letVariableTypes.put(var_name, type_name);

            if (var.initExpr != null) {
                code.add("e", var.initExpr.accept(this));
            } else {
                switch (type_name) {
                    case "Int" -> {
                        addInt(0);
                        code.add("e", "    la      $a0 " + int_const.get(0));
                    }
                    case "String" -> {
                        addString("");
                        code.add("e", "    la      $a0 " + string_constants.get(""));
                    }
                    case "Bool" -> code.add("e", "    la      $a0 bool_const0");
                    default -> code.add("e", "    li      $a0 0");
                }
            }

            code.add("e", "    addiu   $sp $sp -4");
            code.add("e", "    sw      $a0 " + var_offset + "($fp)");
        }

        code.add("e", letNode.body.accept(this));

        if (num_vars > 0) {
            code.add("e", "    addiu   $sp $sp " + (num_vars * 4));
        }

        currentLetOffset = saved_let_offset;
        letVariableOffsets = saved_offsets;
        letVariableTypes = saved_types;

        return code;
    }

    @Override
    public ST visit(ASTNode.LocalVarNode localVarNode) {
        return null;
    }

    @Override
    public ST visit(ASTNode.NewTypeNode newTypeNode) {
        ST code = templates.getInstanceOf("sequence");
        String type_name = newTypeNode.type.getToken().getText();

        if (type_name.equals("SELF_TYPE")) {
            code.add("e", "    la      $t0 class_objTab");
            code.add("e", "    lw      $t1 0($s0)");
            code.add("e", "    sll     $t1 $t1 3");
            code.add("e", "    addu    $t0 $t0 $t1");
            code.add("e", "    lw      $a0 0($t0)");
            code.add("e", "    jal     Object.copy");
            code.add("e", "    la      $t0 class_objTab");
            code.add("e", "    lw      $t1 0($s0)");
            code.add("e", "    sll     $t1 $t1 3");
            code.add("e", "    addu    $t0 $t0 $t1");
            code.add("e", "    lw      $t0 4($t0)");
            code.add("e", "    jalr    $t0");
        } else {
            code.add("e", "    la      $a0 " + type_name + "_protObj");
            code.add("e", "    jal     Object.copy");
            code.add("e", "    jal     " + type_name + "_init");
        }

        return code;
    }

    @Override
    public ST visit(ASTNode.IfNode ifNode) {
        ST code = templates.getInstanceOf("sequence");

        int label_id = labelCounter++;
        String else_branch = "else_branch_" + label_id;
        String end_if = "end_if_" + label_id;

        code.add("e", ifNode.condition.accept(this));
        code.add("e", "    lw      $t0 12($a0)");
        code.add("e", "    beqz    $t0 " + else_branch);
        code.add("e", ifNode.thenExp.accept(this));
        code.add("e", "    b       " + end_if);
        code.add("e", else_branch + ":");
        code.add("e", ifNode.elseExp.accept(this));
        code.add("e", end_if + ":");

        return code;
    }

    @Override
    public ST visit(ASTNode.IsvoidNode isvoidNode) {
        ST code = templates.getInstanceOf("sequence");

        code.add("e", isvoidNode.exp.accept(this));

        int label_id = labelCounter++;
        String is_void_label = "is_void_" + label_id;
        String end_void_label = "end_void_" + label_id;

        code.add("e", "    beqz    $a0 " + is_void_label);
        code.add("e", "    la      $a0 bool_const0");
        code.add("e", "    b       " + end_void_label);
        code.add("e", is_void_label + ":");
        code.add("e", "    la      $a0 bool_const1");
        code.add("e", end_void_label + ":");

        return code;
    }

    @Override
    public ST visit(ASTNode.NotNode notNode) {
        ST code = templates.getInstanceOf("sequence");

        code.add("e", notNode.exp.accept(this));
        code.add("e", "    lw      $t0 12($a0)");

        int label_id = labelCounter++;
        String was_true = "was_true_" + label_id;
        String end_not = "end_not_" + label_id;

        code.add("e", "    beqz    $t0 " + was_true);
        code.add("e", "    la      $a0 bool_const0");
        code.add("e", "    b       " + end_not);
        code.add("e", was_true + ":");
        code.add("e", "    la      $a0 bool_const1");
        code.add("e", end_not + ":");

        return code;
    }

    private ST addOperation(ASTNode left, ASTNode right, String instruction) {
        ST code = templates.getInstanceOf("sequence");

        code.add("e", left.accept(this));

        code.add("e", "    sw      $a0 0($sp)");
        code.add("e", "    addiu   $sp $sp -4");

        code.add("e", right.accept(this));
        code.add("e", "    jal     Object.copy");

        code.add("e", "    addiu   $sp $sp 4");
        code.add("e", "    lw      $t1 0($sp)");

        code.add("e", "    lw      $t1 12($t1)");
        code.add("e", "    lw      $t2 12($a0)");

        code.add("e", String.format("    %s     $t1 $t1 $t2", instruction));
        code.add("e", "    sw      $t1 12($a0)");

        return code;
    }

    @Override
    public ST visit(ASTNode.PlusNode plusNode) {
        return addOperation(plusNode.left, plusNode.right, "add");
    }

    @Override
    public ST visit(ASTNode.MinusNode minusNode) {
        return addOperation(minusNode.left, minusNode.right, "sub");
    }

    @Override
    public ST visit(ASTNode.MulNode mulNode) {
        return addOperation(mulNode.left, mulNode.right, "mul");
    }

    @Override
    public ST visit(ASTNode.DivNode divNode) {
        return addOperation(divNode.left, divNode.right, "div");
    }

    @Override
    public ST visit(ASTNode.TildeNode tildeNode) {
        ST code = templates.getInstanceOf("sequence");

        code.add("e", tildeNode.exp.accept(this));
        code.add("e", "    jal     Object.copy");
        code.add("e", "    lw      $t1 12($a0)");
        code.add("e", "    neg     $t1 $t1");
        code.add("e", "    sw      $t1 12($a0)");

        return code;
    }

    private ST addComparison(ASTNode left, ASTNode right, String branchOp) {
        ST code = templates.getInstanceOf("sequence");

        code.add("e", left.accept(this));
        code.add("e", "    sw      $a0 0($sp)");
        code.add("e", "    addiu   $sp $sp -4");
        code.add("e", right.accept(this));
        code.add("e", "    addiu   $sp $sp 4");
        code.add("e", "    lw      $t1 0($sp)");
        code.add("e", "    lw      $t1 12($t1)");
        code.add("e", "    lw      $t2 12($a0)");

        int labelId = labelCounter++;
        String trueLabel = "comp_true_" + labelId;
        String endLabel = "comp_end_" + labelId;

        code.add("e", String.format("    %s     $t1 $t2 %s", branchOp, trueLabel));
        code.add("e", "    la      $a0 bool_const0");
        code.add("e", "    b       " + endLabel);
        code.add("e", trueLabel + ":");
        code.add("e", "    la      $a0 bool_const1");
        code.add("e", endLabel + ":");

        return code;
    }

    @Override
    public ST visit(ASTNode.LtNode ltNode) {
        return addComparison(ltNode.left, ltNode.right, "blt");
    }

    @Override
    public ST visit(ASTNode.LeNode leNode) {
        return addComparison(leNode.left, leNode.right, "ble");
    }

    @Override
    public ST visit(ASTNode.EqualNode equalNode) {
        ST code = templates.getInstanceOf("sequence");

        code.add("e", equalNode.left.accept(this));
        code.add("e", "    sw      $a0 0($sp)");
        code.add("e", "    addiu   $sp $sp -4");

        code.add("e", equalNode.right.accept(this));
        code.add("e", "    move    $t2 $a0");

        code.add("e", "    addiu   $sp $sp 4");
        code.add("e", "    lw      $t1 0($sp)");

        int label_id = labelCounter++;
        String end_label = "eq_end_" + label_id;

        code.add("e", "    la      $a0 bool_const1");
        code.add("e", "    la      $a1 bool_const0");
        code.add("e", "    beq     $t1 $t2 " + end_label);
        code.add("e", "    jal     equality_test");
        code.add("e", end_label + ":");

        return code;
    }

    @Override
    public ST visit(ASTNode.WhileNode whileNode) {
        ST code = templates.getInstanceOf("sequence");

        int label_id = labelCounter++;
        String loop_label = "while_loop_" + label_id;
        String end_label = "while_end_" + label_id;

        code.add("e", loop_label + ":");
        code.add("e", whileNode.condition.accept(this));
        code.add("e", "    lw      $t0 12($a0)");
        code.add("e", "    beqz    $t0 " + end_label);
        code.add("e", whileNode.body.accept(this));
        code.add("e", "    b       " + loop_label);
        code.add("e", end_label + ":");
        code.add("e", "    li      $a0 0");

        return code;
    }

    @Override
    public ST visit(ASTNode.CaseNode caseNode) {
        ST code = templates.getInstanceOf("sequence");

        int label_id = labelCounter++;
        String end_label = "case_end_" + label_id;

        String full_path = caseNode.getToken().getInputStream().getSourceName();
        String filename = new java.io.File(full_path).getName();
        String filename_label = string_constants.get(filename);
        int line = caseNode.getToken().getLine();

        code.add("e", caseNode.condition.accept(this));
        code.add("e", "    bne     $a0 $zero case_not_void_" + label_id);
        code.add("e", "    la      $a0 " + filename_label);
        code.add("e", "    li      $t1 " + line);
        code.add("e", "    jal     _case_abort2");
        code.add("e", "case_not_void_" + label_id + ":");

        code.add("e", "    sw      $a0 0($sp)");
        code.add("e", "    addiu   $sp $sp -4");
        code.add("e", "    lw      $t0 0($a0)");

        List<ASTNode.CaseMethodNode> sorted_branches = new ArrayList<>(caseNode.cases);
        sorted_branches.sort((a, b) -> {
            int tag_a = class_names.indexOf(a.type.getToken().getText());
            int tag_b = class_names.indexOf(b.type.getToken().getText());
            return tag_b - tag_a;
        });

        int branch_num = 0;
        for (ASTNode.CaseMethodNode branch : sorted_branches) {
            String branch_type = branch.type.getToken().getText();
            int branch_tag = class_names.indexOf(branch_type);
            int max_child_tag = getMaxChildTag(branch_type);

            String branch_label = "case_branch_" + label_id + "_" + branch_num;
            String next_label = "case_next_" + label_id + "_" + branch_num;

            code.add("e", "    li      $t1 " + branch_tag);
            code.add("e", "    blt     $t0 $t1 " + next_label);
            code.add("e", "    li      $t1 " + max_child_tag);
            code.add("e", "    bgt     $t0 $t1 " + next_label);

            code.add("e", branch_label + ":");

            int saved_let_offset = currentLetOffset;
            Map<String, Integer> saved_offsets = new HashMap<>(letVariableOffsets);
            Map<String, String> saved_types = new HashMap<>(letVariableTypes);

            String var_name = branch.id.getToken().getText();
            currentLetOffset += 4;
            int var_offset = -currentLetOffset;
            letVariableOffsets.put(var_name, var_offset);
            letVariableTypes.put(var_name, branch_type);

            code.add("e", "    addiu   $sp $sp 4");
            code.add("e", "    lw      $a0 0($sp)");
            code.add("e", "    addiu   $sp $sp -4");
            code.add("e", "    sw      $a0 " + var_offset + "($fp)");

            code.add("e", branch.cases.accept(this));

            code.add("e", "    addiu   $sp $sp 4");

            currentLetOffset = saved_let_offset;
            letVariableOffsets = saved_offsets;
            letVariableTypes = saved_types;

            code.add("e", "    b       " + end_label);
            code.add("e", next_label + ":");
            branch_num++;
        }

        code.add("e", "    addiu   $sp $sp 4");
        code.add("e", "    lw      $a0 0($sp)");
        code.add("e", "    jal     _case_abort");

        code.add("e", end_label + ":");

        return code;
    }

    private int getMaxChildTag(String className) {
        int tag = class_names.indexOf(className);
        List<String> children = inheritanceTree.get(className);
        if (children != null) {
            for (String child : children) {
                int child_max = getMaxChildTag(child);
                if (child_max > tag) {
                    tag = child_max;
                }
            }
        }
        return tag;
    }

    @Override
    public ST visit(ASTNode.AssignFeatures assignFeatures) {
        return null;
    }

    @Override
    public ST visit(ASTNode.Formal formal) {
        return null;
    }

    @Override
    public ST visit(ASTNode.IDNode idNode) {
        return null;
    }

    @Override
    public ST visit(ASTNode.TypeNode typeNode) {
        return null;
    }

    @Override
    public ST visit(ASTNode.CaseMethodNode caseMethodNode) {
        return null;
    }
}