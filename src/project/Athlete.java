package project;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

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
    * Class constructor.
    */
	public Athlete() {
		generateStatistics();
		setName(generateName());
        setContractPrice(0); // initialize contract price to 0
        setSellBackPrice(0); // initialize sell-back price to 0
        setDescription(""); // initialize description to empty string
	}
	
	/**
	* Class constructor specifying parameters.
	* 
	* @param offensiveStatistic an integer representing the offensive statistic of the athlete
	* @param defensiveStatistic an integer representing the defensive statistic of the athlete
	* @param staminaStatistic an integer representing the stamina statistic of the athlete
	* @param athleteName a String representing the name of the athlete
	* @param athletePosition a String representing the position of the athlete
	* @param contractPrice an integer representing the contract price of the athlete
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
        
        athleteImage = new String();
        
    }

    /**
     * Generates random offensive, defensive, and stamina statistics for an athlete.
     * The offensive statistic is generated as a random integer between 30 and 70.
     * The defensive statistic is calculated as 100 minus the offensive statistic to ensure randomly generated athletes are not overpowered.
     * The stamina statistic is generated as a random integer between 60 and 100.
     */
    
	private void generateStatistics() {
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
	 *
	 * @return a String representing a random name generated from pre-defined arrays.
	 */
	public String generateName() {
		String[] firstNames = {"Alastair", "Ambrosius", "Archibald", "Augustus", "Belladonna", "Balthazar", "Caspian", "Cedric", "Celestina", "Cornelius", "Cyprian", "Desdemona", "Darian", "Draco", "Drusilla", "Endora", "Fabian", "Gideon", "Ginevra", "Hadrian", "Hecate", "Hyacinth", "Ignatius", "Isadora", "Leopold", "Lucian", "Magnus", "Merlin", "Morgana", "Niamh", "Oberon", "Percival", "Phineas", "Remus", "Rowena", "Sabrina", "Salazar", "Seraphina", "Silas", "Sybill", "Thaddeus", "Uriel", "Ursula", "Xanthe"};
		String[] lastNames = {"Aberforth", "Blackwood", "Bones", "Chaucer", "Crewe", "Davenport", "Eldritch", "Fairweather", "Fawley", "Flamel", "Gammidge", "Goshawk", "Greengrass", "Holloway", "Iscariot", "Jorkins", "Kettleburn", "Lestrange", "Macnair", "Malkin", "Montague", "Nott", "Ogden", "Peverell", "Prewett", "Quesne", "Rosier", "Sacharissa", "Skeeter", "Slytherin", "Sparks", "Travers", "Umbridge", "Vablatsky", "Van Helsing", "Weasley", "Wilberforce", "Xenophilius", "Yaxley", "Zabini"};
		
		Random random = new Random();
		String randomName = firstNames[random.nextInt(firstNames.length)] + " " + lastNames[random.nextInt(lastNames.length)];
		
		return randomName;
		
	}
	

	/**
	 * Sets athleteImage to a random string for the athlete image name based on whether the athlete name is traditionally female or male
	 *
	 */
	public void setRandomImage() {
		List<String> femaleNames = Arrays.asList("Belladonna", "Celestina", "Desdemona", "Drusilla", "Endora", "Ginevra", "Hecate", "Isadora", "Morgana", "Niamh", "Rowena", "Sabrina", "Seraphina", "Sybill", "Ursula", "Xanthe");
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
			} else if (nameInput.length() < 3 || nameInput.length() > 20) {
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
    	// allows player to set the nickname for the athlete
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
    
	public void changeAthletePosition() {
		this.setPosition(getAlternatePosition());
	}
	
	public String getAlternatePosition() {
		String position = null;
		if (this.getPosition() == "Attacker") {
			position = "Defender";
		} else if (this.getPosition() == "Defender") {
			position = "Attacker";
		}
		return position;
	}
	
	
    
    /**
	* Returns a string representation of the Athlete object, including the athlete's name, contract price, and offensive, defensive, and stamina statistics.
    * @return a String containing the athlete's name, contract price, and statistics.
    */
    public String toString() {
    	String result = ("Athlete name: " + athleteName);
    	if (this.getPosition() != null) {
    		result += ("\nPosition: " + this.getPosition());
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
    * @return a String containing the athlete's name, contract price, and statistics.
    */
    public String toStringHTML() {
    	String result;
    	if (this.getPosition() != null) {
    		if (this.getPosition() == "Reserve") {
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
    public void useItem(Item item) {
        Add the defense and offense values from the item to the athlete
        this.defensiveStatistic += item.getDefense();
        this.offensiveStatistic += item.getOffense();

        
        Inventory.removeItem(item);
    
    }}
    
	
	
	


