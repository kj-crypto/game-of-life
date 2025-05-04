package Rules;

import Logic.Cell;

public class Replicator implements Rule{

	@Override
	public void rule(Cell c) {
		int number = c.numberOfNeighboors;
		if(number == 0|| number ==2|| number==4|| number ==6 || number ==8) c.state.state=0;
		if(number == 1|| number ==3|| number==5|| number ==7) c.state.state=1;
	}

	
}