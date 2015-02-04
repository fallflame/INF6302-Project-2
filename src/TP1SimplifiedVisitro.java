/**
 * @author Yan Xu
 * 2015-01-16
 * This visitor give:
 * id, fileName, className, methodName, #if, #while, #break, #varLocal
 */
import java.util.Stack;

public class TP1Visitor implements JavaParser1_7Visitor {

//Store the numbers in field, Don't consider the nested class
/*    protected int numberOfId = 1;
    protected int numberOfIf;
    protected int numberOfWhile;
    protected int numberOfBreak;
    protected int numberOfVarLocal;
*/      
    static int id; // count the line number of the output


// A new class used only in this class, it used for count the info needed and print them.
    class Counter{

        String fileName;
        String className;
        String methodName;

        int numberOfIf;
        int numberOfWhile;
        int numberOfBreak;
        int numberOfVarLocal;

        void increaseNumberOfIfByOne(){
            this.numberOfIf += 1;
        }

        void increaseNumberOfWhileByOne(){
            this.numberOfWhile += 1;
        }

        void increaseNumberOfBreakByOne(){
            this.numberOfBreak += 1;
        }

        void increaseNumberOfVarLocalByOne(){
            this.numberOfVarLocal += 1;
        }

        void addCounter(Counter c){
            this.numberOfIf += c.numberOfIf;
            this.numberOfWhile += c.numberOfWhile;
            this.numberOfBreak += c.numberOfBreak;
            this.numberOfVarLocal += c.numberOfVarLocal;
        }

        void print() {
            System.out.println(
                TP1Visitor.id + ";" +
                this.fileName + ";" +
                this.className + ";" +
                this.methodName + ";" +
                this.numberOfIf + ";" +
                this.numberOfWhile + ";" +
                this.numberOfBreak + ";" +
                this.numberOfVarLocal
            );
            TP1Visitor.id += 1;
        }

    }
//Becasue it has some nested situation, like the MethodOrFieldDecl and GenericMethodOrConstructorRest both use MethodDeclaratorRest, so I need a flag to show if the count is processing or not
//    protected Boolean counting = false;


    public Object visit(CompilationUnit node, Object data){
        
        String fileName = data.toString();

        //create a new Stack to save counters
        Stack<Counter> countersStack = new Stack<Counter>();

        //create a new counter and push it
        Counter fileCounter = new Counter();
        fileCounter.fileName = fileName;

        countersStack.push(fileCounter);
 
        node.childrenAccept(this, countersStack);

        // with a push, must be a pop
        Counter counter = countersStack.pop();

        return data;  
    }

    public Object visit(NormalClassDeclaration node, Object data){

        Stack<Counter> countersStack = (Stack<Counter>) data;

        String className = ((SimpleNode)(node.jjtGetChild(0))).jjtGetFirstToken().toString();

        Counter classCounter = new Counter();
        classCounter.fileName = countersStack.peek().fileName;
        classCounter.className = className;

        countersStack.push(classCounter);
 
        node.childrenAccept(this, countersStack);
 
        Counter counter = countersStack.pop();
        countersStack.peek().addCounter(counter);

        return data;  
    }


    public Object visit(MethodBody node, Object data){

        Stack<Counter> countersStack = (Stack<Counter>)data;
 
        String methodName = "noName";
        SimpleNode currentNode = node;

        while(methodName == "noName"){
            
            currentNode = (SimpleNode)currentNode.jjtGetParent();

            int i;
            for (i = 0; i < currentNode.jjtGetNumChildren(); i++){
                SimpleNode childNode = (SimpleNode)currentNode.jjtGetChild(i);
                if (childNode.toString() == "Identifier"){
                    // get the method name
                    methodName = childNode.jjtGetFirstToken().toString(); 
                    break;
                }
            }

        }

        Counter methodCounter = new Counter();
        methodCounter.fileName = countersStack.peek().fileName;
        methodCounter.className = countersStack.peek().className;
        methodCounter.methodName = methodName;

        countersStack.push(methodCounter);

        node.childrenAccept(this, countersStack);    

        Counter counter = countersStack.pop();
        counter.print();
        countersStack.peek().addCounter(counter);
 
        return data;  
    }


//This section is used to count the number of targeted statements

