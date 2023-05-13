package project;

import java.util.ArrayList;
import java.util.Random;

public class RandomEvents {
	
	 public void athleteQuits(Team team) {
		 // injury yet to be implemented
	    	
	    }
	
	
	public void athleteJoins(Team team) {
		ArrayList<Athlete> teamList = team.getTeamList();
		ArrayList<Athlete> reservesList = team.getReservesList();
		Athlete athlete = new Athlete();
		if (teamList.size() > 4) {
			teamList.add(athlete);
			System.out.println(athlete.getName()+" Has been added to team ");
		}else {
			reservesList.add(athlete);
			System.out.println(athlete.getName()+" Has been added to reserves ");
		}
		
		
	}
	public void increaseRandomPlayerStat(Team team) {
	    
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
	                int increasedOffensive = currentOffensive + random.nextInt(11) + 5; // Increase by 5-15
	                randomPlayer.setOffensive(increasedOffensive);
	                break;
	            case 1: // Defensive
	                int currentDefensive = randomPlayer.getDefensive();
	                int increasedDefensive = currentDefensive + random.nextInt(11) + 5; // Increase by 5-15
	                randomPlayer.setDefensive(increasedDefensive);
	                break;
	            case 2: // Stamina
	                int currentStamina = randomPlayer.getStamina();
	                int increasedStamina = currentStamina + random.nextInt(11) + 5; // Increase by 5-15
	                randomPlayer.setStamina(increasedStamina);
	                break;
	        }

	        System.out.println(randomPlayer.toString());
	        System.out.println("stats increased: ");
	        
	    } else {
	        System.out.println("No players in the team. Unable to increase player's stat.");
	    }
	    
	   
	    
	    
	    
	    
	    
	}
	
}
