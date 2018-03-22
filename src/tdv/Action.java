package tdv;

import java.util.ArrayList;

public class Action{
	private static ArrayList<Action> actionList;
	private Player player;
	private Direction direction;
	private int time;
	
	public static void setup(){
		actionList = new ArrayList<Action>();
	}
	
	public static void addAction(Action action){
		actionList.add(action);
	}
	
	public static ArrayList<Action> getActionList(){
		return actionList;
	}
	
	public static Action getAction(int position){
		return actionList.get(position);
	}
	
	public static int getNumberActions(){
		return actionList.size();
	}
	
	public Action(Player player, Direction direction, int time){
		this.player = player;
		this.direction = direction;
		this.time = time;
	}
	
	public Action(Player player, Direction direction){
		this.player = player;
		this.direction = direction;
		this.time = -1;
	}
	
	public Player getPlayer() {
		return this.player;
	}
	public Direction getDirection() {
		return this.direction;
	}
	public int getTime(){
		return this.time;
	}
	
	public void setTime(int time){
		this.time = time;
	}
	
	public Action clone(){
		return new Action(this.player, this.direction,  this.time);
	}
}