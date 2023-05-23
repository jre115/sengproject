package projectExceptions;

/**
 * Inventory full exception extends the exception class.
 *
 */
public class InventoryFullException extends Exception {
	
	/**
	 * The constructor for the InventoryFullException exception
	 */
    public InventoryFullException() {
        super("Inventory is full. Cannot add item.");
    }
}