package economysimulation.classes.managers.popup.hint;

import java.awt.Color;

/**
 *
 * @author Max Carter
 */
public class Urgency {
    
    public static final int
            LOW = 0,
            MEDIUM = 1,
            HIGH = 2,
            NULL = 3;
    
    public static String getUrgencyString(int urgency) {
        switch (urgency) {
            case LOW:
                return "Low";
            case MEDIUM:
                return "Medium";
            case HIGH:
                return "High";
            default:
                return "N/A";
        }
    }
    
    public static Color getUrgencyColor(int urgency) {
        switch (urgency) {
            case Urgency.LOW:
                return new Color(0, 204, 0);
            case Urgency.MEDIUM:
                return Color.orange;
            case Urgency.HIGH:
                return Color.red;
            default:
                return Color.white;
        }
    }
}
