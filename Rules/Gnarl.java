package Rules;

import Logic.Cell;

public class Gnarl implements Rule{

	@Override
	public void rule(Cell c) {
		// TODO Auto-generated method stub
		int number = c.numberOfNeighboors;
		if(number < 1 || number > 1) c.state.state=0;
		if(number == 1) c.state.state=1;
	}

	
}