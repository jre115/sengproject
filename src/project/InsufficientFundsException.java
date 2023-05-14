package project;

public class InsufficientFundsException extends Exception{
    public InsufficientFundsException() {
        super("Not enough funds to make purchase");

}
}

