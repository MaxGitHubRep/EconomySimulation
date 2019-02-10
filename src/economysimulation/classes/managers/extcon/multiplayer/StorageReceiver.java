package economysimulation.classes.managers.extcon.multiplayer;

import economysimulation.classes.economy.structure.Component;
import economysimulation.classes.global.Methods;
import static economysimulation.classes.global.Methods.SectorInstance;
import economysimulation.classes.global.User;
import economysimulation.classes.mode.Mode;
import economysimulation.classes.pulse.GamePulse;
import java.util.List;

/**
 *
 * @author Max Carter
 */
public class StorageReceiver implements GamePulse {

    private MultiplayerComponentUpdate listener;
    
    @Override
    public void onGamePulseEvent() {
        if (!Methods.ModeHandler.isMode(Mode.MULTI_PLAYER)) return;
        
        //Requesting data from the database.
        Methods.StorageConnection.pullLatestPackage(Methods.localPartyId);
        List<StoragePackage> storagePackageList = Methods.StorageConnection.getStoragePackage();
        
        //Checks to see if there have been any changes.
        if (Methods.StorageConnection.getChangesQuantity() == 0) return;
        
        //Loops through the list
        for (StoragePackage pkg : storagePackageList) {
            double value = pkg.getComponentValue();
            User user = pkg.getUpdater();
            
            //Switch case to update all variables that were changed.
            switch (pkg.getComponentId()) {
                case 0:
                    Component.InterestRate = value;
                    onComponentUpdate("Interest Rates", value, user);
                    break;
                case 1:
                    Component.CorporationTax = value;
                    onComponentUpdate("Corporation Tax", value, user);
                    break;
                case 2:
                    Component.IncomeTax = value;
                    onComponentUpdate("Income Tax", value, user);
                    break;
                case 3:
                    SectorInstance.NHS.setSpending((int) value);
                    onComponentUpdate("NHS", value, user);
                    break;
                case 4:
                    SectorInstance.Education.setSpending((int) value);
                    onComponentUpdate("Education", value, user);
                    break;
                case 5:
                    SectorInstance.Housing.setSpending((int) value);
                    onComponentUpdate("Housing", value, user);
                    break;
                case 6:
                    SectorInstance.Food.setSpending((int) value);
                    onComponentUpdate("Food", value, user);
                    break;
                case 7:
                    SectorInstance.Infrastructure.setSpending((int) value);
                    onComponentUpdate("Infrastructure", value, user);
                    break;
                case 8:
                    SectorInstance.Defence.setSpending((int) value);
                    onComponentUpdate("Defence", value, user);
                    break;
                case 9:
                    SectorInstance.Science.setSpending((int) value);
                    onComponentUpdate("Science", value, user);
                    break;
                case 10:
                    SectorInstance.Benefits.setSpending((int) value);
                    onComponentUpdate("Benefits", value, user);
                    break;
                
            }
        }
    }
    
    public void setComponentUpdateListener(MultiplayerComponentUpdate listener) {
        this.listener = listener;
    }
    
    private void onComponentUpdate(String componentName, double value, User user) {
        if (listener != null) listener.onComponentUpdate(componentName, value, user);
    }
    
}
