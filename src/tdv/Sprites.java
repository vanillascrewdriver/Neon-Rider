package tdv;
import java.util.HashMap;

import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;


public class Sprites{
	private static String[] colors = {"Red", "Blue", "Green", "Orange"};
	private static HashMap<String, Image> buttons;
	private static Image[] playerSprites;
	
	@SuppressWarnings("serial")
	public static void loadSprites() throws SlickException{
		playerSprites = new Image[4];
		for(int playerNumber = 0; playerNumber < 4; playerNumber++){
			playerSprites[playerNumber] = new Image("/Sprites/" + colors[playerNumber] + "PlayerSprite.png");
		}
		buttons = new HashMap<String, Image>(){{
			put("start", 	new Image("/Sprites/StartButton.png"));
			put("gallery", 	new Image("/Sprites/GalleryButton.png"));
			put("quit", 	new Image("/Sprites/QuitButton.png"));
			put("1", 		new Image("/Sprites/1Button.png"));
			put("2", 		new Image("/Sprites/2Button.png"));
			put("3", 		new Image("/Sprites/3Button.png"));
			put("4", 		new Image("/Sprites/4Button.png"));
			put("ai", 		new Image("/Sprites/AIButton.png"));
			put("human", 	new Image("/Sprites/HumanButton.png"));
			put("resetscore", new Image("/Sprites/ResetScoreButton.png"));
		}};
	}
	
	public static Image getSprite(int playerNumber, Direction direction){
		playerSprites[playerNumber].setRotation(Direction.getAngle(direction));
		return playerSprites[playerNumber];
	}
	public static Image getButton(String name){
		return buttons.get(name);
	}
}
