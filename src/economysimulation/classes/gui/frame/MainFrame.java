package economysimulation.classes.gui.frame;

import economysimulation.classes.global.Methods;
import economysimulation.classes.managers.theme.GraphicUpdater;
import economysimulation.classes.gui.startup.WelcomePanel;
import economysimulation.classes.managers.theme.Theme;
import static economysimulation.classes.global.Methods.ThemeManager;
import economysimulation.classes.managers.theme.ThemeUpdateEvent;
import java.awt.BorderLayout;
import java.awt.Frame;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import javax.swing.ImageIcon;
import javax.swing.JPanel;

/**
 *
 * @author Max Carter
 */
public class MainFrame extends javax.swing.JFrame implements ThemeUpdateEvent {

    private static int PositionX, PositionY;
    
    public MainFrame() {
        initComponents();
        ThemeManager.addThemeUpdateListener(this);
        setIconImage(new ImageIcon(getClass().getResource("/economysimulation/resources/icon/icon128.png")).getImage());
        addToMainFrame(new WelcomePanel());
    }

    public void addToMainFrame(JPanel panel) {
        back.removeAll();
        back.revalidate();
        back.setLayout(new BorderLayout());
        back.add(panel);
        back.repaint();
        
    }

    public static void frameDragged(JPanel dragPanel) {
        dragPanel.addMouseListener(new MouseAdapter() {
            public void mousePressed(MouseEvent me) {
                PositionX = me.getX();
                PositionY = me.getY();

            }
        });
                
        dragPanel.addMouseMotionListener(new MouseMotionAdapter() {
            public void mouseDragged(MouseEvent me) {
                for (Frame frame : MainFrame.getFrames()) {
                    if (!frame.getTitle().contains("Hint"))
                    frame.setLocation(frame.getLocation().x + me.getX() - PositionX, frame.getLocation().y + me.getY() - PositionY);
                } 
            }
        });
    }

    @Override
    public void updateThemeEvent(GraphicUpdater updater) {
        updater.applyPanelThemes(new JPanel[]{ back }, null);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        back = new javax.swing.JPanel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Economy Simulation");
        setBackground(new java.awt.Color(255, 255, 255));
        setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        setUndecorated(true);
        setResizable(false);

        back.setBackground(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout backLayout = new javax.swing.GroupLayout(back);
        back.setLayout(backLayout);
        backLayout.setHorizontalGroup(
            backLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1800, Short.MAX_VALUE)
        );
        backLayout.setVerticalGroup(
            backLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1000, Short.MAX_VALUE)
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

    public static void main(String args[]) {

//        try {
//            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
//        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | javax.swing.UnsupportedLookAndFeelException ex) {
//            ex.printStackTrace();
//        }

        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                ThemeManager = new Theme();
                Methods.FrameDisplay = new MainFrame();
                Methods.FrameDisplay.setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    public static javax.swing.JPanel back;
    // End of variables declaration//GEN-END:variables
}
