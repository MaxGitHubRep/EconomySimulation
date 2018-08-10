package economysimulation.classes.managers.popup.hint;

import static economysimulation.classes.managers.popup.hint.PopUpHint.frameDragged;
import economysimulation.classes.managers.ui.Format;
import javax.swing.JPanel;

/**
 *
 * @author Max Carter
 */
public class HintDisplay extends JPanel {

    private static final long serialVersionUID = 1L;

    private int urgency;
    private String title, description;
    
    public HintDisplay(String title, String description, int urgency) {
        initComponents();
        
        this.title = title;
        this.description = description;
        this.urgency = urgency;
        createHint();
    }

    private void createHint() {
        titleLabel.setText((urgency < Urgency.NULL ? "[" + Urgency.getUrgencyString(urgency).toUpperCase() + "] " : "") + "Hint: " + title);
        descLabel.setText("<html>" + description + "</html>");
        
        top.setBackground(Urgency.getUrgencyColor(urgency));
        Format.addButtonFormat(bottom, new JPanel());
        frameDragged(top);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        top = new javax.swing.JPanel();
        titleLabel = new javax.swing.JLabel();
        bottom = new javax.swing.JPanel();
        descLabel = new javax.swing.JLabel();

        top.setBackground(new java.awt.Color(204, 0, 0));
        top.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

        titleLabel.setBackground(new java.awt.Color(255, 255, 255));
        titleLabel.setFont(new java.awt.Font("Agency FB", 0, 24)); // NOI18N
        titleLabel.setForeground(new java.awt.Color(255, 255, 255));
        titleLabel.setText("<title>");

        javax.swing.GroupLayout topLayout = new javax.swing.GroupLayout(top);
        top.setLayout(topLayout);
        topLayout.setHorizontalGroup(
            topLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(topLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(titleLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        topLayout.setVerticalGroup(
            topLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(topLayout.createSequentialGroup()
                .addComponent(titleLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        bottom.setBackground(new java.awt.Color(255, 255, 255));

        descLabel.setFont(new java.awt.Font("Agency FB", 0, 24)); // NOI18N
        descLabel.setForeground(new java.awt.Color(204, 0, 0));
        descLabel.setText("<desc>");
        descLabel.setVerticalAlignment(javax.swing.SwingConstants.TOP);

        javax.swing.GroupLayout bottomLayout = new javax.swing.GroupLayout(bottom);
        bottom.setLayout(bottomLayout);
        bottomLayout.setHorizontalGroup(
            bottomLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(bottomLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(descLabel, javax.swing.GroupLayout.DEFAULT_SIZE, 476, Short.MAX_VALUE)
                .addContainerGap())
        );
        bottomLayout.setVerticalGroup(
            bottomLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(bottomLayout.createSequentialGroup()
                .addComponent(descLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 63, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(top, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(bottom, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(top, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(bottom, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    public static javax.swing.JPanel bottom;
    public static javax.swing.JLabel descLabel;
    public static javax.swing.JLabel titleLabel;
    private javax.swing.JPanel top;
    // End of variables declaration//GEN-END:variables
}
