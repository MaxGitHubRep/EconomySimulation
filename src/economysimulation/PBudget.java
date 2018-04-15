package economysimulation;

import java.text.DecimalFormat;
import javax.swing.JLabel;
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

    private DecimalFormat format = new DecimalFormat("0");
    
    private static String[] titles = new String[]{ "NHS", "Education", "Transport", "Food", "Infrastructure", "Defence", "Science", "Benefits", "Debt Interest" }; 
    private static JSlider[] sliders;
    private static JLabel[] percents;
    private static JLabel[] values;
    
    public static int getMoneySpent() {
        int count = 0;
        
        for (JSlider slider : sliders) {
            count += slider.getValue();
        }
        
        return count;
    }
    
    public static void displayGovSpendingGraph() {
        JFreeChart chart;
        DefaultPieDataset datasetPie = new DefaultPieDataset();
        
        for (int i = 0; i < titles.length-1; i++) {
            //datasetPie.insertValue(i, titles[i], sliders[i].getValue());
        }
        
        if (use3D.isSelected()) {
            chart = ChartFactory.createPieChart3D("Annual Budget", datasetPie);
        } else {
            chart = ChartFactory.createPieChart("Annual Budget", datasetPie);
        }
        
        Methods.applyChartTheme(chart, false);
        Methods.addChartToPanel(chart, graphPanel);
    }

    private void updateValueLabels(int id) {
        values[id].setText("£" + sliders[id].getValue() + "bn");
        percents[id].setText((format.format(((double) sliders[id].getValue() / Methods.ANNUAL_BUDGET) * 100)) + "%");
        subTitle.setText("£" + getMoneySpent() + "/" + Methods.ANNUAL_BUDGET + " Billion");
    }
    
    //<editor-fold defaultstate="collapsed" desc="Slider Event">   
    private void addSliderListener(JSlider slider, int id) { 
        slider.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                updateValueLabels(id);
            }
        });
        
    }//</editor-fold> 
    
    public PBudget() {
        initComponents();
        displayGovSpendingGraph();
        
        sliders = new JSlider[]{ slider1 };
        values = new JLabel[]{ value1 };
        percents = new JLabel[]{ percent1 };
        
        for (int i = 0; i < sliders.length; i++) {
            sliders[i].setMaximum(Methods.ANNUAL_BUDGET);
            addSliderListener(sliders[i], i);
            updateValueLabels(i);
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jSeparator1 = new javax.swing.JSeparator();
        subTitle1 = new javax.swing.JLabel();
        graphPanel = new javax.swing.JPanel();
        jSeparator2 = new javax.swing.JSeparator();
        panel1 = new javax.swing.JPanel();
        slider1 = new javax.swing.JSlider();
        percent1 = new javax.swing.JLabel();
        value1 = new javax.swing.JLabel();
        panel2 = new javax.swing.JPanel();
        slider2 = new javax.swing.JSlider();
        percent2 = new javax.swing.JLabel();
        value2 = new javax.swing.JLabel();
        panel3 = new javax.swing.JPanel();
        slider3 = new javax.swing.JSlider();
        percent3 = new javax.swing.JLabel();
        value3 = new javax.swing.JLabel();
        panel4 = new javax.swing.JPanel();
        slider4 = new javax.swing.JSlider();
        percent4 = new javax.swing.JLabel();
        value4 = new javax.swing.JLabel();
        panel5 = new javax.swing.JPanel();
        slider5 = new javax.swing.JSlider();
        percent5 = new javax.swing.JLabel();
        value5 = new javax.swing.JLabel();
        panel6 = new javax.swing.JPanel();
        slider6 = new javax.swing.JSlider();
        percent6 = new javax.swing.JLabel();
        value6 = new javax.swing.JLabel();
        panel7 = new javax.swing.JPanel();
        slider7 = new javax.swing.JSlider();
        percent7 = new javax.swing.JLabel();
        value7 = new javax.swing.JLabel();
        panel8 = new javax.swing.JPanel();
        slider8 = new javax.swing.JSlider();
        percent8 = new javax.swing.JLabel();
        value8 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        subTitle = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        use3D = new javax.swing.JCheckBox();

        setBackground(new java.awt.Color(51, 51, 51));

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

        panel1.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "NHS", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Agency FB", 1, 24), new java.awt.Color(255, 255, 255))); // NOI18N
        panel1.setOpaque(false);

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

        javax.swing.GroupLayout panel1Layout = new javax.swing.GroupLayout(panel1);
        panel1.setLayout(panel1Layout);
        panel1Layout.setHorizontalGroup(
            panel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
            .addGroup(panel1Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(slider1, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(22, 22, 22))
            .addComponent(percent1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(value1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 83, Short.MAX_VALUE)
        );
        panel1Layout.setVerticalGroup(
            panel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panel1Layout.createSequentialGroup()
                .addComponent(slider1, javax.swing.GroupLayout.PREFERRED_SIZE, 280, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(percent1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(value1))
        );

        panel2.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Education", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Agency FB", 1, 24), new java.awt.Color(255, 255, 255))); // NOI18N
        panel2.setOpaque(false);

        slider2.setMaximum(750);
        slider2.setOrientation(javax.swing.JSlider.VERTICAL);
        slider2.setOpaque(false);

        percent2.setFont(new java.awt.Font("Agency FB", 1, 30)); // NOI18N
        percent2.setForeground(new java.awt.Color(204, 0, 0));
        percent2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        percent2.setText("99%");

        value2.setFont(new java.awt.Font("Agency FB", 1, 30)); // NOI18N
        value2.setForeground(new java.awt.Color(204, 0, 0));
        value2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        value2.setText("£750bn");

        javax.swing.GroupLayout panel2Layout = new javax.swing.GroupLayout(panel2);
        panel2.setLayout(panel2Layout);
        panel2Layout.setHorizontalGroup(
            panel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panel2Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(slider2, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(22, 22, 22))
            .addComponent(percent2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(value2, javax.swing.GroupLayout.DEFAULT_SIZE, 83, Short.MAX_VALUE)
        );
        panel2Layout.setVerticalGroup(
            panel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panel2Layout.createSequentialGroup()
                .addComponent(slider2, javax.swing.GroupLayout.PREFERRED_SIZE, 280, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(percent2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(value2))
        );

        panel3.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Transport", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Agency FB", 1, 24), new java.awt.Color(255, 255, 255))); // NOI18N
        panel3.setOpaque(false);

        slider3.setMaximum(750);
        slider3.setOrientation(javax.swing.JSlider.VERTICAL);
        slider3.setOpaque(false);

        percent3.setFont(new java.awt.Font("Agency FB", 1, 30)); // NOI18N
        percent3.setForeground(new java.awt.Color(204, 0, 0));
        percent3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        percent3.setText("99%");

        value3.setFont(new java.awt.Font("Agency FB", 1, 30)); // NOI18N
        value3.setForeground(new java.awt.Color(204, 0, 0));
        value3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        value3.setText("£750bn");

        javax.swing.GroupLayout panel3Layout = new javax.swing.GroupLayout(panel3);
        panel3.setLayout(panel3Layout);
        panel3Layout.setHorizontalGroup(
            panel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panel3Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(slider3, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(22, 22, 22))
            .addComponent(percent3, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(value3, javax.swing.GroupLayout.DEFAULT_SIZE, 83, Short.MAX_VALUE)
        );
        panel3Layout.setVerticalGroup(
            panel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panel3Layout.createSequentialGroup()
                .addComponent(slider3, javax.swing.GroupLayout.PREFERRED_SIZE, 280, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(percent3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(value3))
        );

        panel4.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Food", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Agency FB", 1, 24), new java.awt.Color(255, 255, 255))); // NOI18N
        panel4.setOpaque(false);

        slider4.setMaximum(750);
        slider4.setOrientation(javax.swing.JSlider.VERTICAL);
        slider4.setOpaque(false);

        percent4.setFont(new java.awt.Font("Agency FB", 1, 30)); // NOI18N
        percent4.setForeground(new java.awt.Color(204, 0, 0));
        percent4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        percent4.setText("99%");

        value4.setFont(new java.awt.Font("Agency FB", 1, 30)); // NOI18N
        value4.setForeground(new java.awt.Color(204, 0, 0));
        value4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        value4.setText("£750bn");

        javax.swing.GroupLayout panel4Layout = new javax.swing.GroupLayout(panel4);
        panel4.setLayout(panel4Layout);
        panel4Layout.setHorizontalGroup(
            panel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panel4Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(slider4, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(22, 22, 22))
            .addComponent(percent4, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(value4, javax.swing.GroupLayout.DEFAULT_SIZE, 83, Short.MAX_VALUE)
        );
        panel4Layout.setVerticalGroup(
            panel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panel4Layout.createSequentialGroup()
                .addComponent(slider4, javax.swing.GroupLayout.PREFERRED_SIZE, 280, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(percent4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(value4))
        );

        panel5.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Infrastructure", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Agency FB", 1, 16), new java.awt.Color(255, 255, 255))); // NOI18N
        panel5.setOpaque(false);

        slider5.setMaximum(750);
        slider5.setOrientation(javax.swing.JSlider.VERTICAL);
        slider5.setOpaque(false);

        percent5.setFont(new java.awt.Font("Agency FB", 1, 30)); // NOI18N
        percent5.setForeground(new java.awt.Color(204, 0, 0));
        percent5.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        percent5.setText("99%");

        value5.setFont(new java.awt.Font("Agency FB", 1, 30)); // NOI18N
        value5.setForeground(new java.awt.Color(204, 0, 0));
        value5.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        value5.setText("£750bn");

        javax.swing.GroupLayout panel5Layout = new javax.swing.GroupLayout(panel5);
        panel5.setLayout(panel5Layout);
        panel5Layout.setHorizontalGroup(
            panel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panel5Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(slider5, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(22, 22, 22))
            .addComponent(percent5, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(value5, javax.swing.GroupLayout.DEFAULT_SIZE, 83, Short.MAX_VALUE)
        );
        panel5Layout.setVerticalGroup(
            panel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panel5Layout.createSequentialGroup()
                .addComponent(slider5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(percent5)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(value5))
        );

        panel6.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Defence", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Agency FB", 1, 24), new java.awt.Color(255, 255, 255))); // NOI18N
        panel6.setOpaque(false);

        slider6.setMaximum(750);
        slider6.setOrientation(javax.swing.JSlider.VERTICAL);
        slider6.setOpaque(false);

        percent6.setFont(new java.awt.Font("Agency FB", 1, 30)); // NOI18N
        percent6.setForeground(new java.awt.Color(204, 0, 0));
        percent6.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        percent6.setText("99%");

        value6.setFont(new java.awt.Font("Agency FB", 1, 30)); // NOI18N
        value6.setForeground(new java.awt.Color(204, 0, 0));
        value6.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        value6.setText("£750bn");

        javax.swing.GroupLayout panel6Layout = new javax.swing.GroupLayout(panel6);
        panel6.setLayout(panel6Layout);
        panel6Layout.setHorizontalGroup(
            panel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panel6Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(slider6, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(22, 22, 22))
            .addComponent(percent6, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(value6, javax.swing.GroupLayout.DEFAULT_SIZE, 83, Short.MAX_VALUE)
        );
        panel6Layout.setVerticalGroup(
            panel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panel6Layout.createSequentialGroup()
                .addComponent(slider6, javax.swing.GroupLayout.PREFERRED_SIZE, 280, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(percent6)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(value6))
        );

        panel7.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Science", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Agency FB", 1, 24), new java.awt.Color(255, 255, 255))); // NOI18N
        panel7.setOpaque(false);

        slider7.setMaximum(750);
        slider7.setOrientation(javax.swing.JSlider.VERTICAL);
        slider7.setOpaque(false);

        percent7.setFont(new java.awt.Font("Agency FB", 1, 30)); // NOI18N
        percent7.setForeground(new java.awt.Color(204, 0, 0));
        percent7.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        percent7.setText("99%");

        value7.setFont(new java.awt.Font("Agency FB", 1, 30)); // NOI18N
        value7.setForeground(new java.awt.Color(204, 0, 0));
        value7.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        value7.setText("£750bn");

        javax.swing.GroupLayout panel7Layout = new javax.swing.GroupLayout(panel7);
        panel7.setLayout(panel7Layout);
        panel7Layout.setHorizontalGroup(
            panel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panel7Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(slider7, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(22, 22, 22))
            .addComponent(percent7, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(value7, javax.swing.GroupLayout.DEFAULT_SIZE, 83, Short.MAX_VALUE)
        );
        panel7Layout.setVerticalGroup(
            panel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panel7Layout.createSequentialGroup()
                .addComponent(slider7, javax.swing.GroupLayout.PREFERRED_SIZE, 280, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(percent7)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(value7))
        );

        panel8.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Benefits", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Agency FB", 1, 24), new java.awt.Color(255, 255, 255))); // NOI18N
        panel8.setOpaque(false);

        slider8.setMaximum(750);
        slider8.setOrientation(javax.swing.JSlider.VERTICAL);
        slider8.setOpaque(false);

        percent8.setFont(new java.awt.Font("Agency FB", 1, 30)); // NOI18N
        percent8.setForeground(new java.awt.Color(204, 0, 0));
        percent8.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        percent8.setText("99%");

        value8.setFont(new java.awt.Font("Agency FB", 1, 30)); // NOI18N
        value8.setForeground(new java.awt.Color(204, 0, 0));
        value8.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        value8.setText("£750bn");

        javax.swing.GroupLayout panel8Layout = new javax.swing.GroupLayout(panel8);
        panel8.setLayout(panel8Layout);
        panel8Layout.setHorizontalGroup(
            panel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panel8Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(slider8, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(22, 22, 22))
            .addComponent(percent8, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(value8, javax.swing.GroupLayout.DEFAULT_SIZE, 83, Short.MAX_VALUE)
        );
        panel8Layout.setVerticalGroup(
            panel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panel8Layout.createSequentialGroup()
                .addComponent(slider8, javax.swing.GroupLayout.PREFERRED_SIZE, 280, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(percent8)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(value8))
        );

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Annual Budget", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Agency FB", 1, 36), new java.awt.Color(204, 0, 0))); // NOI18N
        jPanel1.setOpaque(false);

        subTitle.setFont(new java.awt.Font("Agency FB", 1, 24)); // NOI18N
        subTitle.setForeground(new java.awt.Color(204, 0, 0));
        subTitle.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        subTitle.setText("£X bn");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(subTitle, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(subTitle)
        );

        jPanel2.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(255, 255, 255), 2, true));
        jPanel2.setOpaque(false);

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

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(use3D, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(use3D))
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
                            .addComponent(subTitle1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 1056, Short.MAX_VALUE)))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(21, 21, 21)
                        .addComponent(jSeparator2))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(panel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(panel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(panel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(panel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(panel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(panel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(panel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(panel8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(subTitle1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(panel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(panel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(panel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(panel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(panel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(panel6, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(panel7, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(panel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
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
    private static javax.swing.JPanel graphPanel;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JPanel panel1;
    private javax.swing.JPanel panel2;
    private javax.swing.JPanel panel3;
    private javax.swing.JPanel panel4;
    private javax.swing.JPanel panel5;
    private javax.swing.JPanel panel6;
    private javax.swing.JPanel panel7;
    private javax.swing.JPanel panel8;
    private javax.swing.JLabel percent1;
    private javax.swing.JLabel percent2;
    private javax.swing.JLabel percent3;
    private javax.swing.JLabel percent4;
    private javax.swing.JLabel percent5;
    private javax.swing.JLabel percent6;
    private javax.swing.JLabel percent7;
    private javax.swing.JLabel percent8;
    private static javax.swing.JSlider slider1;
    private static javax.swing.JSlider slider2;
    private static javax.swing.JSlider slider3;
    private static javax.swing.JSlider slider4;
    private static javax.swing.JSlider slider5;
    private static javax.swing.JSlider slider6;
    private static javax.swing.JSlider slider7;
    private static javax.swing.JSlider slider8;
    private javax.swing.JLabel subTitle;
    private javax.swing.JLabel subTitle1;
    private static javax.swing.JCheckBox use3D;
    private javax.swing.JLabel value1;
    private javax.swing.JLabel value2;
    private javax.swing.JLabel value3;
    private javax.swing.JLabel value4;
    private javax.swing.JLabel value5;
    private javax.swing.JLabel value6;
    private javax.swing.JLabel value7;
    private javax.swing.JLabel value8;
    // End of variables declaration//GEN-END:variables
}
