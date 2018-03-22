package tdv;
import java.util.HashMap;

import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;


public class Sprites{
	private static HashMap<String, Image> buttons;
	private static Image playerSprite;
	
	@SuppressWarnings("serial")
	public static void loadSprites() throws SlickException{
		playerSprite = new Image("/Sprites/PlayerSprite.png");
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
		playerSprite.setRotation(Direction.getAngle(direction));
		return playerSprite;
	}
	public static Image getButton(String name){
		return buttons.get(name);
	}
}