    public Object visit(IfStatement node, Object data){
    
        ((Stack<Counter>)data).peek().increaseNumberOfIfByOne();

        node.childrenAccept(this, data);
 
        return data;  
    }

    public Object visit(WhileStatement node, Object data){
    
        ((Stack<Counter>)data).peek().increaseNumberOfWhileByOne();

        node.childrenAccept(this, data);
 
        return data;  
    }

    public Object visit(BreakStatement node, Object data){
        
        ((Stack<Counter>)data).peek().increaseNumberOfBreakByOne();

        node.childrenAccept(this, data);
 
        return data;  
    }

    public Object visit(LocalVariableDeclarationStatement node, Object data){
 
        ((Stack<Counter>)data).peek().increaseNumberOfVarLocalByOne();

        node.childrenAccept(this, data);
 
        return data;  
    }

/*=========================Don't need to change after===========================================================*/

    public Object visit(GenericMethodOrConstructorRest node, Object data){
    
        node.childrenAccept(this, data);
 
        return data;  
    }

    public Object visit(ConstructorDecl node, Object data){
        
        node.childrenAccept(this, data);
        
        return data;  
    }

    public Object visit(VoidMethodDecl node, Object data){
 
        node.childrenAccept(this, data);
    
        return data;  
    }

    public Object visit(MethodDeclaratorRest node, Object data){
 
        node.childrenAccept(this, data);
        
        return data;  
    }

    public Object visit(SimpleNode node, Object data){
 
        node.childrenAccept(this, data);
 
        return data;          
    }

    public Object visit(Identifier node, Object data){
 
        //node.childrenAccept(this, data);
  
        return data;    
    }

    public Object visit(QualifiedIdentifier node, Object data){
 
        //node.childrenAccept(this, data);
 
        return data;  
    }

    public Object visit(QualifiedIdentifierList node, Object data){
 
        //node.childrenAccept(this, data);
 
        return data;  
    }

    public Object visit(ImportDeclaration node, Object data){
 
        node.childrenAccept(this, data);
 
        return data;  
    }

    public Object visit(Ellipsis node, Object data){
 
        //node.childrenAccept(this, data);
 
        return data;  
    }

    public Object visit(HasEllipsis node, Object data){
 
        //node.childrenAccept(this, data);
 
        return data;  
    }

    public Object visit(HasNotEllipsis node, Object data){
 
        node.childrenAccept(this, data);
 
        return data;  
    }

    public Object visit(PossibleStaticModifier node, Object data){
 
        //node.childrenAccept(this, data);
 
        return data;  
    }

    public Object visit(HasStaticModifier node, Object data){
 
        node.childrenAccept(this, data);
 
        return data;  
    }

    public Object visit(HasNotStaticModifier node, Object data){
 
        node.childrenAccept(this, data);
 
        return data;  
    }

    public Object visit(TypeDeclaration node, Object data){
 
        node.childrenAccept(this, data);
 
        return data;  
    }

    public Object visit(EmptyInterfaceDeclaration node, Object data){
 
        node.childrenAccept(this, data);
 
        return data;  
    }

    public Object visit(ClassOrInterfaceDeclaration node, Object data){
 
        node.childrenAccept(this, data);
 
        return data;  
    }

    public Object visit(ModifierList node, Object data){
 
        node.childrenAccept(this, data);
 
        return data;  
    }

    public Object visit(ClassDeclaration node, Object data){
 
        node.childrenAccept(this, data);
 
        return data;  
    }

    public Object visit(InterfaceDeclaration node, Object data){
 
        //node.childrenAccept(this, data);
 
        return data;  
    }

    public Object visit(EnumDeclaration node, Object data){
 
        node.childrenAccept(this, data);
 
        return data;  
    }

    public Object visit(NormalInterfaceDeclaration node, Object data){
 
        node.childrenAccept(this, data);
 
        return data;  
    }

    public Object visit(AnnotationTypeDeclaration node, Object data){
 
        node.childrenAccept(this, data);
 
        return data;  
    }

    public Object visit(Type node, Object data){
 
        //node.childrenAccept(this, data);
 
        return data;  
    }

    public Object visit(BasicType node, Object data){
 
        node.childrenAccept(this, data);
 
        return data;  
    }

