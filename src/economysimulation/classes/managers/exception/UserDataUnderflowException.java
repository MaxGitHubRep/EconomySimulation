package economysimulation.classes.managers.exception;

/**
 *
 * @author Max Carter
 */
public class UserDataUnderflowException extends Exception {

    public UserDataUnderflowException() {
        super("There are no records of any user data on the database.");
    }
    
}
