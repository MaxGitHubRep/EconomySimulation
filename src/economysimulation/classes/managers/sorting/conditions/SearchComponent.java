package economysimulation.classes.managers.sorting.conditions;

/**
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

    //Index of the search component.
    private int index;
    
    //Name of the search component.
    private String name;
    
    /**
     * Creates a new search component.
     * @param index Index of the component.
     * @param name  Name of the component.
     */
    private SearchComponent(int index, String name) {
        this.index = index;
        this.name = name;
    }
    
    /**
     * Gets the index of the component.
     * @return The index of the component.
     */
    public int getIndex() {
        return index;
    }
    
    /**
     * Gets the name of the component.
     * @return Name of the component.
     */
    public String getName() {
        return name;
    }
    
}
