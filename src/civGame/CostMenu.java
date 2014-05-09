package civGame;

import javax.swing.JFrame;

public class CostMenu extends JFrame{
	public CostMenu(){
	    setSize(GameSettings.COST_DIM);
	    setLocationRelativeTo(null);
	    setTitle("CostMenu");
	    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    setVisible(true);
	    setResizable(false);
	}
}
