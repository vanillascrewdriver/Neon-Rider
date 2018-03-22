package tdv;

import java.awt.Font;

import org.newdawn.slick.TrueTypeFont;

public class Fonts {
	public static TrueTypeFont getFont(int size){
		Font awtFont = new Font("Monospaced", Font.BOLD, size);
		TrueTypeFont font = new TrueTypeFont(awtFont, false);
		return font;
	}
}
