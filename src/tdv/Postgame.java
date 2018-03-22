package tdv;
import org.newdawn.slick.*;
import org.newdawn.slick.state.*;


public class Postgame extends BasicGameState{
	
	private static int elapsedTime;
	
	public void init(GameContainer gc, StateBasedGame sbg)
			throws SlickException {
		elapsedTime = 0;
	}

	public void render(GameContainer gc, StateBasedGame sbg, Graphics g)
			throws SlickException {
		sbg.getState(2).render(gc, sbg, g);
	}

	public void update(GameContainer gc, StateBasedGame sbg, int delta)
			throws SlickException {
		elapsedTime += delta;
		if(elapsedTime > Storage.getEndWait()){
			Board.reset();
			Player.reset();
			Line.reset();
			elapsedTime = 0;
			sbg.enterState(0);
		}
	}	
	
	public int getID() {
		return 3;
	}

}