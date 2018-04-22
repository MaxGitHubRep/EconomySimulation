package economysimulation;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JSlider;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

/**
 *
 * @author Max Carter
 */
public class PGovernment extends javax.swing.JPanel {

    private static final String[] TITLES = new String[]{ "Interest Rates", "Consumer Taxes", "Corporation Taxes", "Pensions" };
    private static final double[] VALUES = new double[]{ Methods.INTEREST_RATE, Methods.CONS_TAX, Methods.CORP_TAX, Methods.PENSIONS};
    private static final ArrayList<Double>[] HISTORY = new ArrayList[]{ Methods.INTEREST_RATES, Methods.CONSUMER_TAXES, Methods.CORPORATION_TAXES, Methods.PENSIONS_LIST, };
    
    private static JButton[] graphButtons;
    private static JLabel[] valueLabels;
    private static JLabel[] mins;
    private static JLabel[] maxs;
    private static JSlider[] sliders;
    private static JSlider[] slidersDec;
    private static int graphCode = 0;
    
    //<editor-fold defaultstate="collapsed" desc="Receives clock pulse."> 
    public static void globalClockPulseGov() {
        Methods.INTEREST_RATES.add(Methods.INTEREST_RATE);
        Methods.CONSUMER_TAXES.add(Methods.CONS_TAX);
        Methods.CORPORATION_TAXES.add(Methods.CORP_TAX);
        Methods.PENSIONS_LIST.add(Methods.PENSIONS);

        Methods.createGraph(TITLES[graphCode], HISTORY[graphCode], graphPanel);
        
    }//</editor-fold> 

    //<editor-fold defaultstate="collapsed" desc="Adjusts rates of a specific component."> 
    private void adjustRates(double value, JLabel output, JLabel minLabel, JLabel maxLabel, int tenth) {
        minLabel.setText(tenth + "%");
        maxLabel.setText((tenth + 1) + "%");
        output.setText(value + "%");
    }//</editor-fold> 

    //<editor-fold defaultstate="collapsed" desc="Returns percent of a slider."> 
    private double getSliderValue(int id) {
        return sliders[id].getValue() + ((double) slidersDec[id].getValue() / 10);
    }//</editor-fold> 
    
    //<editor-fold defaultstate="collapsed" desc="Switch case that uses slider listener ID to change component."> 
    private void sliderEditEvent(int id) {
        double newValue = getSliderValue(id);
        
        switch (id) {
            case 0:
                Methods.INTEREST_RATE = newValue;
                break;
            case 1:
                Methods.CONS_TAX = newValue;
                break;
            case 2:
                Methods.CORP_TAX = newValue;
                break;
            case 3:
                Methods.PENSIONS = newValue;
                break;
            
        }
        adjustRates(newValue, valueLabels[id], mins[id], maxs[id], sliders[id].getValue());
    }//</editor-fold> 
    
    //<editor-fold defaultstate="collapsed" desc="Button listener to change graph type."> 
    private void addButtonListenerGraph(JButton button, int id) {
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                graphButtons[graphCode].setEnabled(true);
                graphCode = id;
                graphButtons[id].setEnabled(false);
            }
        });
    }//</editor-fold> 
    
    //<editor-fold defaultstate="collapsed" desc="Slider Event">   
    private void addSliderListener(JSlider slider, int id) { 
        slider.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                sliderEditEvent(id);
            }
        });
        
    }//</editor-fold> 
    
    //<editor-fold defaultstate="collapsed" desc="Constructor."> 
    public PGovernment() {
        initComponents();
        GameHold.addToFrontPanel(backPanelGov, new QGovernment(), false);
        
        graphButtons = new JButton[]{ QGovernment.historyIR, QGovernment.historyCT, QGovernment.historyCT2, QGovernment.historyP };
        sliders = new JSlider[]{ QGovernment.sliderIR, QGovernment.sliderCT, QGovernment.sliderCT2, QGovernment.sliderP };
        slidersDec = new JSlider[]{ QGovernment.sliderIRDec, QGovernment.sliderCTDec, QGovernment.sliderCT2Dec, QGovernment.sliderPDec };
        valueLabels = new JLabel[]{ QGovernment.valueIR, QGovernment.valueCT, QGovernment.valueCT2, QGovernment.valueP };
        mins = new JLabel[]{ QGovernment.minIR, QGovernment.minCT, QGovernment.minCT2, QGovernment.minP };
        maxs = new JLabel[]{ QGovernment.maxIR, QGovernment.maxCT, QGovernment.maxCT2, QGovernment.maxP };

        
        
        for (int i = 0; i < graphButtons.length; i++) {
            addButtonListenerGraph(graphButtons[i], i);
            addSliderListener(sliders[i], i);
            addSliderListener(slidersDec[i], i);
            valueLabels[i].setText(getSliderValue(i) + "%");
        }

        
    }//</editor-fold>

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        graphPanel = new javax.swing.JPanel();
        backPanelGov = new javax.swing.JPanel();

        setBackground(new java.awt.Color(51, 51, 51));

        graphPanel.setOpaque(false);

        javax.swing.GroupLayout graphPanelLayout = new javax.swing.GroupLayout(graphPanel);
        graphPanel.setLayout(graphPanelLayout);
        graphPanelLayout.setHorizontalGroup(
            graphPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1076, Short.MAX_VALUE)
        );
        graphPanelLayout.setVerticalGroup(
            graphPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );

        backPanelGov.setOpaque(false);

        javax.swing.GroupLayout backPanelGovLayout = new javax.swing.GroupLayout(backPanelGov);
        backPanelGov.setLayout(backPanelGovLayout);
        backPanelGovLayout.setHorizontalGroup(
            backPanelGovLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        backPanelGovLayout.setVerticalGroup(
            backPanelGovLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 358, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(graphPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
            .addComponent(backPanelGov, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(backPanelGov, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(graphPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel backPanelGov;
    private static javax.swing.JPanel graphPanel;
    // End of variables declaration//GEN-END:variables
}
