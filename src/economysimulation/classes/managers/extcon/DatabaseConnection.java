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
    
    protected final String
            HOST = "jdbc:mysql://rds-mxcrtr-db.cejdk9ogcqcy.eu-west-2.rds.amazonaws.com:3306/mxcrtr_db",
            USERNAME = "",
            PASSWORD = "";
    
    protected Connection Connection;
    protected Statement Statement;
    protected ResultSet ResultSet;
    
    /**
     * Establishes connection to database.
     * @throws SQLException When a safe connection cannot be established.
     */
    public DatabaseConnection() throws SQLException {
        Connection = DriverManager.getConnection(HOST, USERNAME, PASSWORD);
        Statement = Connection.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);

    }
    
}
