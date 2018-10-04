package economysimulation.classes.managers.popup.positions;

/**
 *
 * @author Max Carter
 */
public class FramePosition {
    
    public static int[] Positions = new int[]{ 5, 4, 4, 4 };
    
    public static int getPositionFromId(int id) {
        return Positions[id];
    }
    
    public static void setPositionById(int id, int position) {
        Positions[id] = position;
    }
    
}
