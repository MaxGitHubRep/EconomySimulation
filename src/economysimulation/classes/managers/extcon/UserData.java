package economysimulation.classes.managers.extcon;

import economysimulation.classes.managers.exception.UserDataOverflowException;
import java.sql.SQLException;
import static economysimulation.classes.global.Methods.DBConnector;
import java.sql.PreparedStatement;

/**
 *
 * @author Max Carter
 */
public class UserData {

    private int lastUserID = 0;
    
    /**
     * Deals with the user data.
     */
    public UserData() {
        refresh();
    }
    
    /**
     * Pulls data from the database and updates the locally saved data.
     */
    public void refresh() {
        lastUserID = 0;
        
        try {
            DBConnector.setResultSet(DBConnector.getStatement().executeQuery("SELECT COUNT(*) FROM mxcrtr_db.Users"));
            
            while (DBConnector.getResultSet().next()) {
                lastUserID = DBConnector.getResultSet().getInt(1);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
    
    /**
     * The amount of users stored on the database.
     * @return Size of users list.
     */
    public int getUserCount() {
        return lastUserID;
    }
    
    /**
     * Gets the next available User ID that can be used.
     * @return Unique user ID.
     */
    public int getNextAvailableUserID() {
        if (this.getUserCount() >= 99999) {
            try {
                throw new UserDataOverflowException();
            } catch (UserDataOverflowException ex) {
                ex.printStackTrace();
            }
        }

        return lastUserID + 1;

    }
    
    public String getUsernameFromId(int id) {
        String name = "";
        try {
            String SQLStatement = "SELECT Username FROM mxcrtr_db.Users WHERE UserID = ?";
            PreparedStatement pt = DBConnector.getConnection().prepareStatement(SQLStatement);
            pt.setInt(1, id);
            
            DBConnector.setResultSet(pt.executeQuery());
            DBConnector.getResultSet().next();
            
            name = DBConnector.getResultSet().getString("Username");
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return name;
    }
    
    public void createNewUser(int id, String name) {
        try {
            String SQLStatement = "INSERT INTO mxcrtr_db.Users VALUES (?, ?)";
            PreparedStatement pt = DBConnector.getConnection().prepareStatement(SQLStatement);
            pt.setInt(1, id);
            pt.setString(2, name);
            pt.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
    
}
