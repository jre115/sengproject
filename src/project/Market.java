package project;

import java.util.ArrayList;
import java.util.Random;
public class Market extends GameEnvironment {
	// Creates new items
	public Item Broom = new Item("Nimbus2023", 5, 5, 1000, 200, "The newest model of broom currently avialble on the market");
	public Item LuckPotion = new Item("LiquidLuck", 7, 7, 1500, 200, "A potion that increaes your Luck");
	public Item Gloves = new Item("Enchanted Gloves", 7, 3, 1000, 300, "Gloves that a blessed with stregnth magic giving you mighty throws");
	public Item Broom2 = new Item("SkySoarer9000", 14, 0, 2000, 1200, "A covted broom with unmatched speed");
	public Item charm = new Item("Anti Cheat Charm", 0, 8, 800, 100, "A magical spell that prevents the opposing team from using any underhanded tactics, such as jinxes or hexes, to try and score");
	public Item TimeTurner = new Item("TimeTurner", 0, 15, 2000, 1000, "A magical device that allows the goalie to go back in time and correct any mistakes they may have made during the game");
	public Item fiveSensePotion = new Item("Five Sense Potion", 6, 6, 1200, 3000, "A potion that hightens the 5 senses");
	
	/// adds items to an array list then randomly selects 3 items to pit in a new array list
	public ArrayList<Item> itemShop() {
	    ArrayList<Item> shopItems = new ArrayList<Item>();
	    shopItems.add(Broom);
	    shopItems.add(LuckPotion);
	    shopItems.add(Gloves);
	    shopItems.add(Broom2);
	    shopItems.add(charm);
	    shopItems.add(TimeTurner);
	    shopItems.add(fiveSensePotion);

	    ArrayList<Item> selectedItems = new ArrayList<Item>();
	    Random random = new Random();
	    while (selectedItems.size() < 3) {
	        int index = random.nextInt(shopItems.size());
	        if (!selectedItems.contains(shopItems.get(index))) {
	            selectedItems.add(shopItems.get(index));
	        }
	    }
	    return selectedItems;
	}
	
	/// generates random number between 3,5
	Random R = new Random();
	int min = 3;
	int max = 5;
	
	int Num = R.nextInt(max - min + 1) + min;
	
	///array list of athletes between 3-5 athletes
	public ArrayList<Athlete> Shop() {
		ArrayList<Athlete> ShopAthletes = new ArrayList<Athlete>();
		
		
		for (int i = 1; i <= Num; i ++) {
			Athlete athlete = new Athlete();
			athlete.setContractPrice(5000);
			ShopAthletes.add(athlete);
		}
		
		return ShopAthletes;
		
		/// to do test if works, make items, random array list of items
	}
	
	public void purchaseAthlete(Athlete athlete, String purchaseType) throws Exception {
	    if (playerMoney < athlete.getContractPrice()) {
	        throw new Exception("Not enough money to purchase athlete's contract!");
	    } 
	    if (reservesList.size() >= 5) {
	        throw new Exception("Reserves List is already full, cannot add more athletes!");
	    }
	    if (purchaseType == "Reserve") {
	    	reservesList.add(athlete);
	    } else {
	    	teamList.add(athlete);
	    	athlete.setPosition(purchaseType);
	    }
	    playerMoney -= athlete.getContractPrice();
	}

	
}

