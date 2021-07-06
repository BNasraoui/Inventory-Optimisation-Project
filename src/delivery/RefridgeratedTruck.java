package delivery;

import stock.Item;
import stock.Stock;

public class RefridgeratedTruck implements Truck {
	public static final double MAX_CARGO = 800;
	private double cost;
	private int cargoCount;
	private double temperature;
	private Stock cargo;

	/**
	 * 
	 * @param cargoCount
	 *            - total number of items in the truck
	 * @param cargo
	 *            - the stock associated with this truck
	 * @param temperature
	 *            - the current temperature of the truck
	 */
	public RefridgeratedTruck(int cargoCount, Stock cargo, double temperature) {
		
		if(cargoCount >= 0 && cargoCount <= MAX_CARGO) { 
			this.cargoCount = cargoCount;
		}
		else {this.cargoCount = 1;}
		
		this.cargo = cargo;
		this.temperature = temperature;
		cost = 0;
	}

	/**
	 * @return - the total cost associated with transporting the items within this
	 *         truck
	 */
	public double cost() {
		cost = 900 + (200 * Math.pow(0.7, temperature / 5));
		return cost;
	}

	/**
	 * @return - the total number of items in the truck
	 */
	public int capacity() {
		return cargoCount;
	}

	/**
	 * @return - returns whether this truck is temperature controlled or not
	 */
	public boolean isTempControlled() {
		return true;
	}

	/**
	 * @return - sets the temperature of the truck
	 */
	public void setTemp() {
		for (Item i : cargo.getStock().keySet()) {
			if (i.getTemperature() < temperature) {
				temperature = i.getTemperature();
			}
		}
	}

	/**
	 * @return - the stock associated with this truck
	 */
	@Override
	public Stock getCargo() {
		return cargo;
	}

	/**
	 * @return - the temperature associated with this truck
	 */
	public double getTemperature() {
		return this.temperature;
	}

	/**
	 * @param temperature
	 *            - The temperature of the truck
	 */
	public void setTemperature(double temperature) {
		this.temperature = temperature;
	} 
}