package economysimulation.classes.global;

import java.text.DecimalFormat;

/**
 *
 * @author Max
 */
public class User {

    private String username = null;
    private int userId = -1;
    
    public User(String username, int userId) {
        this.username = username;
        this.userId = userId;
    }
    
    public String getUsername() {
        return username;
    }
    
    public int getUserID() {
        return userId;
    }
    
    public String getFullUsername() {
        return username + "#" + new DecimalFormat("00000").format(userId);
    }
    
}
