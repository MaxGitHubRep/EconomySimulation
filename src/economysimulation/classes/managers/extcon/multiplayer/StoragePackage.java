package economysimulation.classes.managers.extcon.multiplayer;

import economysimulation.classes.global.User;

/**
 *
 * @author Max Carter
 */
public class StoragePackage {

    private int partyId, gameTick, componentId;
    private double componentValue;
    private User user;
    
    /**
     * Creates a new object that syncs a component ID and it's
     * respected value with accessors to adopt the information.
     * 
     * @param partyId        ID of the party.
     * @param componentId    ID of the component.
     * @param componentValue Value of the component.
     * @param user           User who updated the component.
     * @param gameTick       Game tick counter.
     */
    public StoragePackage(int partyId, int componentId, double componentValue, User user, int gameTick) {
        this.partyId = partyId;
        this.componentId = componentId;
        this.componentValue = componentValue;
        this.user = user;
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
    public User getUpdater() {
        return user;
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
    
    /**
     * Gets the ID of the party.
     * @return Party ID.
     */
    public int getPartyID() {
        return partyId;
    }
    
}
