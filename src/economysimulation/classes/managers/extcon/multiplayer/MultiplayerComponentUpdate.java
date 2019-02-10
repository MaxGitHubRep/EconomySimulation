package economysimulation.classes.managers.extcon.multiplayer;

import economysimulation.classes.global.User;

/**
 *
 * @author Max Carter
 */
public interface MultiplayerComponentUpdate {
    
    void onComponentUpdate(String componentName, double value, User user);
    
}
