package economysimulation.classes.managers.popup.hint;

/**
 *
 * @author Max Carter
 */
public class Urgency {
    
    public static final int
            LOW = 0,
            MEDIUM = 1,
            HIGH = 2,
            SEVERE = 3,
            NULL = 4;
    
    public static String getUrgencyString(int urgency) {
        switch (urgency) {
            case LOW:
                return "Low";
            case MEDIUM:
                return "Medium";
            case HIGH:
                return "High";
            case SEVERE:
                return "Severe";
            default:
                return "N/A";
        }
    }
}
