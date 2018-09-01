package economysimulation.classes.managers.events.events;

/**
 *
 * @author Max Carter
 */
public class EventHousingCrisis extends Event {

    @Override
    public String getTitle() {
        return "Floods Destroy Houses!";
    }

    @Override
    public String getDescription() {
        return "Make sure that the Housing sector receives money to help develop new housing.";
    }

    @Override
    public String getImageFileName() {
        return "america";
    }
    
    
    
}
