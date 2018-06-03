package economysimulation.classes.subpanels;

import economysimulation.classes.algorithms.Component;
import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

/**
 *
 * @author Max Carter
 */
public class QGovernment extends javax.swing.JPanel {

    public static int selectedType = 0;
    
    private static JPanel[] backPanels;
    private static JPanel[] colorPanels;
    private static JLabel[] arrowLabels;
    private static final String[] titles = new String[]{ "Interest Rates", "Corporation Tax", "Consumer Tax" };
    
    private static void applySelectedType(int id) {
        selectedType = id;
        title.setText(titles[id]);
        slider.setValue(id);
        saveChanges.setText("Save Changes");
        
        double newValue = 0;
        switch (id) {
            case 0:
                newValue = Component.INTEREST_RATE;
                break;
            case 1:
                newValue = Component.CORP_TAX;
                break;
            case 2:
                newValue = Component.CONS_TAX;
                break;
        }
        slider.setValue((int) newValue * 10);
        updatePercent();
        
    }
    
    //<editor-fold defaultstate="collapsed" desc="Formats the button to open different jPanel."> 
    public static void addButtonFormat(int id) {
        backPanels[id].addMouseListener(new MouseAdapter() {

            @Override 
            public void mouseEntered(MouseEvent e) {
                backPanels[id].setBackground(new Color(240, 240, 240));
                colorPanels[id].setBackground(new Color(240, 240, 240));
            }
            
            @Override
            public void mouseExited(MouseEvent e) {
                backPanels[id].setBackground(Color.white);
                colorPanels[id].setBackground(Color.white);
            }
            
            @Override
            public void mouseClicked(MouseEvent e) {
                applySelectedType(id);
                
                for (int i = 0; i < colorPanels.length; i++) {
                    if (i == id) {
                        arrowLabels[i].setIcon(new javax.swing.ImageIcon(getClass().getResource("/economysimulation/resources/misc/arrow40.png")));
                    } else {
                        arrowLabels[i].setIcon(null);
                    }
                }
                
            }
            
        });
    }//</editor-fold>
    
    //<editor-fold defaultstate="collapsed" desc="Formats the button to open different jPanel."> 
    public static void addSaveChangesFormat(JPanel picPanel, JPanel backPanel) {
        backPanel.addMouseListener(new MouseAdapter() {

            @Override 
            public void mouseEntered(MouseEvent e) {
                backPanel.setBackground(new Color(240, 240, 240));
                picPanel.setBackground(new Color(204, 0, 0));
            }
            
            @Override
            public void mouseExited(MouseEvent e) {
                backPanel.setBackground(Color.white);
                picPanel.setBackground(Color.white);
            }
            
            @Override
            public void mouseClicked(MouseEvent e) {
                int newValue = slider.getValue() / 10;
                
                switch (selectedType) {
                    case 0:
                        Component.INTEREST_RATE = newValue;
                        break;
                    case 1:
                        Component.CORP_TAX = newValue;
                        break;
                    case 2:
                        Component.CONS_TAX = newValue;
                        break;
                    
                }
                saveChanges.setText("Changes Saved");
            }
            
        });
    }//</editor-fold>
    
