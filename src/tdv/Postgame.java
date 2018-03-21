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
		/*
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
				g.setColor(player.getColor());
				g.drawImage(Sprites.getSprite(player.getPlayerNumber(), player.getDirection()), player.getPosition().x-1, player.getPosition().y-1);
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
		*/
	}

	public void update(GameContainer gc, StateBasedGame sbg, int delta)
			throws SlickException {
		elapsedTime += delta;
		if(elapsedTime > Storage.getEndWait()){
			Board.reset();
			Player.reset();
			Line.reset();
			elapsedTime = 0;
			sbg.enterState(1);
		}
	}	
	
	public int getID() {
		return 3;
	}

}