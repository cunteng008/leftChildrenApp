package renderer;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.GridLayout;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTree;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.TreeCellRenderer;
import javax.swing.tree.TreeNode;

import com.sun.corba.se.impl.orbutil.graph.NodeData;

import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeCellRenderer;

import model.Tag;
import model.Variable;

public class TreeMenuRenderer extends JPanel implements TreeCellRenderer {
	JLabel lblName;
	 JPanel renderer;
	 Color backgroundSelectionColor;
	 Color backgroundNonSelectionColor;
	public TreeMenuRenderer(){
		renderer = new JPanel(new GridLayout(0, 1));
		renderer.add(lblName);       
		renderer.add(lblName,BorderLayout.CENTER);
		backgroundSelectionColor =  new Color(11,11,11);
		backgroundNonSelectionColor =  new Color(255,11,11);	        
			  
	}
	@Override
	public Component getTreeCellRendererComponent(JTree tree, Object value, boolean selected,
			boolean expanded, boolean leaf, int row, boolean hasFocus) {
		if( (value != null) && (value instanceof DefaultMutableTreeNode)){
			Object userObject = ((DefaultMutableTreeNode) value).getUserObject();
			 if (userObject instanceof Variable) {
				 Variable  var = (Variable) userObject;
				 lblName.setText(var.getName());
			 }else if(userObject instanceof Tag){
				 Tag  tag = (Tag) userObject;
				 lblName.setText(tag.getName());
			 }else if(userObject instanceof String){
				 lblName.setText((String)  userObject);
			 }
		}
		return this;
	}
	
}
