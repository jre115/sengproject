package projectExceptions;

/**
 * Insufficient funds exception extends the exception. 
 * This exception is thrown when the player does not have enough balance to make a purchase.
 * @author jordan redfern
 *
 */
public class InsufficientFundsException extends Exception{
    public InsufficientFundsException() {
        super("Not enough funds to make purchase");

}
}

