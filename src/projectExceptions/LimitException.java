package projectExceptions;

/**
 * Limit exception extends the exception class.
 * A maximum of 5 reserves are allowed a 4 team players
 * @author jordan redfern
 *
 */
public class LimitException extends Exception {
	
	/**
	 * The constructor for the LimitException exception
	 */
	public LimitException() {
        super("A maximum  of 5 reserves are allowed");
    }
	
	/**
	 * The constructor for the LimitException exception
	 * @param message the message for the exception
	 */
	public LimitException(String message) {
		super(message);
	}

}
