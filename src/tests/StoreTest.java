package tests;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Random;

import org.junit.jupiter.api.Test;

import stock.Store;

class StoreTest {
	private static Random random = new Random();
	
	Store testStore = new Store();

	@Test
	void testGetCapital() {
		assertEquals(100000.00, testStore.getCapital());
	}

	@Test
	void testSetCapital() {
		double capital = random.nextDouble();
		testStore.setCapital(capital);
		
		assertEquals(capital, testStore.getCapital());
	}

}
 