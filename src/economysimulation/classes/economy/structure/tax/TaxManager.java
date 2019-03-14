package economysimulation.classes.economy.structure.tax;

import economysimulation.classes.economy.structure.Component;
import static economysimulation.classes.global.Methods.GameDisplay;

/**
 * @author Max Carter
 */
public class TaxManager {

    /**
     * Gets the rate which the tax is set at.
     * @param tax Type of tax.
     * @return Tax rate.
     */
    public static double getTaxRate(Tax tax) {
        //returns to 0% if the tax is 'frozen'.
        if (GameDisplay.TaxBreak[tax.getID()]) return 0;
        return (tax.getName().equals("Income") ? Component.IncomeTax : Component.CorporationTax)/100;
    }
    
}
