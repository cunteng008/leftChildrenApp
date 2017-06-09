import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import broker.MainBroker;
import model.News;
import model.NewsList;
import model.Tag;
import util.InitVarsAndTags;

public class NewsTest {

	public static List<News> newsList = new ArrayList<News>();
//	MainBroker mainBroker = new MainBroker();
	
	@Before
	public void setUp() throws Exception {
//		  mainBroker = new MainBroker();
//		  InitVarsAndTags.init();
//		  String fileName = "../LeftChildrenApp/res/testData/testData.xml";
//		  mainBroker.openFile(fileName);
//	      newsList = NewsList.getInstance().getNewsList();  
	}

	@Test
	public void testGetAllLabel() {
//		News news = newsList.get(0);
//		List<String> tags = new ArrayList<String>();
//		List<String> tagsList = new ArrayList<String>();
//		tags.add("中央一级的党报");
//		tags.add("纯净新闻");
// 		mainBroker.setTags(news,tags );
// 		tagsList =  news.getAllLabel();
// 		for(String tag: tagsList){
// 			System.out.println(tag);
// 		}
	}

}
