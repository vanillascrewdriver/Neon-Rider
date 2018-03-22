package tdv;
import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;


public class Main extends StateBasedGame{

	public Main(String name) {
		super(name);
		this.addState(new Menu());
		this.addState(new Pregame());
		this.addState(new Game());
		this.addState(new Postgame());
		this.addState(new Gallery());
	}

	public static void main(String[] args) throws SlickException {
		AppGameContainer app = new AppGameContainer(new Main("Neon Rider"));
		//app.setMouseGrabbed(true);
		app.setShowFPS(Storage.showFPS());

		app.setDisplayMode(Storage.getBoardWidth() + Storage.getSideBarWidth(), Storage.getBoardHeight(), false);
		app.setAlwaysRender(true);

		app.start();
	}

	public void initStatesList(GameContainer gc) throws SlickException {
		this.getState(0).init(gc, this);
		this.getState(1).init(gc, this);
		this.getState(2).init(gc, this);
		this.getState(3).init(gc, this);
		this.getState(4).init(gc, this);
		this.enterState(0);

	}

}