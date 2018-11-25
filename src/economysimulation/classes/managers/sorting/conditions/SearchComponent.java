package economysimulation.classes.managers.sorting.conditions;

/**
 *
 * @author Max Carter
 */
public enum SearchComponent {
    GDP(-2),
    TICKS(-1),
    PEOPLE_SUPPORT(4),
    UNEMPLOYMENT(3),
    FIRM_SUPPORT(8),
    CONSUMPTION(0),
    SAVINGS(1),
    INVESTMENT(5),
    TAXATION(6),
    FIRM_PROFITS(7),
    POPULATION(2);

    private int index;
    
    private SearchComponent(int index) {
        this.index = index;
    }
    
    public int getIndex() {
        return index;
    }
    
}
