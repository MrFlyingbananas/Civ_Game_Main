package civGame;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.event.MouseEvent;

import javax.swing.ImageIcon;

public class World {

	
	public Rectangle[][] blocks;
	public static Image[][] blockImg;
	public boolean hovering;
	int hoverX, hoverY;
	public int selectX, selectY;
	public static int selectPlace1 = -1;
	public static int selectPlace2 = -1;
	Image selectBlockImg;
	Rectangle selectBlock;
	boolean select, hover;
	public World(){
		blocks = new Rectangle[Screen.GRID_LENGTH][Screen.GRID_LENGTH];
		blockImg = new Image[Screen.GRID_LENGTH][Screen.GRID_LENGTH];
		setArrays();
	}
	
	private void setArrays(){
		for(int i = 0; i<Screen.GRID_LENGTH/Screen.BLOCK_SIZE;i++){
			for(int e = 0; e<Screen.GRID_LENGTH/Screen.BLOCK_SIZE;e++){
				blockImg[i][e] = TileImgs.GRASS;
				blocks[i][e] = new Rectangle(i*Screen.BLOCK_SIZE,e*Screen.BLOCK_SIZE, Screen.BLOCK_SIZE, Screen.BLOCK_SIZE);
			}
		}
	}
    
    public void draw(Graphics g){
    	for(int i = 0; i<Screen.GRID_LENGTH/Screen.BLOCK_SIZE;i++){
			for(int e = 0; e<Screen.GRID_LENGTH/Screen.BLOCK_SIZE;e++){
				g.drawImage(blockImg[i][e], blocks[i][e].x, blocks[i][e].y,null);
			}
		}
    	drawHoverOutline(g);
    	drawSelectOutline(g);
    }
    public void drawHoverOutline(Graphics g){
    	if(hover){
    		g.setColor(Color.blue);
    		g.drawRect(hoverX, hoverY, Screen.BLOCK_SIZE, Screen.BLOCK_SIZE);
    	}
    }
    public void drawSelectOutline(Graphics g){
    	if(select){
    		g.setColor(Color.yellow);
    		g.drawRect(selectX, selectY, Screen.BLOCK_SIZE, Screen.BLOCK_SIZE);
    	}
    }
    public void mouseMoved(MouseEvent e){
    	int x = e.getX();
    	int y = e.getY();
    	for(int j = 0; j < Screen.GRID_LENGTH/Screen.BLOCK_SIZE; j++){
     		for(int i = 0; i < Screen.GRID_LENGTH/Screen.BLOCK_SIZE; i++){
				if( x > blocks[i][j].x && x < blocks[i][j].x + Screen.BLOCK_SIZE &&
    				y > blocks[i][j].y && y < blocks[i][j].y + Screen.BLOCK_SIZE){
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
    	for(int j = 0; j < Screen.GRID_LENGTH/Screen.BLOCK_SIZE; j++){
     		for(int i = 0; i < Screen.GRID_LENGTH/Screen.BLOCK_SIZE; i++){
				if( x > blocks[i][j].x && x < blocks[i][j].x + Screen.BLOCK_SIZE &&
    				y > blocks[i][j].y && y < blocks[i][j].y + Screen.BLOCK_SIZE){
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
}
