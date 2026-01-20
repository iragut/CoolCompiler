package cool.AST;


public interface ASTVisitor<T> {
    T visit(ASTNode.Program program);

    T visit(ASTNode.ClassDef classDef);

    T visit(ASTNode.FunctionsFeatures functionsFeatures);
    T visit(ASTNode.AssignFeatures assignFeatures);

    T visit(ASTNode.Formal formal);

    T visit(ASTNode.IDNode idNode);
    T visit(ASTNode.TypeNode typeNode);


    T visit(ASTNode.EqualNode equalNode);
    T visit(ASTNode.LeNode leNode);
    T visit(ASTNode.LtNode ltNode);
    T visit(ASTNode.NotNode notNode);


    T visit(ASTNode.PlusNode plusNode);
    T visit(ASTNode.MinusNode minusNode);
    T visit(ASTNode.MulNode mulNode);
    T visit(ASTNode.DivNode divNode);
    T visit(ASTNode.ParenNode parenNode);
    T visit(ASTNode.TildeNode tildeNode);


    T visit(ASTNode.IDtype iDtype);
    T visit(ASTNode.IntType intLiteral);
    T visit(ASTNode.StringType stringType);
    T visit(ASTNode.TrueType trueType);
    T visit(ASTNode.FalseType falseType);

    T visit(ASTNode.AssignNode assignNode);
    T visit(ASTNode.IsvoidNode isvoidNode);
    T visit(ASTNode.NewTypeNode newTypeNode);

    T visit(ASTNode.FuncCallClassNode funcCallClassNode);
    T visit(ASTNode.FuncCallNode funcCallNode);

    T visit(ASTNode.WhileNode whileNode);
    T visit(ASTNode.IfNode ifNode);
    T visit(ASTNode.CaseMethodNode caseMethodNode);
    T visit(ASTNode.CaseNode caseNode);

    T visit(ASTNode.LetNode letNode);
    T visit(ASTNode.LocalVarNode localVarNode);
    T visit(ASTNode.BlockNode blockNode);
}