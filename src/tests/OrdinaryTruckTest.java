package tests;

import java.util.ArrayList;
import java.util.Arrays;
import org.junit.jupiter.api.Test;
import delivery.OrdinaryTruck;
import stock.Item;
import stock.Stock;

class OrdinaryTruckTest {
	double MAX_CARGO = OrdinaryTruck.MAX_CARGO;
	
	ArrayList<Item> stockList = new ArrayList<Item>(
			Arrays.asList(new Item[] { new Item("Cheese", 0.35, 3.5, 2, 10, 10) }));
	int[] intarray = { 1 };
	Stock cargo = new Stock();
	OrdinaryTruck ot = new OrdinaryTruck(500, cargo);

	// Test constructing a OrdinaryTruck object
	@Test
	void testConstructor() {
		this.cargo = new Stock();
		this.ot = new OrdinaryTruck(500, this.cargo);
	}

	// Test cost method functions correctly
	@Test
	void testCost() {
		assert ((500 * .25) + 750 == ot.cost());
	}

	// Test capacity method functions correctly
	@Test
	void testCapacity() {
		assert (500 == ot.capacity());
	}

	// Test an amount greater than maxCargo is handled
	@Test
	void testExceedMaxCargo() {
		ot = new OrdinaryTruck(1001, cargo);
		assert (ot.capacity() <= 1000);
	}

	// Test an amount of negative cargo is handled
	@Test
	void testNegativeCargo() {
		ot = new OrdinaryTruck(-1, cargo);
		assert (ot.capacity() >= 0D);
	}

	// Test if OrdinaryTruck is NOT temperature controlled
	@Test
	void testIsTemperatureControlled() {
		assert (!ot.isTempControlled());
	}

	// // Test if OrdinaryTruck throws StockException if given refrigerated goods
	// @Test
	// void testRefridgeratedGoods() {
	// assertThrows(StockException.class, () -> ot = new OrdinaryTruck(500,
	// chilledCargo));
	// }
}
 