    public Object visit(ReferenceType node, Object data){
 
        node.childrenAccept(this, data);
 
        return data;  
    }

    public Object visit(TypeArguments node, Object data){
 
        node.childrenAccept(this, data);
 
        return data;  
    }

    public Object visit(TypeArgument node, Object data){
 
        node.childrenAccept(this, data);
 
        return data;  
    }

    public Object visit(NonWildcardTypeArguments node, Object data){
 
        node.childrenAccept(this, data);
 
        return data;  
    }

    public Object visit(TypeList node, Object data){
 
        node.childrenAccept(this, data);
 
        return data;  
    }

    public Object visit(TypeArgumentsOrDiamond node, Object data){
 
        node.childrenAccept(this, data);
 
        return data;  
    }

    public Object visit(NonWildcardTypeArgumentsOrDiamond node, Object data){
 
        node.childrenAccept(this, data);
 
        return data;  
    }

    public Object visit(TypeParameters node, Object data){
 
        node.childrenAccept(this, data);
 
        return data;  
    }

    public Object visit(TypeParameter node, Object data){
 
        node.childrenAccept(this, data);
 
        return data;  
    }

    public Object visit(Bound node, Object data){
 
        node.childrenAccept(this, data);
 
        return data;  
    }

    public Object visit(Modifier node, Object data){
 
        node.childrenAccept(this, data);
 
        return data;  
    }

    public Object visit(KeywordModifier node, Object data){
 
        node.childrenAccept(this, data);
 
        return data;  
    }

    public Object visit(Annotations node, Object data){
 
        node.childrenAccept(this, data);
 
        return data;  
    }

    public Object visit(Annotation node, Object data){
 
        node.childrenAccept(this, data);
 
        return data;  
    }

    public Object visit(AnnotationElement node, Object data){
 
        node.childrenAccept(this, data);
 
        return data;  
    }

    public Object visit(ElementValuePairs node, Object data){
 
        node.childrenAccept(this, data);
 
        return data;  
    }

    public Object visit(ElementValuePair node, Object data){
 
        node.childrenAccept(this, data);
 
        return data;  
    }

    public Object visit(ElementValue node, Object data){
 
        node.childrenAccept(this, data);
 
        return data;  
    }

    public Object visit(ElementValueArrayInitializer node, Object data){
 
        node.childrenAccept(this, data);
 
        return data;  
    }

    public Object visit(ElementValues node, Object data){
 
        node.childrenAccept(this, data);
 
        return data;  
    }

    public Object visit(ClassBody node, Object data){
 
        node.childrenAccept(this, data);
 
        return data;  
    }

    public Object visit(ClassBodyDeclaration node, Object data){
 
        node.childrenAccept(this, data);
 
        return data;  
    }

    public Object visit(StaticInitBlock node, Object data){
 
        node.childrenAccept(this, data);
 
        return data;  
    }

    public Object visit(MemberDecl node, Object data){
 
        node.childrenAccept(this, data);
 
        return data;  
    }

    public Object visit(MethodOrFieldDecl node, Object data){
 
        node.childrenAccept(this, data);
 
        return data;  
    }

    public Object visit(MethodOrFieldRest node, Object data){
 
        node.childrenAccept(this, data);
 
        return data;  
    }

    public Object visit(FieldDeclaratorsRest node, Object data){
 
        node.childrenAccept(this, data);
 
        return data;  
    }

    public Object visit(EmptyBody node, Object data){
 
        node.childrenAccept(this, data);
 
        return data;  
    }

    public Object visit(VoidMethodDeclaratorRest node, Object data){
 
        node.childrenAccept(this, data);
 
        return data;  
    }

    public Object visit(ConstructorDeclaratorRest node, Object data){
 
        node.childrenAccept(this, data);
 
        return data;  
    }

    public Object visit(GenericMethodOrConstructorDecl node, Object data){
 
        node.childrenAccept(this, data);
 
        return data;  
    }

    public Object visit(InterfaceBody node, Object data){
 
        node.childrenAccept(this, data);
 
        return data;  
    }

    public Object visit(InterfaceBodyDeclaration node, Object data){
 
        node.childrenAccept(this, data);
 
        return data;  
    }

