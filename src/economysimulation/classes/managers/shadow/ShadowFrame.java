package economysimulation.classes.managers.shadow;

import economysimulation.classes.managers.popup.hint.HintManager;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import javax.swing.AbstractAction;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.Timer;

/**
 *
 * @author Max Carter
 */
public class ShadowFrame extends JFrame {
    
    protected Timer timer;
    protected boolean isRising;
    protected static final int
            frameWidth = 1800,
            frameHeight = 1000;
    protected static int
            xCoord, yCoord,
            shadowSize,
            shadowedMargin,
            duration,
            position,
            animationGap;
    protected static JPanel panel;
    
    public ShadowFrame(String frameTitle, JPanel panel, int panelPosition, int shadowSize, int durationInSeconds) {
        this.shadowSize = shadowSize;
        this.panel = panel;
        this.duration = durationInSeconds * 1000;
        this.position = panelPosition;
        shadowedMargin = 5 + shadowSize;
        animationGap = 20;
        isRising = true;
        
        // Creates the frame
        setUndecorated(true);
        setSize(panel.getWidth() + (shadowSize * 2), panel.getHeight() + (shadowSize * 2));
        setIconImage(new ImageIcon(getClass().getResource("/economysimulation/resources/icon/icon128.png")).getImage());
        setBackground(new Color(0, 0, 0, 0));
        setTitle("Economy Simulation: " + frameTitle);
        this.add(new ShadowPanel());
        
        setLocationInFrame(panelPosition);
        
        if (durationInSeconds > 0) {
            initTimer(isRising);
        }
    }
    
    protected final void setLocationInFrame(int panelPosition) {
        //define location
        switch (panelPosition) {
            case (Position.CENTRE):
                xCoord = ((frameWidth - getWidth())/2) - shadowedMargin;
                yCoord = ((frameHeight - getHeight())/2) - shadowedMargin;
                break;

            case (Position.BOTTOM_LEFT):
                xCoord = shadowedMargin;
                yCoord = frameHeight - getHeight() - shadowedMargin;
                break;
                
            case (Position.BOTTOM_RIGHT):
                xCoord = frameWidth - getWidth() - shadowedMargin;
                yCoord = frameHeight - getHeight() - shadowedMargin;
                break;
                
            case (Position.TOP_RIGHT):
                xCoord = frameWidth - getWidth() - shadowedMargin;
                yCoord = shadowedMargin;
                break;
                
            default:
            case (Position.TOP_LEFT):
                xCoord = shadowedMargin;
                yCoord = shadowedMargin;
                break;
        }
    }
    
    private void close() {
        this.dispose();
        if (getTitle().contains("Hint #")) {
            HintManager.hintDisplayEnded();
        }
    }
    
    private void exec() {
        timer.stop();
        if (animationGap > 0) {
            animationGap--;
            setLocation(xCoord, yCoord + animationGap);
            if (!isVisible()) setVisible(true);
        } else {
            isRising = false;
        }
        
        initTimer(isRising);
    }
    
    private void initTimer(boolean rise) {
        timer = new Timer(rise ? 50 : duration, new AbstractAction() {
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
    
    class ShadowPanel extends JPanel {

        public ShadowPanel() {
            setSize(panel.getWidth() + (shadowSize * 2), panel.getHeight() + (shadowSize * 2));
            add(panel);
            panel.setLocation(shadowSize, shadowSize);

            setBorder(BorderFactory.createCompoundBorder(this.getBorder(), BorderFactory.createEmptyBorder(
                    shadowSize, shadowSize, shadowSize, shadowSize)));
            setLayout(new BorderLayout());
        }

        @Override
        protected void paintComponent(Graphics g) {
            int shade = 0;
            int topOpacity = 80;
            for (int i = 0; i < shadowSize; i++) {
                g.setColor(new Color(shade, shade, shade, ((topOpacity / shadowSize) * i)));
                g.drawRect(i, i, this.getWidth() - ((i * 2) + 1), this.getHeight() - ((i * 2) + 1));
            }
        }
        
    }
    
}