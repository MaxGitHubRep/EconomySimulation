package economysimulation.classes.managers.extcon.lobby;

import economysimulation.classes.global.Methods;
import static economysimulation.classes.global.Methods.DBConnector;
import economysimulation.classes.global.User;
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
    
    /** Forces a data input stream. */
    private synchronized void data() {
        if (controlPanel.getPartyID() == 0) {
            //signal a new invite has occured.
            getPartyInvitesReceived().forEach((invite) -> {
                controlPanel.onPartyInviteEvent(invite);
            });
            
        } else {
            controlPanel.onPartyUpdateEvent(getUsersInParty(controlPanel.getPartyID()));
            //get data from database if all users are ready.
        }
        
        teammateFinder.onLobbyUpdateEvent();
        
        if (looped) {
            try {
                connectionThread.sleep(2000);
            } catch (InterruptedException ex) {
                ex.printStackTrace();
            }
            data();
        }
    }
    
    /** Initiates the data input stream loop. */
    public synchronized void startLoop() {
        connectionThread = new Thread(new Runnable() {
            @Override
            public void run() {
                looped = true;
                data();
            }
        });
        connectionThread.start();
    }
    
    /** Stops the data input stream loop. */
    public synchronized void stopLoop() {
        looped = false;
    }
    
    /**
     * Adds a new party invite from {@code fromUser} to {@code toUser}.
     * 
     * @param partyId  ID of the party.
     * @param fromUser ID of the inviter.
     * @param toUser   ID of the invitee.
     */
    public void addPartyInvite(int partyId, int fromUser, int toUser) {
        try {
            String SQLStatement = "INSERT INTO mxcrtr_db.PartyInvites VALUES (?, ?, ?)";
            PreparedStatement pt = DBConnector.getConnection().prepareStatement(SQLStatement);
            pt.setInt(1, partyId);
            pt.setInt(2, fromUser);
            pt.setInt(3, toUser);
            pt.executeUpdate();
            
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
    
    /**
     * Removes all invites and connected users from {@code partyId}.
     * @param partyId The ID of the party.
     */
    public void removeParty(int partyId) {
        try {
            String SQLStatement = "DELETE FROM mxcrtr_db.PartyInvites WHERE PartyID = ?";
            PreparedStatement pt = DBConnector.getConnection().prepareStatement(SQLStatement);
            pt.setInt(1, partyId);
            pt.executeUpdate();
            
            SQLStatement = "DELETE FROM mxcrtr_db.LobbyData WHERE PartyID = ?";
            pt = DBConnector.getConnection().prepareStatement(SQLStatement);
            pt.setInt(1, partyId);
            pt.executeUpdate();
            
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
    
    /**
     * Removes all invites sent to {@code toUser}.
     * @param toUser ID of the invitee.
     */
    public void removePartyInvitesIncomming(int toUser) {
        try {
            String SQLStatement = "DELETE FROM mxcrtr_db.PartyInvites WHERE ToUserID = ?";
            PreparedStatement pt = DBConnector.getConnection().prepareStatement(SQLStatement);
            pt.setInt(1, toUser);
            pt.executeUpdate();
            
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
    
    /**
     * Removes all invites sent by {@code fromUser}
     * @param fromUser ID of the inviter.
     */
    public void removePartyInvitesOutgoing(int fromUser) {
        try {
            String SQLStatement = "DELETE FROM mxcrtr_db.PartyInvites WHERE FromUserID = ?";
            PreparedStatement pt = DBConnector.getConnection().prepareStatement(SQLStatement);
            pt.setInt(1, fromUser);
            pt.executeUpdate();
            
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
    
    /**
     * Gets the next available ID for a party.
     * @return Next available Party ID.
     */
    public int getNextAvailablePartyID() {
        try {
            DBConnector.setResultSet(DBConnector.getStatement().executeQuery("SELECT PartyID FROM mxcrtr_db.PartyInvites"));
            
            int previousID, nextID = 0;
            
            while (DBConnector.getResultSet().next()) {         //needs testing
                previousID = nextID;
                nextID = DBConnector.getResultSet().getInt(1);
                if (nextID != previousID+1) {
                    return previousID+1;
                }
            }
            return nextID+1;
            
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return 0;
    }
    
    /**
     * Gets a list of all the invites received by the user..
     * @return List of invites.
     */
    public List<PartyInvite> getPartyInvitesReceived() {
        return getPartyInvitesReceived(Methods.UserID);
    }
    
    /**
     * Gets a list of all the invites received by {@code userId}.
     * @param userId ID of user.
     * @return List of invites.
     */
    public List<PartyInvite> getPartyInvitesReceived(int userId) {
        List<PartyInvite> list = new ArrayList<>();
        if (userId < 0) return list;
        try {
            String SQLStatement = "SELECT PartyID, FromUserID FROM mxcrtr_db.PartyInvites WHERE ToUserID = ?";
            PreparedStatement pt = DBConnector.getConnection().prepareStatement(SQLStatement);
            pt.setInt(1, userId);
            DBConnector.setResultSet(pt.executeQuery());
            
            //adds user to list if they're found.
            while (DBConnector.getResultSet().next()) {
                int partyId = DBConnector.getResultSet().getInt(1),
                    fromUser = DBConnector.getResultSet().getInt(2);
                list.add(new PartyInvite(new User(Methods.DBUsers.getUsernameFromId(fromUser), fromUser), partyId));
            }
            
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return list;
    }
    
    /**
     * Gets a list of all the invites received by the user..
     * @return List of invites.
     */
    public List<PartyInvite> getPartyInvitesSent() {
        return getPartyInvitesSent(Methods.UserID);
    }
    
    /**
     * Gets a list of all the invites received by {@code userId}.
     * @param userId ID of user.
     * @return List of invites.
     */
    public List<PartyInvite> getPartyInvitesSent(int userId) {
        List<PartyInvite> list = new ArrayList<>();
        if (userId < 0) return list;
        try {
            String SQLStatement = "SELECT PartyID, ToUserID FROM mxcrtr_db.PartyInvites WHERE FromUserID = ?";
            PreparedStatement pt = DBConnector.getConnection().prepareStatement(SQLStatement);
            pt.setInt(1, userId);
            DBConnector.setResultSet(pt.executeQuery());
            
            //adds user to list if they're found.
            while (DBConnector.getResultSet().next()) {
                int partyId = DBConnector.getResultSet().getInt(1),
                    toUser = DBConnector.getResultSet().getInt(2);
                list.add(new PartyInvite(new User(Methods.DBUsers.getUsernameFromId(toUser), toUser), partyId));
            }
            
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return list;
    }
    
    /**
     * Add a user to the coop lobby.
     * @param userId ID of the user.
     */
    public void addCoopUser(int userId) {
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
    public int getPartyId(int userId) {
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
    
    /**
     * Gets all the users in the party, via their usernames.
     * @param partyId ID of the party.
     * @return The list of users in the party.
     */
    public List<User> getUsersInParty(int partyId) {
        List<User> list = new ArrayList<>();
        
        try {
            //get all users in the server slot first
            String SQLStatement = "SELECT UserID FROM mxcrtr_db.LobbyData WHERE PartyID = ?";
            PreparedStatement pt = DBConnector.getConnection().prepareStatement(SQLStatement);
            pt.setInt(1, partyId);
            DBConnector.setResultSet(pt.executeQuery());
            
            //adds user to list if they're found.
            while (DBConnector.getResultSet().next()) { 
                int nextUser = DBConnector.getResultSet().getInt(1);
                System.out.println("user: " + nextUser);//needs checking
                list.add(new User(Methods.DBUsers.getUsernameFromId(nextUser), nextUser));
            }
            
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        
        return list;
    }
    
    /**
     * Gets all the users in the party, via their usernames.
     * @return The list of users in the party.
     */
    public List<User> getUsersNotInParty() {
        return getUsersInParty(0);
    }
    
    /**
     * Adds the {@code userId} to the party with ID {@code partyId}.
     * @param userId    ID of the user.
     * @param partyId   ID of the party.
     */
    public void addUserToParty(int userId, int partyId) {
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
    
    /**
     * Removes {@code userId} from their party.
     * @param userId ID of the user.
     */
    public void removeUserFromParty(int userId) {
        try {
            String SQLStatement = "DELETE FROM mxcrtr_db.LobbyData WHERE UserID = ?";
            PreparedStatement pt = DBConnector.getConnection().prepareStatement(SQLStatement);
            pt.setInt(1, userId);
            pt.executeUpdate();
            
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
    
}
