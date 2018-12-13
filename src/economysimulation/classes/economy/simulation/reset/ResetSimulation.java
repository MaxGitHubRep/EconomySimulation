package economysimulation.classes.economy.simulation.reset;

import economysimulation.classes.economy.sectors.Sector;
import economysimulation.classes.economy.structure.Component;
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
        for (Sector sector : SectorInstance.SectorList) {
            sector.setSpending(0);
            sector.setSpendingInfluence(0);
        }
        
        Component.SpendingBudget = 250;
        Component.CorporationConfidence = 1;
        Component.ConsumerConfidence = 1;
        Component.PoliticalInflluence = 1;
        Component.StandardOfLiving = 1;
        Component.Unemployment = 0;
        Component.GrossDomesticProduct = 0;
        Component.TotalConsumption = 0;
        Component.TotalCorporationProfits = 0;
        Component.TotalCorporationTax = 0;
        Component.TotalIncomeTax = 0;
        Component.TotalSavings = 25;
        Component.InterestRate = 0;
        Component.CorporationTax = 0;
        Component.IncomeTax = 0;
        Component.Population = 1000000;
        Component.PropensityToConsume = 1;
        
        //Resets game variables.
        GameDisplay.Ticks = 0;
        GameDisplay.TicksPerQuarter = 0;
        GameDisplay.Speed = GameDisplay.SPEED_MID_POINT;
        
        if (PulseUpdater.SimulationTicking) PulseUpdater.SimulationTicking = false;
    }
}
