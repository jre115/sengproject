package ProjectExceptions;

public class InventoryFullException extends Exception {
    public InventoryFullException() {
        super("Inventory is full. Cannot add item.");
    }
}