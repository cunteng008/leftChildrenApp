package model;
import java.util.*;

public class News {

	private String title;
	private Calendar calendar;
	private String type;
	private double wordCount;
	private boolean hasContent;
	private String content;
	private String trueUrl;

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
	    return content;
	}

	public void setTrueUrl(String trueUrl) {
	    this.trueUrl = trueUrl;
	}

	public String getTrueUrl() {
	    return trueUrl;
	}
	
}
