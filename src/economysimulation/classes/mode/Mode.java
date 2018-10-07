package economysimulation.classes.mode;

import economysimulation.classes.global.Methods;
import java.text.DecimalFormat;
/**
 *
 * @author Max Carter
 */
public class Mode {
    
    public static final int
            UNSELECTED = 0,
            SOLO_CLASSIC = 1,
            MP_CLASSIC = 2,
            SOLO_COMP = 3,
            MP_COMP = 4;
    
    public static int mode = UNSELECTED;
    
    public static void setMode(int newMode, String username) {
        mode = newMode;
        if (mode == SOLO_COMP || mode == MP_COMP) {
            Methods.Username = username + "#" + new DecimalFormat("00000").format(Methods.DBUsers.getAvailableUserID());
            
        } else if (mode == SOLO_CLASSIC || mode == MP_CLASSIC) {
            Methods.Username = Methods.generateRandomUsername(username);
            
        }
    }
    
}
