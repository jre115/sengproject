package projectExceptions;

/**
 * No reserve athletes exception extends the exception class.
 * If there are no reserve athletes, and the user calls a method which requires a reserve athlete, this exception will be thrown.
 */
public class NoReserveAthletesException extends Exception {
	
	/**
	 * The constructor for the NoReserveAthletesException exception
	 */
    public NoReserveAthletesException() {
        super("There are no reserve athletes to swap with.");
    }

}
