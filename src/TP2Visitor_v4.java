import java.util.ArrayList;
import java.util.Stack;

/**
 * @author Yan Xu
 * 2015-01-16
 * This visitor give:
 * id, fileName, className, methodName, #if, #while, #break, #varLocal
 */


public class TP2Visitor_v4 implements JavaParser1_7Visitor {

    static int id; // count the line number of the output
    static ArrayList<ClassNode> classNodeList = new ArrayList<>();
    static ArrayList<Relation> relationList = new ArrayList<>();
    //static String relationListString;
    
    class Relation{
    	private String fromNode;
    	private String toNode;
    	private String relation;
    	
    	Relation(String fromNode, String toNode, String relation){
    		this.fromNode = fromNode;
    		this.toNode = toNode;
    		this.relation = relation;
    	}
    	
    	public String getRelationString(){
    		
    		String r = "";
    		
    		if (relation == "composition")
    			r = fromNode + "->" + toNode + " [arrowhead=diamond]";
    		if (relation == "generalization")
    			r = fromNode + "->" + toNode + " [arrowhead=empty]";
    		return r;
    	}
    	
    	public boolean equals(Relation r){
			return (this.fromNode.equals(r.fromNode) 
					&& this.toNode.equals(r.toNode) 
					&& this.relation.equals(r.relation));
    	};
    }
    
    class ClassNode{
    	private String className;
    	private ArrayList<String> attributes = new ArrayList<String>();
    	private ArrayList<String> methods = new ArrayList<String>();
    	
    	ClassNode(String className){
    		this.className = className;
    	}
    	
    	public void addAttribute(String attribute){
    		this.attributes.add(attribute);
    	}
    	
    	public void addMethod(String method){
    		this.methods.add(method);
    	}
    	
    	public void printClassNode(){
    		
    		StringBuffer attributesStringBuffer = new StringBuffer();
    		for (String s : attributes) {
    			attributesStringBuffer.append(s + "\\l");
    		}
    		String attributesString = attributesStringBuffer.toString();
    		
    		StringBuffer methodsStringBuffer = new StringBuffer();
    		for (String s : methods) {
    			methodsStringBuffer.append(s + "\\l");
    		}
    		String methodsString = methodsStringBuffer.toString().replace("\"", "\\\"");
    		
    		
    		String stringInGraphViz = 
    				this.className 
    				+ " [label=\" {" + this.className + "|" + 
							attributesString + "|" +
							methodsString +
					"}\"]";

    		stringInGraphViz = stringInGraphViz.replace("<", "&lt;");
    		stringInGraphViz = stringInGraphViz.replace(">", "&gt;");

    		System.out.println(stringInGraphViz);
    	}
    }
    
    public static void printNodes(){
    	
    	//ArrayList<String> classNodeName = new ArrayList<>();
    	for (ClassNode cn : TP2Visitor_v4.classNodeList){
    		cn.printClassNode();
    		//classNodeName.add(cn.className);
    	}
    }
    
    private void addRelation(Relation relation){
    	boolean duplicated = false;
    	
    	for (Relation r : TP2Visitor_v4.relationList){
    		if(r.equals(relation)){
    			duplicated = true;
    			break;
    		}
    	}
    	
    	if(!duplicated){
    		TP2Visitor_v4.relationList.add(relation);
    	}
    	
    }
    
    public static void printRelations(){
    	//this.removeDuplicatedRelations();
    	//ArrayList<String> formedRelation = new ArrayList<>();
    	//relationListString = "";
    	ArrayList<String> classNodesName = new ArrayList<>();
    	for (ClassNode cn : TP2Visitor_v4.classNodeList){
    		classNodesName.add(cn.className);
    	}
    	
    	
    	for (Relation r : TP2Visitor_v4.relationList){
    		//if (classNodesName.contains(r.fromNode) && classNodesName.contains(r.toNode)){
    		System.out.println(r.getRelationString());
    		//}
//    		if (classNodeName.contains(r.fromNode) && classNodeName.contains(r.toNode)){
//    			String formedRelationString = r.getRelation();
//    			if (!formedRelation.contains(formedRelationString)){
//    				formedRelation.add(formedRelationString);
//    				relationListString = relationListString + formedRelationString + "\n";
//    				//System.out.println(formedRelationString);
//    			}
//    		}
    	}
    	
    	//System.out.println("}");
    	
    }
    
