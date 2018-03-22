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
	private static boolean showFPS = false;
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
	private static HashMap<Integer, Action> controls;
	private static HashMap<Integer, String> galleryControls;
	//Time data
	private static int startWait = 3000;
	private static int endWait = 3000;
	
	
	@SuppressWarnings("serial")
	public static void setup() {
		controls = new HashMap<Integer, Action>(){{
			put(Input.KEY_W, 		new Action(Player.getPlayer(0), Direction.UP));
			put(Input.KEY_S, 		new Action(Player.getPlayer(0), Direction.DOWN));
			put(Input.KEY_A, 		new Action(Player.getPlayer(0), Direction.LEFT));
			put(Input.KEY_D, 		new Action(Player.getPlayer(0), Direction.RIGHT));
			put(Input.KEY_UP, 		new Action(Player.getPlayer(1), Direction.UP));
			put(Input.KEY_DOWN, 	new Action(Player.getPlayer(1), Direction.DOWN));
			put(Input.KEY_LEFT, 	new Action(Player.getPlayer(1), Direction.LEFT));
			put(Input.KEY_RIGHT, 	new Action(Player.getPlayer(1), Direction.RIGHT));
			put(Input.KEY_NUMPAD5, 	new Action(Player.getPlayer(2), Direction.UP));
			put(Input.KEY_NUMPAD2, 	new Action(Player.getPlayer(2), Direction.DOWN));
			put(Input.KEY_NUMPAD1, 	new Action(Player.getPlayer(2), Direction.LEFT));
			put(Input.KEY_NUMPAD3, 	new Action(Player.getPlayer(2), Direction.RIGHT));
			put(Input.KEY_I, 		new Action(Player.getPlayer(3), Direction.UP));
			put(Input.KEY_K, 		new Action(Player.getPlayer(3), Direction.DOWN));
			put(Input.KEY_J, 		new Action(Player.getPlayer(3), Direction.LEFT));
			put(Input.KEY_L, 		new Action(Player.getPlayer(3), Direction.RIGHT));
		}};
		
		galleryControls = new HashMap<Integer, String>(){{
			put(Input.KEY_ESCAPE, "exit");
			put(Input.KEY_RIGHT, "next");
			put(Input.KEY_LEFT, "previous");
			put(Input.KEY_DELETE, "delete");
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
	public static boolean showFPS(){
		return showFPS;
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
	public static Action getAction(Integer input){
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
	//Gallery
	public static String checkInputGallery(Input input){
		for(int key : galleryControls.keySet()){
			if(input.isKeyPressed(key)){
				return galleryControls.get(key);
			}
		}
		return null;
	}
	
	//Sets
	public static void setNumberPlayers(int num){
		numberPlayers = num;
	}
}
