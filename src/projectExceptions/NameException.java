package projectExceptions;

/**
 * Name exception extends illegal arguments exception.
 * It is used in the game when a name does not meet specific requirements
 * @author jordan redfern
 *
 */
public class NameException extends IllegalArgumentException{
	
	/**
	 * The constructor for the NameException exception
	 */
	public NameException() {
		super();
	}
	
	/**
	 * The constructor for the NameException exception
	 * @param message the message for the exception
	 */
	public NameException(String message) {
		super(message);
	}
	

}
