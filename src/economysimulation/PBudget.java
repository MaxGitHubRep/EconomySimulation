package economysimulation;

import javax.swing.JSlider;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.JFreeChart;
import org.jfree.data.general.DefaultPieDataset;

/**
 *
 * @author Max Carter
 */
public class PBudget extends javax.swing.JPanel {

    private JSlider[] sliders;
    
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

    //<editor-fold defaultstate="collapsed" desc="Slider Event">   
    private void addSliderListener(JSlider slider, int id) { 
        slider.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                
            }
        });
        
    }//</editor-fold> 
    
    public PBudget() {
        initComponents();
        displayGovSpendingGraph();
        
        sliders = new JSlider[]{ slider1 };
        
        for (int i = 0; i < sliders.length; i++) {
            addSliderListener(sliders[i], i);
        }
        
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
        jSeparator2 = new javax.swing.JSeparator();
        jSeparator3 = new javax.swing.JSeparator();
        jPanel1 = new javax.swing.JPanel();
        slider1 = new javax.swing.JSlider();
        percent1 = new javax.swing.JLabel();
        value1 = new javax.swing.JLabel();

        setBackground(new java.awt.Color(51, 51, 51));

        subTitle.setFont(new java.awt.Font("Agency FB", 1, 72)); // NOI18N
        subTitle.setForeground(new java.awt.Color(204, 0, 0));
        subTitle.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
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
            .addGap(0, 482, Short.MAX_VALUE)
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

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "NHS", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Agency FB", 1, 24), new java.awt.Color(255, 255, 255))); // NOI18N
        jPanel1.setOpaque(false);

        slider1.setMaximum(750);
        slider1.setOrientation(javax.swing.JSlider.VERTICAL);
        slider1.setOpaque(false);

        percent1.setFont(new java.awt.Font("Agency FB", 1, 30)); // NOI18N
        percent1.setForeground(new java.awt.Color(204, 0, 0));
        percent1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        percent1.setText("99%");

        value1.setFont(new java.awt.Font("Agency FB", 1, 30)); // NOI18N
        value1.setForeground(new java.awt.Color(204, 0, 0));
        value1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        value1.setText("£750bn");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(slider1, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(22, 22, 22))
            .addComponent(percent1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(value1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 78, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(slider1, javax.swing.GroupLayout.PREFERRED_SIZE, 280, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(percent1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(value1))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(graphPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jSeparator1, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(subTitle1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 1056, Short.MAX_VALUE)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                .addComponent(subTitle, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(use3D))))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(21, 21, 21)
                        .addComponent(jSeparator2))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jSeparator3)))
                .addContainerGap())
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(subTitle1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(subTitle, javax.swing.GroupLayout.PREFERRED_SIZE, 87, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(use3D))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator3, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 21, Short.MAX_VALUE)
                .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(graphPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    private void use3DActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_use3DActionPerformed
        displayGovSpendingGraph();
    }//GEN-LAST:event_use3DActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.ButtonGroup chartType;
    private static javax.swing.JPanel graphPanel;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JSeparator jSeparator3;
    private javax.swing.JLabel percent1;
    private javax.swing.JSlider slider1;
    private javax.swing.JLabel subTitle;
    private javax.swing.JLabel subTitle1;
    private static javax.swing.JCheckBox use3D;
    private javax.swing.JLabel value1;
    // End of variables declaration//GEN-END:variables
}
