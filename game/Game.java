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
import java.awt.Rectangle;

//import file i/o
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
//import randomizer
import java.util.Random;

import java.util.Hashtable;

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
	
	//map parameters
	private int mapID;
	private int mapSize = 0;
	
	//config file
	// private Config config;

	//randomizer
	private Random randomizer = new Random();

	//player data
	private int playerCount;
	private Hashtable<Integer, Integer> scoreBoard;

	public static Display display;
	
	public Game(String title, int width, int height) {
		this.title = title;
		this.width = width;
		this.height = height;
		display = new Display(width, height, title, this);

		currentState = State.GAME;

		//initialize sprites
		ImageLoader.init();

		//initialize map object handler
		handler = new MapObjectHandler();

		//add objects to handler by initializing map
		mapID = 1;
		mapInitialize(mapID);

		//spawn player
		Tank player1 = playerSpawn(1, "Weaboo");
		Tank player2 = playerSpawn(2, "Normie");
		playerCount = 2;
		//initialize scoreBoard
		scoreBoard = new Hashtable<Integer, Integer>(playerCount);
		scoreBoard.put(1, 0);
		scoreBoard.put(2, 0);
		//initialize camera
		camera = new Camera(player1.getX(), player1.getY());

		//add key listener for controls
		this.addKeyListener(new PlayerControls(player1));
		this.addMouseListener(new PlayerAim(handler, player1, camera));
		start();
	}


	//require int in the future to identify which map to initialize
	private void mapInitialize(int mapID){
		System.out.println("Initializing map...");
		String path = null;

		//prepare list of powerups for randomizer
		PowerupEffect[] powerups = PowerupEffect.values();
		
		//game will be rendered in these window parameters
		int x = 200;
		int y = 0;

		//map to be rendered depends on mapID passed
		if(mapID == 1) path = "maps/small.in";
		else if(mapID == 2) path = "maps/medium.in";
		else path = "maps/large.in";



		//attempt to read map, throw exception if unable to
		try{
			reader = new BufferedReader(new FileReader(path));
		}catch(FileNotFoundException e){
			e.printStackTrace();
		}
		
		//read first line from map
		String row = null;
		try{
			row = reader.readLine();
		}catch(IOException e){
			e.printStackTrace();
		}
		
		//read map file until end
		while(row  != null){
			//get length of map
			mapSize += 1;
			
			//convert to char array to read characters one by one
			char[] tiles = row.toCharArray();
			
			//read characters
			for(char c : tiles){

				//create new map objects based on mapfile character
				if(c == 'I') handler.addMapObject(new InvincibleBlock(x,y));
				else if(c == 'B') handler.addMapObject(new Block(x,y));
				else if(c == 'U') {
					PowerupEffect effect = powerups[randomizer.nextInt(powerups.length)];
					handler.addMapObject(new Powerup(x,y, effect));
				}
				//increment x axis
				x += MapObject.BLOCK_SIZE;
				
			}

			//increment y axis and reset x position
			y += MapObject.BLOCK_SIZE;
			x = 200;

			//read next line in mapfile
			try{
				row = reader.readLine();
			}catch(IOException e){
				e.printStackTrace();
			}
		}

		//print map size
		System.out.println("Size of map is " + mapSize + " x " + mapSize);
		// print number of objects
		// handler.getMapObjectCount();
	}

	public Tank playerSpawn(int playerID, String name){
		int x = randomizer.nextInt(mapSize-1);
		int y = randomizer.nextInt(mapSize-1);
		
		Rectangle bound = new Rectangle((x*MapObject.BLOCK_SIZE)+200, y*MapObject.BLOCK_SIZE, MapObject.BLOCK_SIZE,MapObject.BLOCK_SIZE);
		System.out.println("Spawning player " + playerID + ": " + name);
		while(true){
			int count=0;
			for(int i=0; i<handler.getMapObjectCount(); i++){
				MapObject temp = handler.getMapObject(i);

				//check each map object, if it collides, break
				if(temp.getBounds().intersects(bound) || temp.getBounds().equals(bound) ||temp.getBounds().contains(bound)){
					break;
				} 
				count++;
			}
			//if all objects have been checked, no object collides with tank
			if(count==handler.getMapObjectCount()){
				System.out.println("Player " + name + " spawned at" + x + "," + y);
				Tank player = new Tank((x*MapObject.BLOCK_SIZE)+200, y*MapObject.BLOCK_SIZE, playerID, name, handler);
				handler.addMapObject(player);
				return player;
			}

			x = randomizer.nextInt(mapSize-1);
			y = randomizer.nextInt(mapSize-1);
			bound = new Rectangle((x*MapObject.BLOCK_SIZE)+200, y*MapObject.BLOCK_SIZE, MapObject.BLOCK_SIZE,MapObject.BLOCK_SIZE);
		}
		
		
	}

	private void printScoreboard(){
		System.out.println("Scores:");
		for(int i = 1; i<=playerCount; i++){
			
			System.out.println("Player "+ i + ": " + scoreBoard.get(i));
		}
	}

	private int checkGameEnd(){
		for(int i = 1; i<=playerCount; i++){
			
			if(scoreBoard.get(i) == 20){
				return i;
			}
		}
		return 0;
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
			//render background first
			g.setColor(Color.lightGray);
			g.fillRect(200, 0, 600, 600);

			//set camera coordinates
			g2d.translate(-camera.getX(), -camera.getY());
			
			//render ground
			g.setColor(Color.white);
			g.fillRect(200, 0, (MapObject.BLOCK_SIZE*(mapSize-1)), MapObject.BLOCK_SIZE*mapSize);
			
			//render mapobjects
			handler.render(g);
			//
			g2d.translate(camera.getX(), camera.getY());

			// g.setColor(Color.white);
			// g.fillRect(0,0, 200,600);
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
			int check = checkGameEnd();
			if(check!=0){
				System.out.println("Game done woohoo");
				System.out.println("Winner is: Player#"+ check );
			}
			for(int i=0; i<handler.getMapObjectCount(); i++){
				MapObject temp = handler.getMapObject(i);
				if (temp.getId() == ID.Tank){
					Tank player = (Tank)temp;
					if(player.getPlayerID()==1)
						camera.update(player);
					if(player.getIsDead()) {
						int killer = player.getLastHit();
						scoreBoard.replace(killer, scoreBoard.get(killer)+2);

						printScoreboard();
						player.setIsDead(false);
						player.setHp(20);
						//respawn
						player.getHandler().removeMapObject(player);
						player = playerSpawn(player.getPlayerID(), player.getName());
					}
				}
			}

			
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