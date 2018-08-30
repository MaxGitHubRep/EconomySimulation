package economysimulation.classes.managers.exception;

import economysimulation.classes.economy.sectors.Sector;

/**
 *
 * @author Max Carter
 */
public class InvalidSectorException extends Exception {
    
    /**
    * Sends exception when an invalid sector id is requested.
    */
    public InvalidSectorException() {
        super();
    }
    
    /**
    * Sends exception when an invalid sector id is requested.
    * 
    * @param id the invalid sector specified
    */
    public InvalidSectorException(int id) {
        super("Invalid sector requested: " + id + ". Specified value must be between 0 and " + Sector.SectorList.length);
    }
    
}
