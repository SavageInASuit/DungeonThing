package proto;

import java.util.ArrayList;

public class Map {
	private final int WIN_WIDTH, WIN_HEIGHT;
	private final float TILE_SIZE;
	private char[][] rawMap;
	private ArrayList<Tile> map;
	
	public Map(int WIN_WIDTH, int WIN_HEIGHT){
		this.WIN_WIDTH = WIN_WIDTH;
		this.WIN_HEIGHT = WIN_HEIGHT;
		this.rawMap = new char[][]{{'F','W','W','W','W','W','W','W','W','W','W','W','W','W','W','W','W','W','W','F'},
								  {'W','F','F','F','F','F','F','F','F','F','F','F','F','F','F','F','F','F','F','W'},
								  {'W','F','F','F','F','F','F','F','F','F','F','F','F','F','F','F','F','F','F','W'},
								  {'W','F','F','F','F','F','F','F','F','F','F','F','F','F','F','F','F','F','F','W'},
								  {'W','F','F','F','F','F','F','F','F','F','F','F','F','F','F','F','F','F','F','W'},
								  {'W','F','F','F','F','F','F','F','F','F','F','F','F','F','F','F','F','F','F','W'},
								  {'W','F','F','F','F','F','F','F','F','F','F','F','F','F','F','F','F','F','F','W'},
								  {'W','F','F','F','F','F','F','F','F','F','F','F','F','F','F','F','F','F','F','W'},
								  {'W','F','F','F','F','F','F','F','F','F','F','F','F','F','F','F','F','F','F','W'},
								  {'W','F','F','F','F','F','F','F','F','F','F','F','F','F','F','F','F','F','F','W'},
								  {'W','F','F','F','F','F','F','F','F','F','F','F','F','F','F','F','F','F','F','W'},
								  {'W','F','F','F','F','F','F','F','F','F','F','F','F','F','F','F','F','F','F','W'},
								  {'W','F','F','F','F','F','F','F','F','F','F','F','F','F','F','F','F','F','F','W'},
								  {'W','F','F','F','F','F','F','F','F','F','F','F','F','F','F','F','F','F','F','W'},
								  {'W','F','F','F','F','F','F','F','F','F','F','F','F','F','F','F','F','F','F','W'},
								  {'W','F','F','F','F','F','F','F','F','F','F','F','F','F','F','F','F','F','F','W'},
								  {'W','F','F','F','F','F','F','F','F','F','F','F','F','F','F','F','F','F','F','W'},
								  {'W','F','F','F','F','F','F','F','F','F','F','F','F','F','F','F','F','F','F','W'},
								  {'W','F','F','F','F','F','F','F','F','F','F','F','F','F','F','F','F','F','F','W'},
								  {'F','W','W','W','W','W','W','W','W','W','W','W','W','W','W','W','W','W','W','F'}};
		this.TILE_SIZE = WIN_HEIGHT/rawMap.length;
		parseRawMap(rawMap);
	}
	
	private ArrayList<Tile> parseRawMap(char[][] rawMap){
		map = new ArrayList<Tile>();
		for(int i = 0; i< rawMap.length; i++){
			for(int j = 0; j<rawMap[0].length; j++){
				switch(rawMap[i][j]){
				case 'F':
					map.add(new Tile(j*TILE_SIZE, i*TILE_SIZE, TILE_SIZE, TILE_SIZE, TileType.Floor));
					break;
				case 'W':
					if(j==0 && (i!=0 || i!=rawMap.length)){
						map.add(new Tile(j*TILE_SIZE, i*TILE_SIZE, TILE_SIZE, TILE_SIZE, TileType.LeftWall));
					}else if(i==0){
						map.add(new Tile(j*TILE_SIZE, i*TILE_SIZE, TILE_SIZE, TILE_SIZE, TileType.TopWall));
					}else if(i==rawMap.length){
						map.add(new Tile(j*TILE_SIZE, i*TILE_SIZE, TILE_SIZE, TILE_SIZE, TileType.BottomWall));
					}else if(j==rawMap[0].length && (i!=0 || i!=rawMap.length)){
						map.add(new Tile(j*TILE_SIZE, i*TILE_SIZE, TILE_SIZE, TILE_SIZE, TileType.RightWall));
					}
					break;
				}
			}
		}
		
		return map;
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
}
