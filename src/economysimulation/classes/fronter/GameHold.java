package economysimulation.classes.fronter;

import economysimulation.classes.Methods;
import economysimulation.classes.algorithms.Component;
import static economysimulation.classes.algorithms.Component.calcComp;
import economysimulation.classes.managers.themes.Theme;
import economysimulation.classes.managers.ui.CompFormat;
import economysimulation.classes.subpanels.QBudget;
import economysimulation.classes.subpanels.QSideBar;
import economysimulation.classes.zmisc.Assist;
import java.awt.event.ActionEvent;
import java.text.DecimalFormat;
import javax.swing.AbstractAction;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.Timer;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

/**
 *
 * @author Max Carter
 */
public class GameHold extends javax.swing.JPanel {

    public final String SPEED_FORMAT = "Speed: %s";
    private final DecimalFormat f = new DecimalFormat("#00");
    private final DecimalFormat fYear = new DecimalFormat("#0000");
    private final int[] monthSize = new int[]{ 31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31 };
    public static int SPEED;
    public final int SPEED_MID_POINT = 100;
    public static final int TICKS_IN_QUARTER = 90;
    public static int TICKS_PER_QUARTER = 0;
    
    private Timer timer;
    private int[] times = new int[]{ 0, 0, 0 };
    
    public static JPanel[] hintBacks;
    public static JLabel[] hintTitles;
    public static JLabel[] hintDescs;
    public static final int MAX_HINTS = 3;
      
    public static void addHint(String title, String desc) {

        for (int i = MAX_HINTS-1; i >= 0; i--) {
            if (i+1 < MAX_HINTS) {
                hintTitles[i+1].setText(hintTitles[i].getText());
                hintDescs[i+1].setText(hintDescs[i].getText());
                hintBacks[i+1].setOpaque(true);
            }
            
        }
        
        hintTitles[0].setText(title);
        hintDescs[0].setText(desc);
        hintBacks[0].setOpaque(true);
        
    }
  
    public static void clearHints() {
        for (int i = 0; i < hintTitles.length; i++) {
            hintTitles[i].setText("");
            hintDescs[i].setText("");
            hintBacks[i].setOpaque(false);
        }
    }
    
    //<editor-fold defaultstate="collapsed" desc="Emits a tick for the game to follow in other classes."> 
    public static void globalClockTick() {
        Methods.TICKS++;
        TICKS_PER_QUARTER++;
        if (TICKS_PER_QUARTER == TICKS_IN_QUARTER) {
            TICKS_PER_QUARTER = 0;
            Methods.updateRealGDPLabel();
        }
        int tempSpending = Component.getPublicSpendingTotal(true);
        labelBudget.setText("£" + tempSpending + "/" + Component.ANNUAL_BUDGET + "bn (" + QBudget.format.format((tempSpending/Component.ANNUAL_BUDGET)*100) + "%)");
        
        calcComp();
        
    }//</editor-fold>
    
    //<editor-fold defaultstate="collapsed" desc="Function within timer clock."> 
    public void updateFunction() {
        timer.stop();
        updateTime();
        updateSpeed();
        globalClockTick();
        timerStart();
    }//</editor-fold>
    
