package economysimulation.classes.fronter;

import economysimulation.classes.MainFrame;
import economysimulation.classes.fronter.IntroPage;

/**
 *
 * @author Max Carter
 */
public class SelectionButtons extends javax.swing.JPanel {

    public SelectionButtons() {
        initComponents();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        singleplayer = new javax.swing.JButton();

        setOpaque(false);

        singleplayer.setFont(new java.awt.Font("Agency FB", 1, 36)); // NOI18N
        singleplayer.setForeground(new java.awt.Color(204, 0, 0));
        singleplayer.setText("<temp: Single Player>");
        singleplayer.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                singleplayerActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(singleplayer, javax.swing.GroupLayout.DEFAULT_SIZE, 476, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(singleplayer)
                .addContainerGap(434, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void singleplayerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_singleplayerActionPerformed
        MainFrame.addToMainFrame(new IntroPage());
    }//GEN-LAST:event_singleplayerActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton singleplayer;
    // End of variables declaration//GEN-END:variables
}
