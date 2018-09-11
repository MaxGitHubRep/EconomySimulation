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
            
            CorporationTaxRate = new CompHold(),
            TotalCorporationTaxRevenue = new CompHold(),
            
            TotalSavings = new CompHold(25),
            TotalConsumption = new CompHold(),
            PropensityToConsume = new CompHold(1),
            
            ConsumerConfidence = new CompHold(1),
            CorporationConfidence = new CompHold(1),
            PoliticalInfluence = new CompHold(1),
            StandardOfLiving = new CompHold(1),
            
            CostOfProduction = new CompHold(),
            TotalCorporationProfits = new CompHold(1),
            Investment = new CompHold(),
            WageMultiplier = new CompHold(),
            
            GrossDomesticProduct = new CompHold(),
            TotalTaxation = new CompHold(),
            SpendingBudget = new CompHold(250),
            Population = new CompHold();
            
}
