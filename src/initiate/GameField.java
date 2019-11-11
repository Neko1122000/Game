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
	private int life = 1;
	private boolean flag = true;
	
	private Road road = new Road();
	private boolean[][] map;
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
		
		objects = new Point[12][12];
		map = new boolean[12][10];
		for (int i = 0; i < 12; ++i) {
			for (int j = 0; j < 10; ++j) {
				map[i][j] = true;
			}
		}
		
		for (int i = 0; i < 12; ++i){
			for (int j = 0; j < 10; ++j) {
				objects[i][j] = new Point(i*64,j*64);
			}
		}
		
		for (int i = 0; i < road.size(); ++i) 
			map[road.getX(i)][road.getY(i)] = false;
		enemy.add(new NormalEnemy());
	}
	
	public boolean getPoint(Point m){
		int i = m.x/64;
		int j = m.y/64;
		return (i < 12 && j < 10 && map[i][j]);
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
		if (!map[i][j] || cash < 100) return;
		for (Tower t: tower){
			if (t.getX()/64 == i && t.getY()/64 == j) {
				tower.remove(t);
				break;
			}
		}
		cash -= 100;
	}
	
	public void paintComponent(Graphics g){
		super.paintComponent(g); 
	    
	    for (int i = 0; i < 12; ++i) {
	      for (int j = 0; j < 10; ++j) {
	    	  if (map[i][j]){
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
	}
	
	public void paint (Graphics g){
		super.paint(g);
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
						  cash = 500;
						  road = new Road();
						  flag = true;
						  remove(b);
				     }  
				 }); 
				add(b);
			}
			return;
		}
	 
		++timer;
	    for (Enemy i: enemy) {
	    	g.drawImage(i.getIcon(), i.getX(), i.getY(), 64, 64, null);
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
	    			g.drawImage(op.filter(toBufferedImage(t.getBullet()), null), 
	    					x, y, 64, 64, null);
	    			back.x = 5; back.y = 5; 
	    			e.setBlood(e.getBlood() + e.getArmor() - t.getDamage());
	    		}
	    	}

	    	// Drawing the rotated image at the required drawing location
	    	g.drawImage(op.filter(toBufferedImage(t.getIcon()), null), 
	    			drawLocationX - back.x, drawLocationY - back.y, null);
	    	
	    }
	    
	    g.setFont(new Font("TimesRoman", Font.PLAIN, 25));
	    g.drawString(Integer.toString(cash) + "$",13*64, 5*64);
	    g.drawString(Integer.toString(life) + " x ", 13*64, 6*64);
	    
	    if (timer % 100 == 0) {
    		int type = (int) (Math.random()*4);
    		switch (type){
	    		case 0: {
	    			enemy.add(new NormalEnemy());
	    			break;
	    		}
	    		case 1: {
	    			enemy.add(new SmallerEnemy());
	    			break;
	    		}
	    		case 2: {
	    			enemy.add(new TankerEnemy());
	    			break;
	    		}
	    		case 3: {
	    			enemy.add(new BossEnemy());
	    			break;
	    		}
    		}
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
