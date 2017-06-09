package View;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import model.User;

public abstract class WindowRoot extends JFrame {
	// 标题
	private JLabel lblTitle;
	
	
	public WindowRoot() {
		
		this.setSize(1100,700);
		this.getContentPane().setLayout(null);
		setLocationRelativeTo(null);  // 设置居中显示
		setVisible(true);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); 
		setResizable(false); 
		
		lblTitle = new JLabel("  留守儿童舆情调查");
		lblTitle.setBounds(0, 0, 1094, 34);
		lblTitle.setFont(new Font("宋体", Font.BOLD, 14));
		lblTitle.setForeground(new Color(255, 255, 255));
		lblTitle.setBackground(new Color(204, 0, 0));
		lblTitle.setOpaque(true);		
		getContentPane().add(lblTitle);
		
	}	
	public abstract void init();

}
