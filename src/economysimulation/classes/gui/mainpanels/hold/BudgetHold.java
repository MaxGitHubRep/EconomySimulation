package economysimulation.classes.gui.mainpanels.hold;

import economysimulation.classes.economy.sectors.Sector;
import economysimulation.classes.global.Methods;
import economysimulation.classes.economy.structure.Component;
import economysimulation.classes.gui.subpanels.BudgetList;
import java.awt.Color;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PiePlot;
import org.jfree.data.general.DefaultPieDataset;

/**
 *
 * @author Max Carter
 */
public class BudgetHold extends javax.swing.JPanel {

    private static PiePlot plot;
    private static JFreeChart pieChart;

    //<editor-fold defaultstate="collapsed" desc="Applies colours to the pie chart sections.">
    private static void applyPieChartColour(JFreeChart chart) {
        plot = (PiePlot) chart.getPlot();

        Color[] colourGuide = new Color[]{
                new Color(204, 0, 0),
                new Color(204, 0, 176),
                new Color(255, 255, 0),
                new Color(0, 128, 255),
                new Color(0, 255, 255),
                new Color(0, 255, 188),
                new Color(0, 204, 0),
                new Color(255, 128, 0)
        };

        for (int i = 0; i < colourGuide.length; i++) {
            plot.setSectionPaint(BudgetList.titles[i], colourGuide[i]);
        }
    }//</editor-fold> 
    
    //<editor-fold defaultstate="collapsed" desc="Creates pie chart.">
    public static void displaySpendingGraph() {
        if (Methods.TICKS > 0) {
            DefaultPieDataset datasetPie = new DefaultPieDataset();

            for (int i = 0; i < BudgetList.titles.length; i++) {
                datasetPie.insertValue(i, BudgetList.titles[i], Sector.SectorList[i].getSpending());
            }

            pieChart = ChartFactory.createPieChart3D("Total Budget Spending", datasetPie);

            Methods.applyChartTheme(pieChart, false);
            applyPieChartColour(pieChart);
            Methods.addChartToPanel(pieChart, graphPanel);
        }
    }//</editor-fold> 

    //<editor-fold defaultstate="collapsed" desc="Constructor.">   
    public BudgetHold() {
        initComponents();
        Methods.addToFrontPanel(backRatesPanel, Methods.BudgetDisplay, false);
        
        displaySpendingGraph();
    }//</editor-fold> 

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        graphPanel = new javax.swing.JPanel();
        backRatesPanel = new javax.swing.JPanel();

        setBackground(new java.awt.Color(51, 51, 51));
        setOpaque(false);

        graphPanel.setBackground(new java.awt.Color(51, 51, 51));
        graphPanel.setOpaque(false);

        javax.swing.GroupLayout graphPanelLayout = new javax.swing.GroupLayout(graphPanel);
        graphPanel.setLayout(graphPanelLayout);
        graphPanelLayout.setHorizontalGroup(
            graphPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1076, Short.MAX_VALUE)
        );
        graphPanelLayout.setVerticalGroup(
            graphPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 411, Short.MAX_VALUE)
        );

        backRatesPanel.setBackground(new java.awt.Color(51, 51, 51));
        backRatesPanel.setOpaque(false);

        javax.swing.GroupLayout backRatesPanelLayout = new javax.swing.GroupLayout(backRatesPanel);
        backRatesPanel.setLayout(backRatesPanelLayout);
        backRatesPanelLayout.setHorizontalGroup(
            backRatesPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1076, Short.MAX_VALUE)
        );
        backRatesPanelLayout.setVerticalGroup(
            backRatesPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 341, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(graphPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(backRatesPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(26, Short.MAX_VALUE)
                .addComponent(backRatesPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(graphPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel backRatesPanel;
    private static javax.swing.JPanel graphPanel;
    // End of variables declaration//GEN-END:variables
}
