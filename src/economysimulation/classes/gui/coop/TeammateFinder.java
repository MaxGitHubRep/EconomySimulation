package economysimulation.classes.gui.coop;

import economysimulation.classes.managers.theme.GraphicUpdater;
import economysimulation.classes.managers.theme.ThemeUpdateEvent;
import javax.swing.JPanel;

/**
 *
 * @author Max Carter
 */
public class TeammateFinder extends javax.swing.JPanel implements ThemeUpdateEvent {

    private ControlPanel teammateController;
    
    public final int HEIGHT = 600, SPEED = 2;
    public int START = 0, plus = 0;
    
    private Thread animationThread, connectionThread;
    
    /**
     * Creates new form TeammateFinder
     */
    public TeammateFinder() {
        initComponents();
        START = 870;
        
        teammateController = new ControlPanel();
        
        add(teammateController);
        teammateController.setSize(1800, 600);
        teammateController.setLocation(0, START);
        
    }
    
    public void openControlPanel() {
        teammateController.arrowState(false);
        moveControlPanel(true);
    }
    
    public void closeControlPanel() {
        teammateController.arrowState(true);
        moveControlPanel(false);
    }
    
    private void moveControlPanel(boolean up) {
        animationThread = new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 470; i++) {
                    teammateController.setLocation(0, teammateController.getY() + (up ? -1 : 1));
                    
                    try {
                        Thread.sleep(SPEED);
                    } catch (InterruptedException ex) {
                        ex.printStackTrace();
                    }
                }
            }
        });
        animationThread.start();
    }
    
    public ControlPanel getTeammateController() {
        return teammateController;
    }

    @Override
    public void onThemeUpdate(GraphicUpdater updater) {
        updater.applyPanelThemes(new JPanel[]{}, null);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        countdownPanel = new javax.swing.JPanel();

        setBackground(new java.awt.Color(204, 204, 204));

        javax.swing.GroupLayout countdownPanelLayout = new javax.swing.GroupLayout(countdownPanel);
        countdownPanel.setLayout(countdownPanelLayout);
        countdownPanelLayout.setHorizontalGroup(
            countdownPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 200, Short.MAX_VALUE)
        );
        countdownPanelLayout.setVerticalGroup(
            countdownPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 200, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(0, 1600, Short.MAX_VALUE)
                .addComponent(countdownPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(countdownPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 700, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel countdownPanel;
    // End of variables declaration//GEN-END:variables
}
