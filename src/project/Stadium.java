package project;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

/**
 * The stadium class represents a stadium in the game environment
 * It manages the current matches available for the week based on the game difficulty and current week.
 * @author jordan redfern
 *
 */
public class Stadium {
	
	/**
	 * The current week in the game
	 */
    int currentWeek;
    
    /**
     * The multiplier based on the difficulty of the game
     */
    double difficultyMultiplier;
    
    /**
     * The divider based on the difficulty of the game
     */
    double difficultyDivider;
    
    /**
     * The user/player's team
     */
    Team playerTeam;
    
    /**
     * A list of team names available for a new match
     */
    ArrayList<String> teamNames;
    
    /**
     * The list of matches available for the week
     */
    ArrayList<Match> matches;
    
    /**
     * Creates a new stadium with the specified game difficulty;
     * If the difficulty is set to Normal - the difficulty multiplier is set to 1.15 and the difficulty divider is set to 2
     * If the difficulty is set to Hard, the difficulty multiplier is set to 1 and the difficulty divider is set to 1.7
     * @param difficulty the game difficulty level
     */
    public Stadium(String difficulty) {
    	currentWeek = 0;
    	playerTeam = new Team();
    	matches = new ArrayList<Match>();
    	teamNames = new ArrayList<String>();
		if (difficulty == "Normal") {
			difficultyMultiplier = 1.15;
			difficultyDivider = 1.7;
		} else {
			difficultyMultiplier = 1;
			difficultyDivider = 2;
		}
    }
    
    /**
     * Sets the stadium with the specified team and current week
     * It updates the current week and player team, and refreshes the stadium matches
     * @param team the player/user's team
     * @param currentWeek the current week of the game
     */
	public void setStadium(Team team, int currentWeek) {
		this.currentWeek = currentWeek;
		playerTeam = team;
		refreshStadiumMatches();
		
	}
	
	/**
	 * retrieves a random team name from the available team names.
	 * If the list of team names is empty, it sets the list with the set of predefined names
	 * @return a random team name
	 */
	public String getTeamName() {
		
		if (teamNames.size() == 0) {
			String[] nameOptions = {"Phoenix Blaze", "Crimson Crests", "Team Thunder", "Mavericks", "Enchanters", 
		            "Fire Flyers", "Golden Owls", "Strong Storms", "Red Raptors", "Velvet Vixens", 
		            "Blackwings", "Broomhounds", "Crestbreakers", "Darkblades", "Dragonscales", "Firewings", 
		            "Goldenrods", "Lightningbolts", "Nightshades", "Phoenixflames", "Redravens", "Shadowcasters", 
		            "Silverstreaks", "Stormchasers", "Thunderclaps", "Cobalt Chasers", "Azure Arrows", "Cobalt Comets", 
		            "Emerald Eagles", "Gilded Greys", "Onyx Owls", "Silver Seekers", "Golden Griffins", 
		            "Ruby Raptors", "Sapphire Suns", "Tornadoes", "Jade Jinxes", "Amethyst Aces", 
		            "Diamond Drakes", "Obsidian Ogres", "Skyriders", "Comet Chasers", "Blazing Flames", "Scarlet Crests",
		            "Emerald Charm", "Mystic Magic", "Velvet Flames", "Crimson Break", "Dark Wings", "Icy Flyers"};
			teamNames = new ArrayList<String>();
			
		    for (String name : nameOptions) {
		        teamNames.add(name);
		    }
			
		}
		
		Random random = new Random();
		String randomName = teamNames.get(random.nextInt(teamNames.size()));
		
		return randomName;
		
	}
	
	/**
	 * Removes the selected match from the list of matches and removes the opposition team name from the list of team names
	 * Name is removed so that each team is only played once during a game.
	 * @param match the match to be removed
	 */
	public void playMatch(Match match) {
		matches.remove(match);
		teamNames.remove(match.getOppositionTeamName());
	}
	
