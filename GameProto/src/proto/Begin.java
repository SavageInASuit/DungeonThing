package proto;

import static helpers.Drawing.initGl;
import helpers.StateManager;
import helpers.Clock;

import org.lwjgl.input.Keyboard;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.GL11;

public class Begin {
	
	public static int WIN_WIDTH = 1440;
	public static int WIN_HEIGHT = 900;
	
	public static void main(String[] args){
		new Begin().start();
	}
	
	public void start(){
		initGl(WIN_WIDTH,WIN_HEIGHT);
		
		while (!Display.isCloseRequested()) {
			GL11.glClear(GL11.GL_COLOR_BUFFER_BIT | GL11.GL_DEPTH_BUFFER_BIT);
			
			if(Keyboard.isKeyDown(Keyboard.KEY_ESCAPE) || Keyboard.isKeyDown(Keyboard.KEY_Q)){
				Display.destroy();
				System.exit(0);
			}
			if(Keyboard.isKeyDown(Keyboard.KEY_P)){
				Clock.pause();
			}
			
			StateManager.update();
			Clock.update();
			
			Display.update();
            Display.sync(60);
        }
         
        Display.destroy();
	}
}
