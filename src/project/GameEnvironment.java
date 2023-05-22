package project;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import projectExceptions.IllegalTeamException;
import projectExceptions.InsufficientFundsException;
import projectExceptions.InventoryFullException;
import projectExceptions.LimitException;
import projectExceptions.NameException;
import projectExceptions.NoReserveAthletesException;
import projectUI.MainGame;
import projectUI.SetupScreen;

/**
 * The game environment class where all the game logic used by UI is located
 *
 */
public class GameEnvironment {
	
	/**
	 * The length of the season
	 */
	int seasonLength;
	
	/**
	 * The money balance for the player
	 */
	int playerMoney;
	
	/**
	 * The points balance for the player
	 */
	int playerPoints;
	
	/**
	 * the setup screen window for the GUI for setting up the game
	 */
    private SetupScreen setupWindow;
    
    /**
     * The main game GUI window
     */
    private MainGame mainGame;
    
    /**
     * Boolean indicating whether it is the final week of the season
     */
    boolean finalWeek;

    /**
     * The string representing the difficulty level of the game
     */
    private String gameDifficulty;
    
    /**
     * The current week in the season
     */
    int currentWeek;
    
    /**
     * The Team object representing the player's team
     */
    Team team;
    
    /**
     * The Market object representing the player's interactions with the market
     */
    Market market;
    
    /**
     * The random events object representing random events that may occur during the game
     */
    RandomEvents randomEvents; 
    
    /**
     * The stadium object from where matches are generated and refreshed weekly
     */
    Stadium stadium;
    
    /**
     * The current match being played
     */
    Match currentMatch;
    
   /**
    * The player's inventory which holds up to 4 items
    */
    private ArrayList<Item> inventory;
    
    /**
     * The Map of the currentRandomEvent
     */
    Map<String, Object> currentRandomEvent;

    
	/**
	 * Constructs a new instance of GameEnvironment.
	 * Initilises random events, inventory, player money, team and player points.
	 */
	public GameEnvironment() {
		randomEvents = new RandomEvents();
		inventory = new ArrayList<>();
		playerMoney = 20000; 
		team = new Team();
		playerPoints = 0;
	}
	
	// The following methods are related to the game window GUI
	
	/**
	 * Launches the setup screen for the game by creating new SetupScreen object
	 */
	public void launchSetupScreen() {
		 setupWindow = new SetupScreen(this);
	}
	
	/**
	 * Closes the setup screen window and launch the main screen window
	 */
	public void closeSetupScreen() {
		setupWindow.closeWindow();
		launchMainScreen();
	}
	
	/**
	 * Launches the main game screen for the game by creating new  MainGame object.
	 */
	public void launchMainScreen() {
		 mainGame = new MainGame(this);
		 
	}
	
	/**
	 * Closes the main scrreen window when game is complete
	 */
	public void closeMainScreen() {
		mainGame.closeWindow();
	}
	
	/**
	 * Launches the setupscreen window with a new game environment if play again is selected
	 */
	public void playAgain() {
	    closeMainScreen(); // Close or dispose the current main screen
	    GameEnvironment game = new GameEnvironment();
	    game.launchSetupScreen(); // Launch the setup screen to start a new game
	}
	
	
	// The following methods are related to setting up the game or retrieving game settings
	
	/**
	 * Sets the team name if the selected name meets requirements, otherwise throws NameException
	 * @param teamName the inputed string name for the team
	 * @throws NameException if the name does not meet the requirements (between 3 and 15 characters inclusive, no special characters)
	 */
	public void setTeamName(String teamName) throws NameException {
		team.setTeamName(teamName);
	}
	
	/**
	 * Returns the name of the player's team
	 * @return team name
	 */
	public String getTeamName() {
		return team.getTeamName();
	}
	
	/**
	 * Sets the length of the season
	 * @param length the length of the season in weeks
	 * @throws IllegalArgumentException if the season length is not between 5-15 weeks inclusive
	 */
	public void setSeasonLength(int length) {

		if (length < 5 || length > 15) {
			throw new IllegalArgumentException("Season length must be between 5 and 15 weeks");
		} 
		seasonLength = length;
	}
	
	/**
	 * Returns the length of the season
	 * @return seasonLength the length of the season
	 */
	public int getSeasonLength() {
		return seasonLength;
	}
	
