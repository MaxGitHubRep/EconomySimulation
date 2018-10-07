package economysimulation.classes.managers.extcon;

import static economysimulation.classes.global.Methods.DBConnection;
import economysimulation.classes.managers.exception.UserDataOverflowException;
import economysimulation.classes.managers.exception.UserDataUnderflowException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Max
 */
public class UserData {

    List<String> listNames = new ArrayList();
    List<Integer> listIDs = new ArrayList();
    
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
        listIDs.clear();
        
        try {
            DBConnection.ResultSet = DBConnection.Statement.executeQuery("SELECT * FROM mxcrtr_db.Users");
            
            while (DBConnection.ResultSet.next()) {
                listNames.add(DBConnection.ResultSet.getString("Name"));
                listIDs.add(DBConnection.ResultSet.getInt("UserID"));
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
     * List of all the user IDs saved on the database.
     * @return List of user IDs.
     */
    public  List<Integer> getUserIDList() {
        return listIDs;
    }
    
    /**
     * List of all the user names and IDs concatenated together using local data.
     * @return List of user names and IDs.
     */
    public List<String> getCompleteUserList() {
        List<String> listConcat = new ArrayList();
        for (int i = 0; i < listNames.size(); i++) {
            listConcat.add(listNames.get(i) + "#" + listIDs.get(i));
        }
        return listConcat;
    }
    
    /**
     * The amount of users stored on the database.
     * @return Size of users list.
     */
    public int getUserCount() {
        return listIDs.size();
    }
    
    /**
     * The latest user ID to be stored in the database.
     * @return Latest user ID.
     */
    public int getLatestUserID() {
        if (listIDs.isEmpty()) {
            try {
                throw new UserDataUnderflowException();
            } catch (UserDataUnderflowException ex) {
                ex.printStackTrace();
            }
        }
        return listIDs.get(listIDs.size()-1);
    }

    /**
     * Gets the next available User ID that can be used.
     * @return Unique user ID.
     */
    public int getAvailableUserID() {
        if (this.getUserCount() >= 99999) {
            try {
                throw new UserDataOverflowException();
            } catch (UserDataOverflowException ex) {
                ex.printStackTrace();
            }
        }

        return (listIDs.isEmpty()) ? 0 : getLatestUserID()+1;

    }
    
}
