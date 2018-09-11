package economysimulation.classes.pulse;

import economysimulation.classes.global.Methods;
import java.util.ArrayList;

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
        
        pulseThread.Pulses = new ArrayList<>();
        
        pulseThread.addGamePulseEventListener(Methods.FormulaInstance);
        pulseThread.addGamePulseEventListener(Methods.GameDisplay);
        pulseThread.addGamePulseEventListener(Methods.TaxRevenueDisplay);

        pulseThread.initPulseThread();
    }

}