    public Object visit(NonEmptyInterfaceDeclaration node, Object data){
 
        node.childrenAccept(this, data);
 
        return data;  
    }

    public Object visit(InterfaceMemberDecl node, Object data){
 
        node.childrenAccept(this, data);
 
        return data;  
    }

    public Object visit(InterfaceMethodOrFieldDecl node, Object data){
 
        node.childrenAccept(this, data);
 
        return data;  
    }

    public Object visit(InterfaceMethodOrFieldRest node, Object data){
 
        node.childrenAccept(this, data);
 
        return data;  
    }

    public Object visit(ConstantDeclaratorsRest node, Object data){
 
        node.childrenAccept(this, data);
 
        return data;  
    }

    public Object visit(ConstantDeclaratorRest node, Object data){
 
        node.childrenAccept(this, data);
 
        return data;  
    }

    public Object visit(ConstantDeclarator node, Object data){
 
        node.childrenAccept(this, data);
 
        return data;  
    }

    public Object visit(InterfaceMethodDeclaratorRest node, Object data){
 
        node.childrenAccept(this, data);
 
        return data;  
    }

    public Object visit(VoidInterfaceMethodDeclaratorRest node, Object data){
 
        node.childrenAccept(this, data);
 
        return data;  
    }

    public Object visit(InterfaceGenericMethodDecl node, Object data){
 
        node.childrenAccept(this, data);
 
        return data;  
    }

    public Object visit(FormalParameters node, Object data){
 
        node.childrenAccept(this, data);
 
        return data;  
    }

    public Object visit(FormalParameterDecls node, Object data){
 
        node.childrenAccept(this, data);
 
        return data;  
    }

    public Object visit(VariableModifier node, Object data){
 
        node.childrenAccept(this, data);
 
        return data;  
    }

    public Object visit(FormalParameterDeclsRest node, Object data){
 
        node.childrenAccept(this, data);
 
        return data;  
    }

    public Object visit(VariableDeclaratorId node, Object data){
 
        node.childrenAccept(this, data);
 
        return data;  
    }

    public Object visit(VariableDeclarators node, Object data){
 
        //node.childrenAccept(this, data);
 
        return data;  
    }

    public Object visit(VariableDeclarator node, Object data){
 
        node.childrenAccept(this, data);
 
        return data;  
    }

    public Object visit(VariableDeclaratorRest node, Object data){
 
        node.childrenAccept(this, data);
 
        return data;  
    }

    public Object visit(VariableInitializer node, Object data){
 
        node.childrenAccept(this, data);
 
        return data;  
    }

    public Object visit(ArrayInitializer node, Object data){
 
        node.childrenAccept(this, data);
 
        return data;  
    }

    public Object visit(Block node, Object data){
 
        node.childrenAccept(this, data);
 
        return data;  
    }

    public Object visit(BlockStatements node, Object data){
 
        node.childrenAccept(this, data);
 
        return data;  
    }

    public Object visit(BlockStatement node, Object data){
 
        node.childrenAccept(this, data);
 
        return data;  
    }

    public Object visit(Statement node, Object data){
 
        node.childrenAccept(this, data);
 
        return data;  
    }

    public Object visit(EmptyStatement node, Object data){
 
        node.childrenAccept(this, data);
 
        return data;  
    }

    public Object visit(IdentifierStatement node, Object data){
 
        node.childrenAccept(this, data);
 
        return data;  
    }

    public Object visit(StatementExpression node, Object data){
 
        node.childrenAccept(this, data);
 
        return data;  
    }

    public Object visit(ElseStatement node, Object data){
 
        node.childrenAccept(this, data);
 
        return data;  
    }

    public Object visit(NoElseStatement node, Object data){
 
        node.childrenAccept(this, data);
 
        return data;  
    }

    public Object visit(AssertStatement node, Object data){
 
        node.childrenAccept(this, data);
 
        return data;  
    }

    public Object visit(SwitchStatement node, Object data){
 
        node.childrenAccept(this, data);
 
        return data;  
    }


    public Object visit(DoStatement node, Object data){
 
        node.childrenAccept(this, data);
 
        return data;  
    }

    public Object visit(ForStatement node, Object data){
 
        node.childrenAccept(this, data);
 
        return data;  
    }

