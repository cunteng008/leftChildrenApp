package model;

public class NewsTitle_ID {
	private String newsTile;
	private int id;
	
	public NewsTitle_ID(String newsTitle,int id){
		this.newsTile = newsTitle;
		this.id = id;
	}
	
	public String getNewsTile() {
		return newsTile;
	}
	public void setNewsTile(String newsTile) {
		this.newsTile = newsTile;
	}
	public int getID() {
		return id;
	}
	public void setID(int id) {
		this.id = id;
	}
	
}
