package model;
import java.util.*;

public class Location {
	
	private String name;
	private List<News> newsList;
	
	public Location(String name) {
		this.name = name;
		newsList = new ArrayList<News>();
	}

	public void setName(String name) {
	    this.name = name;
	}

	public String getName() {
	    return name;
	}
	
	public List<News> getNews() {
		return newsList;
	}
	
	public void addNews(News news) {
		newsList.add(news);
	}
	
}
