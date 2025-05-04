package Interface;

import java.awt.BorderLayout;
import java.awt.Container;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;

public class Window extends JFrame{

	
	
	public Window()
	{
		super();
		setBounds(0,0, 500, 400);
		setTitle("Game Of Life");
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		ImageIcon ii = ResourceLoader.load("images/TheGameOfLifeIcon.png");
		setIconImage(ii.getImage());
		
		DrawPanel dp = new DrawPanel();
		ButtonPanel bp = new ButtonPanel(dp);
		
		Container co = getContentPane();
		co.add(bp, BorderLayout.NORTH);
		co.add(dp, BorderLayout.CENTER);
		setJMenuBar(bp.menuBar);
		
		setVisible(true);
	}
	
	
}
