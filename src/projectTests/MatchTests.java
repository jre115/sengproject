package projectTests;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import project.Athlete;
import project.Match;
import project.Stadium;
import project.Team;
import projectExceptions.LimitException;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

class MatchTests {
	
	static Team playerTeam;
	static Match finishedMatch;
	
	/**
	 * Creates a team and finished match used for testing
	 */
	@BeforeAll
	public static void createTeamAndGenerateFinishedGame() throws LimitException {
		Team team = new Team();
		ArrayList<Athlete> generatedAthletes = team.getInitialAthletes();
		
		int defenderCount = 0;
		for (Athlete athlete : generatedAthletes) {
			if (defenderCount < 2) {
				defenderCount += 1;
				team.addToTeam(athlete, "Defender");
			} else {
				team.addToTeam(athlete, "Attacker");
			}
			
		}
		
		assertEquals(team.getTeamList().size(), 4);
		
		playerTeam = team;
		Stadium stadium = new Stadium("Normal");
		stadium.setStadium(playerTeam, 2);
		stadium.refreshStadiumMatches();
		
		Match match = stadium.getMatches(playerTeam).get(1);
		
		ArrayList<Athlete> playerTeamList = match.getPlayerTeam().getTeamList();
		ArrayList<Athlete> oppositionTeamList = match.getOppositionTeam().getTeamList();

		for (int i = 0; i < 4; i ++) {
			ArrayList<Athlete> encounterAthletes = match.getEncounterAthletes();
			Athlete playerAthlete = encounterAthletes.get(0);
			assertTrue(playerTeamList.contains(playerAthlete));
			Athlete oppositionAthlete = encounterAthletes.get(1);
			assertTrue(oppositionTeamList.contains(oppositionAthlete));
						
				
			if (playerAthlete.getPosition().equals("Attacker")) {
				assertTrue(oppositionAthlete.getPosition().equals("Defender"));
				assertTrue(playerAthlete.getPosition().equals("Attacker"));
			} else {
				assertTrue(oppositionAthlete.getPosition().equals("Attacker"));
				assertTrue(playerAthlete.getPosition().equals("Defender"));
				}
			
			match.encounter(playerAthlete, oppositionAthlete);
			}
		finishedMatch = match;
		
	}
	

	
	/**
	 * Tests getting the encounter Athletes for a game with normal difficulty and from a generated medium or easy match
	 * Checks that encounter athletes are in opposing positions
	 */
	
	@Test
	public void testGetEncounterAthletesAndPlay() {
		Stadium stadium = new Stadium("Normal");
		stadium.setStadium(playerTeam, 2);
		stadium.refreshStadiumMatches();
		
		Match match = stadium.getMatches(playerTeam).get(1);
		
		ArrayList<Athlete> playerTeamList = match.getPlayerTeam().getTeamList();
		ArrayList<Athlete> oppositionTeamList = match.getOppositionTeam().getTeamList();

		for (int i = 0; i < 4; i ++) {
			ArrayList<Athlete> encounterAthletes = match.getEncounterAthletes();
			Athlete playerAthlete = encounterAthletes.get(0);
			assertTrue(playerTeamList.contains(playerAthlete));
			Athlete oppositionAthlete = encounterAthletes.get(1);
			assertTrue(oppositionTeamList.contains(oppositionAthlete));
						
				
			if (playerAthlete.getPosition().equals("Attacker")) {
				assertTrue(oppositionAthlete.getPosition().equals("Defender"));
				assertTrue(playerAthlete.getPosition().equals("Attacker"));
			} else {
				assertTrue(oppositionAthlete.getPosition().equals("Attacker"));
				assertTrue(playerAthlete.getPosition().equals("Defender"));
				}
			
			match.encounter(playerAthlete, oppositionAthlete);
			}
		
		}
	
	/**
	 * Test encounter
	 */
	@Test
	public void testEncounter() {
		Stadium stadium = new Stadium("Normal");
		stadium.setStadium(playerTeam, 2);
		stadium.refreshStadiumMatches();
		
		Match match = stadium.getMatches(playerTeam).get(1);
		
	    Athlete attacker = new Athlete(80, 60, 100, "Name1", "Attacker", 0, 0, "");
	    Athlete defender = new Athlete(70, 90, 100, "Name2", "Defender", 0, 0, "");
	    
	    String result = match.encounter(attacker, defender);
	    
	    // returns string with both athlete's names
	    assertTrue(result.contains("Name1"));
	    assertTrue(result.contains("Name2"));
	    
	    // defender wins and score does not change
	    assertEquals(match.getOppositionScore(), 0);
	    assertEquals(match.getPlayerScore(), 0);

	}

	
	
	/**
	 * Test to check if playerTeam within match is correctly refreshed when get match is called
	 */
	@Test
	public void testGetMatches() throws LimitException {
	    Stadium stadium = new Stadium("Normal");
	    stadium.setStadium(playerTeam, 1);
	    stadium.refreshStadiumMatches();

	    Match matchBefore = stadium.getMatches(playerTeam).get(1);

	    // Update the teamlist
	    Athlete athlete = playerTeam.getTeamList().get(0);
	    playerTeam.removeAthlete(athlete);
	    Athlete newAthlete = new Athlete();
	    playerTeam.addToTeam(newAthlete, null);

	    Match matchAfter = stadium.getMatches(playerTeam).get(1);

	    assertEquals(matchBefore.getPlayerTeam(), matchAfter.getPlayerTeam());
	}
	
	/**
	 * Test for increasing the team stats which is used after the encounter has been played
	 */
	@Test
	public void increaseTeamStats() {
		Team team = finishedMatch.getPlayerTeam();
		int previousTeamScore = team.getTeamScore();
		
		finishedMatch.increaseTeamStats();
		Team updatedTeam = finishedMatch.getPlayerTeam();
		int updatedTeamScore = updatedTeam.getTeamScore();
	
		assertTrue((previousTeamScore < updatedTeamScore));
	}
	
	/**
	 * Tests all players injured method
	 */
	@Test
	public void testAllPlayersInjured() {
		// should not be true
		assertFalse(finishedMatch.allPlayersInjured());
		
		ArrayList<Athlete> teamList = finishedMatch.getPlayerTeam().getTeamList();
		for (Athlete athlete : teamList) {
			athlete.decreaseStamina(100);
		}
		// now they are all injured, should be true
		assertTrue(finishedMatch.allPlayersInjured());
	}
	


		 
		 
	

}
