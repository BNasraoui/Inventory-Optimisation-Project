package delivery;

/**
 * an ordinary truck used to transport items to the store
 * 
 * @author Benjamin Nasraoui
 */
import stock.Stock;

public class OrdinaryTruck implements Truck {
	public final static double MAX_CARGO = 1000;
	
	private double cost;
	private int cargoCount;
	private Stock cargo;
	
	/**
	 * 
	 * @param cargoCount
	 * 			- total number of items in the truck
	 * @param cargo
	 * 			- the stock associated with this truck
	 */			
	public OrdinaryTruck(int cargoCount, Stock cargo) {
		
		if(cargoCount >= 0 && cargoCount <= MAX_CARGO) { 
			this.cargoCount = cargoCount;
		}
		else {this.cargoCount = 1;}
		this.cargo = cargo;
	}
	/**
	 * @return 
	 * 		- the total cost associated with transporting the items within this truck
	 */
	@Override 
	public double cost() {
		
		cost = 750 + (0.25 * cargoCount);
		
		return cost;
	}
	
	/**
	 * @return
	 * 		- the total number of items in the truck
	 */
	@Override
	public int capacity() {
		return cargoCount;
	}
	
	/**
	 * @return
	 * 		- returns whether this truck is temperature controlled or not
	 */
	@Override
	public boolean isTempControlled() {
		return false;
	}

	/**
	 * @return
	 * 		- the stock associated with this truck
	 */
	@Override
	public Stock getCargo() {
		return cargo;
	}
}
 