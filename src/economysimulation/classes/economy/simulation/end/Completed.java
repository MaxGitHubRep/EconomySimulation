package economysimulation.classes.economy.simulation.end;

import economysimulation.classes.global.Methods;
import economysimulation.classes.gui.endgame.EndScreen;
import economysimulation.classes.pulse.PulseThread;

/**
 *
 * @author Max Carter
 */
public class Completed {

    public static void simulationCompleted() {
        if (PulseThread.SimulationRunning) {
            PulseThread.SimulationRunning = false;
            Methods.FrameDisplay.addToMainFrame(new EndScreen());
        }
    }
    
}
