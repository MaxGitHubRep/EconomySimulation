package economysimulation.classes.managers.themes;

import economysimulation.classes.gui.fronter.GameHold;
import economysimulation.classes.gui.fronter.SideBar;
import economysimulation.classes.gui.subpanels.BudgetList;
import economysimulation.classes.gui.subpanels.RateList;
import economysimulation.classes.gui.subpanels.TaxRevenueList;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 *
 * @author Max Carter
 */
public class Theme {
    
    public static Color primaryBack, secondaryBack, primaryText, secondaryText, ghostText, primaryHover, secondaryHover;
    
    private static final ThemeUpdater
            GameHoldTheme = new GameHold.GameHoldTheme(),
            SideBarTheme = new SideBar.SideBarTheme(),
            BudgetListTheme = new BudgetList.BudgetListTheme(),
            RateListTheme = new RateList.RateListTheme(),
            TaxRevenueListTheme = new TaxRevenueList.TaxRevenueListTheme();
    
    private static final ThemeUpdater[] ThemeUpdaterClasses = new ThemeUpdater[]{
        GameHoldTheme,
        SideBarTheme,
        BudgetListTheme,
        RateListTheme,
        TaxRevenueListTheme
    };
    
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
        
        for (ThemeUpdater ThemeClass : ThemeUpdaterClasses) {
            ThemeClass.updateClassTheme();
        }
        
        for (int i = 0; i < SideBar.framed.length; i++) {
            if (SideBar.framed[i]) SideBar.frames[i].updateTheme();
        }
    }
    
    public static void applyTextThemes(JLabel[] primaryTextLabels, JLabel[] secondaryTextLabels) {
        if (primaryTextLabels != null) {
            for (JLabel text : primaryTextLabels) {
                text.setForeground(primaryText);
            }
        }
        
        if (secondaryTextLabels != null) {
            for (JLabel text : secondaryTextLabels) {
                text.setForeground(secondaryText);
            }
        }
    }
    
    public static void applyPanelThemes(JPanel[] primaryBackPanels, JPanel[] secondaryBackPanels, JPanel[] primaryHoverPanels, JPanel[] secondaryHoverPanels) {
        if (primaryBackPanels != null) {
            for (JPanel panel : primaryBackPanels) {
                panel.setBackground(primaryBack);
            }
        }
         
        if (secondaryBackPanels != null) {
            for (JPanel panel : secondaryBackPanels) {
                panel.setBackground(secondaryBack);
            }
        }
        
        if (primaryHoverPanels != null) {
            for (JPanel panel : primaryHoverPanels) {
                panel.setBackground(primaryBack);
            }
        }
        
        if (secondaryHoverPanels != null) {
            for (JPanel panel : secondaryHoverPanels) {
                panel.setBackground(primaryBack);
            }
        }
    }
     
}
