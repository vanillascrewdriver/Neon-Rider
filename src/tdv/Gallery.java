package tdv;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

public class Gallery extends BasicGameState{
	
	private static int elapsedTime, totalTime;
	private static int currentActionNumber;
	private static int gameNumber;
	
	public void init(GameContainer gc, StateBasedGame sbg)
			throws SlickException {
	}

	public void render(GameContainer gc, StateBasedGame sbg, Graphics g)
			throws SlickException {
		sbg.getState(2).render(gc, sbg, g);
		g.setColor(Color.white);
		g.drawString(Integer.toString(gameNumber + 1) + " / " + Integer.toString(SaveFile.getNumberGames()), Board.getWidth() + 5, Board.getHeight() - 20);
	}

	public void update(GameContainer gc, StateBasedGame sbg, int delta)
			throws SlickException {
		Input input = gc.getInput();
		String action = Storage.checkInputGallery(input);
		if(action != null){
			switch(action){
				case "next" : {
					reset(); 
					gameNumber = Math.min(gameNumber + 1, SaveFile.getNumberGames() - 1); 
					SaveFile.loadGame(gameNumber);
					break;
					}
				case "previous" : {
					reset(); 
					gameNumber = Math.max(gameNumber - 1, 0); 
					SaveFile.loadGame(gameNumber);
					break;
					}
				case "delete" : {
					SaveFile.deleteGame(gameNumber);
					reset(); 
					gameNumber = Math.min(gameNumber + 1, SaveFile.getNumberGames() - 1); 
					if(!SaveFile.loadGame(gameNumber)){
						sbg.enterState(0);
					}
					break;
					}
				case "exit" : sbg.enterState(0);
			}
		}
		
		int previousTime = totalTime;
		totalTime += delta;
		elapsedTime += delta;
		int forward = elapsedTime / Storage.getSpeed();
		elapsedTime %= Storage.getSpeed();
		
		for(int move = 0; move < forward; move++){
			if(currentActionNumber < Action.getNumberActions() && Action.getAction(currentActionNumber).getTime() <= previousTime + move * Storage.getSpeed()){
				Action a = Action.getAction(currentActionNumber);
				a.getPlayer().setDirection(a.getDirection());
				currentActionNumber++;
				Line.newTempLine(a.getPlayer(), a.getPlayer().getPosition());
			}
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
			SaveFile.loadGame(gameNumber);
			reset();
		}
	}
	
	public static void reset(){
		elapsedTime = 0;
		totalTime = 0;
		currentActionNumber = 0;
	}
	
	public int getID() {
		return 4;
	}

}