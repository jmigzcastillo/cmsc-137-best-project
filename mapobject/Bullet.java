package mapobject;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.Color;

import game.MapObjectHandler;
import game.Camera;

public class Bullet extends MapObject{

	private MapObjectHandler handler;
	private Camera camera;

	private static final int BULLET_SIZE = 6;

	public Bullet(int x, int y, MapObjectHandler handler, Camera camera, int mx, int my){
		super(x, y, ID.Bullet);

		this.handler = handler;
		this.camera = camera;

		velX = (mx - x) / 10;
		velY = (my - y) / 10;
		
	}


	public void update(){
		x += velX;
		y += velY;

		for(int i=0; i<handler.getMapObjectCount(); i++){
			MapObject temp = handler.getMapObject(i);

			if(temp.getId() == ID.Block){
				if(getBounds().intersects(temp.getBounds())){
					handler.removeMapObject(this);
					handler.removeMapObject(temp);
				}
			}
				
					
			
		}
	}
	public void render(Graphics g){
		g.setColor(Color.RED);
		g.fillOval(x, y, BULLET_SIZE, BULLET_SIZE);
	}

	public Rectangle getBounds(){
		return new Rectangle(x, y, BULLET_SIZE, BULLET_SIZE);
	}
}