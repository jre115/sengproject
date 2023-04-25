package project;

public class GameEnvironment {
	
	Team team;
	
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
	
	
}
