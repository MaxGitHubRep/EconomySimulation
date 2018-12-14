package economysimulation.classes.economy.sectors;

/**
 *
 * @author Max Carter
 */
public interface SectorEvent {
    
    /**
     * Called when money has been invested in a sector
     * @param sector Sector which was changed.
     * @param value  Value which the sector was changed by.
     */
    public void onSectorSpend(Sector sector, int value);
    
}
