package projectTests;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;

import projectExceptions.LimitException;

import org.junit.jupiter.api.Test;
import projectExceptions.LimitException;
import project.Athlete;
import project.RandomEvents;
import project.Team;

class RandomTest {
	private RandomEvents randomEvents;
    private Team team;
    
    @BeforeEach
    void setUp() {
        randomEvents = new RandomEvents();
        team = new Team();
    }

    @Test
    /**
     * checks if empty
     */
    void testIncreaseRandomPlayerStat_EmptyTeam() {
        Athlete increasedPlayer = randomEvents.increaseRandomPlayerStat(team);
        assertNull(increasedPlayer); 
    }

    @Test
    /**
     * checks if player a player stat is increased
     */
    void testIncreaseRandomPlayerStat_TeamWithPlayers() {
        // Add players to the team
        Athlete player1 = new Athlete();
        
        try {
			team.addToTeam(player1, null);
		} catch (LimitException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        int player1OffensiveBefore = player1.getOffensive();
        int player1DefensiveBefore = player1.getDefensive();
        int player1StaminaBefore = player1.getStamina();
       
		
       
		

        // Increase a random player's stat
        
        Athlete increasedPlayer = randomEvents.increaseRandomPlayerStat(team);

        assertNotNull(increasedPlayer); 
        
        //checks if increased stat player is in eithe team
        assertTrue(team.getTeamList().contains(increasedPlayer) || team.getReservesList().contains(increasedPlayer)); 

      
        
        /**
         * checks if stat increase return true other wise false
         * then logical or statement so one has to be true for it to pass test
         */
        if (increasedPlayer == player1) {
        	
            boolean offensiveIncreased = (player1.getOffensive() != player1OffensiveBefore);
            boolean defensiveIncreased = (player1.getDefensive() != player1DefensiveBefore);
            boolean staminaIncreased = (player1.getStamina() != player1StaminaBefore);
            
            assertTrue(offensiveIncreased || defensiveIncreased || staminaIncreased);
        }}


	

	    @Test
	    /**
	     * creates an empty team
	     * test1, checks if correct athlete is added, checks if athlete is added to right team
	     * test2, full main roster checks if added to reserves 
	     * test3, fills up both reserves and main lists, checks that athlete is supposed to = null, checks if athlete is in reserves or main team
	     * 
	     */
	    void testAthleteJoins() {
	        
	        Team team = new Team();

	        
	        RandomEvents randomEvents = new RandomEvents();

	        // Test when the team's roster is not full
	        Athlete addedAthlete = randomEvents.athleteJoins(team);
	        
	        assertNotNull(addedAthlete); 
	        assertTrue(team.getTeamList().contains(addedAthlete)); 
	        assertFalse(team.getReservesList().contains(addedAthlete)); 

	        // Fill up the team's roster
	        for (int i = 0; i < 4; i++) {
	            Athlete athlete = new Athlete();
	            try {
					team.addToTeam(athlete, null);
				} catch (LimitException e) {
					// TODO Auto-generated catch block
					
				}
	        }

	        
	        Athlete addedAthlete2 = randomEvents.athleteJoins(team);
	        
	        assertFalse(team.getTeamList().contains(addedAthlete2)); 
	        assertTrue(team.getReservesList().contains(addedAthlete2)); 
	        
	        // Fill up the reserves list
	        for (int i = 0; i < 5; i++) {
	            Athlete athlete = new Athlete();
	            try {
					team.addToReserves(athlete);
				} catch (LimitException e) {
					
					
				}
	        }

	        // Test when the reserves list is full
	        Athlete addedAthlete3 = randomEvents.athleteJoins(team);
	        assertNull(addedAthlete3); 
	        assertFalse(team.getReservesList().contains(addedAthlete3)); 
	        assertFalse(team.getTeamList().contains(addedAthlete3)); 
	    }
	    
	    
	    
	    
	    

}
	    

