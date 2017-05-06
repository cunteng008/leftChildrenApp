import static org.junit.Assert.*;

import java.util.List;

import org.junit.Before;
import org.junit.Test;

import model.Location;
import model.LocationCollection;
import model.News;
import util.ReadXML;

public class ReadXMLTest {

	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void testImportLocation() {
		
		ReadXML.importLocation("guangming");
		ReadXML.importLocation("nanfangdaily");
		ReadXML.importLocation("sichuan");
		
		LocationCollection locationCollection = LocationCollection.getInstance();
		
		List<Location> locationList = locationCollection.getLocations();
		
		// �Ƿ�������ֱ�ֽ
		assertTrue(locationList.size() == 3);
		
		int sum = 0;
		
		for (Location location : locationList) {
			List<News> newsList = location.getNews();
			sum += newsList.size();
			
			for (News news : newsList) {
				System.out.println(news.getTitle());
				System.out.println(news.getCalendar().toString());
				System.out.println(news.getType());
				System.out.println(news.getWordCount() + "");
				
				if (news.getHasContent()) {
					assertTrue(news.getTrueUrl() == null);
					System.out.println(news.getContent());
				}
				else {
					assertTrue(news.getContent() == null);
					System.out.println(news.getTrueUrl());
				}
				System.out.println();
			}
		}
		
		
		assertTrue(sum == 1420 + 1156 + 1235);
		
	}

	@Test
	public void testParseXML() {
		
	}

}
