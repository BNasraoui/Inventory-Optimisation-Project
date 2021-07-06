package exceptions;

/**
 * Thrown to indicate an error with the stock class or calculation.
 * 
 * @author Alexander Parker
 *
 */
public class StockException extends Exception {

	/**
	 * Constructs a StockException with no detailed message
	 */
	public StockException() {
		super();
	}

	/**
	 * @param message
	 *            - the detailed message
	 */
	public StockException(String message) {
		super(message);
	}
}