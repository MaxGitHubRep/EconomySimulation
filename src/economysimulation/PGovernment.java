package economysimulation;

import javax.swing.JSlider;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import org.jfree.data.category.DefaultCategoryDataset;

/**
 *
 * @author Max Carter
 */
public class PGovernment extends javax.swing.JPanel {

    public static double INTEREST_RATE;
    
    private final String INTEREST_RATES_FORMAT = "Interest Rates: %s";
    
    public static void globalClockPulseGov() {
        
    }
    
    public void createGraphIR() {
        final DefaultCategoryDataset dataSetIR = new DefaultCategoryDataset();
        
        
        
        
    }
    
    private void adjustInterestRates(int tenth, int dec) {
        min.setText(tenth + "%");
        max.setText((tenth + 1) + "%");
        INTEREST_RATE = tenth + ((double) dec / 10);
        valueIR.setText(String.format(INTEREST_RATES_FORMAT, INTEREST_RATE) + "%");
    }

    //<editor-fold defaultstate="collapsed" desc="Slider Event">   
    private void addSliderListener(JSlider slider) { 
        slider.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                
                adjustInterestRates(sliderIR.getValue(), sliderIRDec.getValue());
            }
        });
        
    }//</editor-fold> 
    
    public PGovernment() {
        initComponents();
        addSliderListener(sliderIR);
        addSliderListener(sliderIRDec);
    }

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
        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        sliderIR = new javax.swing.JSlider();
        valueIR = new javax.swing.JLabel();
        min = new javax.swing.JLabel();
        hunnit = new javax.swing.JLabel();
        sliderIRDec = new javax.swing.JSlider();
        zero1 = new javax.swing.JLabel();
        max = new javax.swing.JLabel();

        setBackground(new java.awt.Color(51, 51, 51));

        subTitle.setFont(new java.awt.Font("Agency FB", 1, 72)); // NOI18N
        subTitle.setForeground(new java.awt.Color(255, 255, 255));
        subTitle.setText("Government");

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Interest Rates", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Agency FB", 1, 36), new java.awt.Color(255, 255, 255))); // NOI18N
        jPanel1.setOpaque(false);

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

        min.setFont(new java.awt.Font("Agency FB", 1, 30)); // NOI18N
        min.setForeground(new java.awt.Color(255, 255, 255));
        min.setText("0%");

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

        max.setFont(new java.awt.Font("Agency FB", 1, 30)); // NOI18N
        max.setForeground(new java.awt.Color(255, 255, 255));
        max.setText("100%");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(sliderIR, javax.swing.GroupLayout.DEFAULT_SIZE, 319, Short.MAX_VALUE)
                    .addComponent(valueIR, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(zero1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(hunnit))
                    .addComponent(sliderIRDec, javax.swing.GroupLayout.DEFAULT_SIZE, 319, Short.MAX_VALUE)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(min)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(max)))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(sliderIR, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(hunnit)
                    .addComponent(zero1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(sliderIRDec, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(min)
                    .addComponent(max))
                .addGap(16, 16, 16)
                .addComponent(valueIR)
                .addContainerGap(210, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jSeparator1)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(subTitle, javax.swing.GroupLayout.PREFERRED_SIZE, 686, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 380, Short.MAX_VALUE))
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(subTitle)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(262, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel hunnit;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JLabel max;
    private javax.swing.JLabel min;
    private javax.swing.JSlider sliderIR;
    private javax.swing.JSlider sliderIRDec;
    private javax.swing.JLabel subTitle;
    private javax.swing.JLabel valueIR;
    private javax.swing.JLabel zero1;
    // End of variables declaration//GEN-END:variables
}
