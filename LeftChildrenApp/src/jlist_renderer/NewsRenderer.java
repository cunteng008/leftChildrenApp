package jlist_renderer;

import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.GridLayout;

import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.ListCellRenderer;

import model.News;

public class NewsRenderer extends JPanel implements ListCellRenderer<News>{

	
	private JLabel lblNewsTitle = new JLabel();
	  
	public NewsRenderer(){
		setLayout(new FlowLayout());  
		JPanel panelText = new JPanel(new GridLayout(0, 1));
        panelText.add(lblNewsTitle);
       
        add(panelText);
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
