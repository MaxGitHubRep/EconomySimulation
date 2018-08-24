package economysimulation.classes.economy;

import economysimulation.classes.managers.exception.InvalidPanelSizeException;
import economysimulation.classes.managers.exception.InvalidSectorException;
import economysimulation.classes.managers.popup.hint.HintManager;
import economysimulation.classes.managers.popup.hint.Hints;

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
            YEARLY_INCOME_TAX = 0;
            YEARLY_CORP_TAX = 0;
            TOTAL_INVESTMENT = 0;
            TOTAL_CONSUMPTION = 0;
        }
    }//</editor-fold>
    
    private static double getConsConfidence() {

        return SOL;
    }
    
    //<editor-fold defaultstate="collapsed" desc="Uses the budget to adjust economic behaviour.">
    /**
     * Uses the budget to adjust economic behaviour.
     */
    private static void calculateSpendingInfluence() {

        for (int i = 0; i < SpendingInfluence.length-1; i++) {
            if (SpendingInfluence[i] > 0) SpendingInfluence[i]--;
        }
        
 
        //Testing way to change standard of living & population control.
        if (SpendingInfluence[Sector.NHS] == 0) {
            SOL-=0.002;
            POPULATION+=3;
        } else {
            POPULATION-=3;
            SOL+=0.004;
        }
        if (SOL > 1) SOL = 1;
        if (SOL < 0) SOL = 0;
        /**links:
         * NHS: sol, population
         * Education: sol, min_wage
         * Housing: population, sol
         * Food: dy sol
         * Infrastructure: sol, cop
         * Defence: new var: defence
         * Science: cop
         */
    }//</editor-fold>

    public static void calculateComponents() throws InvalidSectorException, InvalidPanelSizeException {

        OLD_SOL = SOL;
        calculateSpendingInfluence();
       
        IMPORTS = 0;
        RESOURCE_COST = IMPORTS;
        
        WORKERS = POPULATION * ((100 - UNEMPLOYMENT)/100);
        WAGES = (MIN_WAGE * WORKERS * WORK_HOURS_PER_DAY);
        INCOME = WAGES;
        D_INCOME = INCOME;
        COST_OF_PRODUCTION = WAGES + RESOURCE_COST;
        
        FIRM_PROFITS = (CONSUMPTION - COST_OF_PRODUCTION);

        TAXED_CORP = FIRM_PROFITS * (FIRM_PROFITS > 0 && CORP_TAX > 0 && !TAX_BREAK[0] ? (CORP_TAX/100) : 0);
        TAXED_INCOME = INCOME * (INCOME > 0 && INCOME_TAX > 0 && !TAX_BREAK[1] ? (INCOME_TAX/100) : 0);
        
        FIRM_PROFITS -= TAXED_CORP;
        D_INCOME -= TAXED_INCOME;
        TAXATION += TAXED_CORP + TAXED_INCOME;

        CORP_CONFIDENCE = 1;
        CONS_CONFIDENCE = getConsConfidence();
        
        if (D_INCOME == 0 && TOTAL_SAVINGS >= 0.1) {
            TOTAL_SAVINGS-=0.1;
            D_INCOME+=0.1;
        } else if (TOTAL_SAVINGS < 0.1 && D_INCOME == 0) {
            HintManager.createNewHint(Hints.CONSUMERS_OUT_OF_MONEY);
        }
        
        MPC = ((100 - INTEREST_RATE)/100) * CONS_CONFIDENCE;
        CONSUMPTION = MPC * ( D_INCOME + CONS_INJECTION + 0.4 * (!TAX_BREAK[1] ? 1-(INCOME_TAX/100) : 1));
        SAVINGS = (1 - MPC) * ( D_INCOME + CONS_INJECTION);
        
        if (CONS_INJECTION > 0) CONS_INJECTION = 0;

        INVESTMENT = (FIRM_PROFITS - COST_OF_PRODUCTION) > 0 ? (FIRM_PROFITS - COST_OF_PRODUCTION) * CORP_CONFIDENCE : 0;
        FIRM_PROFITS -= INVESTMENT;
        
        OLD_PI = POLITICAL_INFLUENCE;
        POLITICAL_INFLUENCE = MPC; //temp
        
        TOTAL_TAX += TAXATION;
        TOTAL_CORP_TAX += TAXED_CORP;
        TOTAL_INCOME_TAX += TAXED_INCOME;
        QUARTER_CORP_TAX += TAXED_CORP;
        QUARTER_INCOME_TAX += TAXED_INCOME;
        YEARLY_CORP_TAX += TAXED_CORP;
        YEARLY_INCOME_TAX += INCOME_TAX;
        TOTAL_SAVINGS += SAVINGS;
        TOTAL_CORP_PROFITS += FIRM_PROFITS;
        TOTAL_INVESTMENT += INVESTMENT;
        TOTAL_CONSUMPTION += CONSUMPTION;
               
        if (COST_OF_PRODUCTION > TOTAL_CORP_PROFITS && UNEMPLOYMENT < 100) {
            UNEMPLOYMENT++;
        } else if (UNEMPLOYMENT > 1) {
            UNEMPLOYMENT--;
        }
        
    }

    
}
