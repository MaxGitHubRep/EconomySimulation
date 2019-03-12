package economysimulation.classes.managers.events;

import economysimulation.classes.global.Methods;
import static economysimulation.classes.global.Methods.SectorInstance;
import economysimulation.classes.managers.events.events.Event;
import economysimulation.classes.managers.exception.InvalidPanelSizeException;
import economysimulation.classes.managers.popup.positions.FramePosition;
import economysimulation.classes.managers.shadow.ShadowFrame;
import economysimulation.classes.managers.shadow.ShadowSize;
import economysimulation.classes.managers.shadow.Speed;
import java.util.ArrayList;

/**
 * @author Max Carter
 */
public class EventManager {
    
    /** Instance of the event displayer. */
    private static final EventDisplay eventDisplay = new EventDisplay();
    
    /** ID of the event being displayed. */
    public static int eventId = 7;
    
    /** Delay of the repayment of the event-sector. */
    public static double delay = 1;
    
    /** Creates an event based on current spending influences. */
    public static void createEvent() {
        //creates list of all sectors which have a 0% spending influence.
        ArrayList<Event> events = new ArrayList<>();
            
        for (int i = 0; i < SectorInstance.SectorList.length-1; i++) {
            if (SectorInstance.SectorList[i].getSpendingInfluence() <= 0) {
                events.add(Events.EventList[i]);
            }
        }
        
        if (events.size() > 0) {
            //gets a random sector from the list to become the event-sector.
            Event event = events.get(Methods.randomInt(0, events.size()-1));
            delay = 0;
            eventId = event.getIndex();
            
            //create a frame with the event-sector.
            eventDisplay.createEvent(event.getTitle(), event.getDescription(), event.getImageFileName());
            try {
                ShadowFrame shadowFrame = new ShadowFrame("Event - " + event.getTitle(), eventDisplay, FramePosition.getPositionFromId(0), ShadowSize.EXTRA_LARGE, Speed.VERY_SLOW);
                shadowFrame.inflate();
            } catch (InvalidPanelSizeException ex) {
                ex.printStackTrace();
            }
        } else {
            //resets the delay and event ID.
            eventId = 7;
            delay = 1;
        }
    }
    
}
