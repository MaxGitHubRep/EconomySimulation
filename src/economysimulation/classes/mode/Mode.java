package economysimulation.classes.mode;

/**
 *
 * @author Max Carter
 */
public class Mode {
    
    public static final int
            UNSELECTED = 0,
            SOLO = 1,
            COOP = 2;
    
    private static int mode = UNSELECTED;
    
    /**
     * Sets the mode of the simulation.
     * @param newMode New mode to be applied.
     */
    public static void setMode(int newMode) {
        mode = newMode;
    }
    
    /**
     * The mode that the simulation is operating in.
     * @return The current mode.
     */
    public static int getMode() {
        return mode;
    }
    
}
