package project;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

import projectExceptions.NameException;

/**
 * This class extends the Purchasable class and provides attributes and methods to manage the athlete's statistics, positions, name, image.
 * The athlete class includes athlete's name, position offensive, defensive and stamina statistics. 
 * It provides methods to generate random statistics and name. 
 *
 * @author Jordan Redfern 
 * @version 1.1, May 2023.
 */
public class Athlete extends Purchasable {
	
	/**
	 * The offensive statistic value for the athlete
	 */
    private int offensiveStatistic;
    
	/**
	 * The defensive statistic value for the athlete
	 */
    private int defensiveStatistic;
    
	/**
	 * The stamina statistic value for the athlete
	 */
    private int staminaStatistic;
    
	/**
	 * The name of the athlete
	 */
    private String athleteName;
    
	/**
	 * The position of the athlete
	 */
    private String athletePosition;

	/**
	 * The string for the name of the athlete's associated image
	 */
    private String athleteImage;
	
	/**
	 * A boolean set to true if the player is injured and false otherwise
	 */
    private Boolean isInjured;
    
    /** 
    * Class constructor.
    */
	public Athlete() {
		setGeneratedStatistics();
		setName(generateName());
		// contract price and sell-back prices are initialised to 0, both generated in super class
        setContractPrice(0); 
        setSellBackPrice(0); 
        setDescription(""); 
        isInjured = false;
	}
	
	/**
	* Class constructor specifying parameters.
	* 
	* @param offensiveStatistic an integer representing the offensive statistic of the athlete
	* @param defensiveStatistic an integer representing the defensive statistic of the athlete
	* @param staminaStatistic an integer representing the stamina statistic of the athlete
	* @param athleteName a String representing the name of the athlete
	* @param athletePosition a String representing the position of the athlete
	* @param contractPrice an integer representing the contract price of the athlete when purchasing in the market
	* @param sellbackPrice an integer representing the sell-back price of the athlete
	* @param description a String representing the description of the athlete
	*  
	*/
    public Athlete(int offensiveStatistic, int defensiveStatistic, int staminaStatistic, String athleteName, String athletePosition, int contractPrice, int sellbackPrice, String description) {
        super(contractPrice, sellbackPrice, description);
        this.offensiveStatistic = offensiveStatistic;
        this.defensiveStatistic = defensiveStatistic;
        this.staminaStatistic = staminaStatistic;
        this.athleteName = athleteName;
        this.athletePosition = athletePosition;

        isInjured = false;
        
        athleteImage = new String();
        
    }

    /**
     * Generates random offensive, defensive, and stamina statistics for an athlete.
     * The offensive statistic is generated as a random integer between 30 and 70.
     * The defensive statistic is calculated as 100 minus the offensive statistic to ensure randomly generated athletes are not overpowered.
     * The stamina statistic is generated as a random integer between 60 and 100.
     */
	private void setGeneratedStatistics() {
		Random offensive = new Random();
		int min = 30;
		int max = 70;
		
		offensiveStatistic = offensive.nextInt(max - min + 1) + min;
		defensiveStatistic = 100 - offensiveStatistic;
		
		Random stamina = new Random();
		staminaStatistic = stamina.nextInt(100 - 60 + 1) + 60;
	}
	
