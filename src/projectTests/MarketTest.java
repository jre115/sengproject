package projectTests;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;

import project.Athlete;
import project.Item;
import project.Market;

class MarketTest {
	
	@Test
	/**
	 * test if item shop generates the correct number of items "4"
	 */
	void testItemShop() {
		Market market = new Market();
		ArrayList<Item> selectedItems = market.itemShop();
		assertEquals(4, selectedItems.size());
	}

	@Test
	/**
	 * tests player shop fuctionality and that it return a valid number of athletes between 3-5
	 */
	void testPlayerShop() {
		Market market = new Market();
		ArrayList<Athlete> shopAthletes = market.playerShop(1);
		assertTrue(shopAthletes.size() >= 3 && shopAthletes.size() <= 5);
	}

	@Test
	/**
	 * checks if current shop items and athletes are refreshing correctly
	 */
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
	/**
	 * tests the purchase player method by checking if athlete is removed from the shop
	 */
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
	/**
	 *  tests the purchase item method by checking if item is removed from the shop
	 */
	 
	void testPurchaseItem() {
		Market market = new Market();
		int currentWeek = 1;
		market.refreshMarket(currentWeek);
		ArrayList<Item> shopItems = market.getShopItems();
		Item item = shopItems.get(0);
		market.purchaseItem(item);
		assertFalse(shopItems.contains(item));
	}
	
	
}
