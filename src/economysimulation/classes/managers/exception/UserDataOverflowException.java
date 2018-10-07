package economysimulation.classes.managers.exception;

/**
 *
 * @author Max Carter
 */
public class UserDataOverflowException extends Exception {

    public UserDataOverflowException() {
        super("There are too many users stored on the database.");
    }
    
}
