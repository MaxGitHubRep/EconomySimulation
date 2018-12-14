package economysimulation.classes.managers.theme;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import static economysimulation.classes.global.Methods.ThemeHandler;

/**
 *
 * @author Max Carter
 */
public class GraphicUpdater {

    /**
     * Changes the theme colour of the labels.
     * 
     * @param primaryTextLabels   List of primary labels.
     * @param secondaryTextLabels List of secondary labels.
     */
    public void applyTextThemes(JLabel[] primaryTextLabels, JLabel[] secondaryTextLabels) {
        if (primaryTextLabels != null) {
            for (JLabel text : primaryTextLabels) {
                text.setForeground(ThemeHandler.getTheme().getPrimaryTextColor());
            }
        }
        
        if (secondaryTextLabels != null) {
            for (JLabel text : secondaryTextLabels) {
                text.setForeground(ThemeHandler.getTheme().getSecondaryTextColor());
            }
        }
    }
    
    /**
     * Changes the theme colour of the panels.
     * 
     * @param primaryBackPanels   List of primary panels.
     * @param secondaryBackPanels List of secondary panels.
     */
    public void applyPanelThemes(JPanel[] primaryBackPanels, JPanel[] secondaryBackPanels) {
        if (primaryBackPanels != null) {
            for (JPanel panel : primaryBackPanels) {
                panel.setBackground(ThemeHandler.getTheme().getPrimaryColor());
            }
        }
         
        if (secondaryBackPanels != null) {
            for (JPanel panel : secondaryBackPanels) {
                panel.setBackground(ThemeHandler.getTheme().getSecondaryColor());
            }
        }
    }
    
    /**
     * Changes the theme colour of the buttons.
     * 
     * @param primaryButtons   List of primary buttons.
     * @param secondaryButtons List of secondary buttons.
     */
    public void applyRadioButtonThemes(JRadioButton[] primaryButtons, JRadioButton[] secondaryButtons) {
        if (primaryButtons != null) {
            for (JRadioButton button : primaryButtons) {
                button.setBackground(ThemeHandler.getTheme().getPrimaryColor());
                button.setForeground(ThemeHandler.getTheme().getPrimaryTextColor());
            }
        }
        
        if (secondaryButtons != null) {
            for (JRadioButton button : secondaryButtons) {
                button.setBackground(ThemeHandler.getTheme().getSecondaryColor());
                button.setForeground(ThemeHandler.getTheme().getSecondaryTextColor());
            }
        }
        
    }
     
}
