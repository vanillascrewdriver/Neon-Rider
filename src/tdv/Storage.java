package tdv;
import java.awt.Point;
import java.util.HashMap;
import java.util.Set;

import org.newdawn.slick.Color;
import org.newdawn.slick.Input;


public class Storage {
	//Main data
	private static int numberPlayers = 2;
	private static int sideBarWidth = 300;
	private static int speed = 5; //Move forward every [speed] milliseconds
	//Board data
		private static int boardWidth = 1200, boardHeight = 600;
	//Player data
	private static Point[] playerStartPositions = {	new Point(boardWidth / 3, boardHeight / 3), 
													new Point(2 * boardWidth / 3, 2 * boardHeight / 3), 
													new Point(2 * boardWidth / 3, boardHeight / 3), 
													new Point(boardWidth / 3, 2 * boardHeight / 3)};
	private static Direction[] playerStartDirections = {Direction.RIGHT, Direction.LEFT, Direction.LEFT, Direction.RIGHT};
	private static Color[] playerColors = {Color.red, Color.blue, Color.green, Color.orange, Color.black};
	
	//Controller data
	private static HashMap<Integer, Controller.Action> controls;
	//Time data
	private static int startWait = 3000;
	private static int endWait = 3000;
	
	
	@SuppressWarnings("serial")
	public static void setup() {
		controls = new HashMap<Integer, Controller.Action>(){{
			put(Input.KEY_W, 		new Controller.Action(Player.getPlayer(0), Direction.UP));
			put(Input.KEY_S, 		new Controller.Action(Player.getPlayer(0), Direction.DOWN));
			put(Input.KEY_A, 		new Controller.Action(Player.getPlayer(0), Direction.LEFT));
			put(Input.KEY_D, 		new Controller.Action(Player.getPlayer(0), Direction.RIGHT));
			put(Input.KEY_UP, 		new Controller.Action(Player.getPlayer(1), Direction.UP));
			put(Input.KEY_DOWN, 	new Controller.Action(Player.getPlayer(1), Direction.DOWN));
			put(Input.KEY_LEFT, 	new Controller.Action(Player.getPlayer(1), Direction.LEFT));
			put(Input.KEY_RIGHT, 	new Controller.Action(Player.getPlayer(1), Direction.RIGHT));
			put(Input.KEY_NUMPAD5, 	new Controller.Action(Player.getPlayer(2), Direction.UP));
			put(Input.KEY_NUMPAD2, 	new Controller.Action(Player.getPlayer(2), Direction.DOWN));
			put(Input.KEY_NUMPAD1, 	new Controller.Action(Player.getPlayer(2), Direction.LEFT));
			put(Input.KEY_NUMPAD3, 	new Controller.Action(Player.getPlayer(2), Direction.RIGHT));
			put(Input.KEY_I, 		new Controller.Action(Player.getPlayer(3), Direction.UP));
			put(Input.KEY_K, 		new Controller.Action(Player.getPlayer(3), Direction.DOWN));
			put(Input.KEY_J, 		new Controller.Action(Player.getPlayer(3), Direction.LEFT));
			put(Input.KEY_L, 		new Controller.Action(Player.getPlayer(3), Direction.RIGHT));
		}};
	}
	
	//Get methods
	//Main
	public static int getNumberPlayers(){
		return numberPlayers;
	}
	public static int getSpeed(){
		return speed;
	}
	//Player
	public static Color getColor(int playerNumber){
		return playerColors[playerNumber];
	}
	public static Point getStartPosition(int playerNumber){
		return playerStartPositions[playerNumber];
	}
	public static Direction getStartDirection(int playerNumber){
		return playerStartDirections[playerNumber];
	}
	//Board
	public static int getBoardWidth(){
		return boardWidth;
	}
	public static int getBoardHeight(){
		return boardHeight;
	}
	public static int getSideBarWidth(){
		return sideBarWidth;
	}
	//Controller
	public static Controller.Action getAction(Integer input){
		return controls.get(input);
	}
	public static Set<Integer> getKeys(){
		return controls.keySet();
	}
	//Time
	public static int getStartWait(){
		return startWait;
	}
	public static int getEndWait(){
		return endWait;
	}
}
