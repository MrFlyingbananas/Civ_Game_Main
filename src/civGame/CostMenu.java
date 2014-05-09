package civGame;

import javax.swing.JFrame;
import javax.swing.WindowConstants;

public class CostMenu extends JFrame{
	private boolean running;
	public CostMenu(){
	    setSize(GameSettings.COST_DIM);
	    setLocationRelativeTo(null);
	    setTitle("CostMenu");
	    setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
	    setVisible(true);
	    setResizable(false);
	    running = true;
	}
	public boolean isRunning(){
		return running;
	}
}
