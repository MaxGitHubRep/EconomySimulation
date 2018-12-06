package economysimulation.classes.managers.extcon.multiplayer;

import economysimulation.classes.economy.structure.Component;
import economysimulation.classes.global.Methods;
import static economysimulation.classes.global.Methods.SectorInstance;
import economysimulation.classes.managers.exception.InvalidServerSlot;
import economysimulation.classes.pulse.GamePulse;
import java.util.List;

/**
 *
 * @author Max Carter
 */
public class StorageReceiver implements GamePulse {

    @Override
    public void onGamePulseEvent() {
        if (Methods.MPServerSlot == -1) {
            try {
                throw new InvalidServerSlot(Methods.MPServerSlot);
            } catch (InvalidServerSlot ex) {
                ex.printStackTrace();
            }
        }
        
        //Requesting data from the database.
        Methods.StorageConnection.pullLatestPackage(Methods.MPServerSlot);
        List<StoragePackage> storagePackageList = Methods.StorageConnection.getStoragePackage();
        
        //Checks to see if there have been any changes.
        if (Methods.StorageConnection.getChangesQuantity() == 0) return;
        
        //Loops through the list
        for (StoragePackage pkg : storagePackageList) {
            double value = pkg.getComponentValue();
            String name = pkg.getUpdater();
            
            //Switch case to update all variables that were changed.
            switch (pkg.getComponentId()) {
                case 0:
                    Component.InterestRate = value;
                    onComponentUpdate("Interest Rates", value, name);
                    break;
                case 1:
                    Component.CorporationTax = value;
                    onComponentUpdate("Corporation Tax", value, name);
                    break;
                case 2:
                    Component.IncomeTax = value;
                    onComponentUpdate("Income Tax", value, name);
                    break;
                case 3:
                    SectorInstance.NHS.setSpending((int) value);
                    onComponentUpdate("NHS", value, name);
                    break;
                case 4:
                    SectorInstance.Education.setSpending((int) value);
                    onComponentUpdate("Education", value, name);
                    break;
                case 5:
                    SectorInstance.Housing.setSpending((int) value);
                    onComponentUpdate("Housing", value, name);
                    break;
                case 6:
                    SectorInstance.Food.setSpending((int) value);
                    onComponentUpdate("Food", value, name);
                    break;
                case 7:
                    SectorInstance.Infrastructure.setSpending((int) value);
                    onComponentUpdate("Infrastructure", value, name);
                    break;
                case 8:
                    SectorInstance.Defence.setSpending((int) value);
                    onComponentUpdate("Defence", value, name);
                    break;
                case 9:
                    SectorInstance.Science.setSpending((int) value);
                    onComponentUpdate("Science", value, name);
                    break;
                case 10:
                    SectorInstance.Benefits.setSpending((int) value);
                    onComponentUpdate("Benefits", value, name);
                    break;
                
            }
        }
    }
    
    private void onComponentUpdate(String componentName, double value, String user) {
        //TODO link to ui
        System.out.println(user +": " + componentName + " -> " + value);
    }
    
}
