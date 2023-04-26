package project;

public class Purchasable {
	
	private int contractPrice;
	private int sellbackPrice;
	private String description;
	
	public Purchasable() {
	    // default constructor with no arguments
	}
	
    public Purchasable(int contractPrice, int sellbackPrice, String description) {
        this.contractPrice = contractPrice;
        this.sellbackPrice = sellbackPrice;
        this.description = description;
    }
    
    public int getContractPrice() {
        return contractPrice;
    }
    
	public String getContractPriceFormatted() {
	    String formatted = "";
	    if (contractPrice < 0) {
	        formatted += "-";
	        contractPrice = -contractPrice;
	    }
	    formatted += "$" + String.format("%,d", contractPrice);
	    return formatted;
	}

    
    public void setContractPrice(int contractPrice) {
        this.contractPrice = contractPrice;
    }
    
    public int getSellBackPrice() {
        return sellbackPrice;
    }
    
    public void setSellBackPrice(int sellBackPrice) {
        this.sellbackPrice = sellBackPrice;
    }
    
    public String getDescription() {
        return description;
    }
    
    public void setDescription(String description) {
        this.description = description;
    }
}
    
    





