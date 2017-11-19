/**
 * Game Window Handler
 */

package game;

//import display related stuff
import java.awt.image.BufferStrategy;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Color;
import java.awt.image.BufferedImage;
import java.awt.Canvas;

//import file i/o
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
//import randomizer
import java.util.Random;

//import map objects
import mapobject.*;



public class Game extends Canvas implements Runnable{
	//essential stuff
	private Thread thread;
	private BufferStrategy b;
	private Graphics g;

	

	//different windows
	// private GameLauncher launcher;
	// private GameLobby lobby;

	private State currentState;

	

	//window parameters
	private int width;
	private int height;
	private String title;
	private boolean isRunning;

	//will handle map objects
	private MapObjectHandler handler;
	private BufferedReader reader;

	//camera for pov effect
	private Camera camera;
	
	//config file
	// private Config config;

	//randomizer
	private Random randomizer = new Random();

	//player data
	private int playerCount;
	
	public Game(String title, int width, int height) {
		this.title = title;
		this.width = width;
		this.height = height;
		new Display(width, height, title, this);

		currentState = State.GAME;

		//initialize map object handler
		handler = new MapObjectHandler();

		//add objects to handler by initializing map
		mapInitialize(2);

		//spawn player
		Tank player = new Tank(300,300, handler);
		handler.addMapObject(player);
		playerCount = handler.getMapObjectCount();
		//initialize camera
		camera = new Camera(300, 300);

		//add key listener for controls
		this.addKeyListener(new PlayerControls(player));

		start();
	}

	//require int in the future to identify which map to initialize
	private void mapInitialize(int mapID){
		System.out.println("Initializing map...");
		String path = null;

		PowerupEffect[] powerups = PowerupEffect.values();
		int x = 200;
		int y = 0;
		if(mapID == 1) path = "maps/small.in";
		else if(mapID == 2) path = "maps/medium.in";
		else path = "maps/large.in";


		try{
			reader = new BufferedReader(new FileReader(path));
		}catch(FileNotFoundException e){
			e.printStackTrace();
		}
		
		String row = null;
		try{
			row = reader.readLine();
		}catch(IOException e){
			e.printStackTrace();
		}
		
		while(row  != null){
			char[] tiles = row.toCharArray();
			for(char c : tiles){
				if(c == 'B') handler.addMapObject(new Block(x,y));
				else if(c == 'U') {
					PowerupEffect effect = powerups[randomizer.nextInt(powerups.length)];
					handler.addMapObject(new Powerup(x,y, effect));
				}
				x += MapObject.BLOCK_SIZE;
			}
			y += MapObject.BLOCK_SIZE;
			x = 200;
			try{
				row = reader.readLine();
			}catch(IOException e){
				e.printStackTrace();
			}
		}

		handler.getMapObjectCount();
	}

	public void playerSpawn(){

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
		double amountOfTicks = 30.0;
		double ns = 1000000000 / amountOfTicks;
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

		Graphics2D g2d = (Graphics2D) g;

		if(currentState == State.GAME){
			// g.setColor(Color.white);
			// g.fillRect(0,0, 200,600);
			//render ground first
			g.setColor(Color.lightGray);
			g.fillRect(200, 0, 600, 600);
			g2d.translate(-camera.getX(), -camera.getY());
			//render mapobjects here
			handler.render(g);
			//
			g2d.translate(camera.getX(), camera.getY());

			g.setColor(Color.white);
			g.fillRect(0,0, 200,600);
		}
		// else if(currentState == State.LAUNCHER){
		// 	//render things here
		// 	launcher.render(g);
		// 	//
		// }
		// else if(currentState == State.LOBBY){
		// 	//render things here
		// 	lobby.render(g);
		// 	//
		// }

		g.dispose();
		b.show();
	}

	public void update(){
		if(currentState == State.GAME){
			camera.update(handler.getMapObject(playerCount-1));
			handler.update();
		}
		// else if(currentState == State.LAUNCHER){
		// 	launcher.update();
		// 	config = launcher.getConfig();
		// }
		// else if(currentState == State.LOBBY){
		// 	lobby.update();
		// }
	}

	public static void main(String args[]){
		new Game("What the tank?!", 800, 600);
		
	}
}