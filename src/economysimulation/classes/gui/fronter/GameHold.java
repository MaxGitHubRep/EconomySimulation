package economysimulation.classes.gui.fronter;

import economysimulation.classes.global.Methods;
import economysimulation.classes.economy.structure.Component;
import static economysimulation.classes.global.Methods.FormulaInstance;
import static economysimulation.classes.global.Methods.ModeHandler;
import economysimulation.classes.managers.theme.GraphicUpdater;
import economysimulation.classes.managers.ui.Format;
import economysimulation.classes.managers.comp.CircleProgressBar;
import economysimulation.classes.managers.events.EventManager;
import economysimulation.classes.managers.theme.ThemeUpdateEvent;
import economysimulation.classes.mode.Mode;
import economysimulation.classes.pulse.GamePulse;
import java.text.DecimalFormat;
import java.util.ArrayList;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import static economysimulation.classes.global.Methods.ThemeHandler;

/**
 *
 * @author Max Carter
 */
public class GameHold extends javax.swing.JPanel implements GamePulse, ThemeUpdateEvent {

    public ArrayList<Double> HistoryGDP = new ArrayList<>();
    
    public boolean[] TaxBreak = new boolean[]{ false, false };
    
    private CircleProgressBar ProgressBar;
    
    public static double[]
            Percents = new double[3];
    
    private Thread CBPThread;
    
    private final DecimalFormat
            m = new DecimalFormat("0"),
            f = new DecimalFormat("#00"),
            fYear = new DecimalFormat("#0000");
    
    private final int[]
            MONTH_SIZES = new int[]{ 31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31 },
            TimeTrack = new int[]{ 0, 1, 1 };
    
    public final int
            SPEED_MID_POINT = 100;
    public int
            TicksPerQuarter = 0,
            Speed,
            Ticks = 0;

    public final String[] TITLES = new String[]{
        "Standard of Living",
        "Political Influence",
        "Consumption Rate"
    };

    //<editor-fold defaultstate="collapsed" desc="Constructor."> 
    /**'
     * Creates new game set up.
     */
    public GameHold() {
        initComponents();
        
        //Checks to see if the mode allows time changes.
        if (ModeHandler.isMode(Mode.MULTI_PLAYER)) {
            time.setValue(time.getMinimum());
            time.setEnabled(false);
            time.setToolTipText("You cannot alter the time in multiplayer.");
        } else {
            time.setValue(time.getMaximum()/2);
            time.setEnabled(true);
            time.setToolTipText("Drag the slider to change the speed.");
        }
        onSliderChange(time);
        
        Methods.SideBarDisplay = new SideBar();
        Methods.addToFrontPanel(sideBarBack, Methods.SideBarDisplay, false);
        Methods.SideBarDisplay.addPanelButtons();

        addSliderListener(time);
        updateSpeed();
        updateTime();
        
        Format.addButtonFormat(panel1, color1);
        Format.addButtonFormat(panel2, color2);

        for (int i = 0; i < Percents.length; i++) {
            Percents[i] = 1;
        }
        
        addCircleProgressBar(circleAdd, ProgressBar);
        ThemeHandler.addThemeUpdateListener(this);
        Methods.addDraggablePanel(new JPanel[]{ leftBar, rightBar, topBar });
    }//</editor-fold>
    
    /**
     * Adds the circle progress bar to the right side bar.
     * 
     * @param back The panel which the circle will be added to.
     * @param bar  The variable which a new instance will be created in.
     */
    private void addCircleProgressBar(JPanel back, CircleProgressBar bar) {
        back.removeAll();
        bar = new CircleProgressBar();
        bar.setSize(255, 977);
        back.add(bar);
    }
    
