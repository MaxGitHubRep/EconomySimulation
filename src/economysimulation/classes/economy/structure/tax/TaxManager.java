package economysimulation.classes.economy.structure.tax;

import economysimulation.classes.economy.structure.Component;
import static economysimulation.classes.global.Methods.GameDisplay;
import economysimulation.classes.managers.exception.NonExistentTaxException;

/**
 *
 * @author Max Carter
 */
public class TaxManager {

    public static double getTaxRate(int type) {
        if (type > 1 || type < 0) try {
            throw new NonExistentTaxException(type);
        } catch (NonExistentTaxException ex) {
            ex.printStackTrace();
        }
        
        if (GameDisplay.TaxBreak[type]) return 0;
        return (type == Tax.INCOME ? Component.IncomeTax : Component.CorporationTax)/100;
    }
    
}
