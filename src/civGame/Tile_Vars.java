package civGame;

public class Tile_Vars {
	public final static int PEOPLE_PER_HOUSE = 10;
	public final static int MINE_PEOPLE_COST = 4;
	public static enum farmVars{
		PEOPLE_COST(100),
		GOLD_COST(4);
		private int num;
		private farmVars(int num){
			num = this.num;
		}
		int getFarmVars(){
			return num;
		}
	}
}
