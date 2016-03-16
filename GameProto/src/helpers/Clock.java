package helpers;

import org.lwjgl.opengl.Display;

public class Clock {
	
	private static long lastFrame;
	private static float d;
	
	private static boolean paused = false;
	
	public static float getDelta() {
		long currentTime = System.currentTimeMillis();
		int delta = (int) (currentTime - lastFrame);
		lastFrame = currentTime;
		//if(delta> 0.05f){
		//	return 0.05f;
		//}
		return delta * 0.001f;
	}
	
	public static float Delta(){
		if(paused){
			return 0f;
		}else{
			return d;
		}
	}

	public static void update(){
		d = getDelta();
		Display.setTitle(1/d + "fps");
	}
	
	public static void pause(){
		if(paused){
			paused = false;
		}else{
			paused = true;
		}
	}
}
