package economysimulation.classes.managers.popup.hint;

/**
 *
 * @author Max Carter
 */
public class Hints {
    
    //list of all possible hints available with IDs
    
    public static final int
            HINT_INSUFFICIENT_FUNDS = 0,
            HINT_CONSUMERS_OUT_OF_MONEY = 1,
            HINT_FIRMS_OUT_OF_MONEY = 2;
    
    protected static final String[]
        HINT_TITLES = new String[]{
            "Insufficient Funds for Desired Payment!",
            "Consumers are out of Money!",
            "Firms are out of Money!"
        },
        HINT_DECSRIPTIONS = new String[]{
            "Increase taxes to obtain more money.",
            "Reducing taxes will mean more money for the population.",
            "Reducing taxes will increase business profits."
        };
    
    protected static final int[]
        HINT_URGENCIES = new int[]{
            Urgency.MEDIUM,
            Urgency.HIGH,
            Urgency.HIGH
        };
    
    protected static String getHintTitle(int id) {
        return HINT_TITLES[id];
    }
    
    protected static String getHintDescription(int id) {
        return HINT_DECSRIPTIONS[id];
    }
    
    protected static int getHintUrgency(int id) {
        return HINT_URGENCIES[id];
    }
    
    
    protected static boolean isOnCooldown(int id) {
        return HintCooldown.cdTime[id] != HintManager.DURATION;
    }
    
}
