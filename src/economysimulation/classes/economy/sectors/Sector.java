package economysimulation.classes.economy.sectors;

import economysimulation.classes.economy.budget.BudgetSector;

/**
 *
 * @author Max Carter
 */
public class Sector {
    
    public static final BudgetSector
            NHS = new NHS(0),
            Education = new Education(1),
            Housing = new Housing(2),
            Food = new Food(3),
            Infrastructure = new Infrastructure(4),
            Defence = new Defence(5),
            Science = new Science(6),
            Benefits = new Benefits(7);

    public static BudgetSector[] SectorList  = new BudgetSector[]{
        NHS, Education, Housing, Food, Infrastructure, Defence, Science, Benefits
    };
    
}