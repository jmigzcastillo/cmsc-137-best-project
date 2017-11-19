import java.awt.Graphics;
import java.util.LinkedList;

import mapobject.*;

public class MapObjectHandler{
	private LinkedList<MapObject> mapObjects = new LinkedList<MapObject>();

	

	public void update(){
		for(int i=0; i<mapObjects.size(); i++){
			mapObjects.get(i).update();
		}
	}

	public void render(Graphics g){
		for(int i=0; i<mapObjects.size(); i++){
			MapObject temp = mapObjects.get(i);
			temp.render(g);
		}	

	}

	public void addMapObject(MapObject obj){
		mapObjects.add(obj);
	}

	public void removeMapObject(MapObject obj){
		mapObjects.remove(obj);
	}

	public int getMapObjectCount(){
		return mapObjects.size();
	}

	public MapObject getMapObject(int i){
		return mapObjects.get(i);
	}


}