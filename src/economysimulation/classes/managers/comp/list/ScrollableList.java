package economysimulation.classes.managers.comp.list;

import static economysimulation.classes.global.Methods.ThemeHandler;
import economysimulation.classes.managers.theme.GraphicUpdater;
import economysimulation.classes.managers.theme.ThemeUpdateEvent;
import java.awt.Font;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

/**
 * @author Max Carter
 */
public class ScrollableList extends javax.swing.JPanel implements ThemeUpdateEvent {

    /** List which contains the actual data. */
    private List<String> list;
    
    /** Labels which display the items. */
    private JLabel[] labels;
    
    /** Default text that represents an empty slot. */
    private String emptySlotText = "";
    
    /** Whether the list is enabled or not. */
    private boolean enabled = true;
    
    /**
     * Creates a new ScrollableList with a default list.
     * @param defaultList The pre-existing list.
     * @param emptySlotText Custom empty slot text.
     */
    public ScrollableList(List<String> defaultList, String emptySlotText) {
        setList(list);
        setEmptySlotText(emptySlotText);
        init();
    }
    
    /**
     * Creates a new ScrollableList with a default list.
     * @param defaultList The pre-existing list.
     */
    public ScrollableList(List<String> defaultList) {
        setList(list);
        init();
    }
    
    /**
     * Creates a new ScrollableList without a default list.
     * @param emptySlotText Custom empty slot text.
     */
    public ScrollableList(String emptySlotText) {
        setList(new ArrayList());
        setEmptySlotText(emptySlotText);
        init();
    }
    
    /** Creates a new ScrollableList. */
    public ScrollableList() {
        setList(new ArrayList());
        init();
    }
    
    /** Initiates the list. */
    private void init() {
        initComponents();
        
        //formats the labels.
        labels = new JLabel[10];
        for (int i = 0; i < labels.length; i++) {
            labels[i] = new JLabel(emptySlotText);
            this.add(labels[i]);
            labels[i].setFont(new Font("Agency FB",Font.PLAIN, 20));
            labels[i].setLocation(0, i * (472 / labels.length));
            labels[i].setSize(420, (472 / labels.length));
            labels[i].setHorizontalAlignment(SwingConstants.CENTER);
            labels[i].setVerticalAlignment(SwingConstants.CENTER);
        }
        
        //sets up theme handler.
        ThemeHandler.addThemeUpdateListener(this);
        updateList();
    }
    
    /** Enables the list. */
    public void enable() {
        toggle(true);
    }
    
    /** Disables the list. */
    public void disable() {
        toggle(false);
    }
    
    /**
     * Returns true if the list is enabled.
     * @return Whether the list is enabled or not.
     */
    public boolean isEnabled() {
        return enabled;
    }
    
    /**
     * Switches the state between enabled and disabled.
     * @param state The new state of the list.
     */
    private void toggle(boolean state) {
        enabled = state;
        for (JLabel label : labels) label.setEnabled(state);
    }
    
    /** Clears the list. */
    public void clear() {
        list.clear();
        updateList();
    }

    /**
     * Returns true if the list is greater than the amount of labels.
     * @return Whether the list is too small or not.
     */
    public boolean overflows() {
        return list.size() > labels.length;
    }
    
    /**
     * How many items are not displayed in the list.
     * @return Amount of items not on display.
     */
    public int getOverflowSize() {
        if (!overflows()) return 0;
        return list.size() - labels.length;
    }
    
    @Override
    public void onThemeUpdate(GraphicUpdater updater) {
        updater.applyPanelThemes(new JPanel[]{ this }, null);
        updater.applyTextThemes(labels, null);
    }
    
    /**
     * Sets the empty slot text.
     * @param emptySlotText Text for an empty slot.
     */
    public void setEmptySlotText(String emptySlotText) {
        this.emptySlotText = emptySlotText;
    }
    
    /**
     * Returns the empty slot text of the list.
     * @return Empty slot text.
     */
    public String getEmptySlotText() {
        return emptySlotText;
    }
    
    /** Updates all the nodes on the list. */
    public void updateList() {
        if (!enabled) return;
        for (int i = 0; i < labels.length; i++) {
            if (list.size() > i) labels[i].setText(list.get(i));
            else labels[i].setText(emptySlotText);
        }
    }
    
    /**
     * Gets all the list nodes.
     * @return The main list.
     */
    public List<String> getList() {
        return list;
    }
    
    /**
     * Sets the list to a new one, only if the list is enabled.
     * @param list The new list model.
     */
    public void setList(List<String> list) {
        if (enabled) this.list = list;
    }
    
    /**
     * Adds a node to the list.
     * @param item Item to add.
     */
    public void addItem(String item) {
        if (enabled) list.add(item);
    }
    
    /**
     * Removes an item from the list at a specific index.
     * @param index Index to remove.
     */
    public void removeItem(int index) {
        if (!enabled) return;
        //validates that the index is within the bounds of the list.
        if (list.size() > index) {
            list.remove(index);
            fillEmptySlots();
        }
    }
    
    /**
     * Checks that an {@code item} is inside of a list.
     * @param item Item to check.
     * @return Returns true if the item is in the list.
     */
    public boolean contains(String item) {
        return list.contains(item);
    }
    
    /** Fills all the empty slots with the empty slot text. */
    private void fillEmptySlots() {
        //does nothing if there are no empty slots.
        if (list.size() >= 10) return;
        
        for (int i = labels.length-1; i >= 0; i--) {
            if (list.size()-1 < i) labels[i].setText(emptySlotText); else return;
        }
    }
    
    /**
     * Removes a node from the list. Only works when the list is enabled.
     * @param item Item to remove.
     */
    public void removeItem(String item) {
        if (!enabled) return;
        list.remove(item);
        fillEmptySlots();
    }
    
    /**
     * Gets a node at a specific index.
     * @param index Index of the list.
     * @return The item at the index of the list.
     */
    public String getItem(int index) {
        return list.get(index);
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 472, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 472, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents

    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
