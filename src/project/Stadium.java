package project;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

public class Stadium {
	
    int currentWeek;
    double difficultyMultiplier;
    double difficultyDivider;
    Team playerTeam;
    ArrayList<String> teamNames;
    ArrayList<Match> matches;
    
    public Stadium(String difficulty) {
    	currentWeek = 0;
    	playerTeam = new Team();
    	matches = new ArrayList<Match>();
    	teamNames = new ArrayList<String>();
		if (difficulty == "Normal") {
			difficultyMultiplier = 1.15;
			difficultyDivider = 2;
		} else {
			difficultyMultiplier = 1;
			difficultyDivider = 1.7;
		}
    }
    
	public void setStadium(Team team, int currentWeek) {
		this.currentWeek = currentWeek;
		playerTeam = team;
		refreshStadiumMatches();
		
	}
	
	public String getTeamName() {
		
		if (teamNames.size() == 0) {
			String[] nameOptions = {"Phoenix Blaze", "Crimson Crests", "Thundering Hooves", "Mystic Mavericks", "Emerald Enchanters", 
		            "Frostfire Flyers", "Golden Gryphons", "Sapphire Storms", "Radiant Raptors", "Velvet Vixens", 
		            "Blackwings", "Broomhounds", "Crestbreakers", "Darkblades", "Dragonscales", "Firewings", 
		            "Goldenrods", "Lightningbolts", "Nightshades", "Phoenixflames", "Redravens", "Shadowcasters", 
		            "Silverstreaks", "Stormchasers", "Thunderclaps", "Cobalt Chasers", "Azure Arrows", "Crimson Comets", 
		            "Emerald Eagles", "Gilded Gargoyles", "Onyx Owls", "Silver Seekers", "Golden Griffins", 
		            "Ruby Raptors", "Sapphire Snitches", "Topaz Tornadoes", "Jade Jinxes", "Amethyst Aces", 
		            "Diamond Drakes", "Obsidian Ogres", "Skyriders", "Comet Chasers"};
			teamNames = new ArrayList<String>();
			
		    for (String name : nameOptions) {
		        teamNames.add(name);
		    }
			
		}

		Random random = new Random();
		String randomName = teamNames.get(random.nextInt(teamNames.size() - 1));
		
		return randomName;
		
	}
	
	
	public void playMatch(Match match) {
		teamNames.remove(match.getOppositionTeamName());
		matches.remove(match);
	}
	
	public Match generateEasyOpposition() {
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
		Random rand = new Random();
		
		int minMoneyVal = 500;
		int maxMoneyVal = 1500;
		int moneyVal = (int) Math.round((rand.nextInt(maxMoneyVal - minMoneyVal + 1) + minMoneyVal)
                * difficultyMultiplier * (0.95 + currentWeek * 0.01));
		
		int points = (int) (oppositionTeam.getTeamScore() / difficultyDivider);

		Match match = new Match(playerTeam, oppositionTeam, moneyVal, points);
		
		return match;
	}
	

	
	
	public Match generateMediumOpposition() {
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
		
		Random rand = new Random();
		
		int minMoneyVal = 1000;
		int maxMoneyVal = 2000;
		int moneyVal = (int) Math.round((rand.nextInt(maxMoneyVal - minMoneyVal + 1) + minMoneyVal)
                * difficultyMultiplier * (0.95 + currentWeek * 0.01));
		
		int points = (int) (oppositionTeam.getTeamScore() / difficultyDivider);
		
		Match match = new Match(playerTeam, oppositionTeam, moneyVal, points);
		
		return match;

	}
	
	public Match generateDifficultOpposition() {
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
	    

		Random rand = new Random();
		
		int minMoneyVal = 1500;
		int maxMoneyVal = 2500;
		int moneyVal = (int) Math.round((rand.nextInt(maxMoneyVal - minMoneyVal + 1) + minMoneyVal)
                * difficultyMultiplier * (0.95 + currentWeek * 0.01));
		
		int points = (int) (oppositionTeam.getTeamScore() / difficultyDivider);

		Match match = new Match(playerTeam, oppositionTeam, moneyVal, points);
		
		return match;

	}
	
    public void refreshStadiumMatches() {
    	
    	Random rand = new Random();
    	ArrayList<Match> matchOptions = new ArrayList<Match>();
    	
    	int numOfEasyMatches = rand.nextInt(2) + 1;
    	
    	for (int i = 0 ; i < numOfEasyMatches ; i ++) {
    		matchOptions.add(generateEasyOpposition());
    	}
    	if (numOfEasyMatches == 1) {
    		matchOptions.add(generateMediumOpposition());
    	}
    	
    	matchOptions.add(generateDifficultOpposition());
    	
    	matches = matchOptions;
    	
    }
    
    // Gets matches for the stadium view, each time will refresh the playerTeam to current team
    public ArrayList<Match> getMatches(Team currentPlayerTeam) {
    	ArrayList<Match> refreshedMatches = new ArrayList<Match>();
    	for (Match match : matches) {
    		Match newMatch = new Match(currentPlayerTeam, match.getOppositionTeam(), match.getMoney(), match.getPointsValue());
    		refreshedMatches.add(newMatch);
    	}
    	matches = refreshedMatches;
    	return matches;
    }
   
	
	
	

}
