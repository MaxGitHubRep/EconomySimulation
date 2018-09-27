package economysimulation.classes.managers.events.events;

/**
 *
 * @author Max Carter
 */
public class EventStrikeRail extends Event {

    public EventStrikeRail(int index) {
        super.index = index;
    }
    
    @Override
    public int getIndex() {
        return super.index;
    }
    
    @Override
    public String getTitle() {
        return "Train Drivers on Strike due to Low Pay!";
    }

    @Override
    public String getDescription() {
        return "Make sure that the Infrastructure sector receives extra funding to increase train driver pay.";
    }

    @Override
    public String getImageFileName() {
        return "train_strike";
    }
    
    
    
}
