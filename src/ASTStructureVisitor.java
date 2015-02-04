/**
 * @author Yan Xu
 * 2015-01-16
 * The entry point in JavaParser1_7 is CompilationUnit, 
 * so the entry visit function is 
 * public Object visit(CompilationUnit node, Object data), the data is the name of the file
 * 
 */


public class ASTStructureVisitor implements JavaParser1_7Visitor {

    protected int numberOfIf;
    protected int numberOfWhile;
    protected int numberOfBreak;
    protected int numberOfVarLocal;

    public Object visit(CompilationUnit node, Object data){
        
        String fileName = data.toString();

        System.out.println(fileName);
        System.out.println("-" + node.toString());
 
        node.childrenAccept(this, "--");

        return data;  
    }
    
    public Object visit(SimpleNode node, Object data){
        
        System.out.println(data.toString() + node.toString());

        node.childrenAccept(this, data.toString() + "-");
 
        return data;          
    }

    public Object visit(Identifier node, Object data){
 
        System.out.println(data.toString() + node.toString());

        node.childrenAccept(this, data.toString() + "-");
  
        return data;    
    }

    public Object visit(QualifiedIdentifier node, Object data){
 
        System.out.println(data.toString() + node.toString());

        node.childrenAccept(this, data.toString() + "-");
 
        return data;  
    }

    public Object visit(QualifiedIdentifierList node, Object data){
 
        System.out.println(data.toString() + node.toString());

        node.childrenAccept(this, data.toString() + "-");
 
        return data;  
    }

    public Object visit(ImportDeclaration node, Object data){
 
        System.out.println(data.toString() + node.toString());

        node.childrenAccept(this, data.toString() + "-");
 
        return data;  
    }

    public Object visit(Ellipsis node, Object data){
 
        System.out.println(data.toString() + node.toString());

        node.childrenAccept(this, data.toString() + "-");
 
        return data;  
    }

    public Object visit(HasEllipsis node, Object data){
 
        System.out.println(data.toString() + node.toString());

        node.childrenAccept(this, data.toString() + "-");
 
        return data;  
    }

    public Object visit(HasNotEllipsis node, Object data){
 
        System.out.println(data.toString() + node.toString());

        node.childrenAccept(this, data.toString() + "-");
 
        return data;  
    }

    public Object visit(PossibleStaticModifier node, Object data){
 
        System.out.println(data.toString() + node.toString());

        node.childrenAccept(this, data.toString() + "-");
 
        return data;  
    }

    public Object visit(HasStaticModifier node, Object data){
 
        System.out.println(data.toString() + node.toString());

        node.childrenAccept(this, data.toString() + "-");
 
        return data;  
    }

    public Object visit(HasNotStaticModifier node, Object data){
 
        System.out.println(data.toString() + node.toString());

        node.childrenAccept(this, data.toString() + "-");
 
        return data;  
    }

    public Object visit(TypeDeclaration node, Object data){
 
        System.out.println(data.toString() + node.toString());

        node.childrenAccept(this, data.toString() + "-");
 
        return data;  
    }

    public Object visit(EmptyInterfaceDeclaration node, Object data){
 
        System.out.println(data.toString() + node.toString());

        node.childrenAccept(this, data.toString() + "-");
 
        return data;  
    }

    public Object visit(ClassOrInterfaceDeclaration node, Object data){
 
        System.out.println(data.toString() + node.toString());

        node.childrenAccept(this, data.toString() + "-");
 
        return data;  
    }

    public Object visit(ModifierList node, Object data){
 
        System.out.println(data.toString() + node.toString());

        node.childrenAccept(this, data.toString() + "-");
 
        return data;  
    }

    public Object visit(ClassDeclaration node, Object data){
 
        System.out.println(data.toString() + node.toString());

        node.childrenAccept(this, data.toString() + "-");
 
        return data;  
    }

    public Object visit(InterfaceDeclaration node, Object data){
 
        System.out.println(data.toString() + node.toString());

        node.childrenAccept(this, data.toString() + "-");
 
        return data;  
    }

