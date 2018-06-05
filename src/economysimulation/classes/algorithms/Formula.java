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

/**
 *
 * @author Max Carter
 */
public class Formula {
    
    public static int getPublicSpendingTotal() {
        int value = 0;
        for (int i = 0; i < BUDGET_VARS.length; i++) {
            value+=BUDGET_VARS[i];
        }
        return value;
    }
    
    public static void calculateConsumption() {
        CONSUMPTION = AUTO_CONS + (int) (MPC*DISPOSABLE_INCOME); //Consumption = Autonomous Spending + (Marginal Propensity to Consume * Disposable Income)
    }
      
    //<editor-fold defaultstate="collapsed" desc="Recalculates real GDP."> 
    public static void calculateGDP() {
        GDP = (CONSUMPTION + INVESTMENT + getPublicSpendingTotal() + (EXPORTS - IMPORTS));
    }//</editor-fold>
    
    //<editor-fold defaultstate="collapsed" desc="Recalculates the annual budget."> 
    public static void calculateAnnualBudget() {
        ANNUAL_BUDGET = TAXATION - getPublicSpendingTotal();
    }//</editor-fold>
    
}
