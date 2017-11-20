package mapobject;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.Color;

import game.MapObjectHandler;
import game.Camera;

import java.util.Random;

public class Bullet extends MapObject{

	private MapObjectHandler handler;
	private Camera camera;


	private static Random randomizer = new Random();
	private static final int BULLET_SIZE = 6;

	public Bullet(int x, int y, MapObjectHandler handler, Camera camera, int mx, int my){
		super(x, y, ID.Bullet);

		this.handler = handler;
		this.camera = camera;

		// int slope = (mx - x) / (my - y);
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
					int rand = randomizer.nextInt(150);
					if(rand == 1){
						handler.addMapObject(new Powerup(temp.getX(), temp.getY(), PowerupEffect.INVISIBLE));
					}
					else if(rand == 2){
						handler.addMapObject(new Powerup(temp.getX(), temp.getY(), PowerupEffect.SATTACK));
					}
					else if(rand == 3){
						handler.addMapObject(new Powerup(temp.getX(), temp.getY(), PowerupEffect.SHIELD));
					}
					else if(rand == 4){
						handler.addMapObject(new Powerup(temp.getX(), temp.getY(), PowerupEffect.SPEEDUP));
					}
					handler.removeMapObject(temp);
					handler.removeMapObject(this);
					
					
				}
			}
			else if(temp.getId() == ID.InvincibleBlock){
				if(getBounds().intersects(temp.getBounds())){
					handler.removeMapObject(this);
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