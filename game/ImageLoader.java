package game;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;

public class ImageLoader{
	public static BufferedImage tankB, tankG, tankR, tankY, block, ground, wall, speedup, sattack, shield, invisibility;

	public static BufferedImage load(String path){
		try{
			return ImageIO.read(ImageLoader.class.getResource(path));
		}
		catch(Exception e){
			e.printStackTrace();
		}
		return null;
	}

	public static void init(){
		tankB = load("../sprites/circular-blue.png");
		tankG = load("../sprites/circular-green.png");
		tankR = load("../sprites/circular-red.png");
		tankY = load("../sprites/circular-yellow.png");
		block = load("../sprites/block.png");
		wall = load("../sprites/brick.png");
		speedup = load("../sprites/Movement.png");
		sattack = load("../sprites/Damage.png");
		shield = load("../sprites/Shield.png");
		invisibility = load("../sprites/Invisibility.png");
	}
}