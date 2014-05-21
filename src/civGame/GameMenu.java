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
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

public class GameMenu extends JFrame implements ActionListener{
	private static JButton bAddFarm, bAddMine, bAddWell, bAddHouse, bAddWorkshop;
	public static JLabel food, gold, stone, water, population;
	public static JLabel foodPD, goldPD, stonePD, waterPD;
	private static JLabel line;
	public static Framework fw;
	public static JPanel bottomBorder, topBorder, leftBorder, rightBorder;
	private static JPanel infoPanel;
	public static UpgradeMenu upgradeMenu;
	private static JMenuBar topBar;
	private static JMenu file, game;
	private static JMenuItem jMOpenBlockInfoMenu, jMQuitToTitleScreen, jMQuitToDesktop, jMOpenUpgradeMenu;
	private static JFrame j;
	private static TitleScreen ts;
	public GameMenu(){
		if(ts == null){
			ts = new TitleScreen();
		}

	}
	public static void startGame(){
		j = new JFrame();
	    j.setSize(GameSettings.GAME_DIM);
	    j.setPreferredSize(GameSettings.GAME_DIM);
	    j.setLocationRelativeTo(null);
	    j.setTitle("Farming Simulator");
	    j.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    j.setVisible(true);
	    j.setResizable(false);
	    j.pack();
		fw = new Framework();
		j.add(fw);
		initJPanes();
		initButtons();
		initJMenus();
		initLabels();
		createMenu();
	}
	private static void initJPanes(){
		infoPanel = new JPanel();
		bottomBorder = new JPanel();
		topBorder = new JPanel();
		leftBorder = new JPanel();
		j.add(leftBorder, BorderLayout.SOUTH);
		j.add(topBorder, BorderLayout.NORTH);
		j.add(bottomBorder, BorderLayout.SOUTH);
		j.add(infoPanel, BorderLayout.EAST);
	    infoPanel.setLayout(new BoxLayout(infoPanel, BoxLayout.Y_AXIS));
	    infoPanel.setBackground(Color.black);
	    infoPanel.add(Box.createHorizontalGlue());
	    infoPanel.setBorder(new EmptyBorder(60,10,0,50));
	    leftBorder.setVisible(false);
	    topBorder.setVisible(false);
	    bottomBorder.setVisible(false);
	}
	private static void initJMenus(){
		topBar = new JMenuBar();
		file = new JMenu("File");
		game = new JMenu("Game");
		jMOpenBlockInfoMenu = new JMenuItem("Open Block Information Panel");
		jMOpenUpgradeMenu = new JMenuItem("Open Upgrade Menu");
		jMQuitToDesktop = new JMenuItem("Quit To Desktop");
		jMQuitToTitleScreen = new JMenuItem("Quit To Title Screen");

		jMOpenBlockInfoMenu.addActionListener(new GameMenu());
		jMOpenUpgradeMenu.addActionListener(new GameMenu());
		jMQuitToDesktop.addActionListener(new GameMenu());
		jMQuitToTitleScreen.addActionListener(new GameMenu());
		j.setJMenuBar(topBar);
	}
	private static void initButtons() {
		bAddFarm = new JButton("Add a farm");
		bAddMine = new JButton("Add a mine");
		bAddHouse = new JButton("Add a house");
		bAddWell = new JButton("Add a well");
		bAddWorkshop = new JButton("Add workshop");

		bAddFarm.addActionListener(new GameMenu());
		bAddMine.addActionListener(new GameMenu());
		bAddHouse.addActionListener(new GameMenu());
		bAddWell.addActionListener(new GameMenu());
		bAddWorkshop.addActionListener(new GameMenu());
	}
	private static void initLabels() {
		food = new JLabel("Food In Storage");
		foodPD = new JLabel("Food Per Day");
		water = new JLabel("Water In Storage");
		waterPD = new JLabel("Water Per Day");
		gold = new JLabel("Gold In Storage");
		goldPD = new JLabel("Gold Per Day");
		stone = new JLabel("Stone In Storage");
		stonePD = new JLabel("Stone Per Day");
		population = new JLabel("Population");
		line = new JLabel("Line");
		food.setText("Food In Storage: "+fw.food);
		foodPD.setText("Food Per Day: "+fw.foodPD);
		water.setText("Water In Storage: "+fw.water);
		waterPD.setText("Water Per Day: "+fw.waterPD);
		gold.setText("Gold In Storage: "+fw.gold);
		goldPD.setText("Gold Per Day: "+fw.goldPD);
		stone.setText("Stone In Storage: "+fw.stone);
		stonePD.setText("Stone Per Day: "+fw.stonePD);
		population.setText("Population: "+fw.population);
		line.setText("___________________");
		if(foodPD == null){
			System.out.println("initLabels error");
		}
	}
	private static void createMenu(){
		infoPanel.add(population);
		infoPanel.add(Box.createVerticalStrut(30));
		infoPanel.add(food);
		infoPanel.add(Box.createVerticalStrut(30));
		infoPanel.add(water);
		infoPanel.add(Box.createVerticalStrut(30));
		infoPanel.add(gold);
		infoPanel.add(Box.createVerticalStrut(30));
		infoPanel.add(stone);
		infoPanel.add(Box.createVerticalStrut(30));
		infoPanel.add(foodPD);
		infoPanel.add(Box.createVerticalStrut(30));
		infoPanel.add(waterPD);
		infoPanel.add(Box.createVerticalStrut(30));
		infoPanel.add(goldPD);
		infoPanel.add(Box.createVerticalStrut(30));
		infoPanel.add(stonePD);
		infoPanel.add(Box.createVerticalStrut(30));
		infoPanel.add(line);
		infoPanel.add(Box.createVerticalStrut(30));
		infoPanel.add(bAddFarm);
		infoPanel.add(Box.createVerticalStrut(30));
	    infoPanel.add(bAddMine);
	    infoPanel.add(Box.createVerticalStrut(30));
	    infoPanel.add(bAddHouse);
	    infoPanel.add(Box.createVerticalStrut(30));
	    infoPanel.add(bAddWell);
	    infoPanel.add(Box.createVerticalStrut(30));
	    infoPanel.add(bAddWorkshop);
		food.setForeground(Color.white);
		foodPD.setForeground(Color.white);
		water.setForeground(Color.white);
		waterPD.setForeground(Color.white);
		gold.setForeground(Color.white);
		goldPD.setForeground(Color.white);
		stone.setForeground(Color.white);
		stonePD.setForeground(Color.white);
		population.setForeground(Color.white);
	    topBar.add(file);
	    topBar.add(game);
	    game.add(jMOpenUpgradeMenu);
	    file.add(jMQuitToTitleScreen);
	    file.add(jMQuitToDesktop);

	}

