package economysimulation.classes.gui.frame;

import economysimulation.classes.global.Methods;
import economysimulation.classes.managers.theme.GraphicUpdater;
import economysimulation.classes.gui.startup.WelcomePanel;
import economysimulation.classes.managers.theme.ThemeManager;
import economysimulation.classes.managers.theme.ThemeUpdateEvent;
import java.awt.BorderLayout;
import java.awt.Frame;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import javax.swing.ImageIcon;
import javax.swing.JPanel;
import static economysimulation.classes.global.Methods.ThemeHandler;

/**
 * @author Max Carter
 */
public class MainFrame extends javax.swing.JFrame implements ThemeUpdateEvent {

    //Position of the frame relative to the screen.
    private static int PositionX, PositionY;
    
    /**
     * Creates a new MainFrame.
     */
    public MainFrame() {
        initComponents();
        ThemeHandler.addThemeUpdateListener(this);
        setIconImage(new ImageIcon(getClass().getResource("/economysimulation/resources/icon/icon128.png")).getImage());
        Methods.IntroPanel = new WelcomePanel();
        addToMainFrame(Methods.IntroPanel);
    }

    /**
     * Adds a panel to the main frame.
     * @param panel Panel to add.
     */
    public void addToMainFrame(JPanel panel) {
        back.removeAll();
        back.revalidate();
        back.setLayout(new BorderLayout());
        back.add(panel);
        back.repaint();
        
    }

    /**
     * Allows a panel to drag the main frame across the screen.
     * @param dragPanel Panel which can drag the frame.
     */
    public static void frameDragged(JPanel dragPanel) {
        dragPanel.addMouseListener(new MouseAdapter() {
            public void mousePressed(MouseEvent me) {
                PositionX = me.getX();
                PositionY = me.getY();

            }
        });
                
        dragPanel.addMouseMotionListener(new MouseMotionAdapter() {
            public void mouseDragged(MouseEvent me) {
                /** Finds the Frame used and moves it across the screen.
                 *  JFrame and Frame are not the same objects, and a JFrame
                 *  doesn't support this which is why I need to loop through all the Frames.
                 */ 
                for (Frame frame : MainFrame.getFrames()) {
                    if (!frame.getTitle().contains("Hint"))
                    frame.setLocation(frame.getLocation().x + me.getX() - PositionX, frame.getLocation().y + me.getY() - PositionY);
                } 
            }
        });
    }

    @Override
    public void onThemeUpdate(GraphicUpdater updater) {
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

    //Main method.
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                //initiates the main frame and theme manager.
                ThemeHandler = new ThemeManager();
                Methods.FrameDisplay = new MainFrame();
                Methods.FrameDisplay.setVisible(true);
                Methods.FrameDisplay.setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    public static javax.swing.JPanel back;
    // End of variables declaration//GEN-END:variables
}
