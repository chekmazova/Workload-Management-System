package gui;

import java.awt.EventQueue;

public class App {

	public static void main(String[] args) {
	    // Create a frame and set its properties
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				
				try {
					Login loginWindow = new Login();
					loginWindow.setVisible(true);
					
					
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});

	}

}
