package model;
import java.util.*;

import util.LoadFromUrl;

public class News {

	private String title;
	private Calendar calendar;
	private String type;
	private double wordCount;
	private boolean hasContent;
	private String content;
	private String trueUrl;
	private String ID = "";
	private boolean isDeleted;
	
	private List<Variable> variableList;

	public News() {
		variableList = new ArrayList<Variable>();
	}

	public void setTitle(String title) {
	    this.title = title;
	}

	public String getTitle() {
	    return title;
	}

	public void setCalendar(Calendar calendar) {
	    this.calendar = calendar;
	}

	public Calendar getCalendar() {
	    return calendar;
	}

	public void setType(String type) {
	    this.type = type;
	}

	public String getType() {
	    return type;
	}

	public void setWordCount(double wordCount) {
	    this.wordCount = wordCount;
	}

	public double getWordCount() {
	    return wordCount;
	}

	public void setHasContent(boolean hasContent) {
	    this.hasContent = hasContent;
	}

	public boolean getHasContent() {
	    return hasContent;
	}

	public void setContent(String content) {
	    this.content = content;
	}

	public String getContent() {
		if(content==null){
			return LoadFromUrl.getNewsContentFromUrl(trueUrl);
		}
	    return content;
	}

	public void setTrueUrl(String trueUrl) {
	    this.trueUrl = trueUrl;
	}

	public String getTrueUrl() {
	    return trueUrl;
	}

	public void setTagList(List<Variable> variableList) {
		this.variableList = variableList;
	}
	
	public List<Variable> getVaribleList() {
		return variableList;
	}
	
	public void addVariable(Variable variable) {
		variableList.add(variable);
	}
	public void removeAllVariable(){
		variableList.removeAll(variableList);
	}
	public void clearVariable(){
		variableList.removeAll(variableList);
	}
	public List<String>  getAllLabel(){
		List<String> allLabels = new ArrayList<String>(); 
		for(Variable var:variableList){
			for(Tag tag:var.getTagList()){
				boolean hasInclude = false;
				for(News news : tag.getNewsList()){
					if(news.getTitle().equals(title)){
						allLabels.add((String) tag.getName());
						hasInclude = true;
						break;
					}
				}
				if(hasInclude){
					break;
				}				
			}
		}
		return allLabels;
	}
	
	public void setID(String ID){
		this.ID = ID;
	}
	public String getID(){
		return ID;
	}

	public boolean isDeleted() {
		return isDeleted;
	}

	public void setIsDeleted(boolean isTrash) {
		this.isDeleted = isTrash;
	}

	public List<Variable> getVariableList() {
		return variableList;
	}

	public void setVariableList(List<Variable> variableList) {
		this.variableList = variableList;
	}
	
}
