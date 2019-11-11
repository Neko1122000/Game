package static_ob;

public class NormalTower extends Tower{
	public NormalTower(){
		super(NORMAL_SPEED, NORMAL_RANGE, NORMAL_DAMAGE);
		try {
			icon = tk.getImage(s + Integer.toString(291) + str);
		} catch (Exception IO) {}
	}
	
	public NormalTower(int x, int y){
		super(NORMAL_SPEED, NORMAL_RANGE, NORMAL_DAMAGE);
		this.setX(x);
		this.setY(y);
		
		try {
			icon = tk.getImage(s + Integer.toString(291) + str);
		} catch (Exception IO) {}
		this.bullet.setIcon("296");
	}
	
}
