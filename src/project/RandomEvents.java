package project;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;


/**
 * 
 * 
 *
 */
public class RandomEvents {
	/**
	 * a random event that has the chance of making an athlete leave
	 * adds reserve list and team list into array list(entireTeam) checks a random player if their injured there is 50% chance they quit, if they are not only 5% chance they quit
	 * @param team used to reserve list and the team list to get entire team list
	 * @return athlete that is removed or return null for if injury doesn't cause the athlete to quit
	 */
	

	public Athlete athleteQuits(Team team) {
        ArrayList<Athlete> entireTeam = team.getTeamList();
        entireTeam.addAll(team.getReservesList());
        int teamSize = entireTeam.size();
        Random random = new Random();
        Athlete athlete = entireTeam.get(random.nextInt(teamSize) - 1);
        
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
/**
 * Adds an athlete to the specified team. If the team's roster is not full (less than 4 athletes)
 * the athlete is added to the main team. Otherwise, the athlete is added to the reserves.
 * @param team The team to which the athlete will be added.
 * @return The added athlete if successfully added, or null if the team's roster and reserves are full.
 * @throws LimitException if the team's reserves are full and the athlete cannot be added
 */
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
    /**
     * Increases a random statistic (offensive, defensive, or stamina) of a player from the team.
     * The player is randomly selected from the team's main roster and reserves.
     *
     * @param team The team from which the player will be selected.
     * @return The player whose statistic was increased, or null if the team has no players.
     */

    public Athlete increaseRandomPlayerStat(Team team) {
        ArrayList<Athlete> entireTeam = new ArrayList<>(team.getTeamList());
        entireTeam.addAll(team.getReservesList());
        int teamSize = entireTeam.size();

        if (teamSize > 0) {
            Random random = new Random();
            Athlete randomPlayer = entireTeam.get(random.nextInt(teamSize));

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
    
    /**
     * Performs a random event in the game for the specified team.
     * The event can be an athlete quitting, increasing a random player's stat, an athlete joining the team, or a resting event.
     *
     * @param team The team for which the random event will occur.
     * @return A map containing the details of the event:
     *          "eventType": the type of the event that happens when preformrandomeEvent is called("athleteQuits", "athleteQuitsFalse", "increaseStat", "athleteJoins", or "rest").
     *         "athlete": The athlete involved in the event if any.
     */
    public Map<String, Object> performRandomEvent(Team team) {
        Random random = new Random();
        int eventChance = random.nextInt(100);

        Map<String, Object> eventDetails = new HashMap<>();

        if (eventChance < 1) { // 1% chance for athlete quits
            Athlete athleteToQuit = athleteQuits(team);
            if (athleteToQuit == null) {
            	eventDetails.put("eventType", "athleteQuitsFalse");
            	
            	
            }
            
            if (athleteToQuit != null) {
                eventDetails.put("eventType", "athleteQuits");
                eventDetails.put("athlete", athleteToQuit);
            }
        } else if (eventChance < 5) { // 5% chance for increasing a random player's stat
            Athlete athlete = increaseRandomPlayerStat(team);
            eventDetails.put("eventType", "increaseStat");
            eventDetails.put("athlete", athlete);
        } else {
            // Calculate the chance of athlete joins based on the reserves list size
            int reservesSize = team.getReservesList().size();
            int joinChance = (5 - reservesSize) * 3; 

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
	




