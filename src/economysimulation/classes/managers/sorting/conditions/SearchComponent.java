package economysimulation.classes.managers.sorting.conditions;

/**
 *
 * @author Max Carter
 */
public enum SearchComponent {
    GDP(-2, "GDP"),
    TICKS(-1, "Game Ticks"),
    PEOPLE_SUPPORT(4, "Consumer Support"),
    UNEMPLOYMENT(3, "Unemployment"),
    FIRM_SUPPORT(8, "Firm Support"),
    CONSUMPTION(0, "Consumption"),
    SAVINGS(1, "Savings"),
    INVESTMENT(5, "Investment"),
    TAXATION(6, "Taxation"),
    FIRM_PROFITS(7, "Firm Profits"),
    POPULATION(2, "Population");

    private int index;
    private String name;
    
    private SearchComponent(int index, String name) {
        this.index = index;
        this.name = name;
    }
    
    public int getIndex() {
        return index;
    }
    
    public String getName() {
        return name;
    }
    
}
