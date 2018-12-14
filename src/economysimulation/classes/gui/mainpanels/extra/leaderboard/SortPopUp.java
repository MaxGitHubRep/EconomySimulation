package economysimulation.classes.gui.mainpanels.extra.leaderboard;

import economysimulation.classes.global.Methods;
import economysimulation.classes.managers.sorting.conditions.SearchComponent;
import economysimulation.classes.managers.sorting.conditions.SearchCondition;
import economysimulation.classes.managers.theme.GraphicUpdater;
import economysimulation.classes.managers.theme.ThemeUpdateEvent;
import economysimulation.classes.managers.ui.Format;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;

/**
 *
 * @author Max Carter
 */
public class SortPopUp extends javax.swing.JPanel implements ThemeUpdateEvent {

    private SearchComponent[] searchComponents = null;
    
    private int selectedComponentIndex = 0;
    private SearchComponent selectedSearchComponent = null;
    
    /**
     * Creates new form SortPopUp
     */
    public SortPopUp() {
        initComponents();
        searchComponents = new SearchComponent[] {
            SearchComponent.GDP,
            SearchComponent.TICKS,
            SearchComponent.CONSUMPTION,
            SearchComponent.SAVINGS,
            SearchComponent.POPULATION,
            SearchComponent.UNEMPLOYMENT,
            SearchComponent.PEOPLE_SUPPORT,
            SearchComponent.INVESTMENT,
            SearchComponent.TAXATION,
            SearchComponent.FIRM_PROFITS,
            SearchComponent.FIRM_SUPPORT
        };
        
        Methods.ThemeHandler.addThemeUpdateListener(this);
        Format.addButtonFormat(back1, col1);
        Format.addButtonFormat(back2, col2);
        
        setComponentSelected(0);
    }

    private void componentMove(boolean forward) {
        if (forward) {
            if (selectedComponentIndex == searchComponents.length-1) {
                selectedComponentIndex = 0;
            } else {
                selectedComponentIndex ++;
            }
        } else {
            if (selectedComponentIndex == 0) {
                selectedComponentIndex = 10;
            } else {
                selectedComponentIndex--;
            }
        }
        setComponentSelected(selectedComponentIndex);
    }
    
    private void setComponentSelected(int index) {
        selectedSearchComponent = searchComponents[index];
        component.setText(selectedSearchComponent.getName());
    }
    
    private void close(boolean update) {
        if (update) {
            Methods.LBDisplay.onSortCustomClose(state1.isSelected() ? SearchCondition.HIGH_TO_LOW : SearchCondition.LOW_TO_HIGH,
                selectedSearchComponent);
        } else {
            Methods.LBDisplay.onSortCustomClose(null, null);
        }
    }
    
    @Override
    public void onThemeUpdate(GraphicUpdater updater) {
        updater.applyPanelThemes(new JPanel[]{ back1, back2, this }, new JPanel[]{ top });
        updater.applyTextThemes(new JLabel[]{ title1, title2, component, arrow1, arrow2 }, new JLabel[]{ title });
        updater.applyRadioButtonThemes(new JRadioButton[]{ state1, state2 }, null);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        searchConditionButtons = new javax.swing.ButtonGroup();
        top = new javax.swing.JPanel();
        title = new javax.swing.JLabel();
        back1 = new javax.swing.JPanel();
        col1 = new javax.swing.JPanel();
        title1 = new javax.swing.JLabel();
        back2 = new javax.swing.JPanel();
        col2 = new javax.swing.JPanel();
        title2 = new javax.swing.JLabel();
        state1 = new javax.swing.JRadioButton();
        state2 = new javax.swing.JRadioButton();
        jSeparator1 = new javax.swing.JSeparator();
        jSeparator2 = new javax.swing.JSeparator();
        arrow1 = new javax.swing.JLabel();
        arrow2 = new javax.swing.JLabel();
        component = new javax.swing.JLabel();

        setBackground(new java.awt.Color(255, 255, 255));

        top.setBackground(new java.awt.Color(102, 102, 102));

        title.setFont(new java.awt.Font("Agency FB", 0, 52)); // NOI18N
        title.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        title.setText("Custom Sort");

        javax.swing.GroupLayout topLayout = new javax.swing.GroupLayout(top);
        top.setLayout(topLayout);
        topLayout.setHorizontalGroup(
            topLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(topLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(title, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        topLayout.setVerticalGroup(
            topLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(topLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(title, javax.swing.GroupLayout.DEFAULT_SIZE, 88, Short.MAX_VALUE)
                .addContainerGap())
        );

        back1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        back1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                back1MouseClicked(evt);
            }
        });

        javax.swing.GroupLayout col1Layout = new javax.swing.GroupLayout(col1);
        col1.setLayout(col1Layout);
        col1Layout.setHorizontalGroup(
            col1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 32, Short.MAX_VALUE)
        );
        col1Layout.setVerticalGroup(
            col1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        title1.setFont(new java.awt.Font("Agency FB", 0, 42)); // NOI18N
        title1.setText("Sort & Close");

        javax.swing.GroupLayout back1Layout = new javax.swing.GroupLayout(back1);
        back1.setLayout(back1Layout);
        back1Layout.setHorizontalGroup(
            back1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, back1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(title1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 19, Short.MAX_VALUE)
                .addComponent(col1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        back1Layout.setVerticalGroup(
            back1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(col1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, back1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(title1)
                .addContainerGap())
        );

        back2.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        back2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                back2MouseClicked(evt);
            }
        });

