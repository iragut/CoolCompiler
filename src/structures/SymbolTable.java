package cool.structures;

import java.io.File;

import org.antlr.v4.runtime.*;

import cool.compiler.Compiler;
import cool.parser.CoolParser;

public class SymbolTable {
    public static Scope globals;
    
    private static boolean semanticErrors;
    
    public static void defineBasicClasses() {
        globals = new DefaultScope(null);
        semanticErrors = false;

        ClassSymbol object_class = new ClassSymbol("Object", globals);
        globals.add(object_class);

        ClassSymbol int_class = new ClassSymbol("Int", globals);
        int_class.setInherited_class(object_class);
        globals.add(int_class);

        ClassSymbol bool_class = new ClassSymbol("Bool", globals);
        bool_class.setInherited_class(object_class);
        globals.add(bool_class);

        ClassSymbol string_class = new ClassSymbol("String", globals);
        string_class.setInherited_class(object_class);
        globals.add(string_class);

        ClassSymbol io_class = new ClassSymbol("IO", globals);
        io_class.setInherited_class(object_class);
        globals.add(io_class);

        // abort() : Object
        FunctionSymbol abort_func = new FunctionSymbol(object_class, "abort", object_class);
        abort_func.type = object_class.getType();
        object_class.add(abort_func);

        // type_name() : String
        FunctionSymbol type_name_func = new FunctionSymbol(object_class, "type_name", object_class);
        type_name_func.type = string_class.getType();
        object_class.add(type_name_func);

        // copy() : SELF_TYPE
        FunctionSymbol copy_func = new FunctionSymbol(object_class, "copy", object_class);
        copy_func.type = TypeSymbol.SELF_TYPE;
        object_class.add(copy_func);

        // length() : Int
        FunctionSymbol length_func = new FunctionSymbol(string_class, "length", string_class);
        length_func.type = int_class.getType();
        string_class.add(length_func);

        // concat(s : String) : String
        FunctionSymbol concat_func = new FunctionSymbol(string_class, "concat", string_class);
        concat_func.type = string_class.getType();

        IdSymbol concat_param = new IdSymbol("s");
        concat_param.setType(string_class.getType());
        concat_func.add(concat_param);

        string_class.add(concat_func);

        // substr(i : Int, l : Int) : String
        FunctionSymbol substr_func = new FunctionSymbol(string_class, "substr", string_class);
        substr_func.type = string_class.getType();

        IdSymbol substr_param1 = new IdSymbol("i");
        substr_param1.setType(int_class.getType());
        substr_func.add(substr_param1);

        IdSymbol substr_param2 = new IdSymbol("l");
        substr_param2.setType(int_class.getType());
        substr_func.add(substr_param2);

        string_class.add(substr_func);

        // out_string(x : String) : SELF_TYPE
        FunctionSymbol out_string_func = new FunctionSymbol(io_class, "out_string", io_class);
        out_string_func.type = TypeSymbol.SELF_TYPE;

        IdSymbol out_string_param = new IdSymbol("x");
        out_string_param.setType(string_class.getType());
        out_string_func.add(out_string_param);

        io_class.add(out_string_func);

        // out_int(x : Int) : SELF_TYPE
        FunctionSymbol out_int_func = new FunctionSymbol(io_class, "out_int", io_class);
        out_int_func.type = TypeSymbol.SELF_TYPE;

        IdSymbol out_int_param = new IdSymbol("x");
        out_int_param.setType(int_class.getType());
        out_int_func.add(out_int_param);

        io_class.add(out_int_func);

        // in_string() : String
        FunctionSymbol in_string_func = new FunctionSymbol(io_class, "in_string", io_class);
        in_string_func.type = string_class.getType();
        io_class.add(in_string_func);

        // in_int() : Int
        FunctionSymbol in_int_func = new FunctionSymbol(io_class, "in_int", io_class);
        in_int_func.type = int_class.getType();
        io_class.add(in_int_func);
    }
    
    /**
     * Displays a semantic error message.
     * 
     * @param ctx Used to determine the enclosing class context of this error,
     *            which knows the file name in which the class was defined.
     * @param info Used for line and column information.
     * @param str The error message.
     */
    public static void error(ParserRuleContext ctx, Token info, String str) {
        while (! (ctx.getParent() instanceof CoolParser.ProgramContext))
            ctx = ctx.getParent();
        
        String message = "\"" + new File(Compiler.fileNames.get(ctx)).getName()
                + "\", line " + info.getLine()
                + ":" + (info.getCharPositionInLine() + 1)
                + ", Semantic error: " + str;
        
        System.err.println(message);
        
        semanticErrors = true;
    }
    
    public static void error(String str) {
        String message = "Semantic error: " + str;
        
        System.err.println(message);
        
        semanticErrors = true;
    }
    
    public static boolean hasSemanticErrors() {
        return semanticErrors;
    }
}
