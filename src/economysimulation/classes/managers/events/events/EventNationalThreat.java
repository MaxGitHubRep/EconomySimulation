package economysimulation.classes.managers.events.events;

import economysimulation.classes.global.Methods;

/**
 *
 * @author Max Carter
 */
public class EventNationalThreat extends Event {

    private String attacker;
    
    public EventNationalThreat(int index) {
        super.index = index;
        String[] attackers = new String[]{ "North Korea", "Russia", "America" };
        this.attacker = attackers[Methods.randomInt(0, attackers.length-1)];
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
