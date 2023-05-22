package projectExceptions;

/**
 * Illegal team exception extends the exception class
 * It is used in the game for when the team is not full or does not have enough attackers/defenders to play a game
 * @author jordan redfern
 *
 */
public class IllegalTeamException extends Exception {
    public IllegalTeamException(String message) {
        super(message);
    }
}
