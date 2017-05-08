package controller;

import model.VariableList;
import util.ReadXML;

public class MainController {
	
	public static void loadFile(String fileName){
		ReadXML.parseXML(fileName);
		
	}
}