    public Object visit(NormalClassDeclaration node, Object data){
 
        System.out.println(data.toString() + node.toString());

        node.childrenAccept(this, data.toString() + "-");
 
        return data;  
    }

    public Object visit(EnumDeclaration node, Object data){
 
        System.out.println(data.toString() + node.toString());

        node.childrenAccept(this, data.toString() + "-");
 
        return data;  
    }

    public Object visit(NormalInterfaceDeclaration node, Object data){
 
        System.out.println(data.toString() + node.toString());

        node.childrenAccept(this, data.toString() + "-");
 
        return data;  
    }

    public Object visit(AnnotationTypeDeclaration node, Object data){
 
        System.out.println(data.toString() + node.toString());

        node.childrenAccept(this, data.toString() + "-");
 
        return data;  
    }

    public Object visit(Type node, Object data){
 
        System.out.println(data.toString() + node.toString());

        node.childrenAccept(this, data.toString() + "-");
 
        return data;  
    }

    public Object visit(BasicType node, Object data){
 
        System.out.println(data.toString() + node.toString());

        node.childrenAccept(this, data.toString() + "-");
 
        return data;  
    }

    public Object visit(ReferenceType node, Object data){
 
        System.out.println(data.toString() + node.toString());

        node.childrenAccept(this, data.toString() + "-");
 
        return data;  
    }

    public Object visit(TypeArguments node, Object data){
 
        System.out.println(data.toString() + node.toString());

        node.childrenAccept(this, data.toString() + "-");
 
        return data;  
    }

    public Object visit(TypeArgument node, Object data){
 
        System.out.println(data.toString() + node.toString());

        node.childrenAccept(this, data.toString() + "-");
 
        return data;  
    }

    public Object visit(NonWildcardTypeArguments node, Object data){
 
        System.out.println(data.toString() + node.toString());

        node.childrenAccept(this, data.toString() + "-");
 
        return data;  
    }

    public Object visit(TypeList node, Object data){
 
        System.out.println(data.toString() + node.toString());

        node.childrenAccept(this, data.toString() + "-");
 
        return data;  
    }

    public Object visit(TypeArgumentsOrDiamond node, Object data){
 
        System.out.println(data.toString() + node.toString());

        node.childrenAccept(this, data.toString() + "-");
 
        return data;  
    }

    public Object visit(NonWildcardTypeArgumentsOrDiamond node, Object data){
 
        System.out.println(data.toString() + node.toString());

        node.childrenAccept(this, data.toString() + "-");
 
        return data;  
    }

    public Object visit(TypeParameters node, Object data){
 
        System.out.println(data.toString() + node.toString());

        node.childrenAccept(this, data.toString() + "-");
 
        return data;  
    }

    public Object visit(TypeParameter node, Object data){
 
        System.out.println(data.toString() + node.toString());

        node.childrenAccept(this, data.toString() + "-");
 
        return data;  
    }

    public Object visit(Bound node, Object data){
 
        System.out.println(data.toString() + node.toString());

        node.childrenAccept(this, data.toString() + "-");
 
        return data;  
    }

    public Object visit(Modifier node, Object data){
 
        System.out.println(data.toString() + node.toString());

        node.childrenAccept(this, data.toString() + "-");
 
        return data;  
    }

    public Object visit(KeywordModifier node, Object data){
 
        System.out.println(data.toString() + node.toString());

        node.childrenAccept(this, data.toString() + "-");
 
        return data;  
    }

    public Object visit(Annotations node, Object data){
 
        System.out.println(data.toString() + node.toString());

        node.childrenAccept(this, data.toString() + "-");
 
        return data;  
    }

    public Object visit(Annotation node, Object data){
 
        System.out.println(data.toString() + node.toString());

        node.childrenAccept(this, data.toString() + "-");
 
        return data;  
    }

    public Object visit(AnnotationElement node, Object data){
 
        System.out.println(data.toString() + node.toString());

        node.childrenAccept(this, data.toString() + "-");
 
        return data;  
    }

    public Object visit(ElementValuePairs node, Object data){
 
        System.out.println(data.toString() + node.toString());

        node.childrenAccept(this, data.toString() + "-");
 
        return data;  
    }

