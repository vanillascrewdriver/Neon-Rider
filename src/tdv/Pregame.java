package tdv;
import org.newdawn.slick.*;
import org.newdawn.slick.state.*;


public class Pregame extends BasicGameState{
	
	int elapsedTime;
	boolean timer;
	
	public void init(GameContainer gc, StateBasedGame sbg)
			throws SlickException {
		elapsedTime = 0;
		timer = false;
	}

	public void render(GameContainer gc, StateBasedGame sbg, Graphics g)
			throws SlickException {
		g.setColor(Color.white);
		g.drawLine(Storage.getBoardWidth(), 0, Storage.getBoardWidth(), Storage.getBoardHeight());
		//Draw players
		for(Player player : Player.getActivePlayers()){
			g.drawImage(Sprites.getSprite(player.getPlayerNumber(), player.getDirection()), player.getPosition().x, player.getPosition().y, player.getColor());
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
		
		g.setColor(Color.white);
		g.drawString("Scores: ", Storage.getBoardWidth() + 5, Storage.getBoardHeight() / 2);
		for(Player player : Player.getActivePlayers()){
			int xPos = Storage.getBoardWidth() + 5;
			int yPos = Storage.getBoardHeight() / 2 + 20 + 20 * player.getPlayerNumber();
			g.setColor(player.getColor());
			g.drawString("Player " + Integer.toString(player.getPlayerNumber() + 1), xPos, yPos);
			g.setColor(Color.white);
			g.drawString(": " + Integer.toString(player.getScore()), xPos + 75, yPos);
		}
		
		g.setColor(Color.white);
		int x = Board.getWidth() + 5;
		int y = Board.getHeight() - 80;
		g.drawString("Controls:", x, y);
		switch(Storage.getNumberPlayers()){
			case 4: {g.setColor(Player.getPlayer(3).getColor()); g.drawString("Player 4: IJKL", x, y + 60);};
			case 3: {g.setColor(Player.getPlayer(2).getColor()); g.drawString("Player 3: Numpad 5123", x, y + 45);};
			case 2: {g.setColor(Player.getPlayer(1).getColor()); g.drawString("Player 2: Arrows", x , y + 30);};
			case 1: {g.setColor(Player.getPlayer(0).getColor()); g.drawString("Player 1: WASD", x, y + 15);};
		}
		
		if(timer){
			g.setColor(Color.white);
			g.drawString(Integer.toString((Storage.getStartWait() - elapsedTime) / 1000 + 1), Board.getWidth() / 2, Board.getHeight() / 2);
		}
	}

	public void update(GameContainer gc, StateBasedGame sbg, int delta)
			throws SlickException {
		Input input = gc.getInput();
		Action action = Controller.checkInput(input);
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