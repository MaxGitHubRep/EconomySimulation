package economysimulation.classes.pulse;

/**
 *
 * @author Max Carter
 */
public interface GamePulse {
    
    /**
     * Event for when the simulation emits a {@code pulse}, signalling a new {@code day}.
     */
    void gamePulseEvent();
    
}
