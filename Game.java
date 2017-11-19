/**
 * Game Window Handler
 */
import java.awt.image.BufferStrategy;
import java.awt.Graphics;
import java.awt.Color;
import java.awt.image.BufferedImage;
import java.awt.Canvas;

public class Game extends Canvas implements Runnable{
	//essential stuff
	private Thread thread;
	private BufferStrategy b;
	private Graphics g;


	//different windows
	private GameLobby lobby;

	private State currentState;

	

	//window parameters
	private int width;
	private int height;
	private String title;
	private boolean isRunning;

	//will handle map objects
	private MapObjectHandler handler;

	public Game(String title, int width, int height) {
		this.title = title;
		this.width = width;
		this.height = height;
		new Display(width, height, title, this);

		currentState = State.GAME;
		
	}




	public synchronized void start(){
		isRunning = true;
		thread = new Thread(this);
		thread.start();
	}

	public synchronized void stop(){
		isRunning = false;
		try{
			thread.join();
		}catch(InterruptedException e){
			e.printStackTrace();
		}
	}

	public void run(){
		//update 60 times per second
		this.requestFocus();
		long lastTime = System.nanoTime();
		double amountOfTicks = 60.0;
		double ns = 100000000 / amountOfTicks;
		double delta = 0;
		long timer = System.currentTimeMillis();
		int frames = 0;
		while(isRunning){
			long now = System.nanoTime();
			delta += (now - lastTime) / ns;
			lastTime = now;
			while(delta >= 1){
				update();
				delta --;
			}
			render();
			frames++;

			if (System.currentTimeMillis() - timer > 1000){
				timer += 1000;
				frames = 0;
			}
		}
		stop();
	}


	public void render(){
		//set buffer count to 3
		b = this.getBufferStrategy();
		if (b==null){
			this.createBufferStrategy(3);
			return;
		}

		g = b.getDrawGraphics();

		if(currentState == State.GAME){
			//render things here
			handler.render(g);
			//
		}
		else if(currentState == State.LOBBY){
			//render things here
			lobby.render(g);
			//
		}

		g.dispose();
		b.show();
	}

	public void update(){
		if(currentState == State.GAME){
			handler.update();
		}
		else if(currentState == State.LOBBY){
			lobby.update();
		}
	}

	public static void main(String args[]){
		new Game("What the tank?!", 800, 600);
	}
}