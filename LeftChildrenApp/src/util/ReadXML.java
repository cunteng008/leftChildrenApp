package util;
import java.io.*;
import java.util.*;
import org.w3c.dom.*;
import model.News;
import model.NewsList;
import model.User;

import javax.xml.parsers.*;

import sun.misc.*;

public class ReadXML {
	
	public static void parseXML(User user, String locationName) {
		
		NewsList newsListSingleton = user.getNewsList();
		
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		
		try {
			File f = new File(locationName);
			DocumentBuilder builder = factory.newDocumentBuilder();
			Document doc = builder.parse(f);
			
			NodeList newsList = doc.getElementsByTagName("NewsData");
//			System.out.println("һ����" + newsList.getLength() + "������");
			
			for (int i = 0; i < newsList.getLength(); i++) {
				
				News news = new News();
				
//				System.out.println("=================���濪ʼ������" + (i + 1) + "�����ŵ�����=================");
				Node newsNode = newsList.item(i);

				NodeList childNodes = newsNode.getChildNodes();
				
				// title
//				System.out.println(childNodes.item(9).getTextContent());
				news.setTitle(childNodes.item(9).getTextContent());
				
				// date
				String[] date = childNodes.item(11).getTextContent().split("-");
				Calendar calendar = Calendar.getInstance();
				calendar.set(Integer.parseInt(date[0]), Integer.parseInt(date[1]), 
						Integer.parseInt(date[2]));
//				System.out.println(calendar.toString());
				news.setCalendar(calendar);
				
				// type
//				System.out.println(childNodes.item(17).getTextContent());
				news.setType(childNodes.item(17).getTextContent());
				
				// wordCount
//				System.out.println(childNodes.item(19).getTextContent());
				if (childNodes.item(19).getTextContent().matches("^[0-9]*$"))
					news.setWordCount(Double.parseDouble(childNodes.item(19).getTextContent()));
				else
					news.setWordCount(0);
				
				// hasContent
//				System.out.println(childNodes.item(27).getTextContent().length() > 0);
				news.setHasContent(childNodes.item(27).getTextContent().length() > 0);
				
				if (childNodes.item(27).getTextContent().length() > 0) {
					// content
					String originContent = childNodes.item(27).getTextContent();
					BASE64Decoder base64Decoder = new BASE64Decoder();
					byte[] byteArray = base64Decoder.decodeBuffer(originContent);
					byte temp;
					for (int j = 0; j < byteArray.length; j += 2) {
						temp = byteArray[j];
						byteArray[j] = byteArray[j + 1];
						byteArray[j + 1] = temp;
					}
					String content = new String(byteArray, "utf-16");
					content = content.replaceAll("\r|\n", "");
					content = content.replaceAll("<html><body><P>", "");
					content = content.replaceAll("</P></body></html>", "");
					content = content.replaceAll("<P>", "\n");
//					System.out.println(content);
					news.setContent(content);
				} else {
					// trueUrl
//					System.out.println(childNodes.item(23).getTextContent());
					news.setTrueUrl(childNodes.item(23).getTextContent());
				}
				
//				System.out.println();
				
				newsListSingleton.addNews(news);
				
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
}