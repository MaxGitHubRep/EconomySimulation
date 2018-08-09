package economysimulation.classes.managers.popup.frame;

import economysimulation.classes.managers.themes.Theme;
import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 *
 * @author Max Carter
 */
public class PopUpFrame extends JFrame {

    private static JPanel panel, back;
    private String title;
    
    public PopUpFrame(JPanel panel, String title) {
        if (panel == null || title == null) {
            throw new NullPointerException();
        }
        
        this.panel = panel;
        this.title = title;
        
        back = new JPanel();
        this.add(back);
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
    
}