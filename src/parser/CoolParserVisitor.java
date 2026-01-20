// Generated from D:/Facultate/Anul-IV/Comp/tema3/src/cool/parser/CoolParser.g4 by ANTLR 4.13.2

    package cool.parser;

import org.antlr.v4.runtime.tree.ParseTreeVisitor;

/**
 * This interface defines a complete generic visitor for a parse tree produced
 * by {@link CoolParser}.
 *
 * @param <T> The return type of the visit operation. Use {@link Void} for
 * operations with no return type.
 */
public interface CoolParserVisitor<T> extends ParseTreeVisitor<T> {
	/**
	 * Visit a parse tree produced by {@link CoolParser#program}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitProgram(CoolParser.ProgramContext ctx);
	/**
	 * Visit a parse tree produced by {@link CoolParser#class}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitClass(CoolParser.ClassContext ctx);
	/**
	 * Visit a parse tree produced by {@link CoolParser#feature}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFeature(CoolParser.FeatureContext ctx);
	/**
	 * Visit a parse tree produced by {@link CoolParser#arguments}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitArguments(CoolParser.ArgumentsContext ctx);
	/**
	 * Visit a parse tree produced by {@link CoolParser#formal}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFormal(CoolParser.FormalContext ctx);
	/**
	 * Visit a parse tree produced by the {@code new_type}
	 * labeled alternative in {@link CoolParser#expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitNew_type(CoolParser.New_typeContext ctx);
	/**
	 * Visit a parse tree produced by the {@code string}
	 * labeled alternative in {@link CoolParser#expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitString(CoolParser.StringContext ctx);
	/**
	 * Visit a parse tree produced by the {@code while_loop}
	 * labeled alternative in {@link CoolParser#expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitWhile_loop(CoolParser.While_loopContext ctx);
	/**
	 * Visit a parse tree produced by the {@code isvoid}
	 * labeled alternative in {@link CoolParser#expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitIsvoid(CoolParser.IsvoidContext ctx);
	/**
	 * Visit a parse tree produced by the {@code false}
	 * labeled alternative in {@link CoolParser#expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFalse(CoolParser.FalseContext ctx);
	/**
	 * Visit a parse tree produced by the {@code lt}
	 * labeled alternative in {@link CoolParser#expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitLt(CoolParser.LtContext ctx);
	/**
	 * Visit a parse tree produced by the {@code eq}
	 * labeled alternative in {@link CoolParser#expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitEq(CoolParser.EqContext ctx);
	/**
	 * Visit a parse tree produced by the {@code func_call_class}
	 * labeled alternative in {@link CoolParser#expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFunc_call_class(CoolParser.Func_call_classContext ctx);
	/**
	 * Visit a parse tree produced by the {@code func_call}
	 * labeled alternative in {@link CoolParser#expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFunc_call(CoolParser.Func_callContext ctx);
	/**
	 * Visit a parse tree produced by the {@code add_sub}
	 * labeled alternative in {@link CoolParser#expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAdd_sub(CoolParser.Add_subContext ctx);
	/**
	 * Visit a parse tree produced by the {@code int}
	 * labeled alternative in {@link CoolParser#expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitInt(CoolParser.IntContext ctx);
	/**
	 * Visit a parse tree produced by the {@code not}
	 * labeled alternative in {@link CoolParser#expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitNot(CoolParser.NotContext ctx);
	/**
	 * Visit a parse tree produced by the {@code paren}
	 * labeled alternative in {@link CoolParser#expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitParen(CoolParser.ParenContext ctx);
	/**
	 * Visit a parse tree produced by the {@code assig_expresion}
	 * labeled alternative in {@link CoolParser#expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAssig_expresion(CoolParser.Assig_expresionContext ctx);
	/**
	 * Visit a parse tree produced by the {@code local_vars}
	 * labeled alternative in {@link CoolParser#expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitLocal_vars(CoolParser.Local_varsContext ctx);
	/**
	 * Visit a parse tree produced by the {@code negate}
	 * labeled alternative in {@link CoolParser#expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitNegate(CoolParser.NegateContext ctx);
	/**
	 * Visit a parse tree produced by the {@code if_then_else}
	 * labeled alternative in {@link CoolParser#expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitIf_then_else(CoolParser.If_then_elseContext ctx);
	/**
	 * Visit a parse tree produced by the {@code true}
	 * labeled alternative in {@link CoolParser#expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitTrue(CoolParser.TrueContext ctx);
	/**
	 * Visit a parse tree produced by the {@code le}
	 * labeled alternative in {@link CoolParser#expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitLe(CoolParser.LeContext ctx);
	/**
	 * Visit a parse tree produced by the {@code block}
	 * labeled alternative in {@link CoolParser#expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBlock(CoolParser.BlockContext ctx);
	/**
	 * Visit a parse tree produced by the {@code mul_div}
	 * labeled alternative in {@link CoolParser#expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitMul_div(CoolParser.Mul_divContext ctx);
	/**
	 * Visit a parse tree produced by the {@code id}
	 * labeled alternative in {@link CoolParser#expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitId(CoolParser.IdContext ctx);
	/**
	 * Visit a parse tree produced by the {@code case}
	 * labeled alternative in {@link CoolParser#expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCase(CoolParser.CaseContext ctx);
	/**
	 * Visit a parse tree produced by {@link CoolParser#assign}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAssign(CoolParser.AssignContext ctx);
	/**
	 * Visit a parse tree produced by {@link CoolParser#case_method}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCase_method(CoolParser.Case_methodContext ctx);
}