package Rules;

import Logic.Cell;

public class LongLife implements Rule{

	@Override
	public void rule(Cell c) {
		// TODO Auto-generated method stub
		int number = c.numberOfNeighboors;
		if(number <3 || number >5) c.state.state=0;
		if(number == 3|| number ==4|| number==5) c.state.state=1;
	}

	
}
