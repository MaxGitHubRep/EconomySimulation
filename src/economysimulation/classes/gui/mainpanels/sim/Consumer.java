package economysimulation.classes.gui.mainpanels.sim;

/**
 *
 * @author Max Carter
 */
public class Consumer extends javax.swing.JPanel {

    //                                              percentage           int                               int                         int                  pbar              pbar                pbar
    public static String[] titles = new String[]{ "Employment Rate", "Consumption per Capita", "Disposable Income per Capita", "Borrowing per Capita", "Confidence", "Standard of Living", "Politcal Influence" };
    
    public Consumer() {
        initComponents();
        
        
        
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        setBackground(new java.awt.Color(51, 51, 51));
        setOpaque(false);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1108, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 777, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