	/**
	 * Generates a random name by selecting a first name and a last name from pre-defined arrays.
	 * generateName generates 4712 possible combinations of names.
	 *
	 * @return a String representing a random name generated from pre-defined arrays.
	 */
	public String generateName() {
		String[] firstNames = {"Alastair", "Ambrosius", "Archibald", "Augustus", "Belladonna", "Balthazar", 
				"Caspian", "Cedric", "Celestina", "Cornelius", "Cyprian", "Desdemona", "Darian", "Draco", "Drusilla", "Endora",
				"Fabian", "Gideon", "Ginevra", "Hadrian", "Hecate", "Hyacinth", "Ignatius", "Isadora", "Leopold", "Lucian", "Magnus",
				"Merlin", "Morgana", "Niamh", "Oberon", "Percival", "Phineas", "Remus", "Rowena", "Sabrina", "Salazar", "Seraphina", "Silas",
				"Sybill", "Thaddeus", "Uriel", "Ursula", "Xanthe", "Xenia", "Atticus", "Penelope", "Bianca", "Nicolette", "Cressida", "Arabella",
				"Maximilian", "Juliette", "Octavia", "Helena", "Jasper", "Alaric"};
		String[] lastNames = {"Aberforth", "Blackwood", "Bones", "Chaucer", "Crewe", "Davenport", "Eldritch", "Fairweather", "Fawley", "Flamel",
				"Gammidge", "Goshawk", "Greengrass", "Holloway", "Iscariot", "Jorkins", "Kettleburn", "Lestrange", "Macnair", "Malkin", "Montague", "Kettleburn",
				"Nott", "Ogden", "Peverell", "Prewett", "Quesne", "Rosier", "Sacharissa", "Skeeter", "Slytherin", "Sparks", "Travers", "Umbridge", "Vablatsky",
				"Van Helsing", "Weasley", "Wilberforce", "Xenophilius", "Yaxley", "Zabini", "Blackwood", "Rookwood", "Davenport", "Greengrass", "Fletchley",
				"Vablatsky", "Kensington", "Nightshade", "Thornfield", "Fairchild", "Nightingale", "Hawthorn", "Everly", "Devereaux", "Montgomery", "Vanderbilt",
				"Grimaldi", "Everhart", "Blackburn", "Beaumont", "Middleton", "Westbourne", "Wakefield", "Cattermole", "Nettlebed", "Macnair", "Ellwood", "Jorkins", "Ingleby"};
		
		Random random = new Random();
		// generates a name from randomly selected first and last name. nextInt is bound by the length of the list as it is exclusive. 
		String randomName = firstNames[random.nextInt(firstNames.length)] + " " + lastNames[random.nextInt(lastNames.length)];
		
		return randomName;
		
	}
	

	/**
	 * Sets athleteImage to a random string for the athlete image name based on whether the athlete name is traditionally female or male
	 *
	 */
	public void setRandomImage() {
		List<String> femaleNames = Arrays.asList("Belladonna", "Celestina", "Desdemona", "Drusilla", "Endora", "Ginevra", "Hecate",
				"Isadora", "Morgana", "Niamh", "Rowena", "Sabrina", "Seraphina", "Sybill", "Ursula", "Xanthe", "Xenia", "Penelope", "Bianca", "Nicolette", "Cressida", "Arabella",
				"Juliette", "Octavia", "Helena");
		Random randomNumber = new Random();
		int imageNumber = randomNumber.nextInt(24 - 1 + 1) + 1;
		String imageName;
		String firstName = athleteName.split(" ")[0];
		
		if (femaleNames.contains(firstName)) {
			imageName = "female" + (imageNumber);
		} else {
			imageName = "male" + imageNumber;
		}
		
		athleteImage = imageName;
	}
	
	/**
	 * Returns the name of the image for the athlete. If an image string has not been generated then it sets the athlete image first.
	 *
	 * @return a String representing the image name for the athlete.
	 */
	public String getImageName() {
		if (athleteImage == null) {
			this.setRandomImage();
		} 
		return athleteImage;
	}
	
	
	/**
	 * Returns the offensive statistic of an athlete.
	 *
	 * @return an integer representing the offensive statistic of the athlete.
	 */
    public int getOffensive() {

        return offensiveStatistic;
    }
    
	/**
	 * Returns the defensive statistic of an athlete.
	 *
	 * @return an integer representing the defensive statistic of the athlete.
	 */
    public int getDefensive() {

        return defensiveStatistic;
    }
    
