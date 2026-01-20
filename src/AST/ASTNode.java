package cool.AST;

import cool.structures.Symbol;
import org.antlr.v4.runtime.ParserRuleContext;
import org.antlr.v4.runtime.Token;

import java.util.LinkedList;

public abstract class ASTNode {
    protected Token token;
    protected ParserRuleContext ctx;
    public String debugStr = null;

    ASTNode(Token token, ParserRuleContext ctx) {
        this.token = token;
        this.ctx = ctx;
    }

    public Token getToken() {
        return token;
    }

    public ParserRuleContext getCtx() {
        return ctx;
    }

    public <T> T accept(ASTVisitor<T> visitor) {
        return null;
    }


    public static abstract class Expression extends ASTNode {
        Expression(Token token, ParserRuleContext ctx) {
            super(token, ctx);
        }
    }

    public static abstract class Feature extends ASTNode {
        Feature(Token token, ParserRuleContext ctx) {
            super(token, ctx);
        }
    }

    public static class Program extends ASTNode {
        public LinkedList<ClassDef> classes;

        Program(LinkedList<ClassDef> classes, Token token, ParserRuleContext ctx) {
            super(token, ctx);
            this.classes = classes;
        }

        public <T> T accept(ASTVisitor<T> visitor) {
            return visitor.visit(this);
        }
    }

    public static class ClassDef extends ASTNode {
        public TypeNode type;
        public TypeNode inheritsType;
        public LinkedList<Feature> features;

        ClassDef(TypeNode type, TypeNode inheritsType, LinkedList<Feature> features, Token token, ParserRuleContext ctx) {
            super(token, ctx);
            this.type = type;
            this.inheritsType = inheritsType;
            this.features = features;
        }

        public <T> T accept(ASTVisitor<T> visitor) {
            return visitor.visit(this);
        }

    }

    public static class FunctionsFeatures extends Feature {
        public IDNode id;
        public TypeNode type;
        public LinkedList<Formal> formals;
        public Expression body;

        FunctionsFeatures(IDNode id, TypeNode type, LinkedList<Formal> formals, Expression body, Token token, ParserRuleContext ctx) {
            super(token, ctx);
            this.id = id;
            this.type = type;
            this.formals = formals;
            this.body = body;
        }

        public <T> T accept(ASTVisitor<T> visitor) {
            return visitor.visit(this);
        }
    }

    public static class AssignFeatures extends Feature {
        public IDNode id;
        public TypeNode type;
        public Expression exp;

        AssignFeatures(IDNode id, TypeNode type, Expression exp, Token token, ParserRuleContext ctx) {
            super(token, ctx);
            this.id = id;
            this.type = type;
            this.exp = exp;
        }

        public <T> T accept(ASTVisitor<T> visitor) {
            return visitor.visit(this);
        }
    }

    public static class Formal extends ASTNode {
        public IDNode id;
        public TypeNode type;

        Formal(IDNode id, TypeNode type, Token token, ParserRuleContext ctx) {
            super(token, ctx);
            this.id = id;
            this.type = type;
        }

        public <T> T accept(ASTVisitor<T> visitor) {
            return visitor.visit(this);
        }
    }

    public static class IntType extends Expression {
        IntType(Token token, ParserRuleContext ctx) {
            super(token, ctx);
        }

        public <T> T accept(ASTVisitor<T> visitor) {
            return visitor.visit(this);
        }
    }

    public static class TrueType extends Expression {
        TrueType(Token token, ParserRuleContext ctx) {
            super(token, ctx);
        }

        public <T> T accept(ASTVisitor<T> visitor) {
            return visitor.visit(this);
        }
    }

    public static class FalseType extends Expression {
        FalseType(Token token, ParserRuleContext ctx) {
            super(token, ctx);
        }

        public <T> T accept(ASTVisitor<T> visitor) {
            return visitor.visit(this);
        }
    }

    public static class StringType extends Expression {
        StringType(Token token, ParserRuleContext ctx) {
            super(token, ctx);
        }

        public <T> T accept(ASTVisitor<T> visitor) {
            return visitor.visit(this);
        }
    }

    public static class IDtype extends Expression {
        IDtype(Token token, ParserRuleContext ctx) {
            super(token, ctx);
        }

        public <T> T accept(ASTVisitor<T> visitor) {
            return visitor.visit(this);
        }
    }

    public static class IDNode extends ASTNode {
        Symbol symbol;
        IDNode(Token token, ParserRuleContext ctx) {
            super(token, ctx);
        }

