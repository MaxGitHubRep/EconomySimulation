package economysimulation.classes.managers.theme;

import static economysimulation.classes.global.Methods.ThemeManager;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 *
 * @author Max Carter
 */
public class GraphicUpdater {

    public void applyTextThemes(JLabel[] primaryTextLabels, JLabel[] secondaryTextLabels) {
        if (primaryTextLabels != null) {
            for (JLabel text : primaryTextLabels) {
                text.setForeground(ThemeManager.Theme.getPrimaryTextColor());
            }
        }
        
        if (secondaryTextLabels != null) {
            for (JLabel text : secondaryTextLabels) {
                text.setForeground(ThemeManager.Theme.getSecondaryTextColor());
            }
        }
    }
    
    public void applyPanelThemes(JPanel[] primaryBackPanels, JPanel[] secondaryBackPanels, JPanel[] primaryHoverPanels, JPanel[] secondaryHoverPanels) {
        if (primaryBackPanels != null) {
            for (JPanel panel : primaryBackPanels) {
                panel.setBackground(ThemeManager.Theme.getPrimaryColor());
            }
        }
         
        if (secondaryBackPanels != null) {
            for (JPanel panel : secondaryBackPanels) {
                panel.setBackground(ThemeManager.Theme.getSecondaryColor());
            }
        }
        
        if (primaryHoverPanels != null) {
            for (JPanel panel : primaryHoverPanels) {
                panel.setBackground(ThemeManager.Theme.getPrimaryColor());
            }
        }
        
        if (secondaryHoverPanels != null) {
            for (JPanel panel : secondaryHoverPanels) {
                panel.setBackground(ThemeManager.Theme.getPrimaryColor());
            }
        }
    }
     
}
