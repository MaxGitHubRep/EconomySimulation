package economysimulation.classes.managers.popup.positions;

/**
 *
 * @author Max Carter
 */
public class FramePosition {
    
    public static int[] Positions = new int[]{ 4, 3, 3, 3 };
    
    public static int getPositionFromId(int id) {
        return Positions[id];
    }
    
    public static void setPositionById(int id, int position) {
        Positions[id] = position;
    }
    
}
