package helpers;

import java.io.IOException;
import java.io.InputStream;

import org.lwjgl.LWJGLException;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.GL11;
import org.newdawn.slick.opengl.Texture;
import org.newdawn.slick.opengl.TextureLoader;
import org.newdawn.slick.util.ResourceLoader;

public class Drawing {
	
	private static float tex_width, tex_height;
	
	public static void initGl(int WIN_WIDTH, int WIN_HEIGHT){
		try {
			Display.setFullscreen(true);
            Display.setDisplayMode(Display.getDesktopDisplayMode());//new DisplayMode(WIN_WIDTH,WIN_HEIGHT));
            Display.create();
        } catch (LWJGLException e) {
            e.printStackTrace();
            System.exit(0);
        }
        
		//GL11.glShadeModel(GL11.GL_FLAT);
		// init OpenGL here
		GL11.glMatrixMode(GL11.GL_PROJECTION);
		GL11.glLoadIdentity();
		GL11.glOrtho(0, WIN_WIDTH, WIN_HEIGHT, 0, 1, -1);
		GL11.glMatrixMode(GL11.GL_MODELVIEW);
		GL11.glEnable(GL11.GL_TEXTURE_2D);
		GL11.glEnable(GL11.GL_BLEND);
		GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
	}
	
	public static void drawRect(int x, int y, int w, int h){
		GL11.glColor3f(1.0f,1.0f,1.0f);
		
        GL11.glBegin(GL11.GL_QUADS);
	        GL11.glVertex2f(x,y);
	        GL11.glVertex2f(x+w,y);
	        GL11.glVertex2f(x+w,y+h);
	        GL11.glVertex2f(x,y+h);
        GL11.glEnd();
	}
	
	public static Texture loadTexture(String path, String fileType){
		Texture tex = null;
		
		InputStream in = ResourceLoader.getResourceAsStream(path);
		try {
			tex = TextureLoader.getTexture(fileType, in);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return tex;
	}
	
	public static Texture quickLoadTexture(String name){
		Texture tex = null;
		
		tex = loadTexture("res/" + name + ".png", "PNG");
		
		return tex;
	}
	
	public static void drawQuadWithTex(float x, float y, float w, float h, String texture_name){
		//GL11.glColor4f(1.0f,1.0f,1.0f,1.0f);
		
		Texture t = quickLoadTexture(texture_name);
		t.bind();
		
		tex_width = w;
		tex_height = h;
		
        GL11.glBegin(GL11.GL_QUADS);
        	GL11.glTexCoord2f(0.0f, 0.0f);
	        GL11.glVertex2f(x,y);
	        GL11.glTexCoord2f(1.0f, 0.0f);
	        GL11.glVertex2f(x+tex_width,y);
	        GL11.glTexCoord2f(1.0f, 1.0f);
	        GL11.glVertex2f(x+tex_width,y+tex_height);
	        GL11.glTexCoord2f(0.0f, 1.0f);
	        GL11.glVertex2f(x,y+tex_width);
        GL11.glEnd();
	}
	
	public static void drawQuadWithTex(float x, float y, float w, float h, Texture tex){
		tex.bind();
		
		tex_width = w;
		tex_height = h;
		
        GL11.glBegin(GL11.GL_QUADS);
        	GL11.glTexCoord2f(0.0f, 0.0f);
	        GL11.glVertex2f(x,y);
	        GL11.glTexCoord2f(1.0f, 0.0f);
	        GL11.glVertex2f(x+tex_width,y);
	        GL11.glTexCoord2f(1.0f, 1.0f);
	        GL11.glVertex2f(x+tex_width,y+tex_height);
	        GL11.glTexCoord2f(0.0f, 1.0f);
	        GL11.glVertex2f(x,y+tex_width);
        GL11.glEnd();
		
	}
	
	public static void drawQuadWithTexRot(Texture tex, float x, float y, float width, float height, float angle){
		tex.bind();
		GL11.glTranslatef(x+width/2,y+height/2,0);
		GL11.glRotatef(angle,0,0,1);
		GL11.glTranslatef(-width/2,-height/2,0);
		GL11.glBegin(GL11.GL_QUADS);
			GL11.glTexCoord2f(0,0);
			GL11.glVertex2f(0,0);
			GL11.glTexCoord2f(1,0);
			GL11.glVertex2f(width,0);
			GL11.glTexCoord2f(1,1);
			GL11.glVertex2f(width,height);
			GL11.glTexCoord2f(0,1);
			GL11.glVertex2f(0,height);
		GL11.glEnd();
		GL11.glLoadIdentity();
	}
}
