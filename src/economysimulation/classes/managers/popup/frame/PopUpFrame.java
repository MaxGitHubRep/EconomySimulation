package economysimulation.classes.managers.popup.frame;

import static economysimulation.classes.global.Methods.ThemeManager;
import economysimulation.classes.managers.theme.GraphicUpdater;
import economysimulation.classes.managers.theme.ThemeUpdateEvent;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 *
 * @author Max Carter
 */
public class PopUpFrame extends JFrame implements ThemeUpdateEvent {

    private static JPanel panel, back;
    private String title;
    
    public PopUpFrame(JPanel panel, String title) {
        if (panel == null || title == null) {
            throw new NullPointerException();
        }
        
        setIconImage(new ImageIcon(getClass().getResource("/economysimulation/resources/icon/icon128.png")).getImage());
        
        this.panel = panel;
        this.title = title;
        
        back = new JPanel();
        this.add(back);
        ThemeManager.addThemeUpdateListener(this);
    }

    public void createPopUpFrame() {
        back.add(panel);
        this.setResizable(false);
        this.setVisible(true);
        this.setSize(panel.getWidth(), panel.getHeight());
        this.setTitle("Economy Simulation: " + title);
        this.pack();
    }

    @Override
    public void updateThemeEvent(GraphicUpdater updater) {
        updater.applyPanelThemes(new JPanel[]{ back }, null);
    }
    
}