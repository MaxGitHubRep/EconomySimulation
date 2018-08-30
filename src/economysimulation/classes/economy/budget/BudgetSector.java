package economysimulation.classes.economy.budget;

/**
 *
 * @author Max Carter
 */
public abstract class BudgetSector {
    
    /**
     * 
     * @return The index of the sector.
     */
    public abstract int getIndex();
    
    /**
     * 
     * @return The cumulative balance spent on the respected sector.
     */
    public abstract int getSpending();
    
    /**
     * 
     * @return The remaining influence the spent money has on the sectors.
     */
    public abstract double getSpendingInfluence();
    
    /**
     * Adds {@code value} to the {@code Spending Influence} of the respected sector.
     * 
     * @param value The amount the influence is affected by.
     */
    public abstract void changeSpendingInfluence(double value);
    
    /**
     * Sets the {@code Spending Influence} of the respected sector to {@code value}.
     * 
     * @param value The amount the influence is affected by.
     */
    public abstract void setSpendingInfluence(double value);
    
    /**
     * 
     * @return The influence the sector has on the populations standard of living.
     */
    public abstract double getStandardLivingInfluence();
    
    /**
     * 
     * @return The influence that the sector has on the population size.
     */
    public abstract int getPopulationInfluence();
    
    /**
     * 
     * @return The influence that the sector has on wages.
     */
    public abstract double getWageInfluence();
    
}
