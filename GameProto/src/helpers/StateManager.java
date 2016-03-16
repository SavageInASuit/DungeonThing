package helpers;

import proto.Game;

public class StateManager {
	public enum State{
		MAINMENU,GAME
	}
	
	public static State state = State.GAME;
	public static Game game;
	
	public static void update(){
		switch(state){
		case GAME:
			if(game==null){
				game = new Game();
			}
			game.update();
			break;
		case MAINMENU:
			System.out.println("Going to main menu!");
		}
	}
}
