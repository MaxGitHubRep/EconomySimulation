package economysimulation.classes.managers.exception;

/**
 *
 * @author Max Carter
 */
public class InvalidTimeException extends Exception {
    
    /**
    * Sends exception when a time value is invalid.
    */
    public InvalidTimeException() {
        super();
    }
    
    /**
    * Sends exception when a time value is invalid.
    * 
    * @param tick the illegal delay value between number increments.
    */
    public InvalidTimeException(int tick) {
        super("Invalid time: " + tick + ". Must be a positive integer.");
    }
    
}
