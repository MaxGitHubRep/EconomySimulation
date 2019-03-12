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
    
    //Database connection credentials.
    private final String
            HOST = "jdbc:mysql://rds-mxcrtr-db.cejdk9ogcqcy.eu-west-2.rds.amazonaws.com:3306/mxcrtr_db",
            USERNAME = "maxcarter",
            PASSWORD = "";
    
    /** Connection instance. */
    private Connection Connection;
    
    /** Statement instance. */
    private Statement Statement;
    
    /** ResultSet instance. */
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
        Statement = Connection.createStatement();
        
    }
    
    /**
     * Gets the statement of the database connection.
     * @return Statement of database connection.
     */
    public Statement getStatement() {
        try {
            //re-initiates connection if it is closed.
            if (Statement.isClosed()) {
                initConnection();
            }
            return Statement;
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        //defaults to empty statement.
        return null;
    }
    
    /**
     * Sets the ResultSet.
     * @param resultSet New ResultSet.
     */
    public void setResultSet(ResultSet resultSet) {
        this.ResultSet = resultSet;
    }
    
    /**
     * Gets the ResultSet.
     * @return The current ResultSet.
     */
    public ResultSet getResultSet() {
        return this.ResultSet;
    }
    
    /**
     * Gets the connection.
     * @return The connection instance.
     */
    public Connection getConnection() {
        try {
            //re-initiates connection if closed.
            if (Connection.isClosed()) {
                initConnection();
            }
            return Connection;
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        //sends empty connection by default.
        return null;
    }
    
}
