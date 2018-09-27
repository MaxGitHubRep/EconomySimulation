package economysimulation.classes.economy.structure;

import economysimulation.classes.economy.budget.Budget;
import economysimulation.classes.economy.sectors.BudgetSector;
import static economysimulation.classes.global.Methods.GameDisplay;
import economysimulation.classes.pulse.GamePulse;
import economysimulation.classes.economy.sectors.SectorEvent;
import economysimulation.classes.economy.simulation.end.Completed;
import static economysimulation.classes.global.Methods.SectorInstance;
import economysimulation.classes.managers.events.EventManager;

/**
 *
 * @author Max Carter
 */
public class Formula extends Component implements GamePulse, SectorEvent {
    
    /**
     * Creates new Formula class.
     */
    public Formula() {
        SectorInstance.addSectorEventListener(this);
    }
    
    //<editor-fold defaultstate="collapsed" desc="Recalculates real GDP."> 
    public void calculateGDP() {
        GrossDomesticProduct = (TotalConsumption + Investment + Budget.getPublicSpendingTotal(false));
    }//</editor-fold>
    
    //<editor-fold defaultstate="collapsed" desc="Recalculates the annual budget."> 
    public void calculateBudget() {
        SpendingBudget+= Taxation;
        Taxation = 0;
    }//</editor-fold>
    
    //<editor-fold defaultstate="collapsed" desc="Uses the budget to adjust economic behaviour.">
    /**
     * Uses the budget to adjust economic behaviour.
     */
    private void calculateSpendingInfluence() {

        for (BudgetSector sector : SectorInstance.SectorList) {
            if (sector.getSpendingInfluence() > 0) sector.addSpendingInfluence(-0.12);
        }

        for (BudgetSector sector : SectorInstance.SectorList) {
            StandardOfLiving += sector.getSpendingInfluence() > 0 ? sector.getStandardLivingInfluence() : - sector.getStandardLivingInfluence();
            Population += sector.getSpendingInfluence() > 0 ? sector.getPopulationInfluence() : - sector.getPopulationInfluence();
            WageMultiplier*=sector.getWageInfluence() > 0 ? sector.getWageInfluence() : 1;
        }
        
        CostOfProduction = (SectorInstance.Infrastructure.getSpendingInfluence() == 0 ? 0.1 : -0.1) +
                (SectorInstance.Science.getSpendingInfluence() == 0 ? 0.1 : -0.1);

        if (StandardOfLiving > 1) {
            StandardOfLiving = 1;
        } else if (StandardOfLiving <= 0 || PoliticalInflluence <= 0) {
            Completed.simulationCompleted();
            
        }
    }//</editor-fold>

    @Override
    public void gamePulseEvent() {
        WageMultiplier = 1;
        CostOfProduction = 0;
        
        if (GameDisplay.Ticks > 14) calculateSpendingInfluence();

        PoliticalInflluence = ConsumerConfidence * CorporationConfidence * (100-Unemployment)/100;
        
        if (EventManager.eventId < 7 && SectorInstance.SectorList[EventManager.eventId].getSpendingInfluence() == 0) {
            EventManager.delay-=0.01;
            PoliticalInflluence*=EventManager.delay;
        }
    }

    @Override
    public void sectorSpendingEvent(BudgetSector sector, int value) {
        if (sector.equals(SectorInstance.Benefits)) {
            TotalConsumption += value * (!GameDisplay.TaxBreak[1] ? 1-(Component.IncomeTax/100) : 1);
            TotalSavings += (1 - PropensityToConsume) * value;
        }
    }

}
