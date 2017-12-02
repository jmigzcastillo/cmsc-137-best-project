package mapobject;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.Color;

import game.MapObjectHandler;
import game.ImageLoader;
public class Tank extends MapObject{

	
	private boolean up=false, down=false, left=false, right=false;
	private MapObjectHandler handler;
	
	private PowerupEffect currentBuff = PowerupEffect.NONE;
	private int shield = 0;
	private int movementSpeed = 5;
	private int hp = 20;
	private int damage = 5;
	private boolean isInvisible = false;
	private long buffDuration = 0;
	private long buffEnd;

	public Tank(int x, int y, MapObjectHandler handler){
		super(x, y, ID.Tank);

		this.velX = movementSpeed;
		this.velY = movementSpeed;
		this.handler = handler;
	}

	public void update(){
		x+=velX;
		y+=velY;

		checkCollision();
		
		if(up) velY= -movementSpeed;
		else if(!down) velY=0;
		
		if(down) velY = movementSpeed;
		else if(!up) velY=0;
		
		if(left) velX = -movementSpeed;
		else if(!right) velX=0;
		
		if(right) velX= movementSpeed;
		else if(!left) velX=0;
		
		if(this.currentBuff != PowerupEffect.NONE){
			this.buffDuration = this.buffEnd - System.currentTimeMillis();
			printBuffStatus();
			if(buffDuration <= 0){
				normalize();
			}
		}
		
	}



	public void render(Graphics g){
		if (currentBuff == PowerupEffect.INVISIBLE) g.setColor(Color.YELLOW);
		else g.setColor(Color.GREEN);
		// g.fillRect(x, y, BLOCK_SIZE, BLOCK_SIZE);
		g.drawImage(ImageLoader.tankB, x,y, null);
		if(currentBuff == PowerupEffect.SHIELD){
			g.setColor(Color.blue);
			g.drawRect(x,y, BLOCK_SIZE, BLOCK_SIZE);
		}
		
	}

	private void checkCollision(){
		for(int i=0; i<handler.getMapObjectCount(); i++){
			MapObject temp = handler.getMapObject(i);

			if(temp.getId() == ID.Block || temp.getId() == ID.InvincibleBlock){
				if(getBounds().intersects(temp.getBounds())){
					x += velX * -1;
					y += velY * -1;
					// System.out.println("collision!");
				}
			}

			else if(temp.getId() == ID.Powerup){
				if(getBounds().intersects(temp.getBounds())){
					Powerup buff = (Powerup) temp;
					//remove current buff(if there is one), then absorb new buff
					normalize();
					absorb(buff);
					handler.removeMapObject(temp);
				}
			}
		}
	}

	private void absorb(Powerup buff){
		this.currentBuff = buff.getPowerupEffect();
		
		buffEnd = System.currentTimeMillis() + 10000;
		buffDuration = 10;
		if(this.currentBuff == PowerupEffect.INVISIBLE){
			this.isInvisible = true;

		}
		else if(this.currentBuff == PowerupEffect.SATTACK){
			this.damage = 8;
		}
		else if(this.currentBuff == PowerupEffect.SPEEDUP){
			this.movementSpeed = 8;
		}
		else if(this.currentBuff == PowerupEffect.SHIELD){
			this.shield = 10;
		}
		
	}

	private void printBuffStatus(){
		System.out.println("Current buff:"+ currentBuff);
		System.out.println("Duration:"+ buffDuration);

	}

	private void normalize(){
		if (this.currentBuff == PowerupEffect.NONE) return;
		if(this.currentBuff == PowerupEffect.INVISIBLE){
			this.isInvisible = false;
		}
		else if(this.currentBuff == PowerupEffect.SATTACK){
			this.damage = 5;
		}
		else if(this.currentBuff == PowerupEffect.SPEEDUP){
			this.movementSpeed = 5;
		}
		else if(this.currentBuff == PowerupEffect.SHIELD){
			this.shield = 0;
		}
		this.currentBuff = PowerupEffect.NONE;
		this.buffDuration = 0;
		this.buffEnd = 0;
	}

	public Rectangle getBounds(){
		return new Rectangle(x,y, MapObject.BLOCK_SIZE, MapObject.BLOCK_SIZE);
	}

	public void setUp(boolean val){
		this.up = val;
	}
	public void setDown(boolean val){
		this.down = val;
	}
	public void setLeft(boolean val){
		this.left = val;
	}
	public void setRight(boolean val){
		this.right = val;
	}

	public boolean getUp(){
		return this.up;
	}
	public boolean getDown(){
		return this.down;
	}
	public boolean getLeft(){
		return this.left;
	}
	public boolean getRight(){
		return this.right;
	}
}

