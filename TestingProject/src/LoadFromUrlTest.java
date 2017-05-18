import static org.junit.Assert.*;

import org.junit.Test;

import util.LoadFromUrl;


public class LoadFromUrlTest {

	@Test
	public void testGetNewsContentFromUrl() {
		String str =	LoadFromUrl.getNewsContentFromUrl("http://epaper.gmw.cn/gmrb"
				+ "/html/2012-08/30/nw.D110000gmrb_20120830_4-02.htm?div=-1");
		
		assertTrue(str.length()>0);
	}

}
