package delivery;

import stock.Stock;

public interface Truck {
	
	public double cost();
	
	public int capacity();
	
	public boolean isTempControlled();
	
	public Stock getCargo();

}
 