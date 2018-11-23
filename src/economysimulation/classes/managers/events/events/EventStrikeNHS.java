package economysimulation.classes.managers.events.events;

/**
 *
 * @author Max Carter
 */
public class EventStrikeNHS extends Event {

    public EventStrikeNHS(int index) {
        super.index = index;
    }
    
    @Override
    public String getTitle() {
        return "NHS Doctors are on Strike!";
    }

    @Override
    public String getDescription() {
        return "Make sure that the NHS sector is sufficiently funded to prevent further strikes.";
    }

    @Override
    public String getImageFileName() {
        return "nhs_strike";
    }
    
}
