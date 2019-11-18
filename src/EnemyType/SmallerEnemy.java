package EnemyType;

import path.Road;

public class SmallerEnemy extends Enemy{
	public SmallerEnemy(Road r) {
		this.setArmor(LOW_ARMOR);
		this.setBlood(LOW_BLOOD);
		this.setMoveSpeed(HIGH_MOVESPEED);
		this.setReward(LOW_REWARD);
		
		try {
		  	  icon = tk.getImage(s + Integer.toString(248) + str);
		} catch (Exception IO) {}
		
		this.road = r;
		this.object.setLocation(this.road.start().x*64, this.road.start().y*64);
	}
	
}
