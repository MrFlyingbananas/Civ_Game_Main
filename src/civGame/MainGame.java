package civGame;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

public class MainGame extends JFrame implements ActionListener{
	private JButton bAddFarm, bAddMine, bAddWell, bAddHouse, bOpenCostMenu;
	public static JLabel food, gold, stone, water, population;
	public static JLabel foodPD, goldPD, stonePD, waterPD;
	private JLabel line;
	private Framework fw;
	private JPanel ip;
	private CostMenu costMenu;
	public MainGame(){
		fw = new Framework();
	    setSize(GameSettings.GAME_DIM);
	    setLocationRelativeTo(null);
	    setTitle("Farming Simulator");
	    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    setVisible(true);
	    setResizable(false);
	    add(fw);
	    
	    initJPanes();
	    initButtons();
	    initLabels();
	    createMenu();
	}
	private void initJPanes(){
		ip = new JPanel();
	    add(ip, BorderLayout.EAST);
	    ip.setLayout(new BoxLayout(ip, BoxLayout.Y_AXIS));
	    ip.setBackground(Color.black);
	    ip.add(Box.createHorizontalGlue());
	    ip.setBorder(new EmptyBorder(60,10,0,50));

	    
	   
	}
	private void initButtons() {
		bAddFarm = new JButton("Add a farm");
		bAddMine = new JButton("Add a mine");
		bAddHouse = new JButton("Add a house");
		bAddWell = new JButton("Add a well");
		bOpenCostMenu = new JButton("Open the cost menu");
		bAddFarm.addActionListener(this);
		bAddMine.addActionListener(this);
		bAddHouse.addActionListener(this);
		bAddWell.addActionListener(this);
		bOpenCostMenu.addActionListener(this);
	    validate();
	}
	private void initLabels() {
		food = new JLabel("Food In Storage: "+String.valueOf(fw.food));
		foodPD = new JLabel("Food Per Day: "+String.valueOf(fw.foodPD));
		water = new JLabel("Water In Storage: "+String.valueOf(fw.water));
		waterPD = new JLabel("Water Per Day: "+String.valueOf(fw.waterPD));
		gold = new JLabel("Gold In Storage: "+String.valueOf(fw.gold));
		goldPD = new JLabel("Gold Per Day: "+String.valueOf(fw.goldPD));
		stone = new JLabel("Stone In Storage: "+String.valueOf(fw.stone));
		stonePD = new JLabel("Stone Per Day: "+String.valueOf(fw.stonePD));
		population = new JLabel("Population: "+String.valueOf(fw.population));
		line = new JLabel("___________________");
	}
	private void createMenu(){
		ip.add(population);
		ip.add(Box.createVerticalStrut(30));
		ip.add(food);
		ip.add(Box.createVerticalStrut(30));
		ip.add(water);
		ip.add(Box.createVerticalStrut(30));
		ip.add(gold);
		ip.add(Box.createVerticalStrut(30));
		ip.add(stone);
		ip.add(Box.createVerticalStrut(30));
		ip.add(foodPD);
		ip.add(Box.createVerticalStrut(30));
		ip.add(waterPD);
		ip.add(Box.createVerticalStrut(30));
		ip.add(goldPD);
		ip.add(Box.createVerticalStrut(30));
		ip.add(stonePD);
		ip.add(Box.createVerticalStrut(30));
		ip.add(line);
		ip.add(Box.createVerticalStrut(30));
		ip.add(bAddFarm);
		ip.add(Box.createVerticalStrut(30));
	    ip.add(bAddMine);
	    ip.add(Box.createVerticalStrut(30));
	    ip.add(bAddHouse);
	    ip.add(Box.createVerticalStrut(30));
	    ip.add(bAddWell);
	    ip.add(Box.createVerticalStrut(30));
	    ip.add(bOpenCostMenu);
		food.setForeground(Color.white);
		foodPD.setForeground(Color.white);
		water.setForeground(Color.white);
		waterPD.setForeground(Color.white);
		gold.setForeground(Color.white);
		goldPD.setForeground(Color.white);
		stone.setForeground(Color.white);
		stonePD.setForeground(Color.white);
		population.setForeground(Color.white);
		

	}
	
	public static void main (String[] args){
		MainGame m = new MainGame();						
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		int errorCheck;
		if(e.getSource() == bAddFarm){
			errorCheck = fw.addFarm();
			if(errorCheck == GameSettings.ERROR_NONE){
				foodPD.setText(String.valueOf("Food Per Day: "+String.valueOf(fw.foodPD)));
				gold.setText("Gold In Storage: "+String.valueOf(fw.gold));
			}else{
				errorPopup(errorCheck);
			}
			
		}else if(e.getSource() == bAddMine){
			errorCheck = fw.addMine();
			if(errorCheck == GameSettings.ERROR_NONE){
				goldPD.setText("Gold Per Day: "+String.valueOf(fw.goldPD));
				stonePD.setText("Stone Per Day: "+String.valueOf(fw.stonePD));
				population.setText("Population: "+String.valueOf(fw.population));
			}else{
				errorPopup(errorCheck);
			}
			
		}else if(e.getSource() == bAddHouse){
			errorCheck = fw.addHouse();
			if(errorCheck == GameSettings.ERROR_NONE){
				population.setText("Population: "+String.valueOf(fw.population));
				gold.setText("Gold In Storage: "+String.valueOf(fw.gold));
			}else{
				errorPopup(errorCheck);
			}
			
		}else if(e.getSource() == bAddWell){
			errorCheck = fw.addWell();
			if(errorCheck == GameSettings.ERROR_NONE){
				waterPD.setText("Water Per Day: "+String.valueOf(fw.waterPD));
				stone.setText("Stone in Storage"+String.valueOf(fw.stone));
			}else{
				errorPopup(errorCheck);
			}
		}else if(e.getSource() == bOpenCostMenu){
			if(costMenu == null){
				costMenu = new CostMenu();
			}else if(costMenu != null && !costMenu.isShowing()){
				costMenu.dispose();
				costMenu = new CostMenu();
			}else{
				costMenu.requestFocus();
			}
		}
	}
	public void nullCostMenu(){
		costMenu = null;
	}
	private void errorPopup(int errorNum){
		switch(errorNum){
			case GameSettings.ERROR_NONE:
				
				break;
			case GameSettings.ERROR_OCCUPIED_TILE:
				JOptionPane.showMessageDialog(null, "You cannot place a building ontop of another!");
				break;
			case GameSettings.ERROR_INSUFFICENT_RESOURCES:
				JOptionPane.showMessageDialog(null, "You do not have enough supplies for that operation!");
				break;
			case GameSettings.ERROR_CODE:
				JOptionPane.showMessageDialog(null, "Error in code.");
				break;
		}
	}
}
