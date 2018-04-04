package tdv;
import java.awt.Point;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Random;

import org.newdawn.slick.Color;


public class Player {
	private static Player[] players;
	private static boolean scoreLock;
	private int playerNumber;
	private Point position, startPosition;
	private Direction direction, startDirection;
	private Color color;
	private boolean ready;
	private boolean alive;
	private boolean isHuman;
	private Player killer;
	private int score;
	
	
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
	
	public static void resetScores(){
		for(int playerNumber = 0; playerNumber < 4; playerNumber++){
			players[playerNumber].score = 0;
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
		this.isHuman = true;
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
	public int getScore(){
		return this.score;
	}
	public boolean isReady(){
		return this.ready;
	}
	public boolean isAlive(){
		return this.alive;
	}
	public boolean isHuman(){
		return this.isHuman;
	}
	
	public void setDirection(Direction direction){
		this.direction = direction;
	}
	
	public void addScore(int score){
		this.score += score;
	}
	
	public void isReady(boolean ready) {
		this.ready = ready;
	}
	public void isAlive(boolean alive){
		this.alive = alive;
	}
	public void isHuman(boolean isHuman){
		this.isHuman = isHuman;
	}
	
	public void forward(){
		this.position = Direction.addDirection(this.position, this.direction);
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
	
	public static void scoreLock(boolean lock){
		scoreLock = lock;
	}
	public static boolean scoreLock(){
		return scoreLock;
	}
	
	public Action aiMove() {
		int bestWeight = 0;
		Direction bestDirection = null;
		Random rand = new Random();
		Direction[] directions = Direction.getDirections().clone();
		for(int x = 0; x < 5; x++) {
			int a = rand.nextInt(4);
			int b = rand.nextInt(4);
			Direction temp = directions[a];
			directions[a] = directions[b];
			directions[b] = temp;
		}
		for(Direction direction : directions) {
			int weight = 0;
			
			ArrayList<Point> visited = new ArrayList<Point>();
			ArrayList<Point> stack = new ArrayList<Point>();
			stack.add(Direction.addDirection(this.position, direction));
			int reach = 0;
			while(stack.size() > 0 && reach < 100) {
				Point curr = stack.get(0);
				stack.remove(stack.size()-1);
				if(!visited.contains(curr) && Board.getValue(curr.x, curr.y) == 4) {
					visited.add(curr);
					reach++;
					for(Direction d : directions) {
						stack.add(Direction.addDirection(curr, d));
					}
				}
			}			
			weight += reach;

			int distance = 0;
			for(Player player : Player.getActivePlayers()) {
				if(player != this) {
					distance += Board.getWidth() / Math.sqrt(Math.pow(this.getPosition().x - player.getPosition().x, 2) + Math.pow(this.getPosition().y - player.getPosition().y, 2));
				}
			}
			weight += distance;
			System.out.println(weight);
			
			if(weight > bestWeight) {
				bestWeight = weight;
				bestDirection = direction;
			}
		}
		return new Action(this, bestDirection);
	}
}
