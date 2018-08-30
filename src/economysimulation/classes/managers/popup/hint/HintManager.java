package economysimulation.classes.managers.popup.hint;

import economysimulation.classes.global.Methods;
import economysimulation.classes.managers.popup.hint.hints.Hint;
import economysimulation.classes.managers.shadow.Speed;
import economysimulation.classes.managers.shadow.Position;
import economysimulation.classes.managers.shadow.ShadowFrame;
import economysimulation.classes.managers.shadow.ShadowSize;
import java.awt.event.ActionEvent;
import javax.swing.AbstractAction;
import javax.swing.Timer;

/**
 *
 * @author Max Carter
 */
public class HintManager {
    
    protected static HintDisplay hintDisplay = new HintDisplay();
    protected static int HintCount = 0;
    
    public static void createHint(Hint hint) {
        if (!hint.isOnCooldown()) {
            hint.resetCooldown();
            new HintCooldown(hint);
            HintCount++;
            hintDisplay.createHint(hint.getTitle(), hint.getDescription(), hint.getUrgency());
            try {
                new ShadowFrame("Hint #" + HintCount, hintDisplay, Position.BOTTOM_RIGHT, ShadowSize.STANDARD, Speed.MEDIUM, true);
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
    }
    
    private static class HintCooldown {
        private Timer timer;
        private Hint hint;

        /**
         * Initiates a timer to start cool down on specific hint.
         *
         * @param hint Hint being used.
         */
        private HintCooldown(Hint hint) {
            this.hint = hint;
            initCooldown();
        }

        private void initCooldown() {
            timer = new Timer(100, new AbstractAction() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    timer.stop();
                    if (hint.getCooldown() == 0) {
                        hint.resetCooldown();  
                    } else {
                        hint.reduceCooldown();
                        initCooldown();
                    }
                }
            }); 
            timer.start();
        }
    }
}
