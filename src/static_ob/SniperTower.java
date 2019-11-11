package static_ob;

import java.awt.Image;

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
		this.bullet.setIcon("297");
	}
}
