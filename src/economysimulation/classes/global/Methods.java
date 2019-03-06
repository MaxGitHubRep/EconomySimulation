package economysimulation.classes.global;

import economysimulation.classes.economy.sectors.SectorManager;
import economysimulation.classes.economy.structure.Formula;
import economysimulation.classes.gui.coop.TeammateFinder;
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
import economysimulation.classes.gui.startup.Tutorial;
import economysimulation.classes.gui.startup.WelcomePanel;
import economysimulation.classes.gui.subpanels.BudgetList;
import economysimulation.classes.gui.subpanels.RateList;
import economysimulation.classes.gui.subpanels.TaxRevenueList;
import economysimulation.classes.managers.animation.StockGraph;
import economysimulation.classes.managers.extcon.Connection;
import economysimulation.classes.managers.extcon.DatabaseConnector;
import economysimulation.classes.managers.extcon.GameData;
import economysimulation.classes.managers.extcon.UserData;
import economysimulation.classes.managers.extcon.lobby.LobbyConnector;
import economysimulation.classes.managers.extcon.multiplayer.StorageConnector;
import economysimulation.classes.managers.extcon.multiplayer.StorageReceiver;
import economysimulation.classes.managers.theme.GraphicUpdater;
import economysimulation.classes.managers.theme.ThemeManager;
import economysimulation.classes.mode.ModeManager;
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
 * @author Max Carter
 */
public class Methods {

    //Instances of objects.
    public static WelcomePanel IntroPanel;
    public static BudgetList BudgetDisplay;
    public static RateList RateDisplay;
    public static GameHold GameDisplay;
    public static SideBar SideBarDisplay;
    public static MainFrame FrameDisplay;
    public static Formula FormulaInstance;
    public static TaxRevenueList TaxRevenueDisplay;
    public static SectorManager SectorInstance;
    public static ThemeManager ThemeHandler;
    public static GraphicUpdater Updater;
    public static Consumer ConsumerDisplay;
    public static Corporation CorporationDisplay;
    public static StockGraph AnimationGraph;
    public static PulseThread PulseUpdater;
    public static DatabaseConnector DBConnector;
    public static UserData DBUsers;
    public static GameData DBGames;
    public static Leaderboard LBDisplay = null;
    public static Overview OverivewDisplay;
    public static Settings SettingsDisplay;
    public static RateHold RatesBack;
    public static BudgetHold BudgetBack;
    public static Tutorial TutorialDisplay;
    public static ModeManager ModeHandler;
    public static StorageReceiver StorageEvent;
    public static StorageConnector StorageConnection;
    public static LobbyConnector LobbyHandler;
    public static TeammateFinder FindTeammate = null;
    
    /** Local party ID. */
    public static int localPartyId = 0;
    
    /** Local user. */
    private static User user = new User();
    
    /** Whether memory saver is enabled or not. */
    public static volatile boolean MemorySaver = false;
    
    /** Whether or not the simulation is in progress. */
    public static boolean SimulationInProgress = false;
    
    /**
     * Gets the local user instance.
     * @return The local user.
     */
    public static User getUser() {
        //if the local user is null, an empty user is returned.
        if (user == null) return new User();
        return user;
    }
    
    /** Quits the system. */
    public static void quitSystem() {
        //removes any redundant users from the database.
        if (user != null) removeRedundantUser(user);
        System.exit(0);
    }
    
    /**
     * Removes a redundant user from the database.
     * @param user Possible redundant user.
     */
    public static void removeRedundantUser(User user) {
        if (Connection.isConnected && user.getID() != -1) {
            if (DBUsers.isRedundantUser(user)) {
                DBUsers.removeUser(user);
            }
            LobbyHandler.removeUserFromLobby(user.getID());
        }
    }
    
    /** Resets the local user data in the current session. */
    public static void resetCurrentUserData() {
        user.reset();
    }
    
    /**
     * Allows multiple panels to drag the main frame.
     * @param panels Panels that can drag the main frame.
     */
    public static void addDraggablePanel(JPanel[] panels) {
        for (JPanel dragPanel : panels) {
            FrameDisplay.frameDragged(dragPanel);
        }
    }
    
    /**
     * Allows a panel to drag the main frame.
     * @param panel Panel that can drag the main frame.
     */
    public static void addDraggablePanel(JPanel panel) {
        FrameDisplay.frameDragged(panel);
    }
    
    /**
     * Adds an external panel to an existing panel.
     * @param backPanel   The existing panel.
     * @param panel       The panel which will be placed on top.
     * @param scrollable  Whether or not the top panel is scrollable.
     */
    public static void addToFrontPanel(JPanel backPanel, JPanel panel, boolean scrollable) {
        //creates back panel layout
        backPanel.removeAll();
        backPanel.revalidate();
        backPanel.setLayout(new BorderLayout());
        
        //creates scrollpane if option enabled.
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
    }
    
    /**
     * Creates a chart theme object and applies it to the {@code chart}.
     * @param chart Chart to add the theme too.
     */
    public static void applyChartTheme(JFreeChart chart) {
        StandardChartTheme theme = (StandardChartTheme) org.jfree.chart.StandardChartTheme.createJFreeTheme();
        
        //handles the text.
        theme.setTitlePaint(ThemeHandler.getTheme().getPrimaryColor());
        theme.setExtraLargeFont(new Font("Agency FB",Font.PLAIN, 32));
        theme.setLargeFont(new Font("Agency FB",Font.PLAIN, 25));
        theme.setRegularFont(new Font("Agency FB",Font.PLAIN, 20));

        //handles chart colours.
        theme.setAxisLabelPaint(ThemeHandler.getTheme().getPrimaryTextColor());
        theme.setChartBackgroundPaint(ThemeHandler.getTheme().getPrimaryColor());
        theme.setPlotBackgroundPaint(ThemeHandler.getTheme().getPrimaryHoverColor());
        theme.setRangeGridlinePaint(ThemeHandler.getTheme().getSecondaryColor());
        theme.setShadowVisible(true);
        
        //applies chart.
        theme.apply(chart);
    }
    
    /**
     * Adds a chart to a panel.
     * @param chart The chart to add on top.
     * @param panel The background panel.
     */
    public static void addChartToPanel(JFreeChart chart, JPanel panel) {
        //creates new layout for the panel.
        panel.setLayout(new BorderLayout());
        panel.removeAll();
        //adds chart to panel.
        panel.add(new ChartPanel(chart), BorderLayout.CENTER);
        panel.validate();
    }
    
    /**
     * Generates a random number.
     * @param min Minimum possible number.
     * @param max Maximum possible number.
     * @return The random number.
     */
    public static int randomInt(int min, int max) {
        return new Random().nextInt((max-min)+1)+min;
    }

}
