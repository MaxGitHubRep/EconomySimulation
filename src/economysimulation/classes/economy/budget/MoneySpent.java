package economysimulation.classes.economy.budget;

import economysimulation.classes.economy.sectors.BudgetSector;

/**
 *
 * @author Max Carter
 */
public interface MoneySpent {
    
    public void onMoneySpent(BudgetSector sector, int money);
    
}
