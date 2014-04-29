package civGame;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.JPanel;

public class GamePanel extends JPanel implements Runnable{
	//Double buffering
	private Image dbImage;
	private Graphics dbG;
	//JPanel variables
	static final Dimension gameDim = new Dimension(Screen.WINDOW_WIDTH, Screen.WINDOW_HEIGHT);
	//game vars
	private Thread game;
	private volatile boolean running = false;
	private long period = 6*1000000;  //ms >nano
	private static final int DELAYS_BEFORE_PAUSE = 10;
	//game objects
	private World world;
	private Gold money = new Gold();
	public GamePanel(){
		world = new World();
		setPreferredSize(gameDim);
		setVisible(true);
		setFocusable(true);
		requestFocus();
		
		addKeyListener(new KeyAdapter(){
			public void keyPressed(KeyEvent e){
				if(e.getKeyCode() == KeyEvent.VK_UP){
				
				}
				else if(e.getKeyCode() == KeyEvent.VK_DOWN){
				
				}
				else if(e.getKeyCode() == KeyEvent.VK_RIGHT){
				
				}
				else if(e.getKeyCode() == KeyEvent.VK_LEFT){
				
				}
				
			}
			public void keyReleased(KeyEvent e){
			
			}
			public void keyTyped(KeyEvent e){
	
			}
		});
	}
	public void addNotify(){
		super.addNotify();
		startGame();
	}
	public void run(){
		long beforeTime, afterTime, diff, sleepTime, overSleepTime = 0;
		int delays = 0;
		while(running){
			beforeTime = System.nanoTime();
			gameUpdate();
			gameRender();
			paintScreen();
			afterTime = System.nanoTime();
			diff = afterTime - beforeTime;
			sleepTime = (period - diff) - overSleepTime;
			if(sleepTime < period && sleepTime > 0){
				try {
					game.sleep(sleepTime/1000000);
					overSleepTime = 0;
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				//Diff was greater then period
			}else if(diff > period){
				overSleepTime = diff - period;
				//adds up delay
			}else if(++delays > DELAYS_BEFORE_PAUSE){
				game.yield();
				delays = 0;
				overSleepTime = 0;
				//loop took less time then calculated but over sleep needs to be made up
			}else{
				overSleepTime = 0;
			}
			log(
					"beforeTime:	"+beforeTime+"\n"+
					"afterTime:		"+afterTime+"\n"+
					"diff:			"+diff+"\n"+
					"sleepTime:		"+sleepTime / 1000000+"\n"+
					"overSleepTime: "+overSleepTime+"\n"+
					"delays:		"+delays+"\n"+
					"Gold:			"+money.getGold()	
			);
		}
	}
	private void gameUpdate() {
		if(running && game != null){
			//update game state
		}
		
	}
	private void gameRender() {
		if(dbImage == null){
			dbImage = createImage(Screen.WINDOW_WIDTH, Screen.WINDOW_HEIGHT);
			if(dbImage == null){
				System.err.println("dbImage is null");
				return;
			}else{
				dbG = dbImage.getGraphics();
			}
		}
		dbG.setColor(Color.white);
		dbG.fillRect(0,0,Screen.WINDOW_WIDTH,Screen.WINDOW_HEIGHT);
		
		draw(dbG);
	}
	private void draw(Graphics g) {
		world.draw(g);
		
	}
	private void paintScreen() {
		Graphics g;
		try{
			g = this.getGraphics();
			if(dbImage != null && g != null){
				g.drawImage(dbImage, 0, 0, null);
			}
			g.dispose();
		}catch(Exception e){
			e.printStackTrace();
		}
		
	}
	
	private void startGame(){
		if(game == null || !running){
			game = new Thread(this);
			game.start();
			running = true;
		}
	}
	public void stopGame(){
		if(running){
			running = false;
		}
	}
	public void log(String s){
		System.out.println(s);
	}
}
