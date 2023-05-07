package project;

import java.util.ArrayList;

public class GameEnvironment {
	
	int seasonLength;
	int playerMoney;
    private SetupScreen2 setupWindow;
    private ArrayList<Athlete> initialAthleteOptions;
    private String gameDifficulty;
    int currentWeek;
    
	private String teamName;
	protected ArrayList<Athlete> teamList;
	protected ArrayList<Athlete> reservesList;
	
	public GameEnvironment() {
		playerMoney = 20000;
		teamList = new ArrayList<Athlete>();
		reservesList = new ArrayList<Athlete>();
		
		// this is for a test
		Athlete athlete = new Athlete();
		reservesList.add(athlete); // THIS IS FOR TESTIGN!!
	}
	
	public String getTeamName() {
		return teamName;
	}
	
	public void setTeamName(String teamName) throws NameException {
		if (teamName.length() < 3 || teamName.length() > 15) {
			throw new NameException("Team name must be between 3 - 15 characters long");
		}
		else if (teamName.matches(".*[!@#$%^&*()_+\\-=\\[\\]{};':\"\\\\|,.<>\\/?~]+.*")) {
			throw new NameException("Team name must not include any special characters");
		}
			
		this.teamName = teamName;
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

	
	public void refreshInitialAthletes() {
		ArrayList<Athlete> availableAthletes = new ArrayList<Athlete>();
		
		
		for (int i = 1; i <= 4; i ++) {
			Athlete athlete = new Athlete();
			athlete.setContractPrice(5000);
			availableAthletes.add(athlete);
		}
		
		initialAthleteOptions = availableAthletes;
	}
	
	public ArrayList<Athlete> getInitialAthletes() {
		if (initialAthleteOptions == null) {
			refreshInitialAthletes();
		}
		return initialAthleteOptions;
	}
	
	public void purchaseInitialAthlete(Athlete athlete, String position) {
		addToInitialTeam(athlete);
		athlete.setPosition(position);
		modifyPlayerMoney(- athlete.getContractPrice());
		refreshInitialAthletes();
	}
	
	public void addToInitialTeam(Athlete athlete) {
		teamList.add(athlete);
	}
	
	public ArrayList<Athlete> getTeamList() {
		return teamList;
	}
	
	public ArrayList<Athlete> getReservesList() {
		return reservesList;
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
		//setupWindow.closeWindow();
		//launchMainScreen();
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
		int playerIndex = this.getTeamList().indexOf(playerAthlete);
		int reserveIndex = this.getReservesList().indexOf(reserveAthlete);
		String position = playerAthlete.getPosition();
		
		playerAthlete.setPosition("Null");
		reserveAthlete.setPosition(position);
		
		this.getTeamList().set(playerIndex, reserveAthlete);
		this.getReservesList().set(reserveIndex, playerAthlete);
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


	
	
	
}
