package Rules;

import Logic.Cell;

public class HighLife implements Rule{

	@Override
	public void rule(Cell c) {
		// TODO Auto-generated method stub
		int number = c.numberOfNeighboors;
		if(number < 2 || number==4 || number ==5 || number > 6) c.state.state=0;
		if(number == 3|| number == 6) c.state.state=1;
	}

	
}