        public void setSymbol(Symbol symbol) {
            this.symbol = symbol;
        }

        public Symbol getSymbol() {
            return symbol;
        }

        public <T> T accept(ASTVisitor<T> visitor) {
            return visitor.visit(this);
        }
    }

    public static class TypeNode extends ASTNode {
        Symbol symbol;

        TypeNode(Token token, ParserRuleContext ctx) {
            super(token, ctx);
        }

        public void setSymbol(Symbol symbol) {
            this.symbol = symbol;
        }

        public Symbol getSymbol() {
            return symbol;
        }

        public <T> T accept(ASTVisitor<T> visitor) {
            return visitor.visit(this);
        }
    }

    public static class PlusNode extends Expression {
        public Expression left;
        public Expression right;

        PlusNode(Expression left, Expression right, Token token, ParserRuleContext ctx) {
            super(token, ctx);
            this.left = left;
            this.right = right;
        }

        public <T> T accept(ASTVisitor<T> visitor) {
            return visitor.visit(this);
        }
    }

    public static class MinusNode extends Expression {
        public Expression left;
        public Expression right;

        MinusNode(Expression left, Expression right, Token token, ParserRuleContext ctx) {
            super(token, ctx);
            this.left = left;
            this.right = right;
        }

        public <T> T accept(ASTVisitor<T> visitor) {
            return visitor.visit(this);
        }
    }

    public static class MulNode extends Expression {
        public Expression left;
        public Expression right;

        MulNode(Expression left, Expression right, Token token, ParserRuleContext ctx) {
            super(token, ctx);
            this.left = left;
            this.right = right;
        }

        public <T> T accept(ASTVisitor<T> visitor) {
            return visitor.visit(this);
        }
    }

    public static class DivNode extends Expression {
        public Expression left;
        public Expression right;

        DivNode(Expression left, Expression right, Token token, ParserRuleContext ctx) {
            super(token, ctx);
            this.left = left;
            this.right = right;
        }

        public <T> T accept(ASTVisitor<T> visitor) {
            return visitor.visit(this);
        }
    }

    public static class ParenNode extends Expression {
        public Expression exp;

        ParenNode(Expression exp, Token token, ParserRuleContext ctx) {
            super(token, ctx);
            this.exp = exp;
        }

        public <T> T accept(ASTVisitor<T> visitor) {
            return visitor.visit(this);
        }
    }

    public static class TildeNode extends Expression {
        public Expression exp;

        TildeNode(Expression exp, Token token, ParserRuleContext ctx) {
            super(token, ctx);
            this.exp = exp;
        }

        public <T> T accept(ASTVisitor<T> visitor) {
            return visitor.visit(this);
        }
    }

    public static class NotNode extends Expression {
        public Expression exp;

        NotNode(Expression exp, Token token, ParserRuleContext ctx) {
            super(token, ctx);
            this.exp = exp;
        }

        public <T> T accept(ASTVisitor<T> visitor) {
            return visitor.visit(this);
        }
    }

    public static class LtNode extends Expression {
        public Expression left;
        public Expression right;

        LtNode(Expression left, Expression right, Token token, ParserRuleContext ctx) {
            super(token, ctx);
            this.left = left;
            this.right = right;
        }

        public <T> T accept(ASTVisitor<T> visitor) {
            return visitor.visit(this);
        }
    }

    public static class LeNode extends Expression {
        public Expression left;
        public Expression right;

        LeNode(Expression left, Expression right, Token token, ParserRuleContext ctx) {
            super(token, ctx);
            this.left = left;
            this.right = right;
        }

        public <T> T accept(ASTVisitor<T> visitor) {
            return visitor.visit(this);
        }
    }

    public static class EqualNode extends Expression {
        public Expression left;
        public Expression right;

        EqualNode(Expression left, Expression right, Token token, ParserRuleContext ctx) {
            super(token, ctx);
            this.left = left;
            this.right = right;
        }

        public <T> T accept(ASTVisitor<T> visitor) {
            return visitor.visit(this);
        }
    }

    public static class AssignNode extends Expression {
        public IDNode id;
        public Expression exp;

        AssignNode(IDNode id, Expression exp, Token token, ParserRuleContext ctx) {
            super(token, ctx);
            this.id = id;
            this.exp = exp;
        }

        public <T> T accept(ASTVisitor<T> visitor) {
            return visitor.visit(this);
        }
    }

    public static class IsvoidNode extends Expression {
        public Expression exp;

