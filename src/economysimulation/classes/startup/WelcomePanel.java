package economysimulation.classes.startup;

import economysimulation.classes.Methods;
import economysimulation.classes.managers.ui.CompFormat;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.AbstractAction;
import javax.swing.JLabel;
import javax.swing.JPanel;
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
    
    private static final String[] TITLES = new String[]{ "Solo Classic" };
    private static final String[] DESCS = new String[]{ "Solo, stats not tracked" };
    
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
                    titleLabels[id].setText("<html>" + DESCS[id] + "</html>");
                    titleLabels[id].setFont(new Font("Agency FB", 0, 24));

                }

                @Override
                public void mouseExited(MouseEvent e) {
                    titleLabels[id].setText(TITLES[id]);
                    titleLabels[id].setFont(new Font("Agency FB", 0, 48));
                }

            });
        } catch (Exception ex) {
            ex.printStackTrace();
        }

    }
    
    public WelcomePanel() {
        initComponents();
        
        backPanels = new JPanel[]{ back1 };
        colorPanels = new JPanel[]{ co1 };
        titleLabels = new JLabel[]{ title1 };
        
        for (int i = 0; i < backPanels.length; i++) {
            CompFormat.addButtonFormat(backPanels[i], colorPanels[i]);
        }
        
        CompFormat.addGhostText(enterUsername, "Username");
        
        for (int i = 0; i < backPanels.length; i++) {
            addPanelHoverEvent(backPanels[i], i);
        }
        
        signals = new JLabel[max];
        
        for (int i = 0; i < signals.length; i++) {
            signals[i] = new JLabel();
            signals[i].setSize(size, size);
            signals[i].setOpaque(true);
            signals[i].setBackground(Color.green);
            signals[i].setVisible(true);
        }

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
                    .addComponent(back1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
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
                .addContainerGap(349, Short.MAX_VALUE))
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


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel animBack;
    private javax.swing.JPanel back1;
    private javax.swing.JPanel co1;
    private javax.swing.JTextField enterUsername;
    private javax.swing.JLabel gdPic;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JPanel sideBarLeft;
    private javax.swing.JLabel title1;
    // End of variables declaration//GEN-END:variables
}
