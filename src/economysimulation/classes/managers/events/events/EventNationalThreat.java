package economysimulation.classes.managers.events.events;

/**
 *
 * @author Max Carter
 */
public class EventNationalThreat extends Event {

    private String attacker;
    
    public EventNationalThreat(String attacker) {
        this.attacker = attacker;
    }
    
    
    @Override
    public String getTitle() {
        return "National Security Threat from " + attacker;
    }

    @Override
    public String getDescription() {
        return "Make sure that the defence and science sectors are sufficiently funded to warn off a threat from another country.";
    }

    @Override
    public String getImageFileName() {
        return attacker.toLowerCase().replace(" ", "_");
    }
    
    
    
}
