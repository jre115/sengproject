package projectTests;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;

import project.Athlete;
import project.Item;
import project.Market;

class MarketTest {

	@Test
	void testItemShop() {
		Market market = new Market();
		ArrayList<Item> selectedItems = market.itemShop();
		assertEquals(4, selectedItems.size());
	}

	@Test
	void testPlayerShop() {
		Market market = new Market();
		ArrayList<Athlete> shopAthletes = market.playerShop(1);
		assertTrue(shopAthletes.size() >= 3 && shopAthletes.size() <= 5);
	}

	@Test
	void testRefreshMarket() {
		Market market = new Market();
		int currentWeek = 1;
		market.refreshMarket(currentWeek);
		ArrayList<Item> currentItems = market.getShopItems();
		ArrayList<Athlete> currentAthletes = market.getShopAthletes();
		assertEquals(4, currentItems.size());
		assertTrue(currentAthletes.size() >= 3 && currentAthletes.size() <= 5);
	}

	@Test
	void testPurchaseAthlete() {
		Market market = new Market();
		int currentWeek = 1;
		market.refreshMarket(currentWeek);
		ArrayList<Athlete> shopAthletes = market.getShopAthletes();
		Athlete athlete = shopAthletes.get(0);
		market.purchaseAthlete(athlete);
		assertFalse(shopAthletes.contains(athlete));
	}

	@Test
	void testPurchaseItem() {
		Market market = new Market();
		int currentWeek = 1;
		market.refreshMarket(currentWeek);
		ArrayList<Item> shopItems = market.getShopItems();
		Item item = shopItems.get(0);
		market.purchaseItem(item);
		assertFalse(shopItems.contains(item));
	}
	
	/**
	 * Tests the increase of athletes' stats as the weeks increase in the Market class.
	 */
	
}
