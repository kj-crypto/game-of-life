package Interface;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JComboBox;
import javax.swing.JPanel;

public class ListPanel extends JPanel implements ActionListener {

	JComboBox<String> list;
	boolean enabled ;

	public ListPanel(JPanel jp)
	{
		String tab[] ={"Breeder","Space Ship","Rand Gun","Puffer", "Rake", "Glier Gun", "Zips","Corder","Hws Gun"};
		list = new JComboBox<>(tab);
		list.setVisible(true);
		list.setEnabled(false);
		enabled = false;
		jp.add(list);
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		e.getSource().equals(list);
	}

}
