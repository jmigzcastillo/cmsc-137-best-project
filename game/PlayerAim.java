package game;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import mapobject.Tank;
import mapobject.Bullet;
public class PlayerAim implements MouseListener{
	private MapObjectHandler handler;
	private Camera camera;
	private Tank tank;

	public PlayerAim(MapObjectHandler handler, Tank tank, Camera camera){
		this.handler = handler;
		this.tank = tank;
		this.camera = camera;
	}


	public void mousePressed(MouseEvent e){
		int mx = (int) (e.getX() + camera.getX());
		int my = (int) (e.getY() + camera.getY());

		handler.addMapObject(new Bullet(tank.getX()+13, tank.getY()+13, handler, camera, mx, my, tank.getDamage(), tank.isSAttack()));
	}

	public void mouseClicked(MouseEvent e){}
	public void mouseReleased(MouseEvent e){}
	public void mouseEntered(MouseEvent e){}
	public void mouseExited(MouseEvent e){}

}