package project;

import java.util.ArrayList;

import lab.RocketManager;
import lab.SetupScreen;

public class GameEnvironment {
	
	Team team;
	int seasonLength;
	int playerMoney;
    private SetupScreen setupWindow;
	
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



	
	public ArrayList<Athlete> generateStartingAthletes() {
		ArrayList<Athlete> availableAthletes = new ArrayList<Athlete>();
		
		
		for (int i = 1; i <= 4; i ++) {
			Athlete athlete = new Athlete();
			athlete.setContractPrice(5000);
			availableAthletes.add(athlete);
		}
		
		return availableAthletes;
		
		
	}
	
	public void launchSetupScreen() {
		 setupWindow = new SetupScreen(this);
		 
	}
	
	public void closeSetupScreen() {
		//setupWindow.closeWindow();
		//launchMainScreen();
	}
	
	public static void main(String[] args) {
		GameEnvironment game = new GameEnvironment();
		game.launchSetupScreen();
	}
	
	
}
