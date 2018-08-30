package economysimulation.classes.managers.events;

import economysimulation.classes.managers.events.events.Event;
import economysimulation.classes.managers.exception.InvalidPanelSizeException;
import economysimulation.classes.managers.shadow.Position;
import economysimulation.classes.managers.shadow.ShadowFrame;
import economysimulation.classes.managers.shadow.ShadowSize;
import economysimulation.classes.managers.shadow.Speed;

/**
 *
 * @author Max Carter
 */
public class EventManager {
    
    private static EventDisplay eventDisplay = new EventDisplay();
    
    public static void createEvent(Event event) {
        eventDisplay.createEvent(event.getTitle(), event.getDescription(), event.getImageFileName());
        try {
            new ShadowFrame("Event - " + event.getTitle(), eventDisplay, Position.CENTRE, ShadowSize.EXTRA_LARGE, Speed.VERY_SLOW);
        } catch (InvalidPanelSizeException ex) {
            ex.printStackTrace();
        }
    }
    
}
