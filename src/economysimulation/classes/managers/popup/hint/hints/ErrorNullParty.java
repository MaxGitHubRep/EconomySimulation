package economysimulation.classes.managers.popup.hint.hints;

import economysimulation.classes.managers.popup.hint.Urgency;

/**
 *
 * @author Max Carter
 */
public class ErrorNullParty extends Hint {

    @Override
    public String getTitle() {
        return "You are not in a party!";
    }

    @Override
    public String getDescription() {
        return "You are not in a party, if this is a mistake, go the the main menu and re-join the lobby.";
    }

    @Override
    public int getUrgency() {
        return Urgency.LOW;
    }
  
}
