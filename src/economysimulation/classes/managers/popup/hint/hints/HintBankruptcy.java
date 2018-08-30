package economysimulation.classes.managers.popup.hint.hints;

import economysimulation.classes.managers.popup.hint.Urgency;

/**
 *
 * @author Max Carter
 */
public class HintBankruptcy extends Hint {

    public int cooldown = super.COOLDOWN_TIME;
    public String affected;
    
    public HintBankruptcy(String affected) {
        this.affected = affected;
    }
    
    @Override
    public String getTitle() {
        return this.affected + " are Bankrupt!";
    }

    @Override
    public String getDescription() {
        return "Reduce taxes to allow an increase in the flow of money.";
    }

    @Override
    public int getUrgency() {
        return Urgency.HIGH;
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
