package civGame;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class MainGame extends JFrame implements ActionListener{
	JButton bAddFarm;
	JButton bAddMine;
	JButton bAddHouse;
	JButton bAddWell;
	JLabel food, gold, stone, water, population;
	JLabel foodPD, goldPD, stonePD, waterPD;
	JFrame j;
	Framework fw;
	JPanel bp, ip;
	public MainGame(){
		fw = new Framework();
		ip = new JPanel();
		bp = new JPanel();
	    setSize(Screen.GAME_WINDOW_WIDTH, Screen.GAME_WINDOW_HEIGHT);
	    setLocationRelativeTo(null);
	    setTitle("Farming Simulator");
	    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    setVisible(true);
	    setResizable(false);
	    add(fw);
	    add(bp, BorderLayout.SOUTH);
	    add(ip, BorderLayout.NORTH);
	    bp.setBackground(Color.black);
	    initButtons();
	    initLabels();
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
		bp.add(bAddFarm);
	    bp.add(bAddMine);
	    bp.add(bAddHouse);
	    bp.add(bAddWell);
	    
	    validate();
	}
	private void initLabels() {
		food = new JLabel(String.valueOf(fw.food));
		foodPD = new JLabel(String.valueOf(fw.foodPD));
		water = new JLabel(String.valueOf(fw.water));
		waterPD = new JLabel(String.valueOf(fw.waterPD));
		gold = new JLabel(String.valueOf(fw.gold));
		goldPD = new JLabel(String.valueOf(fw.goldPD));
		stone = new JLabel(String.valueOf(fw.stone));
		stonePD = new JLabel(String.valueOf(fw.stonePD));
		population = new JLabel(String.valueOf(fw.population));
		ip.add(food);
		ip.add(foodPD);
		ip.add(water);
		ip.add(waterPD);
		ip.add(gold);
		ip.add(goldPD);
		ip.add(stone);
		ip.add(stonePD);
		ip.add(population);
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
