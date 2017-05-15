package renderer;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Cursor;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.EventObject;

import javax.swing.CellEditor;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.ListCellRenderer;
import javax.swing.event.CellEditorListener;

import model.News;

public class JListRenderer extends JPanel implements ListCellRenderer<News>{
	
	private JLabel lblNewsTitle = new JLabel();
	  
	public JListRenderer(){
		setLayout(new BorderLayout());  
		JPanel panelText = new JPanel(new GridLayout(0, 1));
        panelText.add(lblNewsTitle);       
        add(panelText,BorderLayout.CENTER);
        this.setFocusable(true);
	}
	@Override
	public Component getListCellRendererComponent(JList<? extends News> list, News news,
			int index, boolean isSelected, boolean cellHasFocus) {
		lblNewsTitle.setText(news.getTitle());
		
		 // set Opaque to change background color of JLabel
	    lblNewsTitle.setOpaque(true);
	    // when select item
	    if (isSelected) {
	        lblNewsTitle.setBackground(list.getSelectionBackground());
	        setBackground(list.getSelectionBackground());
	    } else { // when don't select
	        lblNewsTitle.setBackground(list.getBackground());
	        setBackground(list.getBackground());
	    }
		
		return this;
	}
	
}
