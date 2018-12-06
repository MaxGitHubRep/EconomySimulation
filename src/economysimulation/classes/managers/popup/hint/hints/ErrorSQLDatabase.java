package economysimulation.classes.managers.popup.hint.hints;

import economysimulation.classes.managers.popup.hint.Urgency;

/**
 *
 * @author Max Carter
 */
public class ErrorSQLDatabase extends Hint {

    @Override
    public String getTitle() {
        return "Encountered a Problem with the Database!";
    }

    @Override
    public String getDescription() {
        return "Try to make sure you are connected to the internet.";
    }

    @Override
    public int getUrgency() {
        return Urgency.HIGH;
    }
  
}
