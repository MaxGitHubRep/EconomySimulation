package economysimulation.classes.managers.extcon.multiplayer;

import economysimulation.classes.global.Methods;
import static economysimulation.classes.global.Methods.DBConnector;
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
     * @param slotId Server slot to take data from.
     */
    public void pullLatestPackage(int slotId) {
        //update package accordingly
        if (latestPackages == null) {
            latestPackages = new ArrayList<>();
        } else {
            latestPackages.clear();
        }
        try {
            //Gets the server state from the database table.
            String SQLStatement = "SELECT * FROM mxcrtr_db.VariableStorage WHERE GameTick = ? AND SlotID = ?";
            PreparedStatement pt = DBConnector.getConnection().prepareStatement(SQLStatement);
            pt.setInt(1, Methods.GameDisplay.Ticks-1);
            pt.setInt(2, slotId);
            
            DBConnector.setResultSet(pt.executeQuery());
            while (DBConnector.getResultSet().next()) {
                latestPackages.add(new StoragePackage(slotId, 4, 56.5, "max#00001", 567));
            }
            
        } catch (SQLException ex) {
            HintManager.createHint(Hints.NotConnected);
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
    
    public void addServerUser(int slotId, int userSlot, int userId) {
        try {
            String SQLStatement = "UPDATE mxcrtr_db.Servers SET UserSlot" + (userSlot + 1) + " = ? WHERE ServerID = ?";
            PreparedStatement pt = DBConnector.getConnection().prepareStatement(SQLStatement);
            pt.setInt(1, userId);
            pt.setInt(2, slotId);
            pt.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
    
    public void removeServerUser(int slotId, int userSlot) {
        try {
            String SQLStatement = "UPDATE mxcrtr_db.Servers SET UserSlot" + (userSlot + 1) + " = 0 WHERE ServerID = ?";
            PreparedStatement pt = DBConnector.getConnection().prepareStatement(SQLStatement);
            pt.setInt(1, slotId);
            pt.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
    
    public boolean isUserInServer(int userId) {
        try {
            String SQLStatement = "SELECT UserSlot1, UserSlot2, UserSlot3, UserSlot4 FROM mxcrtr_db.Servers";
            PreparedStatement pt = DBConnector.getConnection().prepareStatement(SQLStatement);
            
            DBConnector.setResultSet(pt.executeQuery());
            while (DBConnector.getResultSet().next()) {
                for (int i = 1; i < 5; i++) {
                    if (userId == DBConnector.getResultSet().getInt(i)) {
                        return true;
                    }
                }
            }
                
        } catch (SQLException ex) {
            HintManager.createHint(Hints.DatabaseError);
            ex.printStackTrace();
        }
        return false;
    }
    
    public State getServerState(int slotId) throws SQLException {
        int state = -1;
        try {
            //Gets the server state from the database table.
            String SQLStatement = "SELECT ServerState FROM mxcrtr_db.Servers WHERE ServerID = ?";
            PreparedStatement pt = DBConnector.getConnection().prepareStatement(SQLStatement);
            pt.setInt(1, slotId);
            
            DBConnector.setResultSet(pt.executeQuery());
            DBConnector.getResultSet().next();
            state = DBConnector.getResultSet().getInt(1);
                
        } catch (SQLException ex) {
            HintManager.createHint(Hints.DatabaseError);
            ex.printStackTrace();
        }
        
        //Uses switch case to return the server slot state.
        switch (state) {
            case 0:
                return State.LOBBY;
            case 1:
                return State.IN_GAME;
            case 2:
                return State.END_GAME;
            case 3:
                return State.RESETTING;
            case -1:
            default:
                return State.ERROR;
        }
    }
    
}
