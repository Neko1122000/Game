package initiate;

import java.util.ArrayList;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;

import javax.swing.*;

import EnemyType.*;
import Shot.Bullet;
import path.Road;
import static_ob.*;

public class GameField extends JPanel{
	final static String s = "D:/DULIEU/Java/Game/TowerDefense/AssetsKit_2/PNG/Default size/towerDefense_tile";
	final static String str = ".png";
	final static int limitTimer = 80*45*30;
	 
	private Toolkit tk   = Toolkit.getDefaultToolkit(); 
	private Image   land, path, start, en;
	private Image[]  gun = new Image[4];
	private Point[][] objects;
	
	private Point mouse = new Point(0, 0);
	private int timer = 1;
	private int cash = 500;
	private int life = 5;
	private boolean flag = true;
	private int level = 1, countEnemy = 5;
	
	private Road road = new Road();
	private int [][] map;
	private ArrayList <Enemy> enemy = new ArrayList <Enemy>();
	private ArrayList <Tower> tower = new ArrayList <Tower>();
	
	public GameField(){
		try {
			land = tk.getImage(s + "024" + str);
			path = tk.getImage(s + "055" + str);
			start = tk.getImage(s + "061" + str);
			en = tk.getImage(s + "064" + str);
			gun[0] = tk.getImage(s + "291" + str);
			gun[1] = tk.getImage(s + "292" + str);
			gun[2] = tk.getImage(s + "203" + str);
			gun[3] = tk.getImage(s + "090" + str);
		} catch (Exception e)  {
			  //Display a message if something goes wrong
			  JOptionPane.showMessageDialog( null, e.toString() );
		}
		init();
	}
		
		
	
	private void init(){
		objects = new Point[12][12];
		map = new int[12][10];
		for (int i = 0; i < 12; ++i) {
			for (int j = 0; j < 10; ++j) {
				map[i][j] = 1;
			}
		}
		
		for (int i = 0; i < 12; ++i){
			for (int j = 0; j < 10; ++j) {
				objects[i][j] = new Point(i*64,j*64);
			}
		}
		
		for (int i = 0; i < road.size(); ++i) 
			map[road.getX(i)][road.getY(i)] = 0;
	}
	
	public boolean getPoint(Point m){
		int i = m.x/64;
		int j = m.y/64;
		return (i < 12 && j < 10 && map[i][j] == 1);
	}
	
	public Point getMouse(){
		int i = mouse.x/64;
		int j = mouse.y/64;
		return (new Point(i, j));
	}
	public void setMouse(Point e){
		this.mouse = e;
	}
	
	public void addTower(Point m, int k){
		int i = m.x/64;
		int j = m.y/64;
		if (cash < 100) return;
		map[i][j] = 2;
		switch (k) {
			case 0: {
				tower.add(new NormalTower(i*64, j*64));
				break;
			}
			case 1: {
				tower.add(new SniperTower(i*64, j*64));
				break;
			}
			case 2: {
				tower.add(new MachineGunTower(i*64, j*64));
				break;
			}
		}
		cash -= 100;
		
	}
	public void deleteTower(Point m){
		int i = m.x/64;
		int j = m.y/64;
		if (map[i][j] != 1 || cash < 100) return;
		for (Tower t: tower){
			if (t.getX()/64 == i && t.getY()/64 == j) {
				tower.remove(t);
				map[i][j] = 1;
				break;
			}
		}
		cash -= 100;
	}
	