	/**
	 * Sets the game difficulty to the specified value
	 * @param difficultyInput the game difficulty to set
	 */
	public void setGameDifficulty(String difficultyInput) {
		gameDifficulty = difficultyInput;
	}
	
	/**
	 * Returns the difficulty of the game. 
	 * @return gameDifficulty the difficulty string of the game
	 */
	public String getGameDifficulty( ) {
		return gameDifficulty;
	}
	
	
	// Methods used to initialise the player's initial team during game setup
	
	/**
	 * Returns the array list containing the 4 athletes to select from when setting up the game
	 * @return the ArrayList containing the initial set of athletes to select from
	 */
	public ArrayList<Athlete> getInitialAthletes() {
		return team.getInitialAthletes();
	}
	
	/**
	 * Purchases an athlete to add to the initial team at a specfied position during game setup.
	 * @param athlete the athlete being purchased for the initial team
	 * @param position the position at which the athlete will be added in the team
	 */
	public void purchaseInitialAthlete(Athlete athlete, String position) {
		team.addToInitialTeam(athlete, position);
		modifyPlayerMoney(- athlete.getContractPrice());
	}
	
	// Methods related to game progression and information retrieval
	
	/**
	 * Starts the main game after setup has been initialised. 
	 * Sets up the initial game state.
	 * Creates new instance of market and stadium objects.
	 * Sets the initial week to 0.
	 * Player starting money and stadium object is dependent on the set game difficulty.
	 */
	public void startGame() {
		market = new Market();
		currentWeek = 0;
		
		if (gameDifficulty == "Normal") {
			setPlayerMoney(10000);
		}
		else if (gameDifficulty == "Hard") {
			setPlayerMoney(5000);
		}
		stadium = new Stadium(gameDifficulty);
		increaseWeek();
	}
	/**
	 * Ends the game and returns the game results. 
	 * Checks that the game ended and throws an IllegalStateException if the game has not yet ended. 
	 * Otherwise, returns a map containing the results of the game: name of the team, the weeks played, points score and money earned
	 * @throws IllegalStateException if game has not yet ended
	 * @return a Map with the results of the game
	 */
	public Map<String, Object> endGame() throws IllegalStateException {
	    if (!finalWeek && checkGameContinues()) {
	        throw new IllegalStateException("The game has not ended yet.");
	    }

	    // Prepare the game results
	    Map<String, Object> results = new HashMap<>();
	    results.put("name", team.getTeamName());
	    results.put("weeks", seasonLength);
	    results.put("points", playerPoints);
	    results.put("money", playerMoney);

	    return results;
	}
	
	/**
	 * Checks that the game can continue based on the current game state.
	 * Calculates the minimum contract price for an athlete in the shop.
	 * If the player's balance is less than that and they do not have enough players to fill a team.
	 * The game will not be able to continue
	 * @return true if the game continues, false otherwise.
	 */
	public Boolean checkGameContinues() {
		int minPrice = 100000000;
		for (Athlete athlete : market.getShopAthletes()) {
			if (athlete.getContractPrice() < minPrice) {
				minPrice = athlete.getContractPrice();
			}
		}
		if (playerMoney < minPrice) {
			int teamSize = team.getTeamList().size();
			teamSize += team.getReservesList().size();
			if (teamSize < 4) {
				return false;
			}
		}
		return true;
	}
	
	/**
	 * Increases the current week of the game
	 * Sets the current random event if the game is not moving into its first week.
	 * The stadium is set based on the current week (opposition teams will increase in difficulty as the game progresses)
	 * The market is refreshed and all athletes in the team are restored.
	 * If the season is in it's last week it sets final week to true.
	 */
	public void increaseWeek() {
		if (currentWeek < seasonLength) {
			// Only begin performing random events after the first week 
			if (currentWeek != 0) {
		    	currentRandomEvent = randomEvents.performRandomEvent(team);
			}
			currentWeek += 1;
	    	stadium.setStadium(team, currentWeek);
			market.refreshMarket(currentWeek);
	    	team.restoreAthletes();

	    	if (currentWeek == seasonLength) {
	    		finalWeek = true;
	    	}
		} else {
			endGame();
		}
	}
	
	/**
	 * Returns the current week of the game
	 * @return currentWeek the current week
	 */
	public int getCurrentWeek() {
		return currentWeek;
	}
	
