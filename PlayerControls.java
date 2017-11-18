/*
Will contain constants for player keybindings
*/

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class PlayerControls implements KeyListener{
	private int pressed=0;
	private int key;
	protected int p1Up=0;//= KeyEvent.VK_W;
	protected int p1Left=0;//=KeyEvent.VK_A;
	protected int p1Down=0;//=KeyEvent.VK_S;
	protected int p1Right=0;//=KeyEvent.VK_D;
	protected int p1Fire=0;//=KeyEvent.VK_E;
	
	public void	update(){
		if(key==KeyEvent.VK_W)
			p1Up=pressed;
		if(key==KeyEvent.VK_A)
			p1Left=pressed;
		if(key==KeyEvent.VK_S)
			p1Down=pressed;
		if(key==KeyEvent.VK_D)
			p1Right=pressed;
		if(key==KeyEvent.VK_E)
			p1Fire=pressed;
	}


	public void keyPressed(KeyEvent ke){
		pressed=1;
		key=ke.getKeyCode();
		update();
	}

	public void keyReleased(KeyEvent ke){
		pressed=0;
		key=ke.getKeyCode();
		update();
	}

	public void keyTyped(KeyEvent ke){

	}
}