    private String getFirstToken(Node node){
    	return ((SimpleNode)node).jjtGetFirstToken().toString();
    }
    
    
    private ArrayList<String> addModifierAndType(ArrayList<String> arrayListString, SimpleNode modifierListNode, String type){
		
    	ArrayList<String> modifierList = new ArrayList<>();
    	for(int i=0; i<modifierListNode.jjtGetNumChildren(); i++){
    		modifierList.add(getFirstToken(modifierListNode.jjtGetChild(i)));
    	}
    	
    	ArrayList<String> reformedArrayListString = new ArrayList<>();
    	    	
    	for (String s : arrayListString){
    		String attribute = s;
    		
    		if(modifierList.contains("static")){
    			attribute = "static " + attribute;
    		}
    		
    		if(modifierList.contains("public")){
    			attribute = "+ " + attribute;
    		} else if (modifierList.contains("private")){
    			attribute = "- " + attribute;
    		} else if (modifierList.contains("protected")){
    			attribute = "# " + attribute;
    		} else if (modifierList.contains("package")){
    			attribute = "~ " + attribute;
    		}
    		
    		if (type != ""){
    			attribute = attribute + " : " + type;
    		}
    		
    		reformedArrayListString.add(attribute);
    	}
    	
    	return reformedArrayListString;
    }
    
    private String addModifierAndType(String string, SimpleNode modifierListNode, String type){
    	
    	ArrayList<String> s = new ArrayList<String>();
    	s.add(string);
    	
    	return this.addModifierAndType(s, modifierListNode, type).get(0);
    }

    public Object visit(CompilationUnit node, Object data){
    	
    	Stack<ClassNode> classNodeStack = new Stack<ClassNode>();
    	
        node.childrenAccept(this, classNodeStack);
        
        //this.printNodes();

        return data;  
    }
   
    
/****************************************** Class ************************************************/

    public Object visit(NormalClassDeclaration node, Object data){
    	
    	Stack<ClassNode> classNodeStack = (Stack<ClassNode>) data;
    	
    	String className = "";
    	
    	for (ClassNode n : classNodeStack){
    		className = className + n.className + ".";
    	}
    	
    	for (int i = 0; i < node.jjtGetNumChildren(); i++){
            SimpleNode childNode = (SimpleNode)node.jjtGetChild(i);
            if (childNode.toString() == "Identifier"){
                // get the class name
                className = className + childNode.jjtGetFirstToken().toString(); 
                break;
            }
        }
    	
    	classNodeStack.push(new ClassNode(className));
    	
    	for (int i = 0; i < node.jjtGetNumChildren(); i++){
            SimpleNode childNode = (SimpleNode)node.jjtGetChild(i);
            if (childNode.toString() == "Type"){
                // get the its parent 
            	
            	String type = "";
            	Token t = childNode.jjtGetFirstToken();
            	while(t != null){
            		type = type + t.toString();
            		if (t == childNode.jjtGetLastToken()) break;
            		t = t.next;
            	}
            	
            	this.addRelation(new Relation(className, type, "generalization"));
                break;
            }
        }
    	
        node.childrenAccept(this, data);
        
        ClassNode classNode = classNodeStack.pop();
        TP2Visitor_v4.classNodeList.add(classNode);

        return data;  
    }

/****************************************** Field ************************************************/
    public Object visit(FieldDeclaratorsRest node, Object data){
   	 
    	//here, identify a field (attribute) or several field of class
    	Stack<ClassNode> classNodeStack = (Stack<ClassNode>) data;
    	    	
    	// get Fields' name : fieldsName
    	// get the type : type
    	ArrayList<String> fieldsName = new ArrayList<>();
    	
    	fieldsName.add(getFirstToken(node.jjtGetParent().jjtGetParent().jjtGetChild(1)));
    	String type = getFirstToken((SimpleNode) node.jjtGetParent().jjtGetParent().jjtGetChild(0));
    	
    	if (node.jjtGetParent().jjtGetParent().jjtGetChild(0).jjtGetChild(0).toString() != "BasicType"){ // if it is not a BasicType, add a relationship
    		
    		this.addRelation(new Relation(type, classNodeStack.peek().className, "composition"));
    	}
    	
    	for(int i=0; i<node.jjtGetNumChildren(); i++){
    		if(node.jjtGetChild(i).toString() == "VariableDeclarator"){
    			fieldsName.add(getFirstToken(node.jjtGetChild(i).jjtGetChild(0)));
    		}
    	}
    	
    	// get the modifier list : modifierList
    	MethodOrFieldRest mfrNode = (MethodOrFieldRest) node.parent;
    	MethodOrFieldDecl mfdNode = (MethodOrFieldDecl) mfrNode.parent;
    	MemberDecl mdNode = (MemberDecl) mfdNode.parent;
    	ClassBodyDeclaration mbdNode = (ClassBodyDeclaration) mdNode.parent;
    	ModifierList mlNode = (ModifierList)mbdNode.jjtGetChild(0);
    	
    	ArrayList<String> fieldsString = this.addModifierAndType(fieldsName, mlNode, type);
    	    	
    	for (String s : fieldsString){

    		classNodeStack.peek().addAttribute(s);
    	}
    		
        node.childrenAccept(this, data);
 
        return data;  
    }
    
/****************************************** VoidMethod ************************************************/
    public Object visit(VoidMethodDecl node, Object data){
    	
    	Stack<ClassNode> classNodeStack = (Stack<ClassNode>) data;
    	
    	String methodName = getFirstToken(node.jjtGetChild(0));
    	VoidMethodDeclaratorRest vmdrNode = (VoidMethodDeclaratorRest) node.jjtGetChild(1);
    	
    	String methodParams = "";
    	Token t = vmdrNode.jjtGetFirstToken();
    	while(t.toString() != "{" && t.toString() != ";"){
    		methodParams = methodParams + t.toString() + " ";
    		t = t.next;
    	}
    	
    	String methodType = "void";
    	SimpleNode modifierListNode = (SimpleNode) node.jjtGetParent().jjtGetParent().jjtGetChild(0);
    	
    	String voidMethodString = this.addModifierAndType(methodName + methodParams, modifierListNode, methodType);
    	
    	classNodeStack.peek().addMethod(voidMethodString);
    	
        node.childrenAccept(this, data);
    
        return data;  
    }

