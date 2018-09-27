package economysimulation.classes.managers.events.events;

/**
 *
 * @author Max Carter
 */
public class EventVirusOutbreak extends Event {

    public EventVirusOutbreak(int index) {
        super.index = index;
    }
    
    @Override
    public int getIndex() {
        return super.index;
    }
    
    @Override
    public String getTitle() {
        return "Deadly Virus Outbreak!";
    }

    @Override
    public String getDescription() {
        return "Make sure that the Science sector is sufficiently funded so they can create a cure.";
    }

    @Override
    public String getImageFileName() {
        return "toxic";
    }
    
    
    
}
