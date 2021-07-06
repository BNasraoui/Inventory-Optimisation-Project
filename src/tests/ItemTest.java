package tests;

import static org.junit.jupiter.api.Assertions.*;
import java.util.Random;
import org.junit.jupiter.api.Test;

import stock.Item;

class ItemTest {
	
	// Testing Helper Variables
	private static Random random = new Random();
	
	private static String[] names = new String[] {
		"Cheese",
		"Bacon",
		"Milk",
		"Bread",
		"Soap",
	};
	
	private static String randName() {
		return names[random.nextInt(names.length)];
	}
	
	// Test 0 Creating Item Objects 
	Item testItem = new Item("Cheese", 0.35, 3.5, 2, 10, 10);
	
	
	// Test 1: Create a basic Item Object.
	@Test
	public void createItem() {
		testItem = null;
		
		String name = ("Cheese");
		double manufacturingCost = 0.35;
		double sellPrice = 3.5;
		int reorderPoint = 2;
		int reorderAmount = 10;
		double temperature = 10;
		
		testItem = new Item(name, manufacturingCost, sellPrice, reorderPoint, reorderAmount, temperature);	
	}
	
	// Test 2: Testing getName Method.
	@Test
	public void testGetName(){
		testItem = null;
		
		String name = randName();
		double manufacturingCost = 0.35;
		double sellPrice = 3.5;
		int reorderPoint = 2;
		int reorderAmount = 10; 
		double temperature = 10;
		
		testItem = new Item(name, manufacturingCost, sellPrice, reorderPoint, reorderAmount, temperature);	
		
		assertEquals(name, testItem.getName());
	}
	
	// Test 3: Testing setName Method.
	@Test
	public void testSetName() {
		String name = randName();
		testItem.setName(name);
		
		assert(name == testItem.getName());
	}
	
	// Test 4: Testing getManufacturingCost
	@Test 
	public void testGetManufacturingCost(){
		testItem = null;
		
		String name = randName();
		double manufacturingCost = random.nextDouble();
		double sellPrice = 3.5;
		int reorderPoint = 2;
		int reorderAmount = 10;
		double temperature = 10;
		
		testItem = new Item(name, manufacturingCost, sellPrice, reorderPoint, reorderAmount, temperature);
		
		assertEquals(manufacturingCost, testItem.getCost());
	}
	
	// Test 5: Testing setManufacturing Cost
	@Test
	public void testSetManufacturingCost() {
		
		double manufacturingCost = random.nextDouble();
		testItem.setManufacturingCost(manufacturingCost);
		
		assertEquals(manufacturingCost, testItem.getCost());
	}
	
	// Test 6: Testing getSellPrice
	@Test
	public void testGetSellPrice() {
		testItem = null;
		
		String name = ("Cheese");
		double manufacturingCost = 0.35;
		double sellPrice = random.nextDouble();
		int reorderPoint = 2;
		int reorderAmount = 10;
		double temperature = 10;
		
		testItem = new Item(name, manufacturingCost, sellPrice, reorderPoint, reorderAmount, temperature);
		
		assertEquals(sellPrice, testItem.getPrice());
	}
	
	//Test 7: Testing setSellPrice
	@Test
	public void testSetSellPrice() {
		double sellPrice = random.nextDouble();
		testItem.setPrice(sellPrice);
		
		assertEquals(sellPrice, testItem.getPrice());
	}
	
	// Test 8: Testing getReorderPoint
	@Test
	public void testGetReorderPoint() {
		testItem = null;
		
		String name = ("Cheese");
		double manufacturingCost = 0.35;
		double sellPrice = 3.5;
		int reorderPoint = random.nextInt();
		int reorderAmount = 10;
		double temperature = 10;
		
		testItem = new Item(name, manufacturingCost, sellPrice, reorderPoint, reorderAmount, temperature);
		
		assertEquals(reorderPoint, testItem.getReorderPoint());
	}
	// Test 9: Testing setReorderPoint
	@Test
	public void testsetReorderPoint() {
		
		int reorderPoint = random.nextInt();
		testItem.setReorderPoint(reorderPoint);
		
		assert(reorderPoint == testItem.getReorderPoint());
	}
	
	//Test 10: Testing getReorderAmount
	@Test
	public void testGetReorderAmount() {
		testItem = null;
		
		String name = ("Cheese");
		double manufacturingCost = 0.35;
		double sellPrice = 3.5;
		int reorderPoint = 2;
		int reorderAmount = random.nextInt(); 
		double temperature = 10;
		
		testItem = new Item(name, manufacturingCost, sellPrice, reorderPoint, reorderAmount, temperature);
		
		assertEquals(reorderAmount, testItem.getReorderAmount());
	}
	
	// Test 11: Testing setReorderAmouuunt
	@Test
	public void testSetReorderAmount() {
		int reorderAmount = random.nextInt();
		testItem.setReorderAmount(reorderAmount);
		assert(reorderAmount == testItem.getReorderAmount());
	}
	
	// Test 12: Testing getIsTemperatureControlled
	@Test
	public void getIsTemperatureControlled() {
		testItem = null;
		
		String name = ("Cheese");
		double manufacturingCost = 0.35;
		double sellPrice = 3.5;
		int reorderPoint = 2;
		int reorderAmount = 10;
		double temperature = random.nextDouble() * 20;
		
		testItem = new Item(name, manufacturingCost, sellPrice, reorderPoint, reorderAmount, temperature);
		
		assertEquals(testItem.IsTemperatureControlled(), temperature < 20);
	}
	
	// Test 13: Testing getTemperature
	@Test
	public void testGetTemperature() {
		String name = ("Cheese");
		double manufacturingCost = 0.35;
		double sellPrice = 3.5;
		int reorderPoint = 2;
		int reorderAmount = 10;
		double temperature = random.nextDouble();
		
		testItem = new Item(name, manufacturingCost, sellPrice, reorderPoint, reorderAmount, temperature);
		
		assertEquals(temperature, testItem.getTemperature());
	}
	
	// Test 14; Testing setTemperature
	public void testsetTemperature() {
		double temperature = random.nextDouble();
		
		testItem.setTemperature(temperature);
		assertEquals(temperature, testItem.getTemperature());
	}
}
 