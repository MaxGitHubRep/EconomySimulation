package economysimulation.classes.global;

import economysimulation.classes.economy.sectors.Sector;
import economysimulation.classes.economy.structure.Formula;
import economysimulation.classes.gui.frame.MainFrame;
import economysimulation.classes.gui.fronter.GameHold;
import economysimulation.classes.gui.mainpanels.extra.Leaderboard;
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
import java.util.ArrayList;
import java.util.Random;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.StandardChartTheme;
import org.jfree.data.category.DefaultCategoryDataset;

/**
 *
 * @author Max Carter
 */
public class Methods {

    //Instances of objects.
    public static BudgetList BudgetDisplay;
    public static RateList RateDisplay;
    public static GameHold GameDisplay;
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
    
    public static String Username = "", UserID = "";
    
    public static boolean SimulationInProgress = false;
    
    //<editor-fold defaultstate="collapsed" desc="Reset local user data in current session."> 
    /**
     * Resets the local user data in the current session.
     */
    public static void resetCurrentUserData() {
        UserID = "";
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
    
    //<editor-fold defaultstate="collapsed" desc="Creates a graph with given data."> 
    public static void createGraph(String title, ArrayList<Double> historyList, JPanel panel) {
        DefaultCategoryDataset dataSet = new DefaultCategoryDataset();

        int size = historyList.size();
        
        for (int i = 0; i < size; i++) {
            dataSet.addValue(historyList.get(i), title + " (£bn)", (i+1) + "");
        }

        JFreeChart chart = ChartFactory.createBarChart(title, "Quarters", title + " (£bn)", dataSet);

        Methods.applyChartTheme(chart, true);
        
        addChartToPanel(chart, panel);
        
    }//</editor-fold> 
    
    //<editor-fold defaultstate="collapsed" desc="Adds a chart to a panel."> 
    public static void addChartToPanel(JFreeChart chart, JPanel panel) {
        panel.setLayout(new BorderLayout());
        ChartPanel CP = new ChartPanel(chart);
        panel.add(CP, BorderLayout.CENTER);
        panel.validate();
    }//</editor-fold> 
    
    //<editor-fold defaultstate="collapsed" desc="Generates random number."> 
    public static int randomInt(int min, int max) {
        return new Random().nextInt((max-min)+1)+min;
    }//</editor-fold> 

}