	/**
     * Generates the match object for an "easy" match. 
     * @return the Match object including the player team, the opposition team and the points and money reward for the match. 
     */
    public Match generateEasyMatch() {
    	Team oppositionTeam = generateEasyOpposition();
    	int moneyReward = generateMatchMoney(500, 1500);
    	int pointsReward = generateMatchPoints(oppositionTeam);
    	
    	Match match = new Match(playerTeam, oppositionTeam, moneyReward, pointsReward);
    	return match;
    }
    
    /**
     * Generates the match object for a "medium" match. 
     * @return the Match object including the player team, the opposition team and the points and money reward for the match. 
     */
    public Match generateMediumMatch() {
    	Team oppositionTeam = generateMediumOpposition();
    	int moneyReward = generateMatchMoney(1000, 2000);
    	int pointsReward = generateMatchPoints(oppositionTeam);
    	
    	Match match = new Match(playerTeam, oppositionTeam, moneyReward, pointsReward);
    	return match;
    }
    
    /**
     * Generates the match object for a "medium" match. 
     * @return the Match object including the player team, the opposition team and the points and money reward for the match. 
     */
    public Match generateDifficultMatch() {
    	Team oppositionTeam = generateDifficultOpposition();
    	int moneyReward = generateMatchMoney(1500, 2500);
    	int pointsReward = generateMatchPoints(oppositionTeam);
    	
    	Match match = new Match(playerTeam, oppositionTeam, moneyReward, pointsReward);
    	return match;
    }
	
	
	/**
	 * Generates an easy opposition team for a match.
	 * An easy team is generated by creating 2 athletes, setting their statistics based on the offensive score of the player team and setting them as attackers in the opposition team,
	 * and two athletes and setting them based on the defensive team score of the player team and setting the two athletes as defenders in the opposition team.
	 * This should generate a caliber of opposition players that is similar to the player's team.
	 * @return oppositionTeam the generated opposition team
	 */
	public Team generateEasyOpposition() {
		Team oppositionTeam = new Team();
		oppositionTeam.setTeamName(getTeamName());

		for (int i = 0 ; i < 4 ; i ++) {
			Athlete athlete = new Athlete();
			if (i < 2) {
				athlete.setAthleteStatisticsBasedOnOffensive(playerTeam.getOffensiveScore());
				oppositionTeam.addToInitialTeam(athlete, "Attacker");
			} else {
				athlete.setAthleteStatisticsBasedOnDefensive(playerTeam.getDefensiveScore());
				oppositionTeam.addToInitialTeam(athlete, "Defender");
			}
			
			
		}
		
		return oppositionTeam;
	}
	
	/**
	 * Generates a medium-level opposition team for a match.
	 * The opposition team is created by generating new athletes, if their offensive score is higher than their defensive score they are set as an attacker and vice versa.
	 * The athlete's statistics are then increased using the current week as a multiplier to ensure matches are increasing in difficulty as the game progresses
	 * @return oppositionTeam the generated opposition team
	 */
	public Team generateMediumOpposition() {
		Team oppositionTeam = new Team();
		oppositionTeam.setTeamName(getTeamName());
				
		int attackerCount = 0;
	    int defenderCount = 0;

	    while (attackerCount < 2 || defenderCount < 2) {
	        Athlete athlete = new Athlete();
	        athlete.increaseStatistics(0.95 + (currentWeek * 0.05));

	        if (athlete.getDefensive() > athlete.getOffensive()) {
	            if (defenderCount < 2) {
	                oppositionTeam.addToInitialTeam(athlete, "Defender");
	                defenderCount++;
	            }
	        } else {
	            if (attackerCount < 2) {
	                oppositionTeam.addToInitialTeam(athlete, "Attacker");
	                attackerCount++; 
	            }
	        }
	    }
		
		return oppositionTeam;

	}
	
