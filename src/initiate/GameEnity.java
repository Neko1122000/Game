package initiate;
public abstract class GameEnity {
	private	int x;
	private	int y;
	private	int width;
	private	int height;
	
	protected void setX(int x) {this.x = x;}
	protected int getX() {return this.x;}
	
	protected void setY(int x) {this.y = x;}
	protected int getY() {return this.y;}
	
	protected void setWidth(int x) {this.width = x;}
	protected int getWidth() {return this.width;}
	
	protected void setHeight(int x) {this.height = x;}
	protected int getHeight() {return this.height;}
	
}
