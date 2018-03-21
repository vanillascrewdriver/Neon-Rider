package tdv;
import java.awt.Point;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;


public class Line {
	private Point start, end;
	private Player player;
	private static Set<Line> permanentLines;
	private static HashMap<Player, Line> tempLines;
	
	public static void reset(){
		permanentLines = new HashSet<Line>();
		clearTempLines();
		for(Player player : Player.getActivePlayers()){
			tempLines.put(player, new Line(player.getPosition(), player.getPosition(), player));
		}
	}
	
	public static void addPermanentLine(Line line){
		permanentLines.add(line);
	}
	
	public static void changeTempLine(Player player, Point end){
		tempLines.get(player).setEnd(end);
	}
	
	public static void newTempLine(Player player, Point position){
		permanentLines.add(tempLines.get(player));
		tempLines.put(player, new Line(position, position, player));
	}
	
	public static void clearTempLines(){
		tempLines = new HashMap<Player, Line>();
		for(Player player : Player.getPlayers()){
			tempLines.put(player, null);
		}
	}
	
	public static Set<Line> getLines(){
		Set<Line> allLines = new HashSet<Line>();
		allLines.addAll(permanentLines);
		allLines.addAll(tempLines.values());
		allLines.remove(null);
		return allLines;
	}
	
	
	public Line(Point start, Point end, Player player){
		this.start = start;
		this.end = end;
		this.player = player;
	}
	
	public Point getStart(){
		return this.start;
	}
	public Point getEnd(){
		return this.end;
	}
	public Player getPlayer(){
		return this.player;
	}
	
	public void setEnd(Point end){
		this.end = end;
	}
	
}
