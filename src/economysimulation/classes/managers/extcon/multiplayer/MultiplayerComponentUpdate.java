package economysimulation.classes.managers.extcon.multiplayer;

import economysimulation.classes.global.User;

/**
 * @author Max Carter
 */
public interface MultiplayerComponentUpdate {
    
    /**
     * When a component has been updated.
     * @param componentName Name of the component.
     * @param value New value of the component.
     * @param user The user who updated it.
     */
    void onComponentUpdate(String componentName, double value, User user);
    
}
