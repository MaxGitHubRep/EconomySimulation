package economysimulation.classes.managers.extcon;

import economysimulation.classes.managers.exception.UserDataOverflowException;
import economysimulation.classes.managers.exception.UserDataUnderflowException;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import static economysimulation.classes.global.Methods.DBConnector;

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
            DBConnector.ResultSet = DBConnector.Statement.executeQuery("SELECT * FROM mxcrtr_db.Users");
            
            while (DBConnector.ResultSet.next()) {
                listNames.add(DBConnector.ResultSet.getString("Name"));
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
     * The latest user ID to be stored in the database.
     * @return Latest user ID.
     */
    public int getLatestUserID() {
        if (lastUserID == 0) {
            try {
                throw new UserDataUnderflowException();
            } catch (UserDataUnderflowException ex) {
                ex.printStackTrace();
            }
        }
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
    
}
