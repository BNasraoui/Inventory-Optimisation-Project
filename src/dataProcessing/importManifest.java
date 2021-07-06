package dataProcessing;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import stock.Item;
import stock.Store;

public class importManifest {
	private static String itemName = "";
	private static String itemQuantity = "";

	public static void read(File file) throws IOException {
		FileReader reader = null;
		BufferedReader bufferedReader = null;

		try {
			reader = new FileReader(file);
			bufferedReader = new BufferedReader(reader);
			String line = "";

			// While there are lines of text in the CSV file
			while ((line = bufferedReader.readLine()) != null) {

				// Separate out the values
				if (!line.startsWith(">")) {
					String[] item = line.split(" , ");
					String[] values = item[0].toString().split(",");
					itemName = values[0];
					itemQuantity = values[1];
				}

				for (Item item : ItemProperties.ITEM_PROPERTIES) { // Add these items to the current inventory of the
																	// store
					if (item.getName().equals(itemName)) { // HAHAHA STRING1 == STRING1 = FALSE HAVE TO USE
															// STRING1.EQUALS(STRING1)?????s
						Store.getInstance().getCurrentInventory().add(item, Integer.parseInt(itemQuantity));
					}
				}

				// Update the capital
				double currentCapital = Store.getInstance().getCapital();
				double costThisManifest = 0;
				for (Item item : ItemProperties.ITEM_PROPERTIES) {
					if (item.getName().equals(itemName)) {
						costThisManifest += item.getCost() * Double.parseDouble(itemQuantity);
					}
				}
				Store.getInstance().setCapital(currentCapital - costThisManifest);
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally { // Clean up
			if (reader != null) {
				bufferedReader.close();
			}
		}
	}
}
