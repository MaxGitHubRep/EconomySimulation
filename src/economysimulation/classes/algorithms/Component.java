package economysimulation.classes.algorithms;

import java.util.ArrayList;

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
    
    //Stores history of rates
    public static ArrayList<Double> INTEREST_RATES = new ArrayList<>();
    public static ArrayList<Double> CONSUMER_TAXES = new ArrayList<>();
    public static ArrayList<Double> CORPORATION_TAXES = new ArrayList<>();
    public static ArrayList<Double> PENSIONS_LIST = new ArrayList<>();
    
    // Budget variables
    public static int[] BUDGET_VARS = new int[8];
    
}
