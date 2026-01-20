package cool.AST;

public class PrintVisitor implements ASTVisitor<Void> {
    int indent = 0;

    void printIndent(String str) {
        for (int i = 0; i < indent; i++)
            System.out.print("  ");
        System.out.println(str);
    }

    @Override
    public Void visit(ASTNode.Program program) {
        System.out.print("program\n");
        for (ASTNode.ClassDef classDef : program.classes) {
            classDef.accept(this);
        }
        return null;
    }

    @Override
    public Void visit(ASTNode.ClassDef classDef) {
        indent++;
        printIndent("class");
        classDef.type.accept(this);
        if (classDef.inheritsType != null) {
            classDef.inheritsType.accept(this);
        }
        for (ASTNode.Feature feature : classDef.features) {
            feature.accept(this);
        }
        indent--;
        return null;
    }

    @Override
    public Void visit(ASTNode.FunctionsFeatures functionsFeatures) {
        indent++;
        printIndent("method");
        functionsFeatures.id.accept(this);

        if (functionsFeatures.formals != null ) {
            for (ASTNode.Formal formal : functionsFeatures.formals) {
                formal.accept(this);
            }
        }
        functionsFeatures.type.accept(this);
        functionsFeatures.body.accept(this);

        indent--;
        return null;
    }

    @Override
    public Void visit(ASTNode.AssignFeatures assignFeatures) {
        indent++;
        printIndent("attribute");
        assignFeatures.id.accept(this);
        assignFeatures.type.accept(this);
        if (assignFeatures.exp != null) {
            assignFeatures.exp.accept(this);
        }
        indent--;
        return null;
    }

    @Override
    public Void visit(ASTNode.Formal formal) {
        indent++;
        printIndent("formal");
        formal.id.accept(this);
        formal.type.accept(this);
        indent--;

        return null;
    }

    @Override
    public Void visit(ASTNode.IDNode idNode) {
        indent++;
        printIndent(idNode.token.getText());
        indent--;
        return null;
    }

    @Override
    public Void visit(ASTNode.TypeNode typeNode) {
        indent++;
        printIndent(typeNode.token.getText());
        indent--;
        return null;
    }

    @Override
    public Void visit(ASTNode.EqualNode equalNode) {
        indent++;
        printIndent("=");
        equalNode.left.accept(this);
        equalNode.right.accept(this);
        indent--;
        return null;
    }

    @Override
    public Void visit(ASTNode.LeNode leNode) {
        indent++;
        printIndent("<=");
        leNode.left.accept(this);
        leNode.right.accept(this);
        indent--;
        return null;
    }

    @Override
    public Void visit(ASTNode.LtNode ltNode) {
        indent++;
        printIndent("<");
        ltNode.left.accept(this);
        ltNode.right.accept(this);
        indent--;
        return null;
    }

    @Override
    public Void visit(ASTNode.NotNode notNode) {
        indent++;
        printIndent("not");
        notNode.exp.accept(this);
        indent--;
        return null;
    }

    @Override
    public Void visit(ASTNode.PlusNode plusNode) {
        indent++;
        printIndent("+");
        plusNode.left.accept(this);
        plusNode.right.accept(this);
        indent--;
        return null;
    }

    @Override
    public Void visit(ASTNode.MinusNode minusNode) {
        indent++;
        printIndent("-");
        minusNode.left.accept(this);
        minusNode.right.accept(this);
        indent--;
        return null;
    }

    @Override
    public Void visit(ASTNode.MulNode mulNode) {
        indent++;
        printIndent("*");  // Print operator FIRST
        mulNode.left.accept(this);
        mulNode.right.accept(this);
        indent--;
        return null;
    }

    @Override
    public Void visit(ASTNode.DivNode divNode) {
        indent++;
        printIndent("/");  // Print operator FIRST
        divNode.left.accept(this);
        divNode.right.accept(this);
        indent--;
        return null;
    }

    @Override
    public Void visit(ASTNode.ParenNode parenNode) {
        parenNode.exp.accept(this);
        return null;
    }


    @Override
    public Void visit(ASTNode.IDtype idtype) {
        indent++;
        printIndent(idtype.token.getText());
        indent--;
        return null;
    }

    @Override
    public Void visit(ASTNode.IntType int_type) {
        indent++;
        printIndent(int_type.token.getText());
        indent--;
        return null;
    }

