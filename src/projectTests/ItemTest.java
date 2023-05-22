package projectTests;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import project.Item;

class ItemTest {

	@Test
	void testItemConstructor() {
		Item item = new Item("Fireflyer Charm", 5, 5, 1000, 200, "A charm that enhances your broom's attack and defense capabilities with its enchanting glow");
		assertEquals("Fireflyer Charm", item.getName());
		assertEquals(5, item.getOffence());
		assertEquals(5, item.getDefence());
		assertEquals(1000, item.getContractPrice());
		assertEquals(200, item.getSellBackPrice());
		assertEquals("A charm that enhances your broom's attack and defense capabilities with its enchanting glow", item.getDescription());
	}

	
}