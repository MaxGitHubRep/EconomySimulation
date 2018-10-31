package economysimulation.classes.gui.coop;

import economysimulation.classes.global.Methods;
import static economysimulation.classes.global.Methods.ThemeManager;
import economysimulation.classes.managers.popup.hint.HintManager;
import economysimulation.classes.managers.popup.hint.Hints;
import economysimulation.classes.managers.theme.GraphicUpdater;
import economysimulation.classes.managers.theme.ThemeUpdateEvent;
import economysimulation.classes.managers.ui.Format;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 *
 * @author Max Carter
 */
public class ServerSlot extends javax.swing.JPanel implements ThemeUpdateEvent {

    /** Unique ID of the server slot. */
    private int ServerId = 0;
    
    /** Text that is used to display a player slot that is not occupied. */
    private static final String EMPTY_TEXT = "#empty#";
    
    
    /** Maximum amount of players that can be on one team. */
    public static final int MAX_PLAYERS = 0,

    /** Minimum amount of players that can be on one team. */
    MIN_PLAYERS = 2,
    
    /** Maximum amount of teams that can be created at once. */
    MAX_TEAMS = 5;
    
    private JPanel[] backPanels, colorPanels;
    private JLabel[] titleLabels;
    
    public List<String> UsersInSlot;
    
    /**
     * Creates new individual display that associates with a server slot.
     * 
     * @param ServerId ID of the server slot.
     */
    public ServerSlot(int ServerId) {
        initComponents();
        
        UsersInSlot = new ArrayList<>();
        
        backPanels = new JPanel[]{ back1 };
        colorPanels = new JPanel[]{ color1 };
        
        titleLabels = new JLabel[]{ display1 };
        
        for (int i = 0; i < backPanels.length; i++) {
            Format.addButtonFormat(backPanels[i], colorPanels[i]);
        }
        
        this.ServerId = ServerId;
        serverIdTitle.setText("SERVER " + ServerId);
        
        ThemeManager.addThemeUpdateListener(this);
    }

    public void joinServerSlot() {
        if (UsersInSlot.size() == MAX_PLAYERS) {
            HintManager.createHint(Hints.ServerSlotFull);
        } else {
            UsersInSlot.add(Methods.Username);
        }
    }
    
    @Override
    public void updateThemeEvent(GraphicUpdater updater) {
        updater.applyPanelThemes(new JPanel[]{ this, back1, color1 }, null);
        updater.applyTextThemes(new JLabel[]{ display1, playerListTitle, serverIdTitle }, null);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        serverIdTitle = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        playerListTitle = new javax.swing.JLabel();
        back1 = new javax.swing.JPanel();
        color1 = new javax.swing.JPanel();
        display1 = new javax.swing.JLabel();

        setBackground(new java.awt.Color(255, 255, 255));

        serverIdTitle.setFont(new java.awt.Font("Agency FB", 0, 48)); // NOI18N
        serverIdTitle.setForeground(new java.awt.Color(204, 0, 0));
        serverIdTitle.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        serverIdTitle.setText("SERVER [id]");

        playerListTitle.setFont(new java.awt.Font("Agency FB", 0, 24)); // NOI18N
        playerListTitle.setForeground(new java.awt.Color(204, 0, 0));
        playerListTitle.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        playerListTitle.setText("Player List");

        back1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

        color1.setBackground(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout color1Layout = new javax.swing.GroupLayout(color1);
        color1.setLayout(color1Layout);
        color1Layout.setHorizontalGroup(
            color1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 40, Short.MAX_VALUE)
        );
        color1Layout.setVerticalGroup(
            color1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        display1.setFont(new java.awt.Font("Agency FB", 0, 30)); // NOI18N
        display1.setForeground(new java.awt.Color(204, 0, 0));
        display1.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        display1.setText("#empty#");

        javax.swing.GroupLayout back1Layout = new javax.swing.GroupLayout(back1);
        back1.setLayout(back1Layout);
        back1Layout.setHorizontalGroup(
            back1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(back1Layout.createSequentialGroup()
                .addComponent(color1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(display1, javax.swing.GroupLayout.DEFAULT_SIZE, 184, Short.MAX_VALUE)
                .addContainerGap())
        );
        back1Layout.setVerticalGroup(
            back1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(color1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, back1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(display1, javax.swing.GroupLayout.DEFAULT_SIZE, 48, Short.MAX_VALUE)
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(serverIdTitle, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jSeparator1)
                        .addContainerGap())
                    .addComponent(playerListTitle, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(0, 58, Short.MAX_VALUE)
                        .addComponent(back1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(serverIdTitle, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(2, 2, 2)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(playerListTitle)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(back1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(475, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel back1;
    private javax.swing.JPanel color1;
    private javax.swing.JLabel display1;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JLabel playerListTitle;
    private javax.swing.JLabel serverIdTitle;
    // End of variables declaration//GEN-END:variables
}
