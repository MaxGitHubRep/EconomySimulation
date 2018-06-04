package economysimulation.classes.fronter;

import economysimulation.classes.Methods;
import economysimulation.classes.algorithms.Formula;
import economysimulation.classes.subpanels.QSideBar;
import economysimulation.classes.zmisc.Assist;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.DecimalFormat;
import javax.swing.AbstractAction;
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
    
    private Timer timer;
    private int[] times = new int[]{ 0, 0, 0 };
    
    //<editor-fold defaultstate="collapsed" desc="Formats the labels."> 
    public static void addButtonFormat(JPanel backPanel, JPanel colorPanel) {
        backPanel.addMouseListener(new MouseAdapter() {

            @Override 
            public void mouseEntered(MouseEvent e) {
                backPanel.setBackground(new Color(240, 240, 240));
                colorPanel.setBackground(new Color(204, 0, 0));
            }
            
            @Override
            public void mouseExited(MouseEvent e) {
                backPanel.setBackground(Color.white);
                colorPanel.setBackground(Color.white);
            }
        });
    }//</editor-fold>
    
    //<editor-fold defaultstate="collapsed" desc="Emits a tick for the game to follow in other classes."> 
    public static void globalClockTick() {
        Methods.TICKS++;
        Methods.updateRealGDPLabel();
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
            times[0] = 0;
            times[1]++;
            if (times[1] == 12) {
                times[1] = 0;
                times[2]++;
                Formula.calculateAnnualBudget();
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
        
        addButtonFormat(panel1, color1);
        addButtonFormat(panel2, color2);
        
        Methods.changeComponenetColour();
        
    }//</editor-fold>

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        backadd = new javax.swing.JPanel();
        sidebar = new javax.swing.JPanel();
        picHold = new javax.swing.JLabel();
        time = new javax.swing.JSlider();
        sideBarBack = new javax.swing.JPanel();
        labelGDP = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        panel1 = new javax.swing.JPanel();
        titleTime = new javax.swing.JLabel();
        color1 = new javax.swing.JPanel();
        panel2 = new javax.swing.JPanel();
        titleSpeed = new javax.swing.JLabel();
        color2 = new javax.swing.JPanel();

        setBackground(new java.awt.Color(204, 204, 204));

        backadd.setBackground(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout backaddLayout = new javax.swing.GroupLayout(backadd);
        backadd.setLayout(backaddLayout);
        backaddLayout.setHorizontalGroup(
            backaddLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1100, Short.MAX_VALUE)
        );
        backaddLayout.setVerticalGroup(
            backaddLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 800, Short.MAX_VALUE)
        );

        sidebar.setBackground(new java.awt.Color(255, 255, 255));
        sidebar.setPreferredSize(new java.awt.Dimension(400, 100));

        picHold.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        picHold.setIcon(new javax.swing.ImageIcon(getClass().getResource("/economysimulation/resources/logos/border280.png"))); // NOI18N

        time.setMaximum(200);
        time.setMinimum(1);
        time.setValue(100);
        time.setFocusable(false);
        time.setOpaque(false);

        sideBarBack.setOpaque(false);

        javax.swing.GroupLayout sideBarBackLayout = new javax.swing.GroupLayout(sideBarBack);
        sideBarBack.setLayout(sideBarBackLayout);
        sideBarBackLayout.setHorizontalGroup(
            sideBarBackLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        sideBarBackLayout.setVerticalGroup(
            sideBarBackLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 453, Short.MAX_VALUE)
        );

        labelGDP.setFont(new java.awt.Font("Agency FB", 1, 24)); // NOI18N
        labelGDP.setForeground(new java.awt.Color(204, 0, 0));
        labelGDP.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        labelGDP.setText("Real GDP: £xxxxx bn");

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
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(titleTime, javax.swing.GroupLayout.PREFERRED_SIZE, 198, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(32, 32, 32))
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
                .addComponent(titleSpeed, javax.swing.GroupLayout.PREFERRED_SIZE, 189, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        panel2Layout.setVerticalGroup(
            panel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panel2Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(titleSpeed, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addComponent(color2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout sidebarLayout = new javax.swing.GroupLayout(sidebar);
        sidebar.setLayout(sidebarLayout);
        sidebarLayout.setHorizontalGroup(
            sidebarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(picHold, javax.swing.GroupLayout.DEFAULT_SIZE, 300, Short.MAX_VALUE)
            .addComponent(sideBarBack, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(sidebarLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(sidebarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(time, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(labelGDP, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jSeparator1, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(panel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(panel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        sidebarLayout.setVerticalGroup(
            sidebarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(sidebarLayout.createSequentialGroup()
                .addComponent(picHold, javax.swing.GroupLayout.PREFERRED_SIZE, 136, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(3, 3, 3)
                .addComponent(panel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(time, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(panel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(labelGDP, javax.swing.GroupLayout.DEFAULT_SIZE, 51, Short.MAX_VALUE)
                .addGap(4, 4, 4)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(sideBarBack, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addComponent(sidebar, javax.swing.GroupLayout.DEFAULT_SIZE, 300, Short.MAX_VALUE)
                .addGap(0, 0, 0)
                .addComponent(backadd, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(sidebar, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 800, Short.MAX_VALUE)
                    .addComponent(backadd, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(0, 0, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    public static javax.swing.JPanel backadd;
    private javax.swing.JPanel color1;
    private javax.swing.JPanel color2;
    private javax.swing.JSeparator jSeparator1;
    public static javax.swing.JLabel labelGDP;
    private javax.swing.JPanel panel1;
    private javax.swing.JPanel panel2;
    private javax.swing.JLabel picHold;
    private static javax.swing.JPanel sideBarBack;
    private javax.swing.JPanel sidebar;
    private javax.swing.JSlider time;
    public static javax.swing.JLabel titleSpeed;
    public static javax.swing.JLabel titleTime;
    // End of variables declaration//GEN-END:variables
}
