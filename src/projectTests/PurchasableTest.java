package projectTests;

import static org.junit.jupiter.api.Assertions.*;
import project.Athlete;
import org.junit.jupiter.api.Test;

/**
 * Testing for the purchasable class.
 * @author jordan redfern
 *
 */
class PurchasableTest {

	/**
	 * Tests the get sell-back price method (for purchasable type athlete)
	 */
	@Test
	public void testGetSellBackPrice() {
	    Athlete athlete = new Athlete(60, 70, 100, "Name", "Attacker", 0, 0, "");

	    // Test with full stamina
	    int fullStaminaPrice = (60 + 70) * 10;
	    assertEquals(fullStaminaPrice, athlete.getSellBackPrice());

	    // Test with reduced stamina
	    athlete.decreaseStamina(50);
	    int reducedStaminaPrice = fullStaminaPrice - (50 * 10);
	    assertEquals(reducedStaminaPrice, athlete.getSellBackPrice());
	}
	
	/**
	 * Tests the get contract price method (for purchasable type athlete)
	 */
	@Test
	public void testGenerateContractPrice() {
	    Athlete athlete = new Athlete(60, 70, 80, "Name", "Defender", 0, 0, "");

	    int contractPrice = (60 + 70) * 50;
	    assertEquals(contractPrice, athlete.generateContractPrice());
	}

}
