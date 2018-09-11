package economysimulation.classes.pulse;

import static economysimulation.classes.global.Methods.GameDisplay;
import java.util.ArrayList;

/**
 *
 * @author Max Carter
 */
public class PulseThread {
    
    protected ArrayList<GamePulse> Pulses;
    public Thread PulseThread;
    public static volatile boolean IS_RUNNING = false;
    
    public void addGamePulseEventListener(GamePulse pulse) {
        this.Pulses.add(pulse);
    }
    
    protected synchronized void initPulseThread() {
        IS_RUNNING = true;
        PulseThread = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    while (IS_RUNNING) {
                        Thread.sleep(GameDisplay.Speed);
                        if (Pulses != null) {
                            for (GamePulse pulse : Pulses) {
                                pulse.gamePulseEvent();
                            }
                        }
                    }
                } catch (InterruptedException ex) {
                    ex.printStackTrace();
                }
            }
        });
        PulseThread.start();
    }
    
}
