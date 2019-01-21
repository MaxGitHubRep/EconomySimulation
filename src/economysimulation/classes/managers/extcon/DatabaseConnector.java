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
public class DatabaseConnector {
    
    private final String
            HOST = "jdbc:mysql://rds-mxcrtr-db.cejdk9ogcqcy.eu-west-2.rds.amazonaws.com:3306/mxcrtr_db",
            USERNAME = "maxcarter",
            PASSWORD = "1_>_FRXvrMZ>6yR^40htM";
    
    private Connection Connection;
    private Statement Statement;
    private ResultSet ResultSet;
    
    /**
     * Creates a new instance of the database connector.
     * @throws SQLException When a safe connection cannot be established.
     */
    public DatabaseConnector() throws SQLException {
        initConnection();
    }
    
    /**
     * Establishes a connection to the database.
     * @throws SQLException When a safe connection cannot be established.
     */
    public void initConnection() throws SQLException {
        Connection = DriverManager.getConnection(HOST, USERNAME, PASSWORD);
        Statement = Connection.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
        
    }
    
    public Statement getStatement() {
        return this.Statement;
    }
    
    public void setResultSet(ResultSet resultSet) {
        this.ResultSet = resultSet;
    }
    
    public ResultSet getResultSet() {
        return this.ResultSet;
    }
    
    public Connection getConnection() {
        return this.Connection;
    }
    
}
