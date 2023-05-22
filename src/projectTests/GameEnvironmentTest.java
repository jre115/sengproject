package projectTests;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.Map;

import project.Athlete;
import project.GameEnvironment;
import project.Item;
import projectExceptions.InsufficientFundsException;
import projectExceptions.InventoryFullException;
import projectExceptions.LimitException;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * Tests related to the game environment
 * @author jorda
 *
 */
class GameEnvironmentTest {
	
	GameEnvironment testGame;
	
	/**
	 * Creates setup game to be used for testing
	 */
	@BeforeEach
	public void prepGame() {
		GameEnvironment game = new GameEnvironment();
        game.setGameDifficulty("Normal");
        assertEquals("Normal", game.getGameDifficulty());
        
        game.setTeamName("Team Name");
        assertEquals("Team Name", game.getTeamName());
        
        game.setSeasonLength(5);
        assertEquals(5, game.getSeasonLength());
        
        ArrayList<Athlete> athletes = game.getInitialAthletes();
        int defenderCount = 0;
        int attackerCount = 0;

        for (Athlete athlete : athletes) {
            if (defenderCount < 2) {
                game.purchaseInitialAthlete(athlete, "Defender");
                defenderCount++;
            } else {
                game.purchaseInitialAthlete(athlete, "Attacker");
                attackerCount++;
            }
        }

        game.startGame();
        testGame = game;
           
        
	}
	/**
	 * Tests initialising the game and start game method
	 */
	 @Test
    public void testStartGame() {
		GameEnvironment game = new GameEnvironment();
        game.setGameDifficulty("Normal");
        assertEquals("Normal", game.getGameDifficulty());
        
        game.setTeamName("Team Name");
        assertEquals("Team Name", game.getTeamName());
        
        game.setSeasonLength(5);
        assertEquals(5, game.getSeasonLength());
        
        ArrayList<Athlete> athletes = game.getInitialAthletes();
        int defenderCount = 0;

        for (Athlete athlete : athletes) {
            if (defenderCount < 2) {
                game.purchaseInitialAthlete(athlete, "Defender");
                defenderCount++;
            } else {
                game.purchaseInitialAthlete(athlete, "Attacker");
            }
        }
        

        game.startGame();
        
        assertEquals(1, game.getCurrentWeek());
        assertEquals(10000, game.getPlayerMoney());
	 }
	 
	 /**
	  * Tests game progression and ending the game
	  */
	 @Test
	 public void testGameProgression() {
	     GameEnvironment game = testGame;

	     // Play first 4 weeks
	     for (int i = 1; i < 4; i++) {
	         game.increaseWeek();
	         assertEquals(i + 1, game.getCurrentWeek());
	         assertTrue(game.checkGameContinues());
	         assertFalse(game.isFinalWeek());
	     }

	     // Final week
	     game.increaseWeek();
	     assertEquals(5, game.getCurrentWeek());
	     // Has a full team so game shouldnt have ended early
	     assertTrue(game.checkGameContinues());
	     assertTrue(game.isFinalWeek());

	     // Verify that the game has ended and get the game results
	     Map<String, Object> results = game.endGame();

	     assertEquals("Team Name", results.get("name"));
	     assertEquals(5, results.get("weeks"));
	     assertEquals(0, results.get("points"));
	     assertEquals(10000, results.get("money"));

	 }
	 
	 /**
	  * Tests purchasing an item and inventory full
	  */
	 @Test
	 public void testPurchaseItem() {
	     GameEnvironment game = testGame;

	     // Create an item with a contract price of 5000
	     Item item = new Item("Charm1", 5, 5, 5000, 200, "check");

	     // Purchase the item
	     try {
	         game.purchaseItem(item);
	     } catch (Exception e) {
	     }

	     // Check player's money and inventory
	     assertEquals(5000, game.getPlayerMoney());
	     assertTrue(game.getInventory().contains(item));

	     // Create an item with a contract price of 6000
	     Item item2 = new Item("Charm2", 5, 5, 6000, 200, "check");

	     // Purchase the item2
	     try {
	         game.purchaseItem(item2);
	     } catch (Exception e) {
	         assertEquals("Not enough funds to make purchase", e.getMessage());
	     }

	     // Purchase items until inventory is full
	     for (int i = 0; i < 3; i++) {
	         Item item3 = new Item("Charm" + (i + 3), 5, 5, 10, 200, "check");
	         try {
	             game.purchaseItem(item3);
	         } catch (Exception e) {
	         }
	     }

	     // Attempt to purchase another item when inventory is full
	     Item item4 = new Item("Charm5", 5, 5, 10, 200, "check");
	     try {
	         game.purchaseItem(item4);
	     } catch (Exception e) {
	         assertEquals("Inventory is full. Cannot add item.", e.getMessage());
	     }
	 }


	 @Test
	 public void testPurchaseAthlete() {
	     GameEnvironment game = testGame;

	     // Create an athlete with a contract price of 5000
	     Athlete athlete = new Athlete(80, 70, 90, "Tester", "Attacker", 5000, 500, "");


	     // Purchase the athlete
	     try {
	         game.purchaseAthlete(athlete);
	     } catch (Exception e) {
	     }

	     // Check player's money and team
	     assertEquals(5000, game.getPlayerMoney());
	     try {
			game.addAthleteAsReserve(athlete);
		} catch (LimitException e) {
			e.printStackTrace();
		}
	     assertTrue(game.getReservesList().contains(athlete));
	     
	     // Should not be able to have the funds to purchase athlete
	     Athlete athlete2 = new Athlete(80, 70, 90, "Tester2", "Attacker", 10000, 500, "");
	     
	     try {
			game.purchaseAthlete(athlete2);
		} catch (InsufficientFundsException e) {
	         assertEquals("Not enough funds to make purchase", e.getMessage());
		} catch (LimitException e) {
			e.printStackTrace();
		}
	     
	    // Fill up reserves team so that team is full
	    for (int i = 0 ; i < 4; i ++) {
		     Athlete athlete3 = new Athlete(80, 70, 90, "Tester2", "Attacker", 10, 500, "");
		     try {
					game.purchaseAthlete(athlete3);
				     try {
							game.addAthleteAsReserve(athlete);
						} catch (LimitException e) {
						}
				} catch (InsufficientFundsException e) {
				} catch (LimitException e) {
				}
	    }
	    
	     Athlete athlete3 = new Athlete(80, 70, 90, "Tester2", "Attacker", 10000, 500, "");
	     try {
			game.purchaseAthlete(athlete2);
		} catch (InsufficientFundsException e) {
		} catch (LimitException e) {
	         assertEquals("Team is full!", e.getMessage());
		}

	    

	 }



	 

	

}
