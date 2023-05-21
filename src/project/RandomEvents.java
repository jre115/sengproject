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
        Athlete athlete = teamList.get(random.nextInt(teamSize-1));

        if (athlete.getPosition().equals("Injured")) {
            int quitChance = 50; // 50% chance if injured
            if (random.nextInt(100) < quitChance) {
                team.removeAthlete(athlete);
                return athlete;
            }
        } else {
            int quitChance = 5; // 5% chance if not injured
            if (random.nextInt(100) < quitChance) {
                team.removeAthlete(athlete);
                return athlete;
            }
        }

        return null; // No athlete quit
    }

    public Athlete athleteJoins(Team team) {
       
        Athlete athlete = new Athlete();
        if (team.getTeamList().size() < 4) {
        	team.addToTeam(athlete, null);
        } else {
        	try {
        		team.addToReserves(athlete);
        	} catch (LimitException e) {
        		return null;
        	}
        }
        
        return athlete;

    }

    public Athlete increaseRandomPlayerStat(Team team) {
        ArrayList<Athlete> teamList = team.getTeamList();
        int teamSize = teamList.size();
        
        if (teamSize > 0) {
            Random random = new Random();
            Athlete randomPlayer = teamList.get(random.nextInt(teamSize-1));

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

        if (eventChance < 80) { // 1% chance for athlete quits
            Athlete athleteToQuit = athleteQuits(team);
            if (athleteToQuit == null) {
            	eventDetails.put("eventType", "athleteQuitsFalse");
            	
            	
            }
            
            if (athleteToQuit != null) {
                eventDetails.put("eventType", "athleteQuits");
                eventDetails.put("athlete", athleteToQuit);
            }
        } else if (eventChance < 4) { // 4% chance for increasing a random player's stat
            Athlete athlete = increaseRandomPlayerStat(team);
            eventDetails.put("eventType", "increaseStat");
            eventDetails.put("athlete", athlete);
        } else {
            // Calculate the chance of athlete joins based on the reserves list size
            int reservesSize = team.getReservesList().size();
            int joinChance = (5 - reservesSize) * 5; 

            if (eventChance < (15 + joinChance)) {
                Athlete newAthlete = athleteJoins(team);
                eventDetails.put("eventType", "athleteJoins");
                eventDetails.put("athlete", newAthlete);
            } else {
                eventDetails.put("eventType", "rest");
            }
        }

        return eventDetails;
    }
}
	




