package util;

import java.io.IOException;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

/*
 * All Right By Shenjie
 * */

public class LoadFromUrl {
	public static String getNewsContentFromUrl(String url){
		String ret="";
		Element content = null;
		Document doc = null;	
		try {
			doc = (Document) Jsoup.connect(url).get();
			String str =
			        doc.html().replace("&nbsp;", "$").replace("<strong>", "*").replace("</strong>", "");	
			doc = Jsoup.parse(str);
		} catch (IOException e) {
			e.printStackTrace();
		}
		if(doc.getElementById("articleContent") != null){
			content = doc.getElementById("articleContent");
		}else if(doc.getElementById("page_a")!=null){
			content = doc.getElementById("page_a");
		}
		ret = content.text().replace("$","");
		return ret;
	}
}
