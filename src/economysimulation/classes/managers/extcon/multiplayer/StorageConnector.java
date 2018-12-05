package economysimulation.classes.managers.extcon.multiplayer;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Max Carter
 */
public class StorageConnector {

    private int slotId = -1;
    
    private List<StoragePackage> latestPackages = null;
    
    /**
     * Object which pulls data from the multi-player database.
     * 
     * @param slotId ID of the server slot.
     */
    public StorageConnector(int slotId) {
        this.slotId = slotId;
    }
    
    /**
     * Pull the data from the multi-player database.
     */
    public void pullLatestPackage() {
        
        //get package from server
        
        //update package accordingly
        if (latestPackages == null) {
            latestPackages = new ArrayList<>();
        } else {
            latestPackages.clear();
        }
        //latestPackages.add(new StoragePackage(slotId, 4, 56.5, "max#00001", 567));
    }
    
    /**
     * Gets the latest storage package from the database.
     * 
     * @return Latest variable package from the database.
     */
    public List<StoragePackage> getStoragePackage() {
        return latestPackages;
    }
    
    /**
     * Gets the amount of changes that were made to the
     * components in the last game tick.
     * 
     * @return Amount of changes made recently.
     */
    public int getChangesQuantity() {
        return latestPackages.size();
    }
    
}
