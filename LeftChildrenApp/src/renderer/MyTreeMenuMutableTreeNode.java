package renderer;

import javax.swing.tree.DefaultMutableTreeNode;

import model.Tag;
import model.Variable;

public class MyTreeMenuMutableTreeNode extends DefaultMutableTreeNode {
	String name;
	public MyTreeMenuMutableTreeNode(Object userObject){
		super(userObject);
		 if (userObject instanceof Variable) {
			 Variable  var = (Variable) userObject;
			 name = var.getName();
		 }else if(userObject instanceof Tag){
			 Tag  tag = (Tag) userObject;
			 name = tag.getName();
		 }else if(userObject instanceof String){
			 name = (String)  userObject;
		 }
	}
	@Override  
	public String toString() {  
	    return name;  
	}  
	
	
}
