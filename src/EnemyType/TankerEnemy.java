package EnemyType;

import java.awt.Point;

public class TankerEnemy extends Enemy{
	
	public TankerEnemy() {
		this.setArmor(HIGH_ARMOR);
		this.setBlood(HIGH_BLOOD);
		this.setMoveSpeed(LOW_MOVESPEED);
		this.setReward(BASIC_REWARD);
		
		try {
		  	  icon = tk.getImage(s + Integer.toString(269) + str);
		  	  } catch (Exception IO) {}
		     
		     object = new Point(0*64, 5*64);
	}
	
	@Override 
	public void next(){
		this.setX(this.getX() + 1);
		this.setY(this.getY() + 0);
	}
}
