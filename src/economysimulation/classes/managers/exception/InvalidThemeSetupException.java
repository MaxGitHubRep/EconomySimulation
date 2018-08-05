package economysimulation.classes.managers.exception;

/**
 *
 * @author Max Carter
 */
public class InvalidThemeSetupException extends Exception {
    
    /**
    * Sends exception when the number of theme names
    * does not match the number of color arrays.
    */
    public InvalidThemeSetupException() {
        super();
    }
    
    /**
    * Sends exception when the number of theme names
    * does not match the number of color arrays.
    * 
    * @param themeNames  the amount of theme names present.
    * @param themeColors the amount of color arrays present.
    */
    public InvalidThemeSetupException(int themeNames, int themeColors) {
        super("Theme names present: " + themeNames + ", theme colors present: " + themeColors);
    }
    
}
