package economysimulation.classes.managers.extcon.lobby;

import static economysimulation.classes.global.Methods.DBConnector;
import economysimulation.classes.gui.coop.ControlPanel;
import economysimulation.classes.gui.coop.TeammateFinder;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Max Carter
 */
public class LobbyConnector {

    private Thread connectionThread = null;
    private volatile boolean looped = false;
    
    private ControlPanel controlPanel = null;
    private TeammateFinder teammateFinder = null;
    
    public LobbyConnector(ControlPanel controlPanel, TeammateFinder teammateFinder) {
        this.controlPanel = controlPanel;
        this.teammateFinder = teammateFinder;
    }
    
    public void data() {
        if (controlPanel.getPartyID() == 0) {
            //get invites
            
        } else {
            controlPanel.onPartyUpdateEvent(getUsersInParty(controlPanel.getPartyID()));
            //get data from database if all users are ready.
        }
      
        if (looped) {
            try {
                connectionThread.sleep(2000);
            } catch (InterruptedException ex) {
                ex.printStackTrace();
            }
            data();
        }
    }
    
    public void startLoop() {
        connectionThread = new Thread(new Runnable() {
            @Override
            public void run() {
                looped = true;
                data();
            }
        });
        connectionThread.start();
    }
    
    public void stopLoop() {
        looped = false;
    }
    
    public static void addCoopUser(int userId) {
        try {
            String SQLStatement = "INSERT INTO mxcrtr_db.LobbyData VALUES (?, ?)";
            PreparedStatement pt = DBConnector.getConnection().prepareStatement(SQLStatement);
            pt.setInt(1, userId);
            pt.setInt(2, 0);
            pt.executeUpdate();
            
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
    
    /**
     * Gets the party ID from the user ID. If the
     * returned value is 0, the user is not in a
     * party, or the user it not found.
     * 
     * @param userId    The ID of the user.
     * @return ID of the user's party.
     */
    public static int getPartyId(int userId) {
        int id = 0;
        try {
            //get all users in the server slot first
            String SQLStatement = "SELECT PartyID FROM mxcrtr_db.LobbyData WHERE UserID = ?";
            PreparedStatement pt = DBConnector.getConnection().prepareStatement(SQLStatement);
            pt.setInt(1, userId);
            DBConnector.setResultSet(pt.executeQuery());
            
            //adds user to list if they're found.
            DBConnector.getResultSet().next();

            id = DBConnector.getResultSet().getInt(1);
            
        } catch (SQLException ex) {
            ex.printStackTrace();
            return 0;
        }
        
        return id;
    }
    
    public static List<String> getUsersInParty(int partyId) {
        List<String> list = new ArrayList<>();
        
        try {
            //get all users in the server slot first
            String SQLStatement = "SELECT UserID FROM mxcrtr_db.LobbyData WHERE PartyID = ?";
            PreparedStatement pt = DBConnector.getConnection().prepareStatement(SQLStatement);
            pt.setInt(1, partyId);
            DBConnector.setResultSet(pt.executeQuery());
            
            //adds user to list if they're found.
            DBConnector.getResultSet().next();

            for (int i = 1; i < 5; i++) {
                String nextUser = DBConnector.getResultSet().getString(i);
                if (nextUser != null) {
                    list.add(nextUser);
                }
            }
            
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        
        return list;
    }
    
    public static void addUserToParty(int userId, int partyId) {
        try {
            String SQLStatement = "UPDATE mxcrtr_db.LobbyData SET PartyID = ? WHERE UserID = ?";
            PreparedStatement pt = DBConnector.getConnection().prepareStatement(SQLStatement);
            pt.setInt(2, partyId);
            pt.setInt(1, userId);
            pt.executeUpdate();
            
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
    
}
