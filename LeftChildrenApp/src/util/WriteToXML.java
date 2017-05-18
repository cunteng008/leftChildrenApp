package util;



import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.SAXReader;
import org.dom4j.io.XMLWriter;

import model.News;


public class WriteToXML {

	public static void main(String[] args) {
		Map<String,News> data = new HashMap<String,News>();
//		xmlWrite("res/xmlData/guangming.xml",data);
//		xmlWrite("res/xmlData/nanfangdaily.xml",data);
//		xmlWrite("res/xmlData/sichuan.xml",data);
	}
	//@Parameter: fileName   data: a Map newsList
	public static void  xmlWrite(String fileName,Map<String,News> data) {
        try {
           Document doc = new SAXReader().read(new File(fileName));
		   //删除第一个age标签
           Element root = doc.getRootElement();//根节点  
		   Iterator it = root.elementIterator("NewsData");  
		   while(it.hasNext()){
			   Element element = (Element)it.next();  
			   String ID = element.element("ID").getText();
			   element.element("IsDeleted").setText("false");
		   }
		   OutputFormat format = OutputFormat.createPrettyPrint();  
           format.setEncoding("UTF-8");  
           XMLWriter writer = new XMLWriter(new FileWriter(new File(fileName)), format);  
           writer.write(doc);
           writer.close(); 		 			 
		}catch (DocumentException e) {
			e.printStackTrace();
		}catch(IOException e){  
	        e.printStackTrace();  
	    }  
	}

}
