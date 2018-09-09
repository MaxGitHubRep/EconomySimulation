package economysimulation.classes.global;

import economysimulation.classes.economy.budget.BudgetSector;
import economysimulation.classes.economy.sectors.Sector;
import economysimulation.classes.economy.structure.components.ComponentType;
import static economysimulation.classes.global.Methods.GameDisplay;
import economysimulation.classes.pulse.PulseThread;

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
        //Applies default values to all components.
        for (ComponentType comp : new ComponentType[]{  }) {
            comp.applyDefaultValue();
        }
        
        //Sets all public spending to 0.
        for (BudgetSector sector : Sector.SectorList) {
            sector.setSpending(0);
        }
        
        //Resets game variables.
        GameDisplay.Ticks = 0;
        GameDisplay.TicksPerQuarter = 0;
        GameDisplay.Speed = GameDisplay.SPEED_MID_POINT;
        
        if (PulseThread.IS_RUNNING) PulseThread.IS_RUNNING = false;
    }
}
