package economysimulation.classes.managers.events.events;

/**
 * @author Max Carter
 */
public abstract class Event {
    
    /** Index of the event. */
    public int index;
    
    /**
     * Gets the index of the event.
     * @return Index of the event.
     */
    public int getIndex() {
        return index;
    }
    
    /**
     * Gets the title of the event.
     * @return The title of the event.
     */
    public abstract String getTitle();
    
    /**
     * Gets the description of the event.
     * @return The description of the event.
     */
    public abstract String getDescription();
    
    /**
     * Gets the image file name of the event.
     * @return The image file name of the event.
     */
    public abstract String getImageFileName();
    
}
