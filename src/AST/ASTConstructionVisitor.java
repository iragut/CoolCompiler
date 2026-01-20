package cool.AST;

import java.util.LinkedList;

import cool.parser.CoolParser;
import cool.parser.CoolParserBaseVisitor;

public class ASTConstructionVisitor extends CoolParserBaseVisitor<ASTNode> {
    @Override
    public ASTNode visitProgram(CoolParser.ProgramContext ctx) {
        LinkedList<ASTNode.ClassDef> classes = new LinkedList<>();
        for (var child : ctx.children) {
            ASTNode.ClassDef stmt = (ASTNode.ClassDef) visit(child);
            if (stmt != null) {
                classes.add(stmt);
            }
        }
        return new ASTNode.Program(classes, ctx.start, ctx);
    }

    @Override
    public ASTNode visitClass(CoolParser.ClassContext ctx) {
        ASTNode.TypeNode type = new ASTNode.TypeNode(ctx.TYPE(0).getSymbol(), ctx);

        ASTNode.TypeNode inheritsType = null;
        if (ctx.INHERITS() != null) {
            inheritsType = new ASTNode.TypeNode(ctx.TYPE(1).getSymbol(), ctx);
        }

        LinkedList<ASTNode.Feature> features = new LinkedList<>();
        for (var featureCtx : ctx.feature()) {
            features.add((ASTNode.Feature)visit(featureCtx));
        }

        return new ASTNode.ClassDef(type, inheritsType, features, ctx.TYPE(0).getSymbol(), ctx);
    }

    @Override
    public ASTNode visitFeature(CoolParser.FeatureContext ctx) {
        // Method
        if (ctx.LPAREN() != null) {
            ASTNode.IDNode id = new ASTNode.IDNode(ctx.ID().getSymbol(), ctx);
            ASTNode.TypeNode type = new ASTNode.TypeNode(ctx.TYPE().getSymbol(), ctx);

            LinkedList<ASTNode.Formal> formals = new LinkedList<>();
            if (ctx.formal() != null) {
                formals.add((ASTNode.Formal)visit(ctx.formal()));

                if (ctx.arguments() != null) {
                    for (var arg : ctx.arguments().formal()) {
                        formals.add((ASTNode.Formal)visit(arg));
                    }
                }
            }
            ASTNode.Expression body = (ASTNode.Expression)visit(ctx.expr());
            return new ASTNode.FunctionsFeatures(id, type, formals, body, ctx.start, ctx);
        }
        // Attribute (using assign rule)
        else {
            return visit(ctx.assign());
        }
    }

    @Override
    public ASTNode visitAssign(CoolParser.AssignContext ctx) {
        ASTNode.IDNode id = new ASTNode.IDNode(ctx.ID().getSymbol(), ctx);
        ASTNode.TypeNode type = new ASTNode.TypeNode(ctx.TYPE().getSymbol(), ctx);
        ASTNode.Expression initExpr = null;
        if (ctx.ASSIGN() != null) {
            initExpr = (ASTNode.Expression)visit(ctx.expr());
        }
        return new ASTNode.AssignFeatures(id, type, initExpr, ctx.start, ctx);
    }

    @Override
    public ASTNode visitNot(CoolParser.NotContext ctx) {
        ASTNode.Expression expr = (ASTNode.Expression) visit(ctx.expr());
        return new ASTNode.NotNode(expr, ctx.start, ctx);
    }

    @Override
    public ASTNode visitLt(CoolParser.LtContext ctx) {
        ASTNode.Expression left = (ASTNode.Expression) visit(ctx.expr(0));
        ASTNode.Expression right = (ASTNode.Expression) visit(ctx.expr(1));
        return new ASTNode.LtNode(left, right, ctx.start, ctx);
    }

    @Override
    public ASTNode visitLe(CoolParser.LeContext ctx) {
        ASTNode.Expression left = (ASTNode.Expression) visit(ctx.expr(0));
        ASTNode.Expression right = (ASTNode.Expression) visit(ctx.expr(1));
        return new ASTNode.LeNode(left, right, ctx.start, ctx);
    }

