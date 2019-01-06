package economysimulation.classes.managers.comp.list;

import static economysimulation.classes.global.Methods.ThemeHandler;
import economysimulation.classes.managers.theme.GraphicUpdater;
import economysimulation.classes.managers.theme.ThemeUpdateEvent;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
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
    
    private LabelClickEvent listener = null;
    
    private String emptySlotText = "";
     
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
            labels[i].addMouseListener(new MouseEventClick(labels[i]));
        }
        
        ThemeHandler.addThemeUpdateListener(this);
        updateList();
    }

    @Override
    public void onThemeUpdate(GraphicUpdater updater) {
        updater.applyPanelThemes(new JPanel[]{ this }, null);
        updater.applyTextThemes(labels, null);
    }
    
    private class MouseEventClick extends MouseAdapter {
        
        private JLabel label = null;
        
        public MouseEventClick(JLabel label) {
            this.label = label;
        }
        
        @Override
        public void mouseClicked(MouseEvent me) {
            listener.onLabelClick(this.label);
        }
        
    }
    
    public void setEmptySlotText(String emptySlotText) {
        this.emptySlotText = emptySlotText;
    }
    
    public String getEmptySlotText() {
        return emptySlotText;
    }
    
    public void setLabelClickListener(LabelClickEvent listener) {
        this.listener = listener;
    }
    
    public void updateList() {
        for (int i = 0; i < labels.length; i++) {
            if (list.size() > i) labels[i].setText(list.get(i));
            else labels[i].setText(emptySlotText);
        }
    }
    
    public List<String> getList() {
        return list;
    }
    
    public void setList(List<String> list) {
        this.list = list;
    }
    
    public void addItem(String item) {
        list.add(item);
    }
    
    public void removeItem(int index) {
        if (list.size() > index) {
            list.remove(index);
            fillEmptySlots();
        }
    }
    
    private void fillEmptySlots() {
        if (list.size() >= 10) return;
        
        for (int i = labels.length-1; i >= 0; i--) {
            if (list.size()-1 < i) labels[i].setText(emptySlotText); else return;
        }
        
    }
    
    public void removeItem(String item) {
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
