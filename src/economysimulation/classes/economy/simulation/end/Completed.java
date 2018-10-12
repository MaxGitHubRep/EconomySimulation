package economysimulation.classes.economy.simulation.end;

import economysimulation.classes.global.Methods;
import static economysimulation.classes.global.Methods.DBConnector;
import static economysimulation.classes.global.Methods.DBUsers;
import static economysimulation.classes.global.Methods.PulseUpdater;
import economysimulation.classes.gui.endgame.EndScreen;
import economysimulation.classes.gui.frame.MainFrame;
import java.awt.Frame;
import java.text.DecimalFormat;
import economysimulation.classes.managers.extcon.Connection;
import java.sql.SQLException;

/**
 *
 * @author Max Carter
 */
public class Completed {

    public static void simulationCompleted() {

       
        
        try {
            if (Connection.isConnected) {
                if (DBConnector.getConnection().isValid(60)) {
                    if (Methods.UserID == -1) {
                        Methods.UserID = DBUsers.getNextAvailableUserID();
                        DBUsers.createNewUser(Methods.UserID, Methods.Username);
                    }

                } else {
                    Connection.isConnected = false;
                }
            }
            
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        
        if (PulseUpdater.SimulationTicking) {
            PulseUpdater.SimulationTicking = false;
            Methods.FrameDisplay.addToMainFrame(new EndScreen());
        }
        
        for (Frame frame : MainFrame.getFrames()) {
            if (frame.getTitle().contains(": ")) {
                frame.dispose();
            }
        } 
        
    }
    
}
