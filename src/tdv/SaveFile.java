package tdv;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class SaveFile {
	
	private final static String LOCATION = "SavedGames.txt";
	private static int numberGames;
	
	public static void saveGame(){
		numberGames++;
		try{
			File f = new File(LOCATION);
			if(f.createNewFile()){
				BufferedWriter writer = new BufferedWriter(new FileWriter(LOCATION, true));
				writer.append("1\n");
				writer.close();
			} else {
				BufferedReader reader = new BufferedReader(new FileReader(LOCATION));
				String text = reader.readLine();
				text = Integer.toString(Integer.parseInt(text) + 1) + "\n";
				while(reader.ready()){
					text += reader.readLine() + "\n";
				}
				reader.close();
				BufferedWriter writer = new BufferedWriter(new FileWriter(LOCATION, false));
				writer.write(text);
				writer.close();
			}
			
			BufferedWriter writer = new BufferedWriter(new FileWriter(LOCATION, true));
			
			ArrayList<Action> actionList = Action.getActionList();
			writer.append(Integer.toString(actionList.size()) + "\n");
			
			writer.append(Integer.toString(Storage.getNumberPlayers()) + "\n");
			
			for(Action action : actionList){
				writer.append(Integer.toString(action.getTime()) + "\n");
				writer.append(Direction.toString(action.getDirection()) + "\n");
				writer.append(Integer.toString(action.getPlayer().getPlayerNumber()) + "\n");
			}
			
			writer.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	public static boolean loadGame(int gameNumber){
		try{
			File f = new File(LOCATION);
			if(f.createNewFile()){
				BufferedWriter writer = new BufferedWriter(new FileWriter(LOCATION, true));
				writer.append("0\n");
				writer.close();
			}
			BufferedReader reader = new BufferedReader(new FileReader(LOCATION));
			if(Integer.parseInt(reader.readLine()) == 0){
				reader.close();
				return false;
			}
			for(int current = 0; current < gameNumber; current++){
				String s = reader.readLine();
				if(s == null){
					reader.close();
					return false;
				}
				reader.readLine();
				for(int line = 0; line < Integer.parseInt(s); line++){
					reader.readLine();
					reader.readLine();
					reader.readLine();
				}
			}
			
			int numActions = Integer.parseInt(reader.readLine());
			int numPlayers = Integer.parseInt(reader.readLine());
			Storage.setNumberPlayers(numPlayers);
			
			Board.reset();
			Player.reset();
			Line.reset();
			Action.setup();
			
			for(int numAction = 0; numAction < numActions; numAction++){
				int time = Integer.parseInt(reader.readLine());
				Direction direction = Direction.parseDir(reader.readLine());
				Player player = Player.getPlayer(Integer.parseInt(reader.readLine()));
				Action action = new Action(player, direction, time);
				Action.addAction(action);
			}

			reader.close();
			Gallery.reset();
			return true;
		} catch (IOException e) {
			e.printStackTrace();
		}
		return false;
	}
	
	public static void deleteGame(int gameNumber){
		numberGames--;
		try{
			File f = new File(LOCATION);
			if(f.createNewFile()){
				BufferedWriter writer = new BufferedWriter(new FileWriter(LOCATION, true));
				writer.append("0\n");
				writer.close();
			} else {
				BufferedReader reader = new BufferedReader(new FileReader(LOCATION));
				String text = reader.readLine();
				if(Integer.parseInt(text) != 0){
					text = Integer.toString(Integer.parseInt(text) - 1) + "\n";
					for(int number = 0; number < gameNumber; number++){
						int numActions = Integer.parseInt(reader.readLine());
						text += Integer.toString(numActions) + "\n";
						text += reader.readLine() + "\n";
						for(int action = 0; action < numActions; action++){
							text += reader.readLine() + "\n";
							text += reader.readLine() + "\n";
							text += reader.readLine() + "\n";
						}
					}
					int numActions = Integer.parseInt(reader.readLine());
					reader.readLine();
					for(int action = 0; action < numActions; action++){
						reader.readLine();
						reader.readLine();
						reader.readLine();
					}
					while(reader.ready()){
						text += reader.readLine() + "\n";
					}
					reader.close();
					BufferedWriter writer = new BufferedWriter(new FileWriter(LOCATION, false));
					writer.write(text);
					writer.close();
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static void setup(){
		try{
			BufferedReader reader = new BufferedReader(new FileReader(LOCATION));
			numberGames = Integer.parseInt(reader.readLine());
			reader.close();
		} catch (NumberFormatException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static int getNumberGames(){
		return numberGames;
	}
}
