package proto;

import org.newdawn.slick.opengl.Texture;

import static helpers.Drawing.*;

public class Tile {
	private float x, y, width, height,angle;
	
	private boolean walkable;
	private TileType tileType;
	private Texture texture;
	
	public float getHeight() {
		return height;
	}

	public void setHeight(float height) {
		this.height = height;
	}

	public boolean isWalkable() {
		return walkable;
	}

	public void setWalkable(boolean walkable) {
		this.walkable = walkable;
	}

	public float getX() {
		return x;
	}

	public float getY() {
		return y;
	}

	public float getWidth() {
		return width;
	}

	public Texture getTexture() {
		return texture;
	}

	public Tile(float x, float y, float width, float height, TileType tileType){
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		this.tileType = tileType;
		this.texture = quickLoadTexture(tileType.texture_name);
		this.walkable = tileType.walkable;
		this.angle = tileType.angle;
		if(tileType==TileType.Floor){
			angle = (int) (Math.floor(Math.random()*5) * 90);
		}
	}
	
	public void update(){
		//Update the state of the tile and then draw
		
		draw();
	}
	
	private void draw(){
		//Draw tile with the given texture at a given angle, depending on its position on the map;
		//At the top 0 rotation, left side 90 degrees etc.
		drawQuadWithTexRot(texture,x,y,width,height,angle);
	}
}
