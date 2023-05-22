package projectTests;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import project.Athlete;
import project.Item;
import project.Market;

class MarketTest {
    private Market market;

    @BeforeEach
    void setUp() {
        market = new Market();
        market.refreshMarket(1); // Initialize market with current week 1
    }

    @Test
    /**
     * Test if item shop generates the correct number of items "4"
     */
    void testItemShop() {
        market.getShopItems();
        assertEquals(4, market.getShopItems().size());
    }

    @Test
    /**
     * Test player shop and ensure it returns a valid number of athletes between 3-5
     */
    void testPlayerShop() {
        
        assertTrue(market.getShopAthletes().size() >= 3 && market.getShopAthletes().size() <= 5);
    }

   
    

    @Test
    /**
     * Test the purchaseAthlete method by checking if the athlete is removed from the shop
     */
    void testPurchaseAthlete() {
    	Athlete athlete = market.getShopAthletes().get(0);
        market.purchaseAthlete(athlete);
        assertFalse(market.getShopAthletes().contains(athlete));
    }

    @Test
    /**
     * Test the purchaseItem method by checking if the item is removed from the shop
     */
    void testPurchaseItem() {
        
        Item item = market.getShopItems().get(0);
        market.purchaseItem(item);
        assertFalse(market.getShopItems().contains(item));
    }
}
