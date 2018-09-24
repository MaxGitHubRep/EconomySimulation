package economysimulation.classes.gui.mainpanels.sim.consumer;

import economysimulation.classes.economy.structure.Component;
import economysimulation.classes.global.Methods;
import static economysimulation.classes.global.Methods.GameDisplay;
import static economysimulation.classes.global.Methods.ThemeManager;
import economysimulation.classes.managers.popup.hint.HintManager;
import economysimulation.classes.managers.popup.hint.Hints;
import economysimulation.classes.managers.theme.GraphicUpdater;
import economysimulation.classes.managers.theme.ThemeUpdateEvent;
import economysimulation.classes.managers.ui.Format;
import economysimulation.classes.pulse.GamePulse;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.DecimalFormat;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 *
 * @author Max Carter
 */
public class Consumer extends javax.swing.JPanel implements GamePulse, ThemeUpdateEvent {

    private JPanel[] backPanels;
    private JLabel[] valueLabels;
    
    private String[] TextFormat = new String[]{
        "%s",
        "£%sm",
        "£%sm",
        "%s" + "%%",
        "£%sm",
        "£%sm"
    };
    
    private ConsumerMiddle middle;
    
    private final ConsumerComponents components;

    private class ConsumerComponents extends Component {
    
        protected double[] calculateConsumerComponents() {

            double Wages = (0.000000064 * (Population * ((100 - Unemployment)/100)) * WageMultiplier);
            double DisposableIncome = Wages;
            CostOfProduction+= Wages;

            ConsumerConfidence = StandardOfLiving * (100-IncomeTax)/100;

            PropensityToConsume = ((100 - InterestRate)/100) * ConsumerConfidence;
            if (PropensityToConsume == 0) PropensityToConsume+=0.01;

            if (DisposableIncome == 0 && TotalSavings >= 0.1) {
                TotalSavings-=0.1;
                DisposableIncome+=0.1;
            } else if (TotalSavings < 0.1 && DisposableIncome == 0) {
                HintManager.createHint(Hints.ConsumersBankrupt);
            }

            DailyIncomeTax = Wages * (Wages > 0 && IncomeTax > 0 && !GameDisplay.TaxBreak[1] ? (IncomeTax/100) : 0);
            DisposableIncome -= DailyIncomeTax;

            Consumption = PropensityToConsume * ( DisposableIncome + 0.4 * (!GameDisplay.TaxBreak[1] ? 1-(IncomeTax/100) : 1));
            double Savings = (1 - PropensityToConsume) * DisposableIncome;
            
            TotalConsumption += Consumption;
            TotalIncomeTax += DailyIncomeTax;
            TotalSavings += Savings;
            Taxation += DailyIncomeTax;
            
            return new double[]{ DisposableIncome, Wages };
        }

    }
    
    public Consumer() {
        initComponents();
        middle = new ConsumerMiddle();
        middle.setSize(450, 450);
        mid.add(middle);
        repaint();
        this.components = new ConsumerComponents();
        
        valueLabels = new JLabel[]{ val1, val2, val3, val4, val5, val6 };
        backPanels = new JPanel[]{ comp1, comp2, comp3, comp4, comp5, comp6 };
        
        for (int i = 0; i < backPanels.length; i++) {
            Format.addButtonFormat(backPanels[i], null);
            addHoverEvent(i);
        }
        
        ThemeManager.addThemeUpdateListener(this);

    }

    private void addHoverEvent(int id) {
        backPanels[id].addMouseListener(new MouseAdapter() {
            @Override 
            public void mouseEntered(MouseEvent e) {
                middle.sector = id;
                repaint();
            }
            
            @Override
            public void mouseExited(MouseEvent e) {
                middle.sector = 6;
                repaint();
            }
        });
    }
    
    @Override
    public void updateThemeEvent(GraphicUpdater updater) {
        //
    }
    
