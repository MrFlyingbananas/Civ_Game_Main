package civGame;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import javax.swing.ImageIcon;

public class World {

	
	public Rectangle[][] blocks;
	public static Image[][] blockImg;
	public boolean hovering;
	int hoverX, hoverY;
	public int selectX, selectY;
	public static int selectPlace1 = -1;
	public static int selectPlace2 = -1;
	public Image selectBlockImg;
	public Rectangle selectBlock;
	public Rectangle Screen;
	private boolean select, hover;
	private int xDirection = 0, yDirection = 0;
	private static ArrayList<Rectangle> buildQueueBlocks;
	public static final int MAP_UP = 0;
	public static final int MAP_DOWN = 1;
	public static final int MAP_LEFT = 2;
	public static final int MAP_RIGHT = 3;
	public World(){
		blocks = new Rectangle[GameSettings.GRID_LENGTH][GameSettings.GRID_LENGTH];
		blockImg = new Image[GameSettings.GRID_LENGTH][GameSettings.GRID_LENGTH];
		setArrays();
		buildQueueBlocks = new ArrayList<Rectangle>();
	}
	
	private void setArrays(){
		for(int i = 0; i<GameSettings.GRID_LENGTH/GameSettings.BLOCK_SIZE;i++){
			for(int e = 0; e<GameSettings.GRID_LENGTH/GameSettings.BLOCK_SIZE;e++){
				blockImg[i][e] = GameSettings.GRASS;
				blocks[i][e] = new Rectangle(i*GameSettings.BLOCK_SIZE,e*GameSettings.BLOCK_SIZE, GameSettings.BLOCK_SIZE, GameSettings.BLOCK_SIZE);
			}
		}
	}
    
    public void draw(Graphics g){
    	for(int i = 0; i<GameSettings.GRID_LENGTH/GameSettings.BLOCK_SIZE;i++){
			for(int e = 0; e<GameSettings.GRID_LENGTH/GameSettings.BLOCK_SIZE;e++){
				g.drawImage(blockImg[i][e], blocks[i][e].x, blocks[i][e].y,null);
			}
		}
    	drawHoverOutline(g);
    	drawSelectOutline(g);
    	drawQueue(g);
    }
    public void drawHoverOutline(Graphics g){
    	if(hover){
    		g.setColor(Color.blue);
    		g.drawRect(hoverX, hoverY, GameSettings.BLOCK_SIZE, GameSettings.BLOCK_SIZE);
    	}
    }
    public void drawSelectOutline(Graphics g){
    	if(select){
    		g.setColor(Color.yellow);
    		g.drawRect(selectX, selectY, GameSettings.BLOCK_SIZE, GameSettings.BLOCK_SIZE);
    	}
    }
    public void mouseMoved(MouseEvent e){
    	int x = e.getX();
    	int y = e.getY();
    	for(int j = 0; j < GameSettings.GRID_LENGTH/GameSettings.BLOCK_SIZE; j++){
     		for(int i = 0; i < GameSettings.GRID_LENGTH/GameSettings.BLOCK_SIZE; i++){
				if( x > blocks[i][j].x && x < blocks[i][j].x + GameSettings.BLOCK_SIZE &&
    				y > blocks[i][j].y && y < blocks[i][j].y + GameSettings.BLOCK_SIZE){
    				hoverX = blocks[i][j].x;
    				hoverY = blocks[i][j].y;
    				hover = true;
    				break;
				}
     		}
    	}
    }
    public void mouseCLicked(MouseEvent e){
    	int x = e.getX();
    	int y = e.getY();
    	for(int j = 0; j < GameSettings.GRID_LENGTH/GameSettings.BLOCK_SIZE; j++){
     		for(int i = 0; i < GameSettings.GRID_LENGTH/GameSettings.BLOCK_SIZE; i++){
				if( x > blocks[i][j].x && x < blocks[i][j].x + GameSettings.BLOCK_SIZE &&
    				y > blocks[i][j].y && y < blocks[i][j].y + GameSettings.BLOCK_SIZE){
    				selectX = blocks[i][j].x;
    				selectY = blocks[i][j].y;
    				selectPlace1 = i;
    				selectPlace2 = j;
    				selectBlockImg = blockImg[i][j];
    				selectBlock = blocks[i][j];
    				select = true;
    				break;
    			}
    		}
    	}
    }
    public void mouseDragged(MouseEvent e){
    	int x = e.getX();
    	int y = e.getY();
    	hover = false;
    	for(int j = 0; j < GameSettings.GRID_LENGTH/GameSettings.BLOCK_SIZE; j++){
     		for(int i = 0; i < GameSettings.GRID_LENGTH/GameSettings.BLOCK_SIZE; i++){
				if( x > blocks[i][j].x && x < blocks[i][j].x + GameSettings.BLOCK_SIZE &&
    				y > blocks[i][j].y && y < blocks[i][j].y + GameSettings.BLOCK_SIZE){
    				selectX = blocks[i][j].x;
    				selectY = blocks[i][j].y;
    				selectPlace1 = i;
    				selectPlace2 = j;
    				selectBlockImg = blockImg[i][j];
    				selectBlock = blocks[i][j];
    				select = true;
    				break;
    			}
    		}
    	}
    }
    public void setSelect(boolean select){
    	this.select = select;
    }
	public void moveMap() {
		for(int j = 0; j < GameSettings.GRID_LENGTH/GameSettings.BLOCK_SIZE; j++){
			for(int i = 0; i < GameSettings.GRID_LENGTH/GameSettings.BLOCK_SIZE; i++){
				blocks[i][j].x+=xDirection;
				blocks[i][j].y+=yDirection;
			}
		}
		
	}
	public void stopXMove(){
		xDirection = 0;
	}
	public void stopYMove(){
		yDirection = 0;
	}
	private void setXDirection(int dir){
		xDirection = dir;
	}
	private void setYDirection(int dir){
		yDirection = dir;
	}
	public void navMap(int nav){
		switch(nav){
		case MAP_UP:
			setYDirection(-GameSettings.MAP_SCROLL_SPEED);
			break;
		case MAP_DOWN:
			setYDirection(GameSettings.MAP_SCROLL_SPEED);

			break;
		case MAP_LEFT:
			setXDirection(-GameSettings.MAP_SCROLL_SPEED);
			break;
		case MAP_RIGHT:
			setXDirection(GameSettings.MAP_SCROLL_SPEED);
			break;
		}
	}

	public void addQueueBlock(Rectangle r) {
		buildQueueBlocks.add(r);
	}
	public static void removeQueueBlock(Rectangle r) {
		buildQueueBlocks.remove(r);
	}
	public void drawQueue(Graphics g){
		g.setColor(Color.red);
		for(Rectangle r : buildQueueBlocks){
			g.drawRect(r.x,r.y,GameSettings.BLOCK_SIZE, GameSettings.BLOCK_SIZE);
		}
	}
	
}
