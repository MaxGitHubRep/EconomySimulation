package economysimulation.classes.gui.mainpanels.hold;

import economysimulation.classes.global.Methods;
import economysimulation.classes.gui.subpanels.TaxRevenueList;
/**
 *
 * @author Max Carter
 */
public class RateHold extends javax.swing.JPanel {

    //<editor-fold defaultstate="collapsed" desc="Constructor."> 
    public RateHold() {
        initComponents();
        Methods.addToFrontPanel(backPanelGov, Methods.RateDisplay, false);
        Methods.addToFrontPanel(taxResultPanel, new TaxRevenueList(), false);
        
    }//</editor-fold>

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        taxResultPanel = new javax.swing.JPanel();
        backPanelGov = new javax.swing.JPanel();

        setBackground(new java.awt.Color(51, 51, 51));
        setOpaque(false);

        taxResultPanel.setOpaque(false);

        javax.swing.GroupLayout taxResultPanelLayout = new javax.swing.GroupLayout(taxResultPanel);
        taxResultPanel.setLayout(taxResultPanelLayout);
        taxResultPanelLayout.setHorizontalGroup(
            taxResultPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1080, Short.MAX_VALUE)
        );
        taxResultPanelLayout.setVerticalGroup(
            taxResultPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 392, Short.MAX_VALUE)
        );

        backPanelGov.setOpaque(false);

        javax.swing.GroupLayout backPanelGovLayout = new javax.swing.GroupLayout(backPanelGov);
        backPanelGov.setLayout(backPanelGovLayout);
        backPanelGovLayout.setHorizontalGroup(
            backPanelGovLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1100, Short.MAX_VALUE)
        );
        backPanelGovLayout.setVerticalGroup(
            backPanelGovLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 358, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(backPanelGov, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(taxResultPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(backPanelGov, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 37, Short.MAX_VALUE)
                .addComponent(taxResultPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel backPanelGov;
    public static javax.swing.JPanel taxResultPanel;
    // End of variables declaration//GEN-END:variables
}
