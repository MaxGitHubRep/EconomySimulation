package economysimulation;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.JFreeChart;
import org.jfree.data.general.DefaultPieDataset;

/**
 *
 * @author Max Carter
 */
public class PBudget extends javax.swing.JPanel {

    private static void setGraphType() {
        
    }

    public static void displayGovSpending() {
        
        DefaultPieDataset datasetPie = new DefaultPieDataset();
        
        datasetPie.insertValue(0, "NHS", 200);
        
        JFreeChart chart;
        
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
        displayGovSpending();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        chartType = new javax.swing.ButtonGroup();
        subTitle = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        subTitle1 = new javax.swing.JLabel();
        graphPanel = new javax.swing.JPanel();
        usePie = new javax.swing.JRadioButton();
        useDonut = new javax.swing.JRadioButton();
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
            .addGap(0, 0, Short.MAX_VALUE)
        );
        graphPanelLayout.setVerticalGroup(
            graphPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 569, Short.MAX_VALUE)
        );

        chartType.add(usePie);
        usePie.setFont(new java.awt.Font("Agency FB", 1, 24)); // NOI18N
        usePie.setForeground(new java.awt.Color(255, 255, 255));
        usePie.setText("Pie Chart");
        usePie.setOpaque(false);

        chartType.add(useDonut);
        useDonut.setFont(new java.awt.Font("Agency FB", 1, 24)); // NOI18N
        useDonut.setForeground(new java.awt.Color(255, 255, 255));
        useDonut.setSelected(true);
        useDonut.setText("Donut Chart");
        useDonut.setOpaque(false);

        use3D.setFont(new java.awt.Font("Agency FB", 1, 24)); // NOI18N
        use3D.setForeground(new java.awt.Color(255, 255, 255));
        use3D.setSelected(true);
        use3D.setText("3D Graph");
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
                    .addComponent(graphPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(subTitle, javax.swing.GroupLayout.PREFERRED_SIZE, 750, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 56, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(usePie, javax.swing.GroupLayout.PREFERRED_SIZE, 127, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(useDonut))
                            .addComponent(use3D, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
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
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(subTitle)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(usePie, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(useDonut, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(use3D)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(graphPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    private void use3DActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_use3DActionPerformed
        displayGovSpending();
    }//GEN-LAST:event_use3DActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.ButtonGroup chartType;
    private static javax.swing.JPanel graphPanel;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JLabel subTitle;
    private javax.swing.JLabel subTitle1;
    private static javax.swing.JCheckBox use3D;
    private javax.swing.JRadioButton useDonut;
    private javax.swing.JRadioButton usePie;
    // End of variables declaration//GEN-END:variables
}