	/**
	 * Generates a difficulty-level opposition team for a match.
	 * The opposition team is created by generating new athletes, if their offensive score is higher than their defensive score they are set as an attacker and vice versa.
	 * The athlete's statistics are then increased using the current week as a multiplier to ensure matches are increasing in difficulty as the game progresses
	 * Note that the increase statistics multiplier is higher than the one in generateMediumOpposition() to ensure the opposition team is more challenging
	 * The money and points for prize in the match are based on the difficulty divider and multiplier from the game's set difficulty. 
	 * @return oppositionTeam the generated opposition team
	 */
	public Team generateDifficultOpposition() {
		Team oppositionTeam = new Team();
		oppositionTeam.setTeamName(getTeamName());
		
		int attackerCount = 0;
	    int defenderCount = 0;
	    
	    while (attackerCount < 2 || defenderCount < 2) {
	        Athlete athlete = new Athlete();
	        athlete.increaseStatistics(1.1 + (currentWeek * 0.05));

	        if (athlete.getDefensive() > athlete.getOffensive()) {
	            if (defenderCount < 2) {
	                oppositionTeam.addToInitialTeam(athlete, "Defender");
	                defenderCount++;
	            }
	        } else {
	            if (attackerCount < 2) {
	                oppositionTeam.addToInitialTeam(athlete, "Attacker");
	                attackerCount++;
	            }
	        }
	    }
		
		return oppositionTeam;
	}
	
	 /**
     * Generates a random value for the points won in the game between the minimum and maximum range parameters based on the difficulty multiplier and current week
     * @param minimumRange the minimum value of prize money before the current week and difficulty multiplier is taken into account
     * @param maximumRange the maximum value of prize money before the current week and difficulty multiplier is taken into account
     * @return moneyValue the money reward for winning the match
     */
    public int generateMatchMoney(int minimumRange, int maximumRange) {
		Random rand = new Random();

    	int moneyValue = (int) Math.round((rand.nextInt(maximumRange - minimumRange + 1) + minimumRange)
                * difficultyMultiplier * (0.95 + currentWeek * 0.01));
    	return moneyValue;
    }
    
    /**
     * Generates the points reward for the match that will be added to the team who wins the match. 
     * The value is based on the opposition team's score and the difficulty divider.
     * @return points the amount of points on offer for winning the match
     */
    public int generateMatchPoints(Team oppositionTeam) {
		int points = (int) (oppositionTeam.getTeamScore() / difficultyDivider);
		return points;
    }
	
	/**
	 * Refreshes the stadium options for matches (matchOptions) by generating new match options.
	 * The method randomly selects between 1 and 2 easy matches and 1 difficult opposition.
	 * If there is only 1 easy match, a medium match will be generated. 
	 */
    public void refreshStadiumMatches() {
    	
    	Random rand = new Random();
    	ArrayList<Match> matchOptions = new ArrayList<Match>();
    	
    	int numOfEasyMatches = rand.nextInt(2) + 1;
    	
    	for (int i = 0 ; i < numOfEasyMatches ; i ++) {
    		matchOptions.add(generateEasyMatch()); 
    	}
    	if (numOfEasyMatches == 1) {
    		matchOptions.add(generateMediumMatch());
    	}
    	
    	matchOptions.add(generateDifficultMatch());
    	
    	matches = matchOptions;
    	 
    }
    
    
    /**
     * Returns the current matches available.
     * Note: The currentPlayerTeam parameter is directly used within the game environment, 
     * so there is no need to refresh the team from when matches were refreshed.
     * @return list of match objects representing the available matches to play for the current week
     */
    public ArrayList<Match> getMatches() {
    	return matches;
    }
    
    /**
     * Returns the array list of strings with the available names to be generated for the opposition teams. 
     * @return teamNames the list of available strings to name an opposition team.
     */
    public ArrayList<String> getAvailableOppositionTeamNames() {
    	return teamNames;
    }
    

   
}
