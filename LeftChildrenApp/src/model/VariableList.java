package model;
import java.util.*;

/* Singleton */
public class VariableList {
	
	private static VariableList instance = new VariableList();
	
	public static VariableList getInstance() {
		return instance;
	}
	
	private VariableList() {
		variableList = new ArrayList<Variable>();
	}
	
	private List<Variable> variableList;

	public List<Variable> getVariableList() {
		return variableList;
	}

	public void setVariableList(List<Variable> variableList) {
		this.variableList = variableList;
	}
	
	public void addVariable(Variable variable) {
		variableList.add(variable);
	}

}
