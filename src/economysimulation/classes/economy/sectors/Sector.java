package economysimulation.classes.economy.sectors;

/**
 *
 * @author Max Carter
 */
public class Sector {
          
    public int
            spending,
            population;
    public double
            spendingInfluence,
            standardOfLiving,
            wageMultiplier;
     
    public Sector(double standardOfLiving, int population, double wageMultiplier) {
        this.standardOfLiving = standardOfLiving;
        this.population = population;
        this.wageMultiplier = wageMultiplier;
    }

    public int getSpending() {
        return this.spending;
    }
    
    public void addSpending(int value) {
        this.spending += value;
    }

    public void setSpending(int value) {
        this.spending = value;
    }
    
    public double getSpendingInfluence() {
        return this.spendingInfluence;
    }
    
    public void addSpendingInfluence(double value) {
        this.spendingInfluence += value;
    }

    public void setSpendingInfluence(double value) {
        this.spendingInfluence = value;
    }

    public double getStandardLivingInfluence() {
        return this.standardOfLiving;
    }

    public int getPopulationInfluence() {
        return this.population;
    }

    public double getWageInfluence() {
        return this.wageMultiplier;
    } 

}
