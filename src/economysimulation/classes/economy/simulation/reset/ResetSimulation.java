package economysimulation.classes.economy.simulation.reset;

import economysimulation.classes.economy.sectors.BudgetSector;
import static economysimulation.classes.global.Methods.GameDisplay;
import static economysimulation.classes.global.Methods.PulseUpdater;
import static economysimulation.classes.global.Methods.SectorInstance;
/**
 *
 * @author Max Carter
 */
public class ResetSimulation {
    
    /**
     * Resets the simulation.
     */
    public static void resetSimulation() {
        resetSimulationVariables();

        //Garbage collection.
        System.gc();
    }
    
    private static void resetSimulationVariables() {

        //Sets all public spending to 0.
        for (BudgetSector sector : SectorInstance.SectorList) {
            sector.setSpending(0);
        }
        
        //Resets game variables.
        GameDisplay.Ticks = 0;
        GameDisplay.TicksPerQuarter = 0;
        GameDisplay.Speed = GameDisplay.SPEED_MID_POINT;
        
        if (PulseUpdater.SimulationRunning) PulseUpdater.SimulationRunning = false;
    }
}
