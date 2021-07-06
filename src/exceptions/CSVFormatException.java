package exceptions;

/**
 * Thrown to indicate that a CSV file does not follow the format required for
 * this program
 * 
 * @author Alexander Parker
 *
 */
public class CSVFormatException extends Exception {

	/**
	 * Constructs a CSVFormatException with no detailed message
	 */
	public CSVFormatException() {
		super();
	}

	/**
	 * @param message
	 *            - the detailed message
	 */
	public CSVFormatException(String message) {
		super(message);
	}
}