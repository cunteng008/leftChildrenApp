package model;
import java.util.*;

public class Tag {
	
	private String name;
	private List<News> newsList;
	
	public Tag(String name) {
		this.name = name;
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
	public void removeNews(int index){
		if(index<0 && index>=newsList.size()){
			return;
		}
		newsList.remove(index);
	}
	public void removeNews(News news){
		if(newsList.contains(news)){
			newsList.remove(news);
		}
	}
	public boolean hasNews(News news){
		return newsList.contains(news);
	}
	
}
