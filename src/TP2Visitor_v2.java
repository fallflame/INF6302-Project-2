import java.util.ArrayList;
import java.util.Stack;

/**
 * @author Yan Xu
 * 2015-01-16
 * This visitor give:
 * id, fileName, className, methodName, #if, #while, #break, #varLocal
 */


public class TP2Visitor_v2 implements JavaParser1_7Visitor {

    static int id; // count the line number of the output

    class ClassNode{
    	private String className;
    	private ArrayList<String> attributes;
    	private ArrayList<String> methods;
    	
    	ClassNode(String className){
    		this.className = className;
    	}
    	
    	public void addAttribute(String attribute){
    		this.attributes.add(attribute);
    	}
    	
    	public void addMethod(String method){
    		this.attributes.add(method);
    	}
    	
    	public void printClassNode(){
    		
    		StringBuffer attributesStringBuffer = new StringBuffer();
    		for (String s : attributes) {
    			attributesStringBuffer.append(s + "&#92;n");
    		}
    		String attributesString = attributesStringBuffer.toString();
    		
    		StringBuffer methodsStringBuffer = new StringBuffer();
    		for (String s : methods) {
    			methodsStringBuffer.append(s + "&#92;n");
    		}
    		String methodsString = methodsStringBuffer.toString();
    		
    		
    		String stringInGraphViz = 
    				this.className + " [label= <" + 
	    				"<TABLE BORDER=\"0\" CELLBORDER=\"1\" CELLSPACING=\"0\">" +
		    				"<TR><TD>" + this.className + "</TD></TR>" +
		    				"<TR><TD ALIGN=\"LEFT\">" + attributesString + "</TD></TR>" +
		    				"<TR><TD ALIGN=\"LEFT\">" + methodsString + "</TD></TR>" +
	    				"</TABLE>" +
    				">];";
    		
    		System.out.println(stringInGraphViz);
    	}
    }


    public Object visit(CompilationUnit node, Object data){
    	
    	Stack<ClassNode> classNodeStack = new Stack<ClassNode>();
    	
        node.childrenAccept(this, classNodeStack);

        return data;  
    }

    public Object visit(NormalClassDeclaration node, Object data){
    	
    	Stack<ClassNode> classNodeStack = (Stack<ClassNode>) data;
    	
    	String className = "classNoName";
    	for (int i = 0; i < node.jjtGetNumChildren(); i++){
            SimpleNode childNode = (SimpleNode)node.jjtGetChild(i);
            if (childNode.toString() == "Identifier"){
                // get the method name
                className = childNode.jjtGetFirstToken().toString(); 
                break;
            }
        }
    	
    	classNodeStack.add(new ClassNode(className));
    	
        node.childrenAccept(this, data);
        
        ClassNode classNode = classNodeStack.pop();
        classNode.printClassNode();

        return data;  
    }
    

    public Object visit(ClassBodyDeclaration node, Object data){
    	
    	String classBodyString = "";
    	Token t = node.jjtGetFirstToken();
    	while(t != null && t.toString() != "{" && t.toString() != ";"){
    		classBodyString = classBodyString + " " + t.toString();
    		t = t.next;
    	}
    	System.out.print("\n");
    	
        node.childrenAccept(this, data);
 
        return data;  
    }
    

    public Object visit(FieldDeclaratorsRest node, Object data){
 
        node.childrenAccept(this, data);
 
        return data;  
    }
    
    public Object visit(MethodDeclaratorRest node, Object data){
    	 
    	/*
    	//get method visibility
    	String methodVisibility = "";
    	SimpleNode currentNode = node;
    	while(currentNode.toString() != "ClassBodyDeclaration"){
    		currentNode = (SimpleNode) currentNode.parent;
    	}
    	ClassBodyDeclaration classBodyDeclarationNode = (ClassBodyDeclaration) currentNode;
    	ModifierList modifierListNode = null;
    	for(int i=0; i<classBodyDeclarationNode.jjtGetNumChildren(); i++){
    		if (classBodyDeclarationNode.jjtGetChild(i).toString() == "ModifierList"){
    			modifierListNode = (ModifierList)classBodyDeclarationNode.jjtGetChild(i);
    		}
    	}
    	if (modifierListNode != null){
    		for(int i=0; i<modifierListNode.jjtGetNumChildren(); i++){
    			Modifier modifierNode = (Modifier) modifierListNode.jjtGetChild(i);
    			String memberVisibilityName = modifierNode.jjtGetFirstToken().toString();
    			
    			switch (memberVisibilityName) {
	                case "public":  	methodVisibility = "+"; break;
	                case "private":  	methodVisibility = "-"; break;
	                case "protected":  	methodVisibility = "#"; break;
	                case "package":  	methodVisibility = "~"; break;
	                default: 			methodVisibility = ""; break;
	            }
    		}
    	}
    	
    	//get method
    	String method = "";
    	MemberDecl memberDeclNode = null;
    	for(int i=0; i<classBodyDeclarationNode.jjtGetNumChildren(); i++){
    		if(classBodyDeclarationNode.jjtGetChild(i).toString() == "MemberDecl"){
    			memberDeclNode = (MemberDecl) classBodyDeclarationNode.jjtGetChild(i);
    			break;
    		}
    	}
    	
    	if (memberDeclNode != null){
    		Token t = memberDeclNode.jjtGetFirstToken();
        	while(t != null && t.toString() != "{" && t.toString() != ";"){
        		method = method + " " + t.toString();
        		t = t.next;
        	}
    	}
    	*/
    	
        node.childrenAccept(this, data);
        
        return data;  
    }
    
