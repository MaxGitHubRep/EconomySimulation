package economysimulation.classes.algorithms;

import java.util.ArrayList;

/**
 *
 * @author Max Carter
 */
public class Component {
    
    public static double
            INTEREST_RATE = 0.5, CORP_TAX = 0.5, CONS_TAX = 0.5,
            UNEMPLOYMENT = 0.042, EMPLOYMENT, REAL_GDP, GDP, CPI_BASE = 1, CPI = 1, MPC,
            PRICE_PER_UNIT = 1, CORP_CONFIDENCE = 1;
    
    public static int 
            CONSUMPTION, INVESTMENT, EXPORTS, IMPORTS,
            DISPOSABLE_INCOME, CONS_BORROWING, AUTO_CONS,
            GOV_BORROWING, TAXATION, ANNUAL_BUDGET = 750, FIRM_PROFITS,
            DEMAND, SUPPLY, COST_OF_PRODUCTION, WAGES, RESOURCE_COST;
    
    // Budget variables
    public static int[] BUDGET_VARS = new int[]{ 50, 50, 50, 50, 50, 50, 50, 50 };
    
    public static ArrayList<Double> historyGDP = new ArrayList<>();
    
}
