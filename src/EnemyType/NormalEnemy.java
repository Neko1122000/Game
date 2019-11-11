package EnemyType;

import java.awt.*;

public class NormalEnemy extends Enemy{
	
	public NormalEnemy(){
		this.setArmor(BASIC_ARMOR);
		this.setBlood(BASIC_BLOOD);
		this.setMoveSpeed(BASIC_MOVESPEED);
		this.setReward(BASIC_REWARD);
		
	  	try {
	  	  icon = tk.getImage(s + Integer.toString(247) + str);
	  	  } catch (Exception IO) {}
	     
	     object = new Point(0*64, 5*64);
	      
	  }

	@Override 
	public void next(){
		this.setX(this.getX() + 2);
		this.setY(this.getY() + 0);
	}
	
}