    @Override
    public ASTNode visitEq(CoolParser.EqContext ctx) {
        ASTNode.Expression left = (ASTNode.Expression) visit(ctx.expr(0));
        ASTNode.Expression right = (ASTNode.Expression) visit(ctx.expr(1));
        return new ASTNode.EqualNode(left, right, ctx.EQUAL().getSymbol(), ctx);
    }

    @Override
    public ASTNode visitFormal(CoolParser.FormalContext ctx) {
        ASTNode.IDNode id = new ASTNode.IDNode(ctx.ID().getSymbol(), ctx);
        ASTNode.TypeNode type = new ASTNode.TypeNode(ctx.TYPE().getSymbol(), ctx);

        return new ASTNode.Formal(id, type, ctx.start, ctx);
    }

    @Override
    public ASTNode visitNegate(CoolParser.NegateContext ctx) {
        ASTNode.Expression expr = (ASTNode.Expression) visit(ctx.expr());
        return new ASTNode.TildeNode(expr, ctx.start, ctx);
    }


    @Override
    public ASTNode visitAdd_sub(CoolParser.Add_subContext ctx) {
        ASTNode.Expression left = (ASTNode.Expression) visit(ctx.expr(0));
        ASTNode.Expression right = (ASTNode.Expression) visit(ctx.expr(1));
        if (ctx.PLUS() != null) {
            return new ASTNode.PlusNode(left, right, ctx.start, ctx);
        } else {
            return new ASTNode.MinusNode(left, right, ctx.start, ctx);
        }
    }

    @Override
    public ASTNode visitMul_div(CoolParser.Mul_divContext ctx) {
        ASTNode.Expression left = (ASTNode.Expression) visit(ctx.expr(0));
        ASTNode.Expression right = (ASTNode.Expression) visit(ctx.expr(1));
        if (ctx.MULT() != null) {
            return new ASTNode.MulNode(left, right, ctx.start, ctx);
        } else {
            return new ASTNode.DivNode(left, right, ctx.start, ctx);
        }
    }

    @Override
    public ASTNode visitParen(CoolParser.ParenContext ctx) {
        return visit(ctx.expr());
    }

    @Override
    public ASTNode visitId(CoolParser.IdContext ctx) {
        return new ASTNode.IDtype(ctx.ID().getSymbol(), ctx);
    }

    @Override
    public ASTNode visitInt(CoolParser.IntContext ctx) {
        return new ASTNode.IntType(ctx.INT().getSymbol(), ctx);
    }

    @Override
    public ASTNode visitString(CoolParser.StringContext ctx) {
        return new ASTNode.StringType(ctx.STRING().getSymbol(), ctx);
    }

    @Override
    public ASTNode visitTrue(CoolParser.TrueContext ctx) {
        return new ASTNode.TrueType(ctx.TRUE().getSymbol(), ctx);
    }

    @Override
    public ASTNode visitFalse(CoolParser.FalseContext ctx) {
        return new ASTNode.FalseType(ctx.FALSE().getSymbol(), ctx);
    }

    @Override
    public ASTNode visitAssig_expresion(CoolParser.Assig_expresionContext ctx) {
        return new ASTNode.AssignNode(
                new ASTNode.IDNode(ctx.ID().getSymbol(), ctx),
                (ASTNode.Expression) visit(ctx.expr()),
                ctx.start,
                ctx
        );
    }

    @Override
    public ASTNode visitIsvoid(CoolParser.IsvoidContext ctx) {
        return new ASTNode.IsvoidNode(
                (ASTNode.Expression) visit(ctx.expr()),
                ctx.start,
                ctx
        );
    }

    @Override
    public ASTNode visitNew_type(CoolParser.New_typeContext ctx) {
        return new ASTNode.NewTypeNode(
                new ASTNode.TypeNode(ctx.TYPE().getSymbol(), ctx),
                ctx.start,
                ctx
        );
    }

