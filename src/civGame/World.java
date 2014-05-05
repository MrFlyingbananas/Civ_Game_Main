package civGame;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;

import javax.swing.ImageIcon;

public class World {

	
	public static Rectangle[][] blocks;
	public Image[][] blockImg;
	private int imgX = 0, imgY = 0;
	private Image BLOCK_GRASS;
	public boolean hovering;
	public World(){
		BLOCK_GRASS = new ImageIcon("res/grass_30.png").getImage();
		blocks = new Rectangle[Screen.GRID_LENGTH][Screen.GRID_LENGTH];
		blockImg = new Image[Screen.GRID_LENGTH][Screen.GRID_LENGTH];
		setArrays();
	}
	
	private void setArrays(){
		for(int i = 0; i<Screen.GRID_LENGTH;i+=30){
			for(int e = 0; e<Screen.GRID_LENGTH;e+=30){
				blockImg[i][e] = BLOCK_GRASS;
				blocks[e][i] = new Rectangle(i,e, Screen.BLOCK_SIZE, Screen.BLOCK_SIZE);
			}
		}
		
		
		
		
     /*   for(int i = 0; i < Screen.GRID_LENGTH; i++){
            if(imgX >= Screen.GRID_LENGTH){
                imgX = 0;
                imgY += Screen.BLOCK_SIZE;
            }
             blockImg[i] = BLOCK_GRASS;
             blocks[i] = new Rectangle(imgX, imgY, Screen.BLOCK_SIZE, Screen.BLOCK_SIZE);
             imgX += Screen.BLOCK_SIZE;
        }
    */}
    
    public void draw(Graphics g){
    	for(int i = 0; i<Screen.GRID_LENGTH;i+=30){
			for(int e = 0; e<Screen.GRID_LENGTH;e+=30){
				g.drawImage(blockImg[i][e], blocks[i][e].x, blocks[i][e].y,null);
			}
		}
    	
        /*for(int i = 0; i < Screen.GRID_LENGTH; i++){
            g.drawImage(blockImg[i], blocks[i].x, blocks[i].y, null);
        }
       */
    }
}
