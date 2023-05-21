package project;

/**
 * The purchasable call represents something that can be purchased and sold such as Athletes and Items.
 * It provides information about hte contract price, sellback price and description of the item
 *
 */
public class Purchasable {

    /**
     * The price at which the item can be purchased.
     */
    private int contractPrice;

    /**
     * The price at which the item can be sold back.
     */
    private int sellbackPrice;

    /**
     * The description of the item.
     */
    private String description;

    /**
     * Constructs a new Purchasable object with default values.
     * This default constructor takes no arguments.
     */
    public Purchasable() {
    }

    /**
     * Constructs a new Purchasable object with the specified contract price, sell-back price, and description.
     *
     * @param contractPrice the price at which the item can be purchased
     * @param sellbackPrice the price at which the item can be sold back for
     * @param description   the description of the item
     */
    public Purchasable(int contractPrice, int sellbackPrice, String description) {
        this.contractPrice = contractPrice;
        this.sellbackPrice = sellbackPrice;
        this.description = description;
    }

    /**
     * Returns the contract price of the item
     * @return the contract price of the item
     */
    public int getContractPrice() {
        return contractPrice;
    }

    /**
     * Returns the formatted contract price of the item.
     * The contract price is formatted as a string with a dollar sign ($) and commas for thousands separators.
     *
     * @return the formatted contract price of the item
     */
    public String getContractPriceFormatted() {
        return getMoneyFormatted(contractPrice);
    }

    /**
     * Returns the formatted sell-back price of the item.
     * The sell-back price is formatted as a string with a dollar sign ($) and commas for thousands separators.
     *
     * @return the formatted sell-back price of the item
     */
    public String getSellBackPriceFormatted() {
        int sellPrice = getSellBackPrice();
        return getMoneyFormatted(sellPrice);
    }

    /**
     * Formats the given amount of money as a string with a dollar sign ($) and commas for thousands.
     * If the money input is negative, a minus sign (-) is added to the formatted string.
     *
     * @param moneyInput the amount of money to be formatted
     * @return the formatted money string
     */
    public String getMoneyFormatted(int moneyInput) {
        String formatted = "";
        if (moneyInput < 0) {
            formatted += "-";
            moneyInput = -moneyInput;
        }
        formatted += "$" + String.format("%,d", moneyInput);
        return formatted;
    }

    /**
     * Sets the contract price of the purchasable item.
     *
     * @param contractPrice the contract price to be set
     */
    public void setContractPrice(int contractPrice) {
        this.contractPrice = contractPrice;
    }

    /**
     * Returns the sell-back price of the item.
     * For an Athlete, the sell-back price is calculated from the offensive and defensive scores.
     * If the stamina is not full, the sell-back price is lowered 
     */
    public int getSellBackPrice() {
    	if (this instanceof Athlete) {
    		Athlete athlete = (Athlete) this;
    		int basePrice = (athlete.getOffensive() + athlete.getDefensive()) * 10;
    		int staminaPenalty = 100 - athlete.getStamina();
    		int price = basePrice - (staminaPenalty * 10); 
    		return price;
            } else {
            	return sellbackPrice;
            }
    }
    
    /**
     * Sets the sell-back price of the purchasable thing to a specified value
     * @param sellBackPrice the sell-back price to be set
     */
    public void setSellBackPrice(int sellBackPrice) {
        this.sellbackPrice = sellBackPrice;
    }
    
    /**
     * Returns the description of the item
     * @return the description of the item
     */
    public String getDescription() {
        return description;
    }
    
    /**
     * Sets the description of the item to a specified string. 
     * @param description the description to set the purchasable item to
     */
    public void setDescription(String description) {
        this.description = description;
    }
}
    
    
