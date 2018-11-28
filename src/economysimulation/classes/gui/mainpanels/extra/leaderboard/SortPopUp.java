package economysimulation.classes.gui.mainpanels.extra.leaderboard;

import economysimulation.classes.managers.sorting.conditions.SearchComponent;

/**
 *
 * @author Max Carter
 */
public class SortPopUp extends javax.swing.JPanel {

    private SearchComponent[] searchComponents = null;
    
    private int selectedComponentIndex = -2;
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
        
        selectedSearchComponent = SearchComponent.GDP;
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
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
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

        jPanel1.setBackground(new java.awt.Color(102, 102, 102));

        title.setFont(new java.awt.Font("Agency FB", 0, 52)); // NOI18N
        title.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        title.setText("Custom Sort");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(title, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(title, javax.swing.GroupLayout.DEFAULT_SIZE, 88, Short.MAX_VALUE)
                .addContainerGap())
        );

        javax.swing.GroupLayout col1Layout = new javax.swing.GroupLayout(col1);
        col1.setLayout(col1Layout);
        col1Layout.setHorizontalGroup(
            col1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 40, Short.MAX_VALUE)
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
                .addComponent(title1, javax.swing.GroupLayout.DEFAULT_SIZE, 178, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(col1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        back1Layout.setVerticalGroup(
            back1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(col1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(back1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(title1, javax.swing.GroupLayout.DEFAULT_SIZE, 63, Short.MAX_VALUE)
                .addContainerGap())
        );

        javax.swing.GroupLayout col2Layout = new javax.swing.GroupLayout(col2);
        col2.setLayout(col2Layout);
        col2Layout.setHorizontalGroup(
            col2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 40, Short.MAX_VALUE)
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
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(title2, javax.swing.GroupLayout.DEFAULT_SIZE, 172, Short.MAX_VALUE)
                .addContainerGap())
        );
        back2Layout.setVerticalGroup(
            back2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, back2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(title2, javax.swing.GroupLayout.DEFAULT_SIZE, 63, Short.MAX_VALUE)
                .addContainerGap())
            .addComponent(col2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        state1.setFont(new java.awt.Font("Agency FB", 0, 36)); // NOI18N
        state1.setSelected(true);
        state1.setText("High to Low");
        state1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                state1ActionPerformed(evt);
            }
        });

        state2.setFont(new java.awt.Font("Agency FB", 0, 36)); // NOI18N
        state2.setText("Low to High");
        state2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                state2ActionPerformed(evt);
            }
        });

        arrow1.setFont(new java.awt.Font("Agency FB", 0, 72)); // NOI18N
        arrow1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        arrow1.setText("<");

        arrow2.setFont(new java.awt.Font("Agency FB", 0, 72)); // NOI18N
        arrow2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        arrow2.setText(">");

        component.setFont(new java.awt.Font("Agency FB", 0, 62)); // NOI18N
        component.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        component.setText("Component");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addComponent(back1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 33, Short.MAX_VALUE)
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
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(state2, javax.swing.GroupLayout.PREFERRED_SIZE, 212, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(12, 12, 12)
                        .addComponent(arrow1, javax.swing.GroupLayout.PREFERRED_SIZE, 68, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(component, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(arrow2, javax.swing.GroupLayout.PREFERRED_SIZE, 68, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
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
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(back1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(back2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    private void state1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_state1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_state1ActionPerformed

    private void state2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_state2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_state2ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel arrow1;
    private javax.swing.JLabel arrow2;
    private javax.swing.JPanel back1;
    private javax.swing.JPanel back2;
    private javax.swing.JPanel col1;
    private javax.swing.JPanel col2;
    private javax.swing.JLabel component;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JRadioButton state1;
    private javax.swing.JRadioButton state2;
    private javax.swing.JLabel title;
    private javax.swing.JLabel title1;
    private javax.swing.JLabel title2;
    // End of variables declaration//GEN-END:variables
}