	/**
	 * Returns true if the game is in its final week, false otherwise
	 * @return true if final week is true, false otherwise
	 */
	public boolean isFinalWeek() {
		if (finalWeek == true) {
			return true;
		} else {
			return false;
		}
	}
	
    /**
     * Returns the current random event which was set when the week was initialised
     * @return currentRandomEvent a Map containing the details of the current random event.
     */
	public Map<String, Object> getRandomEvent() {
		return currentRandomEvent;
		
	}

	
	// Methods related to setting and retrieving player points
	
	/**
	 * Sets the playerMoney value to a specific integer;
	 * @param value the value for player money
	 */
	private void setPlayerMoney(int value) {
		playerMoney = value;
	}
	
	/**
	 * Returns the amount money the player has
	 * @return playerMoney the amount of money belonging to the player and their team
	 */
	public int getPlayerMoney() {
		return playerMoney;
	}
	
	/**
	 * Modifies the players' money by a specified amount.
	 * Player money cannot be lower than 0.
	 * @param money the amount of money to modify
	 */
	private void modifyPlayerMoney(int money) {
		playerMoney += money;
		if (playerMoney < 0) {
			playerMoney = 0;
		}
	}
	
	/**
	 * Returns the formatted string representation of the player's money. 
	 * @return formatted the string representation for the money
	 */
	public String getMoneyFormatted() {
	    String formatted = "";
	    if (playerMoney < 0) {
	        formatted += "-";
	        playerMoney = -playerMoney;
	    }
	    formatted += "$" + String.format("%,d", playerMoney);
	    return formatted;
	}
	
	/**
	 * Returns the formatted string representation of the specified money amount
	 * @param moneyInput the money amount to be formatted
	 * @return the formatted string representation of the moneyInput
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
	 * Returns the amount of points that the player has
	 * @return playerPoints the amount of points the player has won
	 */
	public int getPlayerPoints() {
		return playerPoints;
	}

	/**
	 * Modifies the players' points by a specified amount.
	 * @param points the amount of points to modify
	 */
	private void modifyPlayerPoints(int points) {
		playerPoints += points;
		if (playerPoints < 0) {
			playerPoints = 0;
		}
	}
	
	// The following methods relate to athletes
	
	/**
	 * Returns the position of the selected athlete
	 * @param athlete the selected athlete from which to return its position
	 * @return a string representing the athlete's position
	 */
	public String getAthletePosition(Athlete athlete) {
		return athlete.getPosition();
	}
	
	/**
	 * Returns a string  representing the selected athlete's name
	 * @param athlete the selected athlete
	 * @return a string representing the athlete's name
	 */
	public String getAthleteName(Athlete athlete) {
		return athlete.getName();
	}
	
	/**
	 * Sets the athlete's name and returns true if the name was changed and false otherwise.
	 * If the name does not meet requirements, throws NameException
	 * @param athlete the selected athlete to change the name for
	 * @param name the string name selected to change the athlete's name to
	 * @return true if the athlete's name was successfully changed, false otherwise
	 * @throws NameException if the name does not meet requirements
	 */
	public boolean setAthleteName(Athlete athlete, String name) throws NameException {
		return athlete.setName(name);
	}
	
	/**
	 * Returns a string with the name for the athlete's image
	 * @return a string containing the name of the athlete's set image
	 */
	public String getAthleteImageName(Athlete athlete) {
		return athlete.getImageName();
	}
	
	/**
	 * Returns a string representation of the athlete formatted for HTML
	 * @param athlete the selected athlete
	 * @return string representation of the athlete in HTML format
	 */
	public String athleteToStringHTML(Athlete athlete) {
		return athlete.toStringHTML();
	}
	
	/**
	 * Returns a string representation of the athlete formatted for HTML
	 * This specifically shows the athlete's sell-back price included in athlete's info
	 * @param athlete the selected athlete
	 * @return string representation of the athlete in HTML format for selling the athlete
	 */
	public String athleteToStringHTMLSell(Athlete athlete) {
		return athlete.toStringHTMLSell();
	}
	
	/**
	 * Returns true if the selected athlete is in the reserves team
	 * @param athlete the selected athlete
	 * @return true if the athlete is a reserve, false otherwise
	 */
	public boolean athleteIsReserve(Athlete athlete) {
		for (Athlete reserve : team.getReservesList()) {
		    if (athlete == reserve) {
		    	return true;
		    }
		}
		return false;
	}
	
