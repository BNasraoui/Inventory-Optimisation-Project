package exceptions;

/**
 * Thrown to indicate an error with the delivery class or an error with a
 * calculation related to the truck cargo or manifest
 * 
 * @author Alexander Parker
 *
 */
public class DeliveryException extends Exception {

	/**
	 * Constructs a DeliveryException with no detailed message
	 */
	public DeliveryException() {
		super();
	}

	/**
	 * @param message
	 *            - the detailed message
	 */
	public DeliveryException(String message) {
		super(message);
	}
}