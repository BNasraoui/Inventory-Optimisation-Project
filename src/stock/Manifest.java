package stock;

import java.util.ArrayList;
import delivery.OrdinaryTruck;
import delivery.RefridgeratedTruck;
import delivery.Truck;

/**
 * A collection of trucks used to transport new items to the store inventory
 * 
 */
public class Manifest {
	private Stock orderedItems;
	private int refridgeratedTruckCount;
	private int ordinaryTruckCount;
	private ArrayList<Truck> truckList;

	/**
	 * 
	 * @param orderedItems
	 *            - Items to be added to the store inventory
	 */
	public Manifest(Stock orderedItems) {
		this.orderedItems = orderedItems;
		truckList = new ArrayList<Truck>();
		refridgeratedTruckCount = 0;
		ordinaryTruckCount = 0;
	}

	/**
	 * Determines the number of Refrigerated trucks and ordinary trucks to most
	 * efficiently transport the ordered items
	 */
	public void optimise() {
		int totalItems = orderedItems.getTotalQuantity();
		int tempCount = TempControlCount();
		refridgeratedTruckCount = ((int) Math.ceil(tempCount / 800.0));
		ordinaryTruckCount = ((int) Math.ceil((totalItems - (800.0 * refridgeratedTruckCount)) / 1000.0));
	}

	/**
	 * 
	 * @return - the minimum temperature of the ordered items
	 */
	public double GetMinTemp() {
		double minTemp = 20;
		for (Item item : orderedItems.getStock().keySet()) {
			if (item.getTemperature() < minTemp) {
				minTemp = item.getTemperature();
			}
		}

		return minTemp;
	}

	/**
	 * 
	 * @return - the total number of temperature controlled items in the manifest
	 */
	public int TempControlCount() {

		int count = 0;

		for (Item i : orderedItems.getStock().keySet()) {
			if (i.IsTemperatureControlled()) {
				count += orderedItems.getStock().get(i);
			}

		}

		return count;
	}

	/**
	 * fills the trucks for items to be transported
	 */
	public void fillTrucks() {

		optimise();
		int tempControlled = TempControlCount();
		Stock tempStock = orderedItems;

		for (int i = 0; i < refridgeratedTruckCount; i++) { // Fill Refrigerated Trucks

			double minTemp = GetMinTemp();
			int cargoCount = 0;
			int maxCargo = 800;
			
			Stock cargo = new Stock();

			for(Item item : tempStock.getStock().keySet()) {

				if (item.IsTemperatureControlled() || tempControlled == 0){ // Prioritising Cold Items for cold storage

					int itemQuantity = tempStock.getQuanity(item);
					int allowableQuantity = maxCargo - cargoCount;
					int addedQuantity;

					if (allowableQuantity > itemQuantity) {
						addedQuantity = itemQuantity;
					} else {
						addedQuantity = allowableQuantity;
					}

					cargo.add(item, allowableQuantity);

					tempStock.remove(item);
					cargoCount += addedQuantity;
					tempControlled -= addedQuantity;
				}

			}

			RefridgeratedTruck newFridgeTruck = new RefridgeratedTruck(cargoCount, cargo, minTemp);
			truckList.add(newFridgeTruck);
		}

		for (int i = 0; i < ordinaryTruckCount; i++) {

			int cargoCount = 0;
			int maxCargo = 1000;
			
			Stock cargo = new Stock();

			for(Item item : tempStock.getStock().keySet()) {

				if (tempStock.getQuanity(item) != 0) {

					int itemQuantity = tempStock.getQuanity(item);
					int allowableQuantity = maxCargo - cargoCount;
					int addedQuantity;

					if (allowableQuantity > itemQuantity) {
						addedQuantity = itemQuantity;
					} else {
						addedQuantity = allowableQuantity;
					}

					cargo.add(item, allowableQuantity);
					
					tempStock.remove(item);
					cargoCount += addedQuantity;
				}
			}

			OrdinaryTruck newOrdinaryTruck = new OrdinaryTruck(cargoCount, cargo);
			truckList.add(newOrdinaryTruck);
		}

	}

	/**
	 * 
	 * @return - the list of trucks associated with the manifest
	 */
	public ArrayList<Truck> getTruckList() {
		return truckList;
	}

	/**
	 * 
	 * @return - the total cost of all items and their transport to the associated
	 *         store
	 */
	public double getTotalCost() {
		double totalCost = 0.0;

		for (Truck truck : truckList) {
			totalCost += truck.cost();


			for (Item item : truck.getCargo().getStock().keySet()) {
				totalCost += item.getCost() * truck.getCargo().getQuanity(item);
			}
		}
		return totalCost;
	}
}
