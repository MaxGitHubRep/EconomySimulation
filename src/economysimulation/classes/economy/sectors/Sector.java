package economysimulation.classes.economy.sectors;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Max Carter
 */
public class Sector {
    
    /**
     * List of sector event implementations that
     * listen to change in sector value.
     */
    public List<SectorEvent> spendingListeners;
    
    public Sector() {
        spendingListeners = new ArrayList<>();
    }
    
    public void addSectorEventListener(SectorEvent listener) {
        spendingListeners.add(listener);
    }
    
    public final BudgetSector
            NHS = new SectorBase(0.004, 3, 0),
            Education = new SectorBase(0.003, 0, 1.1),
            Housing = new SectorBase(0.0025, 0, 1.05),
            Food = new SectorBase(0.003, 1, 0),
            Infrastructure = new SectorBase(0.003, 0, 0),
            Defence = new SectorBase(0.001, 1, 0),
            Science = new SectorBase(0.0015, 3, 0),
            Benefits = new SectorBase(0.0015, 1, 1.02);

    public BudgetSector[] SectorList  = new BudgetSector[]{
        NHS, Education, Housing, Food, Infrastructure, Defence, Science, Benefits
    };
    
    /**
     * Returns the amount of sectors.
     * @return The length of the list. 
     */
    public int getSectorSize() {
        return SectorList.length;
    }
    
    /**
     * Will return the sector at the {@code index} of the list.
     * @param index The index of the list.
     * @return The sector located at a specific index in the sector list.     
     */
    public BudgetSector getSector(int index) {
        if (index < 0 || index > getSectorSize()) throw new NullPointerException();
        return SectorList[index];
    }
    
}