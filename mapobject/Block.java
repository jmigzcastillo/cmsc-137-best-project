package mapobject;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.Color;



public class Block extends MapObject{
	

	public Block(int x, int y){
		super(x, y);
		this.velX = 0.0;
		this.velY = 0.0;
	}


	public void render(Graphics g){
		g.setColor(Color.GRAY);
		g.fillRect(x, y, MapObject.BLOCK_SIZE, MapObject.BLOCK_SIZE);
	}
	public void update(){}

	public Rectangle getBounds(){
		Rectangle bound = new Rectangle();

		return bound;
	}
	
}