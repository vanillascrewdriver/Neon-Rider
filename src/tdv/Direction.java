package tdv;
import java.awt.Point;


public enum Direction {
	UP, DOWN, LEFT, RIGHT;
	
	public static Point getDirectionVector(Direction direction){
		switch(direction){
			case UP: return new Point(0, -1);
			case DOWN: return new Point(0, 1);
			case LEFT: return new Point(-1, 0);
			case RIGHT: return new Point(1, 0);
			default: return null;
		}
	}
	
	public static int getAngle(Direction direction){
		switch(direction){
			case UP: return 0;
			case DOWN: return 180;
			case LEFT: return 270;
			case RIGHT: return 90;
			default: return -1;
		}
	}
	
	public static int getPosition(Direction direction){
		switch(direction){
			case UP: return 0;
			case DOWN: return 1;
			case LEFT: return 2;
			case RIGHT: return 3;
			default: return -1;
		}
	}
	
	public static Direction[] getDirections(){
		return new Direction[] {UP, DOWN, LEFT, RIGHT};
	}
}
