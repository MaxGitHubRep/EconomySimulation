package economysimulation.classes.global;

import java.text.DecimalFormat;

/**
 * @author Max Carter
 */
public class User {

    //Local data about the user.
    private String username = null;
    private int userId = -1;
    
    /**
     * Creates a new user.
     * @param username
     * @param userId 
     */
    public User(String username, int userId) {
        setName(username);
        setID(userId);
    }
    
    //Creates an empty user.
    public User() {
        reset();
    };
    
    /**
     * Gets the user's name.
     * @return User's name.
     */
    public String getName() {
        return username;
    }
    
    /**
     * Sets the user's name.
     * @param username Name of user.
     */
    public void setName(String username) {
        this.username = username;
    }
    
    /**
     * Gets the user's ID.
     * @return User's ID.
     */
    public int getID() {
        return userId;
    }
    
    /**
     * Sets the user's ID.
     * @param userId User's ID.
     */
    public void setID(int userId) {
        this.userId = userId;
    }
    
    /**
     * Gets the user's full name. This is formatted in
     * {@code <username>#<id>} where the ID is formatted
     * to 5 characters in length with 0s filling the blanks.
     * @return User's full username.
     */
    public String getFullName() {
        return username + "#" + new DecimalFormat("00000").format(userId);
    }
    
    /**
     * Set's the user's details to empty.
     */
    public void reset() {
        setID(-1);
        setName(null);
    }
    
}
