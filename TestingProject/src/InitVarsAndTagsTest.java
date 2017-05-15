

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Before;
import org.junit.Test;

import model.Tag;
import model.Variable;
import model.VariableList;
import util.InitVarsAndTags;

public class InitVarsAndTagsTest {

	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void test() {
		InitVarsAndTags.init();
		VariableList variableList = VariableList.getInstance();
		List<Variable> variables = variableList.getVariableList();
		
		// 判断variables的数量是否正确
		assertTrue(variables.size() == 11);
		
		// 判断第10个大类的信息
		
		Variable variable10 = variables.get(9);
		// 判断大类名称是否正确
		assertTrue(variable10.getName().equals("农民工子女不能留城读书原因"));
		
		List<Tag> tags = variable10.getTagList();
		
		// 判断小类数量及名称是否正确
		assertTrue(tags.size() == 5);
		assertTrue(tags.get(0).getName().equals("无本地户籍难入公立学校"));
		assertTrue(tags.get(1).getName().equals("私立学校学费高"));
	}

}
