package economysimulation.classes.mode;

/**
 *
 * @author Max Carter
 */
public class ModeManager {

    private Mod selectedMode = Mod.UNSELECTED;
    
    public ModeManager(Mod mode) {
        this.selectedMode = mode;
    }
    
    public Mod getMode() {
        return selectedMode;
    }
    
    public void setMode(Mod mode) {
        selectedMode = mode;
    }
    
    public void setMode(int id) {
        switch (id) {
            case 1:
                selectedMode = Mod.SINGLE_PLAYER;
                break;
            case 2:
                selectedMode = Mod.MULTI_PLAYER;
                break;
            case 0:
            default:    
                selectedMode = Mod.UNSELECTED;
                break;
        }
    }
    
    public boolean isMode(Mod mode) {
        return mode == selectedMode;
    }
    
    public boolean isSelected() {
        return selectedMode != Mod.UNSELECTED;
    }
    
}
