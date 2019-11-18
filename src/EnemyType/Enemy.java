package EnemyType;

import java.awt.Color;
import java.awt.Image;
import java.awt.Point;
import java.awt.Toolkit;

import path.Road;


public class Enemy {
	final static String s = "D:/DULIEU/Java/Game/TowerDefense/AssetsKit_2/PNG/Default size/towerDefense_tile";
	final static String str = ".png";
	
	private int blood, moveSpeed, armor, reward;
	protected Road road;
	protected int dir = 0, pos = 0;
	private boolean flag = false;
	
	
	protected Toolkit tk   = Toolkit.getDefaultToolkit(); 
	protected Image   icon;
	protected Color   background;
	protected Point object = new Point();
	
	final static protected Point[] deltas = {new Point(1, 0), new Point(0, 1),
								new Point(-1, 0), new Point(0, -1)};
	
	final static int HIGH_BLOOD = 400;
	final static int HIGH_ARMOR = 10;
	final static int HIGH_REWARD = 100;
	final static int HIGH_MOVESPEED = 4;
	
	final static int LOW_BLOOD = 100;
	final static int LOW_ARMOR = 1;
	final static int LOW_REWARD = 25;
	final static int LOW_MOVESPEED = 1;
	
	final static int BASIC_BLOOD = 200;
	final static int BASIC_ARMOR = 5;
	final static int BASIC_REWARD = 50;
	final static int BASIC_MOVESPEED = 2;
	
	
	public Enemy(){	
	}
	
	public Enemy(Road r){	
		this.object.x = r.start().x;
		this.object.y = r.start().y;
		this.road = r;
	}
	
	public Point getPoint() {return this.object;}
	public boolean getFlag() {return this.flag;}
	
	public void setX(int x){this.object.x = x;}
	public int getX(){return (int)object.x;}
	
	public void setY(int x){this.object.y = x;}
	public int getY(){return (int)object.y;}
	
	public void setBlood(int x){this.blood = x;}
	public int getBlood(){return this.blood;}
	
	public void setMoveSpeed(int x){this.moveSpeed = x;}
	public int getMoveSpeed(){return this.moveSpeed;}
	
	public void setArmor(int x){this.armor = x;}
	public int getArmor(){return this.armor;}
	
	public void setReward(int x){this.reward = x;}
	public int getReward(){return this.reward;}
	
	public boolean isDead(){
		return (this.getBlood() <= 0);
	}
	
	public Image getIcon() {return this.icon;}
	
	public void next(){
		int next_i, next_j;
		next_i = this.getX() + deltas[dir].x*this.moveSpeed;
		next_j = this.getY() + deltas[dir].y*this.moveSpeed;
		if (inside(pos, next_i, next_j)) {
			this.setX(next_i);
			this.setY(next_j);
		} else if (inside(pos+1, next_i, next_j)){
			this.setX(next_i);
			this.setY(next_j);
			++pos;
		} else {
			for (int k = 1; k < 4; k += 2) {
				int cur_dir = (dir + k) % 4;
				//System.out.println(cur_dir);
				next_i = this.getX() + deltas[cur_dir].x*this.moveSpeed;
				next_j = this.getY() + deltas[cur_dir].y*this.moveSpeed;
				if (inside(pos+1, next_i, next_j)) {
					this.setX(next_i);
					this.setY(next_j);
					dir = cur_dir;
					++pos;
					break;
				} 
				if (cur_dir == 3) System.out.println(road.get(pos+1).x*64 + " " + next_i + " " 
				+ road.get(pos+1).y*64 + " " + next_j);
			}
		}
		if (dir % 2 == 0) flag = false; else flag = true;
	}
	
	private boolean inside(int i, int pos_x, int pos_y){
		if (i >= road.size()) return false;
		if (i != 0){
			int l = road.get(i-1).x*64, r = road.get(i).x*64;
			if (l > r) {
				int tmp = l;
				l = r;
				r = tmp;
			}
			
			if (l > pos_x || r < pos_x) return false;
			l = road.get(i-1).y*64;
			r = road.get(i).y*64;
			if (l > r) {
				int tmp = l;
				l = r;
				r = tmp;
			}
			if (l > pos_y || r < pos_y) return false;
		}
		else {
			if (road.get(i).x*64-64 > pos_x || road.get(i).x*64 < pos_x) return false;
			if (road.get(i).y*64-64 > pos_y || road.get(i).y*64 < pos_y) return false;
		}
		
		return true;
	}
	
	public boolean hitTarget(Point p){
		return (this.object.x/64 == p.x && this.object.y/64 == p.y);
	}
}
