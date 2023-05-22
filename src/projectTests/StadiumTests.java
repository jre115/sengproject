package projectTests;

import static org.junit.jupiter.api.Assertions.*;



import java.util.ArrayList;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import project.Athlete;
import project.Match;
import project.Stadium;
import project.Team;

/**
 * Tests for the Stadium class
 * 
 * @author Jordan Redfern 
 * @version 1.1, May 2023.
 */
class StadiumTests {
	
	static Team playerTeam;
	
	@BeforeAll
	public static void createTeam() {
		Team team = new Team();
		ArrayList<Athlete> generatedAthletes = team.getInitialAthletes();
		for (Athlete athlete : generatedAthletes) {
			team.addToTeam(athlete, null);
		}
		
		assertEquals(team.getTeamList().size(), 4);
		
		playerTeam = team;
	}
	
	
	/**
	 * Tests that generating an easy opposition team is full with 2 attackers and 2 defenders.
	 */
	@Test
	public void testGenerateEasyOpposition() {
        Stadium stadium = new Stadium("Normal");
        Team team = stadium.generateEasyOpposition();
        
        // 4 players in the generated opposition team
        assertEquals(4, team.getTeamList().size());
        
        int defensivePlayers = 0;
        int attackingPlayers = 0;
        for (Athlete athlete : team.getTeamList()) {
        	if (athlete.getPosition().equals("Attacker")) {
        		attackingPlayers += 1;
        	} else if (athlete.getPosition().equals("Defender")) {
        		defensivePlayers += 1;
        	}
        }
        
        assertEquals(attackingPlayers, 2);
        assertEquals(defensivePlayers, 2);
	}
	
	/**
	 * Tests that the has money and points fall within the specified range when generating an easy match with an easy difficulty string set for the game
	 */
	@Test
	public void testGenerateEasyMatch() {
        Stadium stadium = new Stadium("Normal");
        double difficultyMultiplier = 1.15;
        int currentWeek = 1;
        		
        Match match = stadium.generateEasyMatch();

        double expectedMinMoneyVal = 500 * difficultyMultiplier * (0.95 + currentWeek * 0.01);
        double expectedMaxMoneyVal = 1500 * difficultyMultiplier * (0.95 + currentWeek * 0.01);
        
        int money = match.getMoney();
        assertTrue(money >= (int) expectedMinMoneyVal && match.getMoney() <= (int) expectedMaxMoneyVal);
        assertFalse(money < (int) expectedMinMoneyVal);
        assertFalse(money > (int) expectedMaxMoneyVal);

        
        int expectedOppositionTeamScore = (int) (match.getOppositionTeam().getTeamScore() / 1.7);
        assertEquals(expectedOppositionTeamScore, match.getPointsValue());
    }
	
	/**
	 * Tests that a generated medium opposition team is full with 2 attackers and 2 defenders.
	 */
	@Test
	public void testGenerateMediumOpposition() {
        Stadium stadium = new Stadium("Normal");
        Team team = stadium.generateMediumOpposition();
        
        // 4 players in the generated opposition team
        assertEquals(4, team.getTeamList().size());
        
        int defensivePlayers = 0;
        int attackingPlayers = 0;
        for (Athlete athlete : team.getTeamList()) {
        	if (athlete.getPosition().equals("Attacker")) {
        		attackingPlayers += 1;
        	} else if (athlete.getPosition().equals("Defender")) {
        		defensivePlayers += 1;
        	}
        }
        
        assertEquals(attackingPlayers, 2);
        assertEquals(defensivePlayers, 2);
	}
	
	/**
	 * Tests that the has money and points fall within the specified range when generating a medium match with the hard difficulty string
	 */
	@Test
	public void testGenerateMediumMatch() {
        Stadium stadium = new Stadium("Hard");
		int difficultyMultiplier = 1;
		int difficultyDivider = 2;
        int currentWeek = 1;
        		
        Match match = stadium.generateMediumMatch();
        
        double expectedMinMoneyVal = 1000 * difficultyMultiplier * (0.95 + currentWeek * 0.01);
        double expectedMaxMoneyVal = 2000 * difficultyMultiplier * (0.95 + currentWeek * 0.01);
        assertTrue(match.getMoney() >= (int) expectedMinMoneyVal && match.getMoney() <= (int) expectedMaxMoneyVal);
        assertFalse(match.getMoney() < (int) expectedMinMoneyVal);
        assertFalse(match.getMoney() > (int) expectedMaxMoneyVal);

        
        int expectedOppositionTeamScore = (int) (match.getOppositionTeam().getTeamScore() / difficultyDivider);
        assertEquals(expectedOppositionTeamScore, match.getPointsValue());
    }
	
