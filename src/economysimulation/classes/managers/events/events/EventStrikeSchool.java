package economysimulation.classes.managers.events.events;

/**
 *
 * @author Max Carter
 */
public class EventStrikeSchool extends Event {

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
