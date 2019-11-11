package EnemyType;

import java.awt.Color;

import java.awt.Image;
import java.awt.Point;
import java.awt.Toolkit;


public class Enemy {
	final static String s = "D:/DULIEU/Java/Game/TowerDefense/AssetsKit_2/PNG/Default size/towerDefense_tile";
	final static String str = ".png";
	
	private int blood, moveSpeed, armor, reward;
	
	
	protected Toolkit tk   = Toolkit.getDefaultToolkit(); 
	protected Image   icon;
	protected Color   background;
	protected Point object;
	protected Point[] deltas;
	
	final static int HIGH_BLOOD = 400;
	final static int HIGH_ARMOR = 10;
	final static int HIGH_REWARD = 100;
	final static int HIGH_MOVESPEED = 10;
	
	final static int LOW_BLOOD = 100;
	final static int LOW_ARMOR = 1;
	final static int LOW_REWARD = 25;
	final static int LOW_MOVESPEED = 5;
	
	final static int BASIC_BLOOD = 200;
	final static int BASIC_ARMOR = 5;
	final static int BASIC_REWARD = 50;
	final static int BASIC_MOVESPEED = 8;
	
	
	public Enemy(){	
	}
	
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
	
	public void next(){};
	
	public boolean hitTarget(Point p){
		return (this.object.x/64 == p.x && this.object.y/64 == p.y);
	}
}
