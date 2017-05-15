package View;

import java.util.List;

import javax.swing.JList;

import model.News;
import model.NewsList;
import model.VariableList;
import renderer.JListRenderer;

import javax.swing.JPanel;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JButton;
import java.awt.CardLayout;

import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.ListModel;
import javax.swing.SwingConstants;

import broker.MainBroker;

import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import java.awt.Component;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class WindowRecycleBin extends WindowRoot{
	
	List<News> newsList;
	int index=-1;
	MainBroker mainBroker;
	
	//右部
	JPanel cardPanelNewsShow;
	JPanel panelNewsList;
	JPanel panelNewsContent;
	JList  jlist;
	DefaultListModel<News> jlistModel;
	JButton btnPreNews;
	JButton btnNextNews;	
	JTextArea txtContent;
	JLabel lblContentTitle;
	private JLabel lblContentDate;
	private JScrollPane scrollPaneContent;
	
	
	public WindowRecycleBin(){
		
		newsList = VariableList.getInstance().getVariableList().
						get(10).getTagList().get(0).getNewsList();
		mainBroker = new MainBroker();
		
		init();
	}
	@Override
	public void init() {
		JPanel panel = new JPanel();
		panel.setLayout(null);
		panel.setBackground(Color.DARK_GRAY);
		panel.setBounds(0, 34, 234, 637);
		getContentPane().add(panel);
		
		JLabel lblNewsList = new JLabel("New label");
		lblNewsList.setCursor(new Cursor(Cursor.HAND_CURSOR));
		lblNewsList.addMouseListener(new ShowNewsListListener());
		lblNewsList.setIcon(new ImageIcon(WindowRecycleBin.class.getResource("/images/main_list.png")));
		lblNewsList.setBounds(10, 0, 32, 32);
		panel.add(lblNewsList);
		
		JButton btnBack = new JButton("返回");
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
			}
		});
		btnBack.setBounds(131, 604, 93, 23);
		panel.add(btnBack);
		
		JButton btnNewsBack = new JButton("还原");
		btnNewsBack.addActionListener(new NewsBackListener());
		btnNewsBack.setBounds(97, 162, 93, 23);
		panel.add(btnNewsBack);
		newsList = VariableList.getInstance().getVariableList().
						get(10).getTagList().get(0).getNewsList();
		
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
		updateJList();
		
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
	}
	/*
	 * 
	 * */
	private class NewsBackListener implements ActionListener{
		public void actionPerformed(ActionEvent arg0) {
				
			    if(index<0 && index>=newsList.size()){
			    	return;
			    }
			    
				index = jlist.getSelectedIndex();	
				News news = newsList.get(index);
				newsList.remove(index);
				updateJList();
				
				NewsList.getInstance().addNews(news);
				if(newsList.size()==0){
					//删除新闻后若没有了新闻，则返回新闻列表页面
					switchRightBodyCard("cardNewsList");
				}
				if(index>=0 && index < newsList.size()){
					//有新闻则信息下一条新闻
					showNewsContent(newsList.get(index));
				}	
				jlist.setSelectedIndex(index);
				 if(index<0 && index>=newsList.size()){
				    	return;
				 }
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
	
	  /*刷新JList显示
	    * */
	   private void updateJList(){
		   if(newsList!=null){
			   jlist.setListData(newsList.toArray());
			   
		   }  
	   }
	
	  /*
	    * 双击列表显示新闻内容和单击列表
	    * */
	   private class JistListener implements MouseListener{
			public void mouseClicked(MouseEvent e) {
				if(e.getClickCount()==1){
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
		 * 新闻显示页面卡的切换
		 * */
		private void switchRightBodyCard(String card){
			CardLayout c1 = (CardLayout) cardPanelNewsShow.getLayout();
			if(card.equals("cardNewsList")){
				c1.show(cardPanelNewsShow, "cardNewsList");		
			}else if(card.equals("cardNewsContent")){
				c1.show(cardPanelNewsShow, "cardNewsContent");
			}
		}	
}
