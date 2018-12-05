package economysimulation.classes.mode;

/**
 *
 * @author Max Carter
 */
public class ModeManager {

    private Mode selectedMode = Mode.UNSELECTED;
    
    public ModeManager(Mode mode) {
        this.selectedMode = mode;
    }
    
    public Mode getMode() {
        return selectedMode;
    }
    
    public void setMode(Mode mode) {
        selectedMode = mode;
    }
    
    public void setMode(int id) {
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
    
    public boolean isMode(Mode mode) {
        return mode == selectedMode;
    }
    
    public boolean isSelected() {
        return selectedMode != Mode.UNSELECTED;
    }
    
}
