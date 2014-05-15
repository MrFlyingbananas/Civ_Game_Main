package civGame;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Stack;

import javax.swing.JPanel;
import javax.swing.Timer;

@SuppressWarnings("serial")
public class Framework extends JPanel implements Runnable, ActionListener{
	//game vars
	private Thread game;
	private Timer dayTimer, calcTimer, workerDirectionTimer, workerMoveTimer;
	private volatile boolean running = false;
	private long period = 6*1000000;  //ms >nano
	private static final int DELAYS_BEFORE_PAUSE = 10;
	public int foodPD, waterPD, stonePD, goldPD;
	public int food, water, stone, gold, population;
	private Stack<Worker> workers;
	private ArrayList<Worker> workersBusy;
	public static ArrayList<Rectangle> buildQueue;
	private int waterUsed, foodUsed;
	private int houseNum, farmNum, wellNum, mineNum;
	//game objects
	private World world;
	private Worker worker;
	public Framework(){
		world = new World();
		setVisible(true);
		setFocusable(true);
		requestFocus();
		initVars();
		addKeyListener(new KeyAdapter(){
			public void keyPressed(KeyEvent e){	
		/*		if(e.getKeyCode() == KeyEvent.VK_UP){
					world.navMap(World.MAP_UP);
					world.setSelect(false);
				}
				
				if(e.getKeyCode() == KeyEvent.VK_DOWN){
					world.navMap(World.MAP_DOWN);
					world.setSelect(false);
				}
				if(e.getKeyCode() == KeyEvent.VK_LEFT){
					world.navMap(World.MAP_LEFT);
					world.setSelect(false);
				}
				if(e.getKeyCode() == KeyEvent.VK_RIGHT){
					world.navMap(World.MAP_RIGHT);
					world.setSelect(false);
				}
				System.out.println("KeyPressed");
			}
			public void keyReleased(KeyEvent e){
				if(e.getKeyCode() == KeyEvent.VK_UP){
					world.stopYMove();
				}
				if(e.getKeyCode() == KeyEvent.VK_DOWN){
					world.stopYMove();
				}
				if(e.getKeyCode() == KeyEvent.VK_LEFT){
					world.stopXMove();
				}
				if(e.getKeyCode() == KeyEvent.VK_RIGHT){
					world.stopXMove();
				}
				System.out.println("KeyReleased");
			}
			public void keyTyped(KeyEvent e){
				if(e.getKeyCode() == KeyEvent.VK_UP){
					if(world.Screen.y > world.blocks[0][0].y+10){
						world.navMap(World.MAP_UP);
						world.setSelect(false);
					}else{
						world.stopYMove();
					}	
				}
				System.out.println("keyTyped");
			*/}
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
				world.mouseDragged(e);
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
			//GameMenu.refreshAllJLabels();
			gameUpdate();
			//gameRender();
			//paintGameSettings();
			repaint();
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
			//log(
					//"beforeTime:	"+beforeTime+"\n"+
					//"afterTime:		"+afterTime+"\n"+
					//"diff:			"+diff+"\n"+
					//"sleepTime:		"+sleepTime / 1000000+"\n"+
					//"overSleepTime: "+overSleepTime+"\n"+
					//"delays:		"+delays+"\n"+
					//"Population		"+population+"\n"+
					//"Gold Per Day:	"+goldPD+"\n"+
					//"Stone Per Day	"+stonePD+"\n"+
					//"Food Per Day:	"+foodPD+"\n"+
					//"Water Per Day  "+waterPD+"\n"+
					//"Water:			"+water+"\n"
		//	);
			
		}
	}

	private void initVars(){
		food = GameSettings.STARTING_FOOD;
		water = GameSettings.STARTING_WATER;
		stone = GameSettings.STARTING_STONE;
		gold = GameSettings.STARTING_GOLD;
		population = GameSettings.STARTING_POPULATION;
		foodPD = GameSettings.STARTING_FOOD_PER_DAY;
		waterPD = GameSettings.STARTING_WATER_PER_DAY;
		stonePD = GameSettings.STARTING_STONE_PER_DAY;
		goldPD = GameSettings.STARTING_GOLD_PER_DAY;
		houseNum = GameSettings.STARTING_HOUSE_NUM;
		farmNum = GameSettings.STARTING_FARM_NUM;
		wellNum = GameSettings.STARTING_WELL_NUM;
		mineNum = GameSettings.STARTING_MINE_NUM;
		workers = new Stack<Worker>();
		workersBusy = new ArrayList<Worker>();
		buildQueue = new ArrayList<Rectangle>();
		workers.push(new Worker(200,200));
		workers.push(new Worker(400,400));
	}
	private void gameUpdate(){
		world.moveMap();
		if(!buildQueue.isEmpty() && buildQueue != null){
			if(!workers.isEmpty() && workers != null){
				if(buildQueue.size()-1 != workersBusy.size()-1){
					workersBusy.add(workers.pop());
					workersBusy.get(workersBusy.size()-1).setBuild(buildQueue.remove(buildQueue.size()-1));
				}
			}
		}
		if(!workersBusy.isEmpty()){
			Worker badWorker = null;
			for(Worker w : workersBusy){
				if(!w.isWorking()){
					badWorker = w;
				}
			}
			if(badWorker != null){
				workers.push(badWorker);
				workersBusy.remove(badWorker);
			}
		}
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		Object r = e.getSource();
		if(r == dayTimer){
			food+=foodPD;
			water+=waterPD;
			gold+=goldPD;
			stone+=stonePD;
		
			GameMenu.food.setText("Food In Storage: "+String.valueOf(food));
			GameMenu.water.setText("Water In Storage: "+String.valueOf(water));
			GameMenu.gold.setText("Gold In Storage: "+String.valueOf(gold));
			GameMenu.stone.setText("Stone In Storage: "+String.valueOf(stone));
		}else if(r == calcTimer){
			calcPDVars();
			GameMenu.refreshAllJLabels();
			
		}else if(r == workerDirectionTimer){
			if(!workers.isEmpty()){
				for(Worker w : workers){
					w.newDirection();
				}
			}
		}else if(r == workerMoveTimer){
			if(!workers.isEmpty()){
				for(Worker w : workers){
					w.move();
				}
			}
			if(!workersBusy.isEmpty()){
				for(Worker w : workersBusy){
					w.buildMove();
				}
			}
		}
	}
	public void calcPDVars(){
		if(GameMenu.upgradeMenu != null && GameMenu.upgradeMenu.farmIsUpgraded()){
			waterUsed = (houseNum*GameSettings.HOUSE_WATER_COST)
					+(farmNum*GameSettings.FARM_UPGRADE_WATER_USAGE_MULT*GameSettings.FARM_WATER_COST);
					foodUsed = houseNum*GameSettings.HOUSE_FOOD_COST;
		}else{
			waterUsed = (houseNum*GameSettings.HOUSE_WATER_COST)+(farmNum*GameSettings.FARM_WATER_COST);
			foodUsed = houseNum*GameSettings.HOUSE_FOOD_COST;
		}
		if(GameMenu.upgradeMenu != null && GameMenu.upgradeMenu.farmIsUpgraded()){
			foodPD = (farmNum*GameSettings.FARM_FOOD_YILED*GameSettings.FARM_UPGRADE_MULT)-foodUsed;
		}else{
			foodPD = (farmNum*GameSettings.FARM_FOOD_YILED)-foodUsed;
		}
		if(GameMenu.upgradeMenu != null && GameMenu.upgradeMenu.wellIsUpgraded()){
			waterPD = (wellNum*GameSettings.WELL_UPGRADE_MULT*GameSettings.WELL_WATER_YILED)-waterUsed;
		}else{
			waterPD = wellNum*GameSettings.WELL_WATER_YILED-waterUsed;
		}
		if(GameMenu.upgradeMenu != null && GameMenu.upgradeMenu.mineIsUpgraded()){
			goldPD =(mineNum*GameSettings.MINE_GOLD_UPGRADE_MULT)*GameSettings.MINE_GOLD_YIELD;
			stonePD = (mineNum*25)*GameSettings.MINE_STONE_UPGRADE_MULT;
		}else{
			goldPD = mineNum*GameSettings.MINE_GOLD_YIELD;
			stonePD = mineNum*GameSettings.MINE_STONE_YIELD;
		}
	}
	public int addFarm(){
		int error = GameSettings.ERROR_NO_BLOCK_SELECTED;
		if(World.selectPlace1 != -1){
			if(World.blockImg[World.selectPlace1][World.selectPlace2] == GameSettings.GRASS &&
					population > GameSettings.FARM_PEOPLE_COST && gold > GameSettings.FARM_GOLD_COST){
			
				World.blockImg[World.selectPlace1][World.selectPlace2] = GameSettings.FARM;
				farmNum++;
				population-=GameSettings.FARM_PEOPLE_COST;
				gold-=GameSettings.FARM_GOLD_COST;
				error = GameSettings.ERROR_NONE;
				buildQueue.add(world.selectBlock);
				world.addQueueBlock(world.selectBlock);
			}else if(World.blockImg[World.selectPlace1][World.selectPlace2] != GameSettings.GRASS){
				error = GameSettings.ERROR_OCCUPIED_TILE;
			
			}else if(population < GameSettings.FARM_PEOPLE_COST || gold < GameSettings.FARM_GOLD_COST){
				error = GameSettings.ERROR_INSUFFICENT_RESOURCES;
			}
		}
		return error;
	}
	public int addMine(){
		int error = GameSettings.ERROR_NO_BLOCK_SELECTED;
		if(World.selectPlace1 != -1 && World.selectPlace2 != -1){
			if(World.blockImg[World.selectPlace1][World.selectPlace2] == GameSettings.GRASS &&
					population > GameSettings.MINE_PEOPLE_COST){
				
				World.blockImg[World.selectPlace1][World.selectPlace2] = GameSettings.MINE;
				mineNum++;
				population-=GameSettings.MINE_PEOPLE_COST;
				error = GameSettings.ERROR_NONE;
				buildQueue.add(world.selectBlock);
				world.addQueueBlock(world.selectBlock);
			}else if(World.blockImg[World.selectPlace1][World.selectPlace2] != GameSettings.GRASS){
				error = GameSettings.ERROR_OCCUPIED_TILE;
			
			}else if(population < GameSettings.MINE_PEOPLE_COST){
				error = GameSettings.ERROR_INSUFFICENT_RESOURCES;
			}
		}
		return error;
	}
	public int addWell(){
		int error = GameSettings.ERROR_NO_BLOCK_SELECTED;
		if(World.selectPlace1 != -1 && World.selectPlace2 != -1){
			if(World.blockImg[World.selectPlace1][World.selectPlace2] == GameSettings.GRASS &&
					stone > GameSettings.WELL_STONE_COST){
			
				World.blockImg[World.selectPlace1][World.selectPlace2] = GameSettings.WELL;
				wellNum++;
				error = GameSettings.ERROR_NONE;
				buildQueue.add(world.selectBlock);
				world.addQueueBlock(world.selectBlock);
			}else if(World.blockImg[World.selectPlace1][World.selectPlace2] != GameSettings.GRASS){
				error = GameSettings.ERROR_OCCUPIED_TILE;
			
			}else if(stone < GameSettings.WELL_STONE_COST){
				error = GameSettings.ERROR_INSUFFICENT_RESOURCES;
			}
		}
		return error;
	}
	public int addWorkshop(){
		int error = GameSettings.ERROR_NO_BLOCK_SELECTED;
		if(World.selectPlace1 != -1 && World.selectPlace2 != -1){
			if(World.blockImg[World.selectPlace1][World.selectPlace2] == GameSettings.GRASS &&
					gold > GameSettings.WORKSHOP_GOLD_COST){
			
				World.blockImg[World.selectPlace1][World.selectPlace2] = GameSettings.WELL;
				gold-=GameSettings.WORKSHOP_GOLD_COST;
				error = GameSettings.ERROR_NONE;
				buildQueue.add(world.selectBlock);
				world.addQueueBlock(world.selectBlock);
			}else if(World.blockImg[World.selectPlace1][World.selectPlace2] != GameSettings.GRASS){
				error = GameSettings.ERROR_OCCUPIED_TILE;
			
			}else if(gold < GameSettings.WORKSHOP_GOLD_COST){
				error = GameSettings.ERROR_INSUFFICENT_RESOURCES;
			}	
		}
		return error;
	}
	public int addHouse(){
		int error = GameSettings.ERROR_NO_BLOCK_SELECTED;
		if(World.selectPlace1 != -1 && World.selectPlace2 != -1){
			if(World.blockImg[World.selectPlace1][World.selectPlace2] == GameSettings.GRASS &&
				gold > GameSettings.HOUSE_GOLD_COST){
				World.blockImg[World.selectPlace1][World.selectPlace2] = GameSettings.HOUSE;
				population+= GameSettings.HOUSE_PEOPLE_YIELD;
				gold-=GameSettings.HOUSE_GOLD_COST;
				houseNum++;
				error = GameSettings.ERROR_NONE;
				buildQueue.add(world.selectBlock);
				world.addQueueBlock(world.selectBlock);
			}else if(World.blockImg[World.selectPlace1][World.selectPlace2] != GameSettings.GRASS){
				error = GameSettings.ERROR_OCCUPIED_TILE;
			}else if(gold < GameSettings.HOUSE_GOLD_COST){
				error = GameSettings.ERROR_INSUFFICENT_RESOURCES;
			}
		}
		return error;
	}
	public int addWorker(){
		int error = GameSettings.ERROR_INSUFFICENT_RESOURCES;
		if(population > 0){
			error = GameSettings.ERROR_NONE;
		}
		return error;
	}
	private void startGame(){
		if(game == null || !running){
			game = new Thread(this);
			dayTimer = new Timer(GameSettings.DAY_LENGTH*1000, this);
			workerDirectionTimer = new Timer(3000, this);
			workerMoveTimer = new Timer(25, this);
			calcTimer = new Timer(100, this);
			dayTimer.start();
			calcTimer.start();
			workerDirectionTimer.start();
			workerMoveTimer.start();
			game.start();
			
			running = true;
		}
		
	}
	public void stopGame(){
		if(running){
			running = false;
		}
	}
	private void log(String s){
		System.out.println(s);
	}
	public void paintComponent(Graphics g){
		super.paintComponent(g);
		world.draw(g);
		if(!workers.isEmpty()){
			for(Worker w : workers){
				w.draw(g);
			}
		}
		if(!workersBusy.isEmpty()){
			for(Worker w : workersBusy){
				w.draw(g);
			}
		}
	}
}
