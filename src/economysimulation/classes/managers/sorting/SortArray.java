package economysimulation.classes.managers.sorting;

import economysimulation.classes.managers.extcon.GamePackage;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Max Carter
 */
public class SortArray {

    public static List<GamePackage> sortGameData(List<GamePackage> list) {
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
