package economysimulation.classes.gui.endgame;

import economysimulation.classes.global.Methods;
import economysimulation.classes.managers.animation.StockGraph;
import economysimulation.classes.managers.theme.GraphicUpdater;
import economysimulation.classes.managers.theme.ThemeUpdateEvent;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 *
 * @author Max Carter
 */
public class EndScreen extends javax.swing.JPanel implements ThemeUpdateEvent {

    /**
     * Creates new form SimulationOver
     */
    public EndScreen() {
        initComponents();
        Methods.AnimationGraph = new StockGraph(animBack);
    }

    @Override
    public void updateThemeEvent(GraphicUpdater updater) {
        updater.applyPanelThemes(new JPanel[]{ back }, new JPanel[]{ animBack });
        updater.applyTextThemes(new JLabel[]{ title }, null);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        animBack = new javax.swing.JPanel();
        back = new javax.swing.JPanel();
        title = new javax.swing.JLabel();

        animBack.setBackground(new java.awt.Color(204, 204, 204));

        javax.swing.GroupLayout animBackLayout = new javax.swing.GroupLayout(animBack);
        animBack.setLayout(animBackLayout);
        animBackLayout.setHorizontalGroup(
            animBackLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1300, Short.MAX_VALUE)
        );
        animBackLayout.setVerticalGroup(
            animBackLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        back.setBackground(new java.awt.Color(255, 255, 255));

        title.setFont(new java.awt.Font("Agency FB", 0, 48)); // NOI18N
        title.setForeground(new java.awt.Color(204, 0, 0));
        title.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        title.setText("Economy Simulation");

        javax.swing.GroupLayout backLayout = new javax.swing.GroupLayout(back);
        back.setLayout(backLayout);
        backLayout.setHorizontalGroup(
            backLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(backLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(title, javax.swing.GroupLayout.DEFAULT_SIZE, 482, Short.MAX_VALUE)
                .addContainerGap())
        );
        backLayout.setVerticalGroup(
            backLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(backLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(title, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(910, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(animBack, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(back, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(animBack, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(back, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel animBack;
    private javax.swing.JPanel back;
    private javax.swing.JLabel title;
    // End of variables declaration//GEN-END:variables
}
