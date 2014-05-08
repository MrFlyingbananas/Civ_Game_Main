package civGame;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

public class MainGame extends JFrame implements ActionListener{
	JButton bAddFarm;
	JButton bAddMine;
	JButton bAddHouse;
	JButton bAddWell;
	JLabel food, gold, stone, water, population;
	JLabel foodPD, goldPD, stonePD, waterPD;
	JLabel line;
	JFrame j;
	Framework fw;
	JPanel ip;
	public MainGame(){
		fw = new Framework();
		ip = new JPanel();
	    setSize(GameSettings.GAME_DIM);
	    setLocationRelativeTo(null);
	    setTitle("Farming Simulator");
	    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    setVisible(true);
	    setResizable(false);
	    add(fw);
	    add(ip, BorderLayout.EAST);
	    ip.setLayout(new BoxLayout(ip, BoxLayout.Y_AXIS));
	    ip.setBackground(Color.black);
	    ip.add(Box.createHorizontalGlue());
	    ip.setBorder(new EmptyBorder(60,10,0,50));
	    initButtons();
	    initLabels();
	    createMenu();
	}
	
	private void initButtons() {
		bAddFarm = new JButton("Add a farm");
		bAddMine = new JButton("Add a mine");
		bAddHouse = new JButton("Add a house");
		bAddWell = new JButton("Add a well");
		bAddFarm.addActionListener(this);
		bAddMine.addActionListener(this);
		bAddHouse.addActionListener(this);
		bAddWell.addActionListener(this);
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
	public void createMenu(){
		ip.add(food);
		ip.add(Box.createVerticalStrut(30));
		ip.add(foodPD);
		ip.add(Box.createVerticalStrut(30));
		ip.add(water);
		ip.add(Box.createVerticalStrut(30));
		ip.add(waterPD);
		ip.add(Box.createVerticalStrut(30));
		ip.add(gold);
		ip.add(Box.createVerticalStrut(30));
		ip.add(goldPD);
		ip.add(Box.createVerticalStrut(30));
		ip.add(stone);
		ip.add(Box.createVerticalStrut(30));
		ip.add(stonePD);
		ip.add(Box.createVerticalStrut(30));
		ip.add(population);
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
		if(e.getSource() == bAddFarm){
			fw.addFarm();
		}else if(e.getSource() == bAddMine){
			fw.addMine();
		}else if(e.getSource() == bAddHouse){
			fw.addHouse();
		}else if(e.getSource() == bAddWell){
			fw.addWell();
		}
		
	}
}
