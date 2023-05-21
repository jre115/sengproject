package project;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import projectExceptions.IllegalTeamException;
import projectExceptions.InsufficientFundsException;
import projectExceptions.InventoryFullException;
import projectExceptions.NameException;
import projectExceptions.NoReserveAthletesException;
import projectUI.MainGame;
import projectUI.SetupScreen;


public class GameEnvironment {
	
	int seasonLength;
	int playerMoney;
	int playerPoints;
    private SetupScreen setupWindow;
    private MainGame mainGame;
    boolean gameEnded;
    boolean finalWeek;

    private String gameDifficulty;
    int currentWeek;
    Team team;
    Market market;
    RandomEvents randomEvents; 
    Stadium stadium;
    Match currentMatch;
    
    ArrayList<Athlete> reservesList;
    ArrayList<Athlete> teamList;
    private ArrayList<Item> inventory;
    
    Map<String, Object> currentRandomEvent;

    
	
	public GameEnvironment() {
		randomEvents = new RandomEvents();
		inventory = new ArrayList<>();
		playerMoney = 20000;
		team = new Team();
		playerPoints = 0;
		
	}
	
	
	
	public String getTeamName() {
		return team.getTeamName();
	}
	
	public void setTeamName(String teamName) throws NameException {
		if (teamName.length() < 3 || teamName.length() > 15) {
			throw new NameException("Team name must be between 3 - 15 characters long");
		}
		else if (teamName.matches(".*[!@#$%^&*()_+\\-=\\[\\]{};':\"\\\\|,.<>\\/?~]+.*")) {
			throw new NameException("Team name must not include any special characters");
		}
			
		team.setTeamName(teamName);
	}
	
	public void setSeasonLength(int length) {

		if (length < 5 || length > 15) {
			throw new IllegalArgumentException("Season length must be between 5 and 15 weeks");
		} 
		seasonLength = length;
	}
	
	public int getSeasonLength() {
		return seasonLength;
	}
	
	public void setPlayerMoney(int value) {
		playerMoney = value;
	}
	
	public int getPlayerMoney() {
		return playerMoney;
	}
	
	public int getPlayerPoints() {
		return playerPoints;
	}
	
	public String getMoneyFormatted() {
	    String formatted = "";
	    if (playerMoney < 0) {
	        formatted += "-";
	        playerMoney = -playerMoney;
	    }
	    formatted += "$" + String.format("%,d", playerMoney);
	    return formatted;
	}
	
	public String getMoneyFormatted(int moneyInput) {
	    String formatted = "";
	    if (moneyInput < 0) {
	        formatted += "-";
	        moneyInput = -moneyInput;
	    }
	    formatted += "$" + String.format("%,d", moneyInput);
	    return formatted;
	}

	public ArrayList<Athlete> getInitialAthletes() {
		return team.getInitialAthletes();
	}
	
	public void purchaseInitialAthlete(Athlete athlete, String position) {
		team.addToInitialTeam(athlete, position);
		modifyPlayerMoney(- athlete.getContractPrice());
	}
	
	
	public ArrayList<Athlete> getTeamList() {
		return team.getTeamList();
	}
	
	public ArrayList<Athlete> getReservesList() {
		return team.getReservesList();
	}
	public ArrayList<Athlete> getShopAthletes() {
        return market.getShopAthletes();
    }
	public ArrayList<Item> getShopItems(){
		return market.getShopItems();
	}
	
    
	
	private void modifyPlayerMoney(int money) {
		playerMoney += money;
		if (playerMoney < 0) {
			playerMoney = 0;
		}
	}
	
	private void modifyPlayerPoints(int points) {
		playerPoints += points;
		if (playerPoints < 0) {
			playerPoints = 0;
		}
	}
	
	
	public void setGameDifficulty(String difficultyInput) {
		gameDifficulty = difficultyInput;
	}
	
	public String getGameDifficulty( ) {
		return gameDifficulty;
	}
	
	public void launchSetupScreen() {
		 setupWindow = new SetupScreen(this);
		 
	}
	
