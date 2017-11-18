/**
 * Game Window Handler
 */
import java.awt.image.BufferStrategy;
import java.awt.Graphics;
import java.awt.Color;
import java.awt.image.BufferedImage;


public class Game implements Runnable{
	//
	private Thread thread;
	private BufferStrategy b;
	private Graphics g;

	//different windows
	private Window currentWindow;
	private Window launcherWindow;
	private Window lobbyWindow;
	private Window inGameWindow;
	private Window gameOverWindow;

	//window parameters
	private int width;
	private int height;
	private String title;
	private boolean isRunning;

	public Game(String title, int width, int height) {
		this.title = title;
		this.width = width;
		this.height = height;
		this.isRunning = false;
	}




	public synchronized void start(){

	}

	public synchronized void stop(){
		
	}

	public void run(){

	}


	public void render(){

	}

	public void update(){

	}
}