package economysimulation.classes.managers.popup.hint.hints;

import economysimulation.classes.managers.popup.hint.Urgency;

/**
 *
 * @author Max Carter
 */
public class HintCauseOfCompletion extends Hint {
    
    private String causeOfCompletion = null;
    
    public HintCauseOfCompletion(String causeOfCompletion) {
        this.causeOfCompletion = causeOfCompletion;
    }
    
    @Override
    public String getTitle() {
        return "Simulation has Ended!";
    }

    @Override
    public String getDescription() {
        return "Cause of failure: " + causeOfCompletion;
    }

    @Override
    public int getUrgency() {
        return Urgency.LOW;
    }

}
