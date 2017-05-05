package View;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

public abstract class WindowRoot extends JFrame {
	
	public WindowRoot() {
		this.setSize(1000,700);
		this.getContentPane().setLayout(null);
		setLocationRelativeTo(null);  // 设置居中显示
		setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 
	}
	
	public abstract void init();

}
