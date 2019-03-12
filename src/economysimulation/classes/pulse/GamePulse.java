package economysimulation.classes.pulse;

/**
 * @author Max Carter
 */
public interface GamePulse {
    
    /** Event for when the simulation emits a pulse, signalling a new day in the economy. */
    void onGamePulseEvent();
    
}
