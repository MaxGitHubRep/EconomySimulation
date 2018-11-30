package economysimulation.classes.managers.popup.hint.hints;

import economysimulation.classes.managers.popup.hint.Urgency;

/**
 *
 * @author Max Carter
 */
public class HintInsufficientFunds extends Hint {

    @Override
    public String getTitle() {
        return "Insufficient Funds for Desired Payment!";
    }

    @Override
    public String getDescription() {
        return "Increase taxes to obtain more money.";
    }

    @Override
    public int getUrgency() {
        return Urgency.MEDIUM;
    }
  
}
