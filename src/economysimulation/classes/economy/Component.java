package economysimulation.classes.economy;

import economysimulation.classes.managers.exception.InvalidSectorException;
import java.util.ArrayList;

/**
 *
 * @author Max Carter
 */
public class Component {
    
    public static double
            INTEREST_RATE = 0.5, CORP_TAX = 0, INCOME_TAX = 0,
            UNEMPLOYMENT, REAL_GDP, GDP, MPC, SAVINGS = 0.0001, INCOME,
            CORP_CONFIDENCE = 1, CONS_CONFIDENCE = 1, CONS_INJECTION,
            MIN_WAGE = 0.000000008, POPULATION = 1000000, WORKERS,
            MONEY_TO_SPEND, 
            CONSUMPTION, INVESTMENT, EXPORTS, IMPORTS, WORK_HOURS_PER_DAY = 8,
            TAXATION, ANNUAL_BUDGET = 250, FIRM_PROFITS,
            TOTAL_CORP_PROFITS = 25, TOTAL_INVESTMENT, TOTAL_CONSUMPTION, 
            COST_OF_PRODUCTION, WAGES, RESOURCE_COST, DISPOSABLE_INCOME;
    
    //variables that make up gdp will need a "current" variable, and a "total" variable (latter for gdp count)
    
    // Budget variables
    public static int[] BUDGET_VARS = new int[]{ 0, 0, 0, 0, 0, 0, 0, 0 };
    
    public static ArrayList<Double> historyGDP = new ArrayList<>();
    public static int quarterIndex = 0;
    
    // ----------
    //  FORMULAS
    // ----------
    
    
    /**
    * @param includeTransfer Return result with transfer payments included (benefits)
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
    public static int getSectorSpending(int id) throws InvalidSectorException {
        if (id < 0 || id > BUDGET_VARS.length) {
            throw new InvalidSectorException();
        }
        return BUDGET_VARS[id];
    }
      
    //<editor-fold defaultstate="collapsed" desc="Recalculates real GDP."> 
    public static void calculateGDP() {
        GDP = (TOTAL_CONSUMPTION + TOTAL_INVESTMENT + getPublicSpendingTotal(false) + (EXPORTS - IMPORTS));
    }//</editor-fold>
    
    //<editor-fold defaultstate="collapsed" desc="Recalculates the annual budget."> 
    public static void calculateBudget(boolean yearPast) {
        ANNUAL_BUDGET+= TAXATION - MONEY_TO_SPEND;
        MONEY_TO_SPEND = 0;
        TAXATION = 0;
        if (yearPast) {
            TOTAL_INVESTMENT = 0;
            TOTAL_CONSUMPTION = 0;
        }
    }//</editor-fold>
    
    private static double getConsConfidence() {
        double confidence = 1;
        
        //if (BUDGET_VARS[0])
        
        return confidence;
    }

    public static void calcComp() throws InvalidSectorException {

        IMPORTS = 0;
        RESOURCE_COST = IMPORTS;
        
        WAGES = (MIN_WAGE * WORKERS * WORK_HOURS_PER_DAY);
        INCOME = WAGES;
        COST_OF_PRODUCTION = WAGES + RESOURCE_COST;
        
        if (COST_OF_PRODUCTION > TOTAL_CORP_PROFITS && UNEMPLOYMENT < 99) {
            UNEMPLOYMENT++;
        } else if (UNEMPLOYMENT > 1) {
            UNEMPLOYMENT--;
        }
        
        WORKERS = POPULATION * ((100 - UNEMPLOYMENT)/100);
        
        FIRM_PROFITS = ((CONSUMPTION - COST_OF_PRODUCTION));
        
        double corpTax = FIRM_PROFITS * (FIRM_PROFITS > 0 ? (CORP_TAX/100) : 0),
               incTax = INCOME * (INCOME > 0 ? (INCOME_TAX/100) : 0);
        
        FIRM_PROFITS -= corpTax;
        INCOME -= incTax;
        TAXATION += corpTax + incTax;

        if (INCOME < (0.00001*POPULATION) && SAVINGS > (0.00001*POPULATION)) {
            INCOME+=(0.00001*POPULATION);
            SAVINGS-=(0.00001*POPULATION);
        }
        
        CORP_CONFIDENCE = getPublicSpendingTotal(true) > ANNUAL_BUDGET ? ANNUAL_BUDGET / getPublicSpendingTotal(true) : 1;
        CONS_CONFIDENCE = getConsConfidence();
        
        MPC = ((100 - INTEREST_RATE)/100) * CONS_CONFIDENCE;
        CONSUMPTION = MPC * ( INCOME + (CONS_INJECTION > 0 ? CONS_INJECTION : 0));
        SAVINGS = (1 - MPC) * ( INCOME + (CONS_INJECTION > 0 ? CONS_INJECTION : 0));
        if (CONS_INJECTION > 0) CONS_INJECTION = 0;

        INVESTMENT = (FIRM_PROFITS - COST_OF_PRODUCTION) > 0 ? (FIRM_PROFITS - COST_OF_PRODUCTION) * CORP_CONFIDENCE : 0;
        //FIRM_PROFITS -= INVESTMENT;
        
        TOTAL_CORP_PROFITS += FIRM_PROFITS;
        TOTAL_INVESTMENT += INVESTMENT;
        TOTAL_CONSUMPTION += CONSUMPTION;
                
    }
    
}
