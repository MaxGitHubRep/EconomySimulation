package economysimulation;

import java.awt.event.ActionEvent;
import javax.swing.AbstractAction;
import javax.swing.ImageIcon;
import javax.swing.JTextField;
import javax.swing.Timer;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

/**
 *
 * @author Max Carter
 */
public class LoginMenu extends javax.swing.JPanel {

    private final int SWITCH_BACK_TICKS = 5000;
    private final int TOTAL_BACK_PICS = 4;
    private final int ITERATOR = 2;
    private final String GHOST_TEXT = "Enter username here";
    private int TICKS;
    
    private void addRandomImage() {
        picHold.setIcon(new ImageIcon(getClass().getResource("/economysimulation/resources/background/back" + Methods.randomInt(1, TOTAL_BACK_PICS) + ".png")));
    }
    
    public void timerStart() { 
        Timer timerChart = new Timer(ITERATOR, new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    TICKS+=ITERATOR;
                    if (TICKS >= SWITCH_BACK_TICKS){
                        TICKS = 0;
                        addRandomImage();
                    }
                    
                } catch (Exception ex) {
                    
                }
            }
        }); 
        timerChart.start();
    }
    
    private void checkTextFields(JTextField field) {
        field.getDocument().addDocumentListener(new DocumentListener() {
            public void changedUpdate(DocumentEvent e) {
              execute();
            }
            public void removeUpdate(DocumentEvent e) {
              execute();
            }
            public void insertUpdate(DocumentEvent e) {
              execute();
            }

            public void execute() {
                QLogIn.begin.setEnabled(!"".equals(QLogIn.entername.getText()) && !GHOST_TEXT.equals(QLogIn.entername.getText()));
            }
        });
        
    }
    
    public LoginMenu() {
        initComponents();
        Methods.addToFrontPanel(right, new QLogIn(), false);
        
        checkTextFields(QLogIn.entername);
        Methods.addGhostText(QLogIn.entername, GHOST_TEXT);
        timerStart();   
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        right = new javax.swing.JPanel();
        picHold = new javax.swing.JLabel();

        setBackground(new java.awt.Color(204, 204, 204));
        setPreferredSize(new java.awt.Dimension(1400, 800));

        right.setBackground(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout rightLayout = new javax.swing.GroupLayout(right);
        right.setLayout(rightLayout);
        rightLayout.setHorizontalGroup(
            rightLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 600, Short.MAX_VALUE)
        );
        rightLayout.setVerticalGroup(
            rightLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        picHold.setIcon(new javax.swing.ImageIcon(getClass().getResource("/economysimulation/resources/background/back1.png"))); // NOI18N

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addComponent(picHold, javax.swing.GroupLayout.PREFERRED_SIZE, 800, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(right, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(right, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(picHold, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel picHold;
    private javax.swing.JPanel right;
    // End of variables declaration//GEN-END:variables
}
