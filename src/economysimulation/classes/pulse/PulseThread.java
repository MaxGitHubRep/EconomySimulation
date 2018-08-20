package economysimulation.classes.pulse;

import economysimulation.classes.gui.fronter.GameHold;

/**
 *
 * @author Max Carter
 */
public class PulseThread {
    
    private GamePulse pulse;
    public Thread PulseThread;
    public static boolean IS_RUNNING = false;
    
    public void setGamePulseEventListener(GamePulse pulse) {
        this.pulse = pulse;
    }
    
    public void initPulseThread() {
        IS_RUNNING = true;
        PulseThread = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    while (IS_RUNNING) {
                        Thread.sleep(GameHold.SPEED);
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
