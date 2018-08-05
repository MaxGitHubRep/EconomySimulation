package economysimulation.classes.managers.themes;

import economysimulation.classes.gui.fronter.GameHold;
import economysimulation.classes.gui.fronter.SideBar;
import economysimulation.classes.gui.subpanels.BudgetList;
import economysimulation.classes.gui.subpanels.RateList;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 *
 * @author Max Carter
 */
public class Theme {
    
    public static Color primaryBack, secondaryBack, primaryText, secondaryText, ghostText, primaryHover, secondaryHover;
    
    public static void applySelectedTheme(Color[] theme) {
        primaryBack = theme[0];
        secondaryBack = theme[1];
        primaryText = theme[2];
        secondaryText = theme[3];
        ghostText = theme[4];
        primaryHover = theme[5];
        secondaryHover = theme[6];
        
    }
    
    public static void updateAllPanelThemes() {
        GameHold.updateTheme();
        SideBar.updateTheme();
        BudgetList.updateTheme();
        RateList.updateTheme();
        for (int i = 0; i < SideBar.framed.length; i++) {
            if (SideBar.framed[i]) SideBar.frames[i].updateTheme();
        }
    }
    
    public static void applyTextThemes(JLabel[] primaryTextLabels, JLabel[] secondaryTextLabels) {
        if (primaryTextLabels != null && secondaryTextLabels != null) {
            for (JLabel text : primaryTextLabels) {
                text.setForeground(primaryText);
            }
            for (JLabel text : secondaryTextLabels) {
                text.setForeground(secondaryText);
            }
            
        } else {
            throw new NullPointerException();
        }
    }
    
    public static void applyPanelThemes(JPanel[] primaryBackPanels, JPanel[] secondaryBackPanels, JPanel[] primaryHoverPanels, JPanel[] secondaryHoverPanels) {
        if (primaryBackPanels != null && secondaryBackPanels != null && primaryHoverPanels != null && secondaryHoverPanels != null) {
            for (JPanel panel : primaryBackPanels) {
                panel.setBackground(primaryBack);
            }
            for (JPanel panel : secondaryBackPanels) {
                panel.setBackground(secondaryBack);
            }
            for (JPanel panel : primaryHoverPanels) {
                panel.setBackground(primaryBack);
            }
            for (JPanel panel : secondaryHoverPanels) {
                panel.setBackground(primaryBack);
            }
        } else {
            throw new NullPointerException();
        }
    }
     
}
