package economysimulation.classes.managers.extcon;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

/**
 *
 * @author Max Carter
 */
public class Database {
    
    private static final String HOST = "",
                                USERNAME = "",
                                PASSWORD = "";
    
    private static Connection connection;
    private static Statement statement;
    private static ResultSet resultSet;
    
    public Database() {
        try {
            connection = DriverManager.getConnection(HOST, USERNAME, PASSWORD);
            statement = connection.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
            
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        
    }
    
}
