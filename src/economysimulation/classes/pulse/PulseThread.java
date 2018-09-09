package economysimulation.classes.pulse;

import static economysimulation.classes.global.Methods.GameDisplay;

/**
 *
 * @author Max Carter
 */
public class PulseThread {
    
    private GamePulse pulse;
    public Thread PulseThread;
    public static volatile boolean IS_RUNNING = false;
    
    public void setGamePulseEventListener(GamePulse pulse) {
        this.pulse = pulse;
    }
    
    protected synchronized void initPulseThread() {
        IS_RUNNING = true;
        PulseThread = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    while (IS_RUNNING) {
                        Thread.sleep(GameDisplay.Speed);
                        if (pulse != null) pulse.gamePulseEvent();
                    }
                } catch (InterruptedException ex) {
                    ex.printStackTrace();
                }
            }
        });
        PulseThread.start();
    }
    
}
