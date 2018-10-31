package economysimulation.classes.managers.popup.hint.hints;

import economysimulation.classes.managers.popup.hint.Urgency;

/**
 *
 * @author Max Carter
 */
public class ErrorServerSlotFull extends Hint {

    public int cooldown = super.COOLDOWN_TIME;

    @Override
    public String getTitle() {
        return "Server Slot is Full!";
    }

    @Override
    public String getDescription() {
        return "Try joining a different server.";
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