    public Object visit(LabeledBreak node, Object data){
 
        node.childrenAccept(this, data);
 
        return data;  
    }

    public Object visit(UnlabeledBreak node, Object data){
 
        node.childrenAccept(this, data);
 
        return data;  
    }

    public Object visit(ContinueStatement node, Object data){
 
        node.childrenAccept(this, data);
 
        return data;  
    }

    public Object visit(LabeledContinue node, Object data){
 
        node.childrenAccept(this, data);
 
        return data;  
    }

    public Object visit(UnlabeledContinue node, Object data){
 
        node.childrenAccept(this, data);
 
        return data;  
    }

    public Object visit(ReturnStatement node, Object data){
 
        node.childrenAccept(this, data);
 
        return data;  
    }

    public Object visit(ThrowStatement node, Object data){
 
        node.childrenAccept(this, data);
 
        return data;  
    }

    public Object visit(SynchronizedStatement node, Object data){
 
        node.childrenAccept(this, data);
 
        return data;  
    }

    public Object visit(TryStatement node, Object data){
 
        node.childrenAccept(this, data);
 
        return data;  
    }

    public Object visit(Catches node, Object data){
 
        node.childrenAccept(this, data);
 
        return data;  
    }

    public Object visit(CatchClause node, Object data){
 
        node.childrenAccept(this, data);
 
        return data;  
    }

    public Object visit(CatchType node, Object data){
 
        node.childrenAccept(this, data);
 
        return data;  
    }

    public Object visit(Finally node, Object data){
 
        node.childrenAccept(this, data);
 
        return data;  
    }

    public Object visit(ResourceSpecification node, Object data){
 
        node.childrenAccept(this, data);
 
        return data;  
    }

    public Object visit(Resources node, Object data){
 
        node.childrenAccept(this, data);
 
        return data;  
    }

    public Object visit(Resource node, Object data){
 
        node.childrenAccept(this, data);
 
        return data;  
    }

    public Object visit(SwitchBlockStatementGroups node, Object data){
 
        node.childrenAccept(this, data);
 
        return data;  
    }

    public Object visit(SwitchBlockStatementGroup node, Object data){
 
        node.childrenAccept(this, data);
 
        return data;  
    }

    public Object visit(SwitchLabels node, Object data){
 
        node.childrenAccept(this, data);
 
        return data;  
    }

    public Object visit(SwitchLabel node, Object data){
 
        node.childrenAccept(this, data);
 
        return data;  
    }

    public Object visit(EnumConstantName node, Object data){
 
        node.childrenAccept(this, data);
 
        return data;  
    }

    public Object visit(ForControl node, Object data){
 
        node.childrenAccept(this, data);
 
        return data;  
    }

    public Object visit(ForVarControl node, Object data){
 
        node.childrenAccept(this, data);
 
        return data;  
    }

    public Object visit(ForVarControlRest node, Object data){
 
        node.childrenAccept(this, data);
 
        return data;  
    }

    public Object visit(ForVariableDeclaratorsRest node, Object data){
 
        node.childrenAccept(this, data);
 
        return data;  
    }

    public Object visit(ForInit node, Object data){
 
        node.childrenAccept(this, data);
 
        return data;  
    }

    public Object visit(ForUpdate node, Object data){
 
        node.childrenAccept(this, data);
 
        return data;  
    }

    public Object visit(Expression node, Object data){
 
        node.childrenAccept(this, data);
 
        return data;  
    }

    public Object visit(ExpressionRest node, Object data){
 
        node.childrenAccept(this, data);
 
        return data;  
    }

    public Object visit(AssignmentOperator node, Object data){
 
        node.childrenAccept(this, data);
 
        return data;  
    }

    public Object visit(Expression1 node, Object data){
 
        node.childrenAccept(this, data);
 
        return data;  
    }

    public Object visit(Expression1Rest node, Object data){
 
        node.childrenAccept(this, data);
 
        return data;  
    }

    public Object visit(Expression2 node, Object data){
 
        node.childrenAccept(this, data);
 
        return data;  
    }

    public Object visit(Expression2Rest node, Object data){
 
        node.childrenAccept(this, data);
 
        return data;  
    }

