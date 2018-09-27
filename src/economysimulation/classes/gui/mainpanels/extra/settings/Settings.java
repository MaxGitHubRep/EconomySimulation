package economysimulation.classes.gui.mainpanels.extra.settings;

import static economysimulation.classes.global.Methods.ThemeManager;
import economysimulation.classes.managers.theme.GraphicUpdater;
import economysimulation.classes.managers.theme.ThemeUpdateEvent;
import economysimulation.classes.managers.ui.Format;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 *
 * @author Max Carter
 */
public class Settings extends javax.swing.JPanel implements ThemeUpdateEvent {

    public int oldIndex = 0;
    
    private JLabel[] stateLabels;
    private JPanel[] backPanels;
    
    private ThemeMiddle middle;
    
    public Settings() {
        initComponents();
        
        middle = new ThemeMiddle();
        middle.setSize(360, 180);
        mid.add(middle);
        repaint();
        
        stateLabels = new JLabel[]{ state1, state2, state3, state4 };
        backPanels = new JPanel[]{ back1, back2, back3, back4 };
        
        for (int i = 0; i < backPanels.length; i++) {
            Format.addButtonFormat(backPanels[i], null);
            addEventListener(i);
        }
        
        ThemeManager.addThemeUpdateListener(this);
    }
    
    private void addEventListener(int id) {
        backPanels[id].addMouseListener(new MouseAdapter() {
            @Override 
            public void mouseEntered(MouseEvent e) {
                middle.sector = 3 - id;
                repaint();
            }
            
            @Override
            public void mouseExited(MouseEvent e) {
                middle.sector = 4;
                repaint();
            }
            
            @Override
            public void mouseClicked(MouseEvent e) {
                if (stateLabels[id].getText().equals("Unselected")){
                    ThemeManager.setTheme(ThemeManager.Themes[id]);
                    stateLabels[id].setText("Selected");
                    stateLabels[oldIndex].setText("Unselected");
                    oldIndex = id;
                }
            }
        });
    }
    
