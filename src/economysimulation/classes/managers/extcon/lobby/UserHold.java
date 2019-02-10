package economysimulation.classes.managers.extcon.lobby;

import economysimulation.classes.global.Methods;
import economysimulation.classes.global.User;
import economysimulation.classes.managers.popup.hint.HintManager;
import economysimulation.classes.managers.popup.hint.Hints;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

/**
 *
 * @author Max Carter
 */
public class UserHold extends JLabel {

    /** Instance of user who's featured on the label. */
    private User user;
    
    /** Thread which connects to the database. */
    private Thread connectionThread = null;
    
    /**
     * Creates a new display label for the user.
     * @param user Instance of featured user.
     */
    public UserHold(User user) {
        super(user.getFullName());
        this.user = user;
        init();
    }
    
    /** Creates the display of the label. */
    private void init() {
        setFont(new Font("Agency FB",Font.BOLD, 30));
        setForeground(Methods.ThemeHandler.getTheme().getPrimaryTextColor());
        setOpaque(true);
        setBackground(Methods.ThemeHandler.getTheme().getPrimaryColor());
        setSize(180, 60);
        setBorder(BorderFactory.createMatteBorder(1, 5, 1, 1,
                Methods.ThemeHandler.getTheme().getPrimaryTextColor()));
        setHorizontalAlignment(SwingConstants.CENTER);
        setVerticalAlignment(SwingConstants.CENTER);
        setCursor(new Cursor(Cursor.HAND_CURSOR));
        
        //when a user is clicked, they will get invited to the user's party.
        addMouseListener(new MouseClickEvent(connectionThread, this));
    }
    
    /**
     * Gets the instance of the user.
     * @return The instance of the user.
     */
    public User getUser() {
        return user;
    }
    
    private class MouseClickEvent extends MouseAdapter {
        
        /** If the system is still in the process of inviting the user. */
        private volatile boolean validating = false;
        
        /** Thread to connect to the database. */
        private Thread connectionThread = null;
        
        /** Instance of the display for the user. */
        private UserHold userHold = null;
        
        /**
         * When the user clicks on a user's label.
         * @param connectionThread Thread to connect to the database on.
         * @param userHold The display of the user.
         */
        public MouseClickEvent(Thread connectionThread, UserHold userHold) {
            this.connectionThread = connectionThread;
            this.userHold = userHold;
        }
        
        @Override
        public void mouseClicked(MouseEvent me) {
            if (validating) return;
            
            connectionThread = new Thread(new Runnable() {
                @Override
                public void run() {
                    validating = true;
                    
                    //loops through all of the party invites
                    for (PartyInvite inv : Methods.LobbyHandler.getPartyInvitesSent()) {
                        //determines whether or not the user is already invited.
                        if (inv.getUser().getFullName().equals(userHold.getUser().getFullName())) {
                            HintManager.createHint(Hints.AlreadyInvited);
                            validating = false;
                            return;
                        }
                    }
                    
                    //creates the party invite.
                    Methods.LobbyHandler.addPartyInvite(Methods.localPartyId == 0 ? Methods.LobbyHandler.getNextAvailablePartyID() : Methods.localPartyId,
                        Methods.getUser().getID(), user.getID());
                    validating = false;
                }
            });
            connectionThread.start();
            
        }
            
    }
    
}
