import java.awt.Button;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JPanel;

import path.Road;
import initiate.GameStage;


public class Play {
	public static void main(String[] args){
		
		GameStage view = new GameStage();
	    view.build();
	     
	    for(;;) {
	    	if (view.play)
			     try {
			  	      Thread.sleep(40);
			  	       view.repaint();
			  	    } catch (InterruptedException ie) {}
	     }
	     
	   }
}
