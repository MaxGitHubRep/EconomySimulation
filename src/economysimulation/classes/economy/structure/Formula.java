package economysimulation.classes.economy.structure;

import economysimulation.classes.economy.budget.Budget;
import economysimulation.classes.economy.budget.BudgetSector;
import economysimulation.classes.economy.sectors.Sector;
import economysimulation.classes.economy.sectors.SectorID;
import economysimulation.classes.global.Methods;
import economysimulation.classes.managers.exception.InvalidPanelSizeException;
import economysimulation.classes.managers.exception.InvalidSectorException;
import economysimulation.classes.managers.popup.hint.HintManager;
import economysimulation.classes.managers.popup.hint.Hints;
import economysimulation.classes.pulse.PulseThread;

/**
 *
 * @author Max Carter
 */
public class Formula extends Component {
    
    //<editor-fold defaultstate="collapsed" desc="Recalculates real GDP."> 
    public static void calculateGDP() {
        GDP = (TOTAL_CONSUMPTION + TOTAL_INVESTMENT + Budget.getPublicSpendingTotal(false) + (EXPORTS - IMPORTS));
    }//</editor-fold>
    
    //<editor-fold defaultstate="collapsed" desc="Recalculates the annual budget."> 
    public static void calculateBudget(boolean yearPast) {
        ANNUAL_BUDGET+= TAXATION;
        TAXATION = 0;
        if (yearPast) {
            TOTAL_INVESTMENT = 0;
            TOTAL_CONSUMPTION = 0;
        }
    }//</editor-fold>
    
    //<editor-fold defaultstate="collapsed" desc="Adjust standard of living, population level and minimum wage influencer.">
    /**
     * Adjust standard of living, population level and minimum wage influencer.
     * 
     * @param sector Sector being used.
     */
    private static void adjustSubInfluence(BudgetSector sector) {
        SOL += sector.getSpendingInfluence() > 0 ? sector.getStandardLivingInfluence() : - sector.getStandardLivingInfluence();
        POPULATION += sector.getSpendingInfluence() > 0 ? sector.getPopulationInfluence() : - sector.getPopulationInfluence();
        WAGE_MULTIPLIER*=sector.getWageInfluence() > 0 ? sector.getWageInfluence() : 1;
    }//</editor-fold>
    
    //<editor-fold defaultstate="collapsed" desc="Uses the budget to adjust economic behaviour.">
    /**
     * Uses the budget to adjust economic behaviour.
     */
    private static void calculateSpendingInfluence() {

        for (int i = 0; i < SpendingInfluence.length-1; i++) {
            if (SpendingInfluence[i] > 0) SpendingInfluence[i]-=0.12;
        }
        
        for (BudgetSector sector : Sector.SectorList) {
            adjustSubInfluence(sector);
        }

        RESOURCE_COST += (Sector.Infrastructure.getSpendingInfluence()== 0 ? 0.1 : -0.2) +
                (Sector.Science.getSpendingInfluence() == 0 ? 0.12 : -0.18);

        if (SOL > 1) {
            SOL = 1;
        } else if (SOL <= 0) {
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

        WAGE_MULTIPLIER = 1;
        if (Methods.TICKS > 14) calculateSpendingInfluence();
        
        IMPORTS = 0;
        RESOURCE_COST = IMPORTS;
        
        WORKERS = POPULATION * ((100 - UNEMPLOYMENT)/100);
        WAGES = (MIN_WAGE * WORKERS * WORK_HOURS_PER_DAY * WAGE_MULTIPLIER);
        INCOME = WAGES;
        D_INCOME = INCOME;
        COST_OF_PRODUCTION = WAGES + RESOURCE_COST;
        
        CONS_CONFIDENCE = SOL * (100-INCOME_TAX)/100;
        CORP_CONFIDENCE = 1 * (100-CORP_TAX)/100;
        
        MPC = ((100 - INTEREST_RATE)/100) * CONS_CONFIDENCE;
        if (MPC == 0) MPC+=0.01;
        
        if (D_INCOME == 0 && TOTAL_SAVINGS >= 0.1) {
            TOTAL_SAVINGS-=0.1;
            D_INCOME+=0.1;
        } else if (TOTAL_SAVINGS < 0.1 && D_INCOME == 0) {
            HintManager.createNewHint(Hints.HINT_CONSUMERS_OUT_OF_MONEY);
        }
        
        TAXED_INCOME = INCOME * (INCOME > 0 && INCOME_TAX > 0 && !TAX_BREAK[1] ? (INCOME_TAX/100) : 0);
        D_INCOME -= TAXED_INCOME;
        
        CONSUMPTION = MPC * ( D_INCOME + Sector.Benefits.getSpendingInfluence() + 0.4 * (!TAX_BREAK[1] ? 1-(INCOME_TAX/100) : 1));
        SAVINGS = (1 - MPC) * ( D_INCOME + Sector.Benefits.getSpendingInfluence());
        
        FIRM_PROFITS = (CONSUMPTION - COST_OF_PRODUCTION);
        
        TAXED_CORP = FIRM_PROFITS * (FIRM_PROFITS > 0 && CORP_TAX > 0 && !TAX_BREAK[0] ? (CORP_TAX/100) : 0);
        FIRM_PROFITS -= TAXED_CORP;
        
        TAXATION += TAXED_CORP + TAXED_INCOME;
        
        INVESTMENT = FIRM_PROFITS > 0 ? FIRM_PROFITS * CORP_CONFIDENCE * 0.75 : 0;
        FIRM_PROFITS -= INVESTMENT;

        if (Sector.Benefits.getSpendingInfluence() > 0) Sector.Benefits.setSpendingInfluence(0);

        TOTAL_TAX += TAXATION;
        TOTAL_CORP_TAX += TAXED_CORP;
        TOTAL_INCOME_TAX += TAXED_INCOME;
        QUARTER_CORP_TAX += TAXED_CORP;
        QUARTER_INCOME_TAX += TAXED_INCOME;
        TOTAL_SAVINGS += SAVINGS;
        TOTAL_CORP_PROFITS += FIRM_PROFITS;
        TOTAL_INVESTMENT += INVESTMENT;
        TOTAL_CONSUMPTION += CONSUMPTION;
               
        if (COST_OF_PRODUCTION > TOTAL_CORP_PROFITS && UNEMPLOYMENT < 100) {
            UNEMPLOYMENT++;
        } else if (UNEMPLOYMENT > 1) {
            UNEMPLOYMENT--;
        }
        
        if (TOTAL_CORP_PROFITS <= 0) HintManager.createNewHint(Hints.HINT_FIRMS_OUT_OF_MONEY);
    }//</editor-fold>

}
