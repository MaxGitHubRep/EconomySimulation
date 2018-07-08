package economysimulation.classes.startup;

import economysimulation.classes.Methods;
import java.awt.Color;
import java.awt.event.ActionEvent;
import javax.swing.AbstractAction;
import javax.swing.JLabel;
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
    
    public WelcomePanel() {
        initComponents();
        
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
        animBack = new javax.swing.JPanel();
        gdPic = new javax.swing.JLabel();

        setBackground(new java.awt.Color(255, 255, 255));
        setOpaque(false);

        sideBarLeft.setMinimumSize(new java.awt.Dimension(500, 100));

        jLabel1.setText("<economy simulation title>");

        javax.swing.GroupLayout sideBarLeftLayout = new javax.swing.GroupLayout(sideBarLeft);
        sideBarLeft.setLayout(sideBarLeftLayout);
        sideBarLeftLayout.setHorizontalGroup(
            sideBarLeftLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(sideBarLeftLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, 476, Short.MAX_VALUE)
                .addContainerGap())
        );
        sideBarLeftLayout.setVerticalGroup(
            sideBarLeftLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(sideBarLeftLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addContainerGap(971, Short.MAX_VALUE))
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
                .addComponent(sideBarLeft, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(animBack, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(sideBarLeft, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(animBack, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel animBack;
    private javax.swing.JLabel gdPic;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel sideBarLeft;
    // End of variables declaration//GEN-END:variables
}
