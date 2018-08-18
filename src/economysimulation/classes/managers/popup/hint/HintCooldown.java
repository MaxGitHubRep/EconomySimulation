package economysimulation.classes.managers.popup.hint;

import java.awt.event.ActionEvent;
import javax.swing.AbstractAction;
import javax.swing.Timer;

/**
 *
 * @author Max Carter
 */
public class HintCooldown {
    
    //counts down time between hints being shown.
    
    protected static final int DURATION = 220;
    protected static Timer timer;
    
    public static int[] cdTime = new int[]{
        HintCooldown.DURATION,
        HintCooldown.DURATION
    
    };
    
    protected static void initCooldown(int id) {
        timer = new Timer(100, new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (cdTime[id] == 0) {
                    cdTime[id] = DURATION;
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