	public void paint (Graphics g){
		super.paint(g);
		
		for (int i = 0; i < 12; ++i) {
		      for (int j = 0; j < 10; ++j) {
		    	  if (map[i][j] != 0){
		    		  g.drawImage(land, objects[i][j].x, objects[i][j].y, 64, 64, null);
		    	  }
		    	  else {
		    		  if (road.startPointX() == i && road.startPointY() == j) 
		    			  g.drawImage(start, i*64, j*64, 64, 64, null);
		    		  else if (road.end().x == i && road.end().y == j) 
		    			  g.drawImage(en, i*64, j*64, 64, 64, null);
		    		  else g.drawImage(path, objects[i][j].x, objects[i][j].y, 64, 64, null);
		    	  } 
		      }  	  
		    }
		    for (int i = 0; i < 4; ++i){
		    	g.drawImage(gun[i], 12*64, i*64, 64, 64, null);
		    	g.drawString("  100$", 13*64, i*64 + 32);
		    }
		    g.drawImage(tk.getImage(s + "300" + str), 13*64+35, 5*64+32, 32, 32, null);
		    g.setFont(new Font("TimesRoman", Font.PLAIN, 25));
		    g.drawString("LEVEL " + Integer.toString(level),13*64, 7*64);
		    
		if (life == 0) {
			g.setFont(new Font("TimesRoman", Font.PLAIN, 40));
			g.drawString("GAME OVER", 4*64, 5*64);
			if (flag) {
				flag = false;
				Button b=new Button("RESTART");  
				b.setBounds(325, 325, 100, 50);  
				b.addActionListener(new ActionListener(){  
					public void actionPerformed(ActionEvent e){  
						  life = 5;
						  tower.clear();
						  enemy.clear();
						  road = new Road();
						  cash = 500;
						  flag = true;
						  level = 1;
						  countEnemy = 5;
						  remove(b);
						  init();
				     }  
				 }); 
				add(b);
			}
			return;
		} else if (countEnemy == 0 && enemy.size() == 0) {
			g.setFont(new Font("TimesRoman", Font.PLAIN, 40));
			g.drawString("NEXT LEVEL", 4*64, 5*64);
			if (flag) {
				flag = false;
				Button b=new Button("PLAY");  
				b.setBounds(325, 325, 100, 50);  
				b.addActionListener(new ActionListener(){  
					public void actionPerformed(ActionEvent e){  
						  tower.clear();
						  enemy.clear();
						  road = new Road();
						  ++level;
						  countEnemy = 5*level;
						  flag = true;
						  remove(b);
						  init();
				     }  
				 }); 
				add(b);
			}
		}
	 
		++timer;
	    for (Enemy i: enemy) {
	    	if (!i.getFlag())
	    		g.drawImage(i.getIcon(), i.getX(), i.getY(), 64, 64, null);
	    	else {
	    		AffineTransform tx = AffineTransform.getRotateInstance(Math.PI/2.0, 32, 32);
		    	AffineTransformOp op = new AffineTransformOp(tx, AffineTransformOp.TYPE_BILINEAR);
		    	g.drawImage(op.filter(toBufferedImage(i.getIcon()), null), 
		    			i.getX(), i.getY(), null);
	    	}
	    	i.next();
	    }
	    for (int i = 0; i < enemy.size(); ++i){
	    	if (enemy.get(i).isDead()) cash += enemy.get(i).getReward();
	    	if (enemy.get(i).hitTarget(road.end())) --life;
	    	if (enemy.get(i).hitTarget(road.end()) || enemy.get(i).isDead()) {
	    		enemy.remove(i);
	    		--i;
	    	}
	    }
	    for (Tower t: tower){
	    	for (int i = 0; i < t.getBullet().size(); ++i){
	    		if (t.getBullet().get(i).out()) {
	    			t.getBullet().remove(i);
	    			--i;
	    		} else if (t.getBullet().get(i).hit()) {
	    			Enemy e = t.getBullet().get(i).getEnemy();
	    			e.setBlood(e.getBlood()+e.getArmor() - t.getDamage());
	    			t.getBullet().remove(i);
	    			--i;
	    		}
	    	}
	    }
	    for (Tower t: tower){
	    	// The required drawing location
	    	int drawLocationX = t.getX();
	    	int drawLocationY = t.getY();

	    	// Rotation information
	    	Enemy e = t.choseEnemy(enemy);
	    	int x = road.startPointX()*64;
	    	if (e != null) x = e.getX();
	    	int y = road.startPointY()*64;
	    	if (e != null) y = e.getY();
	    	double rotationRequired = Math.atan ((double)(t.getY()-y)/(double)(t.getX()-x));
	    	if (t.getX() >= x) rotationRequired += Math.PI;
	    	AffineTransform tx = AffineTransform.getRotateInstance(rotationRequired, 32, 32);
	    	AffineTransformOp op = new AffineTransformOp(tx, AffineTransformOp.TYPE_BILINEAR);
	    	
	    	Point back = new Point(0, 0);
	    	if (timer % t.getShotSpeed() == 0) {
	    		if (e != null) {
	    			t.shot(e);
	    			back.x = 5; back.y = 5; 
	    		}
	    	}
	    	// Drawing the rotated image at the required drawing location
	    	g.drawImage(op.filter(toBufferedImage(t.getIcon()), null), 
	    			drawLocationX - back.x, drawLocationY - back.y, null);
	    	
	    	for (Bullet b: t.getBullet()){
	    		AffineTransform affineTransform = new AffineTransform();
	    	    affineTransform.rotate(b.getAngle(), 32, 32);

	    	    // Now lets make that transform an operation, and we use it.
	    	    AffineTransformOp opp = new AffineTransformOp(affineTransform, AffineTransformOp.TYPE_BILINEAR); 
	    		g.drawImage(opp.filter(toBufferedImage(b.getIcon()), null), 
		    			b.getPoint().x, b.getPoint().y, null);
	    		b.next();
	    	}
	    	
	    }
	    
	    g.setFont(new Font("TimesRoman", Font.PLAIN, 25));
	    g.drawString(Integer.toString(cash) + "$",13*64, 5*64);
	    g.drawString(Integer.toString(life) + " x ", 13*64, 6*64);
	    
	    if (timer % 100 == 0 && countEnemy > 0) {
    		int type = (int) (Math.random()*4);
    		switch (type){
	    		case 0: {
	    			enemy.add(new NormalEnemy(road));
	    			break;
	    		}
	    		case 1: {
	    			enemy.add(new SmallerEnemy(road));
	    			break;
	    		}
	    		case 2: {
	    			enemy.add(new TankerEnemy(road));
	    			break;
	    		}
	    		case 3: {
	    			enemy.add(new BossEnemy(road));
	    			break;
	    		}
    		}
    		--countEnemy;
    	}
    	if (timer == limitTimer) timer = 1;
	  }
	
	public static BufferedImage toBufferedImage(Image img)
	{
	    if (img instanceof BufferedImage)
	    {
	        return (BufferedImage) img;
	    }

	    // Create a buffered image with transparency
	    BufferedImage bimage = new BufferedImage(64, 64, BufferedImage.TYPE_INT_ARGB);

	    // Draw the image on to the buffered image
	    Graphics2D bGr = bimage.createGraphics();
	    bGr.drawImage(img, 0, 0, null);
	    bGr.dispose();

	    // Return the buffered image
	    return bimage;
	}
	
}
