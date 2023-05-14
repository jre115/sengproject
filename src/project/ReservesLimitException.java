package project;

public class ReservesLimitException extends Exception{
    public ReservesLimitException() {
        super("Maximum of 5 reserves allowed");

}
}
