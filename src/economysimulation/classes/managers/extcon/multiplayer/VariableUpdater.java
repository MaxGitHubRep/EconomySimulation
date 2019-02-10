package economysimulation.classes.managers.extcon.multiplayer;

import economysimulation.classes.global.Methods;
import economysimulation.classes.global.User;

/**
 * @author Max Carter
 */
public class VariableUpdater {

    /** Instance of the storage connector. */
    private StorageConnector storageConnector = null;
    
    /**
     * Creates a new VariableUpdater.
     * @param storageConnector Instance of the database storage connector.
     */
    public VariableUpdater(StorageConnector storageConnector) {
        this.storageConnector = storageConnector;
    }
    
    /**
     * Updates the database when a variable is updated locally.
     * @param storageComponent The component which is modified.
     * @param newValue         The new value of the modified component.
     */
    public void onLocalComponentUpdateEvent(StorageComponent storageComponent, double newValue) {
        if (storageComponent == null) return;
        
        User user = Methods.getUser();
        
        //construct the StoragePackage.
        StoragePackage storagePackage = new StoragePackage(
            Methods.localPartyId,
            storageComponent.getIndex(),
            newValue,
            user,
            Methods.GameDisplay.Ticks
        );
        
        //publish the storage package.
        if (storageConnector != null)
            storageConnector.sendPackage(storagePackage);
    }
    
}
