package economysimulation.classes.managers.extcon;

import static economysimulation.classes.global.Methods.DBConnector;
import static economysimulation.classes.global.Methods.DBUsers;
import economysimulation.classes.managers.exception.NonExistentGameException;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Max Carter
 */
public class GameData {
    
    private int totalGames = 0, totalLinks = 0;
    
    public GameData() {
        refreshGamesPlayed();
    }

    public void refreshGamesPlayed() {
        totalGames = 0;
        
        try {
            DBConnector.setResultSet(DBConnector.getStatement().executeQuery("SELECT COUNT(*) FROM mxcrtr_db.Games"));
            
            while (DBConnector.getResultSet().next()) {
                totalGames = DBConnector.getResultSet().getInt(1);
            }
            
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
    
    public void refreshUserGamesLinkCount() {
        totalLinks = 0;
        
        try {
            DBConnector.setResultSet(DBConnector.getStatement().executeQuery("SELECT COUNT(*) FROM mxcrtr_db.LinkUsersGames"));
            
            while (DBConnector.getResultSet().next()) {
                totalLinks = DBConnector.getResultSet().getInt(1);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
    
    public int getGamesPlayed(boolean refreshTable) {
        if (refreshTable) refreshGamesPlayed();
        return this.totalGames;
    }
    
    public int getGamesLinks(boolean refreshTable) {
        if (refreshTable) refreshUserGamesLinkCount();
        return this.totalLinks;
    }
    
    /**
     * Searches the database for information about the game with id of {@code id}.
     * 
     * @param id Index of the game.
     * @return   Package of information about the game.
     */
    public GamePackage getGameDataFromID(int id) {
        try {
            if (getGamesPlayed(false) == 0) {
                throw new NonExistentGameException();
            }
        } catch (NonExistentGameException ex) {
            ex.printStackTrace();
        }

        GamePackage pkg = null;
        System.out.println(id + " : " + getGamesPlayed(false));
        try {
            String SQLStatement = "SELECT * FROM mxcrtr_db.Games WHERE GameID = ?";
            PreparedStatement pt = DBConnector.getConnection().prepareStatement(SQLStatement);
            pt.setInt(1, id);
            
            DBConnector.setResultSet(pt.executeQuery());
            DBConnector.getResultSet().next();
            
            int ticks = DBConnector.getResultSet().getInt("GameTicks");
            int gdp = DBConnector.getResultSet().getInt("GDP");

            double[] comp = new double[9];
            String[] column = new String[]{ "TotalConsumption", "TotalSavings", "TotalInvestment", "TotalTaxation", "TotalFirmProfits", "Population", "ConsumerConfidence", "FirmConfidence", "Unemployment" };
            
            for (int i = 0; i < comp.length; i++) {
                comp[i] = DBConnector.getResultSet().getInt(column[i]);
            }
            // get players
            
            SQLStatement = "SELECT * FROM mxcrtr_db.LinkUsersGames WHERE GameID = ?";
            pt = DBConnector.getConnection().prepareStatement(SQLStatement);
            pt.setInt(1, id);
            
            DBConnector.setResultSet(pt.executeQuery());
            
            List<String> playerList = new ArrayList<>();
            
            while (DBConnector.getResultSet().next()) {
                int userId = DBConnector.getResultSet().getInt("UserID");
                playerList.add(DBUsers.getUsernameFromId(userId) + "#" + new DecimalFormat("00000").format(userId));
                
            }
            
            String[] players = new String[playerList.size()];
            
            for (int i = 0; i < players.length; i++) {
                players[i] = playerList.get(i);
            }
            
            pkg = new GamePackage(id, gdp, ticks, players, comp);
            
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        
        return pkg;
    }
    
    /**
     * Cycles through every game on the database
     * and returns a list of {@code GamePackage}.
     * @return List of all the information about every game.
     */
    public List<GamePackage> getAllGameData() {
        List<GamePackage> gameData = new ArrayList<>();
        refreshGamesPlayed();
        for (int i = 1; i <= getGamesPlayed(false); i++) {
            gameData.add(getGameDataFromID(i));
        }
        return gameData;
    }
    
    /**
     * Creates a new record in the database that
     * links the users and the games.
     * @param gameId Index of the game.
     * @param userId Indexes of the players in the game.
     */
    public void establishUserGameLink(int gameId, int[] userId) {
        try {
            for (int i = 0; i < userId.length; i++) {
                String SQLStatement = "INSERT INTO mxcrtr_db.LinkUsersGames VALUES (?, ?, ?, ?)";
                PreparedStatement pt = DBConnector.getConnection().prepareStatement(SQLStatement);
                
                pt.setInt(1, getGamesLinks(true)+1); //remove use of link ids
                pt.setInt(2, gameId);
                pt.setInt(3, userId[i]);
                pt.setInt(4, userId.length);

                pt.executeUpdate();
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
    
    /**
     * Creates a new record of a game in the database with the args.
     * 
     * @param id            Index of the game.
     * @param score         GDP obtained in the game.
     * @param gameTicks     Amount of ticks pulsated.
     * @param components    Values of all the variables for the database.
     */
    public void createNewGame(int id, int score, int gameTicks, double[] components) {
        try {
            String SQLStatement = "INSERT INTO mxcrtr_db.Games VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement pt = DBConnector.getConnection().prepareStatement(SQLStatement);
            pt.setInt(1, id);
            pt.setInt(2, score);
            pt.setInt(3, gameTicks);
            
            for (int i = 0; i < components.length; i++) {
                pt.setDouble(i + 4, components[i]);
            }
            
            pt.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
    
}