	/**
	 * Returns true if the selected athlete is injured
	 * @param athlete the selected athlete
	 * @return true if the athlete is injured, false otherwise
	 */
	public boolean athleteIsInjured(Athlete athlete) {
		return athlete.isInjured();
	}
	
	/**
	 * Returns the alternate position of the selected athlete.
	 * If the athlete is an attacker, it will return defender and vice versa.
	 * If the athlete is a reserve, it will return reserve
	 * @param athlete the selected athlete
	 * @return a string representing the alternative position for the athlete
	 */
	public String getAthleteAlternatePosition(Athlete athlete) {
		return athlete.getAlternatePosition();
	}
	
	/**
	 * Sets the position of the selected athlete to the string input
	 * @param athlete the selected athlete
	 * @param position the position to update the athlete to
	 */
	public void setAthletePosition(Athlete athlete, String position) {
		athlete.setPosition(position);
	}
		
	/**
	 * Uses the selected item on the selected athlete
	 * @param item the selected item
	 * @param athlete the selected athlete
	 */
	public void useItemOnAthlete(Item item, Athlete athlete) {
		athlete.useItem(item);
    	inventory.remove(item);
	}
	
	/**
	 * Method for training the selected athlete at the end of the week
	 * @param athleteTrained the selected athlete to be trained
	 */
    public void trainAthlete(Athlete athleteTrained) {
    	for (Athlete athlete : team.getTeamList()) {
    		if (athleteTrained.equals(athlete)) {
    			athlete.trainAthlete();
    		}
    	}
    	for (Athlete reserve : team.getReservesList()) {
    		if (athleteTrained.equals(reserve)) {
    			reserve.trainAthlete();
    		}
    	}
    }
	
	
	// Methods relating to the Team class
	
	/**
	 * Returns the list of athletes in the team. This does not include any reserve athletes.
	 * @return the list of athletes in the team
	 */
	public ArrayList<Athlete> getTeamList() {
		return team.getTeamList();
	}
	
	/**
	 * Returns the list of athletes in the reserves list.
	 * @return the list of athlete in the reserves.
	 */
	public ArrayList<Athlete> getReservesList() {
		return team.getReservesList();
	}
	
	/**
	 * Swaps two athletes owned by the player if one is in the reserves team and one is in the player team
	 * If both athletes are in the same team, does nothing.
	 * @param playerAthlete the first athlete to be swapped with secondAthlete
	 * @param reserveAthlete the second athlete to be swapped with firstAthlete
	 */
	public void swapAthletes(Athlete firstAthlete, Athlete secondAthlete) {
		team.swapAthletes(firstAthlete, secondAthlete);
	}
	
	/**
	 * Checks that a player on the player team is able to be swapped. 
	 * Exception is thrown if there are no reserve athletes to swap with.
	 * @return true if there is at least one athlete on the player team and one athlete on the reserves team, false otherwise
	 * @throws NoReserveAthletesException if there are no reserve athletes
	 */
	public Boolean checkSwappable() throws NoReserveAthletesException {
	    return team.checkSwappable();
	}
	
	/**
	 * Adds athlete to the player's reserve list
	 * Throws LimiException if reserves list is full
	 * @param athlete the athlete to be added to the reserve list
	 * @throws LimitException if reserve list is full (max capacity 5)
	 */
    public void addAthleteAsReserve(Athlete athlete) throws LimitException{
    	try {
    		team.addToReserves(athlete);
    	} catch (LimitException e) {
    		throw new LimitException();
    	}
    }
    
    /**
	 * Adds reserve to the team - if position is not specified it automatically assigns athlete to the position there are less of in the team
	 *
	 * @param reserve the reserve athlete that is being added to the team 
	 * @param the position the athlete is being added as - if null then the athlete's position will be whatever there are less of in the team
	 */
    public void addAthleteToTeam(Athlete athlete, String position) {
    	try {
			team.addToTeam(athlete, position);
		} catch (LimitException e) {
		}
    }
   
    /**
     * For use when the team list is full and the reserves list is not full.
     * Selects athlete to swap with the incoming athlete.
     * The incoming athlete will be added to the team and the other athlete will be swapped to the reserves list.
     * @param added the new athlete that is added to the team
     * @param swapped the athlete swapped with the incoming athlete is moved to the reserves team.
     */
    public void addAthleteToTeamFull(Athlete added, Athlete swapped) {
    	team.addAthleteAndSwap(added, swapped);
    }
    
