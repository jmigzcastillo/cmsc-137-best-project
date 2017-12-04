
/**
 * Game Server
 */

package game;

//import display related stuff
import java.awt.image.BufferedImage;
import java.awt.Canvas;
import java.awt.Rectangle;
import java.awt.Point;

//import file i/o
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
//import randomizer
import java.util.Random;

//import dictionary xd
import java.util.Hashtable;

import java.net.DatagramSocket;
import java.net.DatagramPacket;
import java.net.InetAddress;
//import map objects
import mapobject.*;



public class GameServer implements Runnable{
	//essential stuff
	private Thread thread;
	private DatagramSocket socket;
	private Hashtable<Integer, InetAddress> addresses;
	private int port;

	private State currentState;
	
	private boolean isRunning;

	//will handle map objects
	private MapObjectHandler handler;
	private BufferedReader reader;

	
	//map parameters
	private int mapID;
	private int mapSize = 0;

	//randomizer
	private Random randomizer = new Random();

	//player data
	private int playerCount;
	private Hashtable<Integer, Integer> scoreBoard;


	
	public GameServer(int playerCount, int mapID, int port, Hashtable<Integer, String>playerNames, Hashtable<Integer, InetAdress> adresses) {
		this.mapID = mapID;
		this.playerCount = playerCount;
		this.addresses = addresses;
		this.port = port;
		this.socket = new DatagramSocket(port);
		currentState = State.GAME;

		byte[] buf = new byte[256];
		DatagramPacket packet = null;

		//initialize map object handler
		handler = new MapObjectHandler();

		//add objects to handler by initializing map
		mapInitialize();
		

		Tank player = null;
		
		
		//send to players their respective spawn points
		for(int i=1; i<=playerCount; i++){
			player = playerSpawn(i, playerNames.get(i));
			handler.addMapObject(player);
			Point location = new Point(player.getX(), player.getY());
			buf = location.getBytes();
			packet = new DatagramPacket(buf, buf.length, adresses.get(i), port);
			socket.send(packet);
		}
		
		 
		//initialize scoreBoard
		scoreBoard = new Hashtable<Integer, Integer>(playerCount);
		for(int i=0; i<playerCount; i++){
			scoreBoard.put(i, 0);
		}

		//SERVERS HAVE NO CAMERAS AND CONTROLS :D
		start();
	}


	//require int in the future to identify which map to initialize
	private void mapInitialize(){
		System.out.println("Initializing map...");
		String path = null;

		//prepare list of powerups for randomizer
		PowerupEffect[] powerups = PowerupEffect.values();
		
		//game will be rendered in these window parameters
		int x = 200;
		int y = 0;

		//map to be rendered depends on mapID passed
		if(mapID == 1) {
			path = "maps/small.in";
			mapSize = 20;
		}
		else if(mapID == 2) {
			path = "maps/medium.in";
			mapSize = 60;
		}
		else {
			path = "maps/large.in";
			mapSize = 80;
		}



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

	//
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

	//print scoreboard
	private void printScoreboard(){
		System.out.println("Scores:");
		for(int i = 1; i<=playerCount; i++){
			System.out.println("Player "+ i + ": " + scoreBoard.get(i));
		}
	}

	//if a player managed to score 20 points, game over
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


	//servers don't render lmao
	public void render(){}

	public void update(){
		byte[] buf = new byte[256];
		DatagramPacket packet = null;
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
					Point location = new Point(player.getX(), player.getY());
					int playerID = player.getPlayerID();
					buf = location.getBytes();
					packet = new DatagramPacket(buf, buf.length, addresses.get(playerID), port);
					socket.send(packet);

				}
			}

			
			handler.update();
		}
	}

	public static void main(String args[]){
		new Game("What the tank?!", 800, 600);
		
	}
}