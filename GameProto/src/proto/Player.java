package proto;

import org.lwjgl.input.Keyboard;
import org.newdawn.slick.opengl.Texture;

import static helpers.Drawing.*;
import static helpers.Clock.*;

public class Player {
	private float x,y,speed;
	private int angle;
	private boolean up_down, right_down, down_down, left_down;
	private Texture texture;
	
	public Player(float x, float y, float speed){
		this.x = x;
		this.y = y;
		this.speed = speed;
		this.up_down = false;
		this.right_down = false;
		this.down_down = false;
		this.left_down = false;
		this.texture = quickLoadTexture("T");
		this.angle = 0;
	}
	
	public void update(){
		while(Keyboard.next()){
			if(Keyboard.getEventKey() == Keyboard.KEY_RIGHT){
				if(right_down){
					right_down = false;
				}else{
					right_down = true;
				}
			}
			if(Keyboard.getEventKey() == Keyboard.KEY_LEFT){
				if(left_down){
					left_down = false;
				}else{
					left_down = true;
				}
			}
			if(Keyboard.getEventKey() == Keyboard.KEY_UP){
				if(up_down){
					up_down = false;
				}else{
					up_down = true;
				}
			}
			if(Keyboard.getEventKey() == Keyboard.KEY_DOWN){
				if(down_down){
					down_down = false;
				}else{
					down_down = true;
				}
			}
		}
		
		if(right_down){
			x+=Delta() * speed;
		}
		if(left_down){
			x-=Delta() * speed;
		}
		if(up_down){
			y-=Delta() * speed;
		}
		if(down_down){
			y+=Delta() * speed;
		}

		drawQuadWithTexRot(texture,x,y,64,64,angle);
	}
}
