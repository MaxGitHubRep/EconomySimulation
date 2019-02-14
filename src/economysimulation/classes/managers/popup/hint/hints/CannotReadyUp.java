package economysimulation.classes.managers.popup.hint.hints;

import economysimulation.classes.managers.popup.hint.Urgency;

/**
 * @author Max Carter
 */
public class CannotReadyUp extends Hint {

    @Override
    public String getTitle() {
        return "Cannot Ready Up!";
    }

    @Override
    public String getDescription() {
        return "You cannot Ready Up when you are not in a party.";
    }

    @Override
    public int getUrgency() {
        return Urgency.LOW;
    }
  
}
