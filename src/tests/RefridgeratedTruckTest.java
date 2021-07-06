package tests;

import java.util.ArrayList;
import java.util.Arrays;
import org.junit.jupiter.api.Test;
import delivery.RefridgeratedTruck;
import stock.Item;
import stock.Stock;

class RefridgeratedTruckTest {
	double MAX_CARGO = RefridgeratedTruck.MAX_CARGO;
	double temperature = -4;
	ArrayList<Item> stockList = new ArrayList<Item>(
			Arrays.asList(new Item[] { new Item("Cheese", 0.35, 3.5, 2, 10, -4) }));
	int[] intarray = { 1 };
	Stock cargo = new Stock();
	RefridgeratedTruck rt = new RefridgeratedTruck(500, cargo, temperature);

	// Test constructing a RefridgeratedTruck object
	@Test
	void testConstructor() {
		Stock cargo = new Stock();
		RefridgeratedTruck rt = new RefridgeratedTruck(500, cargo, -4);
	}

	// Test getting/setting temperature
	@Test
	void testSetAndGetTemperature() {
		rt.setTemperature(-20);
		assert (rt.getTemperature() == -20);
	}

	// Test cost method functions correctly
	@Test
	void testCost() {
		assert (900.0 + (200.0 * (Math.pow(0.7, rt.getTemperature() / 5.0))) == rt.cost());
	}

	// Test capacity method functions correctly
	@Test
	void testCapacity() {
		assert (500 == rt.capacity());
	}

	// Test an amount greater than maxCargo is handled
	@Test
	void testExceedMaxCargo() {
		rt = new RefridgeratedTruck(1001, cargo, -4);
		assert (rt.capacity() <= 800);
	}

	// Test an amount of negative cargo is handled
	@Test
	void testNegativeCargo() {
		rt = new RefridgeratedTruck(-1, cargo, -4);
		assert (rt.capacity() >= 0);
	}

	// Test if RefridgeratedTruck is temperature controlled
	@Test
	void testIsTemperatureControlled() {
		assert (rt.isTempControlled());
	}

	// Test if the temperature of the truck is set to the lowest temperature item in
	// the cargo
	@Test
	void testCorrectTemperature() {
		// TODO:
	}

}
 