package civGame;

public class Gold {
	private int money;
	public Gold(){
		
	}
	public int getGold(){
		return money;
	}
	public void addGold(int add){
		money+=add;
	}
	public void removeGold(int sub){
		money-=sub;
	}
}
