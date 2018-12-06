package economysimulation.classes.managers.extcon.multiplayer;

/**
 *
 * @author Max Carter
 */
public enum State {
    LOBBY("Lobby", 0),
    IN_GAME("In Game", 1),
    END_GAME("End Game", 2),
    RESETTING("Resetting", 3),
    ERROR("Error", -1);

    private String textState = null;
    private int index = -1;
    
    State(String textState, int index) {
        this.textState = textState;
        this.index = index;
    }
    
    public int getIndex() {
        return index;
    }
    
    public String getTextState() {
        return textState;
    }
    
}
