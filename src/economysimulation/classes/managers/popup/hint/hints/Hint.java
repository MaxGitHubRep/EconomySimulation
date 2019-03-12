package economysimulation.classes.managers.popup.hint.hints;

/**
 * @author Max Carter
 */
public abstract class Hint {
    
    private final int COOLDOWN_TIME = 25;
    private volatile boolean cool = false;
    
    public abstract String getTitle();
    public abstract String getDescription();
    public abstract int getUrgency();
    
    public int getCooldownTime() {
        return COOLDOWN_TIME;
    }
    
    public void setCooldownState(boolean state) {
        cool = state;
    }
    
    public boolean isCooldownEnabled() {
        return cool;
    }
    
}
