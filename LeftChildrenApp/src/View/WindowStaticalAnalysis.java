package View;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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
import model.Variable;
import model.VariableList;
import renderer.JListRenderer;
import renderer.MyTreeMenuMutableTreeNode;

public class WindowStaticalAnalysis extends WindowRoot {
	
	List<News> newsList;
	JPanel panel;
	
	JPanel panelChart;
	JPanel chartPanel;
	
	public WindowStaticalAnalysis() {		
		init();
	}

	
	@Override
	public void init() {
		panel = new JPanel();
		panel.setLayout(null);
		panel.setBackground(Color.DARK_GRAY);
		panel.setBounds(0, 34, 330, 637);
		getContentPane().add(panel);
		
	
		
	
		
		leftChoose();
		
		JPanel panel_1 = new JPanel();
		panel_1.setBounds(330, 34, 800, 637);
		getContentPane().add(panel_1);
		panel_1.setLayout(null);
		
		panelChart = new JPanel();
		panelChart.setBounds(207, 95, 483, 268);
		panel_1.add(panelChart);
		panelChart.setLayout(new ctionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {				
				 Variable var = VariableList.getInstance().getVariableList().get(0);
				 paintPieChart(var);
			}
		});
		button.setBounds(10, 10, 92, 23);
		panel.add(button);
		
		JButton button_1 = new JButton("中央一级的党报");
		button_1.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent arg0) {
				Tag tag = VariableList.getInstance().getVariableList().get(0).getTagList().get(0);
				paintLineChart(tag);
			}			
		});
		button_1.setBounds(10, 35, 133, 23);
		panel.add(button_1);
		
	}		
	
	
	
	private void paintPieChart(Variable var){
		 int sz = var.getTagList().size();
		 System.out.println("报纸类别");
		 double[] data = new double[sz]; 
		 String[] keys = new String[sz];
		 int i=0;
		 for(Tag tag:var.getTagList()){
			 data[i] = tag.getNewsList().size();
			 keys[i] = tag.getName();
			 i++;
		 }
		JFreeChart chart =	Piechart.createPieChart3D(var.getName(),Piechart.getDataPie(data, keys), keys);
		chartPanel = new ChartPanel(chart); 
		panelChart.remove(chartPanel);
		panelChart.add(chartPanel);
	}
	
	private void paintLineChart(Tag tag){
		
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
		chartPanel = new ChartPanel(chart); 
		panelChart.add(chartPanel);
	}
}
