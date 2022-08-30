package launcher;
import javax.swing.JFrame;

import entities.*;
import gui.*;

public class Launcher {
	public static void main(String [] args) {
        //Schedule a job for the event-dispatching thread:
        //creating and showing this application's GUI.
		Student s1 = new Student(44881207, "Ian", "Sebalt", "iansebalt@live.com.ar", "https://github.com/IanSebalt", "/images/ig.png");
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
            	JFrame nuevo = new SimplePresentationScreen(s1);
            	nuevo.setVisible(true);
            }
        });
    }
}