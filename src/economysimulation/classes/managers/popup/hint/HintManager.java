package economysimulation.classes.managers.popup.hint;

import economysimulation.classes.global.Methods;
import economysimulation.classes.managers.exception.InvalidPanelSizeException;
import economysimulation.classes.managers.shadow.Speed;
import economysimulation.classes.managers.shadow.Position;
import economysimulation.classes.managers.shadow.ShadowFrame;
import economysimulation.classes.managers.shadow.ShadowSize;

/**
 *
 * @author Max Carter
 */
public class HintManager {
    
    protected static HintDisplay hintDisplay = new HintDisplay();
    public static boolean isShowing = false;

    public static void createNewHint(String title, String desc, int urgency) throws InvalidPanelSizeException {

        if (!isShowing) {
            isShowing = true;
            Methods.totalHints++;
            hintDisplay.createHint(title, desc, urgency);
            new ShadowFrame("Hint #" + Methods.totalHints, hintDisplay, Position.BOTTOM_RIGHT, ShadowSize.STANDARD, Speed.MEDIUM);
        }
    }
    
    public static void hintDisplayEnded() {
        isShowing = false;
    }
    
}
