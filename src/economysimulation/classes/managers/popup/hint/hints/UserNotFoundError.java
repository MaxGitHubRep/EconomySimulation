package economysimulation.classes.managers.popup.hint.hints;

import economysimulation.classes.managers.popup.hint.Urgency;

/**
 *
 * @author Max Carter
 */
public class UserNotFoundError extends Hint {

    @Override
    public String getTitle() {
        return "This user does not exist anymore.";
    }

    @Override
    public String getDescription() {
        return "This user may have logged off and cannot respond to your invitiation.";
    }

    @Override
    public int getUrgency() {
        return Urgency.HIGH;
    }
  
}
