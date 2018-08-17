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
        
        perDay = new JLabel[]{ ct1, it1, tt1 };
        perQuarter = new JLabel[]{ ct2, it2, tt2 };
        perYear = new JLabel[]{ ct3, it3, tt3 };
        total = new JLabel[]{ ct4, it4, tt4 };
        taxBreaks = new JLabel[]{ tb1, tb2 };
        
        backPanels = new JPanel[]{ subback1, subback2 };
        colorPanels = new JPanel[]{ subcolor1, subcolor2 };
        
        updateTheme();
        
        for (int i = 0; i < backPanels.length; i++) {
            Format.addButtonFormat(backPanels[i], colorPanels[i]);
            if (i < taxBreaks.length) taxBreakClicked(i);
        }
    }
    
    //<editor-fold defaultstate="collapsed" desc="Updates the theme for the class.">   
    public static void updateTheme() {
        Theme.applyPanelThemes(new JPanel[]{ back1, back2, back3 }, new JPanel[]{ color1, color2, color3 }, backPanels, colorPanels );
        Theme.applyTextThemes(new JLabel[]{ c1, c2, c3, c4, i1, i2, i3, i4, t1, t2, t3, t4 }, new JLabel[]{ title1, title2, title3 });
    }//</editor-fold> 

    public static void updateTaxationLabels(int updater) {
        int index = 0;
        if (updater == TaxRevUpdate.ONLY_PER_YEAR) {
            for (double value : new double[]{ Component.YEARLY_CORP_TAX, Component.YEARLY_INCOME_TAX, (Component.YEARLY_CORP_TAX + Component.YEARLY_INCOME_TAX) }) { //tax per year
                perYear[index].setText(String.format("£%sm", Math.round(value*1000)));
                index++;
            }
            
        } else if (updater == TaxRevUpdate.ONLY_PER_QUARTER) {
            index = 0;
            for (double value : new double[]{ Component.QUARTER_CORP_TAX, Component.YEARLY_INCOME_TAX, (Component.QUARTER_CORP_TAX + Component.YEARLY_INCOME_TAX) }) { //tax per q
                perQuarter[index].setText(String.format("£%sm", Math.round(value*1000)));
                index++;
            }
            
        } else if (updater >= TaxRevUpdate.ONLY_PER_DAY) {
            index = 0;
            for (double value : new double[]{ Component.TAXED_CORP, Component.TAXED_INCOME, (Component.TAXED_CORP + Component.TAXED_INCOME) }) { //tax per day
                perDay[index].setText(String.format("£%sm", Math.round(value*1000)));
                index++;
            }
            index = 0;
            for (double value : new double[]{ Component.TOTAL_CORP_TAX, Component.TOTAL_INCOME_TAX, (Component.TOTAL_CORP_TAX + Component.TOTAL_INCOME_TAX) }) { //tax in total
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
        c1 = new javax.swing.JLabel();
        ct1 = new javax.swing.JLabel();
        ct2 = new javax.swing.JLabel();
        c2 = new javax.swing.JLabel();
        c3 = new javax.swing.JLabel();
        ct3 = new javax.swing.JLabel();
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
        it2 = new javax.swing.JLabel();
        i2 = new javax.swing.JLabel();
        i3 = new javax.swing.JLabel();
        it3 = new javax.swing.JLabel();
        i4 = new javax.swing.JLabel();
        it4 = new javax.swing.JLabel();
        subback2 = new javax.swing.JPanel();
        subcolor2 = new javax.swing.JPanel();
        tb2 = new javax.swing.JLabel();
        jSeparator2 = new javax.swing.JSeparator();
        jSeparator3 = new javax.swing.JSeparator();
        back3 = new javax.swing.JPanel();
        color3 = new javax.swing.JPanel();
        title3 = new javax.swing.JLabel();
        t1 = new javax.swing.JLabel();
        tt1 = new javax.swing.JLabel();
        tt2 = new javax.swing.JLabel();
        t2 = new javax.swing.JLabel();
        t3 = new javax.swing.JLabel();
        tt3 = new javax.swing.JLabel();
        t4 = new javax.swing.JLabel();
        tt4 = new javax.swing.JLabel();
        jSeparator6 = new javax.swing.JSeparator();

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
        c1.setText("Per Day:");

        ct1.setFont(new java.awt.Font("Agency FB", 0, 36)); // NOI18N
        ct1.setForeground(new java.awt.Color(204, 0, 0));
        ct1.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        ct1.setText("Uncalculated");

        ct2.setFont(new java.awt.Font("Agency FB", 0, 36)); // NOI18N
        ct2.setForeground(new java.awt.Color(204, 0, 0));
        ct2.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        ct2.setText("Uncalculated");

        c2.setFont(new java.awt.Font("Agency FB", 0, 36)); // NOI18N
        c2.setForeground(new java.awt.Color(204, 0, 0));
        c2.setText("Per Quarter:");

        c3.setFont(new java.awt.Font("Agency FB", 0, 36)); // NOI18N
        c3.setForeground(new java.awt.Color(204, 0, 0));
        c3.setText("Per Year:");

        ct3.setFont(new java.awt.Font("Agency FB", 0, 36)); // NOI18N
        ct3.setForeground(new java.awt.Color(204, 0, 0));
        ct3.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        ct3.setText("Uncalculated");

        c4.setFont(new java.awt.Font("Agency FB", 0, 36)); // NOI18N
        c4.setForeground(new java.awt.Color(204, 0, 0));
        c4.setText("Total Revenue:");

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
                .addContainerGap()
                .addGroup(back1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(back1Layout.createSequentialGroup()
                        .addGroup(back1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(back1Layout.createSequentialGroup()
                                .addComponent(c1)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(ct1, javax.swing.GroupLayout.PREFERRED_SIZE, 158, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(back1Layout.createSequentialGroup()
                                .addComponent(c3)
                                .addGap(76, 76, 76)
                                .addComponent(ct3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addGroup(back1Layout.createSequentialGroup()
                                .addGroup(back1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(c2)
                                    .addComponent(c4))
                                .addGroup(back1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(back1Layout.createSequentialGroup()
                                        .addGap(27, 27, 27)
                                        .addComponent(ct2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                    .addGroup(back1Layout.createSequentialGroup()
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 19, Short.MAX_VALUE)
                                        .addComponent(ct4, javax.swing.GroupLayout.PREFERRED_SIZE, 166, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                        .addContainerGap())
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, back1Layout.createSequentialGroup()
                        .addComponent(jSeparator1)
                        .addContainerGap())))
            .addGroup(back1Layout.createSequentialGroup()
                .addGap(72, 72, 72)
                .addComponent(subback1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        back1Layout.setVerticalGroup(
            back1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(back1Layout.createSequentialGroup()
                .addComponent(color1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(10, 10, 10)
                .addGroup(back1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(c1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(ct1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(back1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(c2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(ct2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(back1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(c3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(ct3))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(back1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(c4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(ct4))
                .addGap(13, 13, 13)
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
        i1.setText("Per Day:");

        it1.setFont(new java.awt.Font("Agency FB", 0, 36)); // NOI18N
        it1.setForeground(new java.awt.Color(204, 0, 0));
        it1.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        it1.setText("Uncalculated");

        it2.setFont(new java.awt.Font("Agency FB", 0, 36)); // NOI18N
        it2.setForeground(new java.awt.Color(204, 0, 0));
        it2.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        it2.setText("Uncalculated");

        i2.setFont(new java.awt.Font("Agency FB", 0, 36)); // NOI18N
        i2.setForeground(new java.awt.Color(204, 0, 0));
        i2.setText("Per Quarter:");

        i3.setFont(new java.awt.Font("Agency FB", 0, 36)); // NOI18N
        i3.setForeground(new java.awt.Color(204, 0, 0));
        i3.setText("Per Year:");

        it3.setFont(new java.awt.Font("Agency FB", 0, 36)); // NOI18N
        it3.setForeground(new java.awt.Color(204, 0, 0));
        it3.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        it3.setText("Uncalculated");

        i4.setFont(new java.awt.Font("Agency FB", 0, 36)); // NOI18N
        i4.setForeground(new java.awt.Color(204, 0, 0));
        i4.setText("Total Revenue:");

        it4.setFont(new java.awt.Font("Agency FB", 0, 36)); // NOI18N
        it4.setForeground(new java.awt.Color(204, 0, 0));
        it4.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
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
                .addContainerGap()
                .addGroup(back2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(back2Layout.createSequentialGroup()
                        .addComponent(i1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(it1, javax.swing.GroupLayout.PREFERRED_SIZE, 158, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(back2Layout.createSequentialGroup()
                        .addComponent(i3)
                        .addGap(76, 76, 76)
                        .addComponent(it3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(back2Layout.createSequentialGroup()
                        .addGroup(back2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(i2)
                            .addComponent(i4))
                        .addGroup(back2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(back2Layout.createSequentialGroup()
                                .addGap(27, 27, 27)
                                .addComponent(it2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addGroup(back2Layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 19, Short.MAX_VALUE)
                                .addComponent(it4, javax.swing.GroupLayout.PREFERRED_SIZE, 166, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addComponent(jSeparator2, javax.swing.GroupLayout.Alignment.TRAILING))
                .addContainerGap())
            .addGroup(back2Layout.createSequentialGroup()
                .addGap(72, 72, 72)
                .addComponent(subback2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        back2Layout.setVerticalGroup(
            back2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(back2Layout.createSequentialGroup()
                .addComponent(color2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(10, 10, 10)
                .addGroup(back2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(i1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(it1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(back2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(i2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(it2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(back2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(i3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(it3))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(back2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(i4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(it4))
                .addGap(13, 13, 13)
                .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(subback2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        jSeparator3.setOrientation(javax.swing.SwingConstants.VERTICAL);

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
        t1.setText("Per Day:");

        tt1.setFont(new java.awt.Font("Agency FB", 0, 36)); // NOI18N
        tt1.setForeground(new java.awt.Color(204, 0, 0));
        tt1.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        tt1.setText("Uncalculated");

        tt2.setFont(new java.awt.Font("Agency FB", 0, 36)); // NOI18N
        tt2.setForeground(new java.awt.Color(204, 0, 0));
        tt2.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        tt2.setText("Uncalculated");

        t2.setFont(new java.awt.Font("Agency FB", 0, 36)); // NOI18N
        t2.setForeground(new java.awt.Color(204, 0, 0));
        t2.setText("Per Quarter:");

        t3.setFont(new java.awt.Font("Agency FB", 0, 36)); // NOI18N
        t3.setForeground(new java.awt.Color(204, 0, 0));
        t3.setText("Per Year:");

        tt3.setFont(new java.awt.Font("Agency FB", 0, 36)); // NOI18N
        tt3.setForeground(new java.awt.Color(204, 0, 0));
        tt3.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        tt3.setText("Uncalculated");

        t4.setFont(new java.awt.Font("Agency FB", 0, 36)); // NOI18N
        t4.setForeground(new java.awt.Color(204, 0, 0));
        t4.setText("Total Revenue:");

        tt4.setFont(new java.awt.Font("Agency FB", 0, 36)); // NOI18N
        tt4.setForeground(new java.awt.Color(204, 0, 0));
        tt4.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        tt4.setText("Uncalculated");

        javax.swing.GroupLayout back3Layout = new javax.swing.GroupLayout(back3);
        back3.setLayout(back3Layout);
        back3Layout.setHorizontalGroup(
            back3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(color3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(back3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(back3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(back3Layout.createSequentialGroup()
                        .addComponent(t1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(tt1, javax.swing.GroupLayout.PREFERRED_SIZE, 158, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(back3Layout.createSequentialGroup()
                        .addComponent(t3)
                        .addGap(76, 76, 76)
                        .addComponent(tt3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(back3Layout.createSequentialGroup()
                        .addGroup(back3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(t2)
                            .addComponent(t4))
                        .addGroup(back3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(back3Layout.createSequentialGroup()
                                .addGap(27, 27, 27)
                                .addComponent(tt2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addGroup(back3Layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 19, Short.MAX_VALUE)
                                .addComponent(tt4, javax.swing.GroupLayout.PREFERRED_SIZE, 166, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addContainerGap())
        );
        back3Layout.setVerticalGroup(
            back3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(back3Layout.createSequentialGroup()
                .addComponent(color3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(10, 10, 10)
                .addGroup(back3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(t1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(tt1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(back3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(t2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(tt2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(back3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(t3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(tt3))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(back3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(t4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(tt4))
                .addGap(85, 85, 85))
        );

        jSeparator6.setOrientation(javax.swing.SwingConstants.VERTICAL);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(back1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(2, 2, 2)
                .addComponent(jSeparator3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(2, 2, 2)
                .addComponent(back2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(2, 2, 2)
                .addComponent(jSeparator6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(2, 2, 2)
                .addComponent(back3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(80, 80, 80)
                .addComponent(jSeparator3, javax.swing.GroupLayout.PREFERRED_SIZE, 239, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(back1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(back2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(back3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jSeparator6, javax.swing.GroupLayout.PREFERRED_SIZE, 240, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(70, 70, 70))
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    public static javax.swing.JPanel back1;
    public static javax.swing.JPanel back2;
    public static javax.swing.JPanel back3;
    private static javax.swing.JLabel c1;
    private static javax.swing.JLabel c2;
    private static javax.swing.JLabel c3;
    private static javax.swing.JLabel c4;
    private static javax.swing.JPanel color1;
    private static javax.swing.JPanel color2;
    private static javax.swing.JPanel color3;
    private javax.swing.JLabel ct1;
    private javax.swing.JLabel ct2;
    private javax.swing.JLabel ct3;
    private javax.swing.JLabel ct4;
    private static javax.swing.JLabel i1;
    private static javax.swing.JLabel i2;
    private static javax.swing.JLabel i3;
    private static javax.swing.JLabel i4;
    private javax.swing.JLabel it1;
    private javax.swing.JLabel it2;
    private javax.swing.JLabel it3;
    private javax.swing.JLabel it4;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JSeparator jSeparator3;
    private javax.swing.JSeparator jSeparator6;
    private javax.swing.JPanel subback1;
    private javax.swing.JPanel subback2;
    private javax.swing.JPanel subcolor1;
    private javax.swing.JPanel subcolor2;
    private static javax.swing.JLabel t1;
    private static javax.swing.JLabel t2;
    private static javax.swing.JLabel t3;
    private static javax.swing.JLabel t4;
    private javax.swing.JLabel tb1;
    private javax.swing.JLabel tb2;
    private static javax.swing.JLabel title1;
    private static javax.swing.JLabel title2;
    private static javax.swing.JLabel title3;
    private javax.swing.JLabel tt1;
    private javax.swing.JLabel tt2;
    private javax.swing.JLabel tt3;
    private javax.swing.JLabel tt4;
    // End of variables declaration//GEN-END:variables
}
