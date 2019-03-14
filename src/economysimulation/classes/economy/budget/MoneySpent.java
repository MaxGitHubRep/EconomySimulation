package economysimulation.classes.economy.budget;

import economysimulation.classes.economy.sectors.Sector;

/**
 * @author Max Carter
 */
public interface MoneySpent {
    
    /**
     * Called when money is spent on a sector.
     * @param sector Event-sector.
     * @param money How much money is spent.
     */
    public void onMoneySpent(Sector sector, int money);
    
}
