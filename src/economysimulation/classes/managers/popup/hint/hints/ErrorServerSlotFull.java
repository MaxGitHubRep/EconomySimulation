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

}
