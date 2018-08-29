package economysimulation.classes.economy;

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
    
    /**
    * @param includeTransfer Return result with transfer payments included (benefits)
    * @return Sum of budget
    */
    public static int getPublicSpendingTotal(boolean includeTransfer) {
        int value = 0;
        for (int i = 0; i < Spending.length; i++) {
            if (!(!includeTransfer && i == Spending.length-1)) {
                value+=Spending[i];
            }
        }
        
        return value;
    }
    
    public static int getSectorSpending(int id) throws InvalidSectorException {
        if (id < 0 || id > Spending.length) {
            throw new InvalidSectorException();
        }
        return Spending[id];
    }
      
    //<editor-fold defaultstate="collapsed" desc="Recalculates real GDP."> 
    public static void calculateGDP() {
        GDP = (TOTAL_CONSUMPTION + TOTAL_INVESTMENT + getPublicSpendingTotal(false) + (EXPORTS - IMPORTS));
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
     * @param sector     Sector making the influence.
     * @param sol        Effect on the standard of living.
     * @param population Effect on the population.
     * @param minwage    Influence on minimum wage.
     */
    private static void adjustSubInfluence(int sector, double sol, double population, double minwage) {
        SOL += SpendingInfluence[sector] > 0 ? sol : - sol;
        POPULATION += SpendingInfluence[sector] > 0 ? population : - population;
        WAGE_MULTIPLIER*=minwage > 0 ? minwage : 1;
    }//</editor-fold>
    
    //<editor-fold defaultstate="collapsed" desc="Uses the budget to adjust economic behaviour.">
    /**
     * Uses the budget to adjust economic behaviour.
     */
    private static void calculateSpendingInfluence() {

        for (int i = 0; i < SpendingInfluence.length-1; i++) {
            if (SpendingInfluence[i] > 0) SpendingInfluence[i]-=0.12;
        }
        
        adjustSubInfluence(Sector.NHS, 0.004, 3, 0);
        adjustSubInfluence(Sector.EDUCATION, 0.003, 0, 1.1);
        adjustSubInfluence(Sector.HOUSING, 0.0015, 2, 0.05);
        adjustSubInfluence(Sector.FOOD, 0.003, 1, 0);
        adjustSubInfluence(Sector.INFRASTRUCTURE, 0.002, 0, 0);
        adjustSubInfluence(Sector.SCIENCE, 0.0015, 3, 0);

        RESOURCE_COST += (Spending[Sector.INFRASTRUCTURE] == 0 ? 0.1 : -0.2) +
                (Spending[Sector.SCIENCE] == 0 ? 0.12 : -0.18);
        
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
        
        CONSUMPTION = MPC * ( D_INCOME + SpendingInfluence[Sector.BENEFITS] + 0.4 * (!TAX_BREAK[1] ? 1-(INCOME_TAX/100) : 1));
        SAVINGS = (1 - MPC) * ( D_INCOME + SpendingInfluence[Sector.BENEFITS]);
        
        FIRM_PROFITS = (CONSUMPTION - COST_OF_PRODUCTION);
        
        TAXED_CORP = FIRM_PROFITS * (FIRM_PROFITS > 0 && CORP_TAX > 0 && !TAX_BREAK[0] ? (CORP_TAX/100) : 0);
        FIRM_PROFITS -= TAXED_CORP;
        
        TAXATION += TAXED_CORP + TAXED_INCOME;
        
        INVESTMENT = FIRM_PROFITS > 0 ? FIRM_PROFITS * CORP_CONFIDENCE * 0.75 : 0;
        FIRM_PROFITS -= INVESTMENT;

        if (SpendingInfluence[Sector.BENEFITS] > 0) SpendingInfluence[Sector.BENEFITS] = 0;

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
