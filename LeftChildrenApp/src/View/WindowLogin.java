package View;

import java.awt.EventQueue;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import javax.swing.JFrame;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import broker.MainBroker;
import log.Log4j;
import model.News;
import model.NewsList;

import model.Tag;
import model.User;
import model.Variable;
import model.VariableList;
import util.FileUtil;
import util.InitVarsAndTags;
import javax.swing.JLabel;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class WindowLogin extends WindowRoot {

	Vector<String> userNames;
	String selectedUser;
	JComboBox comboBox;
	User user;
	
	public WindowLogin(){
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 		
		this.setSize(300,300);
		
		
		init();
	}
	
	@Override
	public void init() {
		JLabel label = new JLabel("请选择用户:");
		label.setBounds(50, 90, 87, 15);
		getContentPane().add(label);
				
		userNames=new Vector();
		FileUtil.readFileByLines("savedata/users.txt", userNames);
		comboBox = new JComboBox(userNames);
		comboBox.setBounds(41, 115, 194, 29);
		getContentPane().add(comboBox);
		
		JButton btnNewButton = new JButton("进入");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				login();				
			}
		});
		btnNewButton.setBounds(66, 183, 169, 23);
		getContentPane().add(btnNewButton);
	}
	
	
	private void  login(){
		
		
		selectedUser = comboBox.getSelectedItem().toString();
		user = new User(selectedUser);
		
		Logger logger = LogManager.getLogger("mylog");
		
		VariableList mVars;
		NewsList mNews;
		mVars = user.getVariableList();
		// init news
		mNews = user.getNewsList();
		try {
			FileInputStream myVarsIn = new FileInputStream("savedata/"+user.getName());
			if (myVarsIn != null)
				user=(((User) WindowMain.readObjectFromFile("savedata/"+
						user.getName())));
		} catch (FileNotFoundException e) {
			InitVarsAndTags.init(user);
			System.out.println("no local vars data");
		}
		
				
		itegrate();
		
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					WindowMain frame = new WindowMain(user);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
		
		this.dispose();
	}
	
	private void itegrate(){
		User otherUser = new User();
		System.out.println("合并");
		for(String u: userNames){
			if(!u.equals(selectedUser)){
				otherUser.setName(u);
				System.out.println(u+"与其即将合并");
			    VariableList mVars;
				try {	
					;
					FileInputStream myVarsIn = new FileInputStream("savedata/"+otherUser.getName());
					System.out.println("扫面是否有标签");
					if (myVarsIn != null){
						otherUser=(((User) WindowMain.readObjectFromFile("savedata/"+
								otherUser.getName())));
						
					}	
					
					int setLoc = 0;
					News setNews = null;
					MainBroker mainBroker = new MainBroker(user);
	continuePoint:for(News news:user.getNewsList().getNewsList()){
						for(Variable otherVar:otherUser.getVariableList().getVariableList()){
							for(News otherNews:otherVar.getNewsList()){
								if(news.getTitle().equals(otherNews.getTitle())){
									System.out.println(news.getTitle());
									user.getNewsList().getNewsList().set(setLoc,otherNews);
									mainBroker.setTags(news, otherNews.getAllLabel());
									continue continuePoint;
								}
							}
						}
						setLoc++;
					}
				} catch (FileNotFoundException e) {
				  
					System.out.println("no local vars data");
				}
			}
		}
	}
	
}
