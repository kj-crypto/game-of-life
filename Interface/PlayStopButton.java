package Interface;

import javax.swing.ImageIcon;
import javax.swing.JButton;

public class PlayStopButton extends JButton {

	boolean stop = false;
	final private ImageIcon p,s;
	
	public PlayStopButton()
	{
		super();
		
		p=ResourceLoader.load("images/play.png");
		s=ResourceLoader.load("images/pause.png");
		super.setIcon(p);
		
	}
	
	public void state()
	{
		if(stop) 
		{
			setIcon(s);
		}
		else
		{
			setIcon(p);
		}
	}
}
