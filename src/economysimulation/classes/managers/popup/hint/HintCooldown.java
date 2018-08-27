package economysimulation.classes.managers.popup.hint;

import java.awt.event.ActionEvent;
import javax.swing.AbstractAction;
import javax.swing.Timer;

/**
 *
 * @author Max Carter
 */
public class HintCooldown {

    private static Timer timer;
    public static int[] cdTime = new int[]{
        HintManager.DURATION,
        HintManager.DURATION,
        HintManager.DURATION
    };
    
    /**
     * Initiates a timer to start cooldown on specific hint.
     *
     * @param id Hint index.
     */
    public HintCooldown(int id) {
        initCooldown(id);
    }
    
    protected static void initCooldown(int id) {
        timer = new Timer(100, new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (cdTime[id] == 0) {
                    cdTime[id] = HintManager.DURATION;
                    timer.stop();
                } else {
                    cdTime[id]--;
                    timer.start();
                }
            }
        }); 
        timer.start();
    }
    
}