    public Object visit(ElementValuePair node, Object data){
 
        System.out.println(data.toString() + node.toString());

        node.childrenAccept(this, data.toString() + "-");
 
        return data;  
    }

    public Object visit(ElementValue node, Object data){
 
        System.out.println(data.toString() + node.toString());

        node.childrenAccept(this, data.toString() + "-");
 
        return data;  
    }

    public Object visit(ElementValueArrayInitializer node, Object data){
 
        System.out.println(data.toString() + node.toString());

        node.childrenAccept(this, data.toString() + "-");
 
        return data;  
    }

    public Object visit(ElementValues node, Object data){
 
        System.out.println(data.toString() + node.toString());

        node.childrenAccept(this, data.toString() + "-");
 
        return data;  
    }

    public Object visit(ClassBody node, Object data){
 
        System.out.println(data.toString() + node.toString());

        node.childrenAccept(this, data.toString() + "-");
 
        return data;  
    }

    public Object visit(ClassBodyDeclaration node, Object data){
 
        System.out.println(data.toString() + node.toString());

        node.childrenAccept(this, data.toString() + "-");
 
        return data;  
    }

    public Object visit(StaticInitBlock node, Object data){
 
        System.out.println(data.toString() + node.toString());

        node.childrenAccept(this, data.toString() + "-");
 
        return data;  
    }

    public Object visit(MemberDecl node, Object data){
 
        System.out.println(data.toString() + node.toString());

        node.childrenAccept(this, data.toString() + "-");
 
        return data;  
    }

    public Object visit(VoidMethodDecl node, Object data){
 
        System.out.println(data.toString() + node.toString());

        node.childrenAccept(this, data.toString() + "-");
 
        return data;  
    }

    public Object visit(ConstructorDecl node, Object data){
 
        System.out.println(data.toString() + node.toString());

        node.childrenAccept(this, data.toString() + "-");
 
        return data;  
    }

    public Object visit(MethodOrFieldDecl node, Object data){
 
        System.out.println(data.toString() + node.toString());

        node.childrenAccept(this, data.toString() + "-");
 
        return data;  
    }

    public Object visit(MethodOrFieldRest node, Object data){
 
        System.out.println(data.toString() + node.toString());

        node.childrenAccept(this, data.toString() + "-");
 
        return data;  
    }

    public Object visit(FieldDeclaratorsRest node, Object data){
 
        System.out.println(data.toString() + node.toString());

        node.childrenAccept(this, data.toString() + "-");
 
        return data;  
    }

    public Object visit(MethodDeclaratorRest node, Object data){
 
        System.out.println(data.toString() + node.toString());

        node.childrenAccept(this, data.toString() + "-");
 
        return data;  
    }

    public Object visit(MethodBody node, Object data){
 
        System.out.println(data.toString() + node.toString());

        node.childrenAccept(this, data.toString() + "-");
 
        return data;  
    }

    public Object visit(EmptyBody node, Object data){
 
        System.out.println(data.toString() + node.toString());

        node.childrenAccept(this, data.toString() + "-");
 
        return data;  
    }

    public Object visit(VoidMethodDeclaratorRest node, Object data){
 
        System.out.println(data.toString() + node.toString());

        node.childrenAccept(this, data.toString() + "-");
 
        return data;  
    }

    public Object visit(ConstructorDeclaratorRest node, Object data){
 
        System.out.println(data.toString() + node.toString());

        node.childrenAccept(this, data.toString() + "-");
 
        return data;  
    }

    public Object visit(GenericMethodOrConstructorDecl node, Object data){
 
        System.out.println(data.toString() + node.toString());

        node.childrenAccept(this, data.toString() + "-");
 
        return data;  
    }

    public Object visit(GenericMethodOrConstructorRest node, Object data){
 
        System.out.println(data.toString() + node.toString());

        node.childrenAccept(this, data.toString() + "-");
 
        return data;  
    }

