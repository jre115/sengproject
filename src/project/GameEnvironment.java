package project;

import java.util.ArrayList;

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
    
    ArrayList<Athlete> reservesList;
    ArrayList<Athlete> teamList;
    private ArrayList<Item> inventory;

    
	
	public GameEnvironment() {
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
		team.addToTeam(athlete, position);
		modifyPlayerMoney(- athlete.getContractPrice());
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
		
		market = new Market();
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

    public void purchaseItem(Item item) {
        if (playerMoney >= item.getContractPrice()) {
            modifyPlayerMoney(-item.getContractPrice());
            inventory.add(item);
        } else {
            System.out.println("Not enough money to purchase the item!");
        }
    }
    
    public void purchaseAthlete(Athlete athlete, String purchaseType,String position){
    	
    	
    	if (playerMoney >= athlete.getContractPrice()) {
            if( team.getTeamList().size() >= 4) {
            	ArrayList<Athlete> reservesList = team.getReservesList();
                reservesList.add(athlete); 
            } else {
            	team.addToTeam(athlete, position);;
                athlete.setPosition(purchaseType);
            }
            playerMoney -= athlete.getContractPrice();
        } else {
            System.out.println("Not enough money to purchase athlete's contract!");
        }
    }
    

	
	
	
}
