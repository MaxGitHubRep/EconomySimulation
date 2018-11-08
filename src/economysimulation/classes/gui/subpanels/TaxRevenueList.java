package economysimulation.classes.gui.subpanels;

import economysimulation.classes.managers.theme.GraphicUpdater;
import economysimulation.classes.managers.ui.Format;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import static economysimulation.classes.global.Methods.GameDisplay;
import static economysimulation.classes.global.Methods.ThemeManager;
import economysimulation.classes.managers.theme.ThemeUpdateEvent;
import economysimulation.classes.pulse.GamePulse;
import economysimulation.classes.economy.structure.Component;

/**
 *
 * @author Max Carter
 */
public class TaxRevenueList extends javax.swing.JPanel implements GamePulse, ThemeUpdateEvent {

    private final String[]
            taxTexts = new String[]{ "Tax Break", "Taxes Frozen" };
    
    private JLabel[]
            perDay,
            total,
            taxBreaks;
    
    private JPanel[]
            backPanels,
            colorPanels;
    
    /**
     * Creates new form TaxResultList
     */
    public TaxRevenueList() {
        initComponents();
        
        perDay = new JLabel[]{ ct1, it1, tt1 };
        total = new JLabel[]{ ct4, it4, tt4 };
        taxBreaks = new JLabel[]{ tb1, tb2 };
        
        backPanels = new JPanel[]{ subback1, subback2 };
        colorPanels = new JPanel[]{ subcolor1, subcolor2 };
        
        ThemeManager.addThemeUpdateListener(this);
        
        for (int i = 0; i < backPanels.length; i++) {
            Format.addButtonFormat(backPanels[i], colorPanels[i]);
            taxBreakClicked(i);
        }
    }

    @Override
    public void onGamePulseEvent() {
        updateTaxationLabels();
    }

    @Override
    public void updateThemeEvent(GraphicUpdater updater) {
        updater.applyPanelThemes(new JPanel[]{ back1, back2, back3,subback1, subback2, subcolor1, subcolor2 }, new JPanel[]{ color1, color2, color3 } );
        updater.applyTextThemes(new JLabel[]{ c1, ct1, c4, ct4, i1, i4, it1, it4, tt1, tt4, t1, t4, tb1, tb2 }, new JLabel[]{ title1, title2, title3 });
    }

    /**
     * Updates the labels to show daily taxation revenue.
     */
    public void updateTaxationLabels() {
        int index = 0;

        for (double value : new double[]{ Component.DailyCorporationTax, Component.DailyIncomeTax, (Component.DailyCorporationTax + Component.DailyIncomeTax) }) { //tax per day
            perDay[index].setText(String.format("£%sm", Math.round(value*1000)));
            index++;
        }
        index = 0;
        for (double value : new double[]{ Component.TotalCorporationTax, Component.TotalIncomeTax, (Component.TotalCorporationTax + Component.TotalIncomeTax) }) { //tax in total
            total[index].setText(String.format("£%sm", Math.round(value*1000)));
            index++;
        }
    }
    
