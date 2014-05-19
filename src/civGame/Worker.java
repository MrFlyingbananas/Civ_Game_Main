package civGame;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.Random;


public class Worker {
	private boolean isWorking;
	private int random, xDirection, yDirection;
	private Rectangle worker, buildRectangle;
	public Worker(){
		isWorking = false;
		worker = new Rectangle(0,0,8,8);
		worker.x = 400;
		worker.y = 400;
		newDirection();
	}
	public Worker(int x, int y){
		isWorking = false;
		worker = new Rectangle(0,0,8,8);
		worker.x = x;
		worker.y = y;
		newDirection();
	}
	public boolean isWorking() {
		return isWorking;
	}

	public void newDirection() {
		Random r = new Random();
		random = r.nextInt(4)+1;
		switch(random){
		case 1:
			xDirection = 1;
			break;
		case 2:
			xDirection = -1;
			break;
		case 3:
			yDirection = 1;
			break;
		case 4:
			yDirection = -1;
			break;
		}
	}
	public void draw(Graphics g){
		g.setColor(Color.pink);
		g.fillOval(worker.x, worker.y, 8, 8);
	}
	public void move(){
		if(worker.x > 0 && worker.x < GameSettings.GAME_WINDOW_WIDTH-240){
			worker.x+=xDirection;
		}else if(worker.x == 0){
			worker.x+=6;
			xDirection = 1;
		}else if(worker.x == GameSettings.GAME_WINDOW_WIDTH-240){
			worker.x-=6;
			xDirection = -1;
		}
		
		if(worker.y > 0 && worker.y < GameSettings.GAME_WINDOW_HEIGHT-60){
			worker.y+=yDirection;
		}else if(worker.y == 0){
			worker.y+=6;
			yDirection = 1;
		}else if(worker.y == GameSettings.GAME_WINDOW_HEIGHT-60){
			worker.y-=6;
			yDirection = -1;
		}
	}
	public void setBuild(Rectangle r) {
		isWorking = true;
		buildRectangle = r;
	}
	public void buildMove(){
		if(worker.intersects(buildRectangle)){
			xDirection = 0;
			yDirection = 0;
			isWorking = false;
			World.removeQueueBlock(buildRectangle);
		}else{
			if(worker.x == buildRectangle.x+GameSettings.BLOCK_SIZE/2){
				xDirection = 0;
			}else if(worker.x < buildRectangle.x+GameSettings.BLOCK_SIZE/2){
				xDirection = 1; 
			}else if(worker.x > buildRectangle.x+GameSettings.BLOCK_SIZE/2){
				xDirection = -1;
			}
			if(worker.y == buildRectangle.y+GameSettings.BLOCK_SIZE/2){
				yDirection = 0;
			}else if(worker.y < buildRectangle.y+GameSettings.BLOCK_SIZE/2){
				yDirection = 1;
			}else if(worker.y > buildRectangle.y+GameSettings.BLOCK_SIZE/2){
				yDirection = -1;
			}
		}
		worker.x+=xDirection;
		worker.y+=yDirection;
	}
	
}
