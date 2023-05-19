package project;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
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
            // Handle the case when the athlete does not quit
        }
        
        return athlete;
    }

    public Athlete athleteJoins(Team team) {
       
        Athlete athlete = new Athlete();
        try { team.addToTeam(athlete, "defender"); 
        return athlete;
        
        
        }
        catch (LimitException e) {
        	return null;
        	
        }
        

        
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
    
    public Map<String, Object> performRandomEvent(Team team) {
        Random random = new Random();
        int eventChance = random.nextInt(100);

        Map<String, Object> eventDetails = new HashMap<>();

        if (eventChance < 1) { // 1% chance for athlete quits
            Athlete athleteToQuit = athleteQuits(team);
            if (athleteToQuit != null) {
                eventDetails.put("eventType", "athleteQuits");
                eventDetails.put("athlete", athleteToQuit);
            }
        } else if (eventChance < 5) { // 4% chance for athlete joins
            Athlete newAthlete = athleteJoins(team);
            eventDetails.put("eventType", "athleteJoins");
            eventDetails.put("athlete", newAthlete);
        } else if (eventChance < 15) { // 10% chance for increasing a random player's stat
            Athlete athlete = increaseRandomPlayerStat(team);
            eventDetails.put("eventType", "increaseStat");
            eventDetails.put("athlete", athlete);
        } else {
            eventDetails.put("eventType", "rest");
        }

        return eventDetails;
    }
}
	




