package tdv;
import java.awt.Point;
import java.util.Arrays;

import org.newdawn.slick.Color;


public class Player {
	private static Player[] players;
	private int playerNumber;
	private Point position, startPosition;
	private Direction direction, startDirection;
	private Color color;
	private boolean ready;
	private boolean alive;
	private Player killer;
	
	
	public static void setup(){
		players = new Player[4];
		for(int playerNumber = 0; playerNumber < 4; playerNumber++){
			players[playerNumber] = new Player(playerNumber);
			players[playerNumber].resetPlayer();
		}
	}
	
	public static void reset(){
		for(int playerNumber = 0; playerNumber < 4; playerNumber++){
			players[playerNumber].resetPlayer();
		}
	}
	
	public static Player getPlayer(int playerNumber){
		if(playerNumber >= players.length){
			return null;
		} else {
			return players[playerNumber];
		}
	}
	
	public static Player[] getPlayers(){
		return players;
	}
	
	public static Player[] getActivePlayers() {
		return Arrays.copyOfRange(players, 0, Storage.getNumberPlayers());
	}
	
	
	public Player(int playerNumber){
		this.playerNumber = playerNumber;
		this.startPosition = Storage.getStartPosition(playerNumber);
		this.startDirection = Storage.getStartDirection(playerNumber);
		this.color = Storage.getColor(playerNumber);
		this.ready = false;
	}
	
	public int getPlayerNumber(){
		return this.playerNumber;
	}
	public Point getPosition(){
		return this.position;
	}
	public Direction getDirection(){
		return this.direction;
	}
	public Color getColor(){
		return this.color;
	}
	public Player getKiller(){
		return this.killer;
	}
	public boolean isReady(){
		return this.ready;
	}
	public boolean isAlive(){
		return this.alive;
	}
	
	public void setDirection(Direction direction){
		this.direction = direction;
	}
	
	public void isReady(boolean ready) {
		this.ready = ready;
	}
	public void isAlive(boolean alive){
		this.alive = alive;
	}
	
	public void forward(){
		this.position = new Point(	this.position.x + Direction.getDirectionVector(this.direction).x, 
									this.position.y + Direction.getDirectionVector(this.direction).y);
	}
	public void kill(Player player){
		this.alive = false;
		this.killer = player;
	}
	
	public void resetPlayer(){
		this.position = this.startPosition;
		this.direction = this.startDirection;
		this.alive = true;
		this.ready = false;
		this.killer = null;
	}
	
	public enum KeyDirection{
		UP, DOWN, LEFT, RIGHT;
	}
}