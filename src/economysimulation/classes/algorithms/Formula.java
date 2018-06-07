package economysimulation.classes.algorithms;

import static economysimulation.classes.algorithms.Component.AUTO_CONS;
import static economysimulation.classes.algorithms.Component.BUDGET_VARS;
import static economysimulation.classes.algorithms.Component.CONSUMPTION;
import static economysimulation.classes.algorithms.Component.DISPOSABLE_INCOME;
import static economysimulation.classes.algorithms.Component.EXPORTS;
import static economysimulation.classes.algorithms.Component.IMPORTS;
import static economysimulation.classes.algorithms.Component.INVESTMENT;
import static economysimulation.classes.algorithms.Component.MPC;
import static economysimulation.classes.algorithms.Component.TAXATION;
import static economysimulation.classes.algorithms.Component.REAL_GDP;
import static economysimulation.classes.algorithms.Component.GDP;
import static economysimulation.classes.algorithms.Component.ANNUAL_BUDGET;
import static economysimulation.classes.algorithms.Component.CORP_CONFIDENCE;
import static economysimulation.classes.algorithms.Component.CORP_TAX;
import static economysimulation.classes.algorithms.Component.COST_OF_PRODUCTION;
import static economysimulation.classes.algorithms.Component.DEMAND;
import static economysimulation.classes.algorithms.Component.FIRM_PROFITS;
import static economysimulation.classes.algorithms.Component.PRICE_PER_UNIT;
import static economysimulation.classes.algorithms.Component.RESOURCE_COST;
import static economysimulation.classes.algorithms.Component.SUPPLY;
import static economysimulation.classes.algorithms.Component.WAGES;

/**
 *
 * @author Max Carter
 */
public class Formula {
    
    /**
    * @param includeTransfer
    *            Return result with transfer payments included (benefits)
    */
    public static int getPublicSpendingTotal(boolean includeTransfer) {
        int value = 0;
        for (int i = 0; i < BUDGET_VARS.length; i++) {
            if (!includeTransfer && i == BUDGET_VARS.length-1) value+=BUDGET_VARS[i];
        }
        return value;
    }
    
    public static void calculateConsumption() {
        CONSUMPTION = AUTO_CONS + (int) (MPC*DISPOSABLE_INCOME);
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
        COST_OF_PRODUCTION = WAGES + RESOURCE_COST;
        FIRM_PROFITS = (int) (((SUPPLY > DEMAND ? DEMAND : SUPPLY ) * PRICE_PER_UNIT) - COST_OF_PRODUCTION) * (int) (CORP_TAX/100);
        
        INVESTMENT = (int) (FIRM_PROFITS * CORP_CONFIDENCE);
        
        
    }
    
}
        
        
