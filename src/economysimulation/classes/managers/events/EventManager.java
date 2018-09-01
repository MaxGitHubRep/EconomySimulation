package economysimulation.classes.managers.events;

import economysimulation.classes.economy.sectors.Sector;
import economysimulation.classes.global.Methods;
import economysimulation.classes.managers.events.events.Event;
import economysimulation.classes.managers.exception.InvalidPanelSizeException;
import economysimulation.classes.managers.shadow.Position;
import economysimulation.classes.managers.shadow.ShadowFrame;
import economysimulation.classes.managers.shadow.ShadowSize;
import economysimulation.classes.managers.shadow.Speed;
import java.util.ArrayList;

/**
 *
 * @author Max Carter
 */
public class EventManager {
    
    private static EventDisplay eventDisplay = new EventDisplay();
    
    /**
     * Creates an event based on current spending influences.
     */
    public static void createEvent() {
        ArrayList<Event> events = new ArrayList<>();
            
        for (int i = 0; i < Sector.SectorList.length-1; i++) {
            if (Sector.SectorList[i].getSpendingInfluence() == 0) {
                events.add(Events.EventList[i]);
            }
        }
        
        if (events.size() > 0) {
            Event event = events.get(Methods.randomInt(0, events.size()));
            
            eventDisplay.createEvent(event.getTitle(), event.getDescription(), event.getImageFileName());
            try {
                new ShadowFrame("Event - " + event.getTitle(), eventDisplay, Position.CENTRE, ShadowSize.EXTRA_LARGE, Speed.VERY_SLOW);
            } catch (InvalidPanelSizeException ex) {
                ex.printStackTrace();
            }
        }
    }
    
}
