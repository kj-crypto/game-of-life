package Interface;

import java.awt.Dimension;
import java.awt.MenuBar;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButtonMenuItem;

import Logic.Mesh;
import Rules.*;

public class ButtonPanel extends JPanel implements ActionListener{

	JButton grid, random, clear, step;
	PlayStopButton play_stop;
	JCheckBox gridVisible;
	DrawPanel dp;
	Thread thread;
	Play play;
	JComboBox<String> listOfFigures;
	JMenuBar menuBar;
	JMenuItem nowy, time;
	
	public ButtonPanel(DrawPanel dp)
	{
		this.dp = dp;
		play = new Play(dp);
		
		
		play_stop= new PlayStopButton();
		play_stop.setToolTipText("Play/Pause");
		play_stop.addActionListener(this);
		add(play_stop);
		
		step= new JButton(ResourceLoader.load("images/next.png"));
		step.setToolTipText("Next step");
		step.addActionListener(this);
		add(step);
		
		random= new JButton(ResourceLoader.load("images/random.png"));
		random.setToolTipText("Random distribution of points");
		random.addActionListener(this);
		add(random);
		
		
		grid = new JButton(ResourceLoader.load("images/grid.png"));
		grid.setToolTipText("Grid generation");
		grid.addActionListener(this);
		add(grid);
		
		
		gridVisible = new JCheckBox("Grid visibility");
		gridVisible.setSelected(false);
		gridVisible.addActionListener(this);
		add(gridVisible);
		
		
		clear= new JButton("Clear");
		clear.addActionListener(this);
		add(clear);
		
		
		
		String tab[] ={"Breeder","Space Ship","Rand Gun", "Rake", "Glider Gun", "Zips","Corder","Hws Gun"};
		listOfFigures = new JComboBox<>(tab);
		listOfFigures.setVisible(true);
		listOfFigures.addActionListener(this);
		add(listOfFigures);
		
		
		// CREATE A MENU
		menuBar = new JMenuBar();
		JMenu rules = new JMenu("Rules");
		
		
		
		
		JRadioButtonMenuItem rule_1 = new JRadioButtonMenuItem("23/3");
		rule_1.setSelected(true);
		rule_1.addActionListener(this);
		rules.add(rule_1);
		
		JRadioButtonMenuItem rule_2 = new JRadioButtonMenuItem("1/1");
		rule_2.addActionListener(this);
		rules.add(rule_2);
		
		JRadioButtonMenuItem rule_3 = new JRadioButtonMenuItem("/2");
		rule_3.addActionListener(this);
		rules.add(rule_3);
		
		JRadioButtonMenuItem rule_4 = new JRadioButtonMenuItem("125/36");
		rule_4.addActionListener(this);
		rules.add(rule_4);
		
		JRadioButtonMenuItem rule_5 = new JRadioButtonMenuItem("1357/1357");
		rule_5.addActionListener(this);
		rules.add(rule_5);
		
		JRadioButtonMenuItem rule_6 = new JRadioButtonMenuItem("23/36");
		rule_6.addActionListener(this);
		rules.add(rule_6);
		
		JRadioButtonMenuItem rule_7 = new JRadioButtonMenuItem("238/357");
		rule_7.addActionListener(this);
		rules.add(rule_7);
		
		JRadioButtonMenuItem rule_8 = new JRadioButtonMenuItem("4567/345");
		rule_8.addActionListener(this);
		rules.add(rule_8);
		
		JRadioButtonMenuItem rule_9 = new JRadioButtonMenuItem("5/345");
		rule_9.addActionListener(this);
		rules.add(rule_9);
		
		ButtonGroup bg = new ButtonGroup();
		bg.add(rule_1);
		bg.add(rule_2);
		bg.add(rule_3);
		bg.add(rule_4);
		bg.add(rule_5);
		bg.add(rule_6);
		bg.add(rule_7);
		bg.add(rule_8);
		bg.add(rule_9);
		
		
		JMenu menu = new JMenu("Menu");
		nowy = new JMenuItem("New");
		time = new JMenuItem("Delay time");
		nowy.addActionListener(this);
		time.addActionListener(this);
		
		menu.add(nowy);
		menu.add(time);
		
		menuBar.add(menu);
		menuBar.add(rules);
		
	}

