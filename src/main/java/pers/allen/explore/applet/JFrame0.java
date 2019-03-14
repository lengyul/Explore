package pers.allen.explore.applet;

import javax.swing.JFrame;
import javax.swing.JLabel;

public class JFrame0 {
	
	public static void main(String[] args) {
		JFrame jf = new JFrame("JFrame0 Model");	
		jf.setSize(500, 500);
		jf.getContentPane().add(new JLabel("lengyul"));
		
		jf.setVisible(true);
	}
}
