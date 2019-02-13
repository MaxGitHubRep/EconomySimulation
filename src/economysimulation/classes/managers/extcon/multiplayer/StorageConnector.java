package economysimulation.classes.managers.extcon.multiplayer;

import economysimulation.classes.global.Methods;
import static economysimulation.classes.global.Methods.DBConnector;
import economysimulation.classes.global.User;
import economysimulation.classes.managers.popup.hint.HintManager;
import economysimulation.classes.managers.popup.hint.Hints;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Max Carter
 */
public class StorageConnector {
    
    private List<StoragePackage> latestPackages = null;
    
    /**
     * Pull the data from the multi-player database.
     * @param partyId The ID of the party.
     */
    public void pullLatestPackage(int partyId) {
        //update package accordingly
        if (latestPackages == null) {
            latestPackages = new ArrayList<>();
        } else {
            latestPackages.clear();
        }
        try {
            System.out.println("pulling...");
            //Gets the server state from the database table.
            String SQLStatement = "SELECT * FROM mxcrtr_db.VariableStorage WHERE GameTick = ? AND PartyID = ?";
            PreparedStatement pt = DBConnector.getConnection().prepareStatement(SQLStatement);
            pt.setInt(1, Methods.GameDisplay.Ticks);
            pt.setInt(2, partyId);
            DBConnector.setResultSet(pt.executeQuery());
            
            //loops through variable changes and formats them into a package.
            while (DBConnector.getResultSet().next()) {
                //gets the user from the local user pool via their id.
                User user = Methods.LobbyHandler.getUserFromLocalParty(
                        DBConnector.getResultSet().getInt("UpdaterID"));
                
                //formats the storage package.
                latestPackages.add(new StoragePackage(
                    partyId,
                    DBConnector.getResultSet().getInt("ComponentID"), 
                    DBConnector.getResultSet().getDouble("ComponentValue"),
                    user,
                    DBConnector.getResultSet().getInt("GameTick")
                ));
            }
            
        } catch (SQLException ex) {
            HintManager.createHint(Hints.NotConnected);
            ex.printStackTrace();
        }
    }
    
    /**
     * Sends a package to the database which contains changes to a variable.
     * @param pkg Package instance.
     */
    public void sendPackage(StoragePackage pkg) {
        try {
            System.out.println("trying");
            String SQLStatement = "INSERT INTO mxcrtr_db.VariableStorage VALUES (?, ?, ?, ?, ?)";
            PreparedStatement pt = DBConnector.getConnection().prepareStatement(SQLStatement);
            pt.setInt(1, pkg.getPartyID());
            pt.setInt(2, pkg.getComponentId());
            pt.setDouble(3, pkg.getComponentValue());
            pt.setInt(4, pkg.getUpdater().getID());
            pt.setInt(5, pkg.getGameTick());
            pt.executeUpdate();
            
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
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
