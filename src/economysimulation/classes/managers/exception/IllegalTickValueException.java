package economysimulation.classes.managers.exception;

/**
 *
 * @author Max Carter
 */
public class IllegalTickValueException extends Exception {
    
    /**
    * Sends exception when a tick value is illegal.
    */
    public IllegalTickValueException() {
        super();
    }
    
    /**
    * Sends exception when a tick value is illegal.
    * 
    * @param tick the illegal delay value between number increments.
    */
    public IllegalTickValueException(int tick) {
        super("Invalid tick delay: " + tick + ". Must be greater than 1");
    }
    
}
