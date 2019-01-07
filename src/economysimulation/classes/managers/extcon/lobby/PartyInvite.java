package economysimulation.classes.managers.extcon.lobby;

import economysimulation.classes.global.Methods;
import economysimulation.classes.global.User;

/**
 *
 * @author Max Carter
 */
public class PartyInvite {

    private User user;
    private int partyId;
    
    public PartyInvite(User user, int partyId) {
        this.user = user;
        this.partyId = partyId;
    }
    
    /**
     * The User who sent the invite.
     * @return User inviter.
     */
    public User getUser() {
        return user;
    }
    
    /**
     * The party ID in the invite.
     * @return ID of the party.
     */
    public int getPartyID() {
        return partyId;
    }
    
    /* Accepts the party invite and sends the request to the database. */
    public boolean accept() {
        if (LobbyConnector.getPartyId(user.getUserID()) != this.partyId && Methods.UserID < 0) return false;
        else {
            LobbyConnector.addUserToParty(Methods.UserID, partyId);
            return true;
        }
    }
    
    /* Denies the party invite and sends the request to the database. */
    public void ignore() {
        LobbyConnector.removePartyInvitesOutgoing(user.getUserID());
    }
    
    public void undo() {
        LobbyConnector.removeUserFromParty(user.getUserID());
    }
    
}
