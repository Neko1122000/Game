package static_ob;

import java.awt.Image;

import EnemyType.Enemy;
import Shot.Bullet;

public class SniperTower extends Tower{
	public SniperTower(){
		super(LOW_SPEED, EXTEND_RANGE, HIGH_DAMAGE);
		
		try {
			icon = tk.getImage(s + Integer.toString(292) + str);
		} catch (Exception IO) {}
	}
	
	public SniperTower(int x, int y){
		super(LOW_SPEED, EXTEND_RANGE, HIGH_DAMAGE);
		
		try {
			icon = tk.getImage(s + Integer.toString(292) + str);
		} catch (Exception IO) {}
		this.setX(x);
		this.setY(y);
	}
	
	public void shot(Enemy e) {
		bullet.add(new Bullet(e, this.getPos(), "297"));
	}
}

