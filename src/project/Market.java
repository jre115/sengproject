package project;

import java.util.ArrayList;
import java.util.Random;
public class Market   {
	// Creates new items
	public Item Broom = new Item("Fireflyer Charm", 5, 5, 1000, 200, "A charm that enhances your broom's attack and defense capabilities with its enchanting glow");
	public Item LuckPotion = new Item("LiquidLuck", 7, 7, 1500, 200, "A potion that increases your luck");
	public Item Gloves = new Item("Enchanted Gloves", 7, 3, 1000, 300, "Gloves that a blessed with strength magic giving you mighty throws");
	public Item Broom2 = new Item("SkySoarer9000", 14, 0, 2000, 1200, "A covted broom with unmatched speed");
	public Item charm = new Item("Anti Cheat Charm", 0, 8, 800, 100, "A magical spell that prevents the opposing team from using any underhanded tactics");
	public Item TimeTurner = new Item("TimeTurner", 0, 15, 2000, 1000, "A magical device that allows the goalie to go back in time ");
	public Item fiveSensePotion = new Item("Five Sense Potion", 6, 6, 1200, 3000, "A potion that heightens the 5 senses");
	
	ArrayList<Item> currentItemsAvailable;
	ArrayList<Athlete> currentAthletesAvailable;

	
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
	    while (selectedItems.size() < 4) {
	        int index = random.nextInt(shopItems.size());
	        if (!selectedItems.contains(shopItems.get(index))) {
	            selectedItems.add(shopItems.get(index));
	        }
	    }
	    return selectedItems;
	}
	
	/// generates random number between 3,5

	
	///array list of athletes between 3-5 athletes
	public ArrayList<Athlete> playerShop() {
		Random R = new Random();
		int min = 3;
		int max = 5;
		
		int Num = R.nextInt(max - min + 1) + min;
		
		ArrayList<Athlete> ShopAthletes = new ArrayList<Athlete>();
		
		
		for (int i = 1; i <= Num; i ++) {
			Athlete athlete = new Athlete();
			athlete.setContractPrice(5000);
			ShopAthletes.add(athlete);
		}
		
		return ShopAthletes;
		
		
		
		/// to do test if works, make items, random array list of items
	}
	public ArrayList<Athlete> getShopAthletes() {
		return currentAthletesAvailable;
	}
	
	public ArrayList<Item> getShopItems() {
		return currentItemsAvailable;
	}
	
	public void refreshMarket() {
		currentItemsAvailable = itemShop();
		currentAthletesAvailable = playerShop();
	}
	
	public void purchaseAthlete(Athlete athlete) {
		currentAthletesAvailable.remove(athlete);
	}
	
	public void purchaseItem(Item item) {
		currentItemsAvailable.remove(item);
	}
	

	
}

