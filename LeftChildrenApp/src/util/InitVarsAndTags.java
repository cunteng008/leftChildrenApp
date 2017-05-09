package util;

import model.Tag;
import model.Variable;
import model.VariableList;

public class InitVarsAndTags {
	
	public static void init() {
		
		VariableList variableList = VariableList.getInstance();
		
		Variable v1 = new Variable();
		v1.setName("报纸类型");
		v1.addTag(new Tag("中央一级的党报"));
		v1.addTag(new Tag("省一级的党报"));
		v1.addTag(new Tag("经营模式市场化的报纸"));
		
		Variable v2 = new Variable();
		v2.setName("新闻类型");
		v2.addTag(new Tag("纯净新闻"));
		v2.addTag(new Tag("特稿与特写"));
		v2.addTag(new Tag("评论"));
		v2.addTag(new Tag("其他"));
		
		/* 待商榷 */
		Variable v3 = new Variable();
		v3.setName("报道数量");
		
		Variable v4 = new Variable();
		v4.setName("报道主题");
		v4.addTag(new Tag("社会各界帮助关爱"));
		v4.addTag(new Tag("社会各界对留守儿童现象提出的建议和看法"));
		v4.addTag(new Tag("表彰帮助关爱留守儿童的单位或个人"));
		v4.addTag(new Tag("留守儿童遭受暴力"));
		v4.addTag(new Tag("留守儿童被性侵、猥亵、强奸，或是怀孕、生子等"));
		v4.addTag(new Tag("留守儿童犯罪等"));
		v4.addTag(new Tag("留守儿童意外死亡"));
		v4.addTag(new Tag("留守儿童努力上进"));
		v4.addTag(new Tag("打工父母在城市艰难的生活"));
		v4.addTag(new Tag("其他"));

		Variable v5 = new Variable();
		v5.setName("新闻报道消息来源");
		v5.addTag(new Tag("记者"));
		v5.addTag(new Tag("政府"));
		v5.addTag(new Tag("企业"));
		v5.addTag(new Tag("事业单位"));
		v5.addTag(new Tag("公益团体"));
		v5.addTag(new Tag("专家学者"));
		v5.addTag(new Tag("政府领导、政协或人大代表"));
		v5.addTag(new Tag("其他"));

		Variable v6 = new Variable();
		v6.setName("媒介形象呈现");
		v6.addTag(new Tag("积极健康的形象"));
		v6.addTag(new Tag("可怜悲惨的形象"));
		v6.addTag(new Tag("沐恩幸福的形象"));
		v6.addTag(new Tag("问题儿童的形象"));
		v6.addTag(new Tag("其他"));
		
		Variable v7 = new Variable();
		v7.setName("帮助新闻的具体种类");
		v7.addTag(new Tag("单纯一次捐款捐物"));
		v7.addTag(new Tag("旅游活动安排的项目之一"));
		v7.addTag(new Tag("免费开放"));
		v7.addTag(new Tag("设立长期资助项目"));
		v7.addTag(new Tag("其他"));
		
		Variable v8 = new Variable();
		v8.setName("帮助类新闻的主体");
		v8.addTag(new Tag("政府部门"));
		v8.addTag(new Tag("企业"));
		v8.addTag(new Tag("事业单位"));
		v8.addTag(new Tag("公益团体"));
		v8.addTag(new Tag("个人"));
		
		Variable v9 = new Variable();
		v9.setName("表彰奖励的新闻主体");
		v9.addTag(new Tag("政府部门"));
		v9.addTag(new Tag("企业"));
		v9.addTag(new Tag("事业单位"));
		v9.addTag(new Tag("公益团体"));
		v9.addTag(new Tag("个人"));
		
		Variable v10 = new Variable();
		v10.setName("新闻报道中有关农民工子女不能留在城市读书的原因");
		v10.addTag(new Tag("无本地户籍难入公立学校"));
		v10.addTag(new Tag("私立学校学费高"));
		v10.addTag(new Tag("私立学校教学质量没保障"));
		v10.addTag(new Tag("越来越多的小型私立学校被国家取消办学资格"));
		v10.addTag(new Tag("其他"));
		
		Variable v11 = new Variable();
		v11.setName("回收站");
		v11.addTag(new Tag("垃圾"));
				
		variableList.addVariable(v1);
		variableList.addVariable(v2);
		variableList.addVariable(v3);
		variableList.addVariable(v4);
		variableList.addVariable(v5);
		variableList.addVariable(v6);
		variableList.addVariable(v7);
		variableList.addVariable(v8);
		variableList.addVariable(v9);
		variableList.addVariable(v10);
		variableList.addVariable(v11);
		
	}

}