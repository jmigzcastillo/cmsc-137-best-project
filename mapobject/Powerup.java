package mapobject;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.Color;


public class Powerup extends MapObject{
	
	private PowerupEffect effect;
	private int duration = 10;

	public Powerup(int x, int y, PowerupEffect effect){
		super(x, y, ID.Powerup);
		this.velX = 0;
		this.velY = 0;
		this.effect = effect;
	}


	public void render(Graphics g){
		g.setColor(Color.MAGENTA);
		g.fillRect(x, y, BLOCK_SIZE, BLOCK_SIZE);
	}
	public void update(){}

	public Rectangle getBounds(){
		Rectangle bound = new Rectangle(x, y, BLOCK_SIZE, BLOCK_SIZE);

		return bound;
	}
	
}