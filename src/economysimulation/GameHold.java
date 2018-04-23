package economysimulation;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.text.DecimalFormat;
import javax.swing.AbstractAction;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
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
    public static int SPEED;
    public final int SPEED_MID_POINT = 100;
    
    private Timer timer;
    private int[] times;
    
    //<editor-fold defaultstate="collapsed" desc="Emits a tick for the game to follow in other classes."> 
    public static void globalClockTick() {
        Methods.TICKS++;
        PGovernment.globalClockPulseGov();
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
        for (int i = 0; i < 2; i++) {
            if (times[i] == 60) {
                times[i+1]++;
                times[i] = 0;
            }
        }

        titleTime.setText("Time: " + f.format(times[2]) + ":" + f.format(times[1]) + ":" + f.format(times[0]));
        
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
        addToFrontPanel(backadd, new ZAssist(), false);
        addToFrontPanel(sideBarBack, new QSideBar(), true);

        usernameLabel.setText("Username: " + Methods.username);
        times = new int[]{ 0, 0, 0 };

        addSliderListener(time);
        updateSpeed();
        updateTime();
        timerStart();
    }//</editor-fold>

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        backadd = new javax.swing.JPanel();
        sidebar = new javax.swing.JPanel();
        picHold = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        jSeparator2 = new javax.swing.JSeparator();
        time = new javax.swing.JSlider();
        titleTime = new javax.swing.JLabel();
        titleSpeed = new javax.swing.JLabel();
        usernameLabel = new javax.swing.JLabel();
        sideBarBack = new javax.swing.JPanel();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jSeparator3 = new javax.swing.JSeparator();

        setBackground(new java.awt.Color(204, 204, 204));

        backadd.setBackground(new java.awt.Color(51, 51, 51));

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
        time.setOpaque(false);

        titleTime.setBackground(new java.awt.Color(102, 102, 102));
        titleTime.setFont(new java.awt.Font("Agency FB", 1, 36)); // NOI18N
        titleTime.setForeground(new java.awt.Color(255, 51, 0));
        titleTime.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        titleTime.setText("Time: HH:mm:ss");
        titleTime.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        titleTime.setOpaque(true);

        titleSpeed.setBackground(new java.awt.Color(102, 102, 102));
        titleSpeed.setFont(new java.awt.Font("Agency FB", 1, 36)); // NOI18N
        titleSpeed.setForeground(new java.awt.Color(255, 51, 0));
        titleSpeed.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        titleSpeed.setText("Speed: 100%");
        titleSpeed.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        titleSpeed.setOpaque(true);

        usernameLabel.setFont(new java.awt.Font("Agency FB", 1, 18)); // NOI18N
        usernameLabel.setForeground(new java.awt.Color(204, 0, 0));
        usernameLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        usernameLabel.setText("Username: ");
        usernameLabel.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(204, 0, 0), 1, true));
        usernameLabel.setIconTextGap(10);

        sideBarBack.setOpaque(false);

        javax.swing.GroupLayout sideBarBackLayout = new javax.swing.GroupLayout(sideBarBack);
        sideBarBack.setLayout(sideBarBackLayout);
        sideBarBackLayout.setHorizontalGroup(
            sideBarBackLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
        );
        sideBarBackLayout.setVerticalGroup(
            sideBarBackLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 336, Short.MAX_VALUE)
        );

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Real GDP", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Agency FB", 1, 30), new java.awt.Color(204, 0, 0))); // NOI18N
        jPanel1.setOpaque(false);

        jLabel1.setFont(new java.awt.Font("Agency FB", 1, 24)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(204, 0, 0));
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("£xxxxx bn");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, 42, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout sidebarLayout = new javax.swing.GroupLayout(sidebar);
        sidebar.setLayout(sidebarLayout);
        sidebarLayout.setHorizontalGroup(
            sidebarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(picHold, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(sideBarBack, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(sidebarLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(sidebarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(usernameLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(time, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jSeparator1)
                    .addComponent(jSeparator2)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(sidebarLayout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addGroup(sidebarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(titleSpeed, javax.swing.GroupLayout.PREFERRED_SIZE, 254, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(titleTime, javax.swing.GroupLayout.PREFERRED_SIZE, 254, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 12, Short.MAX_VALUE))
                    .addComponent(jSeparator3))
                .addContainerGap())
        );
        sidebarLayout.setVerticalGroup(
            sidebarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(sidebarLayout.createSequentialGroup()
                .addComponent(picHold, javax.swing.GroupLayout.PREFERRED_SIZE, 137, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(titleTime, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(time, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(titleSpeed, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator3, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(sideBarBack, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(usernameLabel)
                .addGap(11, 11, 11))
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
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JSeparator jSeparator3;
    private javax.swing.JLabel picHold;
    private static javax.swing.JPanel sideBarBack;
    private javax.swing.JPanel sidebar;
    private javax.swing.JSlider time;
    private javax.swing.JLabel titleSpeed;
    private javax.swing.JLabel titleTime;
    private javax.swing.JLabel usernameLabel;
    // End of variables declaration//GEN-END:variables
}
