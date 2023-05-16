package project;

/**
 * item class for athletes 
 * increase stats: offense defense
 * one time use
 * JR NOTE NEED 4 different types of items e.g. training equipment and foods
 */

public class Item extends Purchasable {
	
	/**
	 * item name
	 */
	private int defence;
	private int offence;
	
	//JR NOTE: removed variables and methods that are inherited from Purchasable
    //private int contractPrice;
    //private int sellbackPrice;
	
	
	private String name;
	/** item name
	 * 
	 */
	
	//private String description;
	/** item description
	 * 
	 */
	
	//private int cost;
	/**create item
	 * 
	 * @param name
	 * @param description
	 * @param price
	 * @param offence 
	 * @param defence 
	 */
	
	public Item(String name, int offence, int defence, int contractPrice, int sellbackPrice, String description) {
		super(contractPrice, sellbackPrice, description);
		this.name = name;
		this.setOffence(offence);
		this.setDefence(defence);
	}
	
	
	/**
	 * 
	 * @return name
	 */
	public String getName() {
		return name;
		
	}


	/**
	 * @return the offence
	 */
	public int getOffence() {
		return offence;
	}


	/**
	 * @param offence the offence to set
	 */
	public void setOffence(int offence) {
		this.offence = offence;
	}


	/**
	 * @return the defence
	 */
	public int getDefence() {
		return defence;
	}


	/**
	 * @param defence the defence to set
	 */
	public void setDefence(int defence) {
		this.defence = defence;
	}
	
	public String toString() {
		String result = null;
    	result += (this.getName());
    	result += (this.getDescription());
    	
        result += ("<br>Offensive Statistic: " + this.getOffence());
        result += ("<br>Defensive Statistic: " + this.getDefence());
        result += ("<br>Cost: " + this.getContractPrice());
        result += ("<br>Sell price: " + this.getSellBackPrice());
         
        
        return result;
	}
	
	

	public String toStringHTML() {
		String result = "<html>" +(this.getName());
    	result += (this.getDescription());
    	
        result += ("<br>Offensive Statistic: " + this.getOffence());
        result += ("<br>Defensive Statistic: " + this.getDefence());
        result += ("<br>Cost: " + this.getContractPrice());
        result += ("<br>Sell price: " + this.getSellBackPrice()+"<html>");
         
        
        return result;
	}
        
		


}
