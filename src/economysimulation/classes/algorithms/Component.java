package economysimulation.classes.algorithms;

import java.util.ArrayList;

/**
 *
 * @author Max Carter
 */
public class Component {
    
    public static double
            INTEREST_RATE = 0.5, CORP_TAX = 0.5, CONS_TAX = 0.5,
            UNEMPLOYMENT = 4.2, EMPLOYMENT, REAL_GDP, GDP, CPI_BASE = 1, CPI = 1, MPC,
            PRICE_PER_UNIT = 1, CORP_CONFIDENCE = 1;
    
    public static int 
            CONSUMPTION, INVESTMENT, EXPORTS, IMPORTS,
            DISPOSABLE_INCOME, CONS_BORROWING, AUTO_CONS,
            GOV_BORROWING, TAXATION, ANNUAL_BUDGET = 750, FIRM_PROFITS,
            DEMAND, SUPPLY, COST_OF_PRODUCTION, WAGES, RESOURCE_COST;
    
    // Budget variables
    public static int[] BUDGET_VARS = new int[]{ 50, 50, 50, 50, 50, 50, 50, 50 };
    
    public static ArrayList<Double> historyGDP = new ArrayList<>();
    
    
    
    
    /**
    * @param includeTransfer
    *            Return result with transfer payments included (benefits)
    * @return Sum of budget
    */
    public static int getPublicSpendingTotal(boolean includeTransfer) {
        int value = 0;
        for (int i = 0; i < BUDGET_VARS.length; i++) {
            if (!(!includeTransfer && i == BUDGET_VARS.length-1)) {
                value+=BUDGET_VARS[i];
            }
        }
        
        return value;
    }
    
    //NHS, Education, Transport, Food, Infrastructure, Defence, Science, Benefits
    public static int getPublicSpendingSector(int id) {
        return BUDGET_VARS[id];
    }
    
    public static void calculateConsumption() {
        CONSUMPTION = AUTO_CONS + (int) (MPC*DISPOSABLE_INCOME) * (1 - (int) (CONS_TAX/100));
    }
      
    //<editor-fold defaultstate="collapsed" desc="Recalculates real GDP."> 
    public static void calculateGDP() {
        GDP = (CONSUMPTION + INVESTMENT + getPublicSpendingTotal(false) + (EXPORTS - IMPORTS));
    }//</editor-fold>
    
    //<editor-fold defaultstate="collapsed" desc="Recalculates the annual budget."> 
    public static void calculateAnnualBudget() {
        ANNUAL_BUDGET = TAXATION - getPublicSpendingTotal(true);
    }//</editor-fold>
    
    public static void calculateComponents() {
        
        EMPLOYMENT = 100 - UNEMPLOYMENT;
        
        WAGES = 
        RESOURCE_COST = IMPORTS;
        
        COST_OF_PRODUCTION = WAGES + RESOURCE_COST;
        //((SUPPLY > DEMAND ? DEMAND : SUPPLY ) * PRICE_PER_UNIT)
        FIRM_PROFITS = (int) (CONSUMPTION - COST_OF_PRODUCTION) * (1 - (int) (CORP_TAX/100));
        
        INVESTMENT = (int) (FIRM_PROFITS * CORP_CONFIDENCE); //corp confidence can be between 0 to 1 (double)
        
        
    }
    
    public static void calcComp() {

        
    }
    
}
