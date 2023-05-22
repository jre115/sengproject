package projectTests;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import project.Athlete;
import project.Match;
import project.Stadium;
import project.Team;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

class MatchTests {
	
	static Team playerTeam;
	
	@BeforeAll
	public static void createTeam() {
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
		
		Match match = stadium.getMatches().get(1);
		
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
	
	@Test
	public void multipleTests() {
		for (int i = 0; i < 100; i ++) {
			createTeam();
			testGetEncounterAthletesAndPlay();
		}
	}
		 
		 
	

}
