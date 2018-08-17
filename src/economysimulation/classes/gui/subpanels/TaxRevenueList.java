package economysimulation.classes.gui.subpanels;

import economysimulation.classes.economy.Component;
import economysimulation.classes.managers.themes.Theme;
import economysimulation.classes.misc.TaxRevUpdate;
import economysimulation.classes.managers.ui.Format;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 *
 * @author Max Carter
 */
public class TaxRevenueList extends javax.swing.JPanel {

    private static final String[]
            taxTexts = new String[]{ "Tax Break", "Taxes Frozen" };
    
    private static JLabel[]
            perDay,
            perQuarter,
            perYear,
            total,
            taxBreaks;
    
    private static JPanel[]
            backPanels,
            colorPanels;
    
    /**
     * Creates new form TaxResultList
     */
    public TaxRevenueList() {
        initComponents();
        
        perDay = new JLabel[]{ ct1 };
        perQuarter = new JLabel[]{ ct2 };
        perYear = new JLabel[]{ ct3 };
        total = new JLabel[]{ ct4 };
        taxBreaks = new JLabel[]{ tb1 };
        
        backPanels = new JPanel[]{ subback1 };
        colorPanels = new JPanel[]{ subcolor1 };
        
        for (int i = 0; i < backPanels.length; i++) {
            Format.addButtonFormat(backPanels[i], colorPanels[i]);
            taxBreakClicked(i);
        }
    }

    public static void updateTaxationLabels(int updater) {
        int index = 0;
        if (updater == TaxRevUpdate.ONLY_PER_YEAR) {
            for (double value : new double[]{ Component.YEARLY_CORP_TAX }) { //tax per year
                perYear[index].setText(String.format("£%sm", Math.round(value*1000)));
                index++;
            }
            
        } else if (updater == TaxRevUpdate.ONLY_PER_QUARTER) {
            index = 0;
            for (double value : new double[]{ Component.QUARTER_CORP_TAX }) { //tax per q
                perQuarter[index].setText(String.format("£%sm", Math.round(value*1000)));
                index++;
            }
            
        } else if (updater >= TaxRevUpdate.ONLY_PER_DAY) {
            index = 0;
            for (double value : new double[]{ Component.TAXED_CORP }) { //tax per day
                perDay[index].setText(String.format("£%sm", Math.round(value*1000)));
                index++;
            }
            index = 0;
            for (double value : new double[]{ Component.TOTAL_CORP_TAX }) { //tax in total
                total[index].setText(String.format("£%sm", Math.round(value*1000)));
                index++;
            }
        }
    }
    
    private static void taxBreakClicked(int id) {
        backPanels[id].addMouseListener(new MouseAdapter() {
            @Override 
            public void mouseClicked(MouseEvent e) {
                boolean nowBreaking = taxBreaks[id].getText().equals(taxTexts[0]);
                Component.TAX_BREAK[id] = nowBreaking;
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
        jLabel1 = new javax.swing.JLabel();
        ct1 = new javax.swing.JLabel();
        ct2 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        ct3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        ct4 = new javax.swing.JLabel();
        subback1 = new javax.swing.JPanel();
        subcolor1 = new javax.swing.JPanel();
        tb1 = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();

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

        jLabel1.setFont(new java.awt.Font("Agency FB", 0, 36)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(204, 0, 0));
        jLabel1.setText("Per Day:");

        ct1.setFont(new java.awt.Font("Agency FB", 0, 36)); // NOI18N
        ct1.setForeground(new java.awt.Color(204, 0, 0));
        ct1.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        ct1.setText("Uncalculated");

        ct2.setFont(new java.awt.Font("Agency FB", 0, 36)); // NOI18N
        ct2.setForeground(new java.awt.Color(204, 0, 0));
        ct2.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        ct2.setText("Uncalculated");

        jLabel2.setFont(new java.awt.Font("Agency FB", 0, 36)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(204, 0, 0));
        jLabel2.setText("Per Quarter:");

        jLabel3.setFont(new java.awt.Font("Agency FB", 0, 36)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(204, 0, 0));
        jLabel3.setText("Per Year:");

        ct3.setFont(new java.awt.Font("Agency FB", 0, 36)); // NOI18N
        ct3.setForeground(new java.awt.Color(204, 0, 0));
        ct3.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        ct3.setText("Uncalculated");

        jLabel4.setFont(new java.awt.Font("Agency FB", 0, 36)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(204, 0, 0));
        jLabel4.setText("Total Revenue:");

        ct4.setFont(new java.awt.Font("Agency FB", 0, 36)); // NOI18N
        ct4.setForeground(new java.awt.Color(204, 0, 0));
        ct4.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
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
                .addComponent(tb1, javax.swing.GroupLayout.DEFAULT_SIZE, 51, Short.MAX_VALUE)
                .addGap(0, 0, 0)
                .addComponent(subcolor1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        javax.swing.GroupLayout back1Layout = new javax.swing.GroupLayout(back1);
        back1.setLayout(back1Layout);
        back1Layout.setHorizontalGroup(
            back1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(color1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(back1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(back1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(back1Layout.createSequentialGroup()
                        .addGroup(back1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(back1Layout.createSequentialGroup()
                                .addComponent(jLabel1)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(ct1, javax.swing.GroupLayout.PREFERRED_SIZE, 158, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(back1Layout.createSequentialGroup()
                                .addComponent(jLabel3)
                                .addGap(76, 76, 76)
                                .addComponent(ct3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addGroup(back1Layout.createSequentialGroup()
                                .addGroup(back1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel2)
                                    .addComponent(jLabel4))
                                .addGroup(back1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(back1Layout.createSequentialGroup()
                                        .addGap(27, 27, 27)
                                        .addComponent(ct2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                    .addGroup(back1Layout.createSequentialGroup()
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 19, Short.MAX_VALUE)
                                        .addComponent(ct4, javax.swing.GroupLayout.PREFERRED_SIZE, 166, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                        .addContainerGap())
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, back1Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(subback1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(78, 78, 78))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, back1Layout.createSequentialGroup()
                        .addComponent(jSeparator1)
                        .addContainerGap())))
        );
        back1Layout.setVerticalGroup(
            back1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(back1Layout.createSequentialGroup()
                .addComponent(color1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(10, 10, 10)
                .addGroup(back1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(ct1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(back1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(ct2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(back1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(ct3))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(back1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(ct4))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(5, 5, 5)
                .addComponent(subback1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(back1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(740, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(back1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    public static javax.swing.JPanel back1;
    private javax.swing.JPanel color1;
    private javax.swing.JLabel ct1;
    private javax.swing.JLabel ct2;
    private javax.swing.JLabel ct3;
    private javax.swing.JLabel ct4;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JPanel subback1;
    private javax.swing.JPanel subcolor1;
    private javax.swing.JLabel tb1;
    private javax.swing.JLabel title1;
    // End of variables declaration//GEN-END:variables
}
