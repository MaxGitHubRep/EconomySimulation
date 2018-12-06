package economysimulation.classes.managers.popup.hint.hints;

import economysimulation.classes.managers.popup.hint.Urgency;

/**
 *
 * @author Max Carter
 */
public class ErrorInvalidConnection extends Hint {

    @Override
    public String getTitle() {
        return "You are not online!";
    }

    @Override
    public String getDescription() {
        return "Your connection isn't valid. Try making sure you are connected to the internet.";
    }

    @Override
    public int getUrgency() {
        return Urgency.HIGH;
    }
  
}
