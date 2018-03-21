package tdv;
import org.newdawn.slick.Input;


public class Controller {
	
	public static class Action{
		Player player;
		Direction direction;
		public Action(Player player, Direction direction){
			this.player = player;
			this.direction = direction;
		}
		
		public Player getPlayer() {
			return player;
		}
		public Direction getDirection() {
			return direction;
		}
	}
	
	public static Action checkInput(Input input){
		for(Integer key : Storage.getKeys()){
			if(input.isKeyPressed(key)){
				return Storage.getAction(key);
			}
		}
		return null;
	}
}