        javax.swing.GroupLayout col2Layout = new javax.swing.GroupLayout(col2);
        col2.setLayout(col2Layout);
        col2Layout.setHorizontalGroup(
            col2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 32, Short.MAX_VALUE)
        );
        col2Layout.setVerticalGroup(
            col2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        title2.setFont(new java.awt.Font("Agency FB", 0, 42)); // NOI18N
        title2.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        title2.setText("Cancel");

        javax.swing.GroupLayout back2Layout = new javax.swing.GroupLayout(back2);
        back2.setLayout(back2Layout);
        back2Layout.setHorizontalGroup(
            back2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, back2Layout.createSequentialGroup()
                .addComponent(col2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 93, Short.MAX_VALUE)
                .addComponent(title2)
                .addContainerGap())
        );
        back2Layout.setVerticalGroup(
            back2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(col2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, back2Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(title2)
                .addContainerGap())
        );

        searchConditionButtons.add(state1);
        state1.setFont(new java.awt.Font("Agency FB", 0, 36)); // NOI18N
        state1.setSelected(true);
        state1.setText("High to Low");
        state1.setFocusPainted(false);

        searchConditionButtons.add(state2);
        state2.setFont(new java.awt.Font("Agency FB", 0, 36)); // NOI18N
        state2.setText("Low to High");
        state2.setFocusPainted(false);

        arrow1.setFont(new java.awt.Font("Agency FB", 0, 72)); // NOI18N
        arrow1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        arrow1.setText("<");
        arrow1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        arrow1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                arrow1MouseClicked(evt);
            }
        });

        arrow2.setFont(new java.awt.Font("Agency FB", 0, 72)); // NOI18N
        arrow2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        arrow2.setText(">");
        arrow2.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        arrow2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                arrow2MouseClicked(evt);
            }
        });

        component.setFont(new java.awt.Font("Agency FB", 0, 58)); // NOI18N
        component.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        component.setText("Component");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(top, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addComponent(back1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(back2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jSeparator2)
                            .addComponent(jSeparator1)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(state1, javax.swing.GroupLayout.PREFERRED_SIZE, 212, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 57, Short.MAX_VALUE)
                                .addComponent(state2, javax.swing.GroupLayout.PREFERRED_SIZE, 212, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(12, 12, 12)
                        .addComponent(arrow1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(component, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(arrow2)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(top, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(state1, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(state2, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(4, 4, 4)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(arrow1, javax.swing.GroupLayout.PREFERRED_SIZE, 68, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(arrow2, javax.swing.GroupLayout.PREFERRED_SIZE, 68, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(component, javax.swing.GroupLayout.PREFERRED_SIZE, 68, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(back2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(back1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    private void arrow1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_arrow1MouseClicked
        componentMove(true);
    }//GEN-LAST:event_arrow1MouseClicked

    private void arrow2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_arrow2MouseClicked
        componentMove(false);
    }//GEN-LAST:event_arrow2MouseClicked

    private void back1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_back1MouseClicked
        close(true);
    }//GEN-LAST:event_back1MouseClicked

    private void back2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_back2MouseClicked
        close(false);
    }//GEN-LAST:event_back2MouseClicked


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel arrow1;
    private javax.swing.JLabel arrow2;
    private javax.swing.JPanel back1;
    private javax.swing.JPanel back2;
    private javax.swing.JPanel col1;
    private javax.swing.JPanel col2;
    private javax.swing.JLabel component;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.ButtonGroup searchConditionButtons;
    private javax.swing.JRadioButton state1;
    private javax.swing.JRadioButton state2;
    private javax.swing.JLabel title;
    private javax.swing.JLabel title1;
    private javax.swing.JLabel title2;
    private javax.swing.JPanel top;
    // End of variables declaration//GEN-END:variables
}
