package View;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Calendar;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTree;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.tree.DefaultMutableTreeNode;

import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.general.DatasetUtilities;
import org.jfree.data.general.DefaultPieDataset;
import org.jfree.data.general.PieDataset;

import broker.MainBroker;
import chart.LineChart;
import chart.Piechart;
import model.News;
import model.Tag;
import model.User;
import model.Variable;
import model.VariableList;
import renderer.JListRenderer;
import renderer.MyTreeMenuMutableTreeNode;

public class WindowStaticalAnalysis extends WindowRoot {
	
	List<News> newsList;
	User user;
	JPanel panelChoose;
	
	JPanel panelChart;
	JPanel chartPanel;
	JPanel panelCardChartShow;
	private JTree tree;
	
	
	public WindowStaticalAnalysis(User user) {	
		super();
		this.user = user;
		init();
	}
//
//	
	@Override
	public void init() {
		
		JPanel panelShow = new JPanel();
		panelShow.setBounds(330, 34, 800, 637);
		getContentPane().add(panelShow);
		panelShow.setLayout(null);
		
		panelCardChartShow = new JPanel();
		panelCardChartShow.setBounds(146, 56, 570, 379);
		panelShow.add(panelCardChartShow);
		panelCardChartShow.setLayout(new CardLayout(0, 0));
		
			
		DefaultMutableTreeNode top = new DefaultMutableTreeNode("标签");
		for(int i=0;i< user.getVariableList().getVariableList().size()-1;i++){
			if(i==2){
				continue;
			}
		   Variable var=user.getVariableList().getVariableList().get(i);
		   String varName = var.getName();
		   DefaultMutableTreeNode node = new DefaultMutableTreeNode(varName);
		   for(int j=0;j<var.getTagList().size();j++){
			   Tag tag = var.getTagList().get(j);
			   String tagName = tag.getName();
			   node.add(new DefaultMutableTreeNode(tagName));
		   }
		   top.add(node);
		}		
		tree = new JTree(top);
		tree.setBounds(0, 0, 78, 64);
		tree.addMouseListener(new  TreeMenuListener());
		JScrollPane scrollPane = new JScrollPane(tree);
		scrollPane.setBounds(0, 34, 330, 637);
		getContentPane().add(scrollPane);
		 
		
		for(int i=0;i< user.getVariableList().getVariableList().size()-1;i++){
			if(i==2){
				continue;
			}
		   Variable var=user.getVariableList().getVariableList().get(i);
		   String varName = var.getName();
		   panelCardChartShow.add(createPieChart(var),varName);
		   for(int j=0;j<var.getTagList().size();j++){
			   Tag tag = var.getTagList().get(j);
			   String tagName = tag.getName();
			   panelCardChartShow.add(createLineChart(tag),tagName);
		   }
		}
		
	}		
	
	 private class TreeMenuListener implements MouseListener{
		public void mouseClicked(MouseEvent e) {
			DefaultMutableTreeNode node =(DefaultMutableTreeNode)tree.getLastSelectedPathComponent();
			if(node == null){
				 return;
			 }
			Object userObject = node.getUserObject();
			String name = (String) userObject;
			if(name!=null){
				switchLeftMenuCard(name);
			}
		}
		public void mouseEntered(MouseEvent arg0) {	}
		public void mouseExited(MouseEvent arg0) {}
		public void mousePressed(MouseEvent arg0) {}
		public void mouseReleased(MouseEvent arg0) {}
	}
	
		
	private JPanel createPieChart(Variable var){
		 int sz = var.getTagList().size();
		 double[] data = new double[sz]; 
		 String[] keys = new String[sz];
		 int i=0;
		 for(Tag tag:var.getTagList()){
			 data[i] = tag.getNewsList().size();
			 keys[i] = tag.getName();
			 i++;
		 }
		JFreeChart chart =	Piechart.createPieChart3D(var.getName(),Piechart.getDataPie(data, keys), keys);
		JPanel chartPanel = new ChartPanel(chart); 
		return chartPanel;
	}
	
	private JPanel createLineChart(Tag tag){		
		Set<Integer> years = new HashSet<Integer>();
		int year;
		
		for(News news:tag.getNewsList()){
			 year = news.getCalendar().get(Calendar.YEAR);
			    years.add(year);
		}		   
		int sz = years.size();
		double [][] data = new double[1][sz];
		String[] rowKeys = {"新闻"};
		String[] columnKeys = new String[sz];
		
		int i=0;
		int count = 0;
		for(int y:years){
			columnKeys[i]=(y+"");			
			count = 0;
			for(News news:tag.getNewsList()){
				year = news.getCalendar().get(Calendar.YEAR);
				if(year==y){
					count++;
				}
			}	
			data[0][i] = count;	
			i++;
		}	
		
		CategoryDataset dataset = DatasetUtilities.createCategoryDataset(rowKeys, columnKeys, data);
		String chartTitle = tag.getName()+"趋势图";
		String xLabel = "年份";
		String yLabel = "新闻数量";
		JFreeChart chart =  LineChart.createLineAndShapeChart(chartTitle,xLabel,yLabel,dataset);
		JPanel chartPanel = new ChartPanel(chart); 
		//panelCardChart.add(chartPanel);
		return chartPanel;
	}
	
	
	private void switchLeftMenuCard(String card){
		CardLayout c1 = (CardLayout) panelCardChartShow.getLayout();
		for(int i=0;i< user.getVariableList().getVariableList().size()-1;i++){
			if(i==2){
				continue;
			}
			   Variable var=user.getVariableList().getVariableList().get(i);
			   String varName = var.getName();
			   if(card.equals(varName)){
				   c1.show(panelCardChartShow,card);
				   return;
			   }
			   for(int j=0;j<var.getTagList().size();j++){
				   Tag tag = var.getTagList().get(j);
				   String tagName = tag.getName();
				   if(card.equals(tagName)){
					   c1.show(panelCardChartShow,card);
					   return;
				   }
			   }
			}
		
	}	
	
}