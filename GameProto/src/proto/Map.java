package proto;

import java.util.ArrayList;

public class Map {
	private final int WIN_WIDTH, WIN_HEIGHT;
	private final float OFFSET, TILE_SIZE;
	private float spawn_x, spawn_y, player_width, player_height;
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
					map.add(new Tile(j*TILE_SIZE+OFFSET, i*TILE_SIZE, TILE_SIZE, TILE_SIZE, TileType.CenterCenterWall));
					//map.add(new Tile(j*TILE_SIZE+OFFSET, i*TILE_SIZE, TILE_SIZE, TILE_SIZE, TileType.Floor));
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
	
	public boolean CollisionX(float x, float y, float width, float height, float x_speed){
		Tile t = null;
		player_width = width;
		player_height = height;
		if(x_speed==0||x_speed<0) player_width=0;
		t = getTileTypeAtPos(x+x_speed+player_width,y);
		
		if(t==null){
			return true;
		}
		if(x_speed!=0){
			return (t.isWalkable());
		}else{
			return true;
		}
	}
	
	public boolean CollisionY(float x, float y, float width, float height, float y_speed){
		Tile t = null;
		player_width = width;
		player_height = height;
		if(y_speed==0||y_speed<0) player_height=0;
		t = getTileTypeAtPos(x,y+y_speed+player_height);
		
		if(t==null){
			return true;
		}
		if(y_speed!=0){
			return (t.isWalkable());
		}else{
			return true;
		}
	}
	
	public float getTILE_SIZE() {
		return TILE_SIZE;
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
