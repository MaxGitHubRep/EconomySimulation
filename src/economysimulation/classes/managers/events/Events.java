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
            HealthcareStrike = new EventStrikeNHS(),
            StudentStrike = new EventStrikeSchool(),
            HousingCrisis = new EventHousingCrisis(),
            FoodShortage = new EventFoodShortage(),
            TrainStrike = new EventStrikeRail(),
            NationalThreat = new EventNationalThreat(),
            VirusOutbreak = new EventVirusOutbreak();
    
    
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
