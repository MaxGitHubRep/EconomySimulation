package economysimulation.classes.economy.budget;

import economysimulation.classes.economy.sectors.Sector;
import static economysimulation.classes.global.Methods.SectorInstance;
import economysimulation.classes.economy.structure.Component;
import java.util.ArrayList;
import java.util.List;


/**
 *
 * @author Max Carter
 */
public class Budget extends Component {
    
    private static List<MoneySpent> listeners = new ArrayList<>();
    
    public static void addMoneySpentListener(MoneySpent listener) {
        listeners.add(listener);
    }
    
    /**
     * Spends £{@code value} billion into {@code sector}.
     * 
     * @param sector
     * @param money 
     */
    public static void spendMoney(Sector sector, int money) {
        SpendingBudget -= money;
        sector.addSpending(money);
        sector.addSpendingInfluence((double) money);
        listeners.forEach((listener) -> {
            listener.onMoneySpent(sector, money);
        });
    }

    /**
    * @param includeTransfer Return result with transfer payments included (benefits)
    * @return Sum of budget
    */
    public static int getPublicSpendingTotal(boolean includeTransfer) {
        int value = 0, index = 0;
        
        for (Sector sector : SectorInstance.SectorList) {
            if (!(!includeTransfer && index == 7)) {
                value+= sector.getSpending();
            }
            index++;
        }
        
        return value;
    }
}
