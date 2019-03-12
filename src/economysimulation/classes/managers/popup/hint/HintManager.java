package economysimulation.classes.managers.popup.hint;

import economysimulation.classes.managers.popup.hint.hints.Hint;
import economysimulation.classes.managers.popup.positions.FramePosition;
import economysimulation.classes.managers.shadow.Speed;
import economysimulation.classes.managers.shadow.ShadowFrame;
import economysimulation.classes.managers.shadow.ShadowSize;
import java.awt.event.ActionEvent;
import javax.swing.AbstractAction;
import javax.swing.Timer;

/**
 * @author Max Carter
 */
public class HintManager {
    
    /** The amount of hints used in total. */
    private static int HintCount = 0;
    
    /**
     * Creates a new pop-up hint.
     * @param hint The hint that will be inflated.
     */
    public static synchronized void createHint(Hint hint) {
        //makes sure the hint isn't already on cooldown.
        if (hint.isCooldownEnabled()) return;
        
        //initiates the hint.
        hint.setCooldownState(true);
        HintCooldown cooldownTimer = new HintCooldown(hint);
        cooldownTimer.startCooldown();

        //displays the hint.
        HintCount++;
        HintDisplay hintDisplay = new HintDisplay();
        hintDisplay.createHint(hint.getTitle(), hint.getDescription(), hint.getUrgency());

        try {
            ShadowFrame shadowFrame = new ShadowFrame("Hint #" + HintCount, hintDisplay, FramePosition.getPositionFromId(3 - hint.getUrgency()), ShadowSize.STANDARD, Speed.MEDIUM);
            shadowFrame.inflate();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
    
    /**
     * Gets the amount of hints created.
     * @return Amount of hints created.
     */
    public int getHintCount() {
        return HintCount;
    }
    
    private static class HintCooldown {
        
        /** Instance of the timer. */
        private Timer timer;
        
        /** Instance of the hint. */
        private Hint hint = null;

        /**
         * Initiates a timer to start cool down on specific hint.
         *
         * @param hint Hint being used.
         */
        private HintCooldown(Hint hint) {
            this.hint = hint;
        }
        
        public void startCooldown() {
            if (hint != null) initCooldown();
        }

        /** Initiates the cooldown for the hint. */
        private synchronized void initCooldown() {
            timer = new Timer(hint.getCooldownTime() * 1000, new AbstractAction() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    hint.setCooldownState(false);
                }
            }); 
            timer.start();
        }
    }
}