    /**
     * When the tax break button is clicked.
     * 
     * @param id Index of which button is pressed.
     */
    private void taxBreakClicked(int id) {
        backPanels[id].addMouseListener(new MouseAdapter() {
            @Override 
            public void mouseClicked(MouseEvent e) {
                boolean nowBreaking = taxBreaks[id].getText().equals(taxTexts[0]);
                GameDisplay.TaxBreak[id] = nowBreaking;
                taxBreaks[id].setText(taxTexts[nowBreaking ? 1 : 0]);
            }
        });
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        back1 = new javax.swing.JPanel();
        color1 = new javax.swing.JPanel();
        title1 = new javax.swing.JLabel();
        c1 = new javax.swing.JLabel();
        ct1 = new javax.swing.JLabel();
        c4 = new javax.swing.JLabel();
        ct4 = new javax.swing.JLabel();
        subback1 = new javax.swing.JPanel();
        subcolor1 = new javax.swing.JPanel();
        tb1 = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        back2 = new javax.swing.JPanel();
        color2 = new javax.swing.JPanel();
        title2 = new javax.swing.JLabel();
        i1 = new javax.swing.JLabel();
        it1 = new javax.swing.JLabel();
        i4 = new javax.swing.JLabel();
        it4 = new javax.swing.JLabel();
        subback2 = new javax.swing.JPanel();
        subcolor2 = new javax.swing.JPanel();
        tb2 = new javax.swing.JLabel();
        jSeparator2 = new javax.swing.JSeparator();
        back3 = new javax.swing.JPanel();
        color3 = new javax.swing.JPanel();
        title3 = new javax.swing.JLabel();
        t1 = new javax.swing.JLabel();
        tt1 = new javax.swing.JLabel();
        t4 = new javax.swing.JLabel();
        tt4 = new javax.swing.JLabel();

        setOpaque(false);

        back1.setBackground(new java.awt.Color(255, 255, 255));

        color1.setBackground(new java.awt.Color(102, 102, 102));

        title1.setFont(new java.awt.Font("Agency FB", 0, 48)); // NOI18N
        title1.setForeground(new java.awt.Color(255, 255, 255));
        title1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        title1.setText("Corporation Tax");

        javax.swing.GroupLayout color1Layout = new javax.swing.GroupLayout(color1);
        color1.setLayout(color1Layout);
        color1Layout.setHorizontalGroup(
            color1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(title1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        color1Layout.setVerticalGroup(
            color1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(title1, javax.swing.GroupLayout.DEFAULT_SIZE, 80, Short.MAX_VALUE)
        );

        c1.setFont(new java.awt.Font("Agency FB", 0, 36)); // NOI18N
        c1.setForeground(new java.awt.Color(204, 0, 0));
        c1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        c1.setText("Daily Revenue:");

        ct1.setFont(new java.awt.Font("Agency FB", 0, 42)); // NOI18N
        ct1.setForeground(new java.awt.Color(204, 0, 0));
        ct1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        ct1.setText("Uncalculated");

        c4.setFont(new java.awt.Font("Agency FB", 0, 36)); // NOI18N
        c4.setForeground(new java.awt.Color(204, 0, 0));
        c4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        c4.setText("Total Revenue:");

        ct4.setFont(new java.awt.Font("Agency FB", 0, 42)); // NOI18N
        ct4.setForeground(new java.awt.Color(204, 0, 0));
        ct4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        ct4.setText("Uncalculated");

        subback1.setBackground(new java.awt.Color(255, 255, 255));
        subback1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

        subcolor1.setBackground(new java.awt.Color(204, 0, 0));

        javax.swing.GroupLayout subcolor1Layout = new javax.swing.GroupLayout(subcolor1);
        subcolor1.setLayout(subcolor1Layout);
        subcolor1Layout.setHorizontalGroup(
            subcolor1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        subcolor1Layout.setVerticalGroup(
            subcolor1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 14, Short.MAX_VALUE)
        );

        tb1.setFont(new java.awt.Font("Agency FB", 0, 36)); // NOI18N
        tb1.setForeground(new java.awt.Color(204, 0, 0));
        tb1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        tb1.setText("Tax Break");

        javax.swing.GroupLayout subback1Layout = new javax.swing.GroupLayout(subback1);
        subback1.setLayout(subback1Layout);
        subback1Layout.setHorizontalGroup(
            subback1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(subcolor1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(tb1, javax.swing.GroupLayout.DEFAULT_SIZE, 204, Short.MAX_VALUE)
        );
        subback1Layout.setVerticalGroup(
            subback1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, subback1Layout.createSequentialGroup()
                .addComponent(tb1, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(subcolor1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        javax.swing.GroupLayout back1Layout = new javax.swing.GroupLayout(back1);
        back1.setLayout(back1Layout);
        back1Layout.setHorizontalGroup(
            back1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(color1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(back1Layout.createSequentialGroup()
                .addGap(72, 72, 72)
                .addComponent(subback1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 72, Short.MAX_VALUE))
            .addGroup(back1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(back1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(c1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jSeparator1, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(ct1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(c4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(ct4, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        back1Layout.setVerticalGroup(
            back1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(back1Layout.createSequentialGroup()
                .addComponent(color1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(4, 4, 4)
                .addComponent(c1, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(ct1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(c4)
                .addGap(6, 6, 6)
                .addComponent(ct4)
                .addGap(18, 18, 18)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(subback1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        back2.setBackground(new java.awt.Color(255, 255, 255));

        color2.setBackground(new java.awt.Color(102, 102, 102));

        title2.setFont(new java.awt.Font("Agency FB", 0, 48)); // NOI18N
        title2.setForeground(new java.awt.Color(255, 255, 255));
        title2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        title2.setText("Income Tax");

        javax.swing.GroupLayout color2Layout = new javax.swing.GroupLayout(color2);
        color2.setLayout(color2Layout);
        color2Layout.setHorizontalGroup(
            color2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(title2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        color2Layout.setVerticalGroup(
            color2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(title2, javax.swing.GroupLayout.DEFAULT_SIZE, 80, Short.MAX_VALUE)
        );

        i1.setFont(new java.awt.Font("Agency FB", 0, 36)); // NOI18N
        i1.setForeground(new java.awt.Color(204, 0, 0));
        i1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        i1.setText("Daily Revenue:");

        it1.setFont(new java.awt.Font("Agency FB", 0, 42)); // NOI18N
        it1.setForeground(new java.awt.Color(204, 0, 0));
        it1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        it1.setText("Uncalculated");

        i4.setFont(new java.awt.Font("Agency FB", 0, 36)); // NOI18N
        i4.setForeground(new java.awt.Color(204, 0, 0));
        i4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        i4.setText("Total Revenue:");

        it4.setFont(new java.awt.Font("Agency FB", 0, 42)); // NOI18N
        it4.setForeground(new java.awt.Color(204, 0, 0));
        it4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        it4.setText("Uncalculated");

        subback2.setBackground(new java.awt.Color(255, 255, 255));
        subback2.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

        subcolor2.setBackground(new java.awt.Color(204, 0, 0));

        javax.swing.GroupLayout subcolor2Layout = new javax.swing.GroupLayout(subcolor2);
        subcolor2.setLayout(subcolor2Layout);
        subcolor2Layout.setHorizontalGroup(
            subcolor2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        subcolor2Layout.setVerticalGroup(
            subcolor2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 14, Short.MAX_VALUE)
        );

        tb2.setFont(new java.awt.Font("Agency FB", 0, 36)); // NOI18N
        tb2.setForeground(new java.awt.Color(204, 0, 0));
        tb2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        tb2.setText("Tax Break");

        javax.swing.GroupLayout subback2Layout = new javax.swing.GroupLayout(subback2);
        subback2.setLayout(subback2Layout);
        subback2Layout.setHorizontalGroup(
            subback2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(subcolor2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(tb2, javax.swing.GroupLayout.DEFAULT_SIZE, 204, Short.MAX_VALUE)
        );
        subback2Layout.setVerticalGroup(
            subback2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, subback2Layout.createSequentialGroup()
                .addComponent(tb2, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(subcolor2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        javax.swing.GroupLayout back2Layout = new javax.swing.GroupLayout(back2);
        back2.setLayout(back2Layout);
        back2Layout.setHorizontalGroup(
            back2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(color2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(back2Layout.createSequentialGroup()
                .addGap(72, 72, 72)
                .addComponent(subback2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 72, Short.MAX_VALUE))
            .addGroup(back2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(back2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jSeparator2, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(it1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(i1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(i4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(it4, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        back2Layout.setVerticalGroup(
            back2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(back2Layout.createSequentialGroup()
                .addComponent(color2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(i1, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(6, 6, 6)
                .addComponent(it1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(i4)
                .addGap(6, 6, 6)
                .addComponent(it4)
                .addGap(18, 18, 18)
                .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(subback2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        back3.setBackground(new java.awt.Color(255, 255, 255));

        color3.setBackground(new java.awt.Color(102, 102, 102));

        title3.setFont(new java.awt.Font("Agency FB", 0, 48)); // NOI18N
        title3.setForeground(new java.awt.Color(255, 255, 255));
        title3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        title3.setText("Total Tax");

        javax.swing.GroupLayout color3Layout = new javax.swing.GroupLayout(color3);
        color3.setLayout(color3Layout);
        color3Layout.setHorizontalGroup(
            color3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(title3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        color3Layout.setVerticalGroup(
            color3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(title3, javax.swing.GroupLayout.DEFAULT_SIZE, 80, Short.MAX_VALUE)
        );

        t1.setFont(new java.awt.Font("Agency FB", 0, 36)); // NOI18N
        t1.setForeground(new java.awt.Color(204, 0, 0));
        t1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        t1.setText("Total Daily Revenue:");

        tt1.setFont(new java.awt.Font("Agency FB", 0, 42)); // NOI18N
        tt1.setForeground(new java.awt.Color(204, 0, 0));
        tt1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        tt1.setText("Uncalculated");

        t4.setFont(new java.awt.Font("Agency FB", 0, 36)); // NOI18N
        t4.setForeground(new java.awt.Color(204, 0, 0));
        t4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        t4.setText("Total Accumulated Revenue:");

        tt4.setFont(new java.awt.Font("Agency FB", 0, 42)); // NOI18N
        tt4.setForeground(new java.awt.Color(204, 0, 0));
        tt4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        tt4.setText("Uncalculated");

        javax.swing.GroupLayout back3Layout = new javax.swing.GroupLayout(back3);
        back3.setLayout(back3Layout);
        back3Layout.setHorizontalGroup(
            back3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(color3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(back3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(back3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(tt4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(tt1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(t1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(t4, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 336, Short.MAX_VALUE))
                .addContainerGap())
        );
        back3Layout.setVerticalGroup(
            back3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(back3Layout.createSequentialGroup()
                .addComponent(color3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(6, 6, 6)
                .addComponent(t1)
                .addGap(6, 6, 6)
                .addComponent(tt1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(t4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(tt4)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(back1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(back2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(back3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(back1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(back2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addComponent(back3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(0, 0, 0))
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    public static javax.swing.JPanel back1;
    public static javax.swing.JPanel back2;
    public static javax.swing.JPanel back3;
    private static javax.swing.JLabel c1;
    private static javax.swing.JLabel c4;
    private static javax.swing.JPanel color1;
    private static javax.swing.JPanel color2;
    private static javax.swing.JPanel color3;
    private static javax.swing.JLabel ct1;
    private static javax.swing.JLabel ct4;
    private static javax.swing.JLabel i1;
    private static javax.swing.JLabel i4;
    private static javax.swing.JLabel it1;
    private static javax.swing.JLabel it4;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JPanel subback1;
    private javax.swing.JPanel subback2;
    private javax.swing.JPanel subcolor1;
    private javax.swing.JPanel subcolor2;
    private static javax.swing.JLabel t1;
    private static javax.swing.JLabel t4;
    private static javax.swing.JLabel tb1;
    private static javax.swing.JLabel tb2;
    private static javax.swing.JLabel title1;
    private static javax.swing.JLabel title2;
    private static javax.swing.JLabel title3;
    private static javax.swing.JLabel tt1;
    private static javax.swing.JLabel tt4;
    // End of variables declaration//GEN-END:variables
}
