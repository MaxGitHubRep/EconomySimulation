package economysimulation.classes.managers.popup.hint.hints;

import economysimulation.classes.managers.popup.hint.Urgency;

/**
 *
 * @author Max Carter
 */
public class NetworkError extends Hint {

    @Override
    public String getTitle() {
        return "A network error occurred!";
    }

    @Override
    public String getDescription() {
        return "A network error occurred whilst trying to pull the latest package from the server.";
    }

    @Override
    public int getUrgency() {
        return Urgency.HIGH;
    }
  
}
