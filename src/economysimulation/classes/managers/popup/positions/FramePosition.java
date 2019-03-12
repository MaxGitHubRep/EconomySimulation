package economysimulation.classes.managers.popup.positions;

/**
 * @author Max Carter
 */
public class FramePosition {
    
    /** Positions of different frame-types. */
    public static int[] Positions = new int[]{ 4, 3, 3, 3 };
    
    /**
     * Gets the position of a certain frame-type.
     * @param id Which frame-type to get the position of.
     * @return The position on the screen of the frame-type.
     */
    public static int getPositionFromId(int id) {
        return Positions[id];
    }
    
    /**
     * Sets the position on screen of a frame-type.
     * @param id       Which frame-type to change.
     * @param position New position on screen.
     */
    public static void setPositionById(int id, int position) {
        Positions[id] = position;
    }
    
}
