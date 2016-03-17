package proto;

import org.lwjgl.input.Keyboard;
import org.newdawn.slick.opengl.Texture;

import static helpers.Drawing.*;
import static helpers.Clock.*;

public class Player {
	private float x,y,speed,width,height, size;
	private float[] pos;
	private int angle,movement_x,movement_y;
	private boolean up_down, right_down, down_down, left_down,moving;
	private Texture texture;
	private Map map;
	
	public Player(float[] pos, float size, float speed, Map map){
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
		this.width = size;
		this.height = size;
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
					movement_x = (int) Math.floor(Delta() * speed);
				}
			}
			if(Keyboard.getEventKey() == Keyboard.KEY_LEFT){
				if(left_down){
					left_down = false;
					movement_x = 0;
				}else{
					left_down = true;
					movement_x = -(int) Math.floor(Delta() * speed);
				}
			}
			if(Keyboard.getEventKey() == Keyboard.KEY_UP){
				if(up_down){
					up_down = false;
					movement_y = 0;
				}else{
					up_down = true;
					movement_y = -(int) Math.floor(Delta() * speed);
				}
			}
			if(Keyboard.getEventKey() == Keyboard.KEY_DOWN){
				if(down_down){
					down_down = false;
					movement_y = 0;
				}else{
					down_down = true;
					movement_y = (int) Math.floor(Delta() * speed);
				}
			}
		}
		while(!map.CollisionX(x, y,width,height, movement_x)&&moving){
			if(movement_x!=0){
				if(movement_x<0){
					movement_x++;
				}else{
					movement_x--;
				}
			}
			
			if(movement_x==0&&movement_y==0){
				moving=false;
			}
		}
		while(!map.CollisionY(x, y,width,height, movement_y)&&moving){
			if(movement_y!=0){
				if(movement_y<0){
					movement_y++;
				}else{
					movement_y--;
				}
			}
			
			if(movement_x==0&&movement_y==0){
				moving=false;
			}
		}
		System.out.println(movement_x);
		if(right_down){
			x+=movement_x;
		}
		if(left_down){
			x+=movement_x;
		}
		if(up_down){
			y+=movement_y;
		}
		if(down_down){
			y+=movement_y;
		}
		moving = true;
		
		
		drawQuadWithTexRot(texture,x,y,width,height,angle);
	}
}
