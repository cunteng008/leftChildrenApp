package broker;

import java.util.List;

import model.News;
import model.NewsList;
import model.Tag;
import model.Variable;
import model.VariableList;
import util.ReadXML;

public class MainBroker {
	
	public void openFile(String fileName){
		ReadXML.parseXML(fileName);		
	}
	public void setTags(News news,List<String> tagNames){
		List<Variable> varList = news.getVaribleList();
		if(tagNames.size()==0 &&  varList.size()==0 ){
			return;
		}else if(tagNames.size()==0){
			System.out.println("现在前没选");
			if(varList.size()>0){
				NewsList.getInstance().getNewsList().add(news);
			}			
		}else if(varList.size()==0){
			if(tagNames.size()>0){
				NewsList.getInstance().getNewsList().remove(news);
			}			
		}
		for(Variable var:varList){
			for(Tag tag:var.getTagList()){
					tag.removeNews(news);
			}
		}
		news.removeAllVariable();
		
		boolean hasAttachTags;
		for(Variable var:VariableList.getInstance().getVariableList()){	
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
