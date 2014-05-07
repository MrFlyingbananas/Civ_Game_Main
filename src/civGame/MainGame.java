package civGame;

import javax.swing.JFrame;

public class MainGame extends JFrame {
	
	Framework fw;
	public MainGame(){
		fw = new Framework();
	    setSize(Screen.WINDOW_WIDTH, Screen.WINDOW_HEIGHT);
	    setLocationRelativeTo(null);
	    setTitle("Farming Simulator");
	    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    setVisible(true);
	    setResizable(false);
	    add(fw);
	}
	
	public static void main (String[] args){
		MainGame m = new MainGame();						
	}
}
