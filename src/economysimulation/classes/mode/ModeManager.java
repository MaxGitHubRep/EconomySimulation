package economysimulation.classes.mode;

/**
 * @author Max Carter
 */
public class ModeManager {

    /** Currently selected mode. */
    private Mode selectedMode = Mode.UNSELECTED;
    
    /**
     * Creates a new mode manager.
     * @param mode Mode which is selected.
     */
    public ModeManager(Mode mode) {
        this.selectedMode = mode;
    }
    
    /**
     * Gets the currently selected mode.
     * @return Current mode.
     */
    public Mode getMode() {
        return selectedMode;
    }
    
    /**
     * Updates the mode.
     * @param mode New mode.
     */
    public void setMode(Mode mode) {
        selectedMode = mode;
    }
    
    /**
     * Updates the mode.
     * @param id Index of new mode.
     */
    public void setMode(int id) {
        //finds correct mode using index provided.
        switch (id) {
            case 1:
                selectedMode = Mode.SINGLE_PLAYER;
                break;
            case 2:
                selectedMode = Mode.MULTI_PLAYER;
                break;
            case 0:
            default:    
                selectedMode = Mode.UNSELECTED;
                break;
        }
    }
    
    /**
     * Determines if the selected mode is the same as the {@code mode} provided.
     * @param mode Mode to test.
     * @return Returns true if {@code mode} is equal to the selected mode.
     */
    public boolean isMode(Mode mode) {
        return mode == selectedMode;
    }
    
    /**
     * Determines if  a mode has been selected.
     * @return Returns true if a mode has been selected.
     */
    public boolean isSelected() {
        return selectedMode != Mode.UNSELECTED;
    }
    
}