    public Object visit(InterfaceBody node, Object data){
 
        System.out.println(data.toString() + node.toString());

        node.childrenAccept(this, data.toString() + "-");
 
        return data;  
    }

    public Object visit(InterfaceBodyDeclaration node, Object data){
 
        System.out.println(data.toString() + node.toString());

        node.childrenAccept(this, data.toString() + "-");
 
        return data;  
    }

    public Object visit(NonEmptyInterfaceDeclaration node, Object data){
 
        System.out.println(data.toString() + node.toString());

        node.childrenAccept(this, data.toString() + "-");
 
        return data;  
    }

    public Object visit(InterfaceMemberDecl node, Object data){
 
        System.out.println(data.toString() + node.toString());

        node.childrenAccept(this, data.toString() + "-");
 
        return data;  
    }

    public Object visit(InterfaceMethodOrFieldDecl node, Object data){
 
        System.out.println(data.toString() + node.toString());

        node.childrenAccept(this, data.toString() + "-");
 
        return data;  
    }

    public Object visit(InterfaceMethodOrFieldRest node, Object data){
 
        System.out.println(data.toString() + node.toString());

        node.childrenAccept(this, data.toString() + "-");
 
        return data;  
    }

    public Object visit(ConstantDeclaratorsRest node, Object data){
 
        System.out.println(data.toString() + node.toString());

        node.childrenAccept(this, data.toString() + "-");
 
        return data;  
    }

    public Object visit(ConstantDeclaratorRest node, Object data){
 
        System.out.println(data.toString() + node.toString());

        node.childrenAccept(this, data.toString() + "-");
 
        return data;  
    }

    public Object visit(ConstantDeclarator node, Object data){
 
        System.out.println(data.toString() + node.toString());

        node.childrenAccept(this, data.toString() + "-");
 
        return data;  
    }

    public Object visit(InterfaceMethodDeclaratorRest node, Object data){
 
        System.out.println(data.toString() + node.toString());

        node.childrenAccept(this, data.toString() + "-");
 
        return data;  
    }

    public Object visit(VoidInterfaceMethodDeclaratorRest node, Object data){
 
        System.out.println(data.toString() + node.toString());

        node.childrenAccept(this, data.toString() + "-");
 
        return data;  
    }

    public Object visit(InterfaceGenericMethodDecl node, Object data){
 
        System.out.println(data.toString() + node.toString());

        node.childrenAccept(this, data.toString() + "-");
 
        return data;  
    }

    public Object visit(FormalParameters node, Object data){
 
        System.out.println(data.toString() + node.toString());

        node.childrenAccept(this, data.toString() + "-");
 
        return data;  
    }

    public Object visit(FormalParameterDecls node, Object data){
 
        System.out.println(data.toString() + node.toString());

        node.childrenAccept(this, data.toString() + "-");
 
        return data;  
    }

    public Object visit(VariableModifier node, Object data){
 
        System.out.println(data.toString() + node.toString());

        node.childrenAccept(this, data.toString() + "-");
 
        return data;  
    }

    public Object visit(FormalParameterDeclsRest node, Object data){
 
        System.out.println(data.toString() + node.toString());

        node.childrenAccept(this, data.toString() + "-");
 
        return data;  
    }

    public Object visit(VariableDeclaratorId node, Object data){
 
        System.out.println(data.toString() + node.toString());

        node.childrenAccept(this, data.toString() + "-");
 
        return data;  
    }

    public Object visit(VariableDeclarators node, Object data){
 
        System.out.println(data.toString() + node.toString());

        node.childrenAccept(this, data.toString() + "-");
 
        return data;  
    }

    public Object visit(VariableDeclarator node, Object data){
 
        System.out.println(data.toString() + node.toString());

        node.childrenAccept(this, data.toString() + "-");
 
        return data;  
    }

    public Object visit(VariableDeclaratorRest node, Object data){
 
        System.out.println(data.toString() + node.toString());

        node.childrenAccept(this, data.toString() + "-");
 
        return data;  
    }

    public Object visit(VariableInitializer node, Object data){
 
        System.out.println(data.toString() + node.toString());

        node.childrenAccept(this, data.toString() + "-");
 
        return data;  
    }

