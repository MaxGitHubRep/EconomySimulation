package economysimulation.classes.global;

import economysimulation.classes.economy.budget.BudgetSector;
import economysimulation.classes.economy.sectors.Sector;
import economysimulation.classes.economy.structure.components.ComponentType;
import economysimulation.classes.gui.fronter.GameHold;

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
        Methods.Ticks = 0;
        GameHold.TicksPerQuarter = 0;
        GameHold.Speed = 0;
    }
}
