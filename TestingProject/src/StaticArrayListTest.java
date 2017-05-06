import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Test;

public class StaticArrayListTest {
	
	static ArrayList<Integer> elements = new ArrayList<Integer>(); 
	@Test
	public void test() {
		
		for(int i=0;i<10;i++){
			elements.add(i);
		}
		 // 静态变量的赋值
		 ArrayList<Integer> CopyElements = elements;
		 CopyElements.remove(0);
		 CopyElements.remove(1);
		 for(int e:elements){
			System.out.println(e);
		 }
	}

}
