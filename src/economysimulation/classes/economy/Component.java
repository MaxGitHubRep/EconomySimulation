package economysimulation.classes.economy;

import economysimulation.classes.managers.exception.InvalidPanelSizeException;
import economysimulation.classes.managers.exception.InvalidSectorException;
import economysimulation.classes.managers.popup.hint.HintManager;
import economysimulation.classes.managers.popup.hint.Hints;
import java.util.ArrayList;

/**
 *
 * @author Max Carter
 */
public class Component {
    
    public static double
            INTEREST_RATE = 0.5, CORP_TAX = 0, INCOME_TAX = 0,
            UNEMPLOYMENT, REAL_GDP, GDP, MPC, SAVINGS = 50, INCOME,
            CORP_CONFIDENCE = 1, CONS_CONFIDENCE = 1, CONS_INJECTION,
            MIN_WAGE = 0.000000008, POPULATION = 1000000, WORKERS,
            D_INCOME, TOTAL_SAVINGS, 
            CONSUMPTION, INVESTMENT, EXPORTS, IMPORTS, WORK_HOURS_PER_DAY = 8,
            TAXATION, ANNUAL_BUDGET = 250, FIRM_PROFITS,
            TOTAL_CORP_PROFITS, TOTAL_INVESTMENT, TOTAL_CONSUMPTION, TOTAL_TAX,
            TOTAL_CORP_TAX, TOTAL_INCOME_TAX, QUARTER_CORP_TAX, QUARTER_INCOME_TAX,
            YEARLY_INCOME_TAX, YEARLY_CORP_TAX, 
            COST_OF_PRODUCTION, WAGES, RESOURCE_COST, DISPOSABLE_INCOME,
            TAXED_CORP, TAXED_INCOME;
    
    //variables that make up gdp will need a "current" variable, and a "total" variable (latter for gdp count)
    
    // Budget variables
    public static int[] BUDGET_VARS = new int[]{ 0, 0, 0, 0, 0, 0, 0, 0 };
    
    public static ArrayList<Double> historyGDP = new ArrayList<>();
    public static int quarterIndex = 0;
    
    public static boolean[] TAX_BREAK = new boolean[]{ false, false };
    
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
        ANNUAL_BUDGET+= TAXATION;
        TAXATION = 0;
        if (yearPast) {
            YEARLY_INCOME_TAX = 0;
            YEARLY_CORP_TAX = 0;
            TOTAL_INVESTMENT = 0;
            TOTAL_CONSUMPTION = 0;
        }
    }//</editor-fold>
    
    private static double getConsConfidence() {
        double confidence = 1;
        
        //if (BUDGET_VARS[0])
        
        return confidence;
    }

    public static void calcComp() throws InvalidSectorException, InvalidPanelSizeException {

        IMPORTS = 0;
        RESOURCE_COST = IMPORTS;
        
        WORKERS = POPULATION * ((100 - UNEMPLOYMENT)/100);
        WAGES = (MIN_WAGE * WORKERS * WORK_HOURS_PER_DAY);
        INCOME = WAGES;
        D_INCOME = INCOME;
        COST_OF_PRODUCTION = WAGES + RESOURCE_COST;
        
        FIRM_PROFITS = (CONSUMPTION - COST_OF_PRODUCTION);

        TAXED_CORP = FIRM_PROFITS * (FIRM_PROFITS > 0 && CORP_TAX > 0 && !TAX_BREAK[0] ? (CORP_TAX/100) : 0);
        TAXED_INCOME = INCOME * (INCOME > 0 && INCOME_TAX > 0 && !TAX_BREAK[1] ? (INCOME_TAX/100) : 0);
        
        FIRM_PROFITS -= TAXED_CORP;
        D_INCOME -= TAXED_INCOME;
        TAXATION += TAXED_CORP + TAXED_INCOME;

        CORP_CONFIDENCE = 1;
        CONS_CONFIDENCE = getConsConfidence();
        
        if (D_INCOME == 0 && TOTAL_SAVINGS >= 0.1) {
            TOTAL_SAVINGS-=0.1;
            D_INCOME+=0.1;
        } else if (TOTAL_SAVINGS < 0.1 && D_INCOME == 0) {
            HintManager.createNewHint(Hints.CONSUMERS_OUT_OF_MONEY);
        }
        
        MPC = ((100 - INTEREST_RATE)/100) * CONS_CONFIDENCE;
        CONSUMPTION = MPC * ( D_INCOME + CONS_INJECTION + 0.4 * (!TAX_BREAK[1] ? 1-(INCOME_TAX/100) : 1));
        SAVINGS = (1 - MPC) * ( D_INCOME + CONS_INJECTION);
        
        if (CONS_INJECTION > 0) CONS_INJECTION = 0;

        INVESTMENT = (FIRM_PROFITS - COST_OF_PRODUCTION) > 0 ? (FIRM_PROFITS - COST_OF_PRODUCTION) * CORP_CONFIDENCE : 0;
        FIRM_PROFITS -= INVESTMENT;
        
        
        TOTAL_TAX += TAXATION;
        TOTAL_CORP_TAX += TAXED_CORP;
        TOTAL_INCOME_TAX += TAXED_INCOME;
        QUARTER_CORP_TAX += TAXED_CORP;
        QUARTER_INCOME_TAX += TAXED_INCOME;
        YEARLY_CORP_TAX += TAXED_CORP;
        YEARLY_INCOME_TAX += INCOME_TAX;
        TOTAL_SAVINGS += SAVINGS;
        TOTAL_CORP_PROFITS += FIRM_PROFITS;
        TOTAL_INVESTMENT += INVESTMENT;
        TOTAL_CONSUMPTION += CONSUMPTION;
               
        if (COST_OF_PRODUCTION > TOTAL_CORP_PROFITS && UNEMPLOYMENT < 99) {
            UNEMPLOYMENT++;
        } else if (UNEMPLOYMENT > 1) {
            UNEMPLOYMENT--;
        }
        
    }
    
}
