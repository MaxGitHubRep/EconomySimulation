package economysimulation.classes.managers.comp;

import java.awt.Font;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JLabel;

/**
 *
 * @author Max Carter
 */
public class ScrollableList extends javax.swing.JPanel {

    private List<String> list;
    private JLabel[] labels;
    
    public ScrollableList(List<String> defaultList) {
        setList(list);
        init();
    }
    
    public ScrollableList() {
        setList(new ArrayList());
        init();
    }
    
    private void init() {
        labels = new JLabel[10];
        for (int i = 0; i < labels.length; i++) {
            labels[i] = new JLabel();
            labels[i].setFont(new Font("Agency FB",Font.PLAIN, 20));
            labels[i].setLocation(0, i * (472 / labels.length));
            labels[i].setSize(420, (472 / labels.length));
            back.add(labels[i]);
        }
        updateList();
    }
    
    public void updateList() {
        for (int i = 0; i < list.size(); i++) {
            labels[i].setText(list.get(i));
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
    
    public String getItem(int index) {
        return list.get(index);
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        back = new javax.swing.JPanel();
        top = new javax.swing.JLabel();
        bottom = new javax.swing.JLabel();

        javax.swing.GroupLayout backLayout = new javax.swing.GroupLayout(back);
        back.setLayout(backLayout);
        backLayout.setHorizontalGroup(
            backLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 420, Short.MAX_VALUE)
        );
        backLayout.setVerticalGroup(
            backLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 472, Short.MAX_VALUE)
        );

        top.setFont(new java.awt.Font("Agency FB", 0, 48)); // NOI18N
        top.setForeground(new java.awt.Color(204, 0, 0));
        top.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        top.setText("-");

        bottom.setFont(new java.awt.Font("Agency FB", 0, 48)); // NOI18N
        bottom.setForeground(new java.awt.Color(204, 0, 0));
        bottom.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        bottom.setText("-");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(back, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(top, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(bottom, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(back, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(top, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(bottom, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel back;
    private javax.swing.JLabel bottom;
    private javax.swing.JLabel top;
    // End of variables declaration//GEN-END:variables
}
