package proto;

public enum TileType {
	//define tile types with texture filename, whether the player can walk on it and 
	//the texture's rotation
	Floor("stonefloor01",true, 0), 
	TopWall("stonewall01",false,0), 
	RightWall("stonewall01",false,90),
	BottomWall("stonewall01",false,180), 
	LeftWall("stonewall01",false,270), 
	CenterCenterWall("stonecenter01",false,0),
	TopCenterWall("stonecenterside01",false,0),
	RightCenterWall("stonecenterside01",false,90),
	BottomCenterWall("stonecenterside01",false,180),
	LeftCenterWall("stonecenterside01",false,270),;
	
	public String texture_name;
	public boolean walkable;
	public int angle;
	
	TileType(String texture_name, boolean walkable, int angle){
		this.texture_name = texture_name;
		this.walkable = walkable;
		this.angle = angle;
	}
}
