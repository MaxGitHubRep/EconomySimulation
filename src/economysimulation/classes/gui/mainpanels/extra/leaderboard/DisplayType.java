package economysimulation.classes.gui.mainpanels.extra.leaderboard;

/**
 * @author Max Carter
 */
public enum DisplayType {
    COMBINED(0, "Combined"),
    SINGLE_PLAYER(1, "Singleplayer"),
    MULTI_PLAYER(2, "Multiplayer");

    //Local variables which store data about the type.
    private int index;
    private String title;
    
    /**
     * Creates a new displayed sort type.
     * @param index Index of type.
     * @param title Name of type.
     */
    private DisplayType(int index, String title) {
        this.index = index;
        this.title = title;
    }
    
    /**
     * Gets the index of the sort type.
     * @return Index of type.
     */
    public int getIndex() {
        return index;
    }
    
    /**
     * Gets the name of the sort type.
     * @return The name of the type.
     */
    public String getTitle() {
        return title;
    }
    
}
