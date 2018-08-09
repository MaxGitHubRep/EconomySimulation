package economysimulation.classes.managers.popup.hint;

import economysimulation.classes.managers.themes.Theme;
import economysimulation.classes.managers.ui.Format;
import java.awt.Color;
import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import javax.swing.AbstractAction;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.Timer;

/**
 *
 * @author Max Carter
 */
public class PopUpHint extends javax.swing.JFrame {

    public static int urgency, pX, pY, hintDisplayTime = 5000;
    public String title, description;
    
    public PopUpHint(String title, String description, int urgency) {
        initComponents();
        setIconImage(new ImageIcon(getClass().getResource("/economysimulation/resources/icon/icon128.png")).getImage());
        
        this.title = title;
        this.description = description;
        this.urgency = urgency;
    }
    
    public static void frameDragged(JPanel dragPanel) {
        dragPanel.addMouseListener(new MouseAdapter() {
            public void mousePressed(MouseEvent me) {
                pX = me.getX();
                pY = me.getY();
            }
        });
                
        dragPanel.addMouseMotionListener(new MouseMotionAdapter() {
            public void mouseDragged(MouseEvent me) {
                for (Frame frame : PopUpHint.getFrames()) {
                    if (frame.getTitle().contains("Hint: "))
                    frame.setLocation(frame.getLocation().x + me.getX() - pX, frame.getLocation().y + me.getY() - pY);
                } 
            }
        });
    }
    
    private Color getUrgencyColor() {
        switch (urgency) {
            case Urgency.LOW:
                return Color.green;
            case Urgency.MEDIUM:
                return Color.yellow;
            case Urgency.HIGH:
                return Color.orange;
            case Urgency.SEVERE:
                return Color.red;
            default:
                return Theme.secondaryHover;
        }
    }
    
    public static void updateTheme() {
        Theme.applyPanelThemes(new JPanel[]{ bottom }, null, null, null);
        Theme.applyTextThemes(new JLabel[]{ descLabel }, null);
    }
    
    public void close() {
        this.dispose();
    }
    
    public void initTimer() {
        Timer timer = new Timer(hintDisplayTime, new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                close();
            }
        }); 
        timer.start();
    }
    
    public void createHint() {
        titleLabel.setText((urgency < 4 ? "[" + Urgency.getUrgencyString(urgency).toUpperCase() + "] " : "") + "Hint: " + title);
        descLabel.setText("<html>" + description + "</html>");
        
        top.setBackground(getUrgencyColor());
        Format.addButtonFormat(bottom, new JPanel());
        
        this.setVisible(true);
        this.setTitle("[Economy Simulation] Hint: " + title);
        this.setLocation(1280, 880);
        
        frameDragged(top);
        updateTheme();
        initTimer();
        
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        top = new javax.swing.JPanel();
        titleLabel = new javax.swing.JLabel();
        exit = new javax.swing.JLabel();
        bottom = new javax.swing.JPanel();
        descLabel = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setAutoRequestFocus(false);
        setUndecorated(true);
        setResizable(false);

        top.setBackground(new java.awt.Color(204, 0, 0));

        titleLabel.setFont(new java.awt.Font("Agency FB", 0, 24)); // NOI18N
        titleLabel.setForeground(new java.awt.Color(255, 255, 255));
        titleLabel.setText("<title>");

        exit.setFont(new java.awt.Font("Agency FB", 0, 36)); // NOI18N
        exit.setForeground(new java.awt.Color(255, 255, 255));
        exit.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        exit.setText("x");
        exit.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        exit.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                exitMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout topLayout = new javax.swing.GroupLayout(top);
        top.setLayout(topLayout);
        topLayout.setHorizontalGroup(
            topLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(topLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(titleLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 419, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 50, Short.MAX_VALUE)
                .addComponent(exit, javax.swing.GroupLayout.PREFERRED_SIZE, 19, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        topLayout.setVerticalGroup(
            topLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(topLayout.createSequentialGroup()
                .addComponent(titleLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(topLayout.createSequentialGroup()
                .addComponent(exit, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                .addContainerGap())
        );

        bottom.setBackground(new java.awt.Color(255, 255, 255));

        descLabel.setFont(new java.awt.Font("Agency FB", 0, 24)); // NOI18N
        descLabel.setForeground(new java.awt.Color(204, 0, 0));
        descLabel.setText("<desc>");
        descLabel.setVerticalAlignment(javax.swing.SwingConstants.TOP);

        javax.swing.GroupLayout bottomLayout = new javax.swing.GroupLayout(bottom);
        bottom.setLayout(bottomLayout);
        bottomLayout.setHorizontalGroup(
            bottomLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(bottomLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(descLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        bottomLayout.setVerticalGroup(
            bottomLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(bottomLayout.createSequentialGroup()
                .addComponent(descLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 63, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(top, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(bottom, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(top, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(bottom, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void exitMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_exitMouseClicked
        this.dispose();
    }//GEN-LAST:event_exitMouseClicked


    // Variables declaration - do not modify//GEN-BEGIN:variables
    public static javax.swing.JPanel bottom;
    public static javax.swing.JLabel descLabel;
    private javax.swing.JLabel exit;
    public static javax.swing.JLabel titleLabel;
    private javax.swing.JPanel top;
    // End of variables declaration//GEN-END:variables
}
