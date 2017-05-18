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
import java.io.File;
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

import broker.MainBroker;
import model.News;
import model.NewsList;
import model.Tag;
import model.Variable;
import model.VariableList;
import renderer.JListRenderer;
import renderer.MyTreeMenuMutableTreeNode;
import renderer.TreeMenuRenderer;
import util.ReadXML;

import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.JTextArea;
import javax.swing.JScrollPane;
import javax.swing.JCheckBox;
import javax.swing.JSeparator;
import javax.swing.SpinnerListModel;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListModel;

import java.awt.GridLayout;
import javax.swing.ScrollPaneConstants;
import javax.swing.SwingConstants;
import javax.swing.ListModel;

public class WindowMain extends WindowRoot {
	
	MainBroker mainBroker ;
	static int index= -1;
	List<News> newsList;
	boolean isViewContent = false;
	boolean isInTagAttachedState = false;
	
	// 标题
	private JLabel lblTitle;
	
	// 左部
	private JPanel panelLeft;
	private JLabel btnShowNewsList;
	private JLabel lblTrash;
	private JTree tree;
	private JPanel panelMenu;
	private JButton unclassifiedNews;
	
	private JComboBox comboBox1;
	private JComboBox comboBox2;
	private JComboBox comboBox3;
	private JComboBox comboBox4;
	private JComboBox comboBox5;
	private JComboBox comboBox6;
	private JComboBox comboBox7;
	private JComboBox comboBox8;
	private JComboBox comboBox9;
	
		
	//右上
	private JLabel lblDeleteNews;
	
	// 右部
	JPanel cardPanelNewsShow;
	JPanel panelNewsList;
	JPanel panelNewsContent;
	JList  jlist;
	JButton btnPreNews;
	JButton btnNextNews;	
	JTextArea txtContent;
	JLabel lblContentTitle;
	private JLabel lblContentDate;
	private JScrollPane scrollPaneContent;
	boolean graphOpen ;
	DefaultListModel<News> jlistModel;
	DefaultMutableTreeNode top1 =  
	           new MyTreeMenuMutableTreeNode("已分类标签");  	
	
	boolean btnTagOpen ;
	private JButton button;
	private JLabel lblSetNewsTags;
	private JPanel cardSetTags;
	private JLabel lblNewLabel;
	private JButton btnRecycleBin;
	JButton btnDeleteNews;  // 在心事新闻内容时的删除键
				
	public WindowMain() {
		getContentPane().setLayout(null);
		mainBroker = new MainBroker();
		init();			
	}

