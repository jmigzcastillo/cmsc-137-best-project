package mapobject;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.Color;


public class Tank extends MapObject{

	private boolean up=false, down=false, left=false, right=false;

	public Tank(int x, int y){
		super(x, y);

		this.velX = 5;
		this.velY = 5;
	}

	public void update(){
		if(up) y-=velY;
		else if(!down) y-=0;
		
		if(down) y+=velY;
		else if(!up) y-=0;
		
		if(left) x-=velX;
		else if(!right) x-=0;
		
		if(right) x+=velY;
		else if(!left) x-=0;
		

	}
	public void render(Graphics g){
		g.setColor(Color.GREEN);
		g.fillRect(x, y, MapObject.BLOCK_SIZE, MapObject.BLOCK_SIZE);
	}
	public Rectangle getBounds(){
		return new Rectangle();
	}

	public void setUp(boolean val){
		this.up = val;
	}
	public void setDown(boolean val){
		this.down = val;
	}
	public void setLeft(boolean val){
		this.left = val;
	}
	public void setRight(boolean val){
		this.right = val;
	}

	public boolean getUp(){
		return this.up;
	}
	public boolean getDown(){
		return this.down;
	}
	public boolean getLeft(){
		return this.left;
	}
	public boolean getRight(){
		return this.right;
	}
}