    public Object visit(InfixOp node, Object data){
 
        node.childrenAccept(this, data);
 
        return data;  
    }

    public Object visit(Expression3 node, Object data){
 
        node.childrenAccept(this, data);
 
        return data;  
    }

    public Object visit(PrefixOp node, Object data){
 
        node.childrenAccept(this, data);
 
        return data;  
    }

    public Object visit(PostfixOp node, Object data){
 
        node.childrenAccept(this, data);
 
        return data;  
    }

    public Object visit(Primary node, Object data){
 
        node.childrenAccept(this, data);
 
        return data;  
    }

    public Object visit(Literal node, Object data){
 
        node.childrenAccept(this, data);
 
        return data;  
    }

    public Object visit(IntegerLiteral node, Object data){
 
        node.childrenAccept(this, data);
 
        return data;  
    }

    public Object visit(FloatingPointLiteral node, Object data){
 
        node.childrenAccept(this, data);
 
        return data;  
    }

    public Object visit(CharacterLiteral node, Object data){
 
        node.childrenAccept(this, data);
 
        return data;  
    }

    public Object visit(StringLiteral node, Object data){
 
        node.childrenAccept(this, data);
 
        return data;  
    }

    public Object visit(BooleanLiteral node, Object data){
 
        node.childrenAccept(this, data);
 
        return data;  
    }

    public Object visit(NullLiteral node, Object data){
 
        node.childrenAccept(this, data);
 
        return data;  
    }

    public Object visit(ParExpression node, Object data){
 
        node.childrenAccept(this, data);
 
        return data;  
    }

    public Object visit(Arguments node, Object data){
 
        node.childrenAccept(this, data);
 
        return data;  
    }

    public Object visit(SuperSuffix node, Object data){
 
        node.childrenAccept(this, data);
 
        return data;  
    }

    public Object visit(ExplicitGenericInvocationSuffix node, Object data){
 
        node.childrenAccept(this, data);
 
        return data;  
    }

    public Object visit(Creator node, Object data){
 
        node.childrenAccept(this, data);
 
        return data;  
    }

    public Object visit(CreatedName node, Object data){
 
        node.childrenAccept(this, data);
 
        return data;  
    }

    public Object visit(ClassCreatorRest node, Object data){
 
        node.childrenAccept(this, data);
 
        return data;  
    }

    public Object visit(ArrayCreatorRest node, Object data){
 
        node.childrenAccept(this, data);
 
        return data;  
    }

    public Object visit(IdentifierSuffix node, Object data){
 
        node.childrenAccept(this, data);
 
        return data;  
    }

    public Object visit(ExplicitGenericInvocation node, Object data){
 
        node.childrenAccept(this, data);
 
        return data;  
    }

    public Object visit(InnerCreator node, Object data){
 
        node.childrenAccept(this, data);
 
        return data;  
    }

    public Object visit(Selector node, Object data){
 
        node.childrenAccept(this, data);
 
        return data;  
    }

    public Object visit(EnumBody node, Object data){
 
        node.childrenAccept(this, data);
 
        return data;  
    }

    public Object visit(EnumConstants node, Object data){
 
        node.childrenAccept(this, data);
 
        return data;  
    }

    public Object visit(EnumConstant node, Object data){
 
        node.childrenAccept(this, data);
 
        return data;  
    }

    public Object visit(EnumBodyDeclarations node, Object data){
 
        node.childrenAccept(this, data);
 
        return data;  
    }

    public Object visit(AnnotationTypeBody node, Object data){
 
        node.childrenAccept(this, data);
 
        return data;  
    }

    public Object visit(AnnotationTypeElementDeclarations node, Object data){
 
        node.childrenAccept(this, data);
 
        return data;  
    }

    public Object visit(AnnotationTypeElementDeclaration node, Object data){
 
        node.childrenAccept(this, data);
 
        return data;  
    }

    public Object visit(AnnotationTypeElementRest node, Object data){
 
        node.childrenAccept(this, data);
 
        return data;  
    }

    public Object visit(AnnotationMethodOrConstantRest node, Object data){
 
        node.childrenAccept(this, data);
 
        return data;  
    }

    public Object visit(AnnotationMethodRest node, Object data){
 
        node.childrenAccept(this, data);
 
        return data;  
    }

}