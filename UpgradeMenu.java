package civGame;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.WindowConstants;

public class UpgradeMenu extends JFrame implements ActionListener{
	private JPanel jPButtons;
	private JButton bWellUpgrade, bHouseUpgrade, bFarmUpgrade, bMineUpgrade;
	private boolean wellUpgrade, houseUpgrade, farmUpgrade, mineUpgrade;
	public UpgradeMenu(){
		jPButtons = new JPanel();
	    setSize(GameSettings.COST_DIM);
	    setLocationRelativeTo(null);
	    setTitle("Upgrade Menu");
	    setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
	    setVisible(true);
	    setResizable(false);
	    add(jPButtons);
	    initButtons();
	    createMenu();
	}
	private void initButtons(){
		bWellUpgrade = new JButton("Double Well Production("+GameSettings.WELL_UPGRADE_GOLD_COST+" gold)");
		bHouseUpgrade = new JButton("Double house capacity("+GameSettings.HOUSE_UPGRADE_GOLD_COST+" gold");
		bFarmUpgrade = new JButton("Double Farm production("+GameSettings.FARM_UPGRADE_GOLD_COST+" gold, "
									+GameSettings.FARM_UPGRADE_WATER_USAGE_MULT+"x water consumption of farms)");
		bMineUpgrade = new JButton("Double mine production("+GameSettings.MINE_UPGRADE_GOLD_COST+" gold)");
		
		bWellUpgrade.addActionListener(this);
		bHouseUpgrade.addActionListener(this);
		bFarmUpgrade.addActionListener(this);
		bMineUpgrade.addActionListener(this);
	}
	private void createMenu(){
		jPButtons.add(bWellUpgrade);
	    jPButtons.add(Box.createVerticalStrut(20));
	    jPButtons.add(bHouseUpgrade);
	    jPButtons.add(Box.createVerticalStrut(120));
	    jPButtons.add(bFarmUpgrade);
	    jPButtons.add(Box.createVerticalStrut(120));
	    jPButtons.add(bMineUpgrade);

	}
	@Override
	public void actionPerformed(ActionEvent e) {
		Object source = e.getSource();
		if(source == bWellUpgrade){
			if(GameMenu.fw.gold > GameSettings.WELL_UPGRADE_GOLD_COST){
				wellUpgrade = true;
				GameMenu.fw.gold-=GameSettings.WELL_UPGRADE_GOLD_COST;
				bWellUpgrade.setVisible(false);
			}
		}else if(source == bHouseUpgrade){
			if(GameMenu.fw.gold > GameSettings.HOUSE_UPGRADE_GOLD_COST){
				houseUpgrade = true;
				GameMenu.fw.gold-=GameSettings.HOUSE_UPGRADE_GOLD_COST;
				bHouseUpgrade.setVisible(false);
			}
		}else if(source == bFarmUpgrade){
			if(GameMenu.fw.gold > GameSettings.FARM_UPGRADE_GOLD_COST){
				farmUpgrade = true;
				GameMenu.fw.gold-=GameSettings.FARM_UPGRADE_GOLD_COST;
				bFarmUpgrade.setVisible(false);
			}
		}else if(source == bMineUpgrade){
			if(GameMenu.fw.gold > GameSettings.MINE_UPGRADE_GOLD_COST){
				mineUpgrade = true;
				GameMenu.fw.gold-=GameSettings.MINE_UPGRADE_GOLD_COST;
				bMineUpgrade.setVisible(false);
			}
		}
	}
	public boolean wellIsUpgraded(){
		return wellUpgrade;
	}
	public boolean houseIsUpgraded(){
		return houseUpgrade;
	}
	public boolean farmIsUpgraded(){
		return farmUpgrade;
	}
	public boolean mineIsUpgraded(){
		return mineUpgrade;
	}
	
	
}
