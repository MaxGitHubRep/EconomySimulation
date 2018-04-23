package economysimulation;

import java.awt.Color;
import java.text.DecimalFormat;
import javax.swing.JLabel;
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
        new Color(255, 128, 0) };
    
    private static final String[] titles = new String[]{ "NHS", "Education", "Transport", "Food", "Infrastructure", "Defence", "Science", "Benefits", "Debt Interest" }; 
    private static JSlider[] sliders;
    private static JLabel[] percents;
    private static JLabel[] values;
    private static JLabel[] colourLabels;
    
    //<editor-fold defaultstate="collapsed" desc="Gets total money spent.">
    public static int getMoneySpent() {
        int count = 0;
        
        for (JSlider slider : sliders) {
            count += slider.getValue();
        }
        
        return count;
    }//</editor-fold> 
    
    //<editor-fold defaultstate="collapsed" desc="Sets values/percents to corresponding colours on pie chart.">
    private static void applyLabelColours() {
        for (int i = 0; i < colourGuide.length; i++) {
            percents[i].setForeground(colourGuide[i]);
            values[i].setForeground(colourGuide[i]);
            sliders[i].setValue(Methods.ANNUAL_BUDGET/sliders.length);
        }
    }//</editor-fold> 
    
    //<editor-fold defaultstate="collapsed" desc="Applies colours to the pie chart sections.">
    private static void applyPieChartColour(JFreeChart chart) {
        plot = (PiePlot) chart.getPlot();
        for (int i = 0; i < colourGuide.length; i++) {
            plot.setSectionPaint(titles[i], colourGuide[i]);
        }
    }//</editor-fold> 
    
    //<editor-fold defaultstate="collapsed" desc="Creates pie chart.">
    public static void displayGovSpendingGraph() {
        DefaultPieDataset datasetPie = new DefaultPieDataset();
        
        for (int i = 0; i < titles.length-1; i++) {
            datasetPie.insertValue(i, titles[i], sliders[i].getValue());
        }
        
        pieChart = ChartFactory.createPieChart3D("Annual Budget", datasetPie);

        Methods.applyChartTheme(pieChart, false);
        applyPieChartColour(pieChart);
        Methods.addChartToPanel(pieChart, graphPanel);
    }//</editor-fold> 

    //<editor-fold defaultstate="collapsed" desc="Updates the labels & percent values of each component.">
    private void updateValueLabels() {
        int allMoney = getMoneySpent();
        for (int id = 0; id < sliders.length; id++) {
            QBudget.pBarState.setValue(allMoney);
            QBudget.picState.setIcon(new javax.swing.ImageIcon(getClass().getResource("/economysimulation/resources/misc/" + (allMoney > Methods.ANNUAL_BUDGET ? "warning90" : "tick90") + ".png")));
            
            values[id].setText("£" + sliders[id].getValue() + "bn");
            percents[id].setText((format.format(((double) sliders[id].getValue() / allMoney) * 100)) + "%");
            
            QBudget.subTitle.setText("£" + allMoney + "/" + Methods.ANNUAL_BUDGET + "bn");
            QBudget.budgetPercent.setText(format.format(((double)allMoney/Methods.ANNUAL_BUDGET) * 100) +  "%");
            QBudget.difference.setText("£" + (Methods.ANNUAL_BUDGET - allMoney) + "bn");
            
            for (JLabel label : colourLabels) {
                label.setForeground(allMoney > Methods.ANNUAL_BUDGET ? Color.red : Color.green);
            }
        }
    }//</editor-fold> 
    
    //<editor-fold defaultstate="collapsed" desc="Slider Event">   
    private void addSliderListener(JSlider slider) { 
        slider.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                updateValueLabels();
                if (!slider.getValueIsAdjusting()) displayGovSpendingGraph();
            }
        });
        
    }//</editor-fold> 
 
    //<editor-fold defaultstate="collapsed" desc="Constructor.">   
    public PBudget() {
        initComponents();
        Methods.addToFrontPanel(backRatesPanel, Methods.budgetClass, false);
        
        sliders = new JSlider[]{ QBudget.slider1, QBudget.slider2, QBudget.slider3, QBudget.slider4, QBudget.slider5, QBudget.slider6, QBudget.slider7, QBudget.slider8 };
        values = new JLabel[]{ QBudget.value1, QBudget.value2, QBudget.value3, QBudget.value4, QBudget.value5, QBudget.value6, QBudget.value7, QBudget.value8 };
        percents = new JLabel[]{ QBudget.percent1, QBudget.percent2, QBudget.percent3, QBudget.percent4, QBudget.percent5, QBudget.percent6, QBudget.percent7, QBudget.percent8 };
        colourLabels = new JLabel[]{ QBudget.subTitle, QBudget.budgetPercent, QBudget.difference };

        for (JSlider slider : sliders) {
            slider.setMaximum(Methods.ANNUAL_BUDGET);
            addSliderListener(slider);
        }
        updateValueLabels();
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
