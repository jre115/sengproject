package project;

import java.util.ArrayList;

public class GameEnvironment {
	
	Team team;
	int seasonLength;
	
	public GameEnvironment() {
		team = new Team();
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
	
	public void startingTeam() {
		ArrayList<Athlete> availableAthletes = new ArrayList<Athlete>();
		
		for (int i = 1; i <= 4; i ++) {
			Athlete athlete = new Athlete();
			availableAthletes.add(athlete);
			System.out.println("\nAthlete number " + i + ":");
			System.out.println(athlete);
		}
		
		
	}
	
	
}
