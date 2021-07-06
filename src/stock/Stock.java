package stock;

import java.util.HashMap;

/**
 * The stock class is used to manage collections of items and tracks their
 * quantity
 * 
 * @author Benjamin Nasraoui
 * 
 */
public class Stock {

	private HashMap<Item, Integer> stock;

	/**
	 * 
	 * @param items
	 *            - An ArrayList of the items associated with this stock object
	 * @param quantity
	 *            - an integer array containing the number of items stored within
	 *            this stock object
	 */
	public Stock() {
		stock = new HashMap<Item, Integer>();
	}

	/**
	 * 
	 * @param newItems
	 *            - Items to be added to the stock object.
	 * @param addedQuantity
	 *            - the number instances of each item being added
	 */
	public void add(Item item, int quantity) {
		stock.put(item, quantity);
	}

	public void generateOrderLog() {
		for (Item item : Store.getInstance().getCurrentInventory().getStock().keySet()) {
			if (Store.getInstance().getCurrentInventory().getStock().get(item) < item.getReorderPoint()) {
				stock.put(item, item.getReorderAmount());
			}
		}
	}

	/**
	 * 
	 * @param removedItems
	 *            - Items to be removed
	 * @param removedQuantity
	 *            - the number of instances of items to be removed
	 */
	public void remove(Item item) {
		stock.remove(item);
	}

	/**
	 * 
	 * @param item
	 *            - item whose quantity is to be checked
	 * @return - an integer representing the the total number of instances of the
	 *         specified Item in the associated stock object
	 */
	public int getQuanity(Item item) {
		return stock.get(item);
	}

	/**
	 * 
	 * @return - the total number of items
	 */
	public int getTotalQuantity() {
		int totalQuantity = 0;
		for (int quantity : stock.values()) {
			totalQuantity += quantity;
		}

		return totalQuantity;
	}

	public HashMap<Item, Integer> getStock() {
		return stock;
	}
}