	/**
	 * Returns the stamina statistic of an athlete.
	 *
	 * @return an integer representing the stamina value of the athlete.
	 */
    public int getStamina() {
    	return staminaStatistic;
    }
    
    /**
     * Sets the offensive statistic of an athlete. This method is protected and can only be accessed by subclasses.
     *
     * @param offensiveStatistic an integer representing the new offensive statistic of the athlete.
     */
    protected void setOffensive(int offensiveStatistic) {
        this.offensiveStatistic = offensiveStatistic;
    }

    /**
     * Sets the defensive statistic of an athlete. This method is protected and can only be accessed by subclasses.
     *
     * @param defensiveStatistic an integer representing the new defensive statistic of the athlete.
     */
    protected void setDefensive(int defensiveStatistic) {
        this.defensiveStatistic = defensiveStatistic;
    }

    /**
     * Sets the stamina statistic of an athlete. This method is protected and can only be accessed by subclasses.
     *
     * @param staminaStatistic an integer representing the new stamina statistic of the athlete.
     */
    protected void setStamina(int staminaStatistic) {
        this.staminaStatistic = staminaStatistic;
    }

    
    /**
     * Sets the name for the athlete, if the athlete name is over 20 characters and includes any special characters it will throw NameException
     * Does not throw name exceptions for previously generated names, only inputted nicknames. Returns true if the name was updated and false
     * if there was no change between nameInput and the athlete's name.
     *
     *@return boolean representing whether the name was changed
     * @param nameInput a string representing the new name of the athlete.
     */
	public boolean setName(String nameInput) throws NameException {
		if (athleteName != null) {
			if (nameInput.matches(athleteName)) {
				return false;
			} else if (nameInput.length() < 1 || nameInput.length() > 20) {
				throw new NameException("Player nickname must be between 1 - 20 characters long");
			}
			else if (nameInput.matches(".*[!@#$%^&*()_+\\-=\\[\\]{};':\"\\\\|,.<>\\/?~]+.*")) {
				throw new NameException("Nickname must not include any special characters");
			}
		}
		athleteName = nameInput;
		return true;
	}
    
    /**
     * Sets the position of the athlete. 
     *
     * @param positionInput a String representing the new position of the athlete.
     */
    public void setPosition(String positionInput) {
    	athletePosition = positionInput;
    }
    
	/**
	 * Returns the position of the athlete.
	 *
	 * @return a String representing the position of the athlete.
	 */
    public String getPosition() {
    	return athletePosition;
    }
    
    
	/**
	 * Returns the name of the athlete.
	 *
	 * @return a String representing the athlete's name.
	 */
    public String getName() {
    	return athleteName;
    }
    
    /**
     * Sets the athlete's position to the alternate position from getAlternatePosition.
     * If an athlete is a defender, their position will be set to attacker and vice versa. If an athlete is a reserve, their position will remain.
     */
	public void changeAthletePosition() {
		this.setPosition(getAlternatePosition());
	}
	
	/**
	 * Returns a string with the alternative position of the athlete
	 * If an athlete is an attacker, returns "Defender" and vice versa. If an athlete a reserve, their position will remain.
	 * @return the alternate position of the athlete
	 */
	public String getAlternatePosition() {
		String position = null;
		if (this.getPosition() == "Attacker") {
			position = "Defender";
		} else if (this.getPosition() == "Defender") {
			position = "Attacker";
		} else {
			position = athletePosition;
		}
		return position;
	}
	
	
   
    
    /**
     * Uses the specified item to modify the defensive and offensive statistics of this Athlete.
     *
     * @param item the Item used
     */
    public void useItem(Item item) {
        
        this.defensiveStatistic += item.getDefence();
        this.offensiveStatistic += item.getOffence();
    }
   
