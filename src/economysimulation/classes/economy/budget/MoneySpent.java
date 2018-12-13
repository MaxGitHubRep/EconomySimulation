package economysimulation.classes.economy.budget;

import economysimulation.classes.economy.sectors.Sector;

/**
 *
 * @author Max Carter
 */
public interface MoneySpent {
    
    public void onMoneySpent(Sector sector, int money);
    
}
