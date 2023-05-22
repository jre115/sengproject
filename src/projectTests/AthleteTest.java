package projectTests;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import project.Athlete;
import project.Item;
import projectExceptions.NameException;

/**
 * Tests for athlete class
 * @author jordan redfern
 *
 */
class AthleteTest {
	
	/**
	 * Tests set athlete name with exceptions
	 */
	@Test
	public void testSetAthleteName() {
		Athlete athlete = new Athlete();
		boolean thrown = false;
		
		// set name to valid input
		try {
            boolean nameChanged = athlete.setName("Test Name");
            assertTrue(nameChanged);
            assertEquals("Test Name", athlete.getName());
        } catch (NameException e) {
           thrown = true;
        }
		
		assertFalse(thrown);
		
		// set name to the same input
		 try {
	            boolean nameChanged = athlete.setName("Test Name");
	            assertFalse(nameChanged);
	            assertEquals("Test Name", athlete.getName());
	        } catch (NameException e) {
	            thrown = true;
	        }
		 assertFalse(thrown);
			
	
		 // try too short
		 
		 try {
	            athlete.setName("");
	        } catch (NameException e) {
	            assertEquals("Player nickname must be between 1 - 20 characters long", e.getMessage());
	            assertEquals("Test Name", athlete.getName()); // Make sure the name was not changed
	        }
		 
		 try {
	            athlete.setName("Test Name!");
	        } catch (NameException e) {
	            assertEquals("Nickname must not include any special characters", e.getMessage());
	            assertEquals("Test Name", athlete.getName()); // Make sure the name was not changed
	        }
	}
	
	/**
	 * Tests athlete change position and set position to alternate position
	 */
	@Test
	public void testAthleteAlternatePosition() {
		Athlete athlete = new Athlete();
		athlete.setPosition("Attacker");
        assertEquals("Defender", athlete.getAlternatePosition());
        
        athlete.setPosition("Reserve");
        assertEquals("Reserve", athlete.getAlternatePosition());
        
        athlete.setPosition("Defender");
        athlete.changeAthletePosition();
        assertEquals("Attacker", athlete.getPosition());
	}
	
	/**
	 * Tests use item method
	 */
	@Test
	public void testUseItem() {
		Athlete athlete = new Athlete(50, 50, 100, "Tester", "Attacker", 5000, 1000, "");
		 Item item = new Item("Power Boost Tester", 2, 3, 100, 50, "Increases offensive and defensive stats");
		 athlete.useItem(item);
	     assertEquals(52, athlete.getOffensive());
	     assertEquals(53, athlete.getDefensive());
		
	}
	
	/**
	 * Test athlete equality
	 */
	@Test
	public void testEquality() {
		Athlete athlete1 = new Athlete(10, 5, 100, "John", "Attacker", 200, 100, "Powerful attacker");
        Athlete athlete2 = new Athlete(10, 5, 100, "John", "Attacker", 200, 100, "Powerful attacker");
        Athlete athlete3 = new Athlete(8, 4, 100, "Jane", "Defender", 200, 100, "Strong defender");

        assertTrue(athlete1.equals(athlete2)); 
        assertFalse(athlete1.equals(athlete3)); 
	}
	
	/**
	 * Test increase athlete statistics, check stamina is capped at 100
	 */
	@Test
	public void testIncreaseStatistics() {
		Athlete athlete = new Athlete(10, 5, 90, "Tester", "Attacker", 200, 100, "");
		athlete.increaseStatistics(1.5);
		
		assertEquals(15, athlete.getOffensive());
		assertEquals(7, athlete.getDefensive());
		assertEquals(100, athlete.getStamina());
	}
	
	
	/**
	 * Tests for decrease stamina and whether the athlete is injured and restoring stamina
	 */
	@Test
	public void testStaminaMethods() {
	    Athlete athlete = new Athlete(10, 10, 100, "Tester", "Attacker", 100, 50, "");
	    athlete.decreaseStamina(50);
	    
	    assertEquals(50, athlete.getStamina());
	    assertFalse(athlete.isInjured());
	    
	    athlete.decreaseStamina(60);
	    assertEquals(0, athlete.getStamina());
	    assertTrue(athlete.isInjured());
	    
	    athlete.restoreStamina();
	    assertFalse(athlete.isInjured());
	    assertEquals(100, athlete.getStamina());
	}
	
	/**
	 * Tests train athlete
	 */
	@Test
	public void testTrainAthlete() {
	    Athlete athlete = new Athlete(10, 10, 100, "Tester", "Attacker", 100, 50, "");
	    
	    // Perform training
	    athlete.trainAthlete();
	    
	    // Check if offensive and defensive statistics increased within the valid range
	    int offensiveStat = athlete.getOffensive();
	    int defensiveStat = athlete.getDefensive();
	    
	    assertTrue(offensiveStat >= 15);
	    assertTrue(offensiveStat <= 25);
	    
	    assertTrue(defensiveStat >= 15);
	    assertTrue(defensiveStat <= 25);
	}
	
	/**
	 * Testing isReserve()
	 */
	@Test
	public void testIsReserve() {
	    Athlete athlete1 = new Athlete();
	    athlete1.setPosition("Reserve");
	    Athlete athlete2 = new Athlete(10, 10, 100, "Tester Name", "Attacker", 100, 50, "Description");

	    assertTrue(athlete1.isReserve());
	    assertFalse(athlete2.isReserve());
	    
	}
	
	/**
	 * Tests set athlete scores based on defensive/offensive
	 */
	@Test
	public void testSetAthleteScoresBasedOn() {
	    Athlete athlete = new Athlete();
	    
	    // These are scores generally generated from a team defensive/offensive score
	    int defensiveScore = 90;
	    int offensiveScore = 110;
	    
	    athlete.setAthleteStatisticsBasedOnDefensive(defensiveScore);
	    athlete.setAthleteStatisticsBasedOnOffensive(offensiveScore);
	    
	    assertTrue(athlete.getDefensive() >= 40);
	    assertTrue(athlete.getDefensive() <= 50);
	    
	    assertTrue(athlete.getOffensive() >= 50);
	    assertTrue(athlete.getOffensive() <= 60);

	}
}
