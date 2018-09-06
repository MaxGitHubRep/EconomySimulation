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
            TotalSavings = new CompHold(),
            DisposableIncome = new CompHold(),
            Wages = new CompHold(),
            Consumption = new CompHold(),
            TotalConsumption = new CompHold(),
            PropensityToConsume = new CompHold(),
            
            ConsumerConfidence = new CompHold(),
            CorporationConfidence = new CompHold(),
            PoliticalInfluence = new CompHold(),
            StandardOfLiving = new CompHold(),
            
            CostOfProduction = new CompHold(),
            FirmProfits = new CompHold(),
            TotalFirmProfits = new CompHold(),
            Investment = new CompHold(),
            WageMultiplier = new CompHold(),
            
            GrossDomesticProduct = new CompHold(),
            Taxation = new CompHold(),
            SpendingBudget = new CompHold(),
            Population = new CompHold();
            
}
