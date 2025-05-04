package Rules;

import Logic.Cell;

public class Conway implements Rule{

	@Override
	public void rule(Cell c) {
		// TODO Auto-generated method stub
		int number = c.numberOfNeighboors;
		if(number < 2 || number > 3) c.state.state=0;
		if(number == 3) c.state.state=1;
	}

	
}