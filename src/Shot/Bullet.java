package Shot;

import java.awt.Image;
import java.awt.Point;
import java.awt.Toolkit;

public class Bullet {
	private int flySpeed;
	private int range;
	private int damage;
	private Point pos = new Point(0, 0);
	
	final static String s = "D:/DULIEU/Java/Game/TowerDefense/AssetsKit_2/PNG/Default size/towerDefense_tile";
	final static String str = ".png";
	protected Toolkit tk   = Toolkit.getDefaultToolkit(); 
	protected Image   icon;
	
	public Bullet(){
		this.setIcon("297");
		this.damage = 90;
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
	
}
