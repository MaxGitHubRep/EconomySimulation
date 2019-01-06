package economysimulation.classes.gui.coop;

import economysimulation.classes.global.Methods;
import static economysimulation.classes.global.Methods.ThemeHandler;
import economysimulation.classes.managers.comp.list.ScrollableList;
import economysimulation.classes.managers.extcon.lobby.LobbyUpdateEvent;
import economysimulation.classes.managers.extcon.lobby.PartyInvite;
import economysimulation.classes.managers.theme.GraphicUpdater;
import economysimulation.classes.managers.theme.ThemeUpdateEvent;
import economysimulation.classes.managers.ui.Format;
import java.util.List;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 *
 * @author Max Carter
 */
public class ControlPanel extends javax.swing.JPanel implements ThemeUpdateEvent, LobbyUpdateEvent {

    private ScrollableList inviteList = null;
    private final String EMPTY_USER = "-";
    private PartyInvite latestPartyInvite = null;
    
    int partyId = 0;
    
    /**
     * Creates new form ControlPanel
     */
    public ControlPanel() {
        initComponents();
        
        ThemeHandler.addThemeUpdateListener(this);
        
        Format.addButtonFormat(back1, color1);
        Format.addButtonFormat(back2, color2);
        
        inviteList = new ScrollableList(EMPTY_USER);
        inviteList.addItem("test user 1");
        inviteList.addItem("test user 2");
        inviteList.addItem("test user 3");
        inviteList.addItem("test user 4");
        inviteList.addItem("test user 5");
        inviteList.updateList();
        Methods.addToFrontPanel(panelInvites, inviteList, false);
        
    }
    
    public int getPartyID() {
        return partyId;
    }
    
    /**
     * Changes the arrow text of the controller.
     * 
     * @param up If set to true, the arrow will face up.
     */
    public void arrowState(boolean up) {
        buttonState.setText(up ? "^" : "-");
    }
    
    @Override
    public void onPartyInviteEvent(PartyInvite partyInvite) {
        this.latestPartyInvite = partyInvite;
        inviteList.addItem(partyInvite.getUser() + "#" + partyInvite.getUserID());
    }
    
    public void onPartyUpdateEvent(List<String> users) {
        //update table to show other users in party
    }
    
    private void inviteOutcome(boolean join) {
        if (inviteList.getList().isEmpty()) return;
        
        // <-- another check to see if not in party already
        
        if (join) {
            latestPartyInvite.accept();
        } else {
            inviteList.removeItem(0);
            inviteList.updateList();
            latestPartyInvite.ignore();
        }
    }
    
