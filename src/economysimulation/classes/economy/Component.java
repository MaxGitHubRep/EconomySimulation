package economysimulation.classes.economy;

import java.util.ArrayList;

/**
 *
 * @author Max Carter
 */
public class Component {
    
    public static double
        INTEREST_RATE = 0.5, CORP_TAX = 0, INCOME_TAX = 0,
        UNEMPLOYMENT, REAL_GDP, GDP, MPC = 1, SAVINGS = 50, INCOME,
        CORP_CONFIDENCE = 1, CONS_CONFIDENCE = 1, CONS_INJECTION,
        MIN_WAGE = 0.000000008, POPULATION = 1000000, WORKERS,
        D_INCOME, TOTAL_SAVINGS, POLITICAL_INFLUENCE = 1, OLD_PI = 0,
        CONSUMPTION, INVESTMENT, EXPORTS, IMPORTS, WORK_HOURS_PER_DAY = 8,
        TAXATION, ANNUAL_BUDGET = 250, FIRM_PROFITS,
        TOTAL_CORP_PROFITS, TOTAL_INVESTMENT, TOTAL_CONSUMPTION, TOTAL_TAX,
        TOTAL_CORP_TAX, TOTAL_INCOME_TAX, QUARTER_CORP_TAX, QUARTER_INCOME_TAX,
        YEARLY_INCOME_TAX, YEARLY_CORP_TAX, 
        COST_OF_PRODUCTION, WAGES, RESOURCE_COST, DISPOSABLE_INCOME,
        TAXED_CORP, TAXED_INCOME, SOL = 1, OLD_SOL;
    
    // Budget variables
    public static int[] Spending = new int[]{
        0, 0, 0, 0, 0, 0, 0, 0
    };
    
    public static int[] SpendingInfluence = new int[]{
        0, 0, 0, 0, 0, 0, 0, 0
    };
    
    public static ArrayList<Double> historyGDP = new ArrayList<>();
    public static int quarterIndex = 0;
    
    public static boolean[] TAX_BREAK = new boolean[]{ false, false };
    
}
