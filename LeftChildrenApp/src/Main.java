
import java.awt.EventQueue;

import View.WindowMain;
import util.InitVarsAndTags;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		InitVarsAndTags.init();
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
