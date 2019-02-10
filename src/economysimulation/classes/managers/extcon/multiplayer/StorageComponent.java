package economysimulation.classes.managers.extcon.multiplayer;

/**
 *
 * @author Max Carter
 */
public enum StorageComponent {
    INTEREST_RATE(0),
    CORPORATION_TAX(1),
    INCOME_TAX(2),
    NHS(3),
    EDUCATION(4),
    HOUSING(5),
    FOOD(6),
    INFRASTRUCTURE(7),
    DEFENCE(8),
    SCIENCE(9),
    BENEFITS(10);

    private int index = -1;
        
    private StorageComponent(int index) {
        this.index = index;
    }
    
    public int getIndex() {
        return this.index;
    }
    
}