	public void closeSetupScreen() {
		setupWindow.closeWindow();
		launchMainScreen();
	}
	
	
	public void launchMainScreen() {
		 mainGame = new MainGame(this);
		 
	}
	
	
	public void closeMainScreen() {
		mainGame.closeWindow();
	}
	
	public void playAgain() {
	    closeMainScreen(); // Close or dispose the current main screen
	    GameEnvironment game = new GameEnvironment();
	    game.launchSetupScreen(); // Launch the setup screen to start a new game
	}
	
	
	public void increaseWeek() {
		if (currentWeek < seasonLength) {
			// Only begin performing random events after the first week
			if (currentWeek != 0) {
		    	currentRandomEvent = randomEvents.performRandomEvent(team);
			}
			currentWeek += 1;
	    	stadium.setStadium(team, currentWeek);
			market.refreshMarket();
	    	team.restoreAthletes();
	    	if (currentWeek == seasonLength) {
	    		finalWeek = true;
	    	}
		} else {
			endGame();
		}
	}
	
	public boolean isFinalWeek() {
		if (finalWeek == true) {
			return true;
		} else {
			return false;
		}
	}
	
	public int getCurrentWeek() {
		return currentWeek;
	}
	
	public Map<String, Object> endGame() {
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

	
	public void startGame() {
		market = new Market();
		currentWeek = 0;
		
		if (gameDifficulty == "Normal") {
			setPlayerMoney(5000);
		}
		else if (gameDifficulty == "Hard") {
			setPlayerMoney(0);
		}
		stadium = new Stadium(gameDifficulty);
		increaseWeek();
	}
	
	public void swapAthletes(Athlete playerAthlete, Athlete reserveAthlete) {
		team.swapAthletes(playerAthlete, reserveAthlete);
	}
	
	public Boolean checkSwappable() throws NoReserveAthletesException {
	    return team.checkSwappable();
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
    public ArrayList<Item> getInventory() {
        return inventory;
    }
    
    

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
    	team.addToTeam(athlete, position);
    }
    
    public void addAthleteToTeamFull(Athlete added, Athlete swapped) {
    	team.addAthleteAndSwap(added, swapped);
    }
    

    
    

    public void sellItem(Item item) {
    	modifyPlayerMoney(+item.getSellBackPrice());
    	inventory.remove(item);
    }

    public void sellPlayer(Athlete athlete) {
    	modifyPlayerMoney(+athlete.getSellBackPrice());
    	team.removeAthlete(athlete);
    }
    	
    public void displayPlayerList(ArrayList<Athlete> playerList) {
        int index = 1;
        System.out.println("\nPlayer List:");
        for (Athlete player : playerList) {
            System.out.println(index + ". " + player.getName());
            index++;
        }
    }
    public void displayItemList(ArrayList<Item> itemList) {
        if (itemList.isEmpty()) {
            System.out.println("Your inventory is empty.");
        } else {
            for (int i = 0; i < itemList.size(); i++) {
                Item item = itemList.get(i);
                System.out.println("Item number " + (i + 1) + ":");
                System.out.println("Name: " + item.getName());
                System.out.println("Price: $" + item.getSellBackPrice());
                System.out.println("Description: " + item.getDescription());
                System.out.println("-------------");
            }
        }
    }
    
    
    
    public ArrayList<Match> getStadiumMatches() {
    	return stadium.getMatches(team);
    }
    
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
    
    public ArrayList<Athlete> getEncounterAthletes() {
    	ArrayList<Athlete> encounterAthletes = currentMatch.getEncounterAthletes();
    	return encounterAthletes;
    }
    
    public String matchEncounter(ArrayList<Athlete> encounterAthletes) {
    	return currentMatch.encounter(encounterAthletes.get(0), encounterAthletes.get(1));
    }
    
    public String getMatchScoreString() {
    	String result = team.getTeamName() + " score: " + currentMatch.getPlayerScore();
    	result += "\n" + currentMatch.getOppositionTeamName() + " score: " + currentMatch.getOppositionScore();
    	return result;
    }
    
    public Map<String, Integer> getCurrentMatchInformation() {
	    Map<String, Object> information = new HashMap<>();
	    
	    int playerScore = match.getPlayerScore();
	    int oppositionScore = match.getOppositionScore();
	    
	    
	    results.put("name", team.getTeamName());
	    results.put("weeks", seasonLength);
	    results.put("points", playerPoints);
	    results.put("money", playerMoney);

	    
    }
    
    public ArrayList<Integer> getMatchScore() {
    	ArrayList<Integer> score = new ArrayList<Integer>();
    	int playerScore = currentMatch.getPlayerScore();
    	int oppositionScore = currentMatch.getOppositionScore();
    	score.add(playerScore);
    	score.add(oppositionScore);
    	return score;
    }
    
    public Map<String, Object> endMatch() throws IllegalStateException {
    	Map<String, Object> result = currentMatch.endGame();
    	ArrayList<Athlete> updatedTeam = currentMatch.getUpdatedTeam();
    	
    	// JR NOTE REMOVE THIS OR DONT DEPENDING ON IF IT WORKS
    	//team.updateTeamAfterMatch(updatedTeam);
    	
        int prizeMoney = (int) result.get("money");
        int pointsWon = (int) result.get("points");
        
        modifyPlayerMoney(prizeMoney);
        modifyPlayerPoints(pointsWon);

    	//currentMatch = null;
    	return result;
    }
    
    public String getMatchTeamName(Match match) {
    	String name = match.getOppositionTeamName();
    	return name;
    }
    
    public String getCurrentMatchTeamName() {
    	String name = currentMatch.getOppositionTeamName();
    	return name;
    }
    
    public int getMatchPoints(Match match) {
    	int points = match.getPointsValue();
    	return points;
    }
    
    public String getMatchPrize(Match match) {
    	String prizeMoney = match.getMoneyFormatted();
    	return prizeMoney;
    }
    
    public boolean isMatchRunning() {
    	if (currentMatch == null) {
    		return false;
    	} else {
        	return currentMatch.isMatchRunning();

    	}
    }
    
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
    
	public static void main(String[] args) {
		GameEnvironment game = new GameEnvironment();
		game.launchSetupScreen();
	}
	
	public Map<String, Object> getRandomEvent() {
		return currentRandomEvent;
		
	}
	
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
	
	// The following methods will relate to athletes
	
	public String getAthletePosition(Athlete athlete) {
		return athlete.getPosition();
	}
	
	public String getAthleteName(Athlete athlete) {
		return athlete.getName();
	}
	
	public boolean setAthleteName(Athlete athlete, String name) throws NameException {
		return athlete.setName(name);
	}
	
	public String getAthleteImageName(Athlete athlete) {
		return athlete.getImageName();
	}
	
	public String athleteToStringHTML(Athlete athlete) {
		return athlete.toStringHTML();
	}
	
	public String athleteToStringHTMLSell(Athlete athlete) {
		return athlete.toStringHTMLSell();
	}
	
	
	public boolean athleteIsReserve(Athlete athlete) {
		for (Athlete reserve : team.getReservesList()) {
		    if (athlete == reserve) {
		    	return true;
		    }
		}
		return false;
	}
	
	public boolean athleteIsInjured(Athlete athlete) {
		return athlete.isInjured();
	}
	
	public String getAthleteAlternatePosition(Athlete athlete) {
		return athlete.getAlternatePosition();
	}
	
	public void setAthletePosition(Athlete athlete, String position) {
		athlete.setPosition(position);
	}
		
	// The following methods will relate to items
	
	public String getItemName(Item item) {
		return item.getName();
	}
	
	public void useItemOnAthlete(Item item, Athlete athlete) {
		athlete.useItem(item);
    	inventory.remove(item);
	}
	
	public String itemToStringHTML(Item item) {
		return item.toStringHTML();
	}
	public void updateShopStats() {
		market.updateShopAthletesStats(currentWeek);
	}
	


}


    

	
	
	




   
    
    
    
    

    



    

	
	
	