    //<editor-fold defaultstate="collapsed" desc="Timer clock."> 
    private void timerStart() { 
        timer = new Timer(SPEED, new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    updateFunction();
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        });
        timer.start();
    }//</editor-fold>
    
    //<editor-fold defaultstate="collapsed" desc="Calculate timer speed."> 
    private void updateSpeed() {
        int speed = time.getValue();
        
        if (speed == SPEED_MID_POINT) {
            SPEED = 1000;
        } else if (speed > SPEED_MID_POINT) {
            SPEED = 500 + ((200 - speed) * 5);
        } else if (speed < SPEED_MID_POINT) {
            SPEED = 1000 + ((100 - speed) * 10);
        }
    }//</editor-fold>
    
    //<editor-fold defaultstate="collapsed" desc="Calculate display time."> 
    private void updateTime() {
        times[0]++;

        if (times[0] == monthSize[times[1]]+1) {
            times[0] = 1;
            times[1]++;
            if (times[1] == 12) {
                times[1] = 0;
                times[2]++;
                Component.calculateAnnualBudget();
            }
        }

        try {
            titleTime.setText(f.format(times[0]) + "/" + f.format(times[1]) + "/" + fYear.format(times[2]));
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        
    }//</editor-fold>
    
    //<editor-fold defaultstate="collapsed" desc="Slider Event.">   
    private void addSliderListener(JSlider slider) { 
        slider.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                
                JSlider source = (JSlider)e.getSource();
                titleSpeed.setText(String.format(SPEED_FORMAT, source.getValue()) + "%");
            }
        });
        
    }//</editor-fold> 

    //<editor-fold defaultstate="collapsed" desc="Constructor."> 
    public GameHold() {
        initComponents();
        Methods.addToFrontPanel(backadd, new Assist(), false);
        Methods.addToFrontPanel(sideBarBack, new QSideBar(), false);

        //usernameLabel.setText("Username: " + Methods.username);

        addSliderListener(time);
        updateSpeed();
        updateTime();
        timerStart();
        
        CompFormat.addButtonFormat(panel1, color1);
        CompFormat.addButtonFormat(panel2, color2);
        
        hintBacks = new JPanel[]{ hintBack1, hintBack2, hintBack3 };
        hintTitles = new JLabel[]{ hintTitle1, hintTitle2, hintTitle3 };
        hintDescs = new JLabel[]{ hintDesc1, hintDesc2, hintDesc3 };

        clearHints();
        
        Theme.applyPanelThemes(new JPanel[]{ backadd, leftBar }, new JPanel[]{ topBar, rightBar }, new JPanel[]{ panel1, panel2 }, new JPanel[]{ color1, color2 });
        Theme.applyTextThemes(new JLabel[]{ titleSpeed, titleTime }, new JLabel[]{ title, description, label1, label2, labelGDP, labelBudget });
        
    }//</editor-fold>

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
        rightBar = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        hintBack1 = new javax.swing.JPanel();
        hintTitle1 = new javax.swing.JLabel();
        hintDesc1 = new javax.swing.JLabel();
        hintBack2 = new javax.swing.JPanel();
        hintTitle2 = new javax.swing.JLabel();
        hintDesc2 = new javax.swing.JLabel();
        hintBack3 = new javax.swing.JPanel();
        hintTitle3 = new javax.swing.JLabel();
        hintDesc3 = new javax.swing.JLabel();
        jSeparator4 = new javax.swing.JSeparator();
        jLabel2 = new javax.swing.JLabel();
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
        backadd.setPreferredSize(new java.awt.Dimension(1500, 800));

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
            .addGap(0, 630, Short.MAX_VALUE)
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

        javax.swing.GroupLayout leftBarLayout = new javax.swing.GroupLayout(leftBar);
        leftBar.setLayout(leftBarLayout);
        leftBarLayout.setHorizontalGroup(
            leftBarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(picHold, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(sideBarBack, javax.swing.GroupLayout.DEFAULT_SIZE, 406, Short.MAX_VALUE)
            .addGroup(leftBarLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(leftBarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
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
                .addGap(41, 41, 41)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(sideBarBack, javax.swing.GroupLayout.PREFERRED_SIZE, 630, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        rightBar.setBackground(new java.awt.Color(102, 102, 102));

        jLabel1.setText("new sidebar right");

        hintTitle1.setFont(new java.awt.Font("Agency FB", 0, 24)); // NOI18N
        hintTitle1.setForeground(new java.awt.Color(204, 0, 0));
        hintTitle1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        hintTitle1.setText("Your consumption rate is low!");

        hintDesc1.setFont(new java.awt.Font("Agency FB", 0, 18)); // NOI18N
        hintDesc1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        hintDesc1.setText("<hint>");
        hintDesc1.setToolTipText("");
        hintDesc1.setVerticalAlignment(javax.swing.SwingConstants.TOP);

        javax.swing.GroupLayout hintBack1Layout = new javax.swing.GroupLayout(hintBack1);
        hintBack1.setLayout(hintBack1Layout);
        hintBack1Layout.setHorizontalGroup(
            hintBack1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, hintBack1Layout.createSequentialGroup()
                .addGap(5, 5, 5)
                .addGroup(hintBack1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(hintDesc1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(hintTitle1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(5, 5, 5))
        );
        hintBack1Layout.setVerticalGroup(
            hintBack1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(hintBack1Layout.createSequentialGroup()
                .addComponent(hintTitle1, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(hintDesc1, javax.swing.GroupLayout.DEFAULT_SIZE, 54, Short.MAX_VALUE))
        );

        hintTitle2.setFont(new java.awt.Font("Agency FB", 0, 24)); // NOI18N
        hintTitle2.setForeground(new java.awt.Color(204, 0, 0));
        hintTitle2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        hintTitle2.setText("Your consumption rate is low!");

        hintDesc2.setFont(new java.awt.Font("Agency FB", 0, 18)); // NOI18N
        hintDesc2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        hintDesc2.setText("<hint>");
        hintDesc2.setToolTipText("");
        hintDesc2.setVerticalAlignment(javax.swing.SwingConstants.TOP);

        javax.swing.GroupLayout hintBack2Layout = new javax.swing.GroupLayout(hintBack2);
        hintBack2.setLayout(hintBack2Layout);
        hintBack2Layout.setHorizontalGroup(
            hintBack2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, hintBack2Layout.createSequentialGroup()
                .addGap(5, 5, 5)
                .addGroup(hintBack2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(hintDesc2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(hintTitle2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(5, 5, 5))
        );
        hintBack2Layout.setVerticalGroup(
            hintBack2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(hintBack2Layout.createSequentialGroup()
                .addComponent(hintTitle2, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(hintDesc2, javax.swing.GroupLayout.DEFAULT_SIZE, 54, Short.MAX_VALUE))
        );

        hintTitle3.setFont(new java.awt.Font("Agency FB", 0, 24)); // NOI18N
        hintTitle3.setForeground(new java.awt.Color(204, 0, 0));
        hintTitle3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        hintTitle3.setText("Your consumption rate is low!");

        hintDesc3.setFont(new java.awt.Font("Agency FB", 0, 18)); // NOI18N
        hintDesc3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        hintDesc3.setText("<hint>");
        hintDesc3.setToolTipText("");
        hintDesc3.setVerticalAlignment(javax.swing.SwingConstants.TOP);

        javax.swing.GroupLayout hintBack3Layout = new javax.swing.GroupLayout(hintBack3);
        hintBack3.setLayout(hintBack3Layout);
        hintBack3Layout.setHorizontalGroup(
            hintBack3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, hintBack3Layout.createSequentialGroup()
                .addGap(5, 5, 5)
                .addGroup(hintBack3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(hintDesc3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(hintTitle3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(5, 5, 5))
        );
        hintBack3Layout.setVerticalGroup(
            hintBack3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(hintBack3Layout.createSequentialGroup()
                .addComponent(hintTitle3, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(hintDesc3, javax.swing.GroupLayout.DEFAULT_SIZE, 54, Short.MAX_VALUE))
        );

        jLabel2.setBackground(new java.awt.Color(255, 255, 255));
        jLabel2.setFont(new java.awt.Font("Agency FB", 0, 36)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(204, 0, 0));
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setText("~ Hints ~");
        jLabel2.setOpaque(true);

        javax.swing.GroupLayout rightBarLayout = new javax.swing.GroupLayout(rightBar);
        rightBar.setLayout(rightBarLayout);
        rightBarLayout.setHorizontalGroup(
            rightBarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(rightBarLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(rightBarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, 274, Short.MAX_VALUE)
                    .addComponent(hintBack1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(hintBack2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(hintBack3, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jSeparator4)
                    .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        rightBarLayout.setVerticalGroup(
            rightBarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(rightBarLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(hintBack1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(hintBack2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(hintBack3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        topBar.setBackground(new java.awt.Color(102, 102, 102));

        title.setFont(new java.awt.Font("Agency FB", 0, 48)); // NOI18N
        title.setForeground(new java.awt.Color(255, 255, 255));
        title.setText("Currently Showing: Nothing");

        description.setFont(new java.awt.Font("Agency FB", 0, 20)); // NOI18N
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
        label1.setText("Quarterly GDP:");

        label2.setFont(new java.awt.Font("Agency FB", 0, 36)); // NOI18N
        label2.setForeground(new java.awt.Color(255, 255, 255));
        label2.setText("Annual Budget:");

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
                    .addComponent(label1)
                    .addComponent(label2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(topBarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(labelGDP, javax.swing.GroupLayout.DEFAULT_SIZE, 324, Short.MAX_VALUE)
                    .addComponent(labelBudget, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 30, Short.MAX_VALUE)
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
                        .addComponent(description, javax.swing.GroupLayout.DEFAULT_SIZE, 65, Short.MAX_VALUE))
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
                .addComponent(leftBar, javax.swing.GroupLayout.DEFAULT_SIZE, 406, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(backadd, javax.swing.GroupLayout.PREFERRED_SIZE, 1100, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(topBar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 0, 0)
                .addComponent(rightBar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(leftBar, javax.swing.GroupLayout.DEFAULT_SIZE, 1003, Short.MAX_VALUE)
            .addComponent(rightBar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addComponent(topBar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(0, 0, 0)
                .addComponent(backadd, javax.swing.GroupLayout.PREFERRED_SIZE, 850, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    public static javax.swing.JPanel backadd;
    private javax.swing.JPanel color1;
    private javax.swing.JPanel color2;
    public static javax.swing.JLabel description;
    private javax.swing.JPanel hintBack1;
    private javax.swing.JPanel hintBack2;
    private javax.swing.JPanel hintBack3;
    private javax.swing.JLabel hintDesc1;
    private javax.swing.JLabel hintDesc2;
    private javax.swing.JLabel hintDesc3;
    private javax.swing.JLabel hintTitle1;
    private javax.swing.JLabel hintTitle2;
    private javax.swing.JLabel hintTitle3;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JSeparator jSeparator3;
    private javax.swing.JSeparator jSeparator4;
    public static javax.swing.JLabel label1;
    public static javax.swing.JLabel label2;
    public static javax.swing.JLabel labelBudget;
    public static javax.swing.JLabel labelGDP;
    private javax.swing.JPanel leftBar;
    private javax.swing.JPanel panel1;
    private javax.swing.JPanel panel2;
    private javax.swing.JLabel picHold;
    private javax.swing.JPanel rightBar;
    private static javax.swing.JPanel sideBarBack;
    private javax.swing.JSlider time;
    public static javax.swing.JLabel title;
    public static javax.swing.JLabel titleSpeed;
    public static javax.swing.JLabel titleTime;
    private javax.swing.JPanel topBar;
    // End of variables declaration//GEN-END:variables
}