    public Object visit(ArrayInitializer node, Object data){
 
        System.out.println(data.toString() + node.toString());

        node.childrenAccept(this, data.toString() + "-");
 
        return data;  
    }

    public Object visit(Block node, Object data){
 
        System.out.println(data.toString() + node.toString());

        node.childrenAccept(this, data.toString() + "-");
 
        return data;  
    }

    public Object visit(BlockStatements node, Object data){
 
        System.out.println(data.toString() + node.toString());

        node.childrenAccept(this, data.toString() + "-");
 
        return data;  
    }

    public Object visit(BlockStatement node, Object data){
 
        System.out.println(data.toString() + node.toString());

        node.childrenAccept(this, data.toString() + "-");
 
        return data;  
    }

    public Object visit(LocalVariableDeclarationStatement node, Object data){
 
        System.out.println(data.toString() + node.toString());

        node.childrenAccept(this, data.toString() + "-");
 
        return data;  
    }

    public Object visit(Statement node, Object data){
 
        System.out.println(data.toString() + node.toString());

        node.childrenAccept(this, data.toString() + "-");
 
        return data;  
    }

    public Object visit(EmptyStatement node, Object data){
 
        System.out.println(data.toString() + node.toString());

        node.childrenAccept(this, data.toString() + "-");
 
        return data;  
    }

    public Object visit(IdentifierStatement node, Object data){
 
        System.out.println(data.toString() + node.toString());

        node.childrenAccept(this, data.toString() + "-");
 
        return data;  
    }

    public Object visit(StatementExpression node, Object data){
 
        System.out.println(data.toString() + node.toString());

        node.childrenAccept(this, data.toString() + "-");
 
        return data;  
    }

    public Object visit(IfStatement node, Object data){
 
        System.out.println(data.toString() + node.toString());

        node.childrenAccept(this, data.toString() + "-");
 
        return data;  
    }

    public Object visit(ElseStatement node, Object data){
 
        System.out.println(data.toString() + node.toString());

        node.childrenAccept(this, data.toString() + "-");
 
        return data;  
    }

    public Object visit(NoElseStatement node, Object data){
 
        System.out.println(data.toString() + node.toString());

        node.childrenAccept(this, data.toString() + "-");
 
        return data;  
    }

    public Object visit(AssertStatement node, Object data){
 
        System.out.println(data.toString() + node.toString());

        node.childrenAccept(this, data.toString() + "-");
 
        return data;  
    }

    public Object visit(SwitchStatement node, Object data){
 
        System.out.println(data.toString() + node.toString());

        node.childrenAccept(this, data.toString() + "-");
 
        return data;  
    }

    public Object visit(WhileStatement node, Object data){
 
        System.out.println(data.toString() + node.toString());

        node.childrenAccept(this, data.toString() + "-");
 
        return data;  
    }

    public Object visit(DoStatement node, Object data){
 
        System.out.println(data.toString() + node.toString());

        node.childrenAccept(this, data.toString() + "-");
 
        return data;  
    }

    public Object visit(ForStatement node, Object data){
 
        System.out.println(data.toString() + node.toString());

        node.childrenAccept(this, data.toString() + "-");
 
        return data;  
    }

    public Object visit(BreakStatement node, Object data){
 
        System.out.println(data.toString() + node.toString());

        node.childrenAccept(this, data.toString() + "-");
 
        return data;  
    }

    public Object visit(LabeledBreak node, Object data){
 
        System.out.println(data.toString() + node.toString());

        node.childrenAccept(this, data.toString() + "-");
 
        return data;  
    }

    public Object visit(UnlabeledBreak node, Object data){
 
        System.out.println(data.toString() + node.toString());

        node.childrenAccept(this, data.toString() + "-");
 
        return data;  
    }

    public Object visit(ContinueStatement node, Object data){
 
        System.out.println(data.toString() + node.toString());

        node.childrenAccept(this, data.toString() + "-");
 
        return data;  
    }

    public Object visit(LabeledContinue node, Object data){
 
        System.out.println(data.toString() + node.toString());

        node.childrenAccept(this, data.toString() + "-");
 
        return data;  
    }

