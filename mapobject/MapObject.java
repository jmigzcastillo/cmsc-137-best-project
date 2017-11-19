package mapobject;

import java.awt.Rectangle;
import java.awt.Graphics;



public abstract class MapObject{
	protected int x, y;
	protected float velX, velY;

	public static final int BLOCK_SIZE = 30;


	public MapObject(int x, int y){
		this.x = x;
		this.y = y;
	}

	public int getX(){
		return this.x;
	}

	public int getY(){
		return this.y;
	}

	public float getVelX(){
		return this.velX;
	}

	public float getVelY(){
		return this.velY;
	}

	public abstract void update();
	public abstract void render(Graphics g);
	public abstract Rectangle getBounds();
}