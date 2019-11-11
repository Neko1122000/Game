package EnemyType;

import java.awt.Point;

public class BossEnemy extends Enemy{
	
	public BossEnemy() {
		this.setArmor(HIGH_ARMOR);
		this.setBlood(HIGH_BLOOD);
		this.setMoveSpeed(LOW_MOVESPEED);
		this.setReward(HIGH_REWARD);
		try {
		  	  icon = tk.getImage(s + Integer.toString(268) + str);
		  	  } catch (Exception IO) {}
		     
		     object = new Point(0*64, 5*64);
		      
		  }

		@Override 
		public void next(){
			this.setX(this.getX() + 2);
			this.setY(this.getY() + 0);
		}
		
}
