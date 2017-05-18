package model;
import java.io.Serializable;
import java.util.*;

/* Singleton */
public class NewsList implements Serializable {
	
	private static NewsList instance = new NewsList();
	
	public static NewsList getInstance() {
		return instance;
	}
	
	private NewsList() {
		newsList = new ArrayList<News>();
	}
	
	private List<News> newsList;

	public List<News> getNewsList() {
		return newsList;
	}

	public void setNewsList(List<News> newsList) {
		this.newsList = newsList;
	}
	
	public void addNews(News news) {
		newsList.add(news);
	}
	
}
