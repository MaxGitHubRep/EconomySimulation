package economysimulation.classes.managers.popup.hint.hints;

import economysimulation.classes.managers.popup.hint.Urgency;

/**
 *
 * @author Max Carter
 */
public class HintBankRateTooHigh extends Hint {

    public int cooldown = super.COOLDOWN_TIME;

    @Override
    public String getTitle() {
        return "New Interest Rates are Dangerously High.";
    }

    @Override
    public String getDescription() {
        return "High interest rates means consumers are more likely to save than spend money.";
    }

    @Override
    public int getUrgency() {
        return Urgency.LOW;
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
