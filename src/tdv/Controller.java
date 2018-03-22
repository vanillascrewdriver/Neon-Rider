package tdv;
import org.newdawn.slick.Input;


public class Controller {
	
	public static Action checkInput(Input input){
		for(Integer key : Storage.getKeys()){
			if(input.isKeyPressed(key)){
				return Storage.getAction(key).clone();
			}
		}
		return null;
	}
}
