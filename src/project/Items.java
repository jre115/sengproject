package project;

/**
 * item class for athletes 
 * increase stats: offense defense
 * one time use
 */

public class Items {
	/**
	 * item name
	 */
	
	
	private String name;
	/** item description
	 * 
	 */
	
	private String description;
	/** item cost
	 * 
	 */
	
	private int cost;
	/**create item
	 * 
	 * @param name
	 * @param description
	 * @param price
	 */
	
	public Items(String name, String description, int price) {
		this.name = name;
		this.description = description;
		this.cost = price;
	}
	
	
	/**
	 * 
	 * @return name
	 */
	public String getName() {
		return name;
		
	}
	/**
	 * 
	 * @return Cost
	 */
	public int getCost() {
		return cost;
		
	}
	/**
	 * 
	 * @return description
	 */
	public String getDescription() {
		return description;
	}
	
}
	