 /****************************************** Constructor, generic or not ************************************************/


    
    public Object visit(ConstructorDeclaratorRest node, Object data){
    	
    	Stack<ClassNode> classNodeStack = (Stack<ClassNode>) data;
    	
    	String methodName = getFirstToken(node.jjtGetParent().jjtGetChild(0));
    	String methodType = "";
    	SimpleNode modifierListNode = (SimpleNode) node.jjtGetParent().jjtGetParent().jjtGetParent().jjtGetChild(0);
    	
    	String methodParams = "";
    	Token t = node.jjtGetFirstToken();
    	while(t.toString() != "{" && t.toString() != ";"){
    		methodParams = methodParams + t.toString() + " ";
    		t = t.next;
    	}
    	
    	String constructorString = this.addModifierAndType(methodName + methodParams, modifierListNode, methodType);
    	
    	classNodeStack.peek().addMethod(constructorString);
    
        node.childrenAccept(this, data);
 
        return data;  
    }
    
 /************************************** Normal Method and generic method *******************************************/
    
    public Object visit(MethodDeclaratorRest node, Object data){
   	 
    	Stack<ClassNode> classNodeStack = (Stack<ClassNode>) data;
    	String methodName = "";
    	String methodType = "void";
    	
    	SimpleNode modifierListNode = (SimpleNode) node.jjtGetParent().jjtGetParent().jjtGetParent().jjtGetParent().jjtGetChild(0);
    	
    	if (node.parent instanceof MethodOrFieldRest){ // so it is a normal method
    		methodName = getFirstToken(node.jjtGetParent().jjtGetParent().jjtGetChild(1));
    		methodType = getFirstToken(node.jjtGetParent().jjtGetParent().jjtGetChild(0));
    	}
    	
    	
    	if (node.parent instanceof GenericMethodOrConstructorRest){ //so it is a generic method
    		GenericMethodOrConstructorRest gmocrNode = (GenericMethodOrConstructorRest) node.parent;
    		for(int i=0; i<gmocrNode.jjtGetNumChildren(); i++){
    			if(gmocrNode.jjtGetChild(i) instanceof Identifier){
    				methodName = getFirstToken(gmocrNode.jjtGetChild(i));
    			}
    			if (gmocrNode.jjtGetChild(i) instanceof Type){
    				methodType = getFirstToken(gmocrNode.jjtGetChild(i));
    			}	
    		}
    	}
    	
    	String methodParams = "";
    	Token t = node.jjtGetFirstToken();
    	while(t.toString() != "{" && t.toString() != ";"){
    		methodParams = methodParams + t.toString() + " ";
    		t = t.next;
    	}
    	
    	String constructorString = this.addModifierAndType(methodName + methodParams, modifierListNode, methodType);
    	
    	classNodeStack.peek().addMethod(constructorString);
    	
        node.childrenAccept(this, data);
        
        return data;  
    }
/*=========================Don't need to change after===========================================================*/
    
    public Object visit(ConstructorDecl node, Object data){
        
        node.childrenAccept(this, data);
        
        return data;  
    }
    
    public Object visit(ClassBodyDeclaration node, Object data){
    	
        node.childrenAccept(this, data);
 
        return data;  
    }
    
    public Object visit(MethodOrFieldDecl node, Object data){
    	
        node.childrenAccept(this, data);
 
        return data;  
    } 
    
    
    public Object visit(MethodBody node, Object data){
    	
        node.childrenAccept(this, data);    
 
        return data;  
    }

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