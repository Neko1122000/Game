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
		JFrame frame = new JFrame();
		JPanel p = new JPanel();
		
		GameStage view = new GameStage();
	    view.build();
	    
	    frame.setTitle("Tower Defense");
		frame.setSize(1000, 700);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setResizable(false);
		frame.setLocationRelativeTo(null);
		
		Button b=new Button("New Game");  
		b.setBounds(50,100,60,30);  
		b.addActionListener(new ActionListener(){  
			public void actionPerformed(ActionEvent e){  
				 frame.setVisible(false);
				 view.setVisible(true);
		     }  
		 }); 
		p.setLayout(null);
		p.add(b);
		b.setBounds(450, 275, 100, 50);
		//frame.setLayout(null);
		frame.setVisible(true);
		frame.getContentPane().add(p);
	     
	     //Kluge; time to get images read/rendered
	      
	    for(;;) {
	     try {
	  	      Thread.sleep(40);
	  	       view.repaint();
	  	    } catch (InterruptedException ie) {}
	     }
	     
	   }
}
