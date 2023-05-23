package project;

/**
 * item class for athletes 
 * increase stats: offense defense
 * one time use
 * 
 */

public class Item extends Purchasable {
	
	/**
	 * item name
	 */
	private int defence;
	private int offence;
	
	private String name;
	/** item name
	 * 
	 */
	
	//private String description;
	/** item description
	 * 
	 */
	
	//private int cost;
	/**
	 * item class constructor 
	 *@param name a string representing the name of the item
	 
	 * @param offence a integer value that represents the offence stat of the item
	 * @param defence a integer value that represents  the defence stat of the item
	 * @param contractPrice an integer value that represents the cost of the item
	 * @param sellbackPrice an integer value that represents the sell price of the item
	 * @param description a string that gives a description of the item
	 */
	public Item(String name, int offence, int defence, int contractPrice, int sellbackPrice, String description) {
		super(contractPrice, sellbackPrice, description);
		this.name = name;
		this.setOffence(offence);
		this.setDefence(defence);
	}
	
	
	/**
	 * Name getter method
	 * @return String name of Item
	 */
	public String getName() {
		return name;
		
	}


	/**
	 * offence getter method
	 * @return the int offence stat
	 * 
	 */
	public int getOffence() {
		return offence;
	}


	/**sets offence stat
	 * @param offence the offence to set
	 */
	public void setOffence(int offence) {
		this.offence = offence;
	}


	/**
	 * getter for defence
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
	
	
	/**
	 * Returns a String representation of offence, defence, description, contract price and sell back price
	 * @return a string representation of description, offence, defence, contract price and sell back price
	 */
	public String toString() {
		String result = null;
    	result += (this.getName());
    	result += (this.getDescription());
    	
        result += ("<br>Increase Offensive: +" + this.getOffence());
        result += ("<br>Increase Defensive: +" + this.getDefence());
        result += ("<br>Cost: " + this.getContractPrice());
        result += ("<br>Sell price: " + this.getSellBackPrice());
         
        
        return result;
	}
	
	
/**
 * Returns a String representation in HTML of offence, defence, description, contract price and sell back price
 * @return a string representation of description, offence, defence, contract price and sell back price
 */
	public String toStringHTML() {
		String result = "<html>" +(this.getDescription());    	
        result += ("<br>Offensive Statistic: +" + this.getOffence());
        result += ("<br>Defensive Statistic: +" + this.getDefence());
        result += ("<br>Cost: " + this.getContractPrice());
        result += ("<br>Sell price: " + this.getSellBackPrice()+"<html>");
        
        return result;
	}
	
		


}
