package economysimulation.classes.pulse;

import economysimulation.classes.global.Methods;

/**
 *
 * @author Max Carter
 */
public class ControlPulse {
    
    
    /**
     * Initiates simulation pulse.
     */
    public ControlPulse() {
        PulseThread pulseThread = new PulseThread();
        
        pulseThread.setGamePulseEventListener(Methods.GameDisplay);

        pulseThread.initPulseThread();
    }

}
