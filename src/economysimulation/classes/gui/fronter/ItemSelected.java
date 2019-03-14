package economysimulation.classes.gui.fronter;

/**
 * @author Max Carter
 */
public interface ItemSelected {
    
    /** 
     * Whenever an item on the sidebar has been selected.
     * @param selected The item which was selected.
     */
    public void onItemSelected(ItemSelected selected);
    
}
