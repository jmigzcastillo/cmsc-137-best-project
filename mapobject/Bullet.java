package mapobject;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.Color;

import game.MapObjectHandler;
import game.Camera;

import java.util.Random;

public class Bullet extends MapObject{

	private MapObjectHandler handler;


	private static Random randomizer = new Random();
	private static final int BULLET_SIZE = 6;

	private int damage;
	private boolean strong;
	private int playerID;
	public Bullet(int x, int y, MapObjectHandler handler, int playerID,  int mx, int my, int damage, boolean strong){
		super(x, y, ID.Bullet);
		this.damage = damage;
		this.strong = strong;
		this.playerID = playerID;
		this.handler = handler;


		// int slope = (mx - x) / (my - y);
		velX = (mx - x) / 10;
		velY = (my - y) / 10;
		
	}


	public void update(){
		x += velX;
		y += velY;

		for(int i=0; i<handler.getMapObjectCount(); i++){
			MapObject temp = handler.getMapObject(i);

			if(getBounds().intersects(temp.getBounds())){
				if(temp.getId() == ID.Block){
					
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
				else if(temp.getId() == ID.InvincibleBlock){
					handler.removeMapObject(this);
				}
				else if(temp.getId() == ID.Tank){
					Tank player = (Tank) temp;
					if (player.getPlayerID()!=playerID){
						player.takeDamage(this.damage, playerID);
						handler.removeMapObject(this);
					}
				}
				
			}


				
			
		}
	}
	public void render(Graphics g){
		if (this.strong)	g.setColor(Color.RED);
		else g.setColor(Color.BLUE);
		g.fillOval(x, y, BULLET_SIZE, BULLET_SIZE);
	}

	public Rectangle getBounds(){
		return new Rectangle(x, y, BULLET_SIZE, BULLET_SIZE);
	}
}