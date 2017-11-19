import java.awt.Graphics;
import java.awt.Rectangle;


public class Powerup extends MapObject{
	
	private PowerupEffect effect;
	private int duration = 10;

	public Powerup(int x, int y, PowerupEffect effect){
		super(x, y);
		this.velX = 0;
		this.velY = 0;
		this.effect = effect;
	}


	public void render(Graphics g){}
	public void update(){}

	public Rectangle getBounds(){
		Rectangle bound = new Rectangle();

		return bound;
	}
	
}