package economysimulation.classes.managers.extcon;

import static economysimulation.classes.global.Methods.DBConnector;
import economysimulation.classes.managers.exception.NonExistentGameException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Max Carter
 */
public class GameData {
    
    private int totalGames = 0;
    
    public GameData() {
        refreshGamesPlayed();
    }

    public void refreshGamesPlayed() {
        totalGames = 0;
        
        try {
            DBConnector.ResultSet = DBConnector.Statement.executeQuery("SELECT * FROM mxcrtr_db.Games");
            
            while (DBConnector.ResultSet.next()) {
                totalGames++;
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
    
    public int getGamesPlayed() {
        refreshGamesPlayed();
        return this.totalGames;
    }
    
    public List<String> getGameDataFromID(int id) {
        try {
            if (totalGames == 0) {
                throw new NonExistentGameException();
                
            } else if (totalGames+1 < id) {
                throw new NonExistentGameException(id);
            }
        } catch (NonExistentGameException ex) {
            ex.printStackTrace();
        }
        
        List<String> data = new ArrayList<>();
        
        try {
            DBConnector.ResultSet = DBConnector.Statement.executeQuery("SELECT * FROM mxcrtr_db.Games WHERE GameID EQUALS " + id); //psuedo statement
            
            for (String column : new String[]{ "Rank", "GameTicks", "Score", "TotalConsumption", "TotalSavings", "Population", "Unemployment", "ConsumerConfidence", "TotalInvestment", "TotalTaxation", "TotalFirmProfits", "FirmConfidence" }) {
                data.add(DBConnector.ResultSet.getString(column));
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        
        return data;
    }
    
}
