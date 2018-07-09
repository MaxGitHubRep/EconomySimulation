package economysimulation.classes.startup;

import economysimulation.classes.MainFrame;
import economysimulation.classes.Methods;
import economysimulation.classes.fronter.IntroPage;
import economysimulation.classes.managers.ui.CompFormat;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.AbstractAction;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.Timer;

/**
 *
 * @author Max Carter
 */
public class WelcomePanel extends javax.swing.JPanel {

    private static final int height = 1000;
    private static final int width = 1300;
    private static final int size = 6;
    private static JLabel[] signals;
    private static final int max = width/2;
    private static boolean rotate = false;
    private static int index = 0;
    private static int length = 0;
    private Timer timer;
    
    private static final String[] TITLES = new String[]{ "Solo Classic", "Coop Classic", "Solo Competitive", "Coop Competitive" };
    private static final String[] DESCS = new String[]{
        "Model an economy on your own, stats are not saved",
        "Model an economy with a friend, stats are not saved",
        "Single player experience where your stats are saved on the leaderboards",
        "Cooperative experience with a friend where your stats are saved on the leaderboards"
    };
    
    private static JLabel[] titleLabels;
    private static JPanel[] colorPanels;
    private static JPanel[] backPanels;
    

    private void update() {
        timer.stop();
        animBack.add(signals[index]);
        
        if (rotate) {
            for (int i = max-1; i >= 0; i--) {
                if (i-1 >= 0) {
                    signals[i].setLocation(signals[i-1].getX(), signals[i-1].getY());
                }
            }

            if (index+1 == max) {
                rotate = false;
                index = 0;
                length = 0;
            }
        } else if (index != 0) {
            int newHeight = signals[index-1].getY()+(int) Math.round(Math.cos(0.02*length)*15) + Methods.randomInt(-size*2, size*2);
            signals[index].setLocation(length, newHeight);
            signals[index].setBackground(newHeight < signals[index-1].getY() ? Color.green : Color.red);
        } else {
            signals[index].setLocation(0, height/2);
        }
        
        length+=2;
        index++;
        
        if (index == max) {
            index = 0;
            if (!rotate) rotate = true;
        }
        
        timerStart();
    }
    
