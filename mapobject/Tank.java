import java.awt.Graphics;
import java.awt.Rectangle;

public class Tank extends MapObject{


	public Tank(int x, int y,int velX, int velY){
		super(x, y);

		this.velX = velX;
		this.velY = velY;
	}

	public void update(){}
	public void render(Graphics g){}
	public Rectangle getBounds(){
		return new Rectangle();
	}
}

