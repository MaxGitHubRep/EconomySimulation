package economysimulation.classes.economy.structure.components;

/**
 *
 * @author Max Carter
 */
public class Comp {

    public static final ComponentType
            InterestRate = new CompHold(),
            
            IncomeTaxRate = new CompHold(),
            TotalIncomeTaxRevenue = new CompHold(),
            DailyIncomeTaxRevenue = new CompHold(),
            
            CorporationTaxRate = new CompHold(),
            TotalCorporationTaxRevenue = new CompHold(),
            DailyCorporationTaxRevenue = new CompHold(),
            
            Savings = new CompHold(),
            TotalSavings = new CompHold(25),
            DisposableIncome = new CompHold(),
            Wages = new CompHold(),
            Consumption = new CompHold(),
            TotalConsumption = new CompHold(),
            PropensityToConsume = new CompHold(1),
            
            ConsumerConfidence = new CompHold(1),
            CorporationConfidence = new CompHold(1),
            PoliticalInfluence = new CompHold(1),
            StandardOfLiving = new CompHold(1),
            
            CostOfProduction = new CompHold(),
            CorporationProfits = new CompHold(),
            TotalCorporationProfits = new CompHold(1),
            Investment = new CompHold(),
            WageMultiplier = new CompHold(),
            
            GrossDomesticProduct = new CompHold(),
            Taxation = new CompHold(),
            SpendingBudget = new CompHold(250),
            Population = new CompHold();
            
}
