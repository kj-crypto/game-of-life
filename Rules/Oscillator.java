package Rules;

import Logic.Cell;

public class Oscillator implements Rule{

	@Override
	public void rule(Cell c) {
		// TODO Auto-generated method stub
		int number = c.numberOfNeighboors;
		if(number ==0 || number==4 || number > 6) c.state.state=0;
		if(number == 3|| number==6) c.state.state=1;
	}

}
