package project;

import java.util.ArrayList;
import java.util.Random;

public class RandomEvents {
	
	public Athlete athleteQuits(Team team) {
		
		ArrayList<Athlete> teamList = team.getTeamList();
		int teamSize = teamList.size();
		Random random = new Random();
        Athlete athlete = teamList.get(random.nextInt(teamSize));
        if (athlete.getPosition().equals("Injured")) {
        	team.removeFromTeam(athlete);
        	
            
        } else {
            
            
        }
		return athlete;
    }
	
	
	 public Athlete athleteJoins(Team team) {
		    ArrayList<Athlete> teamList = team.getTeamList();
		    ArrayList<Athlete> reservesList = team.getReservesList();
		    Athlete athlete = new Athlete();

		    if (teamList.size() < 5) {
		        teamList.add(athlete);
		    } else if (reservesList.size() < 5) {
		        reservesList.add(athlete);
		    } else {
		        // Both team list and reserves list are full, do nothing
		        return null;
		    }
		    return athlete;
		}
		
		
	
	public Athlete increaseRandomPlayerStat(Team team) {
	    
	    ArrayList<Athlete> teamList = team.getTeamList();
	    int teamSize = teamList.size();
	    if (teamSize > 0) {
	        Random random = new Random();
	        Athlete randomPlayer = teamList.get(random.nextInt(teamSize));

	        // Determine which statistic to increase (offensive, defensive, or stamina)
	        int statToIncrease = random.nextInt(3); // 0 - Offensive, 1 - Defensive, 2 - Stamina

	        
	        switch (statToIncrease) {
	            case 0: // Offensive
	                int currentOffensive = randomPlayer.getOffensive();
	                int increasedOffensive = currentOffensive + random.nextInt(5) + 5; // Increase by 5-15
	                randomPlayer.setOffensive(increasedOffensive);
	                break;
	            case 1: // Defensive
	                int currentDefensive = randomPlayer.getDefensive();
	                int increasedDefensive = currentDefensive + random.nextInt(5) + 5; // Increase by 5-15
	                randomPlayer.setDefensive(increasedDefensive);
	                break;
	            case 2: // Stamina
	                int currentStamina = randomPlayer.getStamina();
	                int increasedStamina = currentStamina + random.nextInt(5) + 5; // Increase by 5-15
	                randomPlayer.setStamina(increasedStamina);
	                break;
	        }
	        return randomPlayer;

	       
	        
	    } else {
	        return null;
	    }
	    
	   
	    
	    
	    
	    
	    
	}
	
}