    @Override
    public void updateThemeEvent(GraphicUpdater updater) {
        updater.applyPanelThemes(new JPanel[]{ back1, back2, back3, back4 }, new JPanel[]{ top1, top2, top3, top4 });
        updater.applyTextThemes(new JLabel[]{ state1, state2, state3, state4 }, new JLabel[]{ label1, label2, label3, label4 });
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        mid = new javax.swing.JPanel();
        back1 = new javax.swing.JPanel();
        top1 = new javax.swing.JPanel();
        label1 = new javax.swing.JLabel();
        state1 = new javax.swing.JLabel();
        back2 = new javax.swing.JPanel();
        top2 = new javax.swing.JPanel();
        label2 = new javax.swing.JLabel();
        state2 = new javax.swing.JLabel();
        back3 = new javax.swing.JPanel();
        top3 = new javax.swing.JPanel();
        label3 = new javax.swing.JLabel();
        state3 = new javax.swing.JLabel();
        back4 = new javax.swing.JPanel();
        top4 = new javax.swing.JPanel();
        label4 = new javax.swing.JLabel();
        state4 = new javax.swing.JLabel();

        setOpaque(false);

        javax.swing.GroupLayout midLayout = new javax.swing.GroupLayout(mid);
        mid.setLayout(midLayout);
        midLayout.setHorizontalGroup(
            midLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 360, Short.MAX_VALUE)
        );
        midLayout.setVerticalGroup(
            midLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 180, Short.MAX_VALUE)
        );

        back1.setBackground(new java.awt.Color(255, 255, 255));
        back1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

        top1.setBackground(new java.awt.Color(204, 0, 0));

        label1.setBackground(new java.awt.Color(204, 0, 0));
        label1.setFont(new java.awt.Font("Agency FB", 0, 36)); // NOI18N
        label1.setForeground(new java.awt.Color(255, 255, 255));
        label1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        label1.setText("WHITE");

        javax.swing.GroupLayout top1Layout = new javax.swing.GroupLayout(top1);
        top1.setLayout(top1Layout);
        top1Layout.setHorizontalGroup(
            top1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(top1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(label1, javax.swing.GroupLayout.DEFAULT_SIZE, 188, Short.MAX_VALUE)
                .addContainerGap())
        );
        top1Layout.setVerticalGroup(
            top1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(label1, javax.swing.GroupLayout.DEFAULT_SIZE, 55, Short.MAX_VALUE)
        );

        state1.setFont(new java.awt.Font("Agency FB", 0, 18)); // NOI18N
        state1.setForeground(new java.awt.Color(204, 0, 0));
        state1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        state1.setText("Selected");

        javax.swing.GroupLayout back1Layout = new javax.swing.GroupLayout(back1);
        back1.setLayout(back1Layout);
        back1Layout.setHorizontalGroup(
            back1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(top1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(back1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(state1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        back1Layout.setVerticalGroup(
            back1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(back1Layout.createSequentialGroup()
                .addComponent(top1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(state1)
                .addContainerGap())
        );

        back2.setBackground(new java.awt.Color(255, 255, 255));
        back2.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

        top2.setBackground(new java.awt.Color(204, 0, 0));

        label2.setBackground(new java.awt.Color(204, 0, 0));
        label2.setFont(new java.awt.Font("Agency FB", 0, 36)); // NOI18N
        label2.setForeground(new java.awt.Color(255, 255, 255));
        label2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        label2.setText("DARK");

        javax.swing.GroupLayout top2Layout = new javax.swing.GroupLayout(top2);
        top2.setLayout(top2Layout);
        top2Layout.setHorizontalGroup(
            top2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(top2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(label2, javax.swing.GroupLayout.DEFAULT_SIZE, 188, Short.MAX_VALUE)
                .addContainerGap())
        );
        top2Layout.setVerticalGroup(
            top2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(label2, javax.swing.GroupLayout.DEFAULT_SIZE, 55, Short.MAX_VALUE)
        );

        state2.setFont(new java.awt.Font("Agency FB", 0, 18)); // NOI18N
        state2.setForeground(new java.awt.Color(204, 0, 0));
        state2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        state2.setText("Unselected");

        javax.swing.GroupLayout back2Layout = new javax.swing.GroupLayout(back2);
        back2.setLayout(back2Layout);
        back2Layout.setHorizontalGroup(
            back2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(top2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(back2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(state2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        back2Layout.setVerticalGroup(
            back2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(back2Layout.createSequentialGroup()
                .addComponent(top2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(state2)
                .addContainerGap())
        );

        back3.setBackground(new java.awt.Color(255, 255, 255));
        back3.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

        top3.setBackground(new java.awt.Color(204, 0, 0));

        label3.setBackground(new java.awt.Color(204, 0, 0));
        label3.setFont(new java.awt.Font("Agency FB", 0, 36)); // NOI18N
        label3.setForeground(new java.awt.Color(255, 255, 255));
        label3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        label3.setText("OCEAN");

        javax.swing.GroupLayout top3Layout = new javax.swing.GroupLayout(top3);
        top3.setLayout(top3Layout);
        top3Layout.setHorizontalGroup(
            top3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(top3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(label3, javax.swing.GroupLayout.DEFAULT_SIZE, 188, Short.MAX_VALUE)
                .addContainerGap())
        );
        top3Layout.setVerticalGroup(
            top3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(label3, javax.swing.GroupLayout.DEFAULT_SIZE, 55, Short.MAX_VALUE)
        );

        state3.setFont(new java.awt.Font("Agency FB", 0, 18)); // NOI18N
        state3.setForeground(new java.awt.Color(204, 0, 0));
        state3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        state3.setText("Unselected");

        javax.swing.GroupLayout back3Layout = new javax.swing.GroupLayout(back3);
        back3.setLayout(back3Layout);
        back3Layout.setHorizontalGroup(
            back3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(top3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(back3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(state3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        back3Layout.setVerticalGroup(
            back3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(back3Layout.createSequentialGroup()
                .addComponent(top3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(state3)
                .addContainerGap())
        );

        back4.setBackground(new java.awt.Color(255, 255, 255));
        back4.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

        top4.setBackground(new java.awt.Color(204, 0, 0));

        label4.setBackground(new java.awt.Color(204, 0, 0));
        label4.setFont(new java.awt.Font("Agency FB", 0, 36)); // NOI18N
        label4.setForeground(new java.awt.Color(255, 255, 255));
        label4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        label4.setText("PINK");

        javax.swing.GroupLayout top4Layout = new javax.swing.GroupLayout(top4);
        top4.setLayout(top4Layout);
        top4Layout.setHorizontalGroup(
            top4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(top4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(label4, javax.swing.GroupLayout.DEFAULT_SIZE, 188, Short.MAX_VALUE)
                .addContainerGap())
        );
        top4Layout.setVerticalGroup(
            top4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(label4, javax.swing.GroupLayout.DEFAULT_SIZE, 55, Short.MAX_VALUE)
        );

        state4.setFont(new java.awt.Font("Agency FB", 0, 18)); // NOI18N
        state4.setForeground(new java.awt.Color(204, 0, 0));
        state4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        state4.setText("Unselected");

        javax.swing.GroupLayout back4Layout = new javax.swing.GroupLayout(back4);
        back4.setLayout(back4Layout);
        back4Layout.setHorizontalGroup(
            back4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(top4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(back4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(state4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        back4Layout.setVerticalGroup(
            back4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(back4Layout.createSequentialGroup()
                .addComponent(top4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(state4)
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(54, 54, 54)
                        .addComponent(back1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(116, 116, 116))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(back2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(33, 33, 33)))
                .addComponent(mid, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(30, 30, 30)
                        .addComponent(back3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 107, Short.MAX_VALUE)
                        .addComponent(back4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(63, 63, 63))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(118, 118, 118)
                        .addComponent(mid, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(41, 41, 41)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(back3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(back4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(back2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(back1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addContainerGap(502, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel back1;
    private javax.swing.JPanel back2;
    private javax.swing.JPanel back3;
    private javax.swing.JPanel back4;
    private javax.swing.JLabel label1;
    private javax.swing.JLabel label2;
    private javax.swing.JLabel label3;
    private javax.swing.JLabel label4;
    private javax.swing.JPanel mid;
    private javax.swing.JLabel state1;
    private javax.swing.JLabel state2;
    private javax.swing.JLabel state3;
    private javax.swing.JLabel state4;
    private javax.swing.JPanel top1;
    private javax.swing.JPanel top2;
    private javax.swing.JPanel top3;
    private javax.swing.JPanel top4;
    // End of variables declaration//GEN-END:variables
}
