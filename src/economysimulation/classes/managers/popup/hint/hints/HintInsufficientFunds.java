package economysimulation.classes.managers.popup.hint.hints;

import economysimulation.classes.managers.popup.hint.Urgency;

/**
 *
 * @author Max Carter
 */
public class HintInsufficientFunds extends Hint {

    public int cooldown = super.COOLDOWN_TIME;

    @Override
    public String getTitle() {
        return "Insufficient Funds for Desired Payment!";
    }

    @Override
    public String getDescription() {
        return "Increase taxes to obtain more money.";
    }

    @Override
    public int getUrgency() {
        return Urgency.MEDIUM;
    }

    @Override
    public int getCooldown() {
        return this.cooldown;
    }

    @Override
    public void resetCooldown() {
        this.cooldown = super.COOLDOWN_TIME;
    }

    @Override
    public void reduceCooldown() {
        this.cooldown--;
    }
  
    @Override
    public boolean isOnCooldown() {
        return this.cooldown != super.COOLDOWN_TIME;
    }  
    
}
