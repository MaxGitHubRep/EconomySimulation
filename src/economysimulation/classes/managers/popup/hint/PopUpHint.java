package economysimulation.classes.managers.popup.hint;

import economysimulation.classes.Methods;
import economysimulation.classes.managers.themes.Theme;
import java.awt.Color;
import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import javax.swing.AbstractAction;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.Timer;

/**
 *
 * @author Max Carter
 */
public class PopUpHint extends javax.swing.JFrame {

    private Timer timer;
    protected static boolean isRising = false, isShowing = false;
    
    private static int
            pX, pY,
            hintDisplayTime = 5000, risingDelay = 40,
            xCoord = 900;
    
    public PopUpHint(String title, String description, int urgency) {
        initComponents();
        setIconImage(new ImageIcon(getClass().getResource("/economysimulation/resources/icon/icon128.png")).getImage());
        
        this.setBackground(new Color(0, 0, 0, 0));
        this.add(new ShadowPanel(10, title, description, urgency));
        
        Methods.totalHints++;
        this.setVisible(true);
        this.setTitle("Economy Simulation: Hint #" + Methods.totalHints);
        this.setLocation(1280, 900);

        isShowing = true;
        isRising = true;
        xCoord = 900;
        updateTheme();
        initTimer(isRising);
    }
    
    protected static void frameDragged(JPanel dragPanel) {
        dragPanel.addMouseListener(new MouseAdapter() {
            public void mousePressed(MouseEvent me) {
                pX = me.getX();
                pY = me.getY();
            }
        });
                
        dragPanel.addMouseMotionListener(new MouseMotionAdapter() {
            public void mouseDragged(MouseEvent me) {
                for (Frame frame : PopUpHint.getFrames()) {
                    if (frame.getTitle().contains("Hint"))
                    frame.setLocation(frame.getLocation().x + me.getX() - pX, frame.getLocation().y + me.getY() - pY);
                } 
            }
        });
    }

    public static void updateTheme() {
        Theme.applyPanelThemes(new JPanel[]{ HintDisplay.bottom }, null, null, null);
        Theme.applyTextThemes(new JLabel[]{ HintDisplay.descLabel }, null);
    }
    
    private void close() {
        if (HintManager.titleList.size() > 0) {
            HintManager.titleList.remove(0);
            HintManager.descList.remove(0);
            HintManager.urgencyList.remove(0);
        }
        isShowing = false;
        HintManager.hintDisplayReady();
        this.dispose();
    }
    
    private void exec() {
        timer.stop();
        if (xCoord > 880) {
            xCoord--;
            this.setLocation(1280, xCoord);
        } else {
            isRising = false;
        }
        
        initTimer(isRising);
    }
    
    private void initTimer(boolean rise) {
        timer = new Timer(rise ? risingDelay : hintDisplayTime, new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (rise) {
                    exec();
                } else {
                    close();
                }
            }
        }); 
        timer.start();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setAutoRequestFocus(false);
        setUndecorated(true);
        setResizable(false);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 520, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 120, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
