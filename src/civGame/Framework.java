package civGame;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JPanel;
import javax.swing.Timer;

public class Framework extends JPanel implements Runnable, ActionListener{
	//Double buffering
	private Image dbImage;
	private Graphics dbG;
	//game vars
	private Thread game;
	private Timer timer;
	private volatile boolean running = false;
	private long period = 6*1000000;  //ms >nano
	private static final int DELAYS_BEFORE_PAUSE = 10;
	public int foodPD, waterPD, stonePD, goldPD;
	public int food, water, stone, gold, population;
	//game objects
	private World world;
	public Framework(){
		world = new World();
		setPreferredSize(Screen.GAME_DIM);
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
		addMouseListener(new MouseAdapter(){
			@Override
			public void mousePressed(MouseEvent e){
				
			}
			@Override
			public void mouseReleased(MouseEvent e){
				
			}
			@Override
			public void mouseClicked(MouseEvent e){
				world.mouseCLicked(e);
			}
		});
		addMouseMotionListener(new MouseAdapter(){
			@Override
			public void mouseMoved(MouseEvent e){
				world.mouseMoved(e);
			}
			@Override
			public void mouseDragged(MouseEvent e){
				
			}
			@Override
			public void mouseEntered(MouseEvent e){
				
			}
			@Override
			public void mouseExited(MouseEvent e){
				
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
					Thread.sleep(sleepTime/1000000);
					overSleepTime = 0;
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				//Diff was greater then period
			}else if(diff > period){
				overSleepTime = diff - period;
				//adds up delay
			}else if(++delays > DELAYS_BEFORE_PAUSE){
				Thread.yield();
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
					"Population		"+population+"\n"+
					"Gold Per Day:	"+goldPD+"\n"+
					"Stone Per Day	"+stonePD+"\n"+
					"Food Per Day:	"+foodPD+"\n"+
					"Water Per Day  "+waterPD+"\n"
					
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
			dbImage = createImage(Screen.GAME_WINDOW_WIDTH, Screen.GAME_WINDOW_HEIGHT);
			if(dbImage == null){
				System.err.println("dbImage is null");
				return;
			}else{
				dbG = dbImage.getGraphics();
			}
		}
		dbG.setColor(Color.white);
		dbG.fillRect(0,0,Screen.GAME_WINDOW_WIDTH,Screen.GAME_WINDOW_HEIGHT);
		
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
	public void addFarm(){
		World.blockImg[World.selectPlace1][World.selectPlace2] = TileImgs.FARM;
		foodPD+=TileVars.FARM_FOOD_YILED;
	}
	public void addMine(){
		World.blockImg[World.selectPlace1][World.selectPlace2] = TileImgs.MINE;
		goldPD+=TileVars.MINE_GOLD_YIELD;
		stonePD+=TileVars.MINE_STONE_YIELD;
	}
	public void addWell(){
		World.blockImg[World.selectPlace1][World.selectPlace2] = TileImgs.WELL;
		waterPD+=TileVars.WELL_WATER_YILED;
	}
	public void addHouse(){
		World.blockImg[World.selectPlace1][World.selectPlace2] = TileImgs.HOUSE;
		population+= TileVars.HOUSE_PEOPLE_YIELD;
	}
	private void startGame(){
		if(game == null || !running){
			game = new Thread(this);
			timer = new Timer(TileVars.DAY_LENGTH*1000, this);
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
	@Override
	public void actionPerformed(ActionEvent e) {
		food+=foodPD;
		water+=waterPD;
		gold+=goldPD;
		stone+=stonePD;
		
	}
}
