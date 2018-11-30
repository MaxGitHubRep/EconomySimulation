package economysimulation.classes.managers.popup.hint.hints;

import economysimulation.classes.managers.popup.hint.Urgency;

/**
 *
 * @author Max Carter
 */
public class HintBankRateTooHigh extends Hint {

    @Override
    public String getTitle() {
        return "New Interest Rates are Dangerously High.";
    }

    @Override
    public String getDescription() {
        return "High interest rates means consumers are more likely to save than spend money.";
    }

    @Override
    public int getUrgency() {
        return Urgency.LOW;
    }

}