	@Override
	public void init() {
		// TODO Auto-generated method stub
		graphOpen = false;
		btnTagOpen = false;
		
		lblTitle = new JLabel("  留守儿童舆情调查");
		lblTitle.setBounds(0, 0, 1094, 34);
		lblTitle.setFont(new Font("宋体", Font.BOLD, 14));
		lblTitle.setForeground(new Color(255, 255, 255));
		lblTitle.setBackground(new Color(204, 0, 0));
		lblTitle.setOpaque(true);		
		getContentPane().add(lblTitle);
		
		//左边区域
		leftArea();
		// 右上
		rightTopArea();
		//右边区域
		rightArea();
	}
	
/*
 * 相关UI
 * */
	//左边区域
	private void leftArea(){
		panelLeft = new JPanel();
		panelLeft.setBackground(Color.DARK_GRAY);
		panelLeft.setBounds(0, 34, 234, 637);
		getContentPane().add(panelLeft);
		panelLeft.setLayout(null);
		
		btnShowNewsList = new JLabel("New label");
		btnShowNewsList.addMouseListener(new ShowNewsListListener());
		btnShowNewsList.setCursor(new Cursor(Cursor.HAND_CURSOR));
		btnShowNewsList.setIcon(new ImageIcon(WindowMain.class.getResource("/images/main_list.png")));
		btnShowNewsList.setBounds(10, 0, 32, 32);
		panelLeft.add(btnShowNewsList);
		
		
		button = new JButton("导入文件");
		button.setBounds(131, 5, 93, 23);
		button.addActionListener(new OpenFileListener(this));
		panelLeft.add(button);
		
		panelMenu = new JPanel();
		panelMenu.setBounds(10, 46, 214, 538);
		panelLeft.add(panelMenu);
		panelMenu.setLayout(new CardLayout(0, 0));
		
		JPanel cardTreeMenu = new JPanel();
		cardTreeMenu.addMouseListener(new TreeMenuListener());
		panelMenu.add(cardTreeMenu, "cardTreeMenu");
		cardTreeMenu.setLayout(null);
		
		initJTree();
		tree = new JTree(top1);
		tree.setBounds(0, 0, 214, 538);
		tree.addMouseListener(new TreeMenuListener());
		cardTreeMenu.add(tree);
		cardSetTags = new JPanel();
		cardSetTags.setBackground(Color.WHITE);
		panelMenu.add(cardSetTags, "cardSetTags");		
		cardSetTags.setLayout(null);
		
		unclassifiedNews = new JButton("未分类");
		unclassifiedNews.addActionListener(new UnclassifiedListener());
		unclassifiedNews.setBounds(52, 5, 76, 23);
		panelLeft.add(unclassifiedNews);
		
		btnRecycleBin = new JButton("回收站");
		btnRecycleBin.addActionListener(new btnRecycleBinListener());
		btnRecycleBin.setBounds(131, 604, 93, 23);
		panelLeft.add(btnRecycleBin);
		tagsMenu();
				
	}
	private void tagsMenu(){
		List<Variable> varList = VariableList.getInstance().getVariableList();
		lblNewLabel = new JLabel("报纸类型");
		lblNewLabel.setOpaque(true);
		lblNewLabel.setBounds(10, 10, 194, 23);
		cardSetTags.add(lblNewLabel);
		comboBox1 = new JComboBox();
		comboBox1.setName(varList.get(0).getName());
		comboBox1.setBounds(20, 35, 184, 21);
		comboBox1.addItem("");
		for(Tag tag:varList.get(0).getTagList()){
			comboBox1.addItem(tag.getName());
		}
		cardSetTags.add(comboBox1);
		
		JButton btnNewButton = new JButton("确认修改");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				finishAttachTags();
				if(index>=0 && index<newsList.size()){
					initTagSelected(newsList.get(index));
					
					showNewsContent(newsList.get(index));  //上一条是新闻显示刷新
					jlist.setSelectedIndex(index);
				}
			}
		});
		
		btnNewButton.setBounds(106, 505, 98, 23);
		cardSetTags.add(btnNewButton);
		
		JLabel label = new JLabel("新闻类型");
		label.setOpaque(true);
		label.setBounds(10, 60, 194, 23);
		cardSetTags.add(label);
		
		JLabel label_1 = new JLabel("报道主题");
		label_1.setOpaque(true);
		label_1.setBounds(10, 112, 194, 23);
		cardSetTags.add(label_1);
		
		JLabel label_2 = new JLabel("新闻报道消息来源");
		label_2.setOpaque(true);
		label_2.setBounds(10, 164, 194, 23);
		cardSetTags.add(label_2);
		
		JLabel label_3 = new JLabel("媒介形象呈现");
		label_3.setOpaque(true);
		label_3.setBounds(10, 216, 194, 23);
		cardSetTags.add(label_3);
		
		JLabel label_4 = new JLabel("帮助新闻的具体种类");
		label_4.setOpaque(true);
		label_4.setBounds(10, 264, 194, 23);
		cardSetTags.add(label_4);
		
		JLabel label_5 = new JLabel("帮助类新闻的主体");
		label_5.setOpaque(true);
		label_5.setBounds(10, 313, 194, 23);
		cardSetTags.add(label_5);
		
		JLabel label_6 = new JLabel("表彰奖励的新闻主体");
		label_6.setOpaque(true);
		label_6.setBounds(10, 365, 194, 23);
		cardSetTags.add(label_6);
		
		comboBox2 = new JComboBox();
		comboBox2.setName(varList.get(1).getName());
		comboBox2.setBounds(20, 85, 184, 21);
		comboBox2.addItem("");
		for(Tag tag:varList.get(1).getTagList()){
			comboBox2.addItem(tag.getName());
		}
		cardSetTags.add(comboBox2);
		
		comboBox3 = new JComboBox();
		comboBox3.setName(varList.get(3).getName());
		comboBox3.addItem("");
		for(Tag tag:varList.get(3).getTagList()){
			comboBox3.addItem(tag.getName());
		}
		comboBox3.setBounds(20, 140, 184, 21);
		cardSetTags.add(comboBox3);
		
		comboBox4 = new JComboBox();
		comboBox4.setName(varList.get(4).getName());
		comboBox4.addItem("");
		for(Tag tag:varList.get(4).getTagList()){
			comboBox4.addItem(tag.getName());
		}
		comboBox4.setBounds(20, 191, 184, 21);
		cardSetTags.add(comboBox4);
		
		comboBox5 = new JComboBox();
		comboBox5.setName(varList.get(5).getName());
		comboBox5.addItem("");
		for(Tag tag:varList.get(5).getTagList()){
			comboBox5.addItem(tag.getName());
		}
		comboBox5.setBounds(20, 241, 184, 21);
		cardSetTags.add(comboBox5);
		
		comboBox6 = new JComboBox();
		comboBox6.setName(varList.get(6).getName());
		comboBox6.addItem("");
		for(Tag tag:varList.get(6).getTagList()){
			comboBox6.addItem(tag.getName());
		}
		comboBox6.setBounds(20, 290, 184, 21);
		cardSetTags.add(comboBox6);
		
		comboBox7 = new JComboBox();
		comboBox7.setName(varList.get(7).getName());
		comboBox7.addItem("");
		for(Tag tag:varList.get(7).getTagList()){
			comboBox7.addItem(tag.getName());
		}
		comboBox7.setBounds(20, 339, 184, 21);
		cardSetTags.add(comboBox7);
		
		comboBox8 = new JComboBox();
		comboBox8.setName(varList.get(8).getName());
		comboBox8.addItem("");
		for(Tag tag:varList.get(8).getTagList()){
			comboBox8.addItem(tag.getName());
		}
		comboBox8.setBounds(20, 394, 184, 21);
		cardSetTags.add(comboBox8);
		
		JLabel label_7 = new JLabel("农民工子女不能留城读书原因");
		label_7.setOpaque(true);
		label_7.setBounds(10, 419, 194, 23);
		cardSetTags.add(label_7);
		
		comboBox9 = new JComboBox();
		comboBox9.setName(varList.get(9).getName());
		comboBox9.addItem("");
		for(Tag tag:varList.get(9).getTagList()){
			comboBox9.addItem(tag.getName());
		}
		comboBox9.setBounds(20, 446, 184, 21);
		cardSetTags.add(comboBox9);
		
		JButton btnNewButton_1 = new JButton("返回");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				isInTagAttachedState = false;
				switchLeftMenuCard("cardTreeMenu");
			}
		});
		btnNewButton_1.setBounds(10, 505, 86, 23);
		cardSetTags.add(btnNewButton_1);
	}
	
	// 右边区域
    private void rightArea(){
    	cardPanelNewsShow = new JPanel();
		cardPanelNewsShow.setBackground(Color.LIGHT_GRAY);
		cardPanelNewsShow.setBounds(234, 68, 860, 603);
		getContentPane().add(cardPanelNewsShow);
		cardPanelNewsShow.setLayout(new CardLayout(0, 0));
		
		//新闻列表
		panelNewsList = new JPanel();
		panelNewsList.setBackground(Color.LIGHT_GRAY);
		cardPanelNewsShow.add(panelNewsList, "cardNewsList");
		panelNewsList.setLayout(null);
		
		jlistModel = new DefaultListModel<>(); 
		jlist = new JList(jlistModel);	
		jlist.addMouseListener(new JistListener());
		jlist.setCellRenderer(new JListRenderer());
    	JScrollPane scrollPane = new JScrollPane(jlist);
		scrollPane.setBounds(51, 48, 747, 507);
		panelNewsList.add(scrollPane);		
		
		//新闻详细
		panelNewsContent = new JPanel();
		panelNewsContent.setBackground(Color.LIGHT_GRAY);
		cardPanelNewsShow.add(panelNewsContent, "cardNewsContent");
		panelNewsContent.setLayout(null);
	
		btnPreNews = new JButton("上一条");
		btnPreNews.addActionListener(new BtnPreNewsListener());
		btnPreNews.setBounds(39, 555, 93, 29);
		btnPreNews.setCursor(new Cursor(Cursor.HAND_CURSOR));
		panelNewsContent.add(btnPreNews);
		
		btnNextNews = new JButton("下一条");
		btnNextNews.addActionListener(new BtnNextNewsListener());
		btnNextNews.setCursor(new Cursor(Cursor.HAND_CURSOR));
		btnNextNews.setBounds(726, 555, 93, 29);
		panelNewsContent.add(btnNextNews);
		
		txtContent = new JTextArea();
		txtContent.setLineWrap(true); //激活自动断行功能
		txtContent.setWrapStyleWord(true);// 激活断行不断字功能
		scrollPaneContent = new JScrollPane(txtContent);
		scrollPaneContent.setBounds(39, 102, 780, 443);
		panelNewsContent.add(scrollPaneContent);
		
		lblContentTitle = new JLabel("New label");
		lblContentTitle.setFont(new Font("微软雅黑", Font.BOLD, 16));
		lblContentTitle.setHorizontalAlignment(SwingConstants.CENTER);
		lblContentTitle.setBounds(39, 10, 780, 63);
		lblContentTitle.setOpaque(true);  // 不透明
		lblContentTitle.setForeground(Color.black);
		panelNewsContent.add(lblContentTitle);
		
		lblContentDate = new JLabel("");
		lblContentDate.setBounds(39, 77, 234, 20);
		lblContentDate.setOpaque(true);
		panelNewsContent.add(lblContentDate);
		
		btnDeleteNews = new JButton("delete");
		btnDeleteNews.setForeground(Color.WHITE);
		btnDeleteNews.setOpaque(true);
		btnDeleteNews.setBackground(Color.RED);
		btnDeleteNews.addActionListener(new BtnDeleteNewsListener());
		btnDeleteNews.setBounds(726, 77, 93, 23);
		panelNewsContent.add(btnDeleteNews);
		
    }
    
    private void rightTopArea(){
		JPanel panelRightTop = new JPanel();
		panelRightTop.setBounds(234, 34, 860, 34);
		getContentPane().add(panelRightTop);
		panelRightTop.setLayout(null);
		
		lblDeleteNews = new JLabel("");
		lblDeleteNews.setBounds(781, 0, 22, 30);
		panelRightTop.add(lblDeleteNews);
		lblDeleteNews.addMouseListener(new DeleteNewsListener());
		lblDeleteNews.setIcon(new ImageIcon(WindowMain.class.getResource("/images/trash.png")));
		
		lblSetNewsTags = new JLabel("");
		lblSetNewsTags.addMouseListener(new SetNewsTagsListener());
		lblSetNewsTags.setIcon(new ImageIcon(WindowMain.class.getResource("/images/tag.png")));
		lblSetNewsTags.setBounds(740, 5, 31, 25);
		lblSetNewsTags.addMouseListener(new SetNewsTagsListener());
		panelRightTop.add(lblSetNewsTags);
		
		JLabel lblChart = new JLabel("");
		lblChart.setIcon(new ImageIcon(WindowMain.class.getResource("/images/graph.png")));
		lblChart.addMouseListener(new StaticalAnalysisListener());
		lblChart.setCursor(new Cursor(Cursor.HAND_CURSOR));
		lblChart.setBounds(813, 5, 37, 25);
		panelRightTop.add(lblChart);
	}
    

    /*
     * 统计分析监听
     * */
    private class StaticalAnalysisListener implements MouseListener {

		@Override
		public void mouseClicked(MouseEvent arg0) {
			 EventQueue.invokeLater(new Runnable() {
					public void run() {
						try {
							WindowRoot frame = new WindowStaticalAnalysis();
						} catch (Exception e) {
							e.printStackTrace();
						}
					}
				});

			
		}

		@Override
		public void mouseEntered(MouseEvent arg0) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mouseExited(MouseEvent arg0) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mousePressed(MouseEvent arg0) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mouseReleased(MouseEvent arg0) {
			// TODO Auto-generated method stub
			
		}
    	
    }
    
    /*
     * 显示新闻标题列表
     * */ 
   private class ShowNewsListListener implements MouseListener{		
		public void mouseClicked(MouseEvent e) {
			switchRightBodyCard("cardNewsList");
			
			updateJList();
		}
		public void mouseEntered(MouseEvent arg0) {}
		public void mouseExited(MouseEvent arg0) {}
		public void mousePressed(MouseEvent arg0) {}
		public void mouseReleased(MouseEvent arg0) {}	   
   }
   /*
    * 打开文件
    * */
   private class OpenFileListener implements ActionListener {
		JFrame frame;
		JFileChooser fileChooser = new JFileChooser(new File(System.getProperty("user.dir")));
		
		public OpenFileListener(JFrame frame){
			super();
			this.frame = frame;
		}
		@Override
		public void actionPerformed(ActionEvent arg0) {		
		    if(fileChooser.showOpenDialog(frame)==JFileChooser.APPROVE_OPTION ){
		      String fileName = fileChooser.getSelectedFile().getAbsolutePath();
		      mainBroker.openFile(fileName);
		      newsList = NewsList.getInstance().getNewsList();  
		      updateJList();
		    }
		}	
	}
   /*
    * 双击列表显示新闻内容和单击列表
    * */
   private class JistListener implements MouseListener{
		public void mouseClicked(MouseEvent e) {
			if(e.getClickCount()==1){
				if(isInTagAttachedState){
					initTagSelected(newsList.get(index));
				}
				index = jlist.getSelectedIndex();
			}else if(e.getClickCount()==2){
				index = jlist.getSelectedIndex();
				switchRightBodyCard("cardNewsContent");
				if(index<0 || index>=newsList.size()){
		    		return;
		    	}
				News news = newsList.get(index);
				showNewsContent(news);
			}
		}
		public void mouseEntered(MouseEvent arg0) {}
		public void mouseExited(MouseEvent arg0) {}
		public void mousePressed(MouseEvent arg0) {}
		public void mouseReleased(MouseEvent arg0) {}  
   }
   private class UnclassifiedListener implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			newsList = NewsList.getInstance().getNewsList();
			lblDeleteNews.setVisible(true);
			btnDeleteNews.setVisible(true);
			lblSetNewsTags.setVisible(true);
			tree.clearSelection();  //取消树的选中状态
			updateJList();
		}	   
   }
   private class btnRecycleBinListener implements ActionListener{
	   public void actionPerformed(ActionEvent e) {
		   EventQueue.invokeLater(new Runnable() {
				public void run() {
					try {
						WindowRoot frame = new WindowRecycleBin();
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			});
	   }
	   
   }   
   /*
    * 切换上一条和下一条新闻
    * */
   private class BtnPreNewsListener  implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent arg0) {
			showNewsContent(getPreNews());
		}
   	
   }
   private class BtnNextNewsListener  implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent arg0) {
			showNewsContent(getNextNews());
		}   	
   }	
   /*
    * 删除新闻
    * */
   private class DeleteNewsListener implements MouseListener{
		public void mouseClicked(MouseEvent e) {
			deleteNews();
			updateJList();
		}
		public void mouseEntered(MouseEvent arg0) {}
		public void mouseExited(MouseEvent arg0) {}
		public void mousePressed(MouseEvent arg0) {}
		public void mouseReleased(MouseEvent arg0) {}  
   }
   private class BtnDeleteNewsListener implements ActionListener{
		public void actionPerformed(ActionEvent arg0) {
			deleteNews();
			if(newsList.size()==0){
				//删除新闻后若没有了新闻，则返回新闻列表页面
				switchRightBodyCard("cardNewsList");
			}
			if(index>=0 && index < newsList.size()){
				//有新闻则信息下一条新闻
				showNewsContent(newsList.get(index));
			}			
		}	   
   }
   /*
    * 给新闻添加标签
    * */
   private class SetNewsTagsListener implements MouseListener{
		public void mouseClicked(MouseEvent e) {
			if(index>=0 && index<newsList.size()){
				isInTagAttachedState = true;
				System.out.println("即将给"+index+"贴标签"+  newsList.get(index).getVaribleList().size());
				System.out.println(newsList.get(index).getTitle());
				initTagSelected(newsList.get(index));
				jlist.setSelectedIndex(index);
				switchLeftMenuCard("cardSetTags");
			}			
		}
		public void mouseEntered(MouseEvent arg0) {}
		public void mouseExited(MouseEvent arg0) {}
		public void mousePressed(MouseEvent arg0) {}
		public void mouseReleased(MouseEvent arg0) {}  
   }
   /*
    * 树形
    * */
   private class TreeMenuListener implements MouseListener{
	public void mouseClicked(MouseEvent e) {
		 lblDeleteNews.setVisible(false);
		 btnDeleteNews.setVisible(false);
		 switchRightBodyCard("cardNewsList");
		 switchRightBodyCard("cardList");
		 int selRow = tree.getRowForLocation(e.getX(), e.getY());
		 MyTreeMenuMutableTreeNode node =(MyTreeMenuMutableTreeNode) 
				 tree.getLastSelectedPathComponent();
		 Object userObject = node.getUserObject();
		 System.out.println(userObject);
		 
//		 if (userObject instanceof Variable) {
//			 Variable var = (Variable) userObject;
//			 if(var.getNewsList()==null){
//				 return;
//			 }
//			 newsList = var.getNewsList();
//			 updateJList();
//		 }else if(userObject instanceof Tag){
//			 Tag  tag = (Tag) userObject;
//			 newsList = tag.getNewsList();
//			 updateJList();			
//		 }
	}
	public void mouseEntered(MouseEvent arg0) {	}
	public void mouseExited(MouseEvent arg0) {}
	public void mousePressed(MouseEvent arg0) {}
	public void mouseReleased(MouseEvent arg0) {}
	   
   }
   
   
   
   
   /*刷新JList显示
    * */
   private void updateJList(){
	   if(newsList!=null){
		   jlist.setListData(newsList.toArray());
		   index=0;
	   }  
   }
   /*
    * 得到上一条和下一条新闻
    * */
    private News getNextNews(){
		News news =null;
		System.out.println(index);
    	if(++index<newsList.size()){
			news = newsList.get(index);
		}else{
			index--;
		}
    	return news;
	}
    private News getPreNews(){
    	News news = null;
    	if(--index>0){
			news = newsList.get(index);
			
		}else{
			index++;
		}
    	return news;
    }
   /*
    * 显示新闻内容
    * */
    private void showNewsContent(News news){
    	if(news==null){
    		return;
    	}
		lblContentTitle.setText("<html>"+news.getTitle()+"</html>");
		lblContentDate.setText(news.getCalendar().getTime()+"");					
		txtContent.setText(news.getContent());
    }
    /*
     * 删除新闻
     * */
    private void deleteNews(){
    	if(index>=0 && index < newsList.size()){  
    		Tag trachTag = VariableList.getInstance().getVariableList().get(10).getTagList()
					.get(0); 
    		trachTag.addNews(newsList.get(index));
    		newsList.remove(index);
    	}    	
    }
    /*
     * 初始化树菜单节点
     * */
    private void initJTree(){
    	for(Variable var:VariableList.getInstance().getVariableList()){
    		if(var.getName().equals("报道数量") || var.getName().equals("回收站")){
				continue;
			}
    		DefaultMutableTreeNode node = new MyTreeMenuMutableTreeNode(var);
    		
    		for(Tag tag:var.getTagList()){
    			
    			node.add(new MyTreeMenuMutableTreeNode(tag));
    		}
    		top1.add(node);
    	}
    	
    }
    /*
     * 完成贴标签
     * */
    private void finishAttachTags(){
       List<String> tagNames = new ArrayList<String>();
       String tagName = comboBox1.getSelectedItem().toString();
       if(tagName!=""){
    	   tagNames.add(tagName);
       }      
       tagName = comboBox2.getSelectedItem().toString();
       if(tagName!=""){
    	   tagNames.add(tagName);
       }  
       tagName = comboBox3.getSelectedItem().toString();
       if(tagName!=""){
    	   tagNames.add(tagName);
       }  
       tagName = comboBox4.getSelectedItem().toString();
       if(tagName!=""){
    	   tagNames.add(tagName);
       }  
       tagName = comboBox5.getSelectedItem().toString();
       if(tagName!=""){
    	   tagNames.add(tagName);
       }  
       tagName = comboBox6.getSelectedItem().toString();
       if(tagName!=""){
    	   tagNames.add(tagName);
       }  
       tagName = comboBox7.getSelectedItem().toString();
       if(tagName!=""){
    	   tagNames.add(tagName);
       }  
       tagName = comboBox8.getSelectedItem().toString();
       if(tagName!=""){
    	   tagNames.add(tagName);
       }  
       tagName = comboBox9.getSelectedItem().toString();
       if(tagName!=""){
    	   tagNames.add(tagName);
       }  
    
       
       mainBroker.setTags(newsList.get(index), tagNames);
       updateJList();
//       for(News n:newsList){
//    	   System.out.println(n.getTitle());
//       }   //测试有没有删除
       System.out.println(tagName);
    }
    /*
     * 将新闻的标签，初始化新闻标签状态
     * */
    private void initTagSelected(News news){
    	
    	clearComBoxSelected(comboBox1);
    	clearComBoxSelected(comboBox2);
    	clearComBoxSelected(comboBox3);
    	clearComBoxSelected(comboBox4);
    	clearComBoxSelected(comboBox5);
    	clearComBoxSelected(comboBox6);
    	clearComBoxSelected(comboBox7);
    	clearComBoxSelected(comboBox8);
    	clearComBoxSelected(comboBox9);
    	
    	for(Variable var:news.getVaribleList()){
    		for(Tag tag:var.getTagList()){
    			System.out.println("有就标签要初始化");
    			if(var.getName().equals(comboBox1.getName())&&tag.hasNews(news)){
    				comboBox1.setSelectedItem(tag.getName());
    				break;
    			}else if(var.getName().equals(comboBox2.getName())&&tag.hasNews(news)){
    				comboBox2.setSelectedItem(tag.getName());
    				break;
    			}else if(var.getName().equals(comboBox3.getName())&&tag.hasNews(news)){
    				comboBox3.setSelectedItem(tag.getName());
    				break;
    			}else if(var.getName().equals(comboBox4.getName())&&tag.hasNews(news)){
    				comboBox4.setSelectedItem(tag.getName());
    				break;
    			}else if(var.getName().equals(comboBox5.getName())&&tag.hasNews(news)){
    				comboBox5.setSelectedItem(tag.getName());
    				break;
    			}else if(var.getName().equals(comboBox6.getName())&&tag.hasNews(news)){
    				comboBox6.setSelectedItem(tag.getName());
    				break;
    			}else if(var.getName().equals(comboBox7.getName())&&tag.hasNews(news)){
    				comboBox7.setSelectedItem(tag.getName());
    				break;
    			}else if(var.getName().equals(comboBox8.getName())&&tag.hasNews(news)){
    				comboBox8.setSelectedItem(tag.getName());
    				break;
    			}else if(var.getName().equals(comboBox9.getName())&&tag.hasNews(news)){
    				comboBox9.setSelectedItem(tag.getName());
    				break;
    			}
    		}
    	}
    
    }
    private void clearComBoxSelected(JComboBox jcomboBox){
    	jcomboBox.setSelectedItem("");
    }
    
	/*
	 * 新闻显示页面卡的切换
	 * */
	private void switchRightBodyCard(String card){
		CardLayout c1 = (CardLayout) cardPanelNewsShow.getLayout();
		if(card.equals("cardNewsList")){
			c1.show(cardPanelNewsShow, "cardNewsList");
			lblDeleteNews.setVisible(true);
			isViewContent = false;
		}else if(card.equals("cardNewsContent")){
			c1.show(cardPanelNewsShow, "cardNewsContent");
			lblSetNewsTags.setVisible(true);
			isViewContent = true;
			lblDeleteNews.setVisible(false);
		}
	}	
	private void switchLeftMenuCard(String card){
		CardLayout c1 = (CardLayout) panelMenu.getLayout();
		if(card.equals("cardTreeMenu")){
			c1.show(panelMenu,"cardTreeMenu");
			
		}else if(card.equals("cardSetTags")){
			c1.show(panelMenu,"cardSetTags");
		}
	}	
}