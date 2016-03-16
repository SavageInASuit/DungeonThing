package proto;

import org.lwjgl.opengl.Display;
import helpers.Text;
import static helpers.Drawing.*;

public class Game {
	
	private final int WIN_WIDTH,WIN_HEIGHT;
	private Player player;
	private Text textHelper;
	private Entity entity;
	private Map map;
	
	public Game(){
		WIN_WIDTH =  Display.getWidth();
		WIN_HEIGHT = Display.getHeight();
		textHelper = new Text();
		player = new Player(200.0f,200.0f,700.0f);
		entity = new Entity(500,500,100,100,"star");
		map = new Map(WIN_WIDTH,WIN_HEIGHT);
		//System.out.println("Window dimensions: " + Integer.toString(x) + " x " + Integer.toString(y));
	}
	
	public void update(){
		drawRect(400,400,200,200);
		map.update();
		entity.update();
    	player.update();
	}
}

