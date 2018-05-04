package economysimulation.classes.panelsback;

import economysimulation.classes.Methods;
import economysimulation.classes.panelsfront.QBudget;
import java.awt.Color;
import java.text.DecimalFormat;
import javax.swing.JSlider;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PiePlot;
import org.jfree.data.general.DefaultPieDataset;

/**
 *
 * @author Max Carter
 */
public class PBudget extends javax.swing.JPanel {

    private DecimalFormat format = new DecimalFormat("0");
    private static PiePlot plot;
    private static JFreeChart pieChart;
    
    private static final Color[] colourGuide = new Color[]{
        new Color(204, 0, 0),
        new Color(204, 0, 176),
        new Color(255, 255, 0),
        new Color(0, 128, 255),
        new Color(0, 255, 255),
        new Color(0, 255, 188),
        new Color(0, 204, 0),
        new Color(255, 128, 0)
    };
    
    //<editor-fold defaultstate="collapsed" desc="Sets values/percents to corresponding colours on pie chart.">
    private static void applyLabelColours() {
        for (int i = 0; i < colourGuide.length; i++) {
            QBudget.percents[i].setForeground(colourGuide[i]);
            QBudget.values[i].setForeground(colourGuide[i]);
        }
    }//</editor-fold> 
    
    //<editor-fold defaultstate="collapsed" desc="Applies colours to the pie chart sections.">
    private static void applyPieChartColour(JFreeChart chart) {
        plot = (PiePlot) chart.getPlot();
        for (int i = 0; i < colourGuide.length; i++) {
            plot.setSectionPaint(QBudget.titles[i], colourGuide[i]);
        }
    }//</editor-fold> 
    
    //<editor-fold defaultstate="collapsed" desc="Creates pie chart.">
    public static void displayGovSpendingGraph() {
        DefaultPieDataset datasetPie = new DefaultPieDataset();
        
        for (int i = 0; i < QBudget.titles.length-1; i++) {
            datasetPie.insertValue(i, QBudget.titles[i], QBudget.sliders[i].getValue());
        }
        
        pieChart = ChartFactory.createPieChart3D("Annual Budget", datasetPie);

        Methods.applyChartTheme(pieChart, false);
        applyPieChartColour(pieChart);
        Methods.addChartToPanel(pieChart, graphPanel);
    }//</editor-fold> 

    //<editor-fold defaultstate="collapsed" desc="Slider Event">   
    private void addSliderListenerGraph(JSlider slider) { 
        slider.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                if (!slider.getValueIsAdjusting()) displayGovSpendingGraph();
            }
        });
        
    }//</editor-fold> 
    
    //<editor-fold defaultstate="collapsed" desc="Constructor.">   
    public PBudget() {
        initComponents();
        Methods.addToFrontPanel(backRatesPanel, Methods.budgetClass, false);
        
        for (JSlider slider : QBudget.sliders) {
            addSliderListenerGraph(slider);
        }
        
        QBudget.updateValueLabels();
        applyLabelColours();
        displayGovSpendingGraph();
    }//</editor-fold> 

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        graphPanel = new javax.swing.JPanel();
        backRatesPanel = new javax.swing.JPanel();

        setBackground(new java.awt.Color(51, 51, 51));

        graphPanel.setBackground(new java.awt.Color(51, 51, 51));

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

        javax.swing.GroupLayout backRatesPanelLayout = new javax.swing.GroupLayout(backRatesPanel);
        backRatesPanel.setLayout(backRatesPanelLayout);
        backRatesPanelLayout.setHorizontalGroup(
            backRatesPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        backRatesPanelLayout.setVerticalGroup(
            backRatesPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 406, Short.MAX_VALUE)
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
                .addComponent(backRatesPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(graphPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel backRatesPanel;
    private static javax.swing.JPanel graphPanel;
    // End of variables declaration//GEN-END:variables
}
