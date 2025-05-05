package Interface;

import Logic.Game;

public class Play implements Runnable{

	DrawPanel dp;
	Game game;
	long sleep;
	
	public Play(DrawPanel dp)
	{
		this.dp =dp;
		game = dp.game;
		sleep =20;
	}
	
	
	
	@Override
	public void run() {
		// TODO Auto-generated method stub
		while(true)
		{
			game.computeNeighboors();
			game.update();
			dp.repaint();
			
			try {
				Thread.sleep(sleep);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		
	}

}
