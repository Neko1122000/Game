package initiate;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.*;

public class GameStage extends JFrame implements MouseListener{
	private GameField f = new GameField();
	private Point mouse = new Point(0, 0);
	private int kind = 0;
	
	public void  build (){
		setTitle("Tower Defense");
		setSize(1000, 700);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setResizable(false);
		setLocationRelativeTo(null);
		setContentPane(f);
		getContentPane().setBackground( Color.WHITE );
		addMouseListener(this);
		//setVisible(true);
	}
	
	public void newTower(int kind){
		if (!f.getPoint(mouse)) return;
		f.addTower(mouse, kind);
		return ;
	}
	
	@Override
    public void mouseClicked(MouseEvent e) {
        int x = e.getX();
        int y = e.getY();
        mouse.x = x;
        mouse.y = y;
        f.setMouse(new Point(x, y));
    }
 
    @Override
    public void mouseEntered(MouseEvent e) {
    }
 
    @Override
    public void mouseExited(MouseEvent e) {
    }
 
    @Override
    public void mousePressed(MouseEvent e) {
    	int x = e.getX();
        int y = e.getY();
        mouse.x = x;
        mouse.y = y;
        f.setMouse(mouse);
        if ((12*64 <= mouse.x && mouse.x < 13*64) && (0 <= mouse.y && mouse.y < 64)) {
	        kind = 0;
        } else if ((12*64 <= mouse.x && mouse.x < 13*64) && (64 <= mouse.y && mouse.y < 2*64)){
        	kind = 1;
        } else if ((12*64 <= mouse.x && mouse.x < 13*64) && (2*64 <= mouse.y && mouse.y < 3*64)) {
        	kind = 2;
        } else if ((12*64 <= mouse.x && mouse.x < 13*64) && (3*64 <= mouse.y && mouse.y < 4*64)){
        	kind = 3;
        }
    }
 
    @Override
    public void mouseReleased(MouseEvent e) {
    	int x = e.getX();
        int y = e.getY();
        mouse.x = x;
        mouse.y = y;
        f.setMouse(mouse);
        if (kind != 3) newTower(kind);
        else f.deleteTower(mouse);
        //System.out.println("Mouse at X: " + x + " - Y: " + y);
    }
}
