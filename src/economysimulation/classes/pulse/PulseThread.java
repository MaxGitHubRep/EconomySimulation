package economysimulation.classes.pulse;

import static economysimulation.classes.global.Methods.GameDisplay;
import java.util.ArrayList;

/**
 *
 * @author Max Carter
 */
public class PulseThread {
    
    public ArrayList<GamePulse> Pulses;
    public Thread PulseThread;
    public volatile boolean SimulationRunning = false;
    
    public void addGamePulseEventListener(GamePulse pulse) {
        this.Pulses.add(pulse);
    }
    
    protected synchronized void initPulseThread() {
        SimulationRunning = true;
        PulseThread = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    while (SimulationRunning) {
                        Thread.sleep(GameDisplay.Speed);
                        if (Pulses != null) {
                            Pulses.forEach((pulse) -> {
                                pulse.gamePulseEvent();
                            });
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
