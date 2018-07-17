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
            PRICE_PER_UNIT = 1, CORP_CONFIDENCE = 1, CONS_CONFIDENCE = 1,
            MIN_WAGE = 0.000000008, POPULATION = 100000000, WORKERS,
            
            CONSUMPTION, INVESTMENT, EXPORTS, IMPORTS,
            DISPOSABLE_INCOME, CONS_BORROWING, AUTO_CONS,
            GOV_BORROWING, TAXATION, ANNUAL_BUDGET = 750, FIRM_PROFITS,
            DEMAND, SUPPLY, COST_OF_PRODUCTION, WAGES, RESOURCE_COST;
    
    // Budget variables
    public static int[] BUDGET_VARS = new int[]{ 50, 50, 50, 50, 50, 50, 50, 50 };
    
    public static ArrayList<Double> historyGDP = new ArrayList<>();
    public static int quarterIndex = 0;
    
    // ----------
    //  FORMULAS
    // ----------
    
    
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
      
    //<editor-fold defaultstate="collapsed" desc="Recalculates real GDP."> 
    public static void calculateGDP() {
        GDP = (CONSUMPTION + INVESTMENT + getPublicSpendingTotal(false) + (EXPORTS - IMPORTS));
    }//</editor-fold>
    
    //<editor-fold defaultstate="collapsed" desc="Recalculates the annual budget."> 
    public static void calculateAnnualBudget() {
        //ANNUAL_BUDGET = TAXATION - getPublicSpendingTotal(true);
    }//</editor-fold>
    
    private static double getConsConfidence() {
        double confidence = 1;
        
        confidence = confidence * (EMPLOYMENT/100);//change in gdp/growth
        
        return confidence;
    }
    
    public static void calcComp() {

        //FIRMS AND COP
        IMPORTS = 0;
        RESOURCE_COST = IMPORTS;
        
        COST_OF_PRODUCTION = (MIN_WAGE * WORKERS * 8) + RESOURCE_COST;
        
        EMPLOYMENT = 100 - UNEMPLOYMENT;
        
        WORKERS = POPULATION * (EMPLOYMENT/100);
        
        FIRM_PROFITS = ((CONSUMPTION - COST_OF_PRODUCTION) * (FIRM_PROFITS > 0 ? 1 - (CORP_TAX/100) : 1))/365;

        if (COST_OF_PRODUCTION > CONSUMPTION) {
            UNEMPLOYMENT++;
        } else {
            EMPLOYMENT++;
        }
        
        CORP_CONFIDENCE = getPublicSpendingTotal(true) > ANNUAL_BUDGET ? ANNUAL_BUDGET / getPublicSpendingTotal(true) : 1;
        CONS_CONFIDENCE = getConsConfidence();
        
        INVESTMENT = FIRM_PROFITS * CORP_CONFIDENCE;

        MPC = ((100 - INTEREST_RATE)/100) * CONS_CONFIDENCE;
        
        System.out.println("MPC: " + MPC + ", CONS: " + CONSUMPTION + ", COP: " + COST_OF_PRODUCTION);
        System.out.println("FP: " + FIRM_PROFITS + ", wag: " + WAGES + ", Inv: " + INVESTMENT);
        
        CONSUMPTION = MPC * ( (MIN_WAGE * 8 * POPULATION) + BUDGET_VARS[7] ) * (1 - (CONS_TAX/100));
        
        
    }
    
}
