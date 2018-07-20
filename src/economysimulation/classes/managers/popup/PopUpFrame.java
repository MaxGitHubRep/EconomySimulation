package economysimulation.classes.managers.popup;

import economysimulation.classes.managers.themes.Theme;
import javax.swing.JPanel;

/**
 *
 * @author Max Carter
 */
public class PopUpFrame extends javax.swing.JFrame {

    private JPanel panel;
    private String title;
    
    public PopUpFrame(JPanel panel, String title) {
        initComponents();
        this.panel = panel;
        this.title = title;
        
    }

    public static void updateTheme() {
        Theme.applyPanelThemes(new JPanel[]{ back }, null, null, null);
    }
    
    public void createPopUpFrame() {
        updateTheme();
        back.add(panel);
        this.setResizable(false);
        this.setVisible(true);
        this.setSize(panel.getWidth(), panel.getHeight());
        this.setTitle("Economy Simulation: " + title);
        this.pack();
        
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        back = new javax.swing.JPanel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setBackground(new java.awt.Color(255, 255, 255));
        setResizable(false);

        javax.swing.GroupLayout backLayout = new javax.swing.GroupLayout(back);
        back.setLayout(backLayout);
        backLayout.setHorizontalGroup(
            backLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1100, Short.MAX_VALUE)
        );
        backLayout.setVerticalGroup(
            backLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 800, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(back, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(back, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private static javax.swing.JPanel back;
    // End of variables declaration//GEN-END:variables
}
