package economysimulation.classes.economy.structure;

import java.util.ArrayList;

/**
 *
 * @author Max Carter
 */
public class Component {
    
    public static double
        InterestRate = 0, CorporationTax = 0, IncomeTax = 0,
        Unemployment, GrossDomesticProduct, PropensityToConsume = 1, Savings,
        CorporationConfidence = 1, ConsumerConfidence = 1,
        Population = 1000000,
        DisposableIncome, TotalSavings = 25, PoliticalInflluence = 1,
        Consumption, Investment,
        Taxation, SpendingBudget = 250, CorporationProfits,
        TotalCorporationProfits = 1, TotalConsumption,
        TotalCorporationTax, TotalIncomeTax,
        CostOfProduction, Wages,
        DailyCorporationTax, DailyIncomeTax, StandardOfLiving = 1, WageMultiplier = 1;

    public static ArrayList<Double> historyGDP = new ArrayList<>();
    
    public static boolean[] TaxBreak = new boolean[]{ false, false };
    
}
