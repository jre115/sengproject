package project;

import java.util.ArrayList;

import lab.RocketManager;
import lab.SetupScreen;

public class GameEnvironment {
	
	Team team;
	int seasonLength;
	int playerMoney;
    private SetupScreen2 setupWindow;
    private ArrayList<Athlete> startingAthleteOptions;
    private String gameDifficulty;
	
	public GameEnvironment() {
		team = new Team();
		playerMoney = 20000;
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
	
	public String getTeamName() {
		return team.getTeamName();
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



	
	public void refreshStartingAthletes() {
		ArrayList<Athlete> availableAthletes = new ArrayList<Athlete>();
		
		
		for (int i = 1; i <= 4; i ++) {
			Athlete athlete = new Athlete();
			athlete.setContractPrice(5000);
			availableAthletes.add(athlete);
		}
		
		startingAthleteOptions = availableAthletes;
	}
	
	public ArrayList<Athlete> getStartingAthletes() {
		if (startingAthleteOptions == null) {
			refreshStartingAthletes();
		}
		return startingAthleteOptions;
	}
	
	public void purchaseAthlete(Athlete athlete, String position) {
		team.addToTeam(athlete);
		athlete.setPosition(position);
		modifyPlayerMoney(- athlete.getContractPrice());
		refreshStartingAthletes();
	}
	
	public ArrayList<Athlete> getTeamList() {
		return team.getTeamList();
	}
	
	private void modifyPlayerMoney(int money) {
		playerMoney += money;
	}
	
	public void setGameDifficulty(String difficultyInput) {
		gameDifficulty = difficultyInput;
		if (gameDifficulty == "Normal") {
			playerMoney = 20000;
		}
		else if (gameDifficulty == "Hard") {
			playerMoney = 0;
		}
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
	
    /**
	* Returns a string representation in HTML of the GameEnvironment object, including the difficulty, season length, team name, and starting money value.
    * @return a String containing the difficulty, season length, team name, and player money value.
    */
    public String toStringHTML() {
    	//result += ("\nPosition: " + this.getPosition());
    	String result = "<html>" + ("Team name: " + this.getTeamName());
        result += ("<br>Season length: " + this.getSeasonLength() + " weeks");
        result += ("<br>Difficulty: " + this.getGameDifficulty());
        result += ("<br>Team balance: " + this.getMoneyFormatted()) + "</html>";
        
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
        result += ("\nTeam balance: " + this.getMoneyFormatted());
        
        return result;
        
    }
    
	
	
	
}
