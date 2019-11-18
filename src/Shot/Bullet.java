package Shot;

import java.awt.Image;
import java.awt.Point;
import java.awt.Toolkit;

import EnemyType.Enemy;

public class Bullet {
	private int flySpeed = 10;
	private int range;
	private int damage;
	private Point pos = new Point(0, 0);
	private double rotated = 0.0;
	private Enemy e;
	private boolean hitTarget = false;
	private boolean outOfBound = false;
	
	final static String s = "D:/DULIEU/Java/Game/TowerDefense/AssetsKit_2/PNG/Default size/towerDefense_tile";
	final static String str = ".png";
	protected Toolkit tk   = Toolkit.getDefaultToolkit(); 
	protected Image   icon;
	
	public Bullet(){
		this.setIcon("297");
		this.damage = 90;
	}
	
	public Bullet(Enemy e, Point p, String s){
		this.setIcon(s);
		this.damage = 90;
		this.flySpeed = 10;
		this.e = e;
		this.pos.x = p.x;
		this.pos.y = p.y;
		this.setAngle();
	}
	
	public Bullet(int f, int r, int d) {
		this.flySpeed = f; 
		this.range = r; 
		this.damage = d;
		this.setIcon("297");
	}
	
	public int getFlySpeed() {return this.flySpeed;}
	public void setFlySpped(int f) {this.flySpeed = f;}
	
	public int getRange() {return this.range;}
	public void setRange(int f) {this.range = f;}
	
	public int getDamage() {return this.damage;}
	public void setDamage(int f) {this.damage = f;}
	
	public Point getPoint() {return this.pos;}
	public void setPoint(Point p) {this.pos = p;}
	
	public Image getIcon() {return this.icon;}
	public void setIcon(String x) {
		icon = tk.getImage(s + x + str);
	}
	
	public Enemy getEnemy() {return this.e;}
	
	public double getAngle() {return this.rotated;}
	public void setAngle() {
		if (e.getX() != this.pos.x)
			this.rotated = Math.atan((double)(e.getY()-this.pos.y)/(double)(e.getX()-this.pos.x));
		else this.rotated = 0.0;
    	if (e.getX() < this.pos.x) this.rotated += Math.PI;
	}
	
	public void next() {
		int dis = (e.getX() - this.pos.x)*(e.getX() - this.pos.x) + 
				(e.getY() - this.pos.y)*(e.getY() - this.pos.y);
		if (dis <= this.flySpeed*this.flySpeed) {
			this.pos.x = e.getX();
			this.pos.y = e.getY();
			hitTarget = true;
		} else {
			this.pos.x += this.flySpeed * Math.cos(this.rotated);
			this.pos.y += this.flySpeed * Math.sin(this.rotated);
			this.setAngle();
		}
		if (this.pos.x >= 64*11 || this.pos.x < 0 || this.pos.y >= 64*9 || this.pos.y < 0) 
			outOfBound = true;
	}
	
	public boolean hit() {return this.hitTarget;}
	public boolean out() {return this.outOfBound;}
}
