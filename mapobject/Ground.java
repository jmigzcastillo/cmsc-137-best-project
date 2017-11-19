package mapobject;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.Color;


public class Ground extends MapObject{
	

	public Ground(int x, int y){
		super(x, y);
		this.velX = 0;
		this.velY = 0;
	}

	public void update(){}
	public void render(Graphics g){
		g.setColor(Color.LIGHT_GRAY);
		g.fillRect(x, y, BLOCK_SIZE, BLOCK_SIZE);
	}

	public Rectangle getBounds(){
		return null;
	}
}