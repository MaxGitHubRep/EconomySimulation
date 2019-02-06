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
 *
 * @author Max Carter
 */
public class ScrollableList extends javax.swing.JPanel implements ThemeUpdateEvent {

    private List<String> list;
    private JLabel[] labels;
    
    private String emptySlotText = "";
    
    private boolean enabled = true;
     
    public ScrollableList(List<String> defaultList, String emptySlotText) {
        setList(list);
        setEmptySlotText(emptySlotText);
        init();
    }
    
    public ScrollableList(List<String> defaultList) {
        setList(list);
        init();
    }
    
    public ScrollableList(String emptySlotText) {
        setList(new ArrayList());
        setEmptySlotText(emptySlotText);
        init();
    }
    
    public ScrollableList() {
        setList(new ArrayList());
        init();
    }
    
    private void init() {
        initComponents();
        
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
        
        ThemeHandler.addThemeUpdateListener(this);
        updateList();
    }
    
    public void enable() {
        toggle(true);
    }
    
    public void disable() {
        toggle(false);
    }
    
    public boolean isEnabled() {
        return enabled;
    }
    
    private void toggle(boolean state) {
        enabled = state;
        for (JLabel label : labels) label.setEnabled(state);
    }
    
    public void clear() {
        list.clear();
        updateList();
    }

    public boolean overflows() {
        return list.size() > labels.length;
    }
    
    public int getOverflowSize() {
        if (!overflows()) return 0;
        return list.size() - labels.length;
    }
    
    @Override
    public void onThemeUpdate(GraphicUpdater updater) {
        updater.applyPanelThemes(new JPanel[]{ this }, null);
        updater.applyTextThemes(labels, null);
    }
    
    public void setEmptySlotText(String emptySlotText) {
        this.emptySlotText = emptySlotText;
    }
    
    public String getEmptySlotText() {
        return emptySlotText;
    }
    
    public void updateList() {
        if (!enabled) return;
        for (int i = 0; i < labels.length; i++) {
            if (list.size() > i) labels[i].setText(list.get(i));
            else labels[i].setText(emptySlotText);
        }
    }
    
    public List<String> getList() {
        return list;
    }
    
    public void setList(List<String> list) {
        if (enabled) this.list = list;
    }
    
    public void addItem(String item) {
        if (enabled) list.add(item);
    }
    
    public void removeItem(int index) {
        if (!enabled) return;
        if (list.size() > index) {
            list.remove(index);
            fillEmptySlots();
        }
    }
    
    public boolean contains(String item) {
        return list.contains(item);
    }
    
    private void fillEmptySlots() {
        if (list.size() >= 10) return;
        
        for (int i = labels.length-1; i >= 0; i--) {
            if (list.size()-1 < i) labels[i].setText(emptySlotText); else return;
        }
        
    }
    
    public void removeItem(String item) {
        if (!enabled) return;
        list.remove(item);
        fillEmptySlots();
    }
    
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
