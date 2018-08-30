package economysimulation.classes.managers.popup.hint.hints;

/**
 *
 * @author Max Carter
 */
public abstract class Hint {
    
    public final int COOLDOWN_TIME = 280;
    
    public abstract String getTitle();
    public abstract String getDescription();
    public abstract int getUrgency();
    public abstract int getCooldown();
    public abstract void resetCooldown();
    public abstract void reduceCooldown();
    public abstract boolean isOnCooldown();
    
}