    @Override
    public void onGamePulseEvent() {
        Ticks++;
        updateRealGDPLabel();
        updateTime();
        updateSpeed();
        
        int index = 0;
        for (double percent : new double[]{ Component.StandardOfLiving, Component.PoliticalInflluence, Component.PropensityToConsume }) {
            if (percent != Percents[index]) updateProgressBar(percent, index);
            index++;
        }
        
        FormulaInstance.calculateBudget();
        
        labelBudget.setText("£" + m.format(Component.SpendingBudget) + "bn");
        if (Ticks % 110 == 0) EventManager.createEvent();
    }
    
    /**
     * Updates progress bars with animation.
     * 
     * @param newPercent The new percent of the progress bar.
     * @param id         Index of the percent array.
     */
    private synchronized void updateProgressBar(double newPercent, int id) {
        if (Methods.MemorySaver) {
            Percents[id] = newPercent;
            repaint();
            return;
        }
        
        boolean increase = newPercent > Percents[id];
        CBPThread = new Thread(new Runnable() {
            @Override
            public void run() {
                for (double i = Percents[id]; increase ? i < newPercent : i > newPercent; i = i + (increase ? 0.01 : - 0.01)) {
                    Percents[id] = i;
                    repaint();
                    try {
                        int div = (int)Math.abs((Percents[id]*100) - (newPercent*100));
                        if (div > 0) Thread.sleep(Speed/div);
                    } catch (InterruptedException ex) {
                        ex.printStackTrace();
                    }
                    Percents[id] = newPercent;
                }
            }
        });
        CBPThread.start();
        System.gc();
    }

    //<editor-fold defaultstate="collapsed" desc="Updates GDP label and quarterly components."> 
    private void updateRealGDPLabel() {
        FormulaInstance.calculateGDP();
        labelGDP.setText("£" + m.format(Component.GrossDomesticProduct) + "m");
    }//</editor-fold>
 
    //<editor-fold defaultstate="collapsed" desc="Calculate timer speed."> 
    private void updateSpeed() {
        int speed = time.getValue();
        
        if (speed == SPEED_MID_POINT) {
            Speed = 1000;
        } else if (speed > SPEED_MID_POINT) {
            Speed = 500 + ((200 - speed) * 5);
        } else if (speed < SPEED_MID_POINT) {
            Speed = 1000 + ((100 - speed) * 10);
        }
    }//</editor-fold>
    
