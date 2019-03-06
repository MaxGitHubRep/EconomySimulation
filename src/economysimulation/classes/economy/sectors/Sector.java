package economysimulation.classes.economy.sectors;

/**
 * @author Max Carter
 */
public class Sector {
          
    //Local variables that store the sector's 'settings'.
    public int
            spending,
            population;
    public double
            spendingInfluence,
            standardOfLiving,
            wageMultiplier;
     
    /**
     * Creates a new Sector.
     * @param standardOfLiving Influence on the standard of living.
     * @param population       Influence on the population.
     * @param wageMultiplier   Influence on the wage multiplier.
     */
    public Sector(double standardOfLiving, int population, double wageMultiplier) {
        this.standardOfLiving = standardOfLiving;
        this.population = population;
        this.wageMultiplier = wageMultiplier;
    }
    
    /**
     * Gets the total spending of a sector.
     * @return Sector's spending total.
     */
    public int getSpending() {
        return this.spending;
    }
    
    /**
     * Adds a value to the spending total.
     * @param value Value to be added.
     */
    public void addSpending(int value) {
        this.spending += value;
    }

    /**
     * Sets the total spending to a value.
     * @param value New total spending.
     */
    public void setSpending(int value) {
        this.spending = value;
    }
    
    /**
     * Gets the total spending influence of a sector.
     * @return Sector's spending influence.
     */
    public double getSpendingInfluence() {
        return this.spendingInfluence;
    }
    
    /**
     * Adds a value to the current spending influence.
     * @param value Value to be added.
     */
    public void addSpendingInfluence(double value) {
        this.spendingInfluence += value;
    }

    /**
     * Sets the spending influence to a specific value.
     * @param value New spending influence.
     */
    public void setSpendingInfluence(double value) {
        this.spendingInfluence = value;
    }
    
    /**
     * Gets the standard of living influence.
     * @return Standard of living influence.
     */
    public double getStandardLivingInfluence() {
        return this.standardOfLiving;
    }

    /**
     * Gets the population influence.
     * @return Population influence.
     */
    public int getPopulationInfluence() {
        return this.population;
    }

    /**
     * Gets the wage influence.
     * @return Wage influence.
     */
    public double getWageInfluence() {
        return this.wageMultiplier;
    } 

}
