package economysimulation.classes.managers.shadow;

import economysimulation.classes.managers.exception.InvalidPanelSizeException;
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
    
    /**
    * Creates an undecorated frame with a given panel and specified parameters.
    *
    * @param frameTitle The title of the frame.
    * @param panel The panel that will be applied to the frame.
    * @param panelPosition The location of the frame on the screen.
    * @param shadowSize Size of the shadow.
    * @param durationInSeconds How long the panel will stay on screen for.
     * @param alwaysOnTop If the frame will always be on top.
    * 
    * @throws InvalidPanelSizeException When the panel dimensions have invalid lengths.
    */
    public ShadowFrame(String frameTitle, JPanel panel, int panelPosition, int shadowSize, int durationInSeconds, boolean alwaysOnTop) throws InvalidPanelSizeException {
        super("Economy Simulation: " + frameTitle);
        if (panelPosition > 4 || panelPosition < 0) {
            throw new IllegalArgumentException("Panel position must be between 0 and 4.");
            
        } else if (shadowSize < 0) {
            throw new IllegalArgumentException("The size of the shadow must be greater than 0.");
            
        } else if (panel.getWidth() > frameWidth - (shadowedMargin * 2) || panel.getHeight() > frameHeight - (shadowedMargin * 2)) {
            throw new InvalidPanelSizeException();
        }
        
        this.shadowSize = shadowSize;
        this.panel = panel;
        this.duration = durationInSeconds * 1000;
        this.position = panelPosition;
        shadowedMargin = 5 + shadowSize;
        animationGap = 20;
        isRising = true;
        
        // Creates the frame
        super.setUndecorated(true);
        super.setSize(panel.getWidth() + (shadowSize * 2), panel.getHeight() + (shadowSize * 2));
        super.setIconImage(new ImageIcon(getClass().getResource("/economysimulation/resources/icon/icon128.png")).getImage());
        super.setBackground(new Color(0, 0, 0, 0));
        this.add(new ShadowPanel());
        super.setAlwaysOnTop(alwaysOnTop);
        
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
            super.add(panel);
            panel.setLocation(shadowSize, shadowSize);

            super.setBorder(BorderFactory.createCompoundBorder(this.getBorder(), BorderFactory.createEmptyBorder(
                    shadowSize, shadowSize, shadowSize, shadowSize)));
            super.setLayout(new BorderLayout());
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