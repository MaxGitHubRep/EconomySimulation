package economysimulation.classes.managers.events.events;

/**
 *
 * @author Max Carter
 */
public class EventStrikeSchool extends Event {

    public EventStrikeSchool(int index) {
        super.index = index;
    }
    
    @Override
    public int getIndex() {
        return super.index;
    }
    
    @Override
    public String getTitle() {
        return "Students Strike Against High Uni Fees!";
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
