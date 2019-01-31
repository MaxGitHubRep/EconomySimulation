package economysimulation.classes.global;

import java.text.DecimalFormat;

/**
 *
 * @author Max Carter
 */
public class User {

    private String username = null;
    private int userId = -1;
    
    public User(String username, int userId) {
        setName(username);
        setID(userId);
    }
    
    public User() {};
    
    public String getName() {
        return username;
    }
    
    public void setName(String username) {
        this.username = username;
    }
    
    public int getID() {
        return userId;
    }
    
    public void setID(int userId) {
        this.userId = userId;
    }
    
    public String getFullName() {
        return username + "#" + new DecimalFormat("00000").format(userId);
    }
    
    public void reset() {
        setID(-1);
        setName(null);
    }
    
}
