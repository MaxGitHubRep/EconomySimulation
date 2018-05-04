package economysimulation.classes.panelsfront;

import economysimulation.classes.Methods;
import economysimulation.classes.fronter.LoginMenu;
import java.awt.Color;

/**
 *
 * @author Max Carter
 */
public class QLogIn extends javax.swing.JPanel {

    public static int indexer = 255;
    public static boolean start = false;
    private static int index = 0;
    private static int charLength = LoginMenu.textTitles[0].length();
    
    public static void introPageTick() {
        if (indexer > 50 && start) {
            
            index++;
            if (index == 14) {
                index = 0;
                charLength++;
            }
            indexer--;
            right.setBackground(new Color(indexer, indexer, indexer));
            String builder = "";
            for (int i = 0; i < charLength; i++) {
                builder+= (char) Methods.randomInt(97, 122);
            }
            title.setText(builder);
        } else {
            title.setText(LoginMenu.textTitles[(indexer == 50 ? 1 : 0)]);
        }
    }
    
    public QLogIn() {
        initComponents();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        right = new javax.swing.JPanel();
        jSeparator1 = new javax.swing.JSeparator();
        jSeparator2 = new javax.swing.JSeparator();
        title = new javax.swing.JLabel();
        entername = new javax.swing.JTextField();
        jSeparator3 = new javax.swing.JSeparator();
        begin = new javax.swing.JButton();
        imBack = new javax.swing.JPanel();
        logo = new javax.swing.JLabel();

        setBackground(new java.awt.Color(255, 255, 255));

        right.setBackground(new java.awt.Color(255, 255, 255));

        jSeparator1.setOrientation(javax.swing.SwingConstants.VERTICAL);

        jSeparator2.setOrientation(javax.swing.SwingConstants.VERTICAL);

        title.setFont(new java.awt.Font("Agency FB", 1, 48)); // NOI18N
        title.setForeground(new java.awt.Color(204, 0, 0));
        title.setText("Username");

        entername.setForeground(new java.awt.Color(153, 153, 153));
        entername.setText("Enter username here");
        entername.setBorder(null);

        begin.setBackground(new java.awt.Color(255, 255, 255));
        begin.setFont(new java.awt.Font("Agency FB", 1, 36)); // NOI18N
        begin.setForeground(new java.awt.Color(204, 0, 0));
        begin.setMnemonic('\r');
        begin.setText("Get Started");
        begin.setEnabled(false);
        begin.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                beginActionPerformed(evt);
            }
        });

        imBack.setOpaque(false);

        logo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/economysimulation/resources/logos/logo-gif.gif"))); // NOI18N

        javax.swing.GroupLayout imBackLayout = new javax.swing.GroupLayout(imBack);
        imBack.setLayout(imBackLayout);
        imBackLayout.setHorizontalGroup(
            imBackLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(logo, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        imBackLayout.setVerticalGroup(
            imBackLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(logo, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout rightLayout = new javax.swing.GroupLayout(right);
        right.setLayout(rightLayout);
        rightLayout.setHorizontalGroup(
            rightLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, rightLayout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(rightLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(rightLayout.createSequentialGroup()
                        .addGap(19, 19, 19)
                        .addGroup(rightLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(jSeparator3)
                            .addComponent(entername, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(title, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(begin, javax.swing.GroupLayout.DEFAULT_SIZE, 500, Short.MAX_VALUE)))
                    .addGroup(rightLayout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(imBack, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 19, Short.MAX_VALUE)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(28, 28, 28))
        );
        rightLayout.setVerticalGroup(
            rightLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, rightLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(rightLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(rightLayout.createSequentialGroup()
                        .addComponent(imBack, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(title, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(entername, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jSeparator3, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(begin, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(rightLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, 700, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jSeparator1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 700, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(45, 45, 45))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(right, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(right, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents

    private void beginActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_beginActionPerformed
        Methods.username = Methods.generateRandomUsername(entername.getText());
        
        start = true;
        imBack.remove(logo);
        right.remove(begin);
        right.remove(entername);
        
        Methods.addToFrontPanel(imBack, new QSelectButtons(), false);
    }//GEN-LAST:event_beginActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    public static javax.swing.JButton begin;
    public static javax.swing.JTextField entername;
    public static javax.swing.JPanel imBack;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JSeparator jSeparator3;
    private javax.swing.JLabel logo;
    public static javax.swing.JPanel right;
    public static javax.swing.JLabel title;
    // End of variables declaration//GEN-END:variables
}
