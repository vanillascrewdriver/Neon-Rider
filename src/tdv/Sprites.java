package tdv;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;


public class Sprites{
	private static String[] colors = {"Red", "Blue", "Green", "Orange"};
	private static Image[] playerSprites;
	
	public static void loadSprites() throws SlickException{
		playerSprites = new Image[4];
		for(int playerNumber = 0; playerNumber < 4; playerNumber++){
			playerSprites[playerNumber] = new Image("/Sprites/" + colors[playerNumber] + "PlayerSprite.png");
		}
	}
	
	public static Image getSprite(int playerNumber, Direction direction){
		playerSprites[playerNumber].setRotation(Direction.getAngle(direction));
		return playerSprites[playerNumber];
	}
}
