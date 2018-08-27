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
    protected static final int DURATION = 280;

    public static void createNewHint(int id) throws InvalidPanelSizeException {
        if (id > HintCooldown.cdTime.length) {
            throw new NullPointerException();
            
        } else if (!Hints.isOnCooldown(id)) {
            HintCooldown.cdTime[id] = DURATION;
            new HintCooldown(id);
            Methods.totalHints++;
            hintDisplay.createHint(Hints.getHintTitle(id), Hints.getHintDescription(id), Hints.getHintUrgency(id));
            new ShadowFrame("Hint #" + Methods.totalHints, hintDisplay, Position.BOTTOM_RIGHT, ShadowSize.STANDARD, Speed.MEDIUM, true);
        }
    }
}
