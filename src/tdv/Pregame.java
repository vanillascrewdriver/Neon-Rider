package tdv;
import org.newdawn.slick.*;
import org.newdawn.slick.state.*;


public class Pregame extends BasicGameState{
	
	int elapsedTime;
	boolean timer;
	
	public void init(GameContainer gc, StateBasedGame sbg)
			throws SlickException {
		Sprites.loadSprites();
		Player.setup();
		Storage.setup();
		Board.setup();
		Line.reset();
		elapsedTime = 0;
		timer = false;
	}

	public void render(GameContainer gc, StateBasedGame sbg, Graphics g)
			throws SlickException {
		g.setColor(Color.white);
		g.drawLine(Storage.getBoardWidth(), 0, Storage.getBoardWidth(), Storage.getBoardHeight());
		//Draw players
		for(Player player : Player.getActivePlayers()){
			g.setColor(player.getColor());
			g.drawImage(Sprites.getSprite(player.getPlayerNumber(), player.getDirection()), player.getPosition().x, player.getPosition().y);
		}
		//Draw info board
		g.setColor(Color.white);
		g.drawString("Status: ", Storage.getBoardWidth() + 5, 20);
		for(Player player : Player.getActivePlayers()){
			int xPos = Storage.getBoardWidth() + 5;
			int yPos = 40 + 20 * player.getPlayerNumber();
			g.setColor(player.getColor());
			g.drawString("Player " + Integer.toString(player.getPlayerNumber() + 1), xPos, yPos);
			g.setColor(Color.white);
			g.drawString("is", xPos + 80, yPos);
			if(player.isReady()){
				g.setColor(Color.green);
				g.drawString("ready", xPos + 110, yPos);
			} else {
				g.setColor(Color.red);
				g.drawString("not ready", xPos + 110, yPos);
			}
		}
		
		if(timer){
			g.setColor(Color.white);
			g.drawString(Integer.toString((Storage.getStartWait() - elapsedTime) / 1000 + 1), Board.getWidth() / 2, Board.getHeight() / 2);
		}
	}

	public void update(GameContainer gc, StateBasedGame sbg, int delta)
			throws SlickException {
		Input input = gc.getInput();
		Controller.Action action = Controller.checkInput(input);
		if(action != null) {
			action.getPlayer().isReady(true);
		}
		
		boolean ready = true;
		for(Player player: Player.getActivePlayers()) {
			ready &= player.isReady();
		}
		if(ready) {
			timer = true;
			elapsedTime += delta;
			if(elapsedTime > Storage.getStartWait()){
				elapsedTime = 0;
				timer = false;
				sbg.enterState(2);
			}
		}
	}

	
	
	public int getID() {
		return 1;
	}

}