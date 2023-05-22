package projectTests;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import project.Team;
import project.Athlete;
import projectExceptions.LimitException;
import projectExceptions.NameException;
import projectExceptions.NoReserveAthletesException;

/**
 * Testing for the team class.
 * @author jordan redfern
 *
 */
class TeamTest {

	/**
	 * Tests set team name with exceptions for ASCII special characters and character limits
	 */
	@Test
	public void testSetTeamName() {
		Team team = new Team();
		Boolean thrown;
		
		// set name to valid input
		try {
            team.setTeamName("Team name");
            assertEquals("Team name", team.getTeamName());
        } catch (NameException e) {
           thrown = true;
        }
	
		 // try too short
		 
		 try {
	            team.setTeamName("t");
	        } catch (NameException e) {
	            assertEquals("Team name must be between 3 - 15 characters long", e.getMessage());
	            assertEquals("Team name", team.getTeamName()); // Make sure the name was not changed
	        }
		 
		 try {
	            team.setTeamName("Team name!");
	        } catch (NameException e) {
	            assertEquals("Team name must not include any special characters", e.getMessage());
	            assertEquals("Team name", team.getTeamName()); // Make sure the name was not changed
	        }
	}
	
	/**
	 * Tests remove athlete from team
	 */
	@Test
	public void testRemoveAthlete() {
	    Athlete athlete1 = new Athlete();
	    Athlete athlete2 = new Athlete();
	    Athlete athlete3 = new Athlete();
	    
	    Team team = new Team();
	    try {
			team.addToTeam(athlete1, null);
		    team.addToTeam(athlete2, null);
			team.addToReserves(athlete3);

		} catch (LimitException e) {
		}

	    // Remove athlete2 from the team list
	    team.removeAthlete(athlete2);
	    
	    assertFalse(team.getTeamList().contains(athlete2));
	    assertTrue(team.getTeamList().contains(athlete1));
	    assertFalse(team.getTeamList().contains(athlete3));
	    assertTrue(team.getReservesList().contains(athlete3));
	    
	    // Remove athlete3 from the reserves list
	    team.removeAthlete(athlete3);
	    
	    assertFalse(team.getTeamList().contains(athlete3));
	    assertTrue(team.getTeamList().contains(athlete1));
	    assertFalse(team.getReservesList().contains(athlete3));
	    
	    assertEquals(1, team.getTeamList().size());
	    assertEquals(0, team.getReservesList().size());

	}
	
	/**
	 * Tests add to reserves method
	 */
	@Test
	public void testAddToReserves() {
	    Team team = new Team();
	    
	    for (int i = 1; i <= 5; i++) {
	        Athlete athlete = new Athlete();
	        try {
				team.addToReserves(athlete);
			} catch (LimitException e) {
			}
	        assertTrue(team.getReservesList().contains(athlete));
	        assertFalse(team.getTeamList().contains(athlete));
	    }
	    
	    assertEquals(5, team.getReservesList().size());
	    
	    // Attempt to add the sixth athlete
	    Athlete athlete6 = new Athlete();
	    try {
	        team.addToReserves(athlete6);
	    } catch (LimitException e) {
	        assertEquals("Your reserve team is full!", e.getMessage());
	    }
	    
	    assertFalse(team.getReservesList().contains(athlete6));
	}
	