    @Override
    public void onThemeUpdate(GraphicUpdater updater) {
        updater.applyPanelThemes(new JPanel[]{ this, back1, back2, color1, color2 }, null);
        updater.applyTextThemes(new JLabel[]{ buttonState, label1, label2 }, null);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonState = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        back1 = new javax.swing.JPanel();
        color1 = new javax.swing.JPanel();
        label1 = new javax.swing.JLabel();
        back2 = new javax.swing.JPanel();
        color2 = new javax.swing.JPanel();
        label2 = new javax.swing.JLabel();
        panelInvites = new javax.swing.JPanel();
        joinLabel = new javax.swing.JLabel();
        ignoreLabel = new javax.swing.JLabel();
        arrow = new javax.swing.JLabel();
        back3 = new javax.swing.JPanel();
        color3 = new javax.swing.JPanel();
        label3 = new javax.swing.JLabel();

        setBackground(new java.awt.Color(255, 255, 255));

        buttonState.setFont(new java.awt.Font("Agency FB", 0, 100)); // NOI18N
        buttonState.setForeground(new java.awt.Color(204, 0, 0));
        buttonState.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        buttonState.setText("^");
        buttonState.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                buttonStateMouseClicked(evt);
            }
        });

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/economysimulation/resources/logos/border280.png"))); // NOI18N

        back1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        back1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                back1MouseClicked(evt);
            }
        });

        color1.setBackground(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout color1Layout = new javax.swing.GroupLayout(color1);
        color1.setLayout(color1Layout);
        color1Layout.setHorizontalGroup(
            color1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        color1Layout.setVerticalGroup(
            color1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 20, Short.MAX_VALUE)
        );

        label1.setFont(new java.awt.Font("Agency FB", 0, 36)); // NOI18N
        label1.setForeground(new java.awt.Color(204, 0, 0));
        label1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        label1.setText("Main Menu");

        javax.swing.GroupLayout back1Layout = new javax.swing.GroupLayout(back1);
        back1.setLayout(back1Layout);
        back1Layout.setHorizontalGroup(
            back1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(color1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(back1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(label1, javax.swing.GroupLayout.DEFAULT_SIZE, 198, Short.MAX_VALUE)
                .addContainerGap())
        );
        back1Layout.setVerticalGroup(
            back1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, back1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(label1, javax.swing.GroupLayout.DEFAULT_SIZE, 58, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(color1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        back2.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        back2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                back2MouseClicked(evt);
            }
        });

        color2.setBackground(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout color2Layout = new javax.swing.GroupLayout(color2);
        color2.setLayout(color2Layout);
        color2Layout.setHorizontalGroup(
            color2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        color2Layout.setVerticalGroup(
            color2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 20, Short.MAX_VALUE)
        );

        label2.setFont(new java.awt.Font("Agency FB", 0, 36)); // NOI18N
        label2.setForeground(new java.awt.Color(204, 0, 0));
        label2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        label2.setText("Quit to Desktop");

        javax.swing.GroupLayout back2Layout = new javax.swing.GroupLayout(back2);
        back2.setLayout(back2Layout);
        back2Layout.setHorizontalGroup(
            back2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(color2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(back2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(label2, javax.swing.GroupLayout.DEFAULT_SIZE, 198, Short.MAX_VALUE)
                .addContainerGap())
        );
        back2Layout.setVerticalGroup(
            back2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, back2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(label2, javax.swing.GroupLayout.DEFAULT_SIZE, 58, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(color2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        javax.swing.GroupLayout panelInvitesLayout = new javax.swing.GroupLayout(panelInvites);
        panelInvites.setLayout(panelInvitesLayout);
        panelInvitesLayout.setHorizontalGroup(
            panelInvitesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 472, Short.MAX_VALUE)
        );
        panelInvitesLayout.setVerticalGroup(
            panelInvitesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 457, Short.MAX_VALUE)
        );

        joinLabel.setFont(new java.awt.Font("Agency FB", 0, 28)); // NOI18N
        joinLabel.setForeground(new java.awt.Color(204, 0, 0));
        joinLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        joinLabel.setText("Join?");
        joinLabel.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        joinLabel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                joinLabelMouseClicked(evt);
            }
        });

        ignoreLabel.setFont(new java.awt.Font("Agency FB", 0, 28)); // NOI18N
        ignoreLabel.setForeground(new java.awt.Color(204, 0, 0));
        ignoreLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        ignoreLabel.setText("Ignore?");
        ignoreLabel.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        ignoreLabel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                ignoreLabelMouseClicked(evt);
            }
        });

        arrow.setFont(new java.awt.Font("Agency FB", 0, 48)); // NOI18N
        arrow.setForeground(new java.awt.Color(204, 0, 0));
        arrow.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        arrow.setText("<");

        back3.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        back3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                back3MouseClicked(evt);
            }
        });

        color3.setBackground(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout color3Layout = new javax.swing.GroupLayout(color3);
        color3.setLayout(color3Layout);
        color3Layout.setHorizontalGroup(
            color3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        color3Layout.setVerticalGroup(
            color3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 20, Short.MAX_VALUE)
        );

        label3.setFont(new java.awt.Font("Agency FB", 0, 52)); // NOI18N
        label3.setForeground(new java.awt.Color(204, 0, 0));
        label3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        label3.setText("READY");

        javax.swing.GroupLayout back3Layout = new javax.swing.GroupLayout(back3);
        back3.setLayout(back3Layout);
        back3Layout.setHorizontalGroup(
            back3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(color3, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(back3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(label3, javax.swing.GroupLayout.DEFAULT_SIZE, 326, Short.MAX_VALUE)
                .addContainerGap())
        );
        back3Layout.setVerticalGroup(
            back3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, back3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(label3, javax.swing.GroupLayout.DEFAULT_SIZE, 85, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(color3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1)
                    .addComponent(panelInvites, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(71, 71, 71)
                                .addComponent(buttonState, javax.swing.GroupLayout.PREFERRED_SIZE, 700, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 107, Short.MAX_VALUE)
                                .addComponent(back1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(back2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(6, 6, 6)
                                .addComponent(arrow, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(joinLabel)
                                .addGap(26, 26, 26)
                                .addComponent(ignoreLabel)
                                .addGap(0, 0, Short.MAX_VALUE)))
                        .addContainerGap())
                    .addGroup(layout.createSequentialGroup()
                        .addGap(352, 352, 352)
                        .addComponent(back3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(buttonState)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel1))
                    .addComponent(back1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(back2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(arrow, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(joinLabel)
                                    .addComponent(ignoreLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addGap(415, 415, 415))
                            .addComponent(panelInvites, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addContainerGap())
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(back3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void buttonStateMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_buttonStateMouseClicked
        if (buttonState.getText().equals("^")) {
            Methods.FindTeammate.openControlPanel();
        } else {
            Methods.FindTeammate.closeControlPanel();
        }
    }//GEN-LAST:event_buttonStateMouseClicked

    private void back2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_back2MouseClicked
        Methods.quitSystem();
    }//GEN-LAST:event_back2MouseClicked

    private void back1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_back1MouseClicked
        Methods.FrameDisplay.addToMainFrame(Methods.IntroPanel);
        System.gc();
    }//GEN-LAST:event_back1MouseClicked

    private void joinLabelMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_joinLabelMouseClicked
        inviteOutcome(true);
    }//GEN-LAST:event_joinLabelMouseClicked

    private void ignoreLabelMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_ignoreLabelMouseClicked
        inviteOutcome(false);
    }//GEN-LAST:event_ignoreLabelMouseClicked

    private void back3MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_back3MouseClicked
        //I AM READY
    }//GEN-LAST:event_back3MouseClicked


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel arrow;
    private javax.swing.JPanel back1;
    private javax.swing.JPanel back2;
    private javax.swing.JPanel back3;
    private javax.swing.JLabel buttonState;
    private javax.swing.JPanel color1;
    private javax.swing.JPanel color2;
    private javax.swing.JPanel color3;
    private javax.swing.JLabel ignoreLabel;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel joinLabel;
    private javax.swing.JLabel label1;
    private javax.swing.JLabel label2;
    private javax.swing.JLabel label3;
    private javax.swing.JPanel panelInvites;
    // End of variables declaration//GEN-END:variables
}
