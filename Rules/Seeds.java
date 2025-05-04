package Rules;

import Logic.Cell;

public class Seeds implements Rule{

	@Override
	public void rule(Cell c) {
		// TODO Auto-generated method stub
		int number = c.numberOfNeighboors;
		if(number == 2) c.state.state=1;
	}

	
}
