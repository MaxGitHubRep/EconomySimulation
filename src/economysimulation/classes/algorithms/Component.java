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
            MIN_WAGE = 0.000008, POPULATION = 60, WORKERS,
            
            CONSUMPTION, INVESTMENT, EXPORTS, IMPORTS,
            DISPOSABLE_INCOME, CONS_BORROWING, AUTO_CONS,
            GOV_BORROWING, TAXATION, ANNUAL_BUDGET = 750, FIRM_PROFITS,
            DEMAND, SUPPLY, COST_OF_PRODUCTION, WAGES, RESOURCE_COST;
    
    // Budget variables
    public static int[] BUDGET_VARS = new int[]{ 50, 50, 50, 50, 50, 50, 50, 50 };
    
    public static ArrayList<Double> historyGDP = new ArrayList<>();
    
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
    
    public static void calculateConsumption() {

        CONSUMPTION = (AUTO_CONS + (MPC*DISPOSABLE_INCOME)) * (1 - (CONS_TAX/100));
    }
      
    //<editor-fold defaultstate="collapsed" desc="Recalculates real GDP."> 
    public static void calculateGDP() {
        GDP = (CONSUMPTION + INVESTMENT + getPublicSpendingTotal(false) + (EXPORTS - IMPORTS));
    }//</editor-fold>
    
    //<editor-fold defaultstate="collapsed" desc="Recalculates the annual budget."> 
    public static void calculateAnnualBudget() {
        ANNUAL_BUDGET = TAXATION - getPublicSpendingTotal(true);
    }//</editor-fold>
    
    public static void calcComp() {

        //FIRMS AND COP
        IMPORTS = 0;
        RESOURCE_COST = IMPORTS;
        COST_OF_PRODUCTION = (MIN_WAGE * WORKERS * 8) + RESOURCE_COST - (BUDGET_VARS[6]*1000) - (BUDGET_VARS[4]*1000);
        
        EMPLOYMENT = 100 - UNEMPLOYMENT;
        
        WORKERS = POPULATION * (EMPLOYMENT/100);
        
        
        FIRM_PROFITS = (CONSUMPTION - COST_OF_PRODUCTION) * (1 - (CORP_TAX/100));
        
        
        
        INVESTMENT = FIRM_PROFITS > 0 ? FIRM_PROFITS * CORP_CONFIDENCE : 0;
        

        
        //WAGES & CONSUMERS
        
        
        DISPOSABLE_INCOME = (WAGES * (1 - CONS_TAX/100));
        
        MPC = ((100 - INTEREST_RATE)/100) * CONS_CONFIDENCE;
        
        System.out.println("MPC: " + MPC + ", DI: " + DISPOSABLE_INCOME + ", CONS: " + CONSUMPTION);
        System.out.println("FP: " + FIRM_PROFITS + ", wag: " + WAGES + ", Inv: " + INVESTMENT);
        
        CONSUMPTION = MPC * ( DISPOSABLE_INCOME + BUDGET_VARS[7] );
        
        
    }
    
}
