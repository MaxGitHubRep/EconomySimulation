package economysimulation.classes.managers.extcon.lobby;

import economysimulation.classes.global.User;
import java.awt.Font;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

/**
 *
 * @author Max Carter
 */
public class UserHold extends JLabel {

    private User user;
    
    public UserHold(User user) {
        super(user.getFullUsername());
        init();
    }
    
    private void init() {
        setFont(new Font("Agency FB",Font.PLAIN, 20));
        setSize(100, 50);
        setHorizontalAlignment(SwingConstants.CENTER);
        setVerticalAlignment(SwingConstants.CENTER);
    }
    
    public User getUser() {
        return user;
    }
    
}
