package economysimulation.classes.managers.exception;

/**
 *
 * @author Max Carter
 */
public class NonExistentTaxException extends Exception {

    public NonExistentTaxException() {
        super("The specified tax does not exist.");
    }
    
    public NonExistentTaxException(int tax) {
        super("There is no tax with id: " + tax);
    }
    
}
