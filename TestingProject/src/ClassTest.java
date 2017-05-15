import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;

import model.News;


public class ClassTest {
	static List<News> newsList = new ArrayList<News>();
	@Test
	public void test() {
		
		for(int i=0;i<10;i++){
			News news = new News();
			news.setTitle(""+i);
			newsList.add(news);
		}
		//List<String>  strs1 = strs;
		List<News> strs2 =  new ArrayList<News>();
		//strs2.addAll(strs);
		
		// Is strs2 ,strs1,strs point to the same address ?
		for(int i=0;i<newsList.size();i++){
			strs2.add(newsList.get(i));
			if(strs2.get(i) == newsList.get(i)){
				continue;
			}else{
				System.out.println("str2和arr不是指向同一个地址");
			}
		}
		// What is the difference between str1 and str2 ?
		newsList.get(0).setTitle("修改");
		newsList.remove(0); 
		News news = newsList.get(3);
		newsList.removeAll(newsList);
		  	
		//Assert.assertEquals(strs.get(0), strs2.get(0));
		System.out.println(news.getTitle());
		System.out.println("the length of strs is " + newsList.size());
		System.out.println("the length of strs2 is " + strs2.get(0).getTitle());
	}

}
