package economysimulation.classes;

import economysimulation.classes.algorithms.Component;
import static economysimulation.classes.algorithms.Component.historyGDP;
import economysimulation.classes.algorithms.Formula;
import economysimulation.classes.fronter.GameHold;
import economysimulation.classes.mainpanels.PGovernment;
import economysimulation.classes.subpanels.QBudget;
import economysimulation.classes.subpanels.QGovernment;
import economysimulation.classes.subpanels.QSideBar;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.util.ArrayList;
import java.util.Random;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
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

    //Classes of panels to add at intro
    public static QBudget budgetClass;
    public static QGovernment govClass;
    
    public static String username; //players username
    public static int TICKS; //how many gamer ticks / days have passed so far
    public static final int GRAPH_TICKS = 50; //Amount of days present on graph
    public static final String GRAPH_FONT_NAME = "Agency FB"; //graph font type
    public static int MAX_USERNAME_LENGTH = 8;
    
    public static final Color backPanelColor = new Color(102, 102, 102);
    public static final Color backLabelColor = new Color(102, 102, 102);
    public static final Color frontLabelColor = new Color(102, 102, 102);
    
    public static JLabel[] allLabels;
    
    public static final int DEFAULT_SPENDING = 50;
    public static boolean oneQuarterPassed = false;
    
    public static void updateRealGDPLabel() {
        Formula.calculateGDP();
        GameHold.labelGDP.setText("£" + Component.GDP + "bn");
        historyGDP.add(Component.GDP);
        createGraph("GDP", historyGDP, PGovernment.graphPanel);
    }
    
    public static void changeComponenetColour() {
        allLabels = new JLabel[]{ GameHold.labelGDP, GameHold.titleSpeed, GameHold.titleTime,
            QSideBar.titleOverview, QSideBar.titleBankr, QSideBar.titleBudget, QSideBar.titleCons, QSideBar.titleCons, QSideBar.titleCorp, QSideBar.titleGov };
        
        for (JLabel label : allLabels) {
            //label.setBackground(new Color(55,153,255));
            //label.setForeground(new Color(255,255,255));
            
        }
        //GameHold.backadd.setBackground(new Color(55, 153, 255));
    }
    
    //<editor-fold defaultstate="collapsed" desc="Returns the username with an extra 5 integers."> 
    public static String generateRandomUsername(String currentUsername) {
        return currentUsername + "#" + randomInt(0, 9999);
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
        
        // ---- Editing default graph theme: Fonts ----
        
        theme.setTitlePaint(new Color(204, 0, 0));
        theme.setExtraLargeFont( new Font(Methods.GRAPH_FONT_NAME,Font.BOLD, 32) );
        theme.setLargeFont( new Font(Methods.GRAPH_FONT_NAME,Font.BOLD, 25));
        theme.setRegularFont( new Font(Methods.GRAPH_FONT_NAME,Font.BOLD, 20));
        
        // ---- Editing default graph theme: Colours ----
        
        theme.setAxisLabelPaint(new Color(204, 0, 0));
        theme.setChartBackgroundPaint(Color.WHITE);
        theme.setPlotBackgroundPaint(Color.white);
        theme.setRangeGridlinePaint(new Color(0, 0, 0));
        
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
    
    //<editor-fold defaultstate="collapsed" desc="Adds light grey text with a prompt until user selects text box."> 
    public static void addGhostText(JTextField field, String ghostText) {
        field.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                field.setText(field.getText().replace(ghostText, ""));
            }

            @Override
            public void focusLost(FocusEvent e) {
                if ("".equals(field.getText())) field.setText(ghostText);
            }

        });
    }//</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="Reset game variables."> 
    public static void resetGame() {
        Component.INTEREST_RATE = 0;
        Component.CORP_TAX = 0;
        Component.CONS_TAX = 0;
        Component.ANNUAL_BUDGET = 0;
        
        for (int i = 0; i < Component.BUDGET_VARS.length; i++) {
            Component.BUDGET_VARS[i] = 0;
        }
        
        TICKS = 0;
    }//</editor-fold>
}
