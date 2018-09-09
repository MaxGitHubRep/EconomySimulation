package economysimulation.classes.economy.structure;

import economysimulation.classes.economy.budget.Budget;
import economysimulation.classes.economy.budget.BudgetSector;
import economysimulation.classes.economy.sectors.Sector;
import economysimulation.classes.managers.exception.InvalidPanelSizeException;
import economysimulation.classes.managers.exception.InvalidSectorException;
import economysimulation.classes.managers.popup.hint.HintManager;
import economysimulation.classes.managers.popup.hint.Hints;
import economysimulation.classes.pulse.PulseThread;
import static economysimulation.classes.global.Methods.GameDisplay;

/**
 *
 * @author Max Carter
 */
public class Formula extends Component {
    
    //<editor-fold defaultstate="collapsed" desc="Recalculates real GDP."> 
    public static void calculateGDP() {
        GrossDomesticProduct = (TotalConsumption + Investment + Budget.getPublicSpendingTotal(false));
    }//</editor-fold>
    
    //<editor-fold defaultstate="collapsed" desc="Recalculates the annual budget."> 
    public static void calculateBudget(boolean yearPast) {
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
    private static void calculateSpendingInfluence() {

        for (BudgetSector sector : Sector.SectorList) {
            if (sector.getSpendingInfluence() > 0) sector.addSpendingInfluence(-0.12);
        }

        for (BudgetSector sector : Sector.SectorList) {
            StandardOfLiving += sector.getSpendingInfluence() > 0 ? sector.getStandardLivingInfluence() : - sector.getStandardLivingInfluence();
            Population += sector.getSpendingInfluence() > 0 ? sector.getPopulationInfluence() : - sector.getPopulationInfluence();
            WageMultiplier*=sector.getWageInfluence() > 0 ? sector.getWageInfluence() : 1;
        }
        
        CostOfProduction = (Sector.Infrastructure.getSpendingInfluence() == 0 ? 0.1 : -0.1) +
                (Sector.Science.getSpendingInfluence() == 0 ? 0.1 : -0.1);

        if (StandardOfLiving > 1) {
            StandardOfLiving = 1;
        } else if (StandardOfLiving <= 0) {
            // game thread ends.
            PulseThread.IS_RUNNING = false;
        }
    }//</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="Uses the budget to adjust economic behaviour.">
    /**
     * Source of all components being calculated in one thread.
     * 
     * @throws InvalidSectorException    When a sector reference is incorrect.
     * @throws InvalidPanelSizeException When the size of a requested hint doesn't fit in the frame.
     */
    public static void calculateComponents() throws InvalidSectorException, InvalidPanelSizeException {

        WageMultiplier = 1;
        CostOfProduction = 0;
        
        if (GameDisplay.Ticks > 14) calculateSpendingInfluence();

        Wages = (0.000000008 * (Population * ((100 - Unemployment)/100)) * 8 * WageMultiplier);
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
        
        Consumption = PropensityToConsume * ( DisposableIncome + Sector.Benefits.getSpendingInfluence() + 0.4 * (!GameDisplay.TaxBreak[1] ? 1-(IncomeTax/100) : 1));
        Savings = (1 - PropensityToConsume) * ( DisposableIncome + Sector.Benefits.getSpendingInfluence());
        
        CorporationProfits = (Consumption - CostOfProduction);
        
        DailyCorporationTax = CorporationProfits * (CorporationProfits > 0 && CorporationTax > 0 && !GameDisplay.TaxBreak[0] ? (CorporationTax/100) : 0);
        CorporationProfits -= DailyCorporationTax;
        
        Taxation += DailyCorporationTax + DailyIncomeTax;
        
        double investment = CorporationProfits > 0 ? CorporationProfits * CorporationConfidence * 0.75 : 0;
        Investment+= investment;
        CorporationProfits -= investment;

        if (Sector.Benefits.getSpendingInfluence() > 0) Sector.Benefits.setSpendingInfluence(0);

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

}
