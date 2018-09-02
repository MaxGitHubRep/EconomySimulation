package economysimulation.classes.managers.events.events;

import economysimulation.classes.global.Methods;

/**
 *
 * @author Max Carter
 */
public class EventStrikeSchool extends Event {

    @Override
    public String getTitle() {
        return "Students are on Strike Against High University Fees!";
    }

    @Override
    public String getDescription() {
        return "Make sure that the Education sector is sufficiently funded to reduce fees.";
    }

    @Override
    public String getImageFileName() {
        return "student_strike";
    }
    
    
    
}
