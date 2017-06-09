package model;

import java.io.Serializable;

public class User implements Serializable {
	
	private String name;
	private boolean isQualified;
	private NewsList newsList;
	private VariableList variableList;
	
	public User(String name) {
		this.name = name;
		newsList = new NewsList();
		variableList = new VariableList();
	}
	public User() {
		this.name = null;
		newsList = new NewsList();
		variableList = new VariableList();
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public boolean isQualified() {
		return isQualified;
	}

	public void setQualified(boolean isQualified) {
		this.isQualified = isQualified;
	}

	public NewsList getNewsList() {
		return newsList;
	}

	public void setNewsList(NewsList newsList) {
		this.newsList = newsList;
	}

	public VariableList getVariableList() {
		return variableList;
	}

	public void setVariableList(VariableList variableList) {
		this.variableList = variableList;
	}

}
