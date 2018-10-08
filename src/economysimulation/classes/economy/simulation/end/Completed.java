package economysimulation.classes.economy.simulation.end;

import economysimulation.classes.global.Methods;
import static economysimulation.classes.global.Methods.PulseUpdater;
import economysimulation.classes.gui.endgame.EndScreen;
import economysimulation.classes.gui.frame.MainFrame;
import java.awt.Frame;
import java.text.DecimalFormat;
import static economysimulation.classes.global.Methods.DBConnector;

/**
 *
 * @author Max Carter
 */
public class Completed {

    public static void simulationCompleted() {

        if (Methods.UserID.equals("")) {
            Methods.UserID = new DecimalFormat("00000").format(DBConnector.isConnected() ? Methods.DBUsers.getNextAvailableUserID() : Methods.randomInt(0, 99999));
            Methods.Username+= "#" + Methods.UserID;
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
