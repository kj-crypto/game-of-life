package Interface;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Toolkit;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import Logic.Cell;
import Logic.Game;
import Logic.Mesh;
import Rules.Conway;

public class DrawPanel extends JPanel implements MouseListener, MouseMotionListener {

	Mesh mesh;
	Game game;
	
	public DrawPanel()
	{
		addMouseListener(this);
		addMouseMotionListener(this);
		
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		
		mesh =  new Mesh(dim.width, dim.height, 10);
		game = new Game(mesh);
		game.rule = new Conway();
		
		
	}
	
	@Override
	public void paint(Graphics g)
	{
		super.paint(g);
		Graphics2D g2d = (Graphics2D) g;
		
		g2d.setColor(Color.white);
		g2d.fillRect(0, 0, getWidth(), getHeight());
		g2d.setColor(Color.black);
		
		
		
		if(mesh!=null) mesh.drawMesh(g2d);
		
		
		
	}
	
	public void newMesh(int s)
	{
		mesh.updateCells(s, mesh.meshCells);
		repaint();
	}

	
	
	public void random(int n)
	{
		int s,x,y;
		s=mesh.step;
		mesh.initCells();
		
		for(int i=0; i<n; i++)
		{
			x= (int) ((Math.random()*getWidth()) % getWidth() );
			x=x/s;
			y= (int) ((Math.random()*getHeight()) % getHeight() );
			y=y/s;
		
			mesh.meshCells[x][y].state.state =1;
		}
		repaint();
	}
	
	public void playGame()
	{
		game.computeNeighboors();
		game.update();
		repaint();
	}
	
	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		int s= mesh.step;
		mesh.meshCells[e.getX()/s][e.getY()/s] = new Cell((e.getX()/s)*s, (e.getY()/s)*s, s, s, 1);
		repaint();
	}


	public void mouseEntered(MouseEvent e){}
	public void mouseExited(MouseEvent e) {}
	public void mousePressed(MouseEvent e) {}
	public void mouseReleased(MouseEvent e) {}

	@Override
	public void mouseDragged(MouseEvent e) {
		// TODO Auto-generated method stub
		int s= mesh.step;
		mesh.meshCells[e.getX()/s][e.getY()/s] = new Cell((e.getX()/s)*s, (e.getY()/s)*s, s, s, 1);
		repaint();
	}

	@Override
	public void mouseMoved(MouseEvent arg0) {}

	
}
