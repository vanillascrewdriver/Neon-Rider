package tdv;
import org.newdawn.slick.*;
import org.newdawn.slick.state.*;


public class Game extends BasicGameState{
	
	private static int elapsedTime, totalTime;
	
	public void init(GameContainer gc, StateBasedGame sbg)
			throws SlickException {
		
	}

	public void render(GameContainer gc, StateBasedGame sbg, Graphics g)
			throws SlickException {
		//Draw board
		for(Line line : Line.getLines()){
			g.setColor(line.getPlayer().getColor());
			g.drawLine(line.getStart().x, line.getStart().y, line.getEnd().x, line.getEnd().y);
		}
		g.setColor(Color.white);
		g.drawLine(Storage.getBoardWidth(), 0, Storage.getBoardWidth(), Storage.getBoardHeight());
		
		//Draw players
		for(Player player : Player.getActivePlayers()){
			if(player.isAlive()){
				g.drawImage(Sprites.getSprite(player.getPlayerNumber(), player.getDirection()), player.getPosition().x-1, player.getPosition().y-1, player.getColor());
			}
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
			if(player.isAlive()){
				g.drawString("is alive.", xPos + 80, yPos);
			} else if(player.getKiller() == player){
				g.drawString("killed themself", xPos + 80, yPos);
			} else if(player.getKiller() != null) {
				g.drawString("was killed by", xPos + 80, yPos);
				g.setColor(player.getKiller().getColor());
				g.drawString("Player " + Integer.toString(player.getKiller().getPlayerNumber() + 1), xPos + 205, yPos);
			} else {
				g.drawString("died", xPos + 80, yPos);
			}
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
	}

	public void update(GameContainer gc, StateBasedGame sbg, int delta)
			throws SlickException {
		totalTime += delta;
		elapsedTime += delta;
		int forward = elapsedTime / Storage.getSpeed();
		elapsedTime %= Storage.getSpeed();
		
		Input input = gc.getInput();
		Action action = Controller.checkInput(input);
		doAction(action);
		
		if(forward > 0) {
			for(Player player : Player.getActivePlayers()) {
				if(!player.isHuman()) {
					action = player.aiMove();
					doAction(action);
				}
			}
		}
		
		for(int move = 0; move < forward; move++){
			for(Player player : Player.getActivePlayers()){
				if(player.isAlive()){
					player.forward();
					Line.changeTempLine(player, player.getPosition());
				}
			}
			Board.update();
		}
		
		int numAlive = 0;
		for(Player player : Player.getActivePlayers()){
			numAlive += player.isAlive() ? 1 : 0;
		}
		if(numAlive == 0){
			SaveFile.saveGame();
			sbg.enterState(3);
		}
	}
	
	public static void doAction(Action action) {
		if(action != null && action.getDirection() != null) {
			action.setTime(totalTime);
			if((Direction.getDirectionVector(action.getPlayer().getDirection()).x + Direction.getDirectionVector(action.getDirection()).x) % 2 != 0){
				action.getPlayer().setDirection(action.getDirection());
				Line.newTempLine(action.getPlayer(), action.getPlayer().getPosition());
				Action.addAction(action);
			}
		}
	}
	
	public static void reset(){
		elapsedTime = 0;
		totalTime = 0;
	}
	
	public int getID() {
		return 2;
	}

}