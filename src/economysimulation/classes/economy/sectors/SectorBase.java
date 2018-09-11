package economysimulation.classes.economy.sectors;

import static economysimulation.classes.global.Methods.SectorInstance;

/**
 *
 * @author Max Carter
 */
public class SectorBase extends BudgetSector {
       
    public SectorBase(double standardOfLiving, int population, double wageMultiplier) {
        super.standardOfLiving = standardOfLiving;
        super.population = population;
        super.wageMultiplier = wageMultiplier;
    }

    @Override
    public int getSpending() {
        return super.spending;
    }
    
    @Override
    public void addSpending(int value) {
        spendingEventListener(value);
        super.spending += value;
    }

    @Override
    public void setSpending(int value) {
        super.spending = value;
    }

    public void spendingEventListener(int value) {
        for (SectorEvent event : SectorInstance.spendingListeners) {
            event.sectorSpendingEvent(this, value);
        }
    }
    
    @Override
    public double getSpendingInfluence() {
        return super.spendingInfluence;
    }
    
    @Override
    public void addSpendingInfluence(double value) {
        super.spendingInfluence += value;
    }

    @Override
    public void setSpendingInfluence(double value) {
        super.spendingInfluence = value;
    }

    @Override
    public double getStandardLivingInfluence() {
        return super.standardOfLiving;
    }

    @Override
    public int getPopulationInfluence() {
        return super.population;
    }

    @Override
    public double getWageInfluence() {
        return super.wageMultiplier;
    } 

}
