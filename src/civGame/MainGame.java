package civGame;

import javax.swing.JFrame;

public class MainGame extends JFrame {
	
	GamePanel gp;
	public MainGame(){
		gp = new GamePanel();
	    setSize(Screen.WINDOW_WIDTH, Screen.WINDOW_HEIGHT);
	    setLocationRelativeTo(null);
	    setTitle("Farming Simulator");
	    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    setVisible(true);
	    setResizable(false);
	    add(gp);
	}
	
	public static void main (String[] args){
		MainGame m = new MainGame();						
	}
}
