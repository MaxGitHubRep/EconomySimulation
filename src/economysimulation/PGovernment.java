package economysimulation;

import java.util.ArrayList;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

/**
 *
 * @author Max Carter
 */
public class PGovernment extends javax.swing.JPanel {

    private static final String[] TITLES = new String[]{ "Interest Rates", "Exchange Rates", "Consumer Taxes", "Corporation Taxes", "Regulations", "Subsidies", "Government Spending", "Pensions" };
    private static final double[] VALUES = new double[]{ Methods.INTEREST_RATE, Methods.EXCHANGE_RATE, Methods.CONS_TAX, Methods.CORP_TAX, Methods.REGULATIONS, Methods.SUBSIDIES, Methods.GOV_SPENDING, Methods.PENSIONS };
    private static final ArrayList<Double>[] HISTORY = new ArrayList[]{ Methods.INTEREST_RATES, Methods.EXCHANGE_RATES, Methods.CONSUMER_TAXES, Methods.CORPORATION_TAXES, Methods.REGULATIONS_LIST, Methods.SUBSIDIES_LIST, Methods.GOV_SPENDING_LIST, Methods.PENSIONS_LIST };
    private static JPanel[] panelsBack;
    
    //<editor-fold defaultstate="collapsed" desc="Receives clock pulse."> 
    public static void globalClockPulseGov() {
        Methods.INTEREST_RATES.add(Methods.INTEREST_RATE);
        Methods.EXCHANGE_RATES.add(Methods.EXCHANGE_RATE);
//
//        Methods.createGraph("Interest Rates", Methods.INTEREST_RATES, graphPanelIR);
        
        for (int i = 0; i < 2; i++) {
            //history[i].add(values[i]);
            
            Methods.createGraph(TITLES[i], HISTORY[i], panelsBack[i]);
        }
        
    }//</editor-fold> 
  
    
    
    //<editor-fold defaultstate="collapsed" desc="Adjusts Interest Rates"> 
    private void adjustRates(String title, double value, JLabel output, JLabel minLabel, JLabel maxLabel, int tenth) {
        minLabel.setText(tenth + "%");
        maxLabel.setText((tenth + 1) + "%");
        output.setText(title + value + "%");
    }//</editor-fold> 

    private void sliderEditEvent(int id) {
        
        switch (id) {
            case 1:
                Methods.INTEREST_RATE = sliderIR.getValue() + ((double) sliderIRDec.getValue() / 10);
                adjustRates("Interest Rates: ", Methods.INTEREST_RATE, valueIR, minIR, maxIR, sliderIR.getValue());
            case 2:
        }
        
    }
    
