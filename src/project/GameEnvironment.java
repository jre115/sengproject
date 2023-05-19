package project;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;


public class GameEnvironment {
	
	int seasonLength;
	int playerMoney;
	int playerPoints;
    private SetupScreen2 setupWindow;
    private MainGame mainGame;
    boolean gameEnded;
    boolean finalWeek;

    private String gameDifficulty;
    int currentWeek;
    Team team;
    Market market;
    RandomEvents randomEvents; 
    Stadium stadium;
    //ArrayList<Match> stadiumMatches;
    Match currentMatch;
    
    ArrayList<Athlete> reservesList;
    ArrayList<Athlete> teamList;
    private ArrayList<Item> inventory;

    
	
	public GameEnvironment() {
		market = new Market();
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
	
	/**
	 * Adds reserve to the team automatically assigning them to the position there are less of in the team
	 *
	 * @param reserve the reserve athlete that is being added to the team 
	 */
	public void addReserveToTeam(Athlete reserve) throws LimitException {
		if (getTeamList().size() >= 4) {
			throw new LimitException("Cannot have more than 4 players in the playing team");
		}
		int defenderCount = 0;
		int attackerCount = 0;
		for (Athlete athlete : getTeamList()) {
			if (athlete.getPosition() == "Defender") {
				defenderCount += 1;
			} else {
				attackerCount += 1;
			}
		}
		if (attackerCount < defenderCount) {
			team.addToTeam(reserve, "Attacker");
		} else {
			team.addToTeam(reserve, "Defender");
		}
	}
	
	
	public ArrayList<Athlete> getTeamList() {
		return team.getTeamList();
	}
	
	public ArrayList<Athlete> getReservesList() {
		return team.getReservesList();
	}
	//*cpe81 added get player shop
	public ArrayList<Athlete> getShopAthletes() {
        return market.playerShop();
    }
	public ArrayList<Item> getShopItems(){
		return market.itemShop();
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
		 setupWindow = new SetupScreen2(this);
		 
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
		//// JR NOTE
		//when i have cam's market i need to make a game finish if the player does not have a full team nor enough money to buy more athletes;
		if (currentWeek < seasonLength) {
			currentWeek += 1;
	    	stadium.setStadium(team, currentWeek);
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
	    if (!finalWeek) {
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
			setPlayerMoney(20000);
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
	    if (getReservesList().isEmpty() == true) {
	        throw new NoReserveAthletesException();
	    }
	    return true;
	}
	
	public boolean athleteIsReserve(Athlete athlete) {
		for (Athlete reserve : team.getReservesList()) {
		    if (athlete == reserve) {
		    	return true;
		    }
		}
		return false;
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
        }
    }
    
    public void purchaseAthlete(Athlete athlete, String position) throws InsufficientFundsException, LimitException {
    	
    	if (playerMoney < athlete.getContractPrice()) {
    		throw new InsufficientFundsException();
    	} else {
    		modifyPlayerMoney(-athlete.getContractPrice());
    		team.addToTeam(athlete, position);
    	}
    }
    public void purchaseReserveAthlete(Athlete athlete) throws InsufficientFundsException, LimitException {
        if (playerMoney < athlete.getContractPrice()) {
            throw new InsufficientFundsException();
        } else {
            modifyPlayerMoney(-athlete.getContractPrice());
            reservesList.add(athlete);
        }
    }
    
    
    

    public void sellItem(Item item) {
        if (inventory.contains(item)) {
            modifyPlayerMoney(+item.getSellBackPrice());
            inventory.remove(item);
        } else {
            System.out.println("Item not found in inventory!");
        }
    }

    public void sellPlayer(Athlete athlete) {
        if (team.getTeamList().contains(athlete)) {
            modifyPlayerMoney(+athlete.getSellBackPrice());
            team.removeFromTeam(athlete);
        } else {
            System.out.println("Player not found in team list!");
        }
    }

    public void sellReservePlayer(Athlete athlete) {
        if (team.getReservesList().contains(athlete)) {
            modifyPlayerMoney(+athlete.getSellBackPrice());
            team.removeFromReserve(athlete);
        } else {
            System.out.println("Player not found in reserves list!");
        }
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
    
    
    
    public void removeItem(Item item) {
    	inventory.remove(item);
       
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
    	
    	team.updateTeamAfterMatch(updatedTeam);
    	
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
    		if (athleteTrained.matchesAthlete(athlete)) {
    			athlete.trainAthlete();
    		}
    	}
    	for (Athlete reserve : team.getReservesList()) {
    		if (athleteTrained.matchesAthlete(reserve)) {
    			reserve.trainAthlete();
    		}
    	}
    }
    
	public static void main(String[] args) {
		GameEnvironment game = new GameEnvironment();
		game.launchSetupScreen();
	}
	
	public void preformRandomEvent() {
		randomEvents.performRandomEvent(team);
		
	}

}


    

	
	
	




   
    
    
    
    

    



    

	
	
	



