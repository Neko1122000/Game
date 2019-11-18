package static_ob;

import EnemyType.Enemy;
import Shot.Bullet;

public class MachineGunTower extends Tower{
	public MachineGunTower(){
		super(HIGH_SPEED, NARROW_RANGE, NORMAL_DAMAGE);
	}
	
	public MachineGunTower(int x, int y){
		super(HIGH_SPEED, NARROW_RANGE, NORMAL_DAMAGE);
		
		try {
			icon = tk.getImage(s + Integer.toString(203) + str);
		} catch (Exception IO) {}
	
		this.setX(x);
		this.setY(y);
	}
	
	public void shot(Enemy e) {
		bullet.add(new Bullet(e, this.getPos(), "296"));
	}
}