    public Object visit(UnlabeledContinue node, Object data){
 
        System.out.println(data.toString() + node.toString());

        node.childrenAccept(this, data.toString() + "-");
 
        return data;  
    }

    public Object visit(ReturnStatement node, Object data){
 
        System.out.println(data.toString() + node.toString());

        node.childrenAccept(this, data.toString() + "-");
 
        return data;  
    }

    public Object visit(ThrowStatement node, Object data){
 
        System.out.println(data.toString() + node.toString());

        node.childrenAccept(this, data.toString() + "-");
 
        return data;  
    }

    public Object visit(SynchronizedStatement node, Object data){
 
        System.out.println(data.toString() + node.toString());

        node.childrenAccept(this, data.toString() + "-");
 
        return data;  
    }

    public Object visit(TryStatement node, Object data){
 
        System.out.println(data.toString() + node.toString());

        node.childrenAccept(this, data.toString() + "-");
 
        return data;  
    }

    public Object visit(Catches node, Object data){
 
        System.out.println(data.toString() + node.toString());

        node.childrenAccept(this, data.toString() + "-");
 
        return data;  
    }

    public Object visit(CatchClause node, Object data){
 
        System.out.println(data.toString() + node.toString());

        node.childrenAccept(this, data.toString() + "-");
 
        return data;  
    }

    public Object visit(CatchType node, Object data){
 
        System.out.println(data.toString() + node.toString());

        node.childrenAccept(this, data.toString() + "-");
 
        return data;  
    }

    public Object visit(Finally node, Object data){
 
        System.out.println(data.toString() + node.toString());

        node.childrenAccept(this, data.toString() + "-");
 
        return data;  
    }

    public Object visit(ResourceSpecification node, Object data){
 
        System.out.println(data.toString() + node.toString());

        node.childrenAccept(this, data.toString() + "-");
 
        return data;  
    }

    public Object visit(Resources node, Object data){
 
        System.out.println(data.toString() + node.toString());

        node.childrenAccept(this, data.toString() + "-");
 
        return data;  
    }

    public Object visit(Resource node, Object data){
 
        System.out.println(data.toString() + node.toString());

        node.childrenAccept(this, data.toString() + "-");
 
        return data;  
    }

    public Object visit(SwitchBlockStatementGroups node, Object data){
 
        System.out.println(data.toString() + node.toString());

        node.childrenAccept(this, data.toString() + "-");
 
        return data;  
    }

    public Object visit(SwitchBlockStatementGroup node, Object data){
 
        System.out.println(data.toString() + node.toString());

        node.childrenAccept(this, data.toString() + "-");
 
        return data;  
    }

    public Object visit(SwitchLabels node, Object data){
 
        System.out.println(data.toString() + node.toString());

        node.childrenAccept(this, data.toString() + "-");
 
        return data;  
    }

    public Object visit(SwitchLabel node, Object data){
 
        System.out.println(data.toString() + node.toString());

        node.childrenAccept(this, data.toString() + "-");
 
        return data;  
    }

    public Object visit(EnumConstantName node, Object data){
 
        System.out.println(data.toString() + node.toString());

        node.childrenAccept(this, data.toString() + "-");
 
        return data;  
    }

    public Object visit(ForControl node, Object data){
 
        System.out.println(data.toString() + node.toString());

        node.childrenAccept(this, data.toString() + "-");
 
        return data;  
    }

    public Object visit(ForVarControl node, Object data){
 
        System.out.println(data.toString() + node.toString());

        node.childrenAccept(this, data.toString() + "-");
 
        return data;  
    }

    public Object visit(ForVarControlRest node, Object data){
 
        System.out.println(data.toString() + node.toString());

        node.childrenAccept(this, data.toString() + "-");
 
        return data;  
    }

    public Object visit(ForVariableDeclaratorsRest node, Object data){
 
        System.out.println(data.toString() + node.toString());

        node.childrenAccept(this, data.toString() + "-");
 
        return data;  
    }

