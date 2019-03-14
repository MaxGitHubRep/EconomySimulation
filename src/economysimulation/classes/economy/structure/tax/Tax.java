package economysimulation.classes.economy.structure.tax;

/**
 * @author Max Carter
 */
public enum Tax {

    INCOME("Income", 1),
    CORPORATION("Corporation", 0);
    
    //Local variables of the tax data.
    private String name;
    private int id;
    
    /**
     * Creates new Tax.
     * @param name Name of the tax.
     * @param id ID of the tax.
     */
    Tax(String name, int id) {
        this.name = name;
        this.id = id;
    }
    
    /**
     * Gets the name of the tax.
     * @return Tax name.
     */
    public String getName() {
        return name;
    }
    
    /**
     * Gets the ID of the tax.
     * @return Tax ID.
     */
    public int getID() {
        return id;
    }
    
}
