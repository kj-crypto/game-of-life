package Logic;

import java.awt.Graphics2D;
import java.awt.Rectangle;

public class Cell {

	public State state;
	public int numberOfNeighboors;
	Rectangle rect;
	
	public State whatState()
	{
		State state= null;
		if(numberOfNeighboors>4) state.state = 0;
		
		return state;
	}
	
	public Cell(int x, int y, int w, int h, int s)
	{
		rect= new Rectangle(x, y, w, h);
		state = new State(s);
		numberOfNeighboors =0;
	}
	
	public void drawCell(Graphics2D g2d)
	{
		g2d.fillRect(rect.x, rect.y, rect.width, rect.height);
	}
	
	public String toString()
	{
		return "x= "+rect.x+" y= "+rect.y+" w= "+rect.width+" h= "+rect.height+" state= "+state.state+" nieghtboors= "+numberOfNeighboors;
	}
}