    public Object visit(ForInit node, Object data){
 
        System.out.println(data.toString() + node.toString());

        node.childrenAccept(this, data.toString() + "-");
 
        return data;  
    }

    public Object visit(ForUpdate node, Object data){
 
        System.out.println(data.toString() + node.toString());

        node.childrenAccept(this, data.toString() + "-");
 
        return data;  
    }

    public Object visit(Expression node, Object data){
 
        System.out.println(data.toString() + node.toString());

        node.childrenAccept(this, data.toString() + "-");
 
        return data;  
    }

    public Object visit(ExpressionRest node, Object data){
 
        System.out.println(data.toString() + node.toString());

        node.childrenAccept(this, data.toString() + "-");
 
        return data;  
    }

    public Object visit(AssignmentOperator node, Object data){
 
        System.out.println(data.toString() + node.toString());

        node.childrenAccept(this, data.toString() + "-");
 
        return data;  
    }

    public Object visit(Expression1 node, Object data){
 
        System.out.println(data.toString() + node.toString());

        node.childrenAccept(this, data.toString() + "-");
 
        return data;  
    }

    public Object visit(Expression1Rest node, Object data){
 
        System.out.println(data.toString() + node.toString());

        node.childrenAccept(this, data.toString() + "-");
 
        return data;  
    }

    public Object visit(Expression2 node, Object data){
 
        System.out.println(data.toString() + node.toString());

        node.childrenAccept(this, data.toString() + "-");
 
        return data;  
    }

    public Object visit(Expression2Rest node, Object data){
 
        System.out.println(data.toString() + node.toString());

        node.childrenAccept(this, data.toString() + "-");
 
        return data;  
    }

    public Object visit(InfixOp node, Object data){
 
        System.out.println(data.toString() + node.toString());

        node.childrenAccept(this, data.toString() + "-");
 
        return data;  
    }

    public Object visit(Expression3 node, Object data){
 
        System.out.println(data.toString() + node.toString());

        node.childrenAccept(this, data.toString() + "-");
 
        return data;  
    }

    public Object visit(PrefixOp node, Object data){
 
        System.out.println(data.toString() + node.toString());

        node.childrenAccept(this, data.toString() + "-");
 
        return data;  
    }

    public Object visit(PostfixOp node, Object data){
 
        System.out.println(data.toString() + node.toString());

        node.childrenAccept(this, data.toString() + "-");
 
        return data;  
    }

    public Object visit(Primary node, Object data){
 
        System.out.println(data.toString() + node.toString());

        node.childrenAccept(this, data.toString() + "-");
 
        return data;  
    }

    public Object visit(Literal node, Object data){
 
        System.out.println(data.toString() + node.toString());

        node.childrenAccept(this, data.toString() + "-");
 
        return data;  
    }

    public Object visit(IntegerLiteral node, Object data){
 
        System.out.println(data.toString() + node.toString());

        node.childrenAccept(this, data.toString() + "-");
 
        return data;  
    }

    public Object visit(FloatingPointLiteral node, Object data){
 
        System.out.println(data.toString() + node.toString());

        node.childrenAccept(this, data.toString() + "-");
 
        return data;  
    }

    public Object visit(CharacterLiteral node, Object data){
 
        System.out.println(data.toString() + node.toString());

        node.childrenAccept(this, data.toString() + "-");
 
        return data;  
    }

    public Object visit(StringLiteral node, Object data){
 
        System.out.println(data.toString() + node.toString());

        node.childrenAccept(this, data.toString() + "-");
 
        return data;  
    }

    public Object visit(BooleanLiteral node, Object data){
 
        System.out.println(data.toString() + node.toString());

        node.childrenAccept(this, data.toString() + "-");
 
        return data;  
    }

    public Object visit(NullLiteral node, Object data){
 
        System.out.println(data.toString() + node.toString());

        node.childrenAccept(this, data.toString() + "-");
 
        return data;  
    }

    public Object visit(ParExpression node, Object data){
 
        System.out.println(data.toString() + node.toString());

        node.childrenAccept(this, data.toString() + "-");
 
        return data;  
    }

