package economysimulation.classes.global;

import economysimulation.classes.economy.sectors.Sector;
import economysimulation.classes.economy.structure.Formula;
import economysimulation.classes.gui.frame.MainFrame;
import economysimulation.classes.gui.fronter.GameHold;
import economysimulation.classes.gui.fronter.SideBar;
import economysimulation.classes.gui.mainpanels.extra.Overview;
import economysimulation.classes.gui.mainpanels.extra.leaderboard.Leaderboard;
import economysimulation.classes.gui.mainpanels.extra.settings.Settings;
import economysimulation.classes.gui.mainpanels.hold.BudgetHold;
import economysimulation.classes.gui.mainpanels.hold.RateHold;
import economysimulation.classes.gui.mainpanels.sim.Consumer;
import economysimulation.classes.gui.mainpanels.sim.Corporation;
import economysimulation.classes.gui.subpanels.BudgetList;
import economysimulation.classes.gui.subpanels.RateList;
import economysimulation.classes.gui.subpanels.TaxRevenueList;
import economysimulation.classes.managers.animation.StockGraph;
import economysimulation.classes.managers.extcon.DatabaseConnector;
import economysimulation.classes.managers.extcon.UserData;
import economysimulation.classes.managers.theme.GraphicUpdater;
import economysimulation.classes.managers.theme.Theme;
import economysimulation.classes.pulse.PulseThread;
import java.awt.BorderLayout;
import java.awt.Font;
import java.util.Random;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.StandardChartTheme;

/**
 *
 * @author Max Carter
 */
public class Methods {

    //Instances of objects.
    public static BudgetList BudgetDisplay;
    public static RateList RateDisplay;
    public static GameHold GameDisplay;
    public static SideBar SideBarDisplay;
    public static MainFrame FrameDisplay;
    public static Formula FormulaInstance;
    public static TaxRevenueList TaxRevenueDisplay;
    public static Sector SectorInstance;
    public static Theme ThemeManager;
    public static GraphicUpdater Updater;
    public static Consumer ConsumerDisplay;
    public static Corporation CorporationDisplay;
    public static StockGraph AnimationGraph;
    public static PulseThread PulseUpdater;
    public static DatabaseConnector DBConnector;
    public static UserData DBUsers;
    public static Leaderboard LBDisplay;
    public static Overview OverivewDisplay;
    public static Settings SettingsDisplay;
    public static RateHold RatesBack;
    public static BudgetHold BudgetBack;
    
    
    public static String Username;
    public static int UserID = -1;
    
    public static boolean SimulationInProgress = false;
    
    //<editor-fold defaultstate="collapsed" desc="Reset local user data in current session."> 
    /**
     * Resets the local user data in the current session.
     */
    public static void resetCurrentUserData() {
        UserID = -1;
        Username = "";
    }//</editor-fold>
    
    //<editor-fold defaultstate="collapsed" desc="Allows multiple panels to drag the frame."> 
    /**
     * Allows multiple panels to drag the main frame.
     * @param panels Panels that can drag the main frame.
     */
    public static void addDraggablePanel(JPanel[] panels) {
        for (JPanel dragPanel : panels) {
            FrameDisplay.frameDragged(dragPanel);
        }
    }//</editor-fold>
    
    //<editor-fold defaultstate="collapsed" desc="Allows a single panel to drag the frame."> 
    /**
     * Allows a panel to drag the main frame.
     * @param panel Panel that can drag the main frame.
     */
    public static void addDraggablePanel(JPanel panel) {
        FrameDisplay.frameDragged(panel);
        
    }//</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="Returns the username with an extra 5 integers."> 
    public static String generateRandomUsername(String currentUsername) {
        return currentUsername + "#" + randomInt(0, 99999);
    }//</editor-fold>
    
    //<editor-fold defaultstate="collapsed" desc="Adds panel to another panel."> 
    public static void addToFrontPanel(JPanel backPanel, JPanel panel, boolean scrollable) {
        backPanel.removeAll();
        backPanel.revalidate();
        
        backPanel.setLayout(new BorderLayout());
        
        if (scrollable) {
            JScrollPane scrolling = new JScrollPane(panel,
                JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
                JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
            scrolling.getVerticalScrollBar().setUnitIncrement(16);
            
            backPanel.add(scrolling);
        } else {
            backPanel.add(panel);
        }

        backPanel.repaint();
    }//</editor-fold>
    
    //<editor-fold defaultstate="collapsed" desc="Applies specific theme to graph."> 
    public static void applyChartTheme(JFreeChart chart, boolean cataPlot) {
        StandardChartTheme theme = (StandardChartTheme)org.jfree.chart.StandardChartTheme.createJFreeTheme();
        
        theme.setTitlePaint(ThemeManager.Theme.getPrimaryColor());
        theme.setExtraLargeFont( new Font("Agency FB",Font.PLAIN, 32) );
        theme.setLargeFont( new Font("Agency FB",Font.PLAIN, 25));
        theme.setRegularFont( new Font("Agency FB",Font.PLAIN, 20));
        
        // ---- Editing default graph theme: Colours ----
        
        theme.setAxisLabelPaint(ThemeManager.Theme.getPrimaryTextColor());
        theme.setChartBackgroundPaint(ThemeManager.Theme.getPrimaryColor());
        theme.setPlotBackgroundPaint(ThemeManager.Theme.getPrimaryHoverColor());
        theme.setRangeGridlinePaint(ThemeManager.Theme.getSecondaryColor());
        theme.setShadowVisible(true);
        
        // ---- Editing default graph theme: Formatting ----
        
        if (cataPlot) {
            chart.getCategoryPlot().setOutlineVisible(false);
            chart.getCategoryPlot().getRangeAxis().setAxisLineVisible(false);
        }
        
        theme.apply(chart);
    }//</editor-fold>
    
    //<editor-fold defaultstate="collapsed" desc="Adds a chart to a panel."> 
    public static void addChartToPanel(JFreeChart chart, JPanel panel) {
        panel.setLayout(new BorderLayout());
        panel.removeAll();
        panel.add(new ChartPanel(chart), BorderLayout.CENTER);
        panel.validate();
    }//</editor-fold> 
    
    //<editor-fold defaultstate="collapsed" desc="Generates random number."> 
    public static int randomInt(int min, int max) {
        return new Random().nextInt((max-min)+1)+min;
    }//</editor-fold> 

}
