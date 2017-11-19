package mapobject;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.Color;

import game.MapObjectHandler;

public class Tank extends MapObject{

	private boolean up=false, down=false, left=false, right=false;
	private MapObjectHandler handler;
	public Tank(int x, int y, MapObjectHandler handler){
		super(x, y, ID.Tank);

		this.velX = 5;
		this.velY = 5;
		this.handler = handler;
	}

	public void update(){
		x+=velX;
		y+=velY;

		checkCollision();
		
		if(up) velY= -5;
		else if(!down) velY=0;
		
		if(down) velY = 5;
		else if(!up) velY=0;
		
		if(left) velX = -5;
		else if(!right) velX=0;
		
		if(right) velX= 5;
		else if(!left) velX=0;
		

	}



	public void render(Graphics g){
		g.setColor(Color.GREEN);
		g.fillRect(x, y, MapObject.BLOCK_SIZE, MapObject.BLOCK_SIZE);
	}

	private void checkCollision(){
		for(int i=0; i<handler.getMapObjectCount(); i++){
			MapObject temp = handler.getMapObject(i);
			
			if(temp.getId() == ID.Block){
				if(getBounds().intersects(temp.getBounds())){
					x += velX * -1;
					y += velY * -1;
					System.out.println("collision!");
				}
			}
		}
	}

	public Rectangle getBounds(){
		return new Rectangle(x,y, MapObject.BLOCK_SIZE, MapObject.BLOCK_SIZE);
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

