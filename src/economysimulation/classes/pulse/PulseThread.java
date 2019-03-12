package economysimulation.classes.pulse;

import static economysimulation.classes.global.Methods.GameDisplay;
import java.util.ArrayList;

/**
 * @author Max Carter
 */
public class PulseThread {
    
    /** List of pulse listeners. */
    public ArrayList<GamePulse> Pulses;
    
    /** Thread of the pulse. */
    public Thread PulseThread;

    /**
     * Evaluates {@code true} if the pulse thread is emitting a tick.
     */
    public volatile boolean SimulationTicking = false;
    
    /**
     * Adds a pulse event listener.
     * @param pulse Instance of the listener.
     */
    public void addGamePulseEventListener(GamePulse pulse) {
        this.Pulses.add(pulse);
    }
    
    /** Initiates the pulse thread. */
    protected synchronized void initPulseThread() {
        //starts the simulation tick.
        SimulationTicking = true;
        
        //starts the thread.
        PulseThread = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    while (SimulationTicking) {
                        //starts delay based on simulation speed.
                        Thread.sleep(GameDisplay.Speed);
                        
                        if (Pulses != null && SimulationTicking) {
                            //alert all the listeners about a new pulse.
                            Pulses.forEach((pulse) -> {
                                pulse.onGamePulseEvent();
                            });
                        } else {
                            //exits the thread.
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
