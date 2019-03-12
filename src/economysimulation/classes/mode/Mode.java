package economysimulation.classes.mode;

/**
 * @author Max Carter
 */
public enum Mode {
    UNSELECTED(0),
    SINGLE_PLAYER(1),
    MULTI_PLAYER(2);
    
    //index of the mode
    private int index = -1;
    
    /**
     * Creates a new Mode.
     * @param index Index of the mode.
     */
    Mode(int index) {
        this.index = index;
    }
    
    /**
     * Gets the index of the mode.
     * @return Index of the mode.
     */
    public int getIndex() {
        return index;
    }
    
}
