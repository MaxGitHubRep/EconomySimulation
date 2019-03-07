package economysimulation.classes.gui.mainpanels.hold;

import economysimulation.classes.economy.budget.Budget;
import economysimulation.classes.economy.budget.MoneySpent;
import economysimulation.classes.economy.sectors.Sector;
import economysimulation.classes.global.Methods;
import static economysimulation.classes.global.Methods.SectorInstance;
import static economysimulation.classes.global.Methods.SideBarDisplay;
import economysimulation.classes.gui.fronter.ItemSelected;
import economysimulation.classes.gui.subpanels.BudgetList;
import java.awt.Color;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PiePlot;
import org.jfree.data.general.DefaultPieDataset;

/**
 * @author Max Carter
 */
public class BudgetHold extends javax.swing.JPanel implements ItemSelected, MoneySpent {

    //Chart data to display spending.
    private static PiePlot plot;
    private static JFreeChart pieChart;

    /** Creates a new BudgetHold. */ 
    public BudgetHold() {
        initComponents();
        Methods.addToFrontPanel(backRatesPanel, Methods.BudgetDisplay, false);
        
        SideBarDisplay.addItemSelectionListener(this);
        Budget.addMoneySpentListener(this);
    }

    @Override
    public void onMoneySpent(Sector sector, int money) {
        //updates the spending graph when money is spent.
        displaySpendingGraph();
    }

    @Override
    public void onItemSelected(ItemSelected selected) {
        //update spending graph when user enters the panel.
        if (selected == this) displaySpendingGraph();
    }

    /**
     * Applies colour them to the chart.
     * @param chart Chart to add colours to.
     */
    private void applyPieChartColour(JFreeChart chart) {
        plot = (PiePlot) chart.getPlot();

        //list of colours.
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

        //apply colour to sector on graph.
        for (int i = 0; i < colourGuide.length; i++) {
            plot.setSectionPaint(BudgetList.titles[i], colourGuide[i]);
        }
    }
    
    /** Display the spending graph. */
    public void displaySpendingGraph() {
        DefaultPieDataset datasetPie = new DefaultPieDataset();

        for (int i = 0; i < BudgetList.titles.length; i++) {
            datasetPie.insertValue(i, BudgetList.titles[i], SectorInstance.SectorList[i].getSpending());
        }

        //creates the pie chart.
        pieChart = ChartFactory.createPieChart3D("Total Budget Spending", datasetPie);

        Methods.applyChartTheme(pieChart);
        applyPieChartColour(pieChart);
        Methods.addChartToPanel(pieChart, graphPanel);
    }

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