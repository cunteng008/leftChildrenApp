package model;
import java.util.*;

public class Tag {
	
	private String name;
	private List<News> newsList;
	
	public Tag() {
		newsList = new ArrayList<News>();
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
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
