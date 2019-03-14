package economysimulation.classes.economy.sectors;

/**
 * @author Max Carter
 */
public class SectorManager {
    
    public final Sector
            NHS = new Sector(0.004, 3, 0),
            Education = new Sector(0.003, 0, 1.1),
            Housing = new Sector(0.0025, 0, 1.05),
            Food = new Sector(0.003, 1, 0),
            Infrastructure = new Sector(0.003, 0, 0),
            Defence = new Sector(0.001, 1, 0),
            Science = new Sector(0.0015, 3, 0),
            Benefits = new Sector(0.0015, 1, 1.02);

    public Sector[] SectorList  = new Sector[]{
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
    public Sector getSector(int index) {
        if (index < 0 || index > getSectorSize()) throw new NullPointerException();
        return SectorList[index];
    }
    
}