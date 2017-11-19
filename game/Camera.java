package game;

import mapobject.MapObject;

public class Camera{

	private float x, y;


	public Camera(float x, float y){
		this.x = x;
		this.y = y;
	}

	public void update(MapObject object){
		x += ((object.getX() - x) - 500) * 0.05f;
		y += ((object.getY() - y) - 250) * 0.05f;
	}

	public float getX(){
		return this.x;
	}

	public float getY(){
		return this.y;
	}

	public void setX(float x){
		this.x = x;
	}

	public void setY(float y){
		this.y = y;
	}
}