package economysimulation.classes.managers.events.events;

/**
 *
 * @author Max Carter
 */
public class EventFoodShortage extends Event {

    public EventFoodShortage(int index) {
        super.index = index;
    }
    
    @Override
    public int getIndex() {
        return super.index;
    }
    
    @Override
    public String getTitle() {
        return "Food Shortages Cause Starvation!";
    }

    @Override
    public String getDescription() {
        return "Make sure that the Food sector is sufficiently funded to compensate with the food shortage.";
    }

    @Override
    public String getImageFileName() {
        return "food_shortage";
    }

}