    @Override
    public void gamePulseEvent() {
        double[] data = this.components.calculateConsumerComponents();
        double[] values = new double[]{ Component.Population, Component.Consumption*1000, Component.TotalSavings*1000, Component.Unemployment, data[0]*1000, data[1]*1000 };
        
        DecimalFormat format = new DecimalFormat("0");
        for (int i = 0; i < valueLabels.length; i++) {
            valueLabels[i].setText(String.format(TextFormat[i], format.format(values[i])));
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        mid = new javax.swing.JPanel();
        comp1 = new javax.swing.JPanel();
        top1 = new javax.swing.JPanel();
        title1 = new javax.swing.JLabel();
        val1 = new javax.swing.JLabel();
        comp2 = new javax.swing.JPanel();
        top2 = new javax.swing.JPanel();
        title2 = new javax.swing.JLabel();
        val2 = new javax.swing.JLabel();
        comp3 = new javax.swing.JPanel();
        top3 = new javax.swing.JPanel();
        title3 = new javax.swing.JLabel();
        val3 = new javax.swing.JLabel();
        comp4 = new javax.swing.JPanel();
        top4 = new javax.swing.JPanel();
        title4 = new javax.swing.JLabel();
        val4 = new javax.swing.JLabel();
        comp5 = new javax.swing.JPanel();
        top5 = new javax.swing.JPanel();
        title5 = new javax.swing.JLabel();
        val5 = new javax.swing.JLabel();
        comp6 = new javax.swing.JPanel();
        top6 = new javax.swing.JPanel();
        title6 = new javax.swing.JLabel();
        val6 = new javax.swing.JLabel();

        setBackground(new java.awt.Color(51, 51, 51));
        setOpaque(false);
        setPreferredSize(new java.awt.Dimension(1100, 800));

        mid.setOpaque(false);

        javax.swing.GroupLayout midLayout = new javax.swing.GroupLayout(mid);
        mid.setLayout(midLayout);
        midLayout.setHorizontalGroup(
            midLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 460, Short.MAX_VALUE)
        );
        midLayout.setVerticalGroup(
            midLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 460, Short.MAX_VALUE)
        );

        comp1.setBackground(new java.awt.Color(255, 255, 255));
        comp1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

        top1.setBackground(new java.awt.Color(204, 0, 0));

        title1.setFont(new java.awt.Font("Agency FB", 0, 32)); // NOI18N
        title1.setForeground(new java.awt.Color(255, 255, 255));
        title1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        title1.setText("Population");

