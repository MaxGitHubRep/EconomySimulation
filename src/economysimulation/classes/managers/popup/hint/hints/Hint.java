package economysimulation.classes.managers.popup.hint.hints;

/**
 * @author Max Carter
 */
public abstract class Hint {
    
    /** Default cooldown time. */
    private final int COOLDOWN_TIME = 25;
    
    /** Whether the cooldown is active or not. */
    private volatile boolean cool = false;
    
    /**
     * Gets the title of the hint.
     * @return Title of hint.
     */
    public abstract String getTitle();
    
    /**
     * Get the description of the hint.
     * @return Description of hint.
     */
    public abstract String getDescription();
    
    /**
     * Gets the urgency of the hint.
     * @return Urgency of hint.
     */
    public abstract int getUrgency();
    
    /**
     * Gets the cooldown time of the hint.
     * @return Cooldown time of the hint.
     */
    public int getCooldownTime() {
        return COOLDOWN_TIME;
    }
    
    /**
     * Enables or disables the cooldown.
     * @param state New state of cooldown.
     */
    public void setCooldownState(boolean state) {
        cool = state;
    }
    
    /**
     * Whether or not the hint is on cooldown or not.
     * @return Return true if hint is on cooldown.
     */
    public boolean isCooldownEnabled() {
        return cool;
    }
    
}
