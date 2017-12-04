/*
Will contain constants for player keybindings
*/
package game;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import mapobject.Tank;

public class PlayerControls implements KeyListener{
	MapObjectHandler handler;
	Tank tank;

	public PlayerControls(MapObjectHandler handler){
		this.handler = handler;
	}

	public PlayerControls(Tank tank){
		this.tank = tank;
	}

	public void keyPressed(KeyEvent ke){
		int key = ke.getKeyCode();
		if(key == KeyEvent.VK_W) tank.setUp(true);
		if(key == KeyEvent.VK_S) tank.setDown(true);
		if(key == KeyEvent.VK_A) tank.setLeft(true);
		if(key == KeyEvent.VK_D) tank.setRight(true);

	}

	public void keyReleased(KeyEvent ke){
		int key = ke.getKeyCode();
		if(key == KeyEvent.VK_W) tank.setUp(false);
		if(key == KeyEvent.VK_S) tank.setDown(false);
		if(key == KeyEvent.VK_A) tank.setLeft(false);
		if(key == KeyEvent.VK_D) tank.setRight(false);

	}

	public void keyTyped(KeyEvent ke){}
}