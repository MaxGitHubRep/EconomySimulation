package economysimulation.classes.managers.extcon;

import economysimulation.classes.managers.exception.UserDataOverflowException;
import java.sql.SQLException;
import static economysimulation.classes.global.Methods.DBConnector;
import economysimulation.classes.global.User;
import java.sql.PreparedStatement;

/**
 * @author Max Carter
 */
public class UserData {

    //Next user ID and total user count.
    private int nextAvailableUserID = -1, totalUsers = 0;
    
    /** Deals with the user data. */
    public UserData() {
        refresh();
    }
    
    /** Pulls data from the database and updates the locally saved data. */
    public void refresh() {
        totalUsers = 0;
        
        try {
            //gets the amount of users for the total users.
            DBConnector.setResultSet(DBConnector.getStatement().executeQuery("SELECT COUNT(*) FROM mxcrtr_db.Users"));
            
            while (DBConnector.getResultSet().next()) {
                totalUsers = DBConnector.getResultSet().getInt(1);
            }
            
            //gets all the users from the database.
            DBConnector.setResultSet(DBConnector.getStatement().executeQuery("SELECT UserID FROM mxcrtr_db.Users"));
            
            int previousUser, nextUser = 0;
            
            //loop through all the users.
            while (DBConnector.getResultSet().next()) {
                previousUser = nextUser;
                nextUser = DBConnector.getResultSet().getInt(1);
                //if a user is not found, return id.
                //if user is found, iterate to next user.
                if (nextUser != previousUser+1) {
                    nextAvailableUserID = previousUser+1;
                    return;
                }
            }
            nextAvailableUserID = nextUser+1;
            
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
    
    /**
     * The amount of users stored on the database.
     * @return Size of users list.
     */
    public int getUserCount() {
        return totalUsers;
    }
    
    /**
     * Gets the next available User ID that can be used.
     * @return Unique user ID.
     */
    public int getNextAvailableUserID() {
        //throws exception if the total amount of users is met.
        if (getUserCount() >= 99999) {
            try {
                throw new UserDataOverflowException();
            } catch (UserDataOverflowException ex) {
                ex.printStackTrace();
            }
        }
        return nextAvailableUserID;
    }
    
    /**
     * Gets the username from an ID.
     * @param id ID of the user.
     * @return Name of the user.
     */
    public String getUsernameFromId(int id) {
        //defaults name to null
        String name = "null";
        try {
            //get Username from database.
            String SQLStatement = "SELECT Username FROM mxcrtr_db.Users WHERE UserID = ?";
            PreparedStatement pt = DBConnector.getConnection().prepareStatement(SQLStatement);
            pt.setInt(1, id);
            
            //set the name to the result set.
            DBConnector.setResultSet(pt.executeQuery());
            if (DBConnector.getResultSet().next())
                name = DBConnector.getResultSet().getString("Username");
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return name;
    }
    
    /**
     * Creates a new user from their name.
     * @param user Instance of the user.
     */
    public void createNewUser(User user) {
        createNewUser(user.getID(), user.getName());
    }
    
    /**
     * Creates a new user in the database.
     * @param id   ID of the user.
     * @param name Name of the user.
     */
    public void createNewUser(int id, String name) {
        //validates that it is a valid user ID.
        if (id < 1) return;
        try {
            //creates user in database.
            String SQLStatement = "INSERT INTO mxcrtr_db.Users VALUES (?, ?)";
            PreparedStatement pt = DBConnector.getConnection().prepareStatement(SQLStatement);
            pt.setInt(1, id);
            pt.setString(2, name);
            pt.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
    
        
    /**
     * Determines whether or not the {@code user} is existing.
     * This is useful if a local copy of a user is saved and
     * you want to know if said user is still on the database.
     * @param user Instance of the user.
     * @return Will return true if the user can be found on
     *         the database. WIll return false if there are
     *         no matches between the user's ID and username.
     */
    public boolean isUserAlive(User user) {
        try {
            String SQLStatement = "SELECT * FROM mxcrtr_db.Users WHERE UserID = ? AND Username = ?";
            PreparedStatement pt = DBConnector.getConnection().prepareStatement(SQLStatement);
            pt.setInt(1, user.getID());
            pt.setString(2, user.getName());
            DBConnector.setResultSet(pt.executeQuery());
            
            //returns true if a user exists.
            if (DBConnector.getResultSet().next())
                return true;
            
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        //defaults to returning false;
        return false;
    }
    
    /**
     * Returns true if a user has a profile saved but is not used.
     * @param user Instance of the user.
     * @return Whether the saved user has done anything.
     */
    public boolean isRedundantUser(User user) {
        return isRedundantUser(user.getID());
    }
    
    /**
     * Returns true if a user has a profile saved but is not used.
     * @param userId ID of the user.
     * @return Whether the saved user has done anything.
     */
    public boolean isRedundantUser(int userId) {
        try {
            String SQLStatement = "SELECT UserID FROM mxcrtr_db.LinkUsersGames WHERE UserID = ?";
            PreparedStatement pt = DBConnector.getConnection().prepareStatement(SQLStatement);
            pt.setInt(1, userId);
            
            DBConnector.setResultSet(pt.executeQuery());
            return !(DBConnector.getResultSet().next());
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        //not redundant by default.
        return false;
    }
    
    /**
     * Removes a user from the database.
     * @param user Instance of user.
     */
    public void removeUser(User user) {
        removeUser(user.getID());
    }
    
    /**
     * Removes a user from the database.
     * @param userId ID of the user.
     */
    public void removeUser(int userId) {
        try {
            //deletes a user from Users and LobbyData.
            for (String table : new String[]{ "Users", "LobbyData" }) {
                String SQLStatement = "DELETE FROM mxcrtr_db." + table + " WHERE UserID = ?";
                PreparedStatement pt = DBConnector.getConnection().prepareStatement(SQLStatement);
                pt.setInt(1, userId);
                pt.executeUpdate();
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
    
}
