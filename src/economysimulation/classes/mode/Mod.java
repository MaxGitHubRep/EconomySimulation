package economysimulation.classes.mode;

/**
 *
 * @author Max Carter
 */
public enum Mod {
    UNSELECTED(0),
    SINGLE_PLAYER(1),
    MULTI_PLAYER(2);
    
    private int index = -1;
    
    Mod(int index) {
        this.index = index;
    }
    
    public int getIndex() {
        return index;
    }
    
}