	// Methods relating to playing a Match and the Match and Stadium classes
    
    /**
     * Returns the current available matches to be played from the stadium
     * @return an ArrayList of Matches that are available to play
     */
    public ArrayList<Match> getStadiumMatches() {
    	return stadium.getMatches(team);
    }
    
    /**
     * Returns the opposition team name for the selected match
     * @param match the selected match (from the list of stadium matches)
     * @return name the opposition team's name
     */
    public String getMatchTeamName(Match match) {
    	String name = match.getOppositionTeamName();
    	return name;
    }
    
    /**
     * Returns the amount of points available to be won for the selected match
     * @param match the selected match to get the points reward for
     * @return points the amount of points available for winning the match
     */
    public int getMatchPoints(Match match) {
    	int points = match.getPointsValue();
    	return points;
    }
    
    /**
     * Returns the prize money for the selected match
     * @param match the selected match from which to get the prize money
     * @return prizeMoney the prize money on offer for winning the match
     */
    public String getMatchPrize(Match match) {
    	String prizeMoney = match.getMoneyFormatted();
    	return prizeMoney;
    }
    
    /**
     * Returns the string with the score of the current match
     * @return result the string with the name of each of the two teams in the match and their scores
     */
    public String getMatchScoreString() {
    	String result = team.getTeamName() + " score: " + currentMatch.getPlayerScore();
    	result += "\n" + currentMatch.getOppositionTeamName() + " score: " + currentMatch.getOppositionScore();
    	return result;
    }
    
    /**
     * Returns the match  score of the current game as a list of two integers, the first being the player's team score
     * @return score an ArrayList of integers with each of the team's scores
     */
    public ArrayList<Integer> getMatchScore() {
    	ArrayList<Integer> score = new ArrayList<Integer>();
    	int playerScore = currentMatch.getPlayerScore();
    	int oppositionScore = currentMatch.getOppositionScore();
    	score.add(playerScore);
    	score.add(oppositionScore);
    	return score;
    }
    
    /**
     * Starts the match by setting the current match to the selected match input.
     * Checks team has two attackers and two defenders, otherwise throws IllegalTeamException and match cannot be started
     * @param match the selected match form the list of stadium matches
     * @throws IllegalTeamException if the player's team is not ready to start the match
     */
    public void startMatch(Match match) throws IllegalTeamException {
    	if (team.getTeamList().size() != 4) {
    		throw new IllegalTeamException("Team must have 4 players");
    	} else if (team.checkTeamReady() != true) {
    		throw new IllegalTeamException("Team must have 2 attackers and 2 defenders");
    	} else {
    		currentMatch = match;
        	stadium.playMatch(match);
        	
    	}
    }
    
    /**
     * Returns the opposition team name for the current match
     * @return
     */
    public String getCurrentMatchTeamName() {
    	String name = currentMatch.getOppositionTeamName();
    	return name;
    }
    
   /**
    * Checks whether a match is running.
    * If the current match is null, the game has not started. 
    * Otherwise checks within team if not all players have not yet been played.
    * @return true if the current match is running, false otherwise
    */
    public boolean isMatchRunning() {
    	if (currentMatch == null) {
    		return false;
    	} else {
        	return currentMatch.isMatchRunning();

    	}
    }
    
    /**
     * Returns a list of the next encounter athletes in the current match
     * @return encounterAthletes an ArrayList of two athletes, one from the player team and one from the oppostion team in the current match
     */
    public ArrayList<Athlete> getEncounterAthletes() {
    	ArrayList<Athlete> encounterAthletes = currentMatch.getEncounterAthletes();
    	return encounterAthletes;
    }
    
    /**
     * Returns the string for the result of the encounter.
     * This is a action-based description of the encounter.
     * @param encounterAthletes the two athletes in the encounter
     * @return a String with the result of the encounter
     */
    public String matchEncounter(ArrayList<Athlete> encounterAthletes) {
    	return currentMatch.encounter(encounterAthletes.get(0), encounterAthletes.get(1));
    }
    
    /**
     * Ends the match and returns a Map with the results of the match
     * @return result a Map of objects showing the result of the game
     * @throws IllegalStateException if the match has not yet ended and all players have not completed their encounters
     */
    public Map<String, Object> endMatch() throws IllegalStateException {
    	Map<String, Object> result = currentMatch.endGame();
    	
        int prizeMoney = (int) result.get("money");
        int pointsWon = (int) result.get("points");
        
        modifyPlayerMoney(prizeMoney);
        modifyPlayerPoints(pointsWon);

    	//currentMatch = null;
    	return result;
    }
	
	
	// Methods relating to the Market and Item Classes
	
