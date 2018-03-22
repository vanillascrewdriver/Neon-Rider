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
	private static int pause;
	
	public void init(GameContainer gc, StateBasedGame sbg)
			throws SlickException {
	}

	public void render(GameContainer gc, StateBasedGame sbg, Graphics g)
			throws SlickException {
		sbg.getState(2).render(gc, sbg, g);
			
		int x = Board.getWidth() + 5;
		int y = Board.getHeight() - 80;
		g.setColor(Color.black);
		g.fillRect(x, y, Storage.getSideBarWidth(), 100);
		g.setColor(Color.white);
		g.drawString(Integer.toString(gameNumber + 1) + " / " + Integer.toString(SaveFile.getNumberGames()), Board.getWidth() + Storage.getSideBarWidth() - 70, Board.getHeight() - 20);
		g.drawString("Controls: ", x, y);
		g.drawString("Next:   Right Arrow", x, y + 15);
		g.drawString("Prev:   Left Arrow", x , y + 30);
		g.drawString("Delete: Delete", x, y + 45);
		g.drawString("Back:   Escape", x, y + 60);
	}

	public void update(GameContainer gc, StateBasedGame sbg, int delta)
			throws SlickException {
		Input input = gc.getInput();
		String action = Storage.checkInputGallery(input);
		if(action != null){
			switch(action){
				case "next" : {
					reset(); 
					gameNumber = (gameNumber + 1) % SaveFile.getNumberGames(); 
					SaveFile.loadGame(gameNumber);
					break;
					}
				case "previous" : {
					reset(); 
					gameNumber = (gameNumber - 1 + SaveFile.getNumberGames()) % SaveFile.getNumberGames(); 
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
			pause -= delta;
			if(pause < 0){
				SaveFile.loadGame(gameNumber);
				reset();
			}
		}
	}
	
	public static void reset(){
		elapsedTime = 0;
		totalTime = 0;
		currentActionNumber = 0;
		pause = Storage.getEndWait();
	}
	
	public int getID() {
		return 4;
	}

}