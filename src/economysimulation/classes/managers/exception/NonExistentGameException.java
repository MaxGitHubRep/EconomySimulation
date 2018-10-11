package economysimulation.classes.managers.exception;

/**
 *
 * @author Max Carter
 */
public class NonExistentGameException extends Exception {
    
    public NonExistentGameException(int id) {
        super("There is no game saved with the ID: " + id);
    }
    
    public NonExistentGameException() {
        super("There are no games saved.");
    }
    
}
