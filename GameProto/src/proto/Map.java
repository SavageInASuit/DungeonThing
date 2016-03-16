package proto;

import java.util.ArrayList;

public class Map {
	private final int WIN_WIDTH, WIN_HEIGHT;
	private final float OFFSET, TILE_SIZE;
	private float spawn_x, spawn_y;
	private char[][] rawMap;
	private ArrayList<Tile> map;
	
	public Map(int WIN_WIDTH, int WIN_HEIGHT){
		this.WIN_WIDTH = WIN_WIDTH;
		this.WIN_HEIGHT = WIN_HEIGHT;
		this.rawMap = new char[][]{{'F','W','W','W','W','W','W','W','W','W','W','W','W','W','W','W','W','W','W','F'},
								  {'W','F','F','F','F','F','F','F','F','F','F','F','F','F','F','F','F','F','F','W'},
								  {'W','F','F','S','F','F','F','F','F','F','F','F','F','F','F','F','F','F','F','W'},
								  {'W','F','F','F','F','F','F','F','F','F','F','F','F','F','F','F','F','F','F','W'},
								  {'W','F','F','F','F','F','F','F','F','F','F','F','F','F','F','F','F','F','F','W'},
								  {'W','F','F','F','F','F','F','F','F','F','F','F','F','F','F','F','F','F','F','W'},
								  {'W','F','F','F','F','F','F','F','F','F','F','F','F','F','F','F','F','F','F','W'},
								  {'W','F','F','F','F','F','F','F','F','F','F','F','F','F','F','F','F','F','F','W'},
								  {'W','F','F','F','F','F','F','F','F','F','F','F','F','F','F','F','F','F','F','W'},
								  {'W','F','F','F','C','C','C','F','F','F','F','F','F','F','F','F','F','F','F','W'},
								  {'W','F','F','F','C','F','C','F','F','F','F','F','F','F','F','F','F','F','F','W'},
								  {'W','F','F','F','C','C','C','F','F','F','F','F','F','F','F','F','F','F','F','W'},
								  {'W','F','F','F','F','F','F','F','F','F','F','F','F','F','F','F','F','F','F','W'},
								  {'W','F','F','F','F','F','F','F','F','F','F','F','F','F','F','F','F','F','F','W'},
								  {'W','F','F','F','F','F','F','F','F','F','F','F','F','F','F','F','F','F','F','W'},
								  {'W','F','F','F','F','F','F','F','F','F','F','F','F','F','F','F','F','F','F','W'},
								  {'W','F','F','F','F','F','F','F','F','F','F','F','F','F','F','F','F','F','F','W'},
								  {'W','F','F','F','F','F','F','F','F','F','F','F','F','F','F','F','F','F','F','W'},
								  {'W','F','F','F','F','F','F','F','F','F','F','F','F','F','F','F','F','F','F','W'},
								  {'F','W','W','W','W','W','W','W','W','W','W','W','W','W','W','W','W','W','W','F'}};
		this.TILE_SIZE = WIN_HEIGHT/rawMap.length;
		this.OFFSET = (WIN_WIDTH - (rawMap[0].length*TILE_SIZE))/2;
		parseRawMap(rawMap);
	}
	
	private ArrayList<Tile> parseRawMap(char[][] rawMap){
		map = new ArrayList<Tile>();
		for(int i = 0; i< rawMap.length; i++){
			for(int j = 0; j<rawMap[0].length; j++){
				switch(rawMap[i][j]){
				case 'F':
					map.add(new Tile(j*TILE_SIZE+OFFSET, i*TILE_SIZE, TILE_SIZE, TILE_SIZE, TileType.Floor));
					break;
				case 'S':
					spawn_x = (j*TILE_SIZE)+OFFSET;
					spawn_y = i*TILE_SIZE;
					map.add(new Tile(j*TILE_SIZE+OFFSET, i*TILE_SIZE, TILE_SIZE, TILE_SIZE, TileType.Floor));
					break;
				case 'W':
					if(j==0 && (i!=0 || i!=(rawMap.length-1))){
						map.add(new Tile(j*TILE_SIZE+OFFSET, i*TILE_SIZE, TILE_SIZE, TILE_SIZE, TileType.LeftWall));
					}else if(i==0){
						map.add(new Tile(j*TILE_SIZE+OFFSET, i*TILE_SIZE, TILE_SIZE, TILE_SIZE, TileType.TopWall));
					}else if(i==(rawMap.length-1)){
						map.add(new Tile(j*TILE_SIZE+OFFSET, i*TILE_SIZE, TILE_SIZE, TILE_SIZE, TileType.BottomWall));
					}else if(j==(rawMap[0].length-1) && (i!=0 || i!=(rawMap.length)-1)){
						map.add(new Tile(j*TILE_SIZE+OFFSET, i*TILE_SIZE, TILE_SIZE, TILE_SIZE, TileType.RightWall));
					}
					break;
				case 'C':
					map.add(new Tile(j*TILE_SIZE+OFFSET, i*TILE_SIZE, TILE_SIZE, TILE_SIZE, TileType.Floor));
					map.add(new Tile(j*TILE_SIZE+OFFSET, i*TILE_SIZE, TILE_SIZE, TILE_SIZE, TileType.CenterCenterWall));
				}
			}
		}
		
		return map;
	}
	
	public float[] getSpawn(){
		float[] pos = {spawn_x, spawn_y};
		return pos;
	}
	
	public void update(){
		//Update the map and draw
		draw();
	}
	
	private void draw(){
		for(Tile t : map){
			t.update();
		}
	}
	
	public boolean Collision(float x, float y, float x_speed, float y_speed){
		Tile t = getTileTypeAtPos(x+x_speed,y+y_speed);
		if(t==null){
			return false;
		}
		return (!t.isWalkable());
	}
	
	private Tile getTileTypeAtPos(float x_pos, float y_pos){
		Tile t = null;
		for(Tile tile : map){
			if(x_pos>=tile.getX()&&x_pos<=(tile.getX()+tile.getWidth())){
				if(y_pos>=tile.getY()&&y_pos<=(tile.getY()+tile.getHeight())){
					return tile;
				}
			}
		}
		return t;
	}
}
