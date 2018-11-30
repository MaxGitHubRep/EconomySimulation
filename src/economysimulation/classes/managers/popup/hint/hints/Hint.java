package economysimulation.classes.managers.popup.hint.hints;

/**
 *
 * @author Max Carter
 */
public abstract class Hint {
    
    public final int COOLDOWN_TIME = 280;
    public int cooldown = 280;
    
    public abstract String getTitle();
    public abstract String getDescription();
    public abstract int getUrgency();
    
    public int getCooldown() {
        return cooldown;
    }
    
    public void resetCooldown() {
        cooldown = COOLDOWN_TIME;
    }
    
    public void reduceCooldown() {
        cooldown--;
    }
    
    public boolean isOnCooldown() {
        return cooldown != COOLDOWN_TIME;
    }
    
}