    //<editor-fold defaultstate="collapsed" desc="Slider Event">   
    private void addSliderListener(JSlider slider) { 
        slider.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                updatePercent();
            }
        });
        
    }//</editor-fold> 
    
    private static void updatePercent() {
        percent.setText((double) slider.getValue() / 10 + "%");
        
    }
    
    public QGovernment() {
        initComponents();
        
        backPanels = new JPanel[]{ panel1, panel2, panel3 };
        colorPanels = new JPanel[]{ color1, color2, color3 };
        arrowLabels = new JLabel[]{ arrow1, arrow2, arrow3 };
        
        addSaveChangesFormat(panelPic, saveChangesPanel);
        addSliderListener(slider);
        
        
        for (int i = 0; i < backPanels.length; i++) {
            addButtonFormat(i);
        }
        
        applySelectedType(0);
        
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        panel1 = new javax.swing.JPanel();
        title1 = new javax.swing.JLabel();
        color1 = new javax.swing.JPanel();
        arrow1 = new javax.swing.JLabel();
        panel2 = new javax.swing.JPanel();
        title2 = new javax.swing.JLabel();
        color2 = new javax.swing.JPanel();
        arrow2 = new javax.swing.JLabel();
        panel3 = new javax.swing.JPanel();
        title3 = new javax.swing.JLabel();
        color3 = new javax.swing.JPanel();
        arrow3 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        displayPanel = new javax.swing.JPanel();
        title = new javax.swing.JLabel();
        percent = new javax.swing.JLabel();
        slider = new javax.swing.JSlider();
        saveChangesPanel = new javax.swing.JPanel();
        panelPic = new javax.swing.JPanel();
        tickHold = new javax.swing.JLabel();
        saveChanges = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        jSeparator2 = new javax.swing.JSeparator();

        setBackground(new java.awt.Color(255, 255, 255));
        setOpaque(false);

        panel1.setBackground(new java.awt.Color(255, 255, 255));

        title1.setFont(new java.awt.Font("Agency FB", 0, 48)); // NOI18N
        title1.setForeground(new java.awt.Color(204, 0, 0));
        title1.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        title1.setText("Interest Rates");

        color1.setBackground(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout color1Layout = new javax.swing.GroupLayout(color1);
        color1.setLayout(color1Layout);
        color1Layout.setHorizontalGroup(
            color1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(arrow1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 40, Short.MAX_VALUE)
        );
        color1Layout.setVerticalGroup(
            color1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(arrow1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout panel1Layout = new javax.swing.GroupLayout(panel1);
        panel1.setLayout(panel1Layout);
        panel1Layout.setHorizontalGroup(
            panel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(title1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 70, Short.MAX_VALUE)
                .addComponent(color1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        panel1Layout.setVerticalGroup(
            panel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(color1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(panel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(title1, javax.swing.GroupLayout.DEFAULT_SIZE, 74, Short.MAX_VALUE)
                .addContainerGap())
        );

        panel2.setBackground(new java.awt.Color(255, 255, 255));

        title2.setFont(new java.awt.Font("Agency FB", 0, 48)); // NOI18N
        title2.setForeground(new java.awt.Color(204, 0, 0));
        title2.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        title2.setText("Corporation Tax");

        color2.setBackground(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout color2Layout = new javax.swing.GroupLayout(color2);
        color2.setLayout(color2Layout);
        color2Layout.setHorizontalGroup(
            color2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(arrow2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 40, Short.MAX_VALUE)
        );
        color2Layout.setVerticalGroup(
            color2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(arrow2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout panel2Layout = new javax.swing.GroupLayout(panel2);
        panel2.setLayout(panel2Layout);
        panel2Layout.setHorizontalGroup(
            panel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(title2, javax.swing.GroupLayout.DEFAULT_SIZE, 282, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(color2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        panel2Layout.setVerticalGroup(
            panel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(color2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(panel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(title2, javax.swing.GroupLayout.DEFAULT_SIZE, 74, Short.MAX_VALUE)
                .addContainerGap())
        );

        panel3.setBackground(new java.awt.Color(255, 255, 255));

        title3.setFont(new java.awt.Font("Agency FB", 0, 48)); // NOI18N
        title3.setForeground(new java.awt.Color(204, 0, 0));
        title3.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        title3.setText("Consumer Tax");

        color3.setBackground(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout color3Layout = new javax.swing.GroupLayout(color3);
        color3.setLayout(color3Layout);
        color3Layout.setHorizontalGroup(
            color3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(arrow3, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 40, Short.MAX_VALUE)
        );
        color3Layout.setVerticalGroup(
            color3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(arrow3, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout panel3Layout = new javax.swing.GroupLayout(panel3);
        panel3.setLayout(panel3Layout);
        panel3Layout.setHorizontalGroup(
            panel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(title3, javax.swing.GroupLayout.DEFAULT_SIZE, 282, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(color3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        panel3Layout.setVerticalGroup(
            panel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(color3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(panel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(title3, javax.swing.GroupLayout.DEFAULT_SIZE, 74, Short.MAX_VALUE)
                .addContainerGap())
        );

        jPanel1.setBackground(new java.awt.Color(204, 0, 0));

        displayPanel.setBackground(new java.awt.Color(255, 255, 255));

        title.setFont(new java.awt.Font("Agency FB", 0, 48)); // NOI18N
        title.setForeground(new java.awt.Color(204, 0, 0));
        title.setText("Tax");

        percent.setFont(new java.awt.Font("Agency FB", 0, 48)); // NOI18N
        percent.setForeground(new java.awt.Color(204, 0, 0));
        percent.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        percent.setText("99.9%");

        slider.setMaximum(1000);
        slider.setFocusable(false);
        slider.setOpaque(false);

        saveChangesPanel.setBackground(new java.awt.Color(255, 255, 255));

        panelPic.setBackground(new java.awt.Color(255, 255, 255));

        tickHold.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        tickHold.setIcon(new javax.swing.ImageIcon(getClass().getResource("/economysimulation/resources/misc/wtick50.png"))); // NOI18N

        javax.swing.GroupLayout panelPicLayout = new javax.swing.GroupLayout(panelPic);
        panelPic.setLayout(panelPicLayout);
        panelPicLayout.setHorizontalGroup(
            panelPicLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelPicLayout.createSequentialGroup()
                .addComponent(tickHold, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        panelPicLayout.setVerticalGroup(
            panelPicLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelPicLayout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(tickHold, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        saveChanges.setFont(new java.awt.Font("Agency FB", 0, 48)); // NOI18N
        saveChanges.setForeground(new java.awt.Color(204, 0, 0));
        saveChanges.setText("Save Changes");

        javax.swing.GroupLayout saveChangesPanelLayout = new javax.swing.GroupLayout(saveChangesPanel);
        saveChangesPanel.setLayout(saveChangesPanelLayout);
        saveChangesPanelLayout.setHorizontalGroup(
            saveChangesPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(saveChangesPanelLayout.createSequentialGroup()
                .addComponent(panelPic, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(saveChanges, javax.swing.GroupLayout.DEFAULT_SIZE, 265, Short.MAX_VALUE))
        );
        saveChangesPanelLayout.setVerticalGroup(
            saveChangesPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(saveChangesPanelLayout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addGroup(saveChangesPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(panelPic, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(saveChanges, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
        );

        javax.swing.GroupLayout displayPanelLayout = new javax.swing.GroupLayout(displayPanel);
        displayPanel.setLayout(displayPanelLayout);
        displayPanelLayout.setHorizontalGroup(
            displayPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(displayPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(displayPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, displayPanelLayout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(saveChangesPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(displayPanelLayout.createSequentialGroup()
                        .addGroup(displayPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(slider, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(displayPanelLayout.createSequentialGroup()
                                .addGroup(displayPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(title, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(524, 524, 524)
                                .addGroup(displayPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jSeparator2)
                                    .addComponent(percent, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                        .addContainerGap())))
        );
        displayPanelLayout.setVerticalGroup(
            displayPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, displayPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(displayPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(title)
                    .addComponent(percent))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(displayPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(slider, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(35, 35, 35)
                .addComponent(saveChangesPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(28, 28, 28))
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(displayPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(displayPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(panel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(panel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(panel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 0, 0)
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(panel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, 0)
                        .addComponent(panel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, 0)
                        .addComponent(panel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel arrow1;
    private javax.swing.JLabel arrow2;
    private javax.swing.JLabel arrow3;
    public static javax.swing.JPanel color1;
    public static javax.swing.JPanel color2;
    public static javax.swing.JPanel color3;
    private javax.swing.JPanel displayPanel;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JPanel panel1;
    private javax.swing.JPanel panel2;
    private javax.swing.JPanel panel3;
    private javax.swing.JPanel panelPic;
    public static javax.swing.JLabel percent;
    public static javax.swing.JLabel saveChanges;
    private javax.swing.JPanel saveChangesPanel;
    public static javax.swing.JSlider slider;
    private javax.swing.JLabel tickHold;
    public static javax.swing.JLabel title;
    private javax.swing.JLabel title1;
    private javax.swing.JLabel title2;
    private javax.swing.JLabel title3;
    // End of variables declaration//GEN-END:variables
}