    /**
     * Compares this Athlete object with the specified Athlete object for equality.
     * Returns true if the specified Athlete object has the same name, defensive statistic,
     * offensive statistic, and stamina statistic as this Athlete object; otherwise, returns false.
     *
     * @param comparedAthlete the Athlete object to be compared for equality
     * @return true if the specified Athlete object is equal to this Athlete object, false otherwise
     */
    public Boolean equals(Athlete comparedAthlete) {
    	if (comparedAthlete.getName() == athleteName && comparedAthlete.getDefensive() == defensiveStatistic && comparedAthlete.getOffensive() == offensiveStatistic && comparedAthlete.getStamina() == staminaStatistic) {
    		return true;
    	} else {
    		return false;
    	}
    }
    
    /**
     * Increases the offensive, defensive, and stamina statistics of this Athlete by the specified multiplier. The stamina statistic is capped at 100.
     *
     * @param increaseMultiplier the multiplier by which to increase the statistics
     */
    public void increaseStatistics(double increaseMultiplier) {
        offensiveStatistic = (int) (offensiveStatistic * increaseMultiplier);
        defensiveStatistic = (int) (defensiveStatistic * increaseMultiplier);
        staminaStatistic = (int) Math.min(staminaStatistic * increaseMultiplier, 100);
    }
    
    /**
     * Sets the athlete's statistics based on the defensive score generated from a selected team. 
     * The defensive score is the sum of the defensive statistic of defenders in the selected team. 
     * Defensive score is divided by 2 and then adjusted with a random value between -5 to 5 and set to be the defensive statistic of the Athlete
     * This should generate a defensive statistic that is similar to the team that the defensiveScore was from.
     * 
     * @param defensiveScore sum of the defensive statistic of defenders in the selected team
     */
    public void setAthleteStatisticsBasedOnDefensive(int defensiveScore) {

        Random rand = new Random();
        int randomDefensive = rand.nextInt(11) - 5;
        defensiveStatistic = ((defensiveScore/2) - randomDefensive);
    }
    
    /**
     * Sets the athlete's statistics based on the offensive score generated from a selected team. 
     * The offensive score is the sum of the defensive statistic of defenders in the selected team. 
     * Defensive score is divided by 2 and then adjusted with a random value between -5 to 5 and set to be the offensive statistic of the Athlete
     * This should generate an offensive statistic that is similar to the team that the offensiveScore was from.
     * 
     * @param offensiveScore returns the offensive score of the athlete
     */
    public void setAthleteStatisticsBasedOnOffensive(int offensiveScore) {

        Random rand = new Random();
        int randomOffensive = rand.nextInt(11) - 5;
        offensiveStatistic = (offensiveScore/2) - randomOffensive;
    }
    
    
    /**
     * Decreases the stamina statistic of the athlete by the specified amount. 
     * If the resulting stamina statistic is less than 0, it is set to 0 and and the athlete position is set to injured
     * The previous position of the athlete is set for the athlete's position to return to when their stamina is restored.
     * @param amountDecreased the specified amount to decrease stamina by.
     */
    public void decreaseStamina(int amountDecreased) {
    	staminaStatistic -= amountDecreased;
    	if (staminaStatistic < 0) {
    		staminaStatistic = 0;
    	}
    	
    	if (staminaStatistic == 0) {
    		isInjured = true;;
    	}
    }
    
    /**
     * Increases the offensive statistic of the athlete by the specified amount.
     * @param increaseAmount amount to increase the offensiveStatistic by
     */
    public void increaseOffensive(int increaseAmount) {
    	offensiveStatistic += increaseAmount;

    }
    
    /**
     * Increases the defensive statistic of the athlete by the specified amount.
     * @param increaseAmount amount to increase the defensiveStatistic by
     */
    public void increaseDefensive(int increaseAmount) {
    	defensiveStatistic += increaseAmount;
    }
    
    /**
     * Restores the stamina of the athlete.
     * If the athlete was injured, once their stamina reaches 100 their position will be restored to the previous position they were in before they became injured.
     */
    public void restoreStamina() {
    	if (isInjured) {
    		isInjured = false;
    	}
    	staminaStatistic = 100;
    }
    
