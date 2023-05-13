package project;

public class NoReserveAthletesException extends Exception {
    public NoReserveAthletesException() {
        super("There are no reserve athletes to swap with.");
    }

}