    public Object visit(MethodBody node, Object data){
    	/*
    	//get methodName and return type
    	String methodName = "";
    	String methodReturnType = "void";
    	SimpleNode currentNode = node;
        while(methodName == ""){
            currentNode = (SimpleNode)currentNode.jjtGetParent();
            for (int i = 0; i < currentNode.jjtGetNumChildren(); i++){
                SimpleNode childNode = (SimpleNode)currentNode.jjtGetChild(i);
                if (childNode.toString() == "Type"){
                	methodReturnType = "";
                	Token t = childNode.jjtGetFirstToken();
                	while(t != null){
                		methodReturnType = methodReturnType + t.toString();
                		t = t.next;
                	}
                }
                
                if (childNode.toString() == "Identifier"){
                    methodName = childNode.jjtGetFirstToken().toString(); 
                    break;
                }
            }
        }
        
        //get method visibility
    	String methodVisibility = "";
    	currentNode = node;
    	while(currentNode.toString() != "ClassBodyDeclaration"){
    		currentNode = (SimpleNode) currentNode.parent;
    	}
    	ClassBodyDeclaration classBodyDeclarationNode = (ClassBodyDeclaration) currentNode;
    	ModifierList modifierListNode = null;
    	for(int i=0; i<classBodyDeclarationNode.jjtGetNumChildren(); i++){
    		if (classBodyDeclarationNode.jjtGetChild(i).toString() == "ModifierList"){
    			modifierListNode = (ModifierList)classBodyDeclarationNode.jjtGetChild(i);
    		}
    	}
    	if (modifierListNode != null){
    		for(int i=0; i<modifierListNode.jjtGetNumChildren(); i++){
    			Modifier modifierNode = (Modifier) modifierListNode.jjtGetChild(i);
    			String memberVisibilityName = modifierNode.jjtGetFirstToken().toString();
    			
    			switch (memberVisibilityName) {
	                case "public":  	methodVisibility = "+"; break;
	                case "private":  	methodVisibility = "-"; break;
	                case "protected":  	methodVisibility = "#"; break;
	                case "package":  	methodVisibility = "~"; break;
	                default: 			methodVisibility = ""; break;
	            }
    		}
    	}    	
    	
    	//get method parameters
    	String parameters = "";
    	SimpleNode methodDeclaratorNode = (SimpleNode) node.parent;
    	FormalParameters formalParametersNode = null;
    	for (int i=0; i<methodDeclaratorNode.jjtGetNumChildren(); i++){
    		if(methodDeclaratorNode.jjtGetChild(i).toString() == "FormalParameters"){
    			formalParametersNode = (FormalParameters) methodDeclaratorNode.jjtGetChild(i);
    			break;
    		}
    	}
    	
    	if(formalParametersNode != null){
    		Token t = formalParametersNode.jjtGetFirstToken();
        	while(t != null){
        		parameters = parameters + " " + t.toString();
        		t = t.next;
        	}
    	}
    	*/
        node.childrenAccept(this, data);    
 
        return data;  
    }

/*=========================Don't need to change after===========================================================*/


    public Object visit(MemberDecl node, Object data){
    	
        node.childrenAccept(this, data);
 
        return data;  
    }

    public Object visit(IfStatement node, Object data){
    
        node.childrenAccept(this, data);
 
        return data;  
    }

    public Object visit(WhileStatement node, Object data){
    
        node.childrenAccept(this, data);
 
        return data;  
    }

    public Object visit(BreakStatement node, Object data){
        
        node.childrenAccept(this, data);
 
        return data;  
    }

    public Object visit(LocalVariableDeclarationStatement node, Object data){
 
        node.childrenAccept(this, data);
 
        return data;  
    }
    
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


    public Object visit(SimpleNode node, Object data){
 
        node.childrenAccept(this, data);
 
        return data;          
    }

    public Object visit(Identifier node, Object data){
 
        node.childrenAccept(this, data);
  
        return data;    
    }

    public Object visit(QualifiedIdentifier node, Object data){
 
        node.childrenAccept(this, data);
 
        return data;  
    }

    public Object visit(QualifiedIdentifierList node, Object data){
 
        node.childrenAccept(this, data);
 
        return data;  
    }

    public Object visit(ImportDeclaration node, Object data){
 
        node.childrenAccept(this, data);
 
        return data;  
    }

    public Object visit(Ellipsis node, Object data){
 
        node.childrenAccept(this, data);
 
        return data;  
    }

    public Object visit(HasEllipsis node, Object data){
 
        node.childrenAccept(this, data);
 
        return data;  
    }

    public Object visit(HasNotEllipsis node, Object data){
 
        node.childrenAccept(this, data);
 
        return data;  
    }

    public Object visit(PossibleStaticModifier node, Object data){
 
        node.childrenAccept(this, data);
 
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
 
        node.childrenAccept(this, data);
 
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
 
        node.childrenAccept(this, data);
 
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


    public Object visit(StaticInitBlock node, Object data){
 
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
 
        node.childrenAccept(this, data);
 
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