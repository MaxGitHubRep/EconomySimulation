package economysimulation.classes.managers.popup.frame;

import economysimulation.classes.gui.frame.MainFrame;
import economysimulation.classes.managers.theme.GraphicUpdater;
import economysimulation.classes.managers.theme.ThemeUpdateEvent;
import java.awt.Frame;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import static economysimulation.classes.global.Methods.ThemeHandler;

/**
 * @author Max Carter
 */
public class PopUpFrame extends JFrame implements ThemeUpdateEvent {

    //Panels to add to the frame.
    private static JPanel panel, back;
    
    /** Title of the frame. */
    private String title;
    
    /**
     * Create a new PopUpFrame.
     * @param panel Panel to create a frame around.
     * @param title Title of the frame.
     */
    public PopUpFrame(JPanel panel, String title) {
        //checks that the panel and title are valid.
        if (panel == null || title == null) {
            throw new NullPointerException();
        }
        
        //formats the frame.
        setIconImage(new ImageIcon(getClass().getResource("/economysimulation/resources/icon/icon128.png")).getImage());
        
        this.panel = panel;
        this.title = title;
        
        back = new JPanel();
        this.add(back);
        ThemeHandler.addThemeUpdateListener(this);
    }

    /** Inflates the frame. */
    public void createPopUpFrame() {
        back.add(panel);
        this.setResizable(false);
        this.setVisible(true);
        this.setSize(panel.getWidth(), panel.getHeight());
        this.setTitle("Economy Simulation: " + title);
        this.pack();
    }
    
    /**
     * Checks if the frame is open.
     * @param title Title of the frame.
     * @return Returns true if a frame is open.
     */
    public boolean isOpen(String title) {
        for (Frame frame : MainFrame.getFrames()) {
            //return true if a frame is found.
            if (frame.getTitle().equals("Economy Simulation: " + title)) return true;
        }
        return false;
    }

    @Override
    public void onThemeUpdate(GraphicUpdater updater) {
        updater.applyPanelThemes(new JPanel[]{ back }, null);
    }
    
}