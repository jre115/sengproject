package project;

import java.util.ArrayList;
import java.util.Random;

import lab.MainScreen;

public class GameEnvironment {
	
	int seasonLength;
	int playerMoney;
    private SetupScreen2 setupWindow;
    private MainGame mainGame;

    private String gameDifficulty;
    int currentWeek;
    Team team;
    Market market;
    RandomEvents randomEvents; 
    
    ArrayList<Athlete> reservesList;
    ArrayList<Athlete> teamList;
    ArrayList<Item> inventory;

    
	
	public GameEnvironment() {
		market = new Market();
		randomEvents = new RandomEvents();
		inventory = new ArrayList<>();
		playerMoney = 20000;
		team = new Team();
		
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
	
	public String getMoneyFormatted() {
	    String formatted = "";
	    if (playerMoney < 0) {
	        formatted += "-";
	        playerMoney = -playerMoney;
	    }
	    formatted += "$" + String.format("%,d", playerMoney);
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
	
	public static void main(String[] args) {
		GameEnvironment game = new GameEnvironment();
		game.launchSetupScreen();
	}
	
	private void increaseWeek() {
		if (currentWeek < seasonLength) {
			currentWeek += 1;
		}
		else {
			endGame();
		}
	}
	
	public int getCurrentWeek() {
		return currentWeek;
	}
	
	private void endGame() {
		// insert code here
	}
	
	public void startGame() {
		
		
		currentWeek = 0;
		increaseWeek();
		
		if (gameDifficulty == "Normal") {
			setPlayerMoney(20000);
		}
		else if (gameDifficulty == "Hard") {
			setPlayerMoney(0);
		}
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
    public void purchaseReserveAthlete(Athlete athlete) throws InsufficientFundsException, ReservesLimitException {
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
    public void performRandomEvent() {
	    Random random = new Random();
	    int eventChance = random.nextInt(100); 

	    if (eventChance < 5) { // 5% chance for athlete joins
	        randomEvents.athleteJoins(team);
	    } else if (eventChance < 15) { // 10% chance for increase Random Player Stat
	        randomEvents.increaseRandomPlayerStat(team);
	    } else {
	        // Do nothing 85% 
	    }
	}
    
    
    public void removeItem(Item item) {
    	inventory.remove(item);
       
        }
    }
    
    
    
    

    



    

	
	
	



