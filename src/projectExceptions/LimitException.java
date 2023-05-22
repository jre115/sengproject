package projectExceptions;

/**
 * Limit exception extends the exception class.
 * A maximum of 5 reserves are allowed a 4 team players
 * @author jordan redfern
 *
 */
public class LimitException extends Exception {
	public LimitException() {
        super("A maximum  of 5 reserves are allowed");
    }
	
	public LimitException(String message) {
		super(message);
	}

}
