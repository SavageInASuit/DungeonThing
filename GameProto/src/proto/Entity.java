package proto;

import org.newdawn.slick.opengl.Texture;
import static helpers.Drawing.*;

public class Entity {
	private int x,y,width,height;
	private Texture texture;
	
	public Entity(int x, int y, int width, int height, String texture_name){
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		this.texture = quickLoadTexture(texture_name);
	}
	
	public void update(){
		draw();
	}
	
	private void draw(){
		drawQuadWithTex(x,y,width,height,texture);
	}
}
