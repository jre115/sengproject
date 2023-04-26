package project;

import java.util.Random;

public class Athlete extends Purchasable {
	
    private int offensiveStatistic;
    private int defensiveStatistic;
    private int staminaStatistic;
    private String athleteName;
    private String athletePosition;
    private int athletePrice;
    private int contractPrice;
    private int sellbackPrice;
    private String description;
	
	public Athlete() {
		generateStatistics();
		setName(generateName());
        setContractPrice(0); // initialize contract price to 0
        setSellBackPrice(0); // initialize sell-back price to 0
        setDescription(""); // initialize description to empty string
		
	}
	
    public Athlete(int contractPrice, int sellbackPrice, String description) {
        super(contractPrice, sellbackPrice, description);
        generateStatistics();
        setName(generateName());
    }

	
	public int getContractPrice() {
        return contractPrice;
    }
    
    public void setContractPrice(int price) {
        contractPrice = price;
    }
    
    public int getSellBackPrice() {
        return sellbackPrice;
    }
    
    public void setSellBackPrice(int price) {
        sellbackPrice = price;
    }
    
    public String getDescription() {
        return description;
    }
    
    public void setDescription(String desc) {
        description = desc;
    }
	
	private void generateStatistics() {
		Random offensive = new Random();
		int min = 30;
		int max = 70;
		
		offensiveStatistic = offensive.nextInt(max - min + 1) + min;
		defensiveStatistic = 100 - offensiveStatistic;
		
		Random stamina = new Random();
		staminaStatistic = stamina.nextInt(100 - 60 + 1) + 60;
		
	}
	
	public String generateName() {
		String[] firstNames = {"james", "Mary", "Robert", "John", "Linda", "David"};
		String[] lastNames = {"Smith", "Johnson", "Brown", "Jones" , "Garcia","Miller"};
		
		Random random = new Random();
		String randomName = firstNames[random.nextInt(firstNames.length)] + " " + lastNames[random.nextInt(lastNames.length)];
		
		return randomName;
		
	}
	
	
	
    public int getOffensive() {

        return offensiveStatistic;
    }
    
    public int getDefensive() {

        return defensiveStatistic;
    }
    
    public int getStamina() {
    	return staminaStatistic;
    }
    
    public void setName(String nameInput) {
    	athleteName = nameInput;
    }
    
    public void setPosition(String positionInput) {
    	// allows player to set the nickname for the athlete
    	athletePosition = positionInput;
    }
    
    public int getPrice() {
    	/*
    	 JR NOTE: potentially in first round dull the athlete's abilities to around min 40 max 60 and make all same price.. not sure about stamina
    	then later in shop can increase the statistics for athletes and increase price! 
    	*/
    	return athletePrice;
    }
    
    public String getPosition() {
    	return athletePosition;
    }
    
    public String toString() {
    	String result = ("Athlete name: " + athleteName);
    	//result += ("\nPosition: " + this.getPosition());
        result += ("\nOffensive Statistic: " + this.getOffensive());
        result += ("\nDefensive Statistic: " + this.getDefensive());
        result += ("\nStamina Statistic: " + this.getStamina());
        
        return result;

        
    }
    
    
	
    public static void main(String[] args) {
        Athlete athlete = new Athlete();
        
        // test the get methods
        System.out.println("Offensive Statistic: " + athlete.getOffensive());
        System.out.println("Defensive Statistic: " + athlete.getDefensive());
        System.out.println("Stamina Statistic: " + athlete.getStamina());
    }
	
	
	
	

}
