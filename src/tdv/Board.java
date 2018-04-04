package tdv;
import java.util.Arrays;

public class Board {
	private static Integer[][] board;
	private static int width, height;
	
	public static void setup(){
		width = Storage.getBoardWidth();
		height = Storage.getBoardHeight();
		board = new Integer[height][width];
		for(Integer[] row : board) {
			Arrays.fill(row,  4);
		}
	}
	
	public static void reset(){
		for(Integer[] row : board) {
			Arrays.fill(row,  4);
		}
	}
	
	//Get
	public static int getWidth(){
		return width;
	}
	public static int getHeight(){
		return height;
	}
	public static int getValue(int x, int y){
		if(x < 0 || x >= width || y < 0 || y >= height) {
			return -1;
		}
		return board[y][x];
	}
	
	public static void update(){
		for(Player player : Player.getActivePlayers()){
			if(player.isAlive()){
				int x = player.getPosition().x;
				int y = player.getPosition().y;
				if(getValue(x, y) == -1){
					player.kill(Player.getPlayer(4));
				} else if(getValue(x, y) != 4){
					player.kill(Player.getPlayer(getValue(x, y))); 
					int score = -1;
					for(Player player2 : Player.getActivePlayers()){
						if(!player2.isAlive()){
							score += 1;
						}
					}
					if(!Player.scoreLock()){
						player.addScore(score);
					}
				} else {
					board[y][x] = player.getPlayerNumber();
				}
			}
		}
	}
}
