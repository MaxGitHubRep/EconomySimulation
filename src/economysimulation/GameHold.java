package economysimulation;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import javax.swing.AbstractAction;
import javax.swing.JLabel;
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
    public int SPEED;
    public final int SPEED_MID_POINT = 100;
    
    public JPanel[] opPanels;
    public JLabel[] opButtons;
    
    private Timer timer;
    private int[] times;
    
    //<editor-fold defaultstate="collapsed" desc="Emits a tick for the game to follow in other classes."> 
    public static void globalClockTick() {
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
    
    //<editor-fold defaultstate="collapsed" desc="Adds panel to the Game Panel."> 
    public static void addToFrontPanel(JPanel panel, boolean scrollable) {
        backadd.removeAll();
        backadd.revalidate();
        
        backadd.setLayout(new BorderLayout());
        
        if (scrollable) {
            backadd.add(new JScrollPane(panel,
            JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
            JScrollPane.HORIZONTAL_SCROLLBAR_NEVER));
        } else {
            backadd.add(panel);
        }

        backadd.repaint();
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

        titleTime.setText("Time: " + times[0] + ":" + times[1] + ":" + times[2]);
        
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
        addToFrontPanel(new ZAssist(), false);
        
        usernameLabel.setText("Username: " + Methods.username);
        times = new int[]{ 0, 0, 0 };
        opButtons = new JLabel[]{ titleGov, titleBudget, titleCorp, titleCons, titleConf };
        opPanels = new JPanel[]{
            new PGovernment()
        
        };
        
        for (int i = 0; i < (opButtons.length > opPanels.length ? opPanels : opButtons).length; i++) {
            Methods.addButtonFormat(opButtons[i], opPanels[i]);
        }
        
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
        jPanel1 = new javax.swing.JPanel();
        titleGov = new javax.swing.JLabel();
        titleBudget = new javax.swing.JLabel();
        exit = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        titleCorp = new javax.swing.JLabel();
        titleCons = new javax.swing.JLabel();
        titleConf = new javax.swing.JLabel();
        usernameLabel = new javax.swing.JLabel();

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
            .addGap(0, 0, Short.MAX_VALUE)
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
        titleTime.setText("Time: ss:mm:HH");
        titleTime.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        titleTime.setOpaque(true);

        titleSpeed.setBackground(new java.awt.Color(102, 102, 102));
        titleSpeed.setFont(new java.awt.Font("Agency FB", 1, 36)); // NOI18N
        titleSpeed.setForeground(new java.awt.Color(255, 51, 0));
        titleSpeed.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        titleSpeed.setText("Speed: 100%");
        titleSpeed.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        titleSpeed.setOpaque(true);

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Control Panel", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Agency FB", 1, 36), new java.awt.Color(204, 0, 0))); // NOI18N
        jPanel1.setOpaque(false);

        titleGov.setBackground(new java.awt.Color(102, 102, 102));
        titleGov.setFont(new java.awt.Font("Agency FB", 1, 36)); // NOI18N
        titleGov.setForeground(new java.awt.Color(255, 51, 0));
        titleGov.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        titleGov.setText("Government");
        titleGov.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        titleGov.setOpaque(true);

        titleBudget.setBackground(new java.awt.Color(102, 102, 102));
        titleBudget.setFont(new java.awt.Font("Agency FB", 1, 36)); // NOI18N
        titleBudget.setForeground(new java.awt.Color(255, 51, 0));
        titleBudget.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        titleBudget.setText("Budget Deficit");
        titleBudget.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        titleBudget.setOpaque(true);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(titleGov, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(titleBudget, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(titleGov)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(titleBudget)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        exit.setBackground(new java.awt.Color(255, 255, 255));
        exit.setFont(new java.awt.Font("Agency FB", 1, 18)); // NOI18N
        exit.setForeground(new java.awt.Color(204, 0, 0));
        exit.setText("Exit Game");

        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Determinants", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Agency FB", 1, 36), new java.awt.Color(204, 0, 0))); // NOI18N
        jPanel3.setOpaque(false);

        titleCorp.setBackground(new java.awt.Color(102, 102, 102));
        titleCorp.setFont(new java.awt.Font("Agency FB", 1, 36)); // NOI18N
        titleCorp.setForeground(new java.awt.Color(255, 51, 0));
        titleCorp.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        titleCorp.setText("Corporations");
        titleCorp.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        titleCorp.setOpaque(true);

        titleCons.setBackground(new java.awt.Color(102, 102, 102));
        titleCons.setFont(new java.awt.Font("Agency FB", 1, 36)); // NOI18N
        titleCons.setForeground(new java.awt.Color(255, 51, 0));
        titleCons.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        titleCons.setText("Consumers");
        titleCons.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        titleCons.setOpaque(true);

        titleConf.setBackground(new java.awt.Color(102, 102, 102));
        titleConf.setFont(new java.awt.Font("Agency FB", 1, 36)); // NOI18N
        titleConf.setForeground(new java.awt.Color(255, 51, 0));
        titleConf.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        titleConf.setText("Confidence");
        titleConf.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        titleConf.setOpaque(true);

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(titleCorp, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(titleCons, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(titleConf, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addComponent(titleCorp)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(titleCons)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(titleConf)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        usernameLabel.setFont(new java.awt.Font("Agency FB", 1, 18)); // NOI18N
        usernameLabel.setForeground(new java.awt.Color(204, 0, 0));
        usernameLabel.setText("Username: ");

        javax.swing.GroupLayout sidebarLayout = new javax.swing.GroupLayout(sidebar);
        sidebar.setLayout(sidebarLayout);
        sidebarLayout.setHorizontalGroup(
            sidebarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(picHold, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(sidebarLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(sidebarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(sidebarLayout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addComponent(titleSpeed, javax.swing.GroupLayout.PREFERRED_SIZE, 254, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(24, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, sidebarLayout.createSequentialGroup()
                        .addGroup(sidebarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(usernameLabel, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jPanel3, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(time, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jSeparator1, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jSeparator2, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(exit, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, sidebarLayout.createSequentialGroup()
                                .addGap(10, 10, 10)
                                .addComponent(titleTime, javax.swing.GroupLayout.PREFERRED_SIZE, 254, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, Short.MAX_VALUE)))
                        .addContainerGap())))
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
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(6, 6, 6)
                .addComponent(usernameLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(exit)
                .addContainerGap())
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
            .addComponent(sidebar, javax.swing.GroupLayout.DEFAULT_SIZE, 800, Short.MAX_VALUE)
            .addComponent(backadd, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    public static javax.swing.JPanel backadd;
    private javax.swing.JButton exit;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JLabel picHold;
    private javax.swing.JPanel sidebar;
    private javax.swing.JSlider time;
    private javax.swing.JLabel titleBudget;
    private javax.swing.JLabel titleConf;
    private javax.swing.JLabel titleCons;
    private javax.swing.JLabel titleCorp;
    private javax.swing.JLabel titleGov;
    private javax.swing.JLabel titleSpeed;
    private javax.swing.JLabel titleTime;
    private javax.swing.JLabel usernameLabel;
    // End of variables declaration//GEN-END:variables
}
