package static_ob;

import java.awt.Image;
import java.awt.Point;
import java.awt.Toolkit;
import java.util.ArrayList;
import java.util.List;

import EnemyType.Enemy;
import Shot.Bullet;

public class Tower {
	final static int HIGH_SPEED = 30;
	final static int EXTEND_RANGE = 4*64;
	final static int HIGH_DAMAGE = 75;
	
	final static int LOW_SPEED = 80;
	final static int NARROW_RANGE = 2*64;
	final static int LOW_DAMAGE = 25;
	
	final static int NORMAL_SPEED = 45;
	final static int NORMAL_RANGE = 3*64;
	final static int NORMAL_DAMAGE = 50;
	
	private int shotSpeed;
	private int damageRange;
	private int damage;
	private Point pos = new Point(0, 0);
	
	final static String s = "D:/DULIEU/Java/Game/TowerDefense/AssetsKit_2/PNG/Default size/towerDefense_tile";
	final static String str = ".png";
	protected Toolkit tk   = Toolkit.getDefaultToolkit(); 
	protected Image   icon;
	
	protected ArrayList <Bullet> bullet = new ArrayList <Bullet>();
	
	public Tower(int x, int y){
		pos.x = x;
		pos.y = y;
	}
	
	public Tower(int s, int r, int d) {
		this.shotSpeed = s;
		this.damageRange = r;
		this.damage = d;
	}
	public int getX() {return pos.x;}
	public void setX(int x) {this.pos.x = x;}
	public int getY() {return pos.y;}
	public void setY(int x) {this.pos.y = x;}
	
	public Point getPos() {return this.pos;}
	
	public int getShotSpeed() {return this.shotSpeed; }
	public void setShotSpeed(int s) {this.shotSpeed = s; }
	
	public int getDamageRange() {return this.damageRange; }
	public void setDamageRange(int s) {this.damageRange = s; }
	
	public int getDamage() {return this.damage;}
	public void setDamage(int s) {this.damage = s;}

	public Image getIcon() {return this.icon;}
	public void setBullet(String s){
		for (Bullet b: this.bullet){
			b.setIcon(s);
		}
	}
	
	public Enemy choseEnemy(List <Enemy> e) {
		if (e.size() == 0) return null;
		for (Enemy x: e){
			if ((x.getX() - pos.x - 32)*(x.getX() - pos.x - 32) 
					+ (x.getY() - pos.y -32)*(x.getY() - pos.y - 32)
					< this.damageRange*this.damageRange) return x;
		}
		return null;
		//return e.get(0);
	}
	public ArrayList <Bullet> getBullet() {return this.bullet;}
	
	public void shot(Enemy e) {
		bullet.add(new Bullet(e, this.getPos(), "297"));
	}
}
