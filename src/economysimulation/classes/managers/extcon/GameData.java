package economysimulation.classes.managers.extcon;

import static economysimulation.classes.global.Methods.DBConnector;
import static economysimulation.classes.global.Methods.DBUsers;
import economysimulation.classes.global.User;
import economysimulation.classes.managers.exception.NonExistentGameException;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Max Carter
 */
public class GameData {
    
    /** Local amount of games played. */
    private int totalGames = 0;
    
    /** Creates new GameData. */
    public GameData() {
        refreshGamesPlayed();
    }

    /** Updates the amount of games played. */
    public void refreshGamesPlayed() {
        totalGames = 0;
        
        try {
            //counts all ggames stored.
            DBConnector.setResultSet(DBConnector.getStatement().executeQuery("SELECT COUNT(*) FROM mxcrtr_db.Games"));
            
            if (DBConnector.getResultSet().next()) {
                totalGames = DBConnector.getResultSet().getInt(1);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    /**
     * Returns the amount of games played.
     * @param refreshTable When set to true, this will get the total games from
     *                     the database.When set to false it will use the local
     *                      saved amount of games.
     * @return Amount of games played.
     */
    public int getGamesPlayed(boolean refreshTable) {
        if (refreshTable) refreshGamesPlayed();
        return this.totalGames;
    }
    
    /**
     * Searches the database for information about the game with id of {@code id}.
     * 
     * @param id Index of the game.
     * @return   Package of information about the game.
     */
    public GamePackage getGameDataFromID(int id) {
        //throws error if there are no games played.
        try {
            if (getGamesPlayed(false) == 0) {
                throw new NonExistentGameException();
            }
        } catch (NonExistentGameException ex) {
            ex.printStackTrace();
        }

        //create empty game package.
        GamePackage pkg = null;
        try {
            //selects game data from database with id.
            String SQLStatement = "SELECT * FROM mxcrtr_db.Games WHERE GameID = ?";
            PreparedStatement pt = DBConnector.getConnection().prepareStatement(SQLStatement);
            pt.setInt(1, id);
            
            DBConnector.setResultSet(pt.executeQuery());
            DBConnector.getResultSet().next();
            
            int ticks = DBConnector.getResultSet().getInt("GameTicks");
            int gdp = DBConnector.getResultSet().getInt("GDP");

            double[] comp = new double[9];
            String[] column = new String[]{ "TotalConsumption", "TotalSavings", "TotalInvestment", "TotalTaxation", "TotalFirmProfits", "Population", "ConsumerConfidence", "FirmConfidence", "Unemployment" };
            
            //gets data from result set and adds to local list.
            for (int i = 0; i < comp.length; i++) {
                comp[i] = DBConnector.getResultSet().getInt(column[i]);
            }
            //gets players who participate in the game.
            
            SQLStatement = "SELECT UserID FROM mxcrtr_db.LinkUsersGames WHERE GameID = ?";
            pt = DBConnector.getConnection().prepareStatement(SQLStatement);
            pt.setInt(1, id);
            
            DBConnector.setResultSet(pt.executeQuery());
            
            List<User> userList = new ArrayList<>();
            
            //adds all users to new list.
            while (DBConnector.getResultSet().next()) {
                User user = new User();
                user.setID(DBConnector.getResultSet().getInt("UserID"));
                userList.add(user);
            }
            
            for (int i = 0; i < userList.size(); i++) {
                userList.get(i).setName(DBUsers.getUsernameFromId(userList.get(i).getID()));
            }
            
            //updates empty game package.
            pkg = new GamePackage(id, gdp, ticks, userList, comp);
            
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
        int gamesPlayed = getGamesPlayed(true);
        //loops through every game.
        for (int i = 0; i < gamesPlayed; i++) {
            gameData.add(getGameDataFromID(i+1));
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
            //loops through every user in the list and creates link.
            for (int i = 0; i < userId.length; i++) {
                String SQLStatement = "INSERT INTO mxcrtr_db.LinkUsersGames VALUES (?, ?, ?)";
                PreparedStatement pt = DBConnector.getConnection().prepareStatement(SQLStatement);
                
                pt.setInt(1, gameId);
                pt.setInt(2, userId[i]);
                pt.setInt(3, userId.length);

                pt.executeUpdate();
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
    
    /**
     * Creates a new record of a game in the database with the args.
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
