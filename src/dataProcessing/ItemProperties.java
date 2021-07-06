package dataProcessing;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import stock.Item;
import stock.Store;

/**
 * The ItemProperties class implements functionality to load properties for
 * items from a CSV file and stores them in a static variable to be referenced
 * elsewhere in the program. Custom items can also be added here and saved to
 * the item_properties.csv file
 * 
 * @author Alexander Parker
 */
public class ItemProperties {
	public static final ArrayList<Item> ITEM_PROPERTIES = new ArrayList<Item>();

	/**
	 * This method takes a CSV file, separates the entries and values into a format
	 * used by this program
	 * 
	 * @param file
	 *            - absolute path of the file
	 * @throws IOException
	 *             if the CSV can not be loaded or is in the wrong format
	 */
	public static void read(String file) throws IOException {
		FileReader reader = null;
		BufferedReader bufferedReader = null;

		try {
			reader = new FileReader(file);
			bufferedReader = new BufferedReader(reader);
			String line = "";

			// While there are lines of text in the CSV file
			while ((line = bufferedReader.readLine()) != null) {

				// Separate entries by comma
				String[] item = line.split(" , ");
				String[] values = item[0].toString().split(",");

				// Add Items to master ITEM_PROPERTIES ArrayList
				if (values.length == 5) { // Items with no temperature use the default temperature
					String name = values[0];
					double manufacturingCost = Double.parseDouble(values[1]);
					double price = Double.parseDouble(values[2]);
					int reorderPoint = Integer.parseInt(values[3]);
					int reorderAmount = Integer.parseInt(values[4]);
					double temperature = Item.DEFAULT_TEMPERATURE;
					Item currentItem = new Item(name, manufacturingCost, price, reorderPoint, reorderAmount,
							temperature);
					ITEM_PROPERTIES.add(currentItem);

				} else if (values.length == 6) { // If a temperature is provided pass it into the Item constructor below
					String name = values[0];
					double manufacturingCost = Double.parseDouble(values[1]);
					double price = Double.parseDouble(values[2]);
					int reorderPoint = Integer.parseInt(values[3]);
					int reorderAmount = Integer.parseInt(values[4]);
					double temperature = Double.parseDouble(values[5]);
					Item currentItem = new Item(name, manufacturingCost, price, reorderPoint, reorderAmount,
							temperature);
					ITEM_PROPERTIES.add(currentItem);
				}
			}
			
			for (Item item : ITEM_PROPERTIES) {
				Store.getInstance().getCurrentInventory().add(item, 0);
			}

		} catch (IOException ioe) {
			throw ioe;

		} finally { // Clean up
			if (reader != null) {
				bufferedReader.close();
			}
		}
	}

	/**
	 * @param item
	 *            - item to be added to the ArrayList
	 */
	public void addItem(Item item) {
		ITEM_PROPERTIES.add(item);
	}

	/**
	 * @param item
	 *            - item to be deleted from the ArrayList matched by name
	 */
	public void deleteItem(Item item) {
		for (int i = 0; i < ITEM_PROPERTIES.size(); i++) {
			if (ITEM_PROPERTIES.get(i).getName() == item.getName()) {
				ITEM_PROPERTIES.remove(i);
				break;
			}
		}
	}

	/**
	 * @return the ArrayList with item properties
	 */
	public ArrayList<Item> getItemProperties() {
		return ITEM_PROPERTIES;
	}
}
 