        javax.swing.GroupLayout top1Layout = new javax.swing.GroupLayout(top1);
        top1.setLayout(top1Layout);
        top1Layout.setHorizontalGroup(
            top1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(title1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        top1Layout.setVerticalGroup(
            top1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(title1, javax.swing.GroupLayout.DEFAULT_SIZE, 45, Short.MAX_VALUE)
        );

        val1.setFont(new java.awt.Font("Agency FB", 0, 36)); // NOI18N
        val1.setForeground(new java.awt.Color(204, 0, 0));
        val1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        val1.setText("1,000,000");

        javax.swing.GroupLayout comp1Layout = new javax.swing.GroupLayout(comp1);
        comp1.setLayout(comp1Layout);
        comp1Layout.setHorizontalGroup(
            comp1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(top1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, comp1Layout.createSequentialGroup()
                .addContainerGap(17, Short.MAX_VALUE)
                .addComponent(val1, javax.swing.GroupLayout.PREFERRED_SIZE, 217, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(16, 16, 16))
        );
        comp1Layout.setVerticalGroup(
            comp1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(comp1Layout.createSequentialGroup()
                .addComponent(top1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(val1, javax.swing.GroupLayout.DEFAULT_SIZE, 53, Short.MAX_VALUE)
                .addContainerGap())
        );

        comp2.setBackground(new java.awt.Color(255, 255, 255));
        comp2.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

        top2.setBackground(new java.awt.Color(204, 0, 0));

        title2.setFont(new java.awt.Font("Agency FB", 0, 32)); // NOI18N
        title2.setForeground(new java.awt.Color(255, 255, 255));
        title2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        title2.setText("Total Consumption");

        javax.swing.GroupLayout top2Layout = new javax.swing.GroupLayout(top2);
        top2.setLayout(top2Layout);
        top2Layout.setHorizontalGroup(
            top2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(title2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        top2Layout.setVerticalGroup(
            top2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(title2, javax.swing.GroupLayout.DEFAULT_SIZE, 45, Short.MAX_VALUE)
        );

        val2.setFont(new java.awt.Font("Agency FB", 0, 36)); // NOI18N
        val2.setForeground(new java.awt.Color(204, 0, 0));
        val2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        val2.setText("1,000,000");

        javax.swing.GroupLayout comp2Layout = new javax.swing.GroupLayout(comp2);
        comp2.setLayout(comp2Layout);
        comp2Layout.setHorizontalGroup(
            comp2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(top2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, comp2Layout.createSequentialGroup()
                .addContainerGap(17, Short.MAX_VALUE)
                .addComponent(val2, javax.swing.GroupLayout.PREFERRED_SIZE, 217, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(16, 16, 16))
        );
        comp2Layout.setVerticalGroup(
            comp2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(comp2Layout.createSequentialGroup()
                .addComponent(top2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(val2, javax.swing.GroupLayout.DEFAULT_SIZE, 53, Short.MAX_VALUE)
                .addContainerGap())
        );

        comp3.setBackground(new java.awt.Color(255, 255, 255));
        comp3.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

        top3.setBackground(new java.awt.Color(204, 0, 0));

        title3.setFont(new java.awt.Font("Agency FB", 0, 32)); // NOI18N
        title3.setForeground(new java.awt.Color(255, 255, 255));
        title3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        title3.setText("Total Savings");

        javax.swing.GroupLayout top3Layout = new javax.swing.GroupLayout(top3);
        top3.setLayout(top3Layout);
        top3Layout.setHorizontalGroup(
            top3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(title3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        top3Layout.setVerticalGroup(
            top3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(title3, javax.swing.GroupLayout.DEFAULT_SIZE, 45, Short.MAX_VALUE)
        );

        val3.setFont(new java.awt.Font("Agency FB", 0, 36)); // NOI18N
        val3.setForeground(new java.awt.Color(204, 0, 0));
        val3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        val3.setText("1,000,000");

        javax.swing.GroupLayout comp3Layout = new javax.swing.GroupLayout(comp3);
        comp3.setLayout(comp3Layout);
        comp3Layout.setHorizontalGroup(
            comp3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(top3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, comp3Layout.createSequentialGroup()
                .addContainerGap(17, Short.MAX_VALUE)
                .addComponent(val3, javax.swing.GroupLayout.PREFERRED_SIZE, 217, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(16, 16, 16))
        );
        comp3Layout.setVerticalGroup(
            comp3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(comp3Layout.createSequentialGroup()
                .addComponent(top3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(val3, javax.swing.GroupLayout.DEFAULT_SIZE, 53, Short.MAX_VALUE)
                .addContainerGap())
        );

        comp4.setBackground(new java.awt.Color(255, 255, 255));
        comp4.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

        top4.setBackground(new java.awt.Color(204, 0, 0));

        title4.setFont(new java.awt.Font("Agency FB", 0, 32)); // NOI18N
        title4.setForeground(new java.awt.Color(255, 255, 255));
        title4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        title4.setText("Unemployment");

        javax.swing.GroupLayout top4Layout = new javax.swing.GroupLayout(top4);
        top4.setLayout(top4Layout);
        top4Layout.setHorizontalGroup(
            top4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(title4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        top4Layout.setVerticalGroup(
            top4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(title4, javax.swing.GroupLayout.DEFAULT_SIZE, 45, Short.MAX_VALUE)
        );

        val4.setFont(new java.awt.Font("Agency FB", 0, 36)); // NOI18N
        val4.setForeground(new java.awt.Color(204, 0, 0));
        val4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        val4.setText("1,000,000");

        javax.swing.GroupLayout comp4Layout = new javax.swing.GroupLayout(comp4);
        comp4.setLayout(comp4Layout);
        comp4Layout.setHorizontalGroup(
            comp4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(top4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, comp4Layout.createSequentialGroup()
                .addContainerGap(17, Short.MAX_VALUE)
                .addComponent(val4, javax.swing.GroupLayout.PREFERRED_SIZE, 217, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(16, 16, 16))
        );
        comp4Layout.setVerticalGroup(
            comp4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(comp4Layout.createSequentialGroup()
                .addComponent(top4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(val4, javax.swing.GroupLayout.DEFAULT_SIZE, 53, Short.MAX_VALUE)
                .addContainerGap())
        );

        comp5.setBackground(new java.awt.Color(255, 255, 255));
        comp5.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

        top5.setBackground(new java.awt.Color(204, 0, 0));

        title5.setFont(new java.awt.Font("Agency FB", 0, 32)); // NOI18N
        title5.setForeground(new java.awt.Color(255, 255, 255));
        title5.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        title5.setText("Disposable Income");

        javax.swing.GroupLayout top5Layout = new javax.swing.GroupLayout(top5);
        top5.setLayout(top5Layout);
        top5Layout.setHorizontalGroup(
            top5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(title5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        top5Layout.setVerticalGroup(
            top5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(title5, javax.swing.GroupLayout.DEFAULT_SIZE, 45, Short.MAX_VALUE)
        );

        val5.setFont(new java.awt.Font("Agency FB", 0, 36)); // NOI18N
        val5.setForeground(new java.awt.Color(204, 0, 0));
        val5.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        val5.setText("1,000,000");

        javax.swing.GroupLayout comp5Layout = new javax.swing.GroupLayout(comp5);
        comp5.setLayout(comp5Layout);
        comp5Layout.setHorizontalGroup(
            comp5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(top5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, comp5Layout.createSequentialGroup()
                .addContainerGap(17, Short.MAX_VALUE)
                .addComponent(val5, javax.swing.GroupLayout.PREFERRED_SIZE, 217, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(16, 16, 16))
        );
        comp5Layout.setVerticalGroup(
            comp5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(comp5Layout.createSequentialGroup()
                .addComponent(top5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(val5, javax.swing.GroupLayout.DEFAULT_SIZE, 53, Short.MAX_VALUE)
                .addContainerGap())
        );

        comp6.setBackground(new java.awt.Color(255, 255, 255));
        comp6.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

        top6.setBackground(new java.awt.Color(204, 0, 0));

        title6.setFont(new java.awt.Font("Agency FB", 0, 32)); // NOI18N
        title6.setForeground(new java.awt.Color(255, 255, 255));
        title6.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        title6.setText("Income");

        javax.swing.GroupLayout top6Layout = new javax.swing.GroupLayout(top6);
        top6.setLayout(top6Layout);
        top6Layout.setHorizontalGroup(
            top6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(title6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        top6Layout.setVerticalGroup(
            top6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(title6, javax.swing.GroupLayout.DEFAULT_SIZE, 45, Short.MAX_VALUE)
        );

        val6.setFont(new java.awt.Font("Agency FB", 0, 36)); // NOI18N
        val6.setForeground(new java.awt.Color(204, 0, 0));
        val6.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        val6.setText("1,000,000");

        javax.swing.GroupLayout comp6Layout = new javax.swing.GroupLayout(comp6);
        comp6.setLayout(comp6Layout);
        comp6Layout.setHorizontalGroup(
            comp6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(top6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, comp6Layout.createSequentialGroup()
                .addContainerGap(17, Short.MAX_VALUE)
                .addComponent(val6, javax.swing.GroupLayout.PREFERRED_SIZE, 217, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(16, 16, 16))
        );
        comp6Layout.setVerticalGroup(
            comp6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(comp6Layout.createSequentialGroup()
                .addComponent(top6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(val6, javax.swing.GroupLayout.DEFAULT_SIZE, 53, Short.MAX_VALUE)
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(425, 425, 425)
                        .addComponent(comp1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(424, 424, 424)
                        .addComponent(comp4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addGap(33, 33, 33)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(comp2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(comp3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(37, 37, 37)
                .addComponent(mid, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 39, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(comp5, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(comp6, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(32, 32, 32))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addComponent(comp1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(31, 31, 31)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(0, 21, Short.MAX_VALUE)
                        .addComponent(comp2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(191, 191, 191)
                        .addComponent(comp3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(197, 197, 197))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(mid, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(29, 29, 29))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(16, 16, 16)
                                .addComponent(comp6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(comp5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(55, 55, 55)))
                        .addComponent(comp4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );
    }// </editor-fold>//GEN-END:initComponents

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel comp1;
    private javax.swing.JPanel comp2;
    private javax.swing.JPanel comp3;
    private javax.swing.JPanel comp4;
    private javax.swing.JPanel comp5;
    private javax.swing.JPanel comp6;
    private javax.swing.JPanel mid;
    private javax.swing.JLabel title1;
    private javax.swing.JLabel title2;
    private javax.swing.JLabel title3;
    private javax.swing.JLabel title4;
    private javax.swing.JLabel title5;
    private javax.swing.JLabel title6;
    private javax.swing.JPanel top1;
    private javax.swing.JPanel top2;
    private javax.swing.JPanel top3;
    private javax.swing.JPanel top4;
    private javax.swing.JPanel top5;
    private javax.swing.JPanel top6;
    private javax.swing.JLabel val1;
    private javax.swing.JLabel val2;
    private javax.swing.JLabel val3;
    private javax.swing.JLabel val4;
    private javax.swing.JLabel val5;
    private javax.swing.JLabel val6;
    // End of variables declaration//GEN-END:variables
}