	public static void main (String[] args){
		GameMenu m = new GameMenu();						
	}

	public int test(){
		return fw.goldPD;
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		int errorCheck;
		if(e.getSource() == bAddFarm){
			errorCheck = fw.addFarm();
			errorPopup(errorCheck);

		}else if(e.getSource() == bAddMine){
			errorCheck = fw.addMine();
			errorPopup(errorCheck);


		}else if(e.getSource() == bAddHouse){
			errorCheck = fw.addHouse();
			errorPopup(errorCheck);


		}else if(e.getSource() == bAddWell){
			errorCheck = fw.addWell();
			errorPopup(errorCheck);


		}else if(e.getSource() == bAddWorkshop){
			errorCheck = fw.addWorkshop();
			if(errorCheck == GameSettings.ERROR_NONE){
				bAddWorkshop.setVisible(false);
			}else{
				errorPopup(errorCheck);
			}


		}else if(e.getSource() == jMOpenUpgradeMenu){
			if(!bAddWorkshop.isVisible()){
				if(upgradeMenu == null){
					upgradeMenu = new UpgradeMenu();
				}else if(upgradeMenu != null && !upgradeMenu.isShowing()){
					upgradeMenu.dispose();
					upgradeMenu = new UpgradeMenu();
				}else{
					upgradeMenu.requestFocus();
				}
			}else{
				errorPopup(GameSettings.ERROR_NO_WORKSHOP);
			}
		}else if(e.getSource() == jMQuitToDesktop){
			fw.stopGame();
			super.dispose();
		}else if(e.getSource() == jMQuitToTitleScreen){
			j.dispose();
			ts = null;
			new GameMenu();
		}
	}
	private void errorPopup(int errorNum){
		switch(errorNum){
			case GameSettings.ERROR_NONE:

				break;
			case GameSettings.ERROR_OCCUPIED_TILE:
				JOptionPane.showMessageDialog(null,"You cannot place a building ontop of another!",
						"Occupied Tile Error ",JOptionPane.ERROR_MESSAGE);
				break;
			case GameSettings.ERROR_INSUFFICENT_RESOURCES:
				JOptionPane.showMessageDialog(null, "You do not have enough supplies for that operation!",
						"Insufficent Resources Error", JOptionPane.ERROR_MESSAGE);
				break;
			case GameSettings.ERROR_NO_WORKSHOP:
				JOptionPane.showMessageDialog(null,"You cannot use that menu until you have a workshop!",
						"No Workshop Error ", JOptionPane.INFORMATION_MESSAGE);
				break;
			case GameSettings.ERROR_NO_BLOCK_SELECTED:
				JOptionPane.showMessageDialog(null,"No Block Selected Error","ERROR! No block selected.",JOptionPane.ERROR_MESSAGE);
				break;
			case GameSettings.ERROR_CODE:
				JOptionPane.showMessageDialog(null,"Error in code.", "Code Based Error",JOptionPane.ERROR_MESSAGE);
				break;
		}
	}
	public static void refreshAllJLabels(){
		goldPD.setText("Gold Per Day: "+fw.goldPD);
		stonePD.setText("Stone Per Day: "+fw.stonePD);
		waterPD.setText("Water Per Day: "+fw.waterPD);
		foodPD.setText("Food Per Day: "+fw.foodPD);
		population.setText("Population: "+fw.population);
		food.setText("Food In Storage: "+fw.food);
		water.setText("Water In Storage: "+fw.water);
		gold.setText("Gold In Storage: "+fw.gold);
		stone.setText("Stone In Storage: "+fw.stone);
	}

}