    //<editor-fold defaultstate="collapsed" desc="Slider Event">   
    private void addSliderListener(JSlider slider, int id) { 
        slider.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                //adjustRates(sliderIR.getValue(), sliderIRDec.getValue());
                
                sliderEditEvent(id);
            }
        });
        
    }//</editor-fold> 
    
    //<editor-fold defaultstate="collapsed" desc="Constructor."> 
    public PGovernment() {
        initComponents();
        
        panelsBack = new JPanel[]{ graphPanelIR, graphPanelCT, graphPanelCT };

        addSliderListener(sliderIR, 1);
        addSliderListener(sliderIRDec, 1);
        
//        for (JSlider slider : new JSlider[]{ sliderIR, sliderIRDec, sliderCT, sliderCTDec }) {
//            addSliderListener(slider);
//        }
        
        
        //adjustRates(sliderIR.getValue(), sliderIRDec.getValue());
    }//</editor-fold>

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        subTitle = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        panelIR = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        sliderIR = new javax.swing.JSlider();
        valueIR = new javax.swing.JLabel();
        minIR = new javax.swing.JLabel();
        hunnit = new javax.swing.JLabel();
        sliderIRDec = new javax.swing.JSlider();
        zero1 = new javax.swing.JLabel();
        maxIR = new javax.swing.JLabel();
        graphPanelIR = new javax.swing.JPanel();
        panelCT = new javax.swing.JPanel();
        graphPanelCT = new javax.swing.JPanel();
        jPanel4 = new javax.swing.JPanel();
        sliderCT = new javax.swing.JSlider();
        valueIR3 = new javax.swing.JLabel();
        min1 = new javax.swing.JLabel();
        hunnit1 = new javax.swing.JLabel();
        sliderCTDec = new javax.swing.JSlider();
        zero2 = new javax.swing.JLabel();
        max1 = new javax.swing.JLabel();
        jSeparator2 = new javax.swing.JSeparator();
        panelER1 = new javax.swing.JPanel();
        graphPanelER = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        valueIR2 = new javax.swing.JLabel();
        jSeparator3 = new javax.swing.JSeparator();

        setBackground(new java.awt.Color(51, 51, 51));

        subTitle.setFont(new java.awt.Font("Agency FB", 1, 72)); // NOI18N
        subTitle.setForeground(new java.awt.Color(204, 0, 0));
        subTitle.setText("Government");

        panelIR.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Interest Rates", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Agency FB", 1, 36), new java.awt.Color(255, 255, 255))); // NOI18N
        panelIR.setOpaque(false);

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Control", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Agency FB", 1, 24), new java.awt.Color(255, 255, 255))); // NOI18N
        jPanel2.setOpaque(false);

        sliderIR.setForeground(new java.awt.Color(204, 51, 0));
        sliderIR.setMajorTickSpacing(1);
        sliderIR.setMaximum(99);
        sliderIR.setValue(0);
        sliderIR.setOpaque(false);

        valueIR.setFont(new java.awt.Font("Agency FB", 1, 30)); // NOI18N
        valueIR.setForeground(new java.awt.Color(255, 255, 255));
        valueIR.setText("Interest Rates: ");

        minIR.setFont(new java.awt.Font("Agency FB", 1, 30)); // NOI18N
        minIR.setForeground(new java.awt.Color(255, 255, 255));
        minIR.setText("0%");

        hunnit.setFont(new java.awt.Font("Agency FB", 1, 30)); // NOI18N
        hunnit.setForeground(new java.awt.Color(255, 255, 255));
        hunnit.setText("99%");

        sliderIRDec.setForeground(new java.awt.Color(204, 51, 0));
        sliderIRDec.setMajorTickSpacing(1);
        sliderIRDec.setMaximum(10);
        sliderIRDec.setValue(5);
        sliderIRDec.setOpaque(false);

        zero1.setFont(new java.awt.Font("Agency FB", 1, 30)); // NOI18N
        zero1.setForeground(new java.awt.Color(255, 255, 255));
        zero1.setText("0%");

        maxIR.setFont(new java.awt.Font("Agency FB", 1, 30)); // NOI18N
        maxIR.setForeground(new java.awt.Color(255, 255, 255));
        maxIR.setText("100%");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(sliderIR, javax.swing.GroupLayout.DEFAULT_SIZE, 239, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(zero1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(hunnit))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(12, 12, 12)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(valueIR, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(minIR)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(maxIR))))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(sliderIRDec, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addComponent(sliderIR, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(zero1)
                    .addComponent(hunnit))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(sliderIRDec, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(minIR)
                    .addComponent(maxIR))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(valueIR)
                .addContainerGap(180, Short.MAX_VALUE))
        );

        graphPanelIR.setOpaque(false);

        javax.swing.GroupLayout graphPanelIRLayout = new javax.swing.GroupLayout(graphPanelIR);
        graphPanelIR.setLayout(graphPanelIRLayout);
        graphPanelIRLayout.setHorizontalGroup(
            graphPanelIRLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 749, Short.MAX_VALUE)
        );
        graphPanelIRLayout.setVerticalGroup(
            graphPanelIRLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout panelIRLayout = new javax.swing.GroupLayout(panelIR);
        panelIR.setLayout(panelIRLayout);
        panelIRLayout.setHorizontalGroup(
            panelIRLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelIRLayout.createSequentialGroup()
                .addComponent(graphPanelIR, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        panelIRLayout.setVerticalGroup(
            panelIRLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(graphPanelIR, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        panelCT.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Consumer Taxes", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Agency FB", 1, 36), new java.awt.Color(255, 255, 255))); // NOI18N
        panelCT.setOpaque(false);

        graphPanelCT.setOpaque(false);

        javax.swing.GroupLayout graphPanelCTLayout = new javax.swing.GroupLayout(graphPanelCT);
        graphPanelCT.setLayout(graphPanelCTLayout);
        graphPanelCTLayout.setHorizontalGroup(
            graphPanelCTLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 748, Short.MAX_VALUE)
        );
        graphPanelCTLayout.setVerticalGroup(
            graphPanelCTLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );

        jPanel4.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Control", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Agency FB", 1, 24), new java.awt.Color(255, 255, 255))); // NOI18N
        jPanel4.setOpaque(false);

        sliderCT.setForeground(new java.awt.Color(204, 51, 0));
        sliderCT.setMajorTickSpacing(1);
        sliderCT.setMaximum(99);
        sliderCT.setValue(0);
        sliderCT.setOpaque(false);

        valueIR3.setFont(new java.awt.Font("Agency FB", 1, 30)); // NOI18N
        valueIR3.setForeground(new java.awt.Color(255, 255, 255));
        valueIR3.setText("Tax Rate:");

        min1.setFont(new java.awt.Font("Agency FB", 1, 30)); // NOI18N
        min1.setForeground(new java.awt.Color(255, 255, 255));
        min1.setText("0%");

        hunnit1.setFont(new java.awt.Font("Agency FB", 1, 30)); // NOI18N
        hunnit1.setForeground(new java.awt.Color(255, 255, 255));
        hunnit1.setText("99%");

        sliderCTDec.setForeground(new java.awt.Color(204, 51, 0));
        sliderCTDec.setMajorTickSpacing(1);
        sliderCTDec.setMaximum(10);
        sliderCTDec.setValue(5);
        sliderCTDec.setOpaque(false);

        zero2.setFont(new java.awt.Font("Agency FB", 1, 30)); // NOI18N
        zero2.setForeground(new java.awt.Color(255, 255, 255));
        zero2.setText("0%");

        max1.setFont(new java.awt.Font("Agency FB", 1, 30)); // NOI18N
        max1.setForeground(new java.awt.Color(255, 255, 255));
        max1.setText("100%");

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(sliderCT, javax.swing.GroupLayout.DEFAULT_SIZE, 239, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(zero2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(hunnit1))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGap(12, 12, 12)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(valueIR3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addComponent(min1)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(max1))))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(sliderCTDec, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addComponent(sliderCT, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(zero2)
                    .addComponent(hunnit1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(sliderCTDec, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(min1)
                    .addComponent(max1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(valueIR3)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout panelCTLayout = new javax.swing.GroupLayout(panelCT);
        panelCT.setLayout(panelCTLayout);
        panelCTLayout.setHorizontalGroup(
            panelCTLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelCTLayout.createSequentialGroup()
                .addGap(1, 1, 1)
                .addComponent(graphPanelCT, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        panelCTLayout.setVerticalGroup(
            panelCTLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(graphPanelCT, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        panelER1.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Exchange Rate", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Agency FB", 1, 36), new java.awt.Color(255, 255, 255))); // NOI18N
        panelER1.setOpaque(false);

        graphPanelER.setOpaque(false);

        javax.swing.GroupLayout graphPanelERLayout = new javax.swing.GroupLayout(graphPanelER);
        graphPanelER.setLayout(graphPanelERLayout);
        graphPanelERLayout.setHorizontalGroup(
            graphPanelERLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 749, Short.MAX_VALUE)
        );
        graphPanelERLayout.setVerticalGroup(
            graphPanelERLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Control", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Agency FB", 1, 24), new java.awt.Color(255, 255, 255))); // NOI18N
        jPanel3.setOpaque(false);

        valueIR2.setFont(new java.awt.Font("Agency FB", 1, 30)); // NOI18N
        valueIR2.setForeground(new java.awt.Color(255, 255, 255));
        valueIR2.setText("Exchange Rate: £1 = €1.14");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(valueIR2, javax.swing.GroupLayout.DEFAULT_SIZE, 238, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap(313, Short.MAX_VALUE)
                .addComponent(valueIR2)
                .addContainerGap())
        );

        javax.swing.GroupLayout panelER1Layout = new javax.swing.GroupLayout(panelER1);
        panelER1.setLayout(panelER1Layout);
        panelER1Layout.setHorizontalGroup(
            panelER1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelER1Layout.createSequentialGroup()
                .addGap(1, 1, 1)
                .addComponent(graphPanelER, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        panelER1Layout.setVerticalGroup(
            panelER1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(graphPanelER, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(panelCT, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jSeparator2)
                    .addComponent(jSeparator1)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(subTitle)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(panelIR, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jSeparator3))
                .addContainerGap())
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(panelER1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addContainerGap()))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(subTitle)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(panelIR, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(9, 9, 9)
                .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(483, 483, 483)
                .addComponent(jSeparator3, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(panelCT, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(1868, Short.MAX_VALUE))
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGap(609, 609, 609)
                    .addComponent(panelER1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(2355, Short.MAX_VALUE)))
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel graphPanelCT;
    private javax.swing.JPanel graphPanelER;
    private static javax.swing.JPanel graphPanelIR;
    private javax.swing.JLabel hunnit;
    private javax.swing.JLabel hunnit1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JSeparator jSeparator3;
    private javax.swing.JLabel max1;
    private javax.swing.JLabel maxIR;
    private javax.swing.JLabel min1;
    private javax.swing.JLabel minIR;
    private javax.swing.JPanel panelCT;
    private javax.swing.JPanel panelER1;
    private javax.swing.JPanel panelIR;
    private javax.swing.JSlider sliderCT;
    private javax.swing.JSlider sliderCTDec;
    private javax.swing.JSlider sliderIR;
    private javax.swing.JSlider sliderIRDec;
    private javax.swing.JLabel subTitle;
    private javax.swing.JLabel valueIR;
    private javax.swing.JLabel valueIR2;
    private javax.swing.JLabel valueIR3;
    private javax.swing.JLabel zero1;
    private javax.swing.JLabel zero2;
    // End of variables declaration//GEN-END:variables
}
