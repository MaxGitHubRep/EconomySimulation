package economysimulation.classes.managers.popup.hint;

/**
 *
 * @author Max Carter
 */
public class Hints {
    
    //list of all possible hints available with IDs
    
    public static final int
            INSUFFICIENT_FUNDS = 0,
            CONSUMERS_OUT_OF_MONEY = 1;
    
    public static final String[]
        HINT_TITLES = new String[]{
            "Insufficient Funds for Desired Payment!",
            "Consumers are Running out of Money"
        },
        HINT_DECSRIPTIONS = new String[]{
            "Increase taxes to obtain more money.",
            "Reducing taxes will mean more money for the population."
        };
    
    public static final int[]
        HINT_URGENCIES = new int[]{
            Urgency.MEDIUM,
            Urgency.HIGH
        };
    
    public static String getHintTitle(int id) {
        return HINT_TITLES[id];
    }
    
    public static String getHintDescription(int id) {
        return HINT_DECSRIPTIONS[id];
    }
    
    public static int getHintUrgency(int id) {
        return HINT_URGENCIES[id];
    }
    
    
    public static boolean isOnCooldown(int id) {
        return HintCooldown.cdTime[id] != HintCooldown.DURATION;
    }
    
}
