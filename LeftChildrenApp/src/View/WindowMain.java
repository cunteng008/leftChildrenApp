package View;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.ButtonGroup;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import javax.swing.UIManager;
import javax.swing.JRadioButton;
import javax.swing.JRadioButtonMenuItem;
import javax.swing.JButton;
import javax.swing.ImageIcon;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.JList;
import javax.swing.AbstractListModel;
import javax.swing.BorderFactory;

import java.awt.CardLayout;
import javax.swing.border.LineBorder;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.List;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JMenuBar;
import javax.swing.JPopupMenu;
import java.awt.Component;
import javax.swing.JSpinner;
import javax.swing.JTree;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreeNode;
import javax.swing.tree.TreePath;

import model.NewsTitle_ID;

import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.JTextArea;
import javax.swing.JScrollPane;
import javax.swing.JCheckBox;
import javax.swing.JSeparator;
import javax.swing.SpinnerListModel;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListModel;

import java.awt.GridLayout;

public class WindowMain extends WindowRoot {
   
	// 新闻显示列表
	List<NewsTitle_ID> newsTitles = new ArrayList<NewsTitle_ID>();
	
	// 标题
	JLabel lblTitle;
	
	// 左部
	JPanel panelLeft;
	JPanel panelSelect;
	JPanel panelTagSelect;
	JTree treeMenu;
	JLabel btnlNewsList;
	JLabel lblTrash;
	JCheckBox chckbx_0;
	JComboBox comboBox_0;
	JButton btnSelectTagOK;
	JButton btnSelectTagCancel;
	
	// 右部
	JPanel panelRightBodyCard;
	JPanel panelNewsList;
	JPanel panelGraph;
	JPanel panelNewsContent;
	JPanel panelTrash;
	JList listNews;
	JButton btnPreNews;
	JButton btnNextNews;	
	private JScrollPane scrollPane_1;
	boolean graphOpen ;
	
	// 右上
	JLabel lblGraph;
	JLabel btnTag;
	boolean btnTagOpen ;
	
	public WindowMain() {
		getContentPane().setLayout(null);
		init();						
	}

