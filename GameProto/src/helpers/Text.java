package helpers;

import java.awt.Font;

import org.newdawn.slick.Color;
import org.newdawn.slick.TrueTypeFont;

public class Text {
	private TrueTypeFont font1;

	public void writeText(String text, int x, int y, int size){
		Font awtFont = new Font("Open Sans", Font.BOLD, 24);
		font1 = new TrueTypeFont(awtFont, true);
		
		font1.drawString(x, y, text, Color.blue);
	}
}
