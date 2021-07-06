package stock;

/**
 * The item class represents a conceptual real-world item from a grocery store
 * and relevant information such as the name, cost etc.
 * 
 * @author Alexander Parker
 *
 */
public class Item {
	private String name;
	private double cost;
	private double price;
	private int reorderPoint;
	private int reorderAmount;
	private Boolean isTemperatureControlled = false;
	public static final double DEFAULT_TEMPERATURE = 20;
	private double temperature = DEFAULT_TEMPERATURE;

	/**
	 * Constructor
	 * 
	 * @param name
	 *            - The name of the item
	 * @param manufacturingCost
	 *            - The cost to manufacture the item in dollars
	 * @param price
	 *            - The sell price of the item in dollars
	 * @param reorderPoint
	 *            - The minimum number of this item in stock before more is ordered
	 * @param reorderAmount
	 *            - How much of this item is re-ordered once the reorder point is
	 *            reached
	 * @param temperature
	 *            - The minimum required temperature for perishable items. A default
	 *            temperature is used where temperature is not provided
	 */
	public Item(String name, double manufacturingCost, double price, int reorderPoint, int reorderAmount,
			double temperature) {
		this.setName(name);
		this.setManufacturingCost(manufacturingCost);
		this.setPrice(price);
		this.setReorderPoint(reorderPoint);
		this.setReorderAmount(reorderAmount);

		if (temperature != DEFAULT_TEMPERATURE) {
			this.temperature = temperature;
			this.isTemperatureControlled = true;
		}
	}

	/**
	 * @return the name of the item
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name
	 *            - the name of the item
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the cost of the item
	 */
	public double getCost() {
		return cost;
	}

	/**
	 * @param cost
	 *            - the cost of the item
	 */
	public void setManufacturingCost(double cost) {
		this.cost = cost;
	}

	/**
	 * @return the price of the item
	 */
	public double getPrice() {
		return price;
	}

	/**
	 * @param price
	 *            - the price of the item
	 */
	public void setPrice(double price) {
		this.price = price;
	}

	/**
	 * @return the reorder point of the item
	 */
	public int getReorderPoint() {
		return this.reorderPoint;
	}

	/**
	 * @param reorderPoint
	 *            - the reorder point of the item
	 */
	public void setReorderPoint(int reorderPoint) {
		this.reorderPoint = reorderPoint;
	}

	/**
	 * @return the reorder amount of the item
	 */
	public int getReorderAmount() {
		return reorderAmount;
	}

	/**
	 * @param reorderAmount
	 *            - the reorder amount of the item
	 */
	public void setReorderAmount(int reorderAmount) {
		this.reorderAmount = reorderAmount;
	}

	/**
	 * @return true if the item is temperature controlled
	 */
	public Boolean IsTemperatureControlled() {
		return isTemperatureControlled;
	}

	/**
	 * @return the temperature of the item
	 */
	public double getTemperature() {
		return temperature;
	}

	/**
	 * @param temperature
	 *            - the temperature of the item
	 */
	public void setTemperature(double temperature) {
		this.temperature = temperature;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	public String toString() {
		return "Item: " + this.getName() + ", Cost: " + this.getCost() + ", Price: " + this.price + ", Reorder Point: "
				+ this.getReorderPoint() + ", Reorder Amount: " + this.getReorderAmount() + ", Temperature: "
				+ this.getTemperature();
	}
} 
