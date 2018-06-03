package economysimulation.classes.fronter;

import economysimulation.classes.MainFrame;
import economysimulation.classes.Methods;
import economysimulation.classes.algorithms.Component;
import economysimulation.classes.subpanels.QBudget;
import economysimulation.classes.subpanels.QGovernment;

/**
 *
 * @author Max Carter
 */
public class IntroPage extends javax.swing.JPanel {

    public IntroPage() {
        initComponents();

        Methods.budgetClass = new QBudget();
        Methods.govClass = new QGovernment();
        
        Methods.addToFrontPanel(govPanel, Methods.govClass, false);
        Methods.addToFrontPanel(budgetPanel, Methods.budgetClass, false);
          
    }

    public static void preDefineVariables() {
        //Defines all variables for use in the upcomming PBudget class at game launch.
        for (int i = 0; i < 8; i++) {
            Component.BUDGET_VARS[i] = QBudget.sliders[i].getValue();
        }

        Component.INTEREST_RATE = QGovernment.getSliderValue(0);
        Component.CORP_TAX = QGovernment.getSliderValue(1);
        Component.CONS_TAX = QGovernment.getSliderValue(2);
        //Methods.PENSIONS = QGovernment.getSliderValue(3);
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        subTitle = new javax.swing.JLabel();
        beginGame = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        budgetPanel = new javax.swing.JPanel();
        govPanel = new javax.swing.JPanel();
        jSeparator1 = new javax.swing.JSeparator();

        setBackground(new java.awt.Color(51, 51, 51));
        setOpaque(false);
        setPreferredSize(new java.awt.Dimension(1400, 800));

        subTitle.setFont(new java.awt.Font("Agency FB", 1, 64)); // NOI18N
        subTitle.setForeground(new java.awt.Color(204, 0, 0));
        subTitle.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        subTitle.setText("How To Play");

        beginGame.setBackground(new java.awt.Color(51, 51, 51));
        beginGame.setFont(new java.awt.Font("Agency FB", 0, 36)); // NOI18N
        beginGame.setForeground(new java.awt.Color(204, 0, 0));
        beginGame.setText("Launch Simulation");
        beginGame.setFocusable(false);
        beginGame.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                beginGameActionPerformed(evt);
            }
        });

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Objective of Game", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Agency FB", 0, 36), new java.awt.Color(204, 0, 0))); // NOI18N
        jPanel1.setOpaque(false);

        jLabel1.setText("<hold>");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        budgetPanel.setOpaque(false);

        javax.swing.GroupLayout budgetPanelLayout = new javax.swing.GroupLayout(budgetPanel);
        budgetPanel.setLayout(budgetPanelLayout);
        budgetPanelLayout.setHorizontalGroup(
            budgetPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        budgetPanelLayout.setVerticalGroup(
            budgetPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 406, Short.MAX_VALUE)
        );

        govPanel.setOpaque(false);

        javax.swing.GroupLayout govPanelLayout = new javax.swing.GroupLayout(govPanel);
        govPanel.setLayout(govPanelLayout);
        govPanelLayout.setHorizontalGroup(
            govPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        govPanelLayout.setVerticalGroup(
            govPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 354, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(beginGame, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(subTitle, javax.swing.GroupLayout.DEFAULT_SIZE, 280, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addComponent(jSeparator1, javax.swing.GroupLayout.DEFAULT_SIZE, 1084, Short.MAX_VALUE))
                    .addComponent(govPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(budgetPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(subTitle)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(beginGame))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(govPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 6, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(budgetPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    private void beginGameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_beginGameActionPerformed
        try {
            preDefineVariables();
            MainFrame.addToMainFrame(new GameHold());
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }//GEN-LAST:event_beginGameActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton beginGame;
    private javax.swing.JPanel budgetPanel;
    private javax.swing.JPanel govPanel;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JLabel subTitle;
    // End of variables declaration//GEN-END:variables
}
