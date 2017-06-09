
import java.awt.EventQueue;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import View.WindowLogin;
import View.WindowMain;
import View.WindowRoot;
import log.Log4j;
import model.VariableList;
import util.InitVarsAndTags;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		// init vars
		
		
	
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					WindowRoot frame = new WindowLogin();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

}
