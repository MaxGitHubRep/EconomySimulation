package economysimulation.classes.economy.simulation.end;

import economysimulation.classes.economy.structure.Component;
import economysimulation.classes.global.Methods;
import static economysimulation.classes.global.Methods.DBConnector;
import static economysimulation.classes.global.Methods.DBGames;
import static economysimulation.classes.global.Methods.DBUsers;
import static economysimulation.classes.global.Methods.GameDisplay;
import static economysimulation.classes.global.Methods.PulseUpdater;
import economysimulation.classes.gui.endgame.EndScreen;
import economysimulation.classes.gui.frame.MainFrame;
import java.awt.Frame;
import economysimulation.classes.managers.extcon.Connection;
import economysimulation.classes.managers.popup.hint.HintManager;
import economysimulation.classes.managers.popup.hint.hints.Hint;
import economysimulation.classes.managers.popup.hint.hints.HintCauseOfCompletion;
import java.sql.SQLException;

/**
 * @author Max Carter
 */
public class Completed {

    public static class DBComponents extends Component {
        
        /**
         * Gets a list of all the components used in the simulation.
         * @return List of components.
         */
        public static double[] getDBComponents() {
            return new double[]{ TotalConsumption, TotalSavings, Investment, (TotalCorporationTax + TotalIncomeTax), TotalCorporationProfits,
                Population, ConsumerConfidence*100, CorporationConfidence*100, Unemployment
            };
        }
        
    }
    
    /**
     * Resets the simulation specifically for multiplayer purposes.
     * @param causeOfCompletion How the simulation ended.
     */
    public static void simulationCompletedMP(String causeOfCompletion) {
        //resets the simulation with the reset reason.
        reset(causeOfCompletion);
        
        //validates that the user has an ID and is the 'party captain'.
        if (Methods.getUser().getID() == Methods.LobbyHandler.getUsersInParty(Methods.localPartyId).get(0).getID()) {
            //defines new game id and creates game.
            int gameId = DBGames.getGamesPlayed(true)+1;
            DBGames.createNewGame(gameId, (int) Math.floor(Component.GrossDomesticProduct), GameDisplay.Ticks, DBComponents.getDBComponents());
            
            //creates list of users in party.
            int[] party = new int[Methods.LobbyHandler.getLocalParty().size()];
            for (int i = 0; i < party.length; i++) {
                party[i] = Methods.LobbyHandler.getLocalParty().get(i).getID();
            }
            
            //creates link between game the id and the party.
            DBGames.establishUserGameLink(gameId, party);
            Methods.StorageConnection.removePackagesFromParty(Methods.localPartyId);
        }
    }
    
    /**
     * Ends the simulation and sends scores to database.
     * @param causeOfCompletion The reason behind why the simulation was ended.
     */
    public static void simulationCompleted(String causeOfCompletion) {
        //resets the simulation with the reset reason.
        reset(causeOfCompletion);
        
        try {
            //validation to make sure the user is online.
            //if the user isn't online, nothing needs to happen.
            if (Connection.isConnected) {
                if (DBConnector.getConnection().isValid(60)) {
                    //creates new user id if they don't already have one.
                    if (Methods.getUser().getID() == -1) {
                        Methods.getUser().setID(DBUsers.getNextAvailableUserID());
                        DBUsers.createNewUser(Methods.getUser());
                    }
                    //save game to database.
                    int gameId = DBGames.getGamesPlayed(true)+1;
                    DBGames.createNewGame(gameId, (int) Math.floor(Component.GrossDomesticProduct), GameDisplay.Ticks, DBComponents.getDBComponents());
                    DBGames.establishUserGameLink(gameId, new int[]{ Methods.getUser().getID() });

                } else {
                    //updates local connection variable to false.
                    Connection.isConnected = false;
                }
            }
            
            
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
    
    /** Ends the simulation and outputs the cause of completion. */
    private static void reset(String causeOfCompletion) {
        //stops the somulation pulse thread.
        if (PulseUpdater.SimulationTicking) {
            PulseUpdater.SimulationTicking = false;
            Methods.FrameDisplay.addToMainFrame(new EndScreen());
        }
        
        //closes all open frames.
        for (Frame frame : MainFrame.getFrames()) {
            if (frame.getTitle().contains(": ")) {
                frame.dispose();
            }
        } 
        
        //creates a new hint to display the cause of completion.
        Hint hint = new HintCauseOfCompletion(causeOfCompletion);
        
        if (causeOfCompletion != null) {
            HintManager.createHint(hint);
        }
    }
    
}
