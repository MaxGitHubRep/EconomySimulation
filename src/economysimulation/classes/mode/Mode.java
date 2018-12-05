package economysimulation.classes.mode;

/**
 *
 * @author Max Carter
 */
public enum Mode {
    UNSELECTED(0),
    SINGLE_PLAYER(1),
    MULTI_PLAYER(2);
    
    private int index = -1;
    
    Mode(int index) {
        this.index = index;
    }
    
    public int getIndex() {
        return index;
    }
    
}
