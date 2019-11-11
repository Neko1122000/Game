package EnemyType;

import java.awt.Point;

public class SmallerEnemy extends Enemy{
	public SmallerEnemy() {
		this.setArmor(LOW_ARMOR);
		this.setBlood(LOW_BLOOD);
		this.setMoveSpeed(HIGH_MOVESPEED);
		this.setReward(LOW_REWARD);
		
		try {
		  	  icon = tk.getImage(s + Integer.toString(248) + str);
		  	  } catch (Exception IO) {}
		     
		     object = new Point(0*64, 5*64);
	}
	
	@Override 
	public void next(){
		this.setX(this.getX() + 4);
		this.setY(this.getY() + 0);
	}
}
