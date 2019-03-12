package economysimulation.classes.managers.shadow;

import economysimulation.classes.managers.exception.InvalidPanelSizeException;
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
 * @author Max Carter
 */
public class ShadowFrame extends JFrame {
    
    /** Instance of the timer. */
    protected Timer timer;
    
    /** If the frame is still in the opening animation rise. */
    protected boolean isRising;
    
    //MainFrame dimensions.
    protected static final int
            frameWidth = 1800,
            frameHeight = 1000;
    
    //Data about the shadow frame - dependent on user input.
    protected int
            xCoord, yCoord,
            shadowSize,
            shadowedMargin,
            duration,
            position,
            animationGap;
    protected static JPanel panel;
    
    /**
    * Creates an undecorated frame with a given panel and specified parameters.
    * @param frameTitle The title of the frame.
    * @param panel The panel that will be applied to the frame.
    * @param panelPosition The location of the frame on the screen.
    * @param shadowSize Size of the shadow.
    * @param durationInSeconds How long the panel will stay on screen for.
    * 
    * @throws InvalidPanelSizeException When the panel dimensions have invalid lengths.
    */
    public ShadowFrame(String frameTitle, JPanel panel, int panelPosition, int shadowSize, int durationInSeconds) throws InvalidPanelSizeException {
        super("Economy Simulation: " + frameTitle);
        
        //validates that the parameters are valid.
        if (panelPosition > 4 || panelPosition < 0) {
            throw new IllegalArgumentException("Panel position must be between 0 and 4.");
            
        } else if (shadowSize < 0) {
            throw new IllegalArgumentException("The size of the shadow must be greater than 0.");
            
        } else if (panel.getWidth() > frameWidth - (shadowedMargin * 2) || panel.getHeight() > frameHeight - (shadowedMargin * 2)) {
            throw new InvalidPanelSizeException();
        }
        
        //sets up frame.
        this.shadowSize = shadowSize;
        this.panel = panel;
        this.duration = durationInSeconds * 1000;
        this.position = panelPosition;
        shadowedMargin = 5 + shadowSize;
        animationGap = 20;
        isRising = true;
        
    }
    
    /** Opens up the frame. */
    public void inflate() {
        // Creates the frame
        super.setAlwaysOnTop(true);
        super.setUndecorated(true);
        super.setSize(panel.getWidth() + (shadowSize * 2), panel.getHeight() + (shadowSize * 2));
        super.setIconImage(new ImageIcon(getClass().getResource("/economysimulation/resources/icon/icon128.png")).getImage());
        super.setBackground(new Color(0, 0, 0, 0));
        this.add(new ShadowPanel());
        
        setLocationInFrame(position);
        
        //starts moving the timer.
        if (duration > 0) {
            initTimer(isRising);
        }
    }
    
    /**
     * Defines the position of the location on the screen of the frame.
     * @param panelPosition Position index.
     */
    protected final void setLocationInFrame(int panelPosition) {
        //sets the x/y coords of the frame.
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
    
    /** Disposes the frame. */
    private void close() {
        this.dispose();
    }
    
    /** Moves the frame up. */
    private void exec() {
        timer.stop();
        if (animationGap > 0) {
            //keeps reducing the y-coord-gap between frame and destination until it reaches 0.
            animationGap--;
            setLocation(xCoord, yCoord + animationGap);
            if (!isVisible()) setVisible(true);
        } else {
            //stops the frame from moving upwards when the gap is met.
            isRising = false;
        }
        
        initTimer(isRising);
    }
    
    /**
     * Starts a timer which will either close or animate the frame.
     * @param rise 
     */
    private void initTimer(boolean rise) {
        timer = new Timer(rise ? 50 : duration, new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //if the frame is still rising, move it up by one.
                if (rise) {
                    exec();
                } else {
                    //else, close the frame after {@code duration} ms.
                    close();
                }
            }
        }); 
        timer.start();
    }
    
    class ShadowPanel extends JPanel {

        /** Creates a new ShadowPanel. */
        public ShadowPanel() {
            super.add(panel);
            panel.setLocation(shadowSize, shadowSize);

            super.setBorder(BorderFactory.createCompoundBorder(this.getBorder(), BorderFactory.createEmptyBorder(
                    shadowSize, shadowSize, shadowSize, shadowSize)));
            super.setLayout(new BorderLayout());
        }

        @Override
        protected void paintComponent(Graphics g) {
            //paints the shadow.
            int shade = 0;
            int topOpacity = 80;
            for (int i = 0; i < shadowSize; i++) {
                //loops through the border of the frame and paints a shadow.
                g.setColor(new Color(shade, shade, shade, ((topOpacity / shadowSize) * i)));
                g.drawRect(i, i, this.getWidth() - ((i * 2) + 1), this.getHeight() - ((i * 2) + 1));
            }
        }
        
    }
    
}