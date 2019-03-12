package economysimulation.classes.managers.extcon.lobby;

/**
 *
 * @author Max Carter
 */
public interface LobbyUpdateEvent {
    
    /**
     * When a party invite has been received.
     * @param partyInvite The party invite.
     */
    void onPartyInviteEvent(PartyInvite partyInvite);
    
}
