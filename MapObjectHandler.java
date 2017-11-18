import java.awt.Graphics;
import java.util.LinkedList;

public class MapObjectHandler{
	private LinkedList<MapObject> mapObjects = new LinkedList<MapObject>();


	public void update(){
		for(int i=0; i<mapObjects.size(); i++){
			mapObjects.get(i).update();
		}
	}

	public void render(Graphics g){
		for(int i=0; i<mapObjects.size(); i++){
			mapObjects.get(i).render(g);
		}

	}

	public void addMapObject(MapObject obj){
		mapObjects.add(obj);
	}

	public void removeMapObject(MapObject obj){
		mapObjects.remove(obj);
	}



}