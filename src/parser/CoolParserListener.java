// Generated from D:/Facultate/Anul-IV/Comp/tema3/src/cool/parser/CoolParser.g4 by ANTLR 4.13.2

    package cool.parser;

import org.antlr.v4.runtime.tree.ParseTreeListener;

/**
 * This interface defines a complete listener for a parse tree produced by
 * {@link CoolParser}.
 */
public interface CoolParserListener extends ParseTreeListener {
	/**
	 * Enter a parse tree produced by {@link CoolParser#program}.
	 * @param ctx the parse tree
	 */
	void enterProgram(CoolParser.ProgramContext ctx);
	/**
	 * Exit a parse tree produced by {@link CoolParser#program}.
	 * @param ctx the parse tree
	 */
	void exitProgram(CoolParser.ProgramContext ctx);
	/**
	 * Enter a parse tree produced by {@link CoolParser#class}.
	 * @param ctx the parse tree
	 */
	void enterClass(CoolParser.ClassContext ctx);
	/**
	 * Exit a parse tree produced by {@link CoolParser#class}.
	 * @param ctx the parse tree
	 */
	void exitClass(CoolParser.ClassContext ctx);
	/**
	 * Enter a parse tree produced by {@link CoolParser#feature}.
	 * @param ctx the parse tree
	 */
	void enterFeature(CoolParser.FeatureContext ctx);
	/**
	 * Exit a parse tree produced by {@link CoolParser#feature}.
	 * @param ctx the parse tree
	 */
	void exitFeature(CoolParser.FeatureContext ctx);
	/**
	 * Enter a parse tree produced by {@link CoolParser#arguments}.
	 * @param ctx the parse tree
	 */
	void enterArguments(CoolParser.ArgumentsContext ctx);
	/**
	 * Exit a parse tree produced by {@link CoolParser#arguments}.
	 * @param ctx the parse tree
	 */
	void exitArguments(CoolParser.ArgumentsContext ctx);
	/**
	 * Enter a parse tree produced by {@link CoolParser#formal}.
	 * @param ctx the parse tree
	 */
	void enterFormal(CoolParser.FormalContext ctx);
	/**
	 * Exit a parse tree produced by {@link CoolParser#formal}.
	 * @param ctx the parse tree
	 */
	void exitFormal(CoolParser.FormalContext ctx);
	/**
	 * Enter a parse tree produced by the {@code new_type}
	 * labeled alternative in {@link CoolParser#expr}.
	 * @param ctx the parse tree
	 */
	void enterNew_type(CoolParser.New_typeContext ctx);
	/**
	 * Exit a parse tree produced by the {@code new_type}
	 * labeled alternative in {@link CoolParser#expr}.
	 * @param ctx the parse tree
	 */
	void exitNew_type(CoolParser.New_typeContext ctx);
	/**
	 * Enter a parse tree produced by the {@code string}
	 * labeled alternative in {@link CoolParser#expr}.
	 * @param ctx the parse tree
	 */
	void enterString(CoolParser.StringContext ctx);
	/**
	 * Exit a parse tree produced by the {@code string}
	 * labeled alternative in {@link CoolParser#expr}.
	 * @param ctx the parse tree
	 */
	void exitString(CoolParser.StringContext ctx);
	/**
	 * Enter a parse tree produced by the {@code while_loop}
	 * labeled alternative in {@link CoolParser#expr}.
	 * @param ctx the parse tree
	 */
	void enterWhile_loop(CoolParser.While_loopContext ctx);
	/**
	 * Exit a parse tree produced by the {@code while_loop}
	 * labeled alternative in {@link CoolParser#expr}.
	 * @param ctx the parse tree
	 */
	void exitWhile_loop(CoolParser.While_loopContext ctx);
	/**
	 * Enter a parse tree produced by the {@code isvoid}
	 * labeled alternative in {@link CoolParser#expr}.
	 * @param ctx the parse tree
	 */
	void enterIsvoid(CoolParser.IsvoidContext ctx);
	/**
	 * Exit a parse tree produced by the {@code isvoid}
	 * labeled alternative in {@link CoolParser#expr}.
	 * @param ctx the parse tree
	 */
	void exitIsvoid(CoolParser.IsvoidContext ctx);
	/**
	 * Enter a parse tree produced by the {@code false}
	 * labeled alternative in {@link CoolParser#expr}.
	 * @param ctx the parse tree
	 */
	void enterFalse(CoolParser.FalseContext ctx);
	/**
	 * Exit a parse tree produced by the {@code false}
	 * labeled alternative in {@link CoolParser#expr}.
	 * @param ctx the parse tree
	 */
	void exitFalse(CoolParser.FalseContext ctx);
	/**
	 * Enter a parse tree produced by the {@code lt}
	 * labeled alternative in {@link CoolParser#expr}.
	 * @param ctx the parse tree
	 */
	void enterLt(CoolParser.LtContext ctx);
	/**
	 * Exit a parse tree produced by the {@code lt}
	 * labeled alternative in {@link CoolParser#expr}.
	 * @param ctx the parse tree
	 */
	void exitLt(CoolParser.LtContext ctx);
	/**
	 * Enter a parse tree produced by the {@code eq}
	 * labeled alternative in {@link CoolParser#expr}.
	 * @param ctx the parse tree
	 */
	void enterEq(CoolParser.EqContext ctx);
	/**
	 * Exit a parse tree produced by the {@code eq}
	 * labeled alternative in {@link CoolParser#expr}.
	 * @param ctx the parse tree
	 */
	void exitEq(CoolParser.EqContext ctx);
	/**
	 * Enter a parse tree produced by the {@code func_call_class}
	 * labeled alternative in {@link CoolParser#expr}.
	 * @param ctx the parse tree
	 */
	void enterFunc_call_class(CoolParser.Func_call_classContext ctx);
	/**
	 * Exit a parse tree produced by the {@code func_call_class}
	 * labeled alternative in {@link CoolParser#expr}.
	 * @param ctx the parse tree
	 */
	void exitFunc_call_class(CoolParser.Func_call_classContext ctx);
	/**
	 * Enter a parse tree produced by the {@code func_call}
	 * labeled alternative in {@link CoolParser#expr}.
	 * @param ctx the parse tree
	 */
	void enterFunc_call(CoolParser.Func_callContext ctx);
	/**
	 * Exit a parse tree produced by the {@code func_call}
	 * labeled alternative in {@link CoolParser#expr}.
	 * @param ctx the parse tree
	 */
	void exitFunc_call(CoolParser.Func_callContext ctx);
	/**
	 * Enter a parse tree produced by the {@code add_sub}
	 * labeled alternative in {@link CoolParser#expr}.
	 * @param ctx the parse tree
	 */
	void enterAdd_sub(CoolParser.Add_subContext ctx);
	/**
	 * Exit a parse tree produced by the {@code add_sub}
	 * labeled alternative in {@link CoolParser#expr}.
	 * @param ctx the parse tree
	 */
	void exitAdd_sub(CoolParser.Add_subContext ctx);
	/**
	 * Enter a parse tree produced by the {@code int}
	 * labeled alternative in {@link CoolParser#expr}.
	 * @param ctx the parse tree
	 */
	void enterInt(CoolParser.IntContext ctx);
	/**
	 * Exit a parse tree produced by the {@code int}
	 * labeled alternative in {@link CoolParser#expr}.
	 * @param ctx the parse tree
	 */
	void exitInt(CoolParser.IntContext ctx);
	/**
	 * Enter a parse tree produced by the {@code not}
	 * labeled alternative in {@link CoolParser#expr}.
	 * @param ctx the parse tree
	 */
	void enterNot(CoolParser.NotContext ctx);
	/**
	 * Exit a parse tree produced by the {@code not}
	 * labeled alternative in {@link CoolParser#expr}.
	 * @param ctx the parse tree
	 */
	void exitNot(CoolParser.NotContext ctx);
	/**
	 * Enter a parse tree produced by the {@code paren}
	 * labeled alternative in {@link CoolParser#expr}.
	 * @param ctx the parse tree
	 */
	void enterParen(CoolParser.ParenContext ctx);
	/**
	 * Exit a parse tree produced by the {@code paren}
	 * labeled alternative in {@link CoolParser#expr}.
	 * @param ctx the parse tree
	 */
	void exitParen(CoolParser.ParenContext ctx);
	/**
	 * Enter a parse tree produced by the {@code assig_expresion}
	 * labeled alternative in {@link CoolParser#expr}.
	 * @param ctx the parse tree
	 */
	void enterAssig_expresion(CoolParser.Assig_expresionContext ctx);
	/**
	 * Exit a parse tree produced by the {@code assig_expresion}
	 * labeled alternative in {@link CoolParser#expr}.
	 * @param ctx the parse tree
	 */
	void exitAssig_expresion(CoolParser.Assig_expresionContext ctx);
	/**
	 * Enter a parse tree produced by the {@code local_vars}
	 * labeled alternative in {@link CoolParser#expr}.
	 * @param ctx the parse tree
	 */
	void enterLocal_vars(CoolParser.Local_varsContext ctx);
	/**
	 * Exit a parse tree produced by the {@code local_vars}
	 * labeled alternative in {@link CoolParser#expr}.
	 * @param ctx the parse tree
	 */
	void exitLocal_vars(CoolParser.Local_varsContext ctx);
	/**
	 * Enter a parse tree produced by the {@code negate}
	 * labeled alternative in {@link CoolParser#expr}.
	 * @param ctx the parse tree
	 */
	void enterNegate(CoolParser.NegateContext ctx);
	/**
	 * Exit a parse tree produced by the {@code negate}
	 * labeled alternative in {@link CoolParser#expr}.
	 * @param ctx the parse tree
	 */
	void exitNegate(CoolParser.NegateContext ctx);
	/**
	 * Enter a parse tree produced by the {@code if_then_else}
	 * labeled alternative in {@link CoolParser#expr}.
	 * @param ctx the parse tree
	 */
	void enterIf_then_else(CoolParser.If_then_elseContext ctx);
	/**
	 * Exit a parse tree produced by the {@code if_then_else}
	 * labeled alternative in {@link CoolParser#expr}.
	 * @param ctx the parse tree
	 */
	void exitIf_then_else(CoolParser.If_then_elseContext ctx);
	/**
	 * Enter a parse tree produced by the {@code true}
	 * labeled alternative in {@link CoolParser#expr}.
	 * @param ctx the parse tree
	 */
	void enterTrue(CoolParser.TrueContext ctx);
	/**
	 * Exit a parse tree produced by the {@code true}
	 * labeled alternative in {@link CoolParser#expr}.
	 * @param ctx the parse tree
	 */
	void exitTrue(CoolParser.TrueContext ctx);
	/**
	 * Enter a parse tree produced by the {@code le}
	 * labeled alternative in {@link CoolParser#expr}.
	 * @param ctx the parse tree
	 */
	void enterLe(CoolParser.LeContext ctx);
	/**
	 * Exit a parse tree produced by the {@code le}
	 * labeled alternative in {@link CoolParser#expr}.
	 * @param ctx the parse tree
	 */
	void exitLe(CoolParser.LeContext ctx);
	/**
	 * Enter a parse tree produced by the {@code block}
	 * labeled alternative in {@link CoolParser#expr}.
	 * @param ctx the parse tree
	 */
	void enterBlock(CoolParser.BlockContext ctx);
	/**
	 * Exit a parse tree produced by the {@code block}
	 * labeled alternative in {@link CoolParser#expr}.
	 * @param ctx the parse tree
	 */
	void exitBlock(CoolParser.BlockContext ctx);
	/**
	 * Enter a parse tree produced by the {@code mul_div}
	 * labeled alternative in {@link CoolParser#expr}.
	 * @param ctx the parse tree
	 */
	void enterMul_div(CoolParser.Mul_divContext ctx);
	/**
	 * Exit a parse tree produced by the {@code mul_div}
	 * labeled alternative in {@link CoolParser#expr}.
	 * @param ctx the parse tree
	 */
	void exitMul_div(CoolParser.Mul_divContext ctx);
	/**
	 * Enter a parse tree produced by the {@code id}
	 * labeled alternative in {@link CoolParser#expr}.
	 * @param ctx the parse tree
	 */
	void enterId(CoolParser.IdContext ctx);
	/**
	 * Exit a parse tree produced by the {@code id}
	 * labeled alternative in {@link CoolParser#expr}.
	 * @param ctx the parse tree
	 */
	void exitId(CoolParser.IdContext ctx);
	/**
	 * Enter a parse tree produced by the {@code case}
	 * labeled alternative in {@link CoolParser#expr}.
	 * @param ctx the parse tree
	 */
	void enterCase(CoolParser.CaseContext ctx);
	/**
	 * Exit a parse tree produced by the {@code case}
	 * labeled alternative in {@link CoolParser#expr}.
	 * @param ctx the parse tree
	 */
	void exitCase(CoolParser.CaseContext ctx);
	/**
	 * Enter a parse tree produced by {@link CoolParser#assign}.
	 * @param ctx the parse tree
	 */
	void enterAssign(CoolParser.AssignContext ctx);
	/**
	 * Exit a parse tree produced by {@link CoolParser#assign}.
	 * @param ctx the parse tree
	 */
	void exitAssign(CoolParser.AssignContext ctx);
	/**
	 * Enter a parse tree produced by {@link CoolParser#case_method}.
	 * @param ctx the parse tree
	 */
	void enterCase_method(CoolParser.Case_methodContext ctx);
	/**
	 * Exit a parse tree produced by {@link CoolParser#case_method}.
	 * @param ctx the parse tree
	 */
	void exitCase_method(CoolParser.Case_methodContext ctx);
}