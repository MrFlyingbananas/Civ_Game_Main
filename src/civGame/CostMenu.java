package civGame;

import javax.swing.JFrame;
import javax.swing.WindowConstants;

public class CostMenu extends JFrame{
	public CostMenu(){
	    setSize(GameSettings.COST_DIM);
	    setLocationRelativeTo(null);
	    setTitle("Block Information Panel");
	    setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
	    setVisible(true);
	    setResizable(false);
	}
}
