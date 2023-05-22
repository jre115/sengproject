package projectExceptions;

/**
 * Name exception extends illegal arguments exception.
 * It is used in the game when a name does not meet specific requirements
 * @author jordan redfern
 *
 */
public class NameException extends IllegalArgumentException{
	
	public NameException() {
		super();
	}
	
	public NameException(String message) {
		super(message);
	}
	

}
