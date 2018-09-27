package economysimulation.classes.managers.events;

import economysimulation.classes.managers.events.events.Event;
import economysimulation.classes.managers.events.events.EventFoodShortage;
import economysimulation.classes.managers.events.events.EventHousingCrisis;
import economysimulation.classes.managers.events.events.EventNationalThreat;
import economysimulation.classes.managers.events.events.EventStrikeNHS;
import economysimulation.classes.managers.events.events.EventStrikeRail;
import economysimulation.classes.managers.events.events.EventStrikeSchool;
import economysimulation.classes.managers.events.events.EventVirusOutbreak;

/**
 *
 * @author Max Carter
 */
public class Events {
    
    public static final Event
            HealthcareStrike = new EventStrikeNHS(0),
            StudentStrike = new EventStrikeSchool(1),
            HousingCrisis = new EventHousingCrisis(2),
            FoodShortage = new EventFoodShortage(3),
            TrainStrike = new EventStrikeRail(4),
            NationalThreat = new EventNationalThreat(5),
            VirusOutbreak = new EventVirusOutbreak(6);
    
    
    public static final Event[] EventList = new Event[]{
        HealthcareStrike,
        StudentStrike,
        HousingCrisis,
        FoodShortage,
        TrainStrike,
        NationalThreat,
        VirusOutbreak
    };
    
}
