
import java.awt.EventQueue;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

import View.WindowMain;
import model.VariableList;
import util.InitVarsAndTags;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		// init vars
				VariableList mVars = VariableList.getInstance();
				try {
					FileInputStream myVarsIn = new FileInputStream("savedata/variableList");
					if (myVarsIn != null)
						mVars.setVariableList(((VariableList) WindowMain.readObjectFromFile("savedata/variableList")).getVariableList());
				} catch (FileNotFoundException e) {
					InitVarsAndTags.init();
					System.out.println("no local vars data");
				}
				
		
		
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					WindowMain frame = new WindowMain();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

}
