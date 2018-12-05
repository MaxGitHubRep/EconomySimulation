package economysimulation.classes.managers.extcon.multiplayer;

/**
 *
 * @author Max Carter
 */
public class StoragePackage {

    private int slotId, gameTick, componentId;
    private double componentValue;
    private String updater;
    
    /**
     * Creates a new object that syncs a component ID and it's
     * respected value with accessors to adopt the information.
     * 
     * @param slotId         ID of the server slot.
     * @param componentId    ID of the component.
     * @param componentValue Value of the component.
     * @param updater        User who updated the component.
     * @param gameTick       Game tick counter.
     */
    public StoragePackage(int slotId, int componentId, double componentValue, String updater, int gameTick) {
        this.slotId = slotId;
        this.componentId = componentId;
        this.componentValue = componentValue;
        this.updater = updater;
        this.gameTick = gameTick;
    }
    
    /**
     * Gets the game tick which the component was changed in.
     * 
     * @return Game tick which the component was changed in.
     */
    public int getGameTick() {
        return gameTick;
    }
    
    /**
     * Gets the user who updated the component.
     * 
     * @return User who updated the component.
     */
    public String getUpdater() {
        return updater;
    }
    
    /**
     * Gets the component ID of the object.
     * 
     * @return Component ID.
     */
    public int getComponentId() {
        return componentId;
    }
    
    /**
     * Gets the component value of the object.
     * 
     * @return Component value.
     */
    public double getComponentValue() {
        return componentValue;
    }
    
}
