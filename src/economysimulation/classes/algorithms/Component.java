package economysimulation.classes.algorithms;

/**
 *
 * @author Max Carter
 */
public class Component {
    
    public static double
            INTEREST_RATE = 0.5, CORP_TAX = 0.5, CONS_TAX = 0.5,
            UNEMPLOYMENT = 4.2, REAL_GDP, PRICE_LEVEL = 1, MPC;
    public static int 
            CONSUMPTION, INVESTMENT, EXPORTS, IMPORTS,
            DISPOSABLE_INCOME, CONS_BORROWING, AUTO_CONS,
            GOV_BORROWING, TAXATION, ANNUAL_BUDGET = 750;
    
    // Budget variables
    public static int[] BUDGET_VARS = new int[]{ 50, 50, 50, 50, 50, 50, 50, 50 };
    
}