    /**
     * Trains athlete by increasing their offensive and defensive statistics with random values between 5 and 15 (inclusive).
     */
    public void trainAthlete() {
        Random random = new Random();
        int offensiveIncrease = random.nextInt(11) + 5;
        int defensiveIncrease = random.nextInt(11) + 5; 

        increaseOffensive(offensiveIncrease);
        increaseDefensive(defensiveIncrease);
    }
    
    /**
     * Checks if the athlete is currently in the reserve position
     * @return true if the athlete is a reserve, false otherwise
     */
    public boolean isReserve() {
    	return (athletePosition.equals("Reserve"));
    }
    
    /**
     * Checks if the athlete is currently injured
     * @return true if the athlete is injured, false otherwise
     */
    public boolean isInjured() {
    	return (isInjured);
    }
    
    /**
   	* Returns a string representation of the Athlete object, including the athlete's name, contract price, and offensive, defensive, and stamina statistics.
    * @return a String containing the athlete's name, contract price or position and statistics.
    */
       public String toString() {
       	String result = ("Athlete name: " + athleteName);
       	if (athletePosition != null && !isInjured) {
       		result += ("\nPosition: " + athletePosition);
       	} else if (athletePosition != null && isInjured) {
       		result += ("\n" + "Injured Athlete");
       	} else {
       		result += ("\nPrice: " + this.getContractPriceFormatted());
       	}
           result += ("\nOffensive Statistic: " + this.getOffensive());
           result += ("\nDefensive Statistic: " + this.getDefensive());
           result += ("\nStamina Statistic: " + this.getStamina());
           
           return result;
       }
       
       
    /**
   	* Returns a string representation in HTML of the Athlete object, including the athlete's name, contract price, and offensive, defensive, and stamina statistics.
   	* If the athlete has a position (defender, attacker, injured or reserve) then the String representation includes the position or "Injured player"
   	* Otherwise, athlete's contract price will be included. 
    * @return a String containing the athlete's name, contract price or position, and statistics.
    */
       public String toStringHTML() {
       	String result;
       	if (this.getPosition() != null) {
       		if (isInjured) {
       			result = "<html>" + ("Injured player");
       		} else if (isReserve()){
       			result = "<html>" + ("Reserve player");
       		} else {
           		result = "<html>" + ("Position: " + this.getPosition());
       		}
       	} else {
       		result = "<html>" + ("Price: " + this.getContractPriceFormatted());
       	}
           result += ("<br>Offensive Statistic: " + this.getOffensive());
           result += ("<br>Defensive Statistic: " + this.getDefensive());
           result += ("<br>Stamina Statistic: " + this.getStamina()) + "</html>";
           
           return result;
       }
       
       
    /**
   	* Returns a string representation in HTML of the Athlete object, including the athlete's name, sell-back price, and offensive, defensive, and stamina statistics.
   	* If the athlete has a position (defender, attacker, injured or reserve) then the String representation includes the position or "Injured player"
   	* Sell-back price included for use in the market sell panel.
    * @return a String containing the athlete's name, contract price or position, and statistics.
    */
       public String toStringHTMLSell() {
       	String result = "";
       	if (this.getPosition() != null) {
       		if (isInjured) {
       			result = "<html>" + ("Injured player");
       		} else if (isReserve()){
       			result = "<html>" + ("Reserve player");
       		} else {
           		result = "<html>" + ("Position: " + this.getPosition());
       		}
       	} 
       	
       	result += "<br>Sellback Price: " + this.getSellBackPriceFormatted();
           result += ("<br>Offensive Statistic: " + this.getOffensive());
           result += ("<br>Defensive Statistic: " + this.getDefensive());
           result += ("<br>Stamina Statistic: " + this.getStamina()) + "</html>";
           
           return result;
           
       }
       
     
       
  
	
}

	
	
	