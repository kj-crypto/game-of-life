package Interface;

import java.awt.BorderLayout;
import java.awt.Container;

import javax.swing.JApplet;
import javax.swing.JButton;

public class Applet extends JApplet {

	@Override
	public void init() {
		// TODO Auto-generated method stub
		super.init();
		
		//add(new JButton("nowy"), BorderLayout.NORTH);
		DrawPanel dp = new DrawPanel();
		
		//Container co = getContentPane();
		add(new ButtonPanel(dp), BorderLayout.NORTH);
		add(dp, BorderLayout.CENTER);
	}
}
