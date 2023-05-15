package project;

public class LimitException extends Exception {
	public LimitException() {
        super("A maximum  of 5 reserves are allowed");
    }
	
	public LimitException(String message) {
		super(message);
	}

}
