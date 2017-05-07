package model;
import java.util.*;

public class Variable {
	
	private String name;
	private List<Tag> tagList;
	
	public Variable() {
		tagList = new ArrayList<Tag>();
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public List<Tag> getTagList() {
		return tagList;
	}
	
	public void setTagList(List<Tag> tagList) {
		this.tagList = tagList;
	}
	
	public void addTag(Tag tag) {
		tagList.add(tag);
	}

}