	/**
	 * Tests the add to team method
	 */
	@Test
	public void testAddToTeam() {
	    Team team = new Team();
	    
	    for (int i = 0; i < 4; i++) {
	        Athlete athlete = new Athlete();
	        try {
				team.addToTeam(athlete, null);
			} catch (LimitException e) {
				e.printStackTrace();
			}
	        assertTrue(team.getTeamList().contains(athlete));
	        assertFalse(team.getReservesList().contains(athlete));
	    }
	    
	    assertEquals(4, team.getTeamList().size());
	    
	    // Attempt to add the sixth athlete
	    Athlete athlete6 = new Athlete();
	    try {
	        team.addToTeam(athlete6, null);
	    } catch (LimitException e) {
	        assertEquals("Your team is full", e.getMessage());
	    }
	    
	    assertFalse(team.getTeamList().contains(athlete6));
	    
	    // Now for adding a reserve athlete to the team
	    Team team2 = new Team();
	    
	    Athlete reserveAthlete = new Athlete();
	    try {
	    	team2.addToReserves(reserveAthlete);
		} catch (LimitException e) {
			e.printStackTrace();
		}
	    
	    assertTrue(team2.getReservesList().contains(reserveAthlete));
	    try {
	    	team2.addToTeam(reserveAthlete, "Defender");
		} catch (LimitException e) {
			e.printStackTrace();
		}
	    // Check that it has been removed from the reserves list and added to the team list
	    assertFalse(team2.getReservesList().contains(reserveAthlete));
	    assertTrue(team2.getTeamList().contains(reserveAthlete));
	    // Check that position equals defender
	    assertEquals("Defender", reserveAthlete.getPosition());

	}
	
	/**
	 * Tests swap athletes method for one athlete in the team and the other in the reserves
	 */
	@Test
	public void testSwapAthletes() throws LimitException {
	    Team team = new Team();
	    
	    Athlete athlete1 = new Athlete();
	    Athlete athlete2 = new Athlete();
	    
	    // Add the athletes to the team and reserves
	    team.addToTeam(athlete1, "Attacker");
	    team.addToReserves(athlete2);
	    assertEquals("Attacker", athlete1.getPosition());
	    assertEquals("Reserve", athlete2.getPosition());
	    
	    // Now swap the athletes
	    team.swapAthletes(athlete1, athlete2);
	    
	    assertEquals("Reserve", athlete1.getPosition());
	    assertEquals("Attacker", athlete2.getPosition());
	    assertTrue(team.getTeamList().contains(athlete2));
	    assertTrue(team.getReservesList().contains(athlete1));
	    
	    // And swap again
	    team.swapAthletes(athlete1, athlete2);
	    
	    assertEquals("Attacker", athlete1.getPosition());
	    assertEquals("Reserve", athlete2.getPosition());
	    assertTrue(team.getTeamList().contains(athlete1));
	    assertTrue(team.getReservesList().contains(athlete2));
	    
	    // Check that swapping the same athlete does not change the lists
	    team.swapAthletes(athlete1, athlete1);
	    assertEquals("Attacker", athlete1.getPosition());
	    assertEquals("Reserve", athlete2.getPosition());
	    assertTrue(team.getTeamList().contains(athlete1));
	    assertTrue(team.getReservesList().contains(athlete2));
	}
	
	/**
	 * Tests check swappable method for if a team athlete can be swapped with a reserve athlete
	 */
	@Test
	public void testCheckSwappable() throws LimitException{
	    Team team = new Team();
	    
	    try {
	    	team.checkSwappable();
	    } catch (NoReserveAthletesException e) {
	        assertEquals("There are no reserve athletes to swap with.", e.getMessage());
	    }
	    
	    Athlete athlete1 = new Athlete();
	    Athlete athlete2 = new Athlete();
	    Athlete athlete3 = new Athlete();
	    
	    team.addToTeam(athlete1, null);
	    team.addToTeam(athlete2, null);
	    team.addToReserves(athlete3);

	    try {
	    	Boolean result = team.checkSwappable();
	    	assertTrue(result);
	    } catch (NoReserveAthletesException e) {
	    }
	}
	
	/**
	 * Tests add athlete and swap method
	 */
	@Test
	public void testAddAthleteAndSwap() {
	    Team team = new Team();

	    Athlete addedAthlete = new Athlete();
	    Athlete swappedAthlete = new Athlete();
	    swappedAthlete.setPosition("Defender");

	    team.getTeamList().add(swappedAthlete);
	    team.addAthleteAndSwap(addedAthlete, swappedAthlete);

	    // Check if positions are swapped correctly
	    assertEquals("Reserve", swappedAthlete.getPosition());
	    assertEquals("Defender", addedAthlete.getPosition());

	    // Check if athletes are in the correct lists
	    assertTrue(team.getReservesList().contains(swappedAthlete));
	    assertTrue(team.getTeamList().contains(addedAthlete));
	}
	
