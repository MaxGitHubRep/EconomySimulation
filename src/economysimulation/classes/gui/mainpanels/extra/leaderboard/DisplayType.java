package economysimulation.classes.gui.mainpanels.extra.leaderboard;

/**
 *
 * @author Max Carter
 */
public enum DisplayType {
    COMBINED(0, "Combined"),
    SINGLE_PLAYER(1, "Singleplayer"),
    MULTI_PLAYER(2, "Multiplayer");

    private int index;
    private String title;
    
    private DisplayType(int index, String title) {
        this.index = index;
        this.title = title;
    }
    
    public int getIndex() {
        return index;
    }
    
    public String getTitle() {
        return title;
    }
    
}
