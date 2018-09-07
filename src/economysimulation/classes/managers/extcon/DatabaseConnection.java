package economysimulation.classes.managers.extcon;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author Max Carter
 */
public class DatabaseConnection {
    
    private final String
            HOST = "",
            USERNAME = "",
            PASSWORD = "";
    
    private Connection Connection;
    private Statement Statement;
    private ResultSet ResultSet;
    
    /**
     * Establishes connection to database.
     */
    public DatabaseConnection() {
        try {
            Connection = DriverManager.getConnection(HOST, USERNAME, PASSWORD);
            Statement = Connection.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
            
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
    
}