	@Override
	public void init() {
		// TODO Auto-generated method stub
		graphOpen = false;
		btnTagOpen = false;
		
		lblTitle = new JLabel("  留守儿童舆情调查");
		lblTitle.setBounds(0, 0, 984, 34);
		lblTitle.setFont(new Font("宋体", Font.BOLD, 14));
		lblTitle.setForeground(new Color(255, 255, 255));
		lblTitle.setBackground(new Color(204, 0, 0));
		lblTitle.setOpaque(true);		
		getContentPane().add(lblTitle);
		
		//左边区域
		leftArea();
		
		rightTopArea();
		
		//右边区域
		rightArea();
	
	
	}
	
	
	//左边区域
	private void leftArea(){
		panelLeft = new JPanel();
		panelLeft.setBackground(Color.DARK_GRAY);
		panelLeft.setBounds(0, 34, 194, 627);
		getContentPane().add(panelLeft);
		panelLeft.setLayout(null);
		
		btnlNewsList = new JLabel("New label");
		btnlNewsList.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				lblGraph.setVisible(false);
				switchRightBodyCard("panelCardNewsList");
				leftSwitchCard("treeMenu");
			}
		});
		btnlNewsList.setCursor(new Cursor(Cursor.HAND_CURSOR));
		btnlNewsList.setIcon(new ImageIcon("D:\\cmj\\MyProject\\eclipse\\leftChildrenApplication\\"
				+ "LeftChildrenApp\\res\\images\\main_list.png"));
		btnlNewsList.setBounds(10, 0, 32, 32);
		panelLeft.add(btnlNewsList);
				
		panelSelect = new JPanel();
		panelSelect.setBackground(Color.DARK_GRAY);
		panelSelect.setBounds(10, 33, 174, 530);
		panelLeft.add(panelSelect);
		panelSelect.setLayout(new CardLayout(0, 0));
		
		// 分类目录
		tagTreeMemu();		
	    // 签选择
		tagSelectArea();
		
		lblTrash = new JLabel("New label");
		lblTrash.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				switchRightBodyCard("panelTrash");
			}
		});
		lblTrash.setCursor(new Cursor(Cursor.HAND_CURSOR));
		lblTrash.setIcon(new ImageIcon("D:\\cmj\\MyProject\\eclipse\\leftChildrenApplication\\"
				+ "LeftChildrenApp\\res\\images\\trash.png"));
		lblTrash.setBounds(171, 596, 23, 21);
		panelLeft.add(lblTrash);
	}
	private void tagTreeMemu(){
		treeMenu = new JTree();
		panelSelect.add(treeMenu, "treeMenu");
		
		treeMenu.setModel(new DefaultTreeModel(
			new DefaultMutableTreeNode("标签") {
				{
//					DefaultMutableTreeNode node_1;
//					node_1 = new DefaultMutableTreeNode("报纸类型");
//						node_1.add(new DefaultMutableTreeNode("中央党报"));
//						node_1.add(new DefaultMutableTreeNode("省委机关报"));
//						node_1.add(new DefaultMutableTreeNode("都市类党报"));
//					add(node_1);
//					node_1 = new DefaultMutableTreeNode("媒介形象呈现");
//						node_1.add(new DefaultMutableTreeNode("积极健康"));
//						node_1.add(new DefaultMutableTreeNode("可怜悲惨"));
//						node_1.add(new DefaultMutableTreeNode("沐浴幸福"));
//						node_1.add(new DefaultMutableTreeNode("问题儿童"));
//						node_1.add(new DefaultMutableTreeNode("其他问题"));
//					add(node_1);
				}
			}
		));		
		 MouseListener ml = new MouseAdapter() {
		     public void mousePressed(MouseEvent e) {
		         int selRow = treeMenu.getRowForLocation(e.getX(), e.getY());
		         TreePath selPath = treeMenu.getPathForLocation(e.getX(), e.getY());
		         if(selRow != -1) {
		        	 lblGraph.setVisible(true);
		             if(e.getClickCount() == 1) {
		            	 if(selRow == 1){
		            		 switchRightBodyCard("panelCardNewsList");
		            	 }		            	 
		             }
		             else if(e.getClickCount() == 2) {
		                 
		             }
		         }
		     }
		 };
		treeMenu.addMouseListener(ml);
	}
	private void tagSelectArea(){
		panelTagSelect = new JPanel();
		panelSelect.add(panelTagSelect, "panelTagSelect");
		panelTagSelect.setLayout(null);
		
		chckbx_0 = new JCheckBox("报纸类型");
		chckbx_0.setBounds(24, 6, 89, 23);
		panelTagSelect.add(chckbx_0);
		comboBox_0 = new JComboBox();
		comboBox_0.setModel(new DefaultComboBoxModel(new String[] {"空", "标题1", "标题2", "标题3", 
				"标题4", "标题5"}));
		comboBox_0.setBounds(52, 35, 89, 21);
		panelTagSelect.add(comboBox_0);
		
		btnSelectTagOK = new JButton("确定");
		btnSelectTagOK.addActionListener(new BtnSelectTagOKListener());
		btnSelectTagOK.setBounds(101, 507, 73, 23);
		panelTagSelect.add(btnSelectTagOK);
		btnSelectTagOK.addActionListener(new BtnSelectTagOKListener());
		
		btnSelectTagCancel = new JButton("取消");
		btnSelectTagCancel.setBounds(0, 507, 73, 23);
		btnSelectTagCancel.addActionListener(new BtnSelectTagCancelListener());
		panelTagSelect.add(btnSelectTagCancel);
		
	}
	
	 private class BtnSelectTagOKListener implements ActionListener{  
		 @Override
		public void actionPerformed(ActionEvent e) {
			leftSwitchCard("treeMenu");
		}   
	 }  
	 private class BtnSelectTagCancelListener implements ActionListener{  
		 @Override
		public void actionPerformed(ActionEvent e) {
			leftSwitchCard("treeMenu");
		}   
	 }  
	
	
	 	 
	// 右边区域
    private void rightArea(){
    	panelRightBodyCard = new JPanel();
		panelRightBodyCard.setBackground(Color.LIGHT_GRAY);
		panelRightBodyCard.setBounds(194, 68, 790, 603);
		getContentPane().add(panelRightBodyCard);
		panelRightBodyCard.setLayout(new CardLayout(0, 0));
		
		panelNewsList = new JPanel();
		panelNewsList.setBackground(Color.LIGHT_GRAY);
		panelRightBodyCard.add(panelNewsList, "panelCardNewsList");
		panelNewsList.setLayout(null);
		
		panelNewsContent = new JPanel();
		panelNewsContent.setBackground(Color.LIGHT_GRAY);
		panelRightBodyCard.add(panelNewsContent, "panelCardNewsContent");
		panelNewsContent.setLayout(null);
		
		newsTitlesList();
		
		btnPreNews = new JButton("上一条");
		btnPreNews.setBounds(39, 545, 93, 29);
		btnPreNews.setCursor(new Cursor(Cursor.HAND_CURSOR));
		panelNewsContent.add(btnPreNews);
		
		btnNextNews = new JButton("下一条");
		btnNextNews.setCursor(new Cursor(Cursor.HAND_CURSOR));
		btnNextNews.setBounds(687, 545, 93, 29);
		panelNewsContent.add(btnNextNews);
		
		scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(39, 46, 725, 489);
		panelNewsContent.add(scrollPane_1);
		
		JTextArea txtreclipsepackagesource = new JTextArea();
		txtreclipsepackagesource.setText(""
				+ " ");
		scrollPane_1.setViewportView(txtreclipsepackagesource);
		
		panelGraph = new JPanel();
		panelGraph.setBackground(Color.LIGHT_GRAY);
		panelRightBodyCard.add(panelGraph, "panelCardChart");
		panelGraph.setLayout(null);
		
		JLabel label = new JLabel("统计图");
		label.setFont(new Font("宋体", Font.PLAIN, 18));
		label.setBounds(382, 116, 121, 43);
		
		panelGraph.add(label);
		
		
		panelTrash = new JPanel();
		panelTrash.setBackground(Color.LIGHT_GRAY);
		panelRightBodyCard.add(panelTrash, "panelTrash");
		panelTrash.setLayout(null);
		
    }
    private class BtnTagListener implements ActionListener{  
		 @Override
		public void actionPerformed(ActionEvent e) {
			leftSwitchCard("treeMenu");
		}   
	 }  
    private void newsTitlesList(){
    	JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(51, 48, 692, 507);
		panelNewsList.add(scrollPane);
		
		listNews = new JList();
		newsTitles.add(new NewsTitle_ID("青少年“身边最让我感动的人”评选揭晓",1));
		newsTitles.add(new NewsTitle_ID("青少年“身边最让我感动的人”评选揭晓",2));
		newsTitles.add(new NewsTitle_ID("青少年“身边最让我感动的人”评选揭晓",2));
		newsTitles.add(new NewsTitle_ID("青少年“身边最让我感动的人”评选揭晓",2));
		newsTitles.add(new NewsTitle_ID("青少年“身边最让我感动的人”评选揭晓",2));
		DefaultListModel defaultListModel = new DefaultListModel();
		for(NewsTitle_ID newsTitle:newsTitles){
			defaultListModel.addElement(newsTitle.getNewsTile());
		}
		 
		scrollPane.setColumnHeaderView(listNews);
		listNews.setModel(defaultListModel);
		listNews.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {				
				int index = listNews.getSelectedIndex();
				if(e.getClickCount()==2){
					switchRightBodyCard("panelCardNewsContent");
				}
			}
		});
		
    }
	
    
    
	private void rightTopArea(){
		JPanel panelRightTop = new JPanel();
		panelRightTop.setBounds(194, 34, 790, 34);
		getContentPane().add(panelRightTop);
		panelRightTop.setLayout(null);
		
		lblGraph = new JLabel("");
		lblGraph.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				switchRightBodyCard("panelCardChart");
			}
		});
		lblGraph.setVisible(false);
		lblGraph.setCursor(new Cursor(Cursor.HAND_CURSOR));
		lblGraph.setIcon(new ImageIcon("D:\\cmj\\MyProject\\eclipse\\leftChildrenApplication\\"
				+ "LeftChildrenApp\\res\\images\\graph.png"));
		lblGraph.setBounds(746, 0, 44, 34);
		panelRightTop.add(lblGraph);
		
		JLabel lblDelete = new JLabel("");
		lblDelete.setBounds(702, 0, 20, 28);
		panelRightTop.add(lblDelete);
		lblDelete.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				DefaultListModel defaultListModel = (DefaultListModel)listNews.getModel();
				if(listNews.getSelectedIndex()>=0){
					System.out.println(listNews.getSelectedIndex());
					newsTitles.remove(listNews.getSelectedIndex());  //实现列表了Jlist列表的同步删除
					defaultListModel.remove(listNews.getSelectedIndex());
				}			
			}
		});
		lblDelete.setIcon(new ImageIcon("D:\\cmj\\MyProject\\eclipse\\leftChildrenApplication\\LeftChildrenApp\\res\\images\\trash.png"));
		
		btnTag = new JLabel("");
		btnTag.setBounds(659, 0, 25, 29);
		panelRightTop.add(btnTag);
		btnTag.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if(!btnTagOpen){	
					btnTagOpen = true;
					leftSwitchCard("panelTagSelect");
				}else{
					btnTagOpen = false;
					leftSwitchCard("treeMenu");
				}
			}
		});
		btnTag.setIcon(new ImageIcon("D:\\cmj\\MyProject\\eclipse\\leftChildrenApplication\\LeftChildrenApp\\res\\images\\tag.png"));
	}
    
	
	// 切换页面
	void switchRightBodyCard(String card){
		CardLayout c1 = (CardLayout) panelRightBodyCard.getLayout();
		if(card.equals("panelCardChart")){
			c1.show(panelRightBodyCard, "panelCardChart");
		}else if(card.equals("panelCardNewsContent")){
			c1.show(panelRightBodyCard, "panelCardNewsContent");
		}else if(card.equals("panelCardNewsList")){
			c1.show(panelRightBodyCard, "panelCardNewsList");
		}else if(card.equals("panelTrash")){
			c1.show(panelRightBodyCard, "panelTrash");
		}
	}	
	void leftSwitchCard(String card){
		
		CardLayout c1 = (CardLayout) panelSelect.getLayout();
		if(card.equals("treeMenu")){
			c1.show(panelSelect, "treeMenu");
		}else if(card.equals("panelTagSelect")){
			c1.show(panelSelect, "panelTagSelect");
		}
	}
	
	// 切换新闻列表数据,大于等于0表示切换到标签列表，小于0表示切换到其他标签列表
	private void switchNewsList(int select){
		switch(select){
			case 0: 
					break;
			case 1: 
					break;
			case -1: 
					break;
			case -2:
					break;
		}
	}
}