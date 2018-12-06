package economysimulation.classes.pulse;

import economysimulation.classes.global.Methods;
import static economysimulation.classes.global.Methods.ModeHandler;
import static economysimulation.classes.global.Methods.PulseUpdater;
import economysimulation.classes.mode.Mode;
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
        PulseUpdater = new PulseThread();
        
        PulseUpdater.Pulses = new ArrayList<>();
        
        if (ModeHandler.isMode(Mode.MULTI_PLAYER)) PulseUpdater.addGamePulseEventListener(Methods.StorageEvent);
        
        PulseUpdater.addGamePulseEventListener(Methods.FormulaInstance);
        PulseUpdater.addGamePulseEventListener(Methods.ConsumerDisplay);
        PulseUpdater.addGamePulseEventListener(Methods.CorporationDisplay);
        PulseUpdater.addGamePulseEventListener(Methods.GameDisplay);
        PulseUpdater.addGamePulseEventListener(Methods.TaxRevenueDisplay);
        PulseUpdater.addGamePulseEventListener(Methods.BudgetDisplay);

        PulseUpdater.initPulseThread();
    }

}
