package civGame;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.MouseEvent;

public class Player {
	private World world;
	int hoverX;
	int hoverY;
	boolean hovering;
	public Player(World world){
		this.world = world;
	}
    public void drawBlockOutline(Graphics g){
    	g.setColor(Color.blue);
		g.drawRect(hoverX, hoverY, Screen.BLOCK_SIZE, Screen.BLOCK_SIZE);
    	
    }
    public void mouseMoved(MouseEvent e){
    	int x = e.getX();
    	int y = e.getY();
    	for(int i = 0; i < Screen.GRID_LENGTH; i+=30){
    		for(int j = 0; j < Screen.GRID_LENGTH; i+=30){
				if( x > World.blocks[i][j].x && x < World.blocks[i][j].x + Screen.BLOCK_SIZE &&
    				y > World.blocks[i][j].y && y < World.blocks[i][j].y + Screen.BLOCK_SIZE){
    				hovering = true;
    				hoverX = World.blocks[i][j].x;
    				hoverY = World.blocks[i][j].y;
    			}else{
    				hovering = false;
    			}
    		}
    	}
    }
    public void update(){
    	
    }
    public void draw(Graphics g){
    	drawBlockOutline(g);
    }
}
