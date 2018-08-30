package economysimulation.classes.economy.sectors;

import economysimulation.classes.economy.budget.Budget;
import economysimulation.classes.economy.budget.BudgetSector;
import economysimulation.classes.economy.structure.Component;
import economysimulation.classes.managers.exception.InvalidSectorException;

/**
 *
 * @author Max Carter
 */
public class Benefits extends BudgetSector {

    public int index;
    
    public Benefits(int index) {
        this.index = index;
    }

    @Override
    public int getSpending() {
        try {
            return Budget.getSectorSpending(index);
        } catch (InvalidSectorException ex) {
            ex.printStackTrace();
        }
        return 0;
    }

    @Override
    public double getSpendingInfluence() {
        return Component.SpendingInfluence[index];
    }
    
    @Override
    public void changeSpendingInfluence(double value) {
        Component.SpendingInfluence[index]+= value;
    }

    @Override
    public void setSpendingInfluence(double value) {
        Component.SpendingInfluence[index] = value;
    }

    @Override
    public double getStandardLivingInfluence() {
        return 0.0015;
    }

    @Override
    public int getPopulationInfluence() {
        return 1;
    }

    @Override
    public double getWageInfluence() {
        return 1.02;
    } 

    @Override
    public int getIndex() {
        return this.index;
    }

}
