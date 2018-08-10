package economysimulation.classes.managers.popup.hint;

import java.util.ArrayList;

/**
 *
 * @author Max Carter
 */
public class HintManager {
    
    protected static ArrayList<String>
            titleList = new ArrayList<>(),
            descList = new ArrayList<>();
    protected static ArrayList<Integer>
            urgencyList = new ArrayList<>();
    
    public static void createNewHint(String title, String desc, int urgency) {
        titleList.add(title);
        descList.add(desc);
        urgencyList.add(urgency);
        
        hintDisplayReady();
    }
    
    protected static void hintDisplayReady() {
        if (!PopUpHint.isShowing && titleList.size() > 0) {
            new PopUpHint(titleList.get(0), descList.get(0), urgencyList.get(0));
        }
    }
    
}
