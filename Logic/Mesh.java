package Logic;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;

public class Mesh {

	int width, height;
	public int step;
	public Cell [][] meshCells;
	
	public boolean visibleOfMesh = false;
	
	public Mesh(int w, int h, int s)
	{
		width = w;
		height = h;
		step = s;
		
		meshCells = new Cell [w/s][h/s];
		initCells();
	}
	
	public void drawMesh(Graphics2D g2d)
	{
		g2d.setColor(Color.black);
		
		drawCells(g2d);
		drawLines(g2d);
		
	}
	
	public void drawLines(Graphics g)
	{
		Graphics2D g2d = (Graphics2D) g;
		
		if(visibleOfMesh)
		{
			drawVerticalLines(g2d);
			drawHorizontallLines(g2d);
		}
	}
	
	private void drawVerticalLines(Graphics2D g2d)
	{
		int numbers = width/step;
		for(int i=0; i<=numbers; i++)
			g2d.drawLine(i*step, 0, i*step, height);
	}
	
	private void drawHorizontallLines(Graphics2D g2d)
	{
		int numbers = height/step;
		for(int i=0; i<=numbers; i++)
			g2d.drawLine(0, i*step, width, i*step);
	}
	
	private void drawCells(Graphics2D g2d)
	{
		for(int i=0; i<meshCells.length; i++)
			for(int j=0; j<meshCells[0].length; j++)
			{
				if(meshCells[i][j].state.state == 1) 
					meshCells[i][j].drawCell(g2d);
			}
	}
	
	public void initCells()
	{
		
		for(int i=0; i<meshCells.length; i++)
			for(int j=0; j<meshCells[0].length; j++)
			{
				meshCells[i][j]= new Cell(i*step, j*step, step, step, 0);
			}
	}
	
	public void updateCells(int s, Cell[][] tab)
	{
		step = s;
		
		meshCells = new Cell [width/s][height/s];
		initCells();
		rewrite(tab);
		
	}
	
	private void rewrite(Cell [][] tab)
	{
		int w,h;
		w=0;
		h=0;
		
		if(tab.length<meshCells.length) w= tab.length;
		if(tab.length>meshCells.length) w= meshCells.length;
		if(tab[0].length<meshCells[0].length) h= tab[0].length;
		if(tab[0].length>meshCells[0].length) h= meshCells[0].length;
		
		
		for(int i=0; i<w; i++)
			for(int j=0; j<h; j++)
			{
				meshCells[i][j].state.state = tab[i][j].state.state;
			}
		
	}
	
	public void newMesh(int row, int column, int s)
	{
		step =s;
		meshCells = null;
		meshCells= new Cell [row][column];
		initCells();
	}
}