	private boolean isInt(String str)
	{
		try {
			Integer.parseInt(str);
		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			return false;
		}
		
		return true;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		
		if(e.getSource().equals(grid))
		{
			String step = JOptionPane.showInputDialog("Lenght of step");
			if(isInt(step))
			{
				dp.newMesh(Integer.parseInt(step));
			}
			
		}
		
		if(e.getSource().equals(random))
		{
			String n = JOptionPane.showInputDialog("Number of random cells");
			
			if(isInt(n)) 
			{
				dp.random(Integer.parseInt(n));
			}
		}
		
		
		if(e.getSource().equals(play_stop))
		{
			
			
			if(play_stop.stop)
			{
				
				play_stop.stop=false;
				thread.stop();
				
				listOfFigures.setEnabled(true);
				step.setEnabled(true);
			}
			else
			{
				thread = new Thread(play);
				thread.start();
				play_stop.stop = true;
				
				listOfFigures.setEnabled(false);
				step.setEnabled(false);
			}
			
			play_stop.state();
		}
		
		if(e.getSource().equals(gridVisible))
		{
			System.out.println("visibility "+gridVisible.isSelected());
			
			if(gridVisible.isSelected())
			{
				dp.mesh.visibleOfMesh = true;
				
			}
			if(!gridVisible.isSelected())
			{
				dp.mesh.visibleOfMesh = false;
			}
			if(play_stop.stop)
			{
				dp.mesh.drawLines(dp.getGraphics());
			}
			else
			{
				dp.repaint();
			}
			
			
			
			
		}
		
		if(e.getSource().equals(step))
		{
			dp.playGame();
		}
		
		if(e.getActionCommand().equals("Clear"))
		{
			dp.mesh.initCells();
			dp.repaint();
		
		}
		
		if(e.getActionCommand().equals("23/3")) dp.game.rule = new Conway();
		if(e.getActionCommand().equals("1/1")) dp.game.rule = new Gnarl();
		if(e.getActionCommand().equals("/2")) dp.game.rule = new Seeds();
		if(e.getActionCommand().equals("125/36")) dp.game.rule = new Oscillator();
		if(e.getActionCommand().equals("1357/1357")) dp.game.rule = new Replicator();
		if(e.getActionCommand().equals("23/36")) dp.game.rule = new HighLife();
		if(e.getActionCommand().equals("238/357")) dp.game.rule = new PseudoLife();
		if(e.getActionCommand().equals("4567/345")) dp.game.rule = new Assimilation();
		if(e.getActionCommand().equals("5/345")) dp.game.rule = new LongLife();
		
		if(e.getSource().equals(listOfFigures))
		{
			Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
			int w=dim.width;
			int h=dim.height;
			
			switch((String)listOfFigures.getSelectedItem())
			{
			case("Breeder"):
			{
				dp.mesh.newMesh(w/5, h/5, 5);
				dp.game.breeder(10, (int)(0.8*h/5 - 100));
			}
			break;
			case("Space Ship"):
			{
				dp.mesh.newMesh(w/10, h/10, 10);
				dp.game.puffSupresor((int)(1*h/10 - 0), 10);
			}
			break;
			case("Rand Gun"):
			{
				dp.mesh.newMesh(w/4, h/4, 4);
				dp.game.randgun((int)(0.8*w/4 - 100), (int)(0.1*h/4));
			}
			break;
			case("Rake"):
			{
				dp.mesh.newMesh(w/10, h/10, 10);
				dp.game.rake((int)(0.1*w/10), (int)(0.4*h/10));
			}
			break;
			case("Glider Gun"):
			{
				dp.mesh.newMesh(w/10, h/10, 10);
				dp.game.goseprGliderGun((int)(0.1*w/10), (int)(0.1*h/10));
			}
			break;
			case("Zips"):
			{
				dp.mesh.newMesh(w/10, h/10, 10);
				dp.game.zips((int)(0.1*w/10), (int)(0.5*h/10));
			}
			break;
			case("Corder"):
			{
				dp.mesh.newMesh(w/4, h/4, 4);
				dp.game.corder((int)(0.4*w/4), (int)(0.4*h/4));
			}
			break;
			case("Hws Gun"):
			{
				dp.mesh.newMesh(w/6, h/6, 6);
				dp.game.hwsgun((int)(0.1*w/6), (int)(0.1*h/6));
			}
			break;
			}
			
			dp.repaint();
		}
		
		if(e.getSource().equals(nowy))
		{
			String step = JOptionPane.showInputDialog("Lenght of step");
			if(isInt(step))
			{
				dp.newMesh(Integer.parseInt(step));
				dp.mesh.initCells();
				dp.repaint();
			}
			
		}
		
		if(e.getSource().equals(time))
		{
			String t = JOptionPane.showInputDialog("Current delay time: "+play.sleep+" ms\n"+
					"Set new delay time [ms]");
			if(isInt(t))
			{
				play.sleep =Math.abs(Integer.parseInt(t));
			}
		}
	}




}
