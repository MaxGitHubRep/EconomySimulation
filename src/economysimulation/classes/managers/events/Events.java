package economysimulation.classes.managers.events;

import economysimulation.classes.managers.events.events.Event;
import economysimulation.classes.managers.events.events.EventNationalThreat;

/**
 *
 * @author Max Carter
 */
public class Events {
    
    public static final Event
            NationalThreatNorthKorea = new EventNationalThreat("North Korea"),
            NationalThreadAmerica = new EventNationalThreat("America"),
            NationalThreatRussia = new EventNationalThreat("Russia");
    
    
}
