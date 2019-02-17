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

    /**
     * Evaluates {@code True} if the pulse thread is emitting a tick.
     */
    public volatile boolean SimulationTicking = false;
    
    public void addGamePulseEventListener(GamePulse pulse) {
        this.Pulses.add(pulse);
    }
    
    protected synchronized void initPulseThread() {
        SimulationTicking = true;
        PulseThread = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    while (SimulationTicking) {
                        Thread.sleep(GameDisplay.Speed);
                        if (Pulses != null && SimulationTicking) {
                            Pulses.forEach((pulse) -> {
                                pulse.onGamePulseEvent();
                            });
                        } else {
                            System.out.println("game over");
                            return;
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
