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
        if (Methods.LobbyHandler.getPartyId(user.getID()) != this.partyId && Methods.getUser().getID() < 0) return false;
        else {
            if (getPartyID() == 0) partyId = Methods.LobbyHandler.getNextAvailablePartyID();
            Methods.LobbyHandler.addUserToParty(Methods.getUser().getID(), partyId);
            Methods.LobbyHandler.addUserToParty(user.getID(), partyId);
            Methods.LobbyHandler.removePartyInvitesIncomming(Methods.getUser().getID());
            Methods.LobbyHandler.removePartyInvitesOutgoing(Methods.getUser().getID());
            return true;
        }
    }
    
    /* Denies the party invite and sends the request to the database. */
    public void ignore() {
        Methods.LobbyHandler.removePartyInvitesOutgoing(user.getID());
    }
    
    /** Places the invite receiver into the party pool. */
    public void undo() {
        Methods.LobbyHandler.removeUserFromParty(Methods.getUser().getID());
    }
    
}
