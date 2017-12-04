package mapobject;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.Color;

import game.ImageLoader;


public class Block extends MapObject{
	

	public Block(int x, int y){
		super(x, y, ID.Block);
		this.velX = 0;
		this.velY = 0;
	}


	public void render(Graphics g){
		// g.setColor(Color.GRAY);
		// g.fillRect(x, y, BLOCK_SIZE, BLOCK_SIZE);
		g.drawImage(ImageLoader.wall, x, y, BLOCK_SIZE,BLOCK_SIZE, null, null);
	}
	public void update(){}

	public Rectangle getBounds(){
		return new Rectangle(x, y, BLOCK_SIZE, BLOCK_SIZE);
	}
	
}