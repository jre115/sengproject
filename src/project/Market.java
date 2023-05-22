package project;

import java.util.ArrayList;

import java.util.Random;

/**
 * The Market class represents a marketplace where items and athletes are available for purchase.
 *It provides methods for managing the market
 *
 */
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

	
	/**
	 * Constructs an arraylist(shopItems) of items and randomly adds 4 items to a new arraylist (selectedItems) and returns selected items
	 * @return selected arraylist of random items
	 */
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
	
	

	
	/**
	 * generates a random number between 3-5 and adds that number of random athletes to the arraylist
	 * The current week to increases all athletes' statistics in the shop as the weeks increase
	 * @param currentWeek and integer value that represents the current week of the game and scale of statistic increase
	 * @return an array list(shop athletes) of random athletes between 3-5
	 */
	public ArrayList<Athlete> playerShop(int currentWeek) {
		Random R = new Random();
		int min = 3;
		int max = 5;
		
		int Num = R.nextInt(max - min + 1) + min; 
		
		ArrayList<Athlete> ShopAthletes = new ArrayList<Athlete>();
		
		
		for (int i = 1; i <= Num; i ++) {
			Athlete athlete = new Athlete();
			double range = 0.95 + (R.nextDouble() * 0.05);
	        double statIncrease = range + (currentWeek * 0.05);
	        athlete.increaseStatistics(statIncrease);
			athlete.setContractPrice(athlete.generateContractPrice());
			ShopAthletes.add(athlete);
		}
		
		return ShopAthletes;
		
		
		
		/// to do test if works, make items, random array list of items
	}
	/**
	 * gets an array list of random athletes
	 * @return currentAthletesAvailable 
	 */
	public ArrayList<Athlete> getShopAthletes() {
		return currentAthletesAvailable;
	}
	/**
	 * gets an array list of random items
	 * @return currentItemsAvailable
	 */
	public ArrayList<Item> getShopItems() {
		return currentItemsAvailable;
	}
	
	/**
	 * sets selectedItem to variable(currentItemsAvailable) and shop athletes to vairble(currentAthletesAvailable)
	 * @param currentWeek and integer value that represents the current week of the game

	 */
	public void refreshMarket(int currentWeek) {
		currentItemsAvailable = itemShop();
		currentAthletesAvailable = playerShop(currentWeek);
	}
	
	/**
	 * method that removes athlete form array list currentAthletesAvailable
	 * @param athlete the athlete that is removed from currentAthletesAvailable
	 */
	public void purchaseAthlete(Athlete athlete) {
		currentAthletesAvailable.remove(athlete);
	}
	
	/**
	 * method that removes item from array list currentItemsAvailable
	 * @param item the item is removed item from  array list currentItemsAvailable
	 */
	public void purchaseItem(Item item) {
		currentItemsAvailable.remove(item);
	}
	


	
}

