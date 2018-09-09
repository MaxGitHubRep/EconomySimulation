package economysimulation.classes.economy.budget;

import economysimulation.classes.economy.sectors.Sector;
import economysimulation.classes.economy.structure.Component;
import economysimulation.classes.gui.mainpanels.hold.BudgetHold;
import economysimulation.classes.managers.exception.InvalidSectorException;

/**
 *
 * @author Max Carter
 */
public class Budget extends Component {
    
    /**
     * Spends £{@code value} billion into {@code sector}.
     * 
     * @param sector
     * @param money 
     */
    public static void spendMoney(int sector, int money) {
        SpendingBudget -= money;
        Sector.getSector(sector).addSpending(money);
        Sector.getSector(sector).addSpendingInfluence((double) money);
        BudgetHold.displaySpendingGraph();
    }

    /**
    * @param includeTransfer Return result with transfer payments included (benefits)
    * @return Sum of budget
    */
    public static int getPublicSpendingTotal(boolean includeTransfer) {
        int value = 0, index = 0;
        
        for (BudgetSector sector : Sector.SectorList) {
            if (!(!includeTransfer && index == 7)) {
                value+= sector.getSpending();
            }
            index++;
        }
        
        return value;
    }
}
