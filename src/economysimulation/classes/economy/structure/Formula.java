package economysimulation.classes.economy.structure;

import economysimulation.classes.economy.budget.Budget;
import economysimulation.classes.economy.sectors.BudgetSector;
import economysimulation.classes.managers.popup.hint.HintManager;
import economysimulation.classes.managers.popup.hint.Hints;
import economysimulation.classes.pulse.PulseThread;
import static economysimulation.classes.global.Methods.GameDisplay;
import economysimulation.classes.pulse.GamePulse;
import economysimulation.classes.economy.sectors.SectorEvent;
import static economysimulation.classes.global.Methods.SectorInstance;

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
    public void calculateBudget(boolean yearPast) {
        SpendingBudget+= Taxation;
        Taxation = 0;
        if (yearPast) {
            Investment = 0;
            TotalConsumption = 0;
        }
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
        } else if (StandardOfLiving <= 0) {
            // game thread ends.
            PulseThread.SimulationRunning = false;
        }
    }//</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="Uses the budget to adjust economic behaviour.">
    /**
     * Source of all components being calculated in one thread.
     */
    public void calculateComponents() {

        Wages = (0.000000064 * (Population * ((100 - Unemployment)/100)) * WageMultiplier);
        DisposableIncome = Wages;
        CostOfProduction+= Wages;
        
        ConsumerConfidence = StandardOfLiving * (100-IncomeTax)/100;
        CorporationConfidence = 1 * (100-CorporationTax)/100;
        
        PropensityToConsume = ((100 - InterestRate)/100) * ConsumerConfidence;
        if (PropensityToConsume == 0) PropensityToConsume+=0.01;
        
        if (DisposableIncome == 0 && TotalSavings >= 0.1) {
            TotalSavings-=0.1;
            DisposableIncome+=0.1;
        } else if (TotalSavings < 0.1 && DisposableIncome == 0) {
            HintManager.createHint(Hints.ConsumersBankrupt);
        }
        
        DailyIncomeTax = Wages * (Wages > 0 && IncomeTax > 0 && !GameDisplay.TaxBreak[1] ? (IncomeTax/100) : 0);
        DisposableIncome -= DailyIncomeTax;
        
        Consumption = PropensityToConsume * ( DisposableIncome + 0.4 * (!GameDisplay.TaxBreak[1] ? 1-(IncomeTax/100) : 1));
        Savings = (1 - PropensityToConsume) * DisposableIncome;
        
        CorporationProfits = (Consumption - CostOfProduction);
        
        DailyCorporationTax = CorporationProfits * (CorporationProfits > 0 && CorporationTax > 0 && !GameDisplay.TaxBreak[0] ? (CorporationTax/100) : 0);
        CorporationProfits -= DailyCorporationTax;
        
        Taxation += DailyCorporationTax + DailyIncomeTax;
        
        double investment = CorporationProfits > 0 ? CorporationProfits * CorporationConfidence * 0.75 : 0;
        Investment+= investment;
        CorporationProfits -= investment;
        
        TotalCorporationTax += DailyCorporationTax;
        TotalIncomeTax += DailyIncomeTax;
        TotalSavings += Savings;
        TotalCorporationProfits += CorporationProfits;
        TotalConsumption += Consumption;
               
        if (CostOfProduction > TotalCorporationProfits && Unemployment < 100) {
            Unemployment++;
        } else if (Unemployment > 1) {
            Unemployment--;
        }
        
        PoliticalInflluence = ConsumerConfidence * CorporationConfidence * (100-Unemployment)/100;
        
        if (TotalCorporationProfits <= 0) HintManager.createHint(Hints.CorporationBankrupt);
    }//</editor-fold>

    @Override
    public void gamePulseEvent() {
        WageMultiplier = 1;
        CostOfProduction = 0;
        
        if (GameDisplay.Ticks > 14) calculateSpendingInfluence();
        //consumer
        //firms
        calculateComponents();

        calculateBudget(false);
    }

    @Override
    public void sectorSpendingEvent(BudgetSector sector, int value) {
        if (sector.equals(SectorInstance.Benefits)) {
            TotalConsumption += value * (!GameDisplay.TaxBreak[1] ? 1-(IncomeTax/100) : 1);
            TotalSavings += (1 - PropensityToConsume) * value;
        }
    }

}
