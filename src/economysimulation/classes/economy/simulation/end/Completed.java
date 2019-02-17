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
        reset(causeOfCompletion);
        if (Methods.getUser().getID() == Methods.LobbyHandler.getUsersInParty(Methods.localPartyId).get(0).getID()) {
            int gameId = DBGames.getGamesPlayed(true)+1;
            DBGames.createNewGame(gameId, (int) Math.floor(Component.GrossDomesticProduct), GameDisplay.Ticks, DBComponents.getDBComponents());
            int[] party = new int[Methods.LobbyHandler.getLocalParty().size()];
            for (int i = 0; i < party.length; i++) {
                party[i] = Methods.LobbyHandler.getLocalParty().get(i).getID();
            }
            
            DBGames.establishUserGameLink(gameId, party);
            Methods.StorageConnection.removePackagesFromParty(Methods.localPartyId);
        }
    }
    
    /**
     * Ends the simulation and sends scores to database.
     * @param causeOfCompletion The reason behind why the simulation was ended.
     */
    public static void simulationCompleted(String causeOfCompletion) {
        reset(causeOfCompletion);
        
        try {
            if (Connection.isConnected) {
                if (DBConnector.getConnection().isValid(60)) {
                    if (Methods.getUser().getID() == -1) {
                        Methods.getUser().setID(DBUsers.getNextAvailableUserID());
                        DBUsers.createNewUser(Methods.getUser());
                    }
                    int gameId = DBGames.getGamesPlayed(true)+1;
                    DBGames.createNewGame(gameId, (int) Math.floor(Component.GrossDomesticProduct), GameDisplay.Ticks, DBComponents.getDBComponents());
                    DBGames.establishUserGameLink(gameId, new int[]{ Methods.getUser().getID() });

                } else {
                    Connection.isConnected = false;
                }
            }
            
            
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
    
    /** Ends the simulation and outputs the cause of completion. */
    private static void reset(String causeOfCompletion) {
        if (PulseUpdater.SimulationTicking) {
            PulseUpdater.SimulationTicking = false;
            Methods.FrameDisplay.addToMainFrame(new EndScreen());
        }
        
        for (Frame frame : MainFrame.getFrames()) {
            if (frame.getTitle().contains(": ")) {
                frame.dispose();
            }
        } 
        
        Hint hint = new HintCauseOfCompletion(causeOfCompletion);
        
        if (causeOfCompletion != null) {
            HintManager.createHint(hint);
        }
    }
    
}
