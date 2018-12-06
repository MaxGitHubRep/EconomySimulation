package economysimulation.classes.managers.exception;

/**
 *
 * @author Max Carter
 */
public class InvalidServerSlot extends Exception {
    
    /**
    * Sends exception when a server slot provided is invalid.
    */
    public InvalidServerSlot() {
        super();
    }
    
    /**
    * Sends exception when a server slot provided is invalid.
    * 
    * @param slot Invalid slot index.
    */
    public InvalidServerSlot(int slot) {
        super("Invalid slot: " + slot + " is not a valid slot index.");
    }
    
}
