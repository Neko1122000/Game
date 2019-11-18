package EnemyType;

import java.awt.*;

import path.Road;

public class NormalEnemy extends Enemy{
	
	public NormalEnemy(Road x){
		this.setArmor(BASIC_ARMOR);
		this.setBlood(BASIC_BLOOD);
		this.setMoveSpeed(BASIC_MOVESPEED);
		this.setReward(BASIC_REWARD);
		
	  	try {
	  	  icon = tk.getImage(s + Integer.toString(247) + str);
	  	} catch (Exception IO) {}
	  	
	  	this.road = x;
	  	this.object.setLocation(this.road.start().x*64, this.road.start().y*64);
	  }
	
}