	public ArrayList<Athlete> getShopAthletes() {
        return market.getShopAthletes();
    }
	public ArrayList<Item> getShopItems(){
		return market.getShopItems();
	}
	
	/**
	 * buys item, modifies player money by cost then adds item to inventory the calls methods to remove item from shop
	 * @param item item to be added to inventory
	 * @throws InsufficientFundsException when player can't afford item
	 * @throws InventoryFullException when inventory is full
	 */
    public void purchaseItem(Item item) throws InsufficientFundsException, InventoryFullException {
        if (playerMoney <= item.getContractPrice()) {
            throw new InsufficientFundsException();
        } else if (inventory.size() >= 4) {
            throw new InventoryFullException();
        } else {
            modifyPlayerMoney(-item.getContractPrice());
            inventory.add(item);
            market.purchaseItem(item);
        }
    }
    /**
     * method to buy athlete, modify player money add to team or reserve list depending on circumstance then calls methods to remove athlete from shop
     * @param athlete to be added to team and removed from shop
     * @throws InsufficientFundsException when player can't afford athlete 
     * @throws LimitException when both team lists are full
     */
    public void purchaseAthlete(Athlete athlete) throws InsufficientFundsException, LimitException {
    	
    	if (playerMoney < athlete.getContractPrice()) {
    		throw new InsufficientFundsException();
    	} else if (team.isTeamFull()) {
    		throw new LimitException("Team is full!");
    	} else {
        	modifyPlayerMoney(-athlete.getContractPrice());
        	market.purchaseAthlete(athlete);
    	}
    }
	
   /**
    * Returns the inventory belonging to the player
    * @return
    */
    public ArrayList<Item> getInventory() {
        return inventory;
    }
    
    /**
     * Returns the name for the selected item
     * @param item the selected item from which to get its name
     * @return item name the name of the selected item
     */
	public String getItemName(Item item) {
		return item.getName();
	}
    
	/**
	 * Returns a string with the result of the item in HTML form for the GUI
	 * @param item the selected item from which to get the string
	 * @return the string with the item's information
	 */
	public String itemToStringHTML(Item item) {
		return item.toStringHTML();
	}
	
	/**
	 * sells items, gets item sell back price and updates current players money then removes item from inventory
	 * @param item the item to modify player money and removed from inventory
	 */
    public void sellItem(Item item) {
    	modifyPlayerMoney(+item.getSellBackPrice());
    	inventory.remove(item);
    }
    
	/**
	 * sells player, updates player money by the sell price then removes player from team
	 * @param athlete the athlete that is to be sold and removed
	 */
    public void sellPlayer(Athlete athlete) {
    	modifyPlayerMoney(+athlete.getSellBackPrice());
    	team.removeAthlete(athlete);
    }
   
    
	
 /**
	* Returns a string representation in HTML of the GameEnvironment object, including the difficulty, season length, team name, and starting money value.
    * @return a String containing the difficulty, season length, team name, and player money value.
    */
    public String toStringHTML() {
    	//result += ("\nPosition: " + this.getPosition());
    	String result = "<html>" + ("Team name: " + this.getTeamName());
        result += ("<br>Season length: " + this.getSeasonLength() + " weeks");
        result += ("<br>Difficulty: " + this.getGameDifficulty() + "</html>");
        
        return result;
        
    }
    
    /**
	* Returns a string representation of the GameEnvironment object, including the difficulty, season length, team name, and starting money value.
    * @return a String containing the difficulty, season length, team name, and player money value.
    */
    public String toString() {
    	//result += ("\nPosition: " + this.getPosition());
    	String result = ("Team name: " + this.getTeamName());
        result += ("\nSeason length: " + this.getSeasonLength() + " weeks");
        result += ("\nDifficulty: " + this.getGameDifficulty());
        
        return result;
        
    }
    
    /**
     * The main method which starts the entire game by creating a new game environment and launching the game setup.
     * @param args
     */
	public static void main(String[] args) {
		GameEnvironment game = new GameEnvironment();
		game.launchSetupScreen();
	}


}


    

	
	
	




   
    
    
    
    

    



    

	
	
	



