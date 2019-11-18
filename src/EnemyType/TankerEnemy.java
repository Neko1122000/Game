package EnemyType;

import path.Road;

public class TankerEnemy extends Enemy{
	
	public TankerEnemy(Road r) {
		this.setArmor(HIGH_ARMOR);
		this.setBlood(HIGH_BLOOD);
		this.setMoveSpeed(LOW_MOVESPEED);
		this.setReward(BASIC_REWARD);
		
		try {
		  	  icon = tk.getImage(s + Integer.toString(269) + str);
		} catch (Exception IO) {}
		     
		this.road = r;
		this.object.setLocation(this.road.start().x*64, this.road.start().y*64);
	}
	
}