    public void timerStart() { 
        timer = new Timer(5, new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    update();
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
                
            }
        }); 
        timer.start();
    }
    
    private static void addPanelHoverEvent(JPanel panel, int id) {
        try {
            panel.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseEntered(MouseEvent e) {
                    titleLabels[id].setText("<html>" + DESCS[id] + ".</html>");
                    titleLabels[id].setFont(new Font("Agency FB", 0, 24));
                }

                @Override
                public void mouseExited(MouseEvent e) {
                    titleLabels[id].setText(TITLES[id]);
                    titleLabels[id].setFont(new Font("Agency FB", 0, 48));
                }
                
                @Override
                public void mouseClicked(MouseEvent e) {
                    Methods.mode = id + 1;
                    MainFrame.addToMainFrame(new IntroPage());
                }

            });
        } catch (Exception ex) {
            ex.printStackTrace();
        }

    }
    
    public WelcomePanel() {
        initComponents();
        
        backPanels = new JPanel[]{ back1, back2, back3, back4, back5, back6 };
        colorPanels = new JPanel[]{ co1, co2, co3, co4, co5, co6 };
        titleLabels = new JLabel[]{ title1, title2, title3, title4, title5, title6 };
        
        for (int i = 0; i < backPanels.length; i++) {
            CompFormat.addButtonFormat(backPanels[i], colorPanels[i]);
            if (i < backPanels.length-2) {
                addPanelHoverEvent(backPanels[i], i);
            }
        }
        
        title5.setText("<html>Created by<br>Max Carter</html>");
        CompFormat.addGhostText(enterUsername, "Username");
        
        signals = new JLabel[max];
        
        for (int i = 0; i < signals.length; i++) {
            signals[i] = new JLabel();
            signals[i].setSize(size, size);
            signals[i].setOpaque(true);
            signals[i].setBackground(Color.green);
            signals[i].setVisible(true);
        }

        JRadioButton btn = new JRadioButton("removes automatic text box focus");
        sideBarLeft.add(btn);
        
        timerStart();
        
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        sideBarLeft = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        enterUsername = new javax.swing.JTextField();
        jSeparator2 = new javax.swing.JSeparator();
        back1 = new javax.swing.JPanel();
        co1 = new javax.swing.JPanel();
        title1 = new javax.swing.JLabel();
        back2 = new javax.swing.JPanel();
        co2 = new javax.swing.JPanel();
        title2 = new javax.swing.JLabel();
        back3 = new javax.swing.JPanel();
        co3 = new javax.swing.JPanel();
        title3 = new javax.swing.JLabel();
        back4 = new javax.swing.JPanel();
        co4 = new javax.swing.JPanel();
        title4 = new javax.swing.JLabel();
        back5 = new javax.swing.JPanel();
        co5 = new javax.swing.JPanel();
        title5 = new javax.swing.JLabel();
        back6 = new javax.swing.JPanel();
        co6 = new javax.swing.JPanel();
        title6 = new javax.swing.JLabel();
        animBack = new javax.swing.JPanel();
        gdPic = new javax.swing.JLabel();

        setBackground(new java.awt.Color(255, 255, 255));
        setOpaque(false);

        sideBarLeft.setBackground(new java.awt.Color(255, 255, 255));
        sideBarLeft.setMinimumSize(new java.awt.Dimension(500, 100));

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/economysimulation/resources/logos/logo-gif.gif"))); // NOI18N

        enterUsername.setFont(new java.awt.Font("Agency FB", 0, 36)); // NOI18N
        enterUsername.setForeground(new java.awt.Color(153, 153, 153));
        enterUsername.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        enterUsername.setText("Username");
        enterUsername.setBorder(null);
        enterUsername.setOpaque(false);

        back1.setBackground(new java.awt.Color(255, 255, 255));

        co1.setBackground(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout co1Layout = new javax.swing.GroupLayout(co1);
        co1.setLayout(co1Layout);
        co1Layout.setHorizontalGroup(
            co1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 40, Short.MAX_VALUE)
        );
        co1Layout.setVerticalGroup(
            co1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        title1.setFont(new java.awt.Font("Agency FB", 0, 48)); // NOI18N
        title1.setForeground(new java.awt.Color(204, 0, 0));
        title1.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        title1.setText("Solo Classic");
        title1.setVerticalAlignment(javax.swing.SwingConstants.TOP);

        javax.swing.GroupLayout back1Layout = new javax.swing.GroupLayout(back1);
        back1.setLayout(back1Layout);
        back1Layout.setHorizontalGroup(
            back1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(back1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(title1, javax.swing.GroupLayout.DEFAULT_SIZE, 383, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(co1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        back1Layout.setVerticalGroup(
            back1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, back1Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addGroup(back1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(title1, javax.swing.GroupLayout.DEFAULT_SIZE, 60, Short.MAX_VALUE)
                    .addComponent(co1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
        );

        back2.setBackground(new java.awt.Color(255, 255, 255));

        co2.setBackground(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout co2Layout = new javax.swing.GroupLayout(co2);
        co2.setLayout(co2Layout);
        co2Layout.setHorizontalGroup(
            co2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 40, Short.MAX_VALUE)
        );
        co2Layout.setVerticalGroup(
            co2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        title2.setFont(new java.awt.Font("Agency FB", 0, 48)); // NOI18N
        title2.setForeground(new java.awt.Color(204, 0, 0));
        title2.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        title2.setText("Coop Classic");
        title2.setVerticalAlignment(javax.swing.SwingConstants.TOP);

        javax.swing.GroupLayout back2Layout = new javax.swing.GroupLayout(back2);
        back2.setLayout(back2Layout);
        back2Layout.setHorizontalGroup(
            back2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(back2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(title2, javax.swing.GroupLayout.DEFAULT_SIZE, 383, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(co2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        back2Layout.setVerticalGroup(
            back2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, back2Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addGroup(back2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(title2, javax.swing.GroupLayout.DEFAULT_SIZE, 60, Short.MAX_VALUE)
                    .addComponent(co2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
        );

        back3.setBackground(new java.awt.Color(255, 255, 255));

        co3.setBackground(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout co3Layout = new javax.swing.GroupLayout(co3);
        co3.setLayout(co3Layout);
        co3Layout.setHorizontalGroup(
            co3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 40, Short.MAX_VALUE)
        );
        co3Layout.setVerticalGroup(
            co3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        title3.setFont(new java.awt.Font("Agency FB", 0, 48)); // NOI18N
        title3.setForeground(new java.awt.Color(204, 0, 0));
        title3.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        title3.setText("Solo Competitive");
        title3.setVerticalAlignment(javax.swing.SwingConstants.TOP);

        javax.swing.GroupLayout back3Layout = new javax.swing.GroupLayout(back3);
        back3.setLayout(back3Layout);
        back3Layout.setHorizontalGroup(
            back3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(back3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(title3, javax.swing.GroupLayout.DEFAULT_SIZE, 383, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(co3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        back3Layout.setVerticalGroup(
            back3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, back3Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addGroup(back3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(title3, javax.swing.GroupLayout.DEFAULT_SIZE, 60, Short.MAX_VALUE)
                    .addComponent(co3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
        );

        back4.setBackground(new java.awt.Color(255, 255, 255));

        co4.setBackground(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout co4Layout = new javax.swing.GroupLayout(co4);
        co4.setLayout(co4Layout);
        co4Layout.setHorizontalGroup(
            co4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 40, Short.MAX_VALUE)
        );
        co4Layout.setVerticalGroup(
            co4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        title4.setFont(new java.awt.Font("Agency FB", 0, 48)); // NOI18N
        title4.setForeground(new java.awt.Color(204, 0, 0));
        title4.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        title4.setText("Coop Competitive");
        title4.setVerticalAlignment(javax.swing.SwingConstants.TOP);

        javax.swing.GroupLayout back4Layout = new javax.swing.GroupLayout(back4);
        back4.setLayout(back4Layout);
        back4Layout.setHorizontalGroup(
            back4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(back4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(title4, javax.swing.GroupLayout.DEFAULT_SIZE, 383, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(co4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        back4Layout.setVerticalGroup(
            back4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, back4Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addGroup(back4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(title4, javax.swing.GroupLayout.DEFAULT_SIZE, 60, Short.MAX_VALUE)
                    .addComponent(co4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
        );

        back5.setBackground(new java.awt.Color(255, 255, 255));

        co5.setBackground(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout co5Layout = new javax.swing.GroupLayout(co5);
        co5.setLayout(co5Layout);
        co5Layout.setHorizontalGroup(
            co5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 40, Short.MAX_VALUE)
        );
        co5Layout.setVerticalGroup(
            co5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 60, Short.MAX_VALUE)
        );

        title5.setFont(new java.awt.Font("Agency FB", 0, 24)); // NOI18N
        title5.setForeground(new java.awt.Color(204, 0, 0));
        title5.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        title5.setText("Max Carter");
        title5.setVerticalAlignment(javax.swing.SwingConstants.TOP);

        javax.swing.GroupLayout back5Layout = new javax.swing.GroupLayout(back5);
        back5.setLayout(back5Layout);
        back5Layout.setHorizontalGroup(
            back5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(back5Layout.createSequentialGroup()
                .addComponent(co5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(title5, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 7, Short.MAX_VALUE))
        );
        back5Layout.setVerticalGroup(
            back5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(back5Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(co5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addComponent(title5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        back6.setBackground(new java.awt.Color(255, 255, 255));
        back6.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                back6MouseClicked(evt);
            }
        });

        co6.setBackground(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout co6Layout = new javax.swing.GroupLayout(co6);
        co6.setLayout(co6Layout);
        co6Layout.setHorizontalGroup(
            co6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 40, Short.MAX_VALUE)
        );
        co6Layout.setVerticalGroup(
            co6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 60, Short.MAX_VALUE)
        );

        title6.setFont(new java.awt.Font("Agency FB", 0, 24)); // NOI18N
        title6.setForeground(new java.awt.Color(204, 0, 0));
        title6.setText("Leave");

        javax.swing.GroupLayout back6Layout = new javax.swing.GroupLayout(back6);
        back6.setLayout(back6Layout);
        back6Layout.setHorizontalGroup(
            back6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(back6Layout.createSequentialGroup()
                .addGap(7, 7, 7)
                .addComponent(title6, javax.swing.GroupLayout.DEFAULT_SIZE, 125, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(co6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        back6Layout.setVerticalGroup(
            back6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(back6Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(co6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addComponent(title6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout sideBarLeftLayout = new javax.swing.GroupLayout(sideBarLeft);
        sideBarLeft.setLayout(sideBarLeftLayout);
        sideBarLeftLayout.setHorizontalGroup(
            sideBarLeftLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, sideBarLeftLayout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addGroup(sideBarLeftLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, sideBarLeftLayout.createSequentialGroup()
                        .addGroup(sideBarLeftLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(enterUsername, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 360, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jSeparator2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 360, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(24, 24, 24))
                    .addComponent(back1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(back2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(back3, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(back4, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
            .addGroup(sideBarLeftLayout.createSequentialGroup()
                .addComponent(back5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(back6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        sideBarLeftLayout.setVerticalGroup(
            sideBarLeftLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(sideBarLeftLayout.createSequentialGroup()
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 500, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(enterUsername, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(back1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(back2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(back3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(back4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 42, Short.MAX_VALUE)
                .addGroup(sideBarLeftLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(back5, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(back6, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        animBack.setBackground(new java.awt.Color(204, 204, 204));
        animBack.setPreferredSize(new java.awt.Dimension(1300, 1000));

        javax.swing.GroupLayout animBackLayout = new javax.swing.GroupLayout(animBack);
        animBack.setLayout(animBackLayout);
        animBackLayout.setHorizontalGroup(
            animBackLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(gdPic, javax.swing.GroupLayout.DEFAULT_SIZE, 1300, Short.MAX_VALUE)
        );
        animBackLayout.setVerticalGroup(
            animBackLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(gdPic, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(animBack, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(sideBarLeft, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(sideBarLeft, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(animBack, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents

    private void back6MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_back6MouseClicked
        System.exit(0);
    }//GEN-LAST:event_back6MouseClicked


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel animBack;
    private javax.swing.JPanel back1;
    private javax.swing.JPanel back2;
    private javax.swing.JPanel back3;
    private javax.swing.JPanel back4;
    private javax.swing.JPanel back5;
    private javax.swing.JPanel back6;
    private javax.swing.JPanel co1;
    private javax.swing.JPanel co2;
    private javax.swing.JPanel co3;
    private javax.swing.JPanel co4;
    private javax.swing.JPanel co5;
    private javax.swing.JPanel co6;
    private javax.swing.JTextField enterUsername;
    private javax.swing.JLabel gdPic;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JPanel sideBarLeft;
    private javax.swing.JLabel title1;
    private javax.swing.JLabel title2;
    private javax.swing.JLabel title3;
    private javax.swing.JLabel title4;
    private javax.swing.JLabel title5;
    private javax.swing.JLabel title6;
    // End of variables declaration//GEN-END:variables
}
