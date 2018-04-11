package economysimulation;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.JFreeChart;
import org.jfree.data.general.DefaultPieDataset;

/**
 *
 * @author Max Carter
 */
public class PBudget extends javax.swing.JPanel {

    public static void displayGovSpendingGraph() {
        JFreeChart chart;
        DefaultPieDataset datasetPie = new DefaultPieDataset();
        
        //datasetPie.insertValue(0, "NHS", 200);
        
        /* Spending:
        Education
        Housing
        Transport
        Food & Agriculture
        Interest on Debt
        Military
        NHS
        Science
        Social Security & U/E
        */
        
        if (use3D.isSelected()) {
            chart = ChartFactory.createPieChart3D("Annual Budget", datasetPie);
        } else {
            chart = ChartFactory.createPieChart("Annual Budget", datasetPie);
        }
        
        Methods.applyChartTheme(chart, false);
        Methods.addChartToPanel(chart, graphPanel);
    }

    public PBudget() {
        initComponents();
        displayGovSpendingGraph();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        chartType = new javax.swing.ButtonGroup();
        subTitle = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        subTitle1 = new javax.swing.JLabel();
        graphPanel = new javax.swing.JPanel();
        use3D = new javax.swing.JCheckBox();

        setBackground(new java.awt.Color(51, 51, 51));

        subTitle.setFont(new java.awt.Font("Agency FB", 1, 72)); // NOI18N
        subTitle.setForeground(new java.awt.Color(204, 0, 0));
        subTitle.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        subTitle.setText("Annual Budget: £750 Billion");

        subTitle1.setFont(new java.awt.Font("Agency FB", 1, 72)); // NOI18N
        subTitle1.setForeground(new java.awt.Color(204, 0, 0));
        subTitle1.setText("Budget");

        graphPanel.setBackground(new java.awt.Color(51, 51, 51));

        javax.swing.GroupLayout graphPanelLayout = new javax.swing.GroupLayout(graphPanel);
        graphPanel.setLayout(graphPanelLayout);
        graphPanelLayout.setHorizontalGroup(
            graphPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 600, Short.MAX_VALUE)
        );
        graphPanelLayout.setVerticalGroup(
            graphPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 500, Short.MAX_VALUE)
        );

        use3D.setFont(new java.awt.Font("Agency FB", 1, 24)); // NOI18N
        use3D.setForeground(new java.awt.Color(255, 255, 255));
        use3D.setSelected(true);
        use3D.setText("3D Graph");
        use3D.setBorder(null);
        use3D.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        use3D.setOpaque(false);
        use3D.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                use3DActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jSeparator1)
                    .addComponent(subTitle1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(subTitle, javax.swing.GroupLayout.PREFERRED_SIZE, 750, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 213, Short.MAX_VALUE)
                        .addComponent(use3D))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(graphPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(subTitle1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(subTitle)
                    .addComponent(use3D))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(graphPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    private void use3DActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_use3DActionPerformed
        displayGovSpendingGraph();
    }//GEN-LAST:event_use3DActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.ButtonGroup chartType;
    private static javax.swing.JPanel graphPanel;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JLabel subTitle;
    private javax.swing.JLabel subTitle1;
    private static javax.swing.JCheckBox use3D;
    // End of variables declaration//GEN-END:variables
}
