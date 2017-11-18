import java.awt.Graphics;
import java.awt.Rectangle;


public class Block extends MapObject{
	

	public Block(int x, int y){
		super(x, y);
		this.velX = 0;
		this.velY = 0;
	}


	public void render(Graphics g){}
	public void update(){}

	public Rectangle getBounds(){
		Rectangle bound = new Rectangle();

		return bound;
	}
	
}