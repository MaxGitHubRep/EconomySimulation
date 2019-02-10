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

    private User user;
    private Thread connectionThread = null;
    
    public UserHold(User user) {
        super(user.getFullName());
        this.user = user;
        init();
    }
    
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
    
    public User getUser() {
        return user;
    }
    
    private class MouseClickEvent extends MouseAdapter {
        
        private volatile boolean validating = false;
        
        private Thread connectionThread = null;
        private UserHold userHold = null;
        
        public MouseClickEvent(Thread connectionThread, UserHold userHold) {
            this.connectionThread = connectionThread;
            this.userHold = userHold;
        }
        
        //needs testing
        @Override
        public void mouseClicked(MouseEvent me) {
            if (validating) return;
            
            connectionThread = new Thread(new Runnable() {
                @Override
                public void run() {
                    validating = true;
                    
                    for (PartyInvite inv : Methods.LobbyHandler.getPartyInvitesSent()) {
                        if (inv.getUser().equals(userHold.getUser())) {
                            HintManager.createHint(Hints.AlreadyInvited);
                            validating = false;
                            return;
                        }
                    }
                    
                    int partyId = Methods.LobbyHandler.getPartyId(Methods.getUser().getID());
                    Methods.LobbyHandler.addPartyInvite(partyId == 0 ? Methods.LobbyHandler.getNextAvailablePartyID() : partyId,
                        Methods.getUser().getID(), user.getID());
                    validating = false;
                }
            });
            connectionThread.start();
            
        }
            
    }
    
}
