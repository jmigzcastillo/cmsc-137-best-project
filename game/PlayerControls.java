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
		// for(int i=0; i<handler.getMapObjectCount(); i++){
		// 	if(handler.getMapObject(i).getID() == ID.Player){
		// 		if(key == keyEvent.VK_W) handler.setUp(true);
		// 		if(key == keyEvent.VK_S) handler.setDown(true);
		// 		if(key == keyEvent.VK_A) handler.setLeft(true);
		// 		if(key == keyEvent.VK_D) handler.setRight(true);
		// 	}
		// }
	}

	public void keyReleased(KeyEvent ke){
		int key = ke.getKeyCode();
		if(key == KeyEvent.VK_W) tank.setUp(false);
		if(key == KeyEvent.VK_S) tank.setDown(false);
		if(key == KeyEvent.VK_A) tank.setLeft(false);
		if(key == KeyEvent.VK_D) tank.setRight(false);
		// for(int i=0; i<handler.getMapObjectCount(); i++){
		// 	if(handler.getMapObject(i).getID() == ID.Player){
		// 		if(key == keyEvent.VK_W) handler.setUp(false);
		// 		if(key == keyEvent.VK_S) handler.setDown(false);
		// 		if(key == keyEvent.VK_A) handler.setLeft(false);
		// 		if(key == keyEvent.VK_D) handler.setRight(false);
		// 	}
		// }
	}

	public void keyTyped(KeyEvent ke){}
}