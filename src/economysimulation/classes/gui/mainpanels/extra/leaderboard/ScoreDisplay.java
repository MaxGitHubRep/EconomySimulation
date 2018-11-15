package economysimulation.classes.gui.mainpanels.extra.leaderboard;

import economysimulation.classes.global.Methods;
import static economysimulation.classes.global.Methods.ThemeManager;
import economysimulation.classes.managers.extcon.GamePackage;
import economysimulation.classes.managers.theme.GraphicUpdater;
import economysimulation.classes.managers.theme.ThemeUpdateEvent;
import economysimulation.classes.managers.ui.Format;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 *
 * @author Max Carter
 */
public class ScoreDisplay extends javax.swing.JPanel implements ThemeUpdateEvent {

    private int gameid;
    private GamePackage pkg;
    
    public ScoreDisplay() {
        initComponents();
    }
    
    public void setDisplayData(int rank, GamePackage pkg) {
        
        this.pkg = pkg;
        if (pkg != null) {
            this.gameid = pkg.getID();
            rankDisplay.setText("#" + rank);
            scoreDisplay.setText(pkg.getScore() + "");
            String builder = "";
            for (String player : pkg.getPlayers()) {
                builder += player + " ";
            }  
            
            playersDisplay.setText("<html>" + builder + "</html>");
            
        } else {
            this.gameid = -1;
            for (JLabel label : new JLabel[]{ rankDisplay, scoreDisplay, playersDisplay }) {
                label.setText(" - ");
            }
        }
        
        
        applyButtonListener(this);
        Format.addButtonFormat(this, null);
        ThemeManager.addThemeUpdateListener(this);
    }
    
    private void applyButtonListener(JPanel panel) {
        panel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (pkg != null) Methods.LBDisplay.onScoreHoverListener(gameid, pkg);
            }
        });
    }

    @Override
    public void updateThemeEvent(GraphicUpdater updater) {
        updater.applyPanelThemes(new JPanel[]{ this }, null);
        updater.applyTextThemes(new JLabel[]{ rankDisplay, scoreDisplay, playersDisplay }, null);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        rankDisplay = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        jSeparator2 = new javax.swing.JSeparator();
        scoreDisplay = new javax.swing.JLabel();
        playersDisplay = new javax.swing.JLabel();

        setBackground(new java.awt.Color(255, 255, 255));
        setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

        rankDisplay.setFont(new java.awt.Font("Agency FB", 0, 48)); // NOI18N
        rankDisplay.setForeground(new java.awt.Color(204, 0, 0));
        rankDisplay.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        rankDisplay.setText("#-");

        jSeparator1.setOrientation(javax.swing.SwingConstants.VERTICAL);

        jSeparator2.setOrientation(javax.swing.SwingConstants.VERTICAL);

        scoreDisplay.setFont(new java.awt.Font("Agency FB", 0, 48)); // NOI18N
        scoreDisplay.setForeground(new java.awt.Color(204, 0, 0));
        scoreDisplay.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        scoreDisplay.setText("-");

        playersDisplay.setFont(new java.awt.Font("Agency FB", 0, 22)); // NOI18N
        playersDisplay.setForeground(new java.awt.Color(204, 0, 0));
        playersDisplay.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        playersDisplay.setText("-");
        playersDisplay.setVerticalAlignment(javax.swing.SwingConstants.TOP);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(rankDisplay, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 13, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(scoreDisplay, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, 13, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(playersDisplay, javax.swing.GroupLayout.DEFAULT_SIZE, 482, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(rankDisplay, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jSeparator1)
                    .addComponent(jSeparator2)
                    .addComponent(scoreDisplay, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(playersDisplay, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JLabel playersDisplay;
    private javax.swing.JLabel rankDisplay;
    private javax.swing.JLabel scoreDisplay;
    // End of variables declaration//GEN-END:variables
}
