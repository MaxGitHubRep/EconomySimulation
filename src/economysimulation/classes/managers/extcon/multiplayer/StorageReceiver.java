package economysimulation.classes.managers.extcon.multiplayer;

import economysimulation.classes.economy.budget.Budget;
import economysimulation.classes.economy.structure.Component;
import economysimulation.classes.global.Methods;
import static economysimulation.classes.global.Methods.SectorInstance;
import economysimulation.classes.global.User;
import economysimulation.classes.mode.Mode;
import economysimulation.classes.pulse.GamePulse;
import java.util.List;

/**
 * @author Max Carter
 */
public class StorageReceiver implements GamePulse {

    /** Listener of the component update event. */
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
                    Budget.spendMoney(SectorInstance.NHS, (int) value);
                    onComponentUpdate("NHS", value, user);
                    break;
                case 4:
                    Budget.spendMoney(SectorInstance.Education, (int) value);
                    onComponentUpdate("Education", value, user);
                    break;
                case 5:
                    Budget.spendMoney(SectorInstance.Housing, (int) value);
                    onComponentUpdate("Housing", value, user);
                    break;
                case 6:
                    Budget.spendMoney(SectorInstance.Food, (int) value);
                    onComponentUpdate("Food", value, user);
                    break;
                case 7:
                    Budget.spendMoney(SectorInstance.Infrastructure, (int) value);
                    onComponentUpdate("Infrastructure", value, user);
                    break;
                case 8:
                    Budget.spendMoney(SectorInstance.Defence, (int) value);
                    onComponentUpdate("Defence", value, user);
                    break;
                case 9:
                    Budget.spendMoney(SectorInstance.Science, (int) value);
                    onComponentUpdate("Science", value, user);
                    break;
                case 10:
                    Budget.spendMoney(SectorInstance.Benefits, (int) value);
                    onComponentUpdate("Benefits", value, user);
                    break;
                
            }
        }
    }
    
    /**
     * Updates the component listener.
     * @param listener New component listener.
     */
    public void setComponentUpdateListener(MultiplayerComponentUpdate listener) {
        this.listener = listener;
    }
    
    /**
     * When a component is updated.
     * @param componentName Name of the component.
     * @param value New value of component.
     * @param user The user who cchanged the ccomponent.
     */
    private void onComponentUpdate(String componentName, double value, User user) {
        if (listener != null) listener.onComponentUpdate(componentName, value, user);
    }
    
}
