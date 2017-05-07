package util;

import model.Tag;
import model.Variable;
import model.VariableList;

public class InitVarsAndTags {
	
	public static void init() {
		
		VariableList variableList = VariableList.getInstance();
		
		Variable v1 = new Variable();
		v1.setName("��ֽ����");
		v1.addTag(new Tag("����һ���ĵ���"));
		v1.addTag(new Tag("ʡһ���ĵ���"));
		v1.addTag(new Tag("��Ӫģʽ�г����ı�ֽ"));
		
		Variable v2 = new Variable();
		v2.setName("��������");
		v2.addTag(new Tag("��������"));
		v2.addTag(new Tag("�ظ�����д"));
		v2.addTag(new Tag("����"));
		v2.addTag(new Tag("����"));
		
		/* ����ȶ */
		Variable v3 = new Variable();
		v3.setName("��������");
		
		Variable v4 = new Variable();
		v4.setName("��������");
		v4.addTag(new Tag("����������ذ�"));
		v4.addTag(new Tag("����������ض�ͯ��������Ľ���Ϳ���"));
		v4.addTag(new Tag("���ð����ذ����ض�ͯ�ĵ�λ�����"));
		v4.addTag(new Tag("���ض�ͯ���ܱ���"));
		v4.addTag(new Tag("���ض�ͯ�����֡������ǿ�飬���ǻ��С����ӵ�"));
		v4.addTag(new Tag("���ض�ͯ�����"));
		v4.addTag(new Tag("���ض�ͯ��������"));
		v4.addTag(new Tag("���ض�ͯŬ���Ͻ�"));
		v4.addTag(new Tag("�򹤸�ĸ�ڳ��м��ѵ�����"));
		v4.addTag(new Tag("����"));

		Variable v5 = new Variable();
		v5.setName("���ű�����Ϣ��Դ");
		v5.addTag(new Tag("����"));
		v5.addTag(new Tag("����"));
		v5.addTag(new Tag("��ҵ"));
		v5.addTag(new Tag("��ҵ��λ"));
		v5.addTag(new Tag("��������"));
		v5.addTag(new Tag("ר��ѧ��"));
		v5.addTag(new Tag("�����쵼����Э���˴����"));
		v5.addTag(new Tag("����"));

		Variable v6 = new Variable();
		v6.setName("ý���������");
		v6.addTag(new Tag("��������������"));
		v6.addTag(new Tag("�������ҵ�����"));
		v6.addTag(new Tag("����Ҹ�������"));
		v6.addTag(new Tag("�����ͯ������"));
		v6.addTag(new Tag("����"));
		
		Variable v7 = new Variable();
		v7.setName("�������ŵľ�������");
		v7.addTag(new Tag("����һ�ξ�����"));
		v7.addTag(new Tag("���λ���ŵ���Ŀ֮һ"));
		v7.addTag(new Tag("��ѿ���"));
		v7.addTag(new Tag("��������������Ŀ"));
		v7.addTag(new Tag("����"));
		
		Variable v8 = new Variable();
		v8.setName("���������ŵ�����");
		v8.addTag(new Tag("��������"));
		v8.addTag(new Tag("��ҵ"));
		v8.addTag(new Tag("��ҵ��λ"));
		v8.addTag(new Tag("��������"));
		v8.addTag(new Tag("����"));
		
		Variable v9 = new Variable();
		v9.setName("���ý�������������");
		v9.addTag(new Tag("��������"));
		v9.addTag(new Tag("��ҵ"));
		v9.addTag(new Tag("��ҵ��λ"));
		v9.addTag(new Tag("��������"));
		v9.addTag(new Tag("����"));
		
		Variable v10 = new Variable();
		v10.setName("���ű������й�ũ����Ů�������ڳ��ж����ԭ��");
		v10.addTag(new Tag("�ޱ��ػ������빫��ѧУ"));
		v10.addTag(new Tag("˽��ѧУѧ�Ѹ�"));
		v10.addTag(new Tag("˽��ѧУ��ѧ����û����"));
		v10.addTag(new Tag("Խ��Խ���С��˽��ѧУ������ȡ����ѧ�ʸ�"));
		v10.addTag(new Tag("����"));
		
		Variable v11 = new Variable();
		v11.setName("����վ");
		
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
