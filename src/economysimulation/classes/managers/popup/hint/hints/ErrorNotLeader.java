package economysimulation.classes.managers.popup.hint.hints;

import economysimulation.classes.managers.popup.hint.Urgency;

/**
 *
 * @author Max Carter
 */
public class ErrorNotLeader extends Hint {

    @Override
    public String getTitle() {
        return "You are not the party leader!";
    }

    @Override
    public String getDescription() {
        return "Only the first player in the party can launch the simulation.";
    }

    @Override
    public int getUrgency() {
        return Urgency.LOW;
    }
  
}