    public Object visit(Arguments node, Object data){
 
        System.out.println(data.toString() + node.toString());

        node.childrenAccept(this, data.toString() + "-");
 
        return data;  
    }

    public Object visit(SuperSuffix node, Object data){
 
        System.out.println(data.toString() + node.toString());

        node.childrenAccept(this, data.toString() + "-");
 
        return data;  
    }

    public Object visit(ExplicitGenericInvocationSuffix node, Object data){
 
        System.out.println(data.toString() + node.toString());

        node.childrenAccept(this, data.toString() + "-");
 
        return data;  
    }

    public Object visit(Creator node, Object data){
 
        System.out.println(data.toString() + node.toString());

        node.childrenAccept(this, data.toString() + "-");
 
        return data;  
    }

    public Object visit(CreatedName node, Object data){
 
        System.out.println(data.toString() + node.toString());

        node.childrenAccept(this, data.toString() + "-");
 
        return data;  
    }

    public Object visit(ClassCreatorRest node, Object data){
 
        System.out.println(data.toString() + node.toString());

        node.childrenAccept(this, data.toString() + "-");
 
        return data;  
    }

    public Object visit(ArrayCreatorRest node, Object data){
 
        System.out.println(data.toString() + node.toString());

        node.childrenAccept(this, data.toString() + "-");
 
        return data;  
    }

    public Object visit(IdentifierSuffix node, Object data){
 
        System.out.println(data.toString() + node.toString());

        node.childrenAccept(this, data.toString() + "-");
 
        return data;  
    }

    public Object visit(ExplicitGenericInvocation node, Object data){
 
        System.out.println(data.toString() + node.toString());

        node.childrenAccept(this, data.toString() + "-");
 
        return data;  
    }

    public Object visit(InnerCreator node, Object data){
 
        System.out.println(data.toString() + node.toString());

        node.childrenAccept(this, data.toString() + "-");
 
        return data;  
    }

    public Object visit(Selector node, Object data){
 
        System.out.println(data.toString() + node.toString());

        node.childrenAccept(this, data.toString() + "-");
 
        return data;  
    }

    public Object visit(EnumBody node, Object data){
 
        System.out.println(data.toString() + node.toString());

        node.childrenAccept(this, data.toString() + "-");
 
        return data;  
    }

    public Object visit(EnumConstants node, Object data){
 
        System.out.println(data.toString() + node.toString());

        node.childrenAccept(this, data.toString() + "-");
 
        return data;  
    }

    public Object visit(EnumConstant node, Object data){
 
        System.out.println(data.toString() + node.toString());

        node.childrenAccept(this, data.toString() + "-");
 
        return data;  
    }

    public Object visit(EnumBodyDeclarations node, Object data){
 
        System.out.println(data.toString() + node.toString());

        node.childrenAccept(this, data.toString() + "-");
 
        return data;  
    }

    public Object visit(AnnotationTypeBody node, Object data){
 
        System.out.println(data.toString() + node.toString());

        node.childrenAccept(this, data.toString() + "-");
 
        return data;  
    }

    public Object visit(AnnotationTypeElementDeclarations node, Object data){
 
        System.out.println(data.toString() + node.toString());

        node.childrenAccept(this, data.toString() + "-");
 
        return data;  
    }

    public Object visit(AnnotationTypeElementDeclaration node, Object data){
 
        System.out.println(data.toString() + node.toString());

        node.childrenAccept(this, data.toString() + "-");
 
        return data;  
    }

    public Object visit(AnnotationTypeElementRest node, Object data){
 
        System.out.println(data.toString() + node.toString());

        node.childrenAccept(this, data.toString() + "-");
 
        return data;  
    }

    public Object visit(AnnotationMethodOrConstantRest node, Object data){
 
        System.out.println(data.toString() + node.toString());

        node.childrenAccept(this, data.toString() + "-");
 
        return data;  
    }

    public Object visit(AnnotationMethodRest node, Object data){
 
        System.out.println(data.toString() + node.toString());

        node.childrenAccept(this, data.toString() + "-");
 
        return data;  
    }

}