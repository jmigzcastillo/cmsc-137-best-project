package mapobject;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.Color;

import game.ImageLoader;
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
		// g.setColor(Color.MAGENTA);
		// g.fillRect(x, y, BLOCK_SIZE, BLOCK_SIZE);
		if (this.effect == PowerupEffect.INVISIBLE)
			g.drawImage(ImageLoader.invisibility, this.x, this.y, null);
		else if(this.effect == PowerupEffect.SATTACK)
			g.drawImage(ImageLoader.sattack, this.x, this.y, null);
		else if(this.effect == PowerupEffect.SPEEDUP)
			g.drawImage(ImageLoader.speedup, this.x, this.y, null);
		else if(this.effect == PowerupEffect.SHIELD)
			g.drawImage(ImageLoader.shield, this.x, this.y, null);
	}
	public void update(){}

	public Rectangle getBounds(){
		Rectangle bound = new Rectangle(x, y, BLOCK_SIZE, BLOCK_SIZE);

		return bound;
	}

	public PowerupEffect getPowerupEffect(){
		return this.effect;
	}
	
}