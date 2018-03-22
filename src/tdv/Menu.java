package tdv;

import java.awt.Point;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Circle;
import org.newdawn.slick.geom.Shape;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

public class Menu extends BasicGameState{
	Button startButton, galleryButton, quitButton;
	Button[][] numberButtons;
	Button resetScoreButton;
	Shape mouse;
	
	public void init(GameContainer gc, StateBasedGame sbg)
			throws SlickException {
		Sprites.loadSprites();
		Player.setup();
		Storage.setup();
		Board.setup();
		Line.reset();
		Action.setup();
		SaveFile.setup();
		
		int x = Board.getWidth() / 2;
		int y = Board.getHeight() / 5;
		startButton = new Button(Sprites.getButton("start"), new Point(x, y * 1));
		galleryButton = new Button(Sprites.getButton("gallery"), new Point(x, y * 2));
		quitButton = new Button(Sprites.getButton("quit"), new Point(x, y * 3));
		
		resetScoreButton = new Button(Sprites.getButton("resetscore"), new Point(Board.getWidth() / 6, Board.getHeight() * 3 / 4));
		
		x = Board.getWidth() / 10;
		y = Board.getHeight() / 6;
		numberButtons = new Button[4][3];
		for(int num = 1; num <= 4; num++){
			numberButtons[num - 1][0] = new Button(Sprites.getButton(Integer.toString(num)), new Point(x * 7, y * num));
			numberButtons[num - 1][1] = new Button(Sprites.getButton("ai"), new Point(x * 8, y * num));
			numberButtons[num - 1][2] = new Button(Sprites.getButton("human"), new Point(x * 10, y * num));
		}

		mouse = new Circle(0, 0, 1);
	}

	public void render(GameContainer gc, StateBasedGame sbg, Graphics g)
			throws SlickException {
		drawButton(g, startButton);
		drawButton(g, galleryButton);
		drawButton(g, quitButton);
		drawButton(g, resetScoreButton);
		for(int num = 0; num < 4; num++){
			drawButton(g, numberButtons[num][0]);
		}
		for(int num = 0; num < Storage.getNumberPlayers(); num++){
			for(int button = 1; button < 3; button++){
				drawButton(g, numberButtons[num][button]);
			}
		}
		
		g.setColor(Color.white);
		g.drawString("Scores: ", Storage.getBoardWidth() / 6 - 10, Storage.getBoardHeight() / 2);
		for(Player player : Player.getActivePlayers()){
			int xPos = Storage.getBoardWidth() / 6 - 10;
			int yPos = Storage.getBoardHeight() / 2 + 20 + 20 * player.getPlayerNumber();
			g.setColor(player.getColor());
			g.drawString("Player " + Integer.toString(player.getPlayerNumber() + 1), xPos, yPos);
			g.setColor(Color.white);
			g.drawString(": " + Integer.toString(player.getScore()), xPos + 75, yPos);
		}
	}
	
	public static void drawButton(Graphics g, Button button){
		g.drawImage(button.getImage(), button.getPosition().x, button.getPosition().y);
	}

	public void update(GameContainer gc, StateBasedGame sbg, int delta)
			throws SlickException {
		Input input = gc.getInput();
		mouse.setLocation(input.getMouseX(), input.getMouseY());
		if(input.isMousePressed(0)){
			if(mouse.intersects(startButton.getShape())){
				Game.reset();
				Board.reset();
				Player.reset();
				Line.reset();
				Action.setup();
				Player.scoreLock(false);
				input.clearKeyPressedRecord();
				sbg.enterState(1);
			}
			if(mouse.intersects(galleryButton.getShape())){
				input.clearKeyPressedRecord();
				if(SaveFile.loadGame(0)){
					Player.scoreLock(true);
					sbg.enterState(4);
				}
			}
			if(mouse.intersects(quitButton.getShape())){
				gc.exit();
			}
			if(mouse.intersects(resetScoreButton.getShape())){
				Player.resetScores();
			}
			for(int num = 0; num < 4; num++){
				if(mouse.intersects(numberButtons[num][0].getShape())){
					Storage.setNumberPlayers(num + 1);
				}
			}
			for(int num = 0; num < Storage.getNumberPlayers(); num++){
				if(mouse.intersects(numberButtons[num][1].getShape())){
					Player.getPlayer(num).isHuman(false);
				}
				if(mouse.intersects(numberButtons[num][1].getShape())){
					Player.getPlayer(num).isHuman(true);
				}
			}
		}
	}

	
	
	public int getID() {
		return 0;
	}

}