    @Override
    public Void visit(ASTNode.TildeNode tildeNode) {
        indent++;
        printIndent("~");
        tildeNode.exp.accept(this);
        return null;
    }

    @Override
    public Void visit(ASTNode.StringType string_type) {
        indent++;

        printIndent(string_type.token.getText().replaceAll("\"", ""));
        indent--;
        return null;
    }

    @Override
    public Void visit(ASTNode.TrueType true_type) {
        indent++;
        printIndent(true_type.token.getText());
        indent--;
        return null;
    }

    @Override
    public Void visit(ASTNode.FalseType false_type) {
        indent++;
        printIndent(false_type.token.getText());
        indent--;
        return null;
    }

    @Override
    public Void visit(ASTNode.AssignNode assignNode) {
        indent++;
        printIndent("<-");
        assignNode.id.accept(this);
        assignNode.exp.accept(this);
        indent--;
        return null;
    }

    @Override
    public Void visit(ASTNode.IsvoidNode isvoidNode) {
        indent++;
        printIndent("isvoid");
        isvoidNode.exp.accept(this);
        indent--;
        return null;
    }

    @Override
    public Void visit(ASTNode.NewTypeNode newTypeNode) {
        indent++;
        printIndent("new");
        newTypeNode.type.accept(this);
        indent--;
        return null;
    }

    @Override
    public Void visit(ASTNode.FuncCallNode funcCallNode) {
        indent++;
        printIndent("implicit dispatch");
        funcCallNode.id.accept(this);

        if (funcCallNode.arguments != null) {
            for (ASTNode.Expression arg : funcCallNode.arguments) {
                arg.accept(this);
            }
        }

        indent--;
        return null;
    }

    @Override
    public Void visit(ASTNode.FuncCallClassNode funcCallClassNode) {
        indent++;
        printIndent(".");

        if (funcCallClassNode.atType != null) {
            funcCallClassNode.object.accept(this);
            funcCallClassNode.atType.accept(this);
        } else {
            funcCallClassNode.object.accept(this);
        }

        funcCallClassNode.id.accept(this);

        if (funcCallClassNode.arguments != null) {
            for (ASTNode.Expression arg : funcCallClassNode.arguments) {
                arg.accept(this);
            }
        }

        indent--;
        return null;
    }

    @Override
    public Void visit(ASTNode.WhileNode whileNode) {
        indent++;
        printIndent("while");
        whileNode.condition.accept(this);
        whileNode.body.accept(this);
        indent--;
        return null;
    }

    @Override
    public Void visit(ASTNode.IfNode ifNode) {
        indent++;
        printIndent("if");
        ifNode.condition.accept(this);
        ifNode.thenExp.accept(this);
        ifNode.elseExp.accept(this);
        indent--;
        return null;
    }

    @Override
    public Void visit(ASTNode.CaseMethodNode caseMethodNode) {
        indent++;
        printIndent("case branch");
        caseMethodNode.id.accept(this);
        caseMethodNode.type.accept(this);
        caseMethodNode.cases.accept(this);
        indent--;
        return null;
    }

    @Override
    public Void visit(ASTNode.CaseNode caseNode) {
        indent++;
        printIndent("case");
        caseNode.condition.accept(this);
        for (ASTNode.CaseMethodNode caseMethod : caseNode.cases) {
            caseMethod.accept(this);
        }
        indent--;
        return null;
    }

    @Override
    public Void visit(ASTNode.LetNode letNode) {
        indent++;
        printIndent("let");

        for (ASTNode.LocalVarNode localVar : letNode.localVars) {
            localVar.accept(this);
        }

        letNode.body.accept(this);
        indent--;
        return null;
    }

    @Override
    public Void visit(ASTNode.LocalVarNode localVarNode) {
        indent++;
        printIndent("local");
        localVarNode.id.accept(this);
        localVarNode.type.accept(this);
        if (localVarNode.initExpr != null) {
            localVarNode.initExpr.accept(this);
        }
        indent--;
        return null;
    }

    @Override
    public Void visit(ASTNode.BlockNode blockNode) {
        indent++;
        printIndent("block");
        for (ASTNode.Expression expr : blockNode.expressions) {
            expr.accept(this);
        }
        indent--;
        return null;
    }
}