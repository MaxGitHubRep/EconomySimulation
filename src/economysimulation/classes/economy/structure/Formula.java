package economysimulation.classes.economy.structure;

import economysimulation.classes.economy.budget.Budget;
import economysimulation.classes.economy.budget.MoneySpent;
import economysimulation.classes.economy.sectors.Sector;
import static economysimulation.classes.global.Methods.GameDisplay;
import economysimulation.classes.pulse.GamePulse;
import economysimulation.classes.economy.simulation.end.Completed;
import economysimulation.classes.economy.structure.tax.Tax;
import economysimulation.classes.economy.structure.tax.TaxManager;
import economysimulation.classes.global.Methods;
import static economysimulation.classes.global.Methods.SectorInstance;
import economysimulation.classes.managers.events.EventManager;
import economysimulation.classes.mode.Mode;

/**
 * @author Max Carter
 */
public class Formula extends Component implements GamePulse, MoneySpent {
    
    /** List of possible completion causations. */
    private static final String[] CAUSES_OF_COMPLETION = new String[]{
        "Standard of Living below 0%.",
        "Political Influence below 0%.",
        "Employment hit 0%.",
        "Consumer Support hit 0%.",
        "Corporation Support hit 0%."
    };
    
    /** Creates new Formula class. */
    public Formula() {
        Budget.addMoneySpentListener(this);
    }
    
    /** Calculates the GDP of the economy. */
    public void calculateGDP() {
        GrossDomesticProduct = (TotalConsumption + Investment + Budget.getPublicSpendingTotal(false))*1000;
    }
    
    /** Calculates the budget of the economy. */
    public void calculateBudget() {
        SpendingBudget+= Taxation;
        Taxation = 0;
    }
    
    /** Uses the budget to adjust economic behaviour.*/
    private void calculateSpendingInfluence() {
        //loops through the sectors and adjusts the spending influences.
        for (Sector sector : SectorInstance.SectorList) {
            if (sector.getSpendingInfluence() >= 0.1) {
                sector.addSpendingInfluence(-0.1);
            } else {
                sector.setSpendingInfluence(0);
            }
            StandardOfLiving += sector.getSpendingInfluence() > 0 ? sector.getStandardLivingInfluence() : - sector.getStandardLivingInfluence();
            Population += sector.getSpendingInfluence() > 0 ? sector.getPopulationInfluence() : - sector.getPopulationInfluence();
            WageMultiplier *= sector.getWageInfluence() > 0 ? sector.getWageInfluence() : 1;
        }
        
        //adjusts cost of production.
        CostOfProduction = (SectorInstance.Infrastructure.getSpendingInfluence() <= 0 ? 0.09 : -0.09) +
                (SectorInstance.Science.getSpendingInfluence() <= 0 ? 0.05 : -0.05);

        if (StandardOfLiving > 1) StandardOfLiving = 1;
        
        //checks if any variables are below 0 -> end the simulation.
        int index = 0;
        for (double component : new double[]{ StandardOfLiving, PoliticalInflluence, 100-Unemployment, ConsumerConfidence, CorporationConfidence }) {
            if (component <= 0 && Methods.PulseUpdater.SimulationTicking) {
                if (Methods.ModeHandler.isMode(Mode.MULTI_PLAYER)){
                    Completed.simulationCompletedMP(CAUSES_OF_COMPLETION[index]);
                } else {
                    Completed.simulationCompleted(CAUSES_OF_COMPLETION[index]);
                }
                return;
            }
            index++;
        }
    }

    @Override
    public void onGamePulseEvent() {
        WageMultiplier = 1;
        CostOfProduction = 0;
        
        //ccalculates the political influence.
        PoliticalInflluence = ConsumerConfidence * CorporationConfidence * (100-Unemployment)/100;
        
        //drops the political influence if the event is not paid off.
        if (EventManager.eventId < 7 && SectorInstance.SectorList[EventManager.eventId].getSpendingInfluence() <= 5) {
            EventManager.delay+=0.01;
            PoliticalInflluence-= EventManager.delay;
        }
        
        //waits at least 14 in-game days before calculating anything.
        if (GameDisplay.Ticks > 14) calculateSpendingInfluence();

    }
    
    @Override
    public void onMoneySpent(Sector sector, int money) {
        if (sector.equals(SectorInstance.Benefits)) {
            //adds benefits payment to total consumption.
            TotalConsumption += money * (1-(TaxManager.getTaxRate(Tax.INCOME)));
        }
    }

}
