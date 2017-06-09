package View;

import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTree;
import javax.swing.SwingConstants;
import javax.swing.tree.DefaultMutableTreeNode;


import broker.MainBroker;
import model.News;
import model.Tag;
import model.User;
import model.Variable;
import renderer.JListRenderer;
import renderer.MyTreeMenuMutableTreeNode;
import util.InitVarsAndTags;
import util.ReadXML;

public class WindowConsistencyDetection extends WindowRoot {

	
	User testUser1,testUser2;
	int count=2;
	boolean isConsistencyTest = false;
	
	static int index= -1;
	List<News> newsList;
	boolean isViewContent = false;
	boolean isInTagAttachedState = false;
	
	// 标题
	private JLabel lblTitle;
	
	// 左部
	private JPanel panelLeft;
	private JLabel lblTrash;
	
	// 右部
	JPanel cardPanelNewsShow;
	JPanel panelNewsContent;
	JList  jlist;
	JButton btnPreNews;
	JButton btnNextNews;	
	boolean graphOpen ;
	DefaultListModel<News> jlistModel;
	DefaultMutableTreeNode top1 =  
	           new MyTreeMenuMutableTreeNode("已分类标签");  	
	
	boolean btnTagOpen ;
	private JButton button;
	private JLabel resultShowLabel;
				
	public  WindowConsistencyDetection() {
		super();
		getContentPane().setLayout(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 
			
//		mainBroker = new MainBroker(user);
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
		
		
		button = new JButton("导入文件");
		button.setBounds(131, 5, 93, 23);
		button.addActionListener(new OpenFileListener(this));
		panelLeft.add(button);
						
	}
	
	// 右边区域
    private void rightArea(){
    	cardPanelNewsShow = new JPanel();
		cardPanelNewsShow.setBackground(Color.LIGHT_GRAY);
		cardPanelNewsShow.setBounds(234, 68, 860, 603);
		getContentPane().add(cardPanelNewsShow);
		cardPanelNewsShow.setLayout(new CardLayout(0, 0));
				
		//新闻详细
		panelNewsContent = new JPanel();
		panelNewsContent.setBackground(Color.LIGHT_GRAY);
		cardPanelNewsShow.add(panelNewsContent, "cardNewsContent");
		panelNewsContent.setLayout(null);
		
		resultShowLabel = new JLabel("一致性为:");
		resultShowLabel.setBackground(Color.WHITE);
		resultShowLabel.setBounds(120, 465, 164, 35);
		resultShowLabel.setOpaque(true);//设置组件JLabel不透明，只有设置为不透明，设置背景色才有效  
		panelNewsContent.add(resultShowLabel);

		
    }
    
    private void rightTopArea(){
		JPanel panelRightTop = new JPanel();
		panelRightTop.setBounds(234, 34, 860, 34);
		getContentPane().add(panelRightTop);
		panelRightTop.setLayout(null);
	
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
			  	try {
					FileInputStream myVarsIn = new FileInputStream(fileName);
					if (myVarsIn != null){
						if(count==2){
							testUser2=(((User) WindowMain.readObjectFromFile(fileName)));
							count--;
						}else if(count==1){
							testUser1=(((User) WindowMain.readObjectFromFile(fileName)));
							analyse(testUser2,testUser1);
							resultShowLabel.setText("一致性为:"+analyse(testUser2,testUser1)*100+"%");
							count = 2;
						}
						
					}
						
				} catch (FileNotFoundException e) {
					System.out.println("error!");
				}
		    }
		}	
	}
 
  
   
    
    public static Object readObjectFromFile(String fileName) {
        Object temp=null;
        File file =new File(fileName);
        FileInputStream in;
        try {
            in = new FileInputStream(file);
            ObjectInputStream objIn=new ObjectInputStream(in);
            temp=objIn.readObject();
            objIn.close();
            System.out.println("read " + fileName + " success!");
        } catch (IOException e) {
            System.out.println("read " + fileName + " failed");
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return temp;
    }


   private double analyse(User user1,User user2){
	   int same = 0;
	   for(News news1:user1.getNewsList().getNewsList()){
		   for(News news2:user2.getNewsList().getNewsList()){
			   if(news1.getTitle().equals(news2.getTitle())){
				   same++;
				   break;
			   }
		   }
	   }
	   Set<News> User1AllNewsSet = new HashSet<>();
	   Set<News> User2AllNewsSet = new HashSet<>();
	   for(Variable var:user1.getVariableList().getVariableList()){
		   for(Tag tag:var.getTagList()){
				for(News news:tag.getNewsList()){
					 User1AllNewsSet.add(news);
				}
			}
	   }
	   for(Variable var:user2.getVariableList().getVariableList()){
		   for(Tag tag:var.getTagList()){
				for(News news:tag.getNewsList()){
					User2AllNewsSet.add(news);
				}
			}
	   }
	   
	   for(News news1: User1AllNewsSet){
		   for(News news2: User2AllNewsSet){
			   if(news1.NewsEquals(news2)){
				   same++;
				   break;
			   }
		   }
	   }

	  return same/10.0;
	   
   }

}
