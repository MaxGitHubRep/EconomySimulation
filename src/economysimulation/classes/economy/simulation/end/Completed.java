package economysimulation.classes.economy.simulation.end;

import economysimulation.classes.global.Methods;
import static economysimulation.classes.global.Methods.PulseUpdater;
import economysimulation.classes.gui.endgame.EndScreen;

/**
 *
 * @author Max Carter
 */
public class Completed {

    public static void simulationCompleted() {
        if (PulseUpdater.SimulationTicking) {
            PulseUpdater.SimulationTicking = false;
            Methods.FrameDisplay.addToMainFrame(new EndScreen());
        }
    }
    
}
