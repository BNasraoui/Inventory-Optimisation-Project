package stock;

public class Store {
	private static Store instance = null;
	private static double STARTING_CAPITOL = 100000;
	private double currentCapital;
	private Stock currentInventory;
	private static String NAME = "SuperMart";

	public Store() {
		this.currentCapital = STARTING_CAPITOL;
		currentInventory = new Stock();
	}

	public static Store getInstance() {
		if (instance == null) {
			instance = new Store();
		}
		return instance;
	}

	public double getCapital() {
		return currentCapital;
	}

	public void setCapital(double capital) {
		this.currentCapital = capital;
	}

	public Stock getCurrentInventory() {
		return this.currentInventory;

	}
}
 