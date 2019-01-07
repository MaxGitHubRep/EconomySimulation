package economysimulation.classes.managers.extcon.lobby;

import economysimulation.classes.global.Methods;

/**
 *
 * @author Max
 */
public class PartyInvite {

    private String user;
    private int userId, partyId;
    
    public PartyInvite(String user, int userId, int partyId) {
        this.user = user;
        this.userId = userId;
        this.partyId = partyId;
    }
    
    public String getUser() {
        return user;
    }
    
    public int getUserID() {
        return userId;
    }
    
    public int getPartyID() {
        return partyId;
    }
    
    /* Accepts the party invite and sends the request to the database. */
    public boolean accept() {
        if (LobbyConnector.getPartyId(userId) != this.partyId && Methods.UserID < 0) return false;
        else {
            LobbyConnector.addUserToParty(Methods.UserID, partyId);
            return true;
        }
    }
    
    /* Denies the party invite and sends the request to the database. */
    public void ignore() {
        /**
         * TODO
         * add sql table for invites
         */
    }
    
    public void undo() {
        LobbyConnector.removeUserFromParty(userId);
    }
    
}
