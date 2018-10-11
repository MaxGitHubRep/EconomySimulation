package economysimulation.classes.managers.extcon;

import economysimulation.classes.managers.exception.UserDataOverflowException;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import static economysimulation.classes.global.Methods.DBConnector;
import java.sql.PreparedStatement;

/**
 *
 * @author Max
 */
public class UserData {

    List<String> listNames = new ArrayList();
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
        listNames.clear();
        lastUserID = 0;
        
        try {
            DBConnector.setResultSet(DBConnector.getStatement().executeQuery("SELECT * FROM mxcrtr_db.Users"));
            
            while (DBConnector.getResultSet().next()) {
                listNames.add(DBConnector.getResultSet().getString("Name"));
                lastUserID++;
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
    
    /**
     * List of all the users saved on the database.
     * @return List of user names.
     */
    public List<String> getUserList() {
        return listNames;
    }
    
    /**
     * List of all the user names and IDs concatenated together using local data.
     * @return List of user names and IDs.
     */
    public List<String> getCompleteUserList() {
        List<String> listConcat = new ArrayList();
        for (int i = 0; i < listNames.size(); i++) {
            listConcat.add(listNames.get(i) + "#" + new DecimalFormat("00000").format(i));
        }
        return listConcat;
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
