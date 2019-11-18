package path;

import java.awt.Point;
import java.util.ArrayList;


public class Road {
	
	ArrayList <Point> road = new ArrayList <Point>();
	
	public Road(){
		int startX = (int)(Math.random()*4 + 3);
		int turn1 = (int)(Math.random()*7 + 3);
		int turn2 = (int)(Math.random()*3 + 2);
		for (int i = 0; i < turn1; ++i) {
			road.add(new Point(i,startX));
		}
		
		int dir = (int)(Math.random()*2);
		if (dir == 0) dir = -1;
		if (dir == -1) turn2 = Math.min(startX-1, turn2);
		if (dir == 1) turn2 = Math.min(turn2, 8-startX);
		for (int i = 1; i < turn2; ++i) {
			road.add(new Point(turn1-1, startX + dir*i));
		}
		for (int i = turn1-1; i < 12; ++i) road.add(new Point(i, startX + turn2*dir));
	}
	
	public int size() {return road.size();}
	public Point get(int i) {return road.get(i);}
	
	public int getX(int i) {return road.get(i).x;}
	public int getY(int i) {return road.get(i).y;}
	
	public int startPointX() {return road.get(0).x; }
	public int startPointY() {return road.get(0).y; }
	public Point start() {return road.get(0);}
	public Point end() {
		return road.get(road.size()-1);
	}
}
