package economysimulation.classes.managers.sorting;

import economysimulation.classes.gui.mainpanels.extra.leaderboard.DisplayType;
import economysimulation.classes.managers.extcon.GamePackage;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Max Carter
 */
public class SortArray {

    public static List<GamePackage> sortGameData(List<GamePackage> list, DisplayType dt) {
        
        if (dt != DisplayType.COMBINED) {
            List<GamePackage> removeList = new ArrayList<>();

            if (dt == DisplayType.SINGLE_PLAYER) {
                for (GamePackage pkg : list) {
                    if (pkg.getPlayers().length > 1) {
                        removeList.add(pkg);
                    }
                }
            } else if (dt == DisplayType.MULTI_PLAYER) {
                for (GamePackage pkg : list) {
                    if (pkg.getPlayers().length == 1) {
                        removeList.add(pkg);
                    }
                }
            }

            for (GamePackage pkg : removeList) {
                if (list.contains(pkg)) {
                    list.remove(pkg);
                }
            }
        }
        
        // Create a new list to store the new data in.
        List<GamePackage> newList = new ArrayList<>();
        
        // Checks that the list being sorted has more than one item.
        if (list.isEmpty() || list.size() == 1) return list;
        
        newList.add(list.get(0));
        
        // Iterate through new list.
        for (int i = 1; i < list.size(); i++) {
            
            int size = newList.size();
            boolean iterate = true;
            
            for (int j = 0; j < newList.size() && iterate; j++) {
                if (newList.get(j).getScore() < list.get(i).getScore()) {
                    newList.add(j, list.get(i));
                    iterate = false;
                }
                
            }
            if (size == newList.size()) newList.add(list.get(i));
            
        }
        
        return newList;
    }
    
}
