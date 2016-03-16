package proto;

import org.lwjgl.input.Keyboard;
import org.newdawn.slick.opengl.Texture;

import static helpers.Drawing.*;
import static helpers.Clock.*;

public class Player {
	private float x,y,speed,movement_x,movement_y;
	private float[] pos;
	private int angle;
	private boolean up_down, right_down, down_down, left_down;
	private Texture texture;
	private Map map;
	
	public Player(float[] pos, float speed, Map map){
		this.pos = pos;
		this.x = pos[0];
		this.y = pos[1];
		this.speed = speed;
		this.map = map;
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
					movement_x = 0;
				}else{
					right_down = true;
					movement_x = Delta() * speed;
				}
			}
			if(Keyboard.getEventKey() == Keyboard.KEY_LEFT){
				if(left_down){
					left_down = false;
					movement_x = 0;
				}else{
					left_down = true;
					movement_x = Delta() * speed;
				}
			}
			if(Keyboard.getEventKey() == Keyboard.KEY_UP){
				if(up_down){
					up_down = false;
					movement_y = 0;
				}else{
					up_down = true;
					movement_y = Delta() * speed;
				}
			}
			if(Keyboard.getEventKey() == Keyboard.KEY_DOWN){
				if(down_down){
					down_down = false;
					movement_y = 0;
				}else{
					down_down = true;
					movement_y = Delta() * speed;
				}
			}
		}
		while(map.Collision(x, y, movement_x, movement_y)){
			if(movement_x!=0){
				movement_x--;
			}else if(movement_y!=0){
				movement_y--;
			}
		}
		if(right_down){
			x+=movement_x;
		}
		if(left_down){
			x-=movement_x;
		}
		if(up_down){
			y-=movement_y;
		}
		if(down_down){
			y+=movement_y;
		}

		drawQuadWithTexRot(texture,x,y,64,64,angle);
	}
}