        IsvoidNode(Expression exp, Token token, ParserRuleContext ctx) {
            super(token, ctx);
            this.exp = exp;
        }

        public <T> T accept(ASTVisitor<T> visitor) {
            return visitor.visit(this);
        }
    }

    public static class NewTypeNode extends Expression {
        public TypeNode type;

        NewTypeNode(TypeNode type, Token token, ParserRuleContext ctx) {
            super(token, ctx);
            this.type = type;
        }

        public <T> T accept(ASTVisitor<T> visitor) {
            return visitor.visit(this);
        }
    }

    public static class FuncCallNode extends Expression {
        public IDNode id;
        public LinkedList<Expression> arguments;

        FuncCallNode(IDNode id, LinkedList<Expression> arguments, Token token, ParserRuleContext ctx) {
            super(token, ctx);
            this.id = id;
            this.arguments = arguments;
        }

        public <T> T accept(ASTVisitor<T> visitor) {
            return visitor.visit(this);
        }
    }

    public static class FuncCallClassNode extends Expression {
        public Expression object;
        public TypeNode atType;
        public IDNode id;
        public LinkedList<Expression> arguments;

        FuncCallClassNode(Expression object, TypeNode atType, IDNode id, LinkedList<Expression> arguments, Token token, ParserRuleContext ctx) {
            super(token, ctx);
            this.object = object;
            this.atType = atType;
            this.id = id;
            this.arguments = arguments;
        }

        public <T> T accept(ASTVisitor<T> visitor) {
            return visitor.visit(this);
        }
    }

    public static class IfNode extends Expression {
        public Expression condition;
        public Expression thenExp;
        public Expression elseExp;

        IfNode(Expression condition, Expression thenExp, Expression elseExp, Token token, ParserRuleContext ctx) {
            super(token, ctx);
            this.condition = condition;
            this.thenExp = thenExp;
            this.elseExp = elseExp;
        }

        public <T> T accept(ASTVisitor<T> visitor) {
            return visitor.visit(this);
        }
    }

    public static class WhileNode extends Expression {
        public Expression condition;
        public Expression body;

        WhileNode(Expression condition, Expression body, Token token, ParserRuleContext ctx) {
            super(token, ctx);
            this.condition = condition;
            this.body = body;
        }

        public <T> T accept(ASTVisitor<T> visitor) {
            return visitor.visit(this);
        }

    }

    public static class CaseMethodNode extends ASTNode {
        public IDNode id;
        public TypeNode type;
        public Expression cases;

        CaseMethodNode(IDNode id, TypeNode type, Expression cases, Token token, ParserRuleContext ctx) {
            super(token, ctx);
            this.id = id;
            this.type = type;
            this.cases = cases;
        }

        public <T> T accept(ASTVisitor<T> visitor) {
            return visitor.visit(this);
        }
    }

    public static class CaseNode extends Expression {
        public Expression condition;
        public LinkedList<CaseMethodNode> cases;

        CaseNode(Expression condition, LinkedList<CaseMethodNode> cases, Token token, ParserRuleContext ctx) {
            super(token, ctx);
            this.condition = condition;
            this.cases = cases;
        }

        public <T> T accept(ASTVisitor<T> visitor) {
            return visitor.visit(this);
        }

    }

    public static class LocalVarNode extends ASTNode {
        public IDNode id;
        public TypeNode type;
        public Expression initExpr;

        LocalVarNode(IDNode id, TypeNode type, Expression initExpr, Token token, ParserRuleContext ctx) {
            super(token, ctx);
            this.id = id;
            this.type = type;
            this.initExpr = initExpr;
        }

        public <T> T accept(ASTVisitor<T> visitor) {
            return visitor.visit(this);
        }
    }

    public static class LetNode extends Expression {
        public LinkedList<LocalVarNode> localVars;
        public Expression body;

        LetNode(LinkedList<LocalVarNode> localVars, Expression body, Token token, ParserRuleContext ctx) {
            super(token, ctx);
            this.localVars = localVars;
            this.body = body;
        }

        public <T> T accept(ASTVisitor<T> visitor) {
            return visitor.visit(this);
        }
    }

    public static class BlockNode extends Expression {
        public LinkedList<Expression> expressions;

        BlockNode(LinkedList<Expression> expressions, Token token, ParserRuleContext ctx) {
            super(token, ctx);
            this.expressions = expressions;
        }

        public <T> T accept(ASTVisitor<T> visitor) {
            return visitor.visit(this);
        }
    }
}