package proto;

import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.GL11;

import helpers.Text;
import static helpers.Drawing.*;

public class Game {
	
	private final int WIN_WIDTH,WIN_HEIGHT;
	private float gl_offset;
	private Player player;
	private Text textHelper;
	private Entity entity;
	private Map map;
	
	public Game(){
		WIN_WIDTH =  Display.getWidth();
		WIN_HEIGHT = Display.getHeight();
		textHelper = new Text();
		map = new Map(WIN_WIDTH,WIN_HEIGHT);
		player = new Player(map.getSpawn(),map.getTILE_SIZE(),600.0f, map);
		gl_offset = 0;
	}
	
	public void update(){
		map.update();
    	player.update();
	}
}