    @Override
    public ASTNode visitFunc_call_class(CoolParser.Func_call_classContext ctx) {
        ASTNode.Expression object = (ASTNode.Expression) visit(ctx.expr(0));

        ASTNode.TypeNode atType = null;
        if (ctx.TYPE() != null) {
            atType = new ASTNode.TypeNode(ctx.TYPE().getSymbol(), ctx);
        }

        ASTNode.IDNode id = new ASTNode.IDNode(ctx.ID().getSymbol(), ctx);

        LinkedList<ASTNode.Expression> arguments = new LinkedList<>();
        for (int i = 1; i < ctx.expr().size(); i++) {
            arguments.add((ASTNode.Expression) visit(ctx.expr(i)));
        }

        return new ASTNode.FuncCallClassNode(object, atType, id, arguments, ctx.start, ctx);
    }

    @Override
    public ASTNode visitFunc_call(CoolParser.Func_callContext ctx) {
        ASTNode.IDNode id = new ASTNode.IDNode(ctx.ID().getSymbol(), ctx);

        LinkedList<ASTNode.Expression> arguments = new LinkedList<>();
        if (ctx.expr() != null && !ctx.expr().isEmpty()) {
            for (var exprCtx : ctx.expr()) {
                arguments.add((ASTNode.Expression) visit(exprCtx));
            }
        }

        return new ASTNode.FuncCallNode(id, arguments, ctx.start, ctx);
    }

    @Override
    public ASTNode visitIf_then_else(CoolParser.If_then_elseContext ctx) {
        return new ASTNode.IfNode(
                (ASTNode.Expression) visit(ctx.expr(0)),
                (ASTNode.Expression) visit(ctx.expr(1)),
                (ASTNode.Expression) visit(ctx.expr(2)),
                ctx.start,
                ctx
        );
    }

    @Override
    public ASTNode visitCase_method(CoolParser.Case_methodContext ctx) {
        ASTNode.IDNode id = new ASTNode.IDNode(ctx.ID().getSymbol(), ctx);
        ASTNode.TypeNode type = new ASTNode.TypeNode(ctx.TYPE().getSymbol(), ctx);
        ASTNode.Expression exp = (ASTNode.Expression) visit(ctx.expr());

        return new ASTNode.CaseMethodNode(id, type, exp, ctx.start, ctx);
    }

    @Override
    public ASTNode visitCase(CoolParser.CaseContext ctx) {
        ASTNode.Expression condition = (ASTNode.Expression) visit(ctx.expr());
        LinkedList<ASTNode.CaseMethodNode> cases = new LinkedList<>();
        for (var caseCtx : ctx.case_method()) {
            cases.add((ASTNode.CaseMethodNode)visit(caseCtx));
        }

        return new ASTNode.CaseNode(condition, cases, ctx.start, ctx);
    }

    @Override
    public ASTNode visitWhile_loop(CoolParser.While_loopContext ctx) {
        ASTNode.Expression exp1 = (ASTNode.Expression) visit(ctx.expr(0));
        ASTNode.Expression exp2 = (ASTNode.Expression) visit(ctx.expr(1));
        return new ASTNode.WhileNode(exp1, exp2, ctx.start, ctx);
    }

    @Override
    public ASTNode visitLocal_vars(CoolParser.Local_varsContext ctx) {
        LinkedList<ASTNode.LocalVarNode> localVars = new LinkedList<>();

        for (var assignCtx : ctx.assign()) {
            ASTNode.IDNode id = new ASTNode.IDNode(assignCtx.ID().getSymbol(), assignCtx);
            ASTNode.TypeNode type = new ASTNode.TypeNode(assignCtx.TYPE().getSymbol(), assignCtx);
            ASTNode.Expression initExpr = null;
            if (assignCtx.ASSIGN() != null) {
                initExpr = (ASTNode.Expression) visit(assignCtx.expr());
            }
            localVars.add(new ASTNode.LocalVarNode(id, type, initExpr, assignCtx.start, assignCtx));
        }

        ASTNode.Expression body = (ASTNode.Expression) visit(ctx.expr());
        return new ASTNode.LetNode(localVars, body, ctx.start, ctx);
    }

    @Override
    public ASTNode visitBlock(CoolParser.BlockContext ctx) {
        LinkedList<ASTNode.Expression> expressions = new LinkedList<>();
        for (var exprCtx : ctx.expr()) {
            expressions.add((ASTNode.Expression) visit(exprCtx));
        }
        return new ASTNode.BlockNode(expressions, ctx.start, ctx);
    }
}