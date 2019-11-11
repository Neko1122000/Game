package path;

import java.awt.Point;
import java.util.ArrayList;


public class Road {
	
	ArrayList <Point> road = new ArrayList <Point>();
	public Road(){
		for (int i = 0; i < 12; ++i) road.add(new Point(i, 5));
	}
	public int size() {return road.size();}
	
	public int getX(int i) {return road.get(i).x;}
	public int getY(int i) {return road.get(i).y;}
	
	public int startPointX() {return road.get(0).x; }
	public int startPointY() {return road.get(0).y; }
	public Point end() {
		return road.get(road.size()-1);
	}
}
