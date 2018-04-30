package economysimulation;

import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 *
 * @author Max
 */
public class QSideBar extends javax.swing.JPanel {

    public JLabel[] opButtons;
    public JPanel[] opPanels;
    
    //<editor-fold defaultstate="collapsed" desc="Constructor.">   
    public QSideBar() {
        initComponents();
        
        opButtons = new JLabel[]{ titleGov, titleBudget, titleCons, titleCorp };
        opPanels = new JPanel[]{
            new PGovernment(), 
            new PBudget(),
            new PExtra()
        };
        
        for (int i = 0; i < opPanels.length; i++) {
            Methods.addButtonFormat(opButtons[i], opPanels[i]);
        }
        
    }//</editor-fold>

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        titleGov = new javax.swing.JLabel();
        titleBudget = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        titleCorp = new javax.swing.JLabel();
        titleCons = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();

        setBackground(new java.awt.Color(255, 255, 255));

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Control Panel", 0, 0, new java.awt.Font("Agency FB", 1, 36), new java.awt.Color(204, 0, 0))); // NOI18N
        jPanel1.setOpaque(false);

        titleGov.setBackground(new java.awt.Color(102, 102, 102));
        titleGov.setFont(new java.awt.Font("Agency FB", 1, 36)); // NOI18N
        titleGov.setForeground(new java.awt.Color(255, 51, 0));
        titleGov.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        titleGov.setText("Government");
        titleGov.setBorder(new javax.swing.border.SoftBevelBorder(0));
        titleGov.setOpaque(true);

        titleBudget.setBackground(new java.awt.Color(102, 102, 102));
        titleBudget.setFont(new java.awt.Font("Agency FB", 1, 36)); // NOI18N
        titleBudget.setForeground(new java.awt.Color(255, 51, 0));
        titleBudget.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        titleBudget.setText("Budget");
        titleBudget.setBorder(new javax.swing.border.SoftBevelBorder(0));
        titleBudget.setOpaque(true);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(titleGov, javax.swing.GroupLayout.DEFAULT_SIZE, 240, Short.MAX_VALUE)
                    .addComponent(titleBudget, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(titleGov)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(titleBudget)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Determinants", 0, 0, new java.awt.Font("Agency FB", 1, 36), new java.awt.Color(204, 0, 0))); // NOI18N
        jPanel2.setOpaque(false);

        titleCorp.setBackground(new java.awt.Color(102, 102, 102));
        titleCorp.setFont(new java.awt.Font("Agency FB", 1, 36)); // NOI18N
        titleCorp.setForeground(new java.awt.Color(255, 51, 0));
        titleCorp.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        titleCorp.setText("Corporations");
        titleCorp.setBorder(new javax.swing.border.SoftBevelBorder(0));
        titleCorp.setOpaque(true);

        titleCons.setBackground(new java.awt.Color(102, 102, 102));
        titleCons.setFont(new java.awt.Font("Agency FB", 1, 36)); // NOI18N
        titleCons.setForeground(new java.awt.Color(255, 51, 0));
        titleCons.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        titleCons.setText("Consumers");
        titleCons.setBorder(new javax.swing.border.SoftBevelBorder(0));
        titleCons.setOpaque(true);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(titleCorp, javax.swing.GroupLayout.DEFAULT_SIZE, 240, Short.MAX_VALUE)
                    .addComponent(titleCons, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addComponent(titleCorp)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(titleCons)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jSeparator1))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 2, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JLabel titleBudget;
    private javax.swing.JLabel titleCons;
    private javax.swing.JLabel titleCorp;
    private javax.swing.JLabel titleGov;
    // End of variables declaration//GEN-END:variables
}