	/**
	 * Tests that generated difficult opposition team is full with 2 attackers and 2 defenders.
	 */
	@Test
	public void testGenerateDifficultOpposition() {
        Stadium stadium = new Stadium("Hard");
        Team team = stadium.generateDifficultOpposition();
        
        // 4 players in the generated opposition team
        assertEquals(4, team.getTeamList().size());
        
        int defensivePlayers = 0;
        int attackingPlayers = 0;
        for (Athlete athlete : team.getTeamList()) {
        	if (athlete.getPosition().equals("Attacker")) {
        		attackingPlayers += 1;
        	} else if (athlete.getPosition().equals("Defender")) {
        		defensivePlayers += 1;
        	}
        }
        
        assertEquals(attackingPlayers, 2);
        assertEquals(defensivePlayers, 2);
	}
	
	/**
	 * Tests that the has money and points fall within the specified range when generating a medium match with the normal difficulty string
	 */
	@Test
	public void testGenerateDifficultMatch() {
        Stadium stadium = new Stadium("Normal");
		double difficultyMultiplier = 1.15;
		double difficultyDivider = 1.7;
        int currentWeek = 2;
        stadium.setStadium(playerTeam, currentWeek);
        		
        Match match = stadium.generateDifficultMatch();
        
        double expectedMinMoneyVal = 1500 * difficultyMultiplier * (0.95 + currentWeek * 0.01);
        double expectedMaxMoneyVal = 2500 * difficultyMultiplier * (0.95 + currentWeek * 0.01);
        assertTrue(match.getMoney() >= (int) expectedMinMoneyVal && match.getMoney() <= (int) expectedMaxMoneyVal);
        assertFalse(match.getMoney() < (int) expectedMinMoneyVal);
        assertFalse(match.getMoney() > (int) expectedMaxMoneyVal);

        
        int expectedOppositionTeamScore = (int) (match.getOppositionTeam().getTeamScore() / difficultyDivider);
        assertEquals(expectedOppositionTeamScore, match.getPointsValue());
    }

	
	/**
	 * Tests that when a match is played, the match is removed from the stadium list of matches available for the week
	 * And that the team name is removed from the list of available names so the team cannot be played twice in a season.
	 * (Note this is limited to approximately 40 matches, after which the name of teams will reset to the original list).
	 */
	 @Test
	    public void testTeamNameAndMatchRemovedAfterMatchPlayed() {
	        Stadium stadium = new Stadium("Normal");
	        
	        stadium.refreshStadiumMatches();
	        
	        ArrayList<Match> availableMatches = new ArrayList<>(stadium.getMatches());
	        ArrayList<String> availableTeamNames = new ArrayList<>(stadium.getAvailableOppositionTeamNames());
	        
	        int numberOfAvailableMatches = availableMatches.size();
	        int numberOfAvailableTeamNames = availableTeamNames.size();
	        
	        // get one of the available matches for test and play it
	        Match match = availableMatches.get(0);
	        stadium.playMatch(match);
	        
	        ArrayList<Match> availableMatchesAfterPlayed = stadium.getMatches();
	        ArrayList<String> availableTeamNamesAfterPlayed = stadium.getAvailableOppositionTeamNames();

	        assertEquals(numberOfAvailableMatches - 1, availableMatchesAfterPlayed.size());
	        assertEquals(numberOfAvailableTeamNames - 1, availableTeamNamesAfterPlayed.size());
	        
	        //Checks that it was actually the team name that was removed from the list of names
	        
	        ArrayList<String> result = new ArrayList<>(availableTeamNames);
	        result.removeAll(availableTeamNamesAfterPlayed);

	        assertEquals(result.get(0), match.getOppositionTeamName());
	        
	        //Checks that it was actually the match that was removed from the list of available matches
	        ArrayList<Match> matchResult = new ArrayList<>(availableMatches);
	        matchResult.removeAll(availableMatchesAfterPlayed);
	        
	        assertEquals(matchResult.get(0), match);
	}
	 
	 
}