	/**
	 * Tests methods that calculate the scores for the team
	 * Tests get team score method, team defensive score and team offensive score.
	 */
	@Test
	public void testTeamScores() {
	    Team team = new Team();

	    // Create athletes to test
	    Athlete attacker1 = new Athlete(50, 60, 80, "Attacker1", "Attacker", 0, 0, "");
	    Athlete attacker2 = new Athlete(50, 60, 85, "Attacker2", "Attacker", 0, 0, "");
	    Athlete defender1 = new Athlete(40, 60, 90, "Defender1", "Defender", 0, 0, "");
	    Athlete defender2 = new Athlete(40, 60, 95, "Defender2", "Defender", 0, 0, "");

	    // Add athletes to the team
	    team.getTeamList().add(attacker1);
	    team.getTeamList().add(attacker2);
	    team.getTeamList().add(defender1);
	    team.getTeamList().add(defender2);

	    // Calculate scores
	    int teamScore = team.getTeamScore();
	    int offensiveScore = team.getOffensiveScore();
	    int defensiveScore = team.getDefensiveScore();
	    
	    // Check results correct
	    assertEquals(420, teamScore);
	    assertEquals(100, offensiveScore);
	    assertEquals(120, defensiveScore);
	}
	
	/**
	 * Tests the check team ready for a match method
	 */
	@Test
	public void testCheckTeamReady() {
	    Team team = new Team();

	    // Create athletes with different positions
	    Athlete attacker1 = new Athlete(50, 60, 80, "Attacker1", "Attacker", 0, 0, "Description1");
	    Athlete attacker2 = new Athlete(50, 60, 85, "Attacker2", "Attacker", 0, 0, "Description2");
	    Athlete defender1 = new Athlete(40, 60, 90, "Defender1", "Defender", 0, 0, "Description3");
	    Athlete defender2 = new Athlete(40, 60, 95, "Defender2", "Defender", 0, 0, "Description4");

	    // Add athletes to the team
	    team.getTeamList().add(attacker1);
	    team.getTeamList().add(attacker2);
	    team.getTeamList().add(defender1);
	    
	    // should not be ready yet
	    assertFalse(team.checkTeamReady());
	    
	    team.getTeamList().add(defender2);

	    // Now the team should be ready for a match
	    assertTrue(team.checkTeamReady());
	    
	    attacker1.setPosition("Defender");
	    // Now the team should not be ready - there are 3 defenders and 1 attacker
	    assertFalse(team.checkTeamReady());
	}
	
	/**
	 * Test for restoring the stamina of athletes and checking that they are no longer injured
	 */
	@Test
	public void testRestoreStamina() throws LimitException {
		Team team = new Team();
		
		Athlete athlete1 = new Athlete();
	    Athlete athlete2 = new Athlete(40, 60, 70, "Athlete2", "Defender", 0, 0, "");
	    Athlete reserve = new Athlete(60, 70, 30, "Reserve1", "Attacker", 0, 0, "");
	    
	    team.addToTeam(athlete1, null);
	    team.addToTeam(athlete2, null);
	    team.addToReserves(reserve);
	    
	    // make athlete 1 become injured
	    athlete1.decreaseStamina(110);
	    assertTrue(athlete1.isInjured());
	    
	    // Now restore stamina and check everything is as expected
	    team.restoreAthletes();
	    assertFalse(athlete1.isInjured());
	    assertEquals(athlete1.getStamina(), 100);
	    assertEquals(athlete2.getStamina(), 100);
	    assertEquals(reserve.getStamina(), 100);
	}
	
	/**
	 * Test for checking if the team is full
	 */
	@Test
	public void testTeamIsFull() throws LimitException{
		Team team = new Team();
		
		for (int i = 0; i < 4; i ++) {
			Athlete athlete = new Athlete();
			team.addToTeam(athlete, null);
		}
		
		assertFalse(team.isTeamFull());
		
		for (int i = 0; i < 5; i ++) {
			Athlete athlete = new Athlete();
			team.addToReserves(athlete);
		}
		
		assertTrue(team.isTeamFull());


	}



}
