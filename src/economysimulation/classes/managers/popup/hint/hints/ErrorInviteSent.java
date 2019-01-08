package economysimulation.classes.managers.popup.hint.hints;

import economysimulation.classes.managers.popup.hint.Urgency;

/**
 *
 * @author Max Carter
 */
public class ErrorInviteSent extends Hint {

    @Override
    public String getTitle() {
        return "Party invite has already been sent!";
    }

    @Override
    public String getDescription() {
        return "The user you invited has not replied to your invite yet.";
    }

    @Override
    public int getUrgency() {
        return Urgency.MEDIUM;
    }
  
}
