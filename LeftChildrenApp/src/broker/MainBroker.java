package broker;

import java.util.List;

import model.News;
import model.NewsList;
import model.Tag;
import model.User;
import model.Variable;
import model.VariableList;
import util.ReadXML;

public class MainBroker {
	NewsList UnclassifiedNewsList = null;
	VariableList variableList = null;
	public MainBroker(User user){
		UnclassifiedNewsList =  user.getNewsList();
		variableList = user.getVariableList();
	}
	
	public void setUnclassifiedNewsList(NewsList unclassifiedNewsList) {
		UnclassifiedNewsList = unclassifiedNewsList;
	}
	public void setVariableList(VariableList variableList) {
		this.variableList = variableList;
	}


	public void setTags(News news,List<String> tagNames){
		List<Variable> varList = news.getVaribleList();
		if(tagNames.size()==0 &&  varList.size()==0 ){
			return;
		}else if(tagNames.size()==0){
			System.out.println("现在前没选");
			if(varList.size()>0){
				UnclassifiedNewsList.getNewsList().add(news);
			}			
		}else if(varList.size()==0){
			if(tagNames.size()>0){
				UnclassifiedNewsList.getNewsList().remove(news);
			}			
		}
		for(Variable var:varList){
			for(Tag tag:var.getTagList()){
					tag.removeNews(news);
			}
		}
		news.removeAllVariable();
		
		boolean hasAttachTags;
		for(Variable var:variableList.getVariableList()){	
			hasAttachTags = false;
			for(Tag tag:var.getTagList()){
				
				for(String tagName:tagNames){
					if(tag.getName().equals(tagName)){
						tag.addNews(news);
						hasAttachTags = true;
						continue;
					}
				}
			}
			if(hasAttachTags){
				if(news == null){
					return;
				}
				news.addVariable(var);
			}			
		}				
	}
	
	
}