    //<editor-fold defaultstate="collapsed" desc="Calculate display time."> 
    private void updateTime() {
        TimeTrack[0]++;

        if (Ticks % 7 == 0) HistoryGDP.add(Component.GrossDomesticProduct);
        if (Ticks > 8) Methods.OverivewDisplay.displayGDPGraph();
        
        if (TimeTrack[0] == MONTH_SIZES[TimeTrack[1]-1]+1) {
            TimeTrack[0] = 1;
            TimeTrack[1]++;
            Component.TotalSavings *=  1 + (Component.InterestRate/100);
            if (TimeTrack[1] == 12) {
                TimeTrack[1] = 1;
                TimeTrack[2]++;
            }
        }

        try {
            titleTime.setText(f.format(TimeTrack[0]) + "/" + f.format(TimeTrack[1]) + "/" + fYear.format(TimeTrack[2]));
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        
    }//</editor-fold>
    
    private void onSliderChange(JSlider slider) {
        titleSpeed.setText(String.format("Speed: %s", slider.getValue()) + "%");
    }
    
    //<editor-fold defaultstate="collapsed" desc="Slider Event.">   
    private void addSliderListener(JSlider slider) { 
        slider.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                onSliderChange(slider);
            }
        });
        
    }//</editor-fold> 

    @Override
    public void onThemeUpdate(GraphicUpdater updater) {
        updater.applyPanelThemes(new JPanel[]{ backadd, leftBar, panel1, panel2, color1, color2 }, new JPanel[]{ topBar, rightBar });
        updater.applyTextThemes(new JLabel[]{ titleSpeed, titleTime }, new JLabel[]{ title, description, label1, label2, labelGDP, labelBudget });
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        backadd = new javax.swing.JPanel();
        leftBar = new javax.swing.JPanel();
        picHold = new javax.swing.JLabel();
        time = new javax.swing.JSlider();
        sideBarBack = new javax.swing.JPanel();
        jSeparator1 = new javax.swing.JSeparator();
        panel1 = new javax.swing.JPanel();
        titleTime = new javax.swing.JLabel();
        color1 = new javax.swing.JPanel();
        panel2 = new javax.swing.JPanel();
        titleSpeed = new javax.swing.JLabel();
        color2 = new javax.swing.JPanel();
        onlineStatePanel = new javax.swing.JPanel();
        rightBar = new javax.swing.JPanel();
        circleAdd = new javax.swing.JPanel();
        topBar = new javax.swing.JPanel();
        title = new javax.swing.JLabel();
        description = new javax.swing.JLabel();
        jSeparator2 = new javax.swing.JSeparator();
        labelGDP = new javax.swing.JLabel();
        labelBudget = new javax.swing.JLabel();
        jSeparator3 = new javax.swing.JSeparator();
        label1 = new javax.swing.JLabel();
        label2 = new javax.swing.JLabel();

        setBackground(new java.awt.Color(102, 102, 102));

        backadd.setBackground(new java.awt.Color(255, 255, 255));
        backadd.setPreferredSize(new java.awt.Dimension(1100, 800));

        javax.swing.GroupLayout backaddLayout = new javax.swing.GroupLayout(backadd);
        backadd.setLayout(backaddLayout);
        backaddLayout.setHorizontalGroup(
            backaddLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1100, Short.MAX_VALUE)
        );
        backaddLayout.setVerticalGroup(
            backaddLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 850, Short.MAX_VALUE)
        );

        leftBar.setBackground(new java.awt.Color(255, 255, 255));
        leftBar.setPreferredSize(new java.awt.Dimension(400, 100));

        picHold.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        picHold.setIcon(new javax.swing.ImageIcon(getClass().getResource("/economysimulation/resources/logos/border280.png"))); // NOI18N

        time.setMaximum(200);
        time.setMinimum(1);
        time.setValue(100);
        time.setFocusable(false);
        time.setOpaque(false);

        sideBarBack.setOpaque(false);
        sideBarBack.setPreferredSize(new java.awt.Dimension(400, 453));

        javax.swing.GroupLayout sideBarBackLayout = new javax.swing.GroupLayout(sideBarBack);
        sideBarBack.setLayout(sideBarBackLayout);
        sideBarBackLayout.setHorizontalGroup(
            sideBarBackLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        sideBarBackLayout.setVerticalGroup(
            sideBarBackLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 520, Short.MAX_VALUE)
        );

        panel1.setBackground(new java.awt.Color(255, 255, 255));

        titleTime.setBackground(new java.awt.Color(102, 102, 102));
        titleTime.setFont(new java.awt.Font("Agency FB", 0, 36)); // NOI18N
        titleTime.setForeground(new java.awt.Color(204, 0, 0));
        titleTime.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        titleTime.setText("Day/Month/Year");

        color1.setBackground(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout color1Layout = new javax.swing.GroupLayout(color1);
        color1.setLayout(color1Layout);
        color1Layout.setHorizontalGroup(
            color1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 30, Short.MAX_VALUE)
        );
        color1Layout.setVerticalGroup(
            color1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout panel1Layout = new javax.swing.GroupLayout(panel1);
        panel1.setLayout(panel1Layout);
        panel1Layout.setHorizontalGroup(
            panel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panel1Layout.createSequentialGroup()
                .addComponent(color1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(41, 41, 41)
                .addComponent(titleTime, javax.swing.GroupLayout.PREFERRED_SIZE, 230, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(73, 73, 73))
        );
        panel1Layout.setVerticalGroup(
            panel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, panel1Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(titleTime, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addComponent(color1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        panel2.setBackground(new java.awt.Color(255, 255, 255));

        titleSpeed.setBackground(new java.awt.Color(102, 102, 102));
        titleSpeed.setFont(new java.awt.Font("Agency FB", 0, 36)); // NOI18N
        titleSpeed.setForeground(new java.awt.Color(204, 0, 0));
        titleSpeed.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        titleSpeed.setText("Speed: 100%");

        color2.setBackground(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout color2Layout = new javax.swing.GroupLayout(color2);
        color2.setLayout(color2Layout);
        color2Layout.setHorizontalGroup(
            color2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 30, Short.MAX_VALUE)
        );
        color2Layout.setVerticalGroup(
            color2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout panel2Layout = new javax.swing.GroupLayout(panel2);
        panel2.setLayout(panel2Layout);
        panel2Layout.setHorizontalGroup(
            panel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panel2Layout.createSequentialGroup()
                .addComponent(color2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(20, 20, 20)
                .addComponent(titleSpeed, javax.swing.GroupLayout.PREFERRED_SIZE, 264, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        panel2Layout.setVerticalGroup(
            panel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panel2Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(titleSpeed, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addComponent(color2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout onlineStatePanelLayout = new javax.swing.GroupLayout(onlineStatePanel);
        onlineStatePanel.setLayout(onlineStatePanelLayout);
        onlineStatePanelLayout.setHorizontalGroup(
            onlineStatePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        onlineStatePanelLayout.setVerticalGroup(
            onlineStatePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout leftBarLayout = new javax.swing.GroupLayout(leftBar);
        leftBar.setLayout(leftBarLayout);
        leftBarLayout.setHorizontalGroup(
            leftBarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(picHold, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(sideBarBack, javax.swing.GroupLayout.DEFAULT_SIZE, 410, Short.MAX_VALUE)
            .addGroup(leftBarLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(leftBarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(onlineStatePanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(time, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jSeparator1, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(panel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(panel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        leftBarLayout.setVerticalGroup(
            leftBarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(leftBarLayout.createSequentialGroup()
                .addComponent(picHold, javax.swing.GroupLayout.PREFERRED_SIZE, 136, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(3, 3, 3)
                .addComponent(panel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(time, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(panel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(10, 10, 10)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(sideBarBack, javax.swing.GroupLayout.PREFERRED_SIZE, 520, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(onlineStatePanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        rightBar.setBackground(new java.awt.Color(102, 102, 102));
        rightBar.setPreferredSize(new java.awt.Dimension(300, 451));

        circleAdd.setOpaque(false);

        javax.swing.GroupLayout circleAddLayout = new javax.swing.GroupLayout(circleAdd);
        circleAdd.setLayout(circleAddLayout);
        circleAddLayout.setHorizontalGroup(
            circleAddLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 255, Short.MAX_VALUE)
        );
        circleAddLayout.setVerticalGroup(
            circleAddLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout rightBarLayout = new javax.swing.GroupLayout(rightBar);
        rightBar.setLayout(rightBarLayout);
        rightBarLayout.setHorizontalGroup(
            rightBarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(rightBarLayout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addComponent(circleAdd, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(22, Short.MAX_VALUE))
        );
        rightBarLayout.setVerticalGroup(
            rightBarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, rightBarLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(circleAdd, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        topBar.setBackground(new java.awt.Color(102, 102, 102));
        topBar.setPreferredSize(new java.awt.Dimension(1100, 153));

        title.setFont(new java.awt.Font("Agency FB", 0, 48)); // NOI18N
        title.setForeground(new java.awt.Color(255, 255, 255));
        title.setText("Currently Showing: Nothing");

        description.setFont(new java.awt.Font("Agency FB", 0, 22)); // NOI18N
        description.setForeground(new java.awt.Color(255, 255, 255));
        description.setText("Click a sector on the left to change things!");
        description.setVerticalAlignment(javax.swing.SwingConstants.TOP);

        jSeparator2.setOrientation(javax.swing.SwingConstants.VERTICAL);

        labelGDP.setFont(new java.awt.Font("Agency FB", 0, 36)); // NOI18N
        labelGDP.setForeground(new java.awt.Color(255, 255, 255));
        labelGDP.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        labelGDP.setText("Uncalculated");

        labelBudget.setFont(new java.awt.Font("Agency FB", 0, 36)); // NOI18N
        labelBudget.setForeground(new java.awt.Color(255, 255, 255));
        labelBudget.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        labelBudget.setText("Uncalculated");

        jSeparator3.setOrientation(javax.swing.SwingConstants.VERTICAL);

        label1.setFont(new java.awt.Font("Agency FB", 0, 36)); // NOI18N
        label1.setForeground(new java.awt.Color(255, 255, 255));
        label1.setText("Real Time GDP:");

        label2.setFont(new java.awt.Font("Agency FB", 0, 36)); // NOI18N
        label2.setForeground(new java.awt.Color(255, 255, 255));
        label2.setText("Spending Budget:");

        javax.swing.GroupLayout topBarLayout = new javax.swing.GroupLayout(topBar);
        topBar.setLayout(topBarLayout);
        topBarLayout.setHorizontalGroup(
            topBarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(topBarLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(topBarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(title, javax.swing.GroupLayout.DEFAULT_SIZE, 530, Short.MAX_VALUE)
                    .addComponent(description, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(topBarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(label1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(label2, javax.swing.GroupLayout.DEFAULT_SIZE, 188, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(topBarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(labelGDP, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 324, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(labelBudget, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 324, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jSeparator3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        topBarLayout.setVerticalGroup(
            topBarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(topBarLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(topBarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(topBarLayout.createSequentialGroup()
                        .addComponent(title, javax.swing.GroupLayout.PREFERRED_SIZE, 68, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(description, javax.swing.GroupLayout.DEFAULT_SIZE, 73, Short.MAX_VALUE))
                    .addGroup(topBarLayout.createSequentialGroup()
                        .addGroup(topBarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(labelGDP, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(label1, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(topBarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(labelBudget, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(label2, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, topBarLayout.createSequentialGroup()
                        .addGroup(topBarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jSeparator3, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jSeparator2))
                        .addContainerGap())))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addComponent(leftBar, javax.swing.GroupLayout.DEFAULT_SIZE, 410, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(backadd, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(topBar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 0, 0)
                .addComponent(rightBar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(leftBar, javax.swing.GroupLayout.DEFAULT_SIZE, 1003, Short.MAX_VALUE)
            .addComponent(rightBar, javax.swing.GroupLayout.DEFAULT_SIZE, 1003, Short.MAX_VALUE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addComponent(topBar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(0, 0, 0)
                .addComponent(backadd, javax.swing.GroupLayout.PREFERRED_SIZE, 850, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    public static javax.swing.JPanel backadd;
    private javax.swing.JPanel circleAdd;
    public static javax.swing.JPanel color1;
    public static javax.swing.JPanel color2;
    public static javax.swing.JLabel description;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JSeparator jSeparator3;
    public static javax.swing.JLabel label1;
    public static javax.swing.JLabel label2;
    public static javax.swing.JLabel labelBudget;
    public static javax.swing.JLabel labelGDP;
    public static javax.swing.JPanel leftBar;
    private javax.swing.JPanel onlineStatePanel;
    public static javax.swing.JPanel panel1;
    public static javax.swing.JPanel panel2;
    private javax.swing.JLabel picHold;
    public static javax.swing.JPanel rightBar;
    private static javax.swing.JPanel sideBarBack;
    private javax.swing.JSlider time;
    public static javax.swing.JLabel title;
    public static javax.swing.JLabel titleSpeed;
    public static javax.swing.JLabel titleTime;
    public static javax.swing.JPanel topBar;
    // End of variables declaration//GEN-END:variables
}
