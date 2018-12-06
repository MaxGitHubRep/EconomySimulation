package economysimulation.classes.gui.coop;

import economysimulation.classes.global.Methods;
import static economysimulation.classes.global.Methods.ThemeManager;
import economysimulation.classes.gui.startup.PreSetup;
import economysimulation.classes.managers.popup.hint.HintManager;
import economysimulation.classes.managers.popup.hint.Hints;
import economysimulation.classes.managers.theme.GraphicUpdater;
import economysimulation.classes.managers.theme.ThemeUpdateEvent;
import economysimulation.classes.managers.ui.Format;
import java.sql.SQLException;
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
    private int ServerId = 0, UserInSlotId = -1;
    
    /** Text that is used to display a player slot that is not occupied. */
    private static final String EMPTY_TEXT = "#empty#";
    
    /** Maximum amount of players that can be on one team. */
    public static final int MAX_PLAYERS = 4,

    /** Minimum amount of players that can be on one team. */
    MIN_PLAYERS = 2;
    
    /** List of server slot states. */
    public static final String[] SERVER_STATES = new String[]{
        "Status", "Lobby", "In Game", "End Game"
    };
    
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
        
        backPanels = new JPanel[]{ back1, back2, back3, back4, back5, back6, back7 };
        colorPanels = new JPanel[]{ color1, color2, color3, color4, color5, color6, color7 };
        
        titleLabels = new JLabel[]{ display1, display2, display3, display4, display5, display6, display7 };
        
        for (int i = 0; i < backPanels.length; i++) {
            Format.addButtonFormat(backPanels[i], colorPanels[i]);
        }
        
        this.ServerId = ServerId;
        serverIdTitle.setText("PARTY " + ServerId);
        
        ThemeManager.addThemeUpdateListener(this);
    }

    /** Adds the current user to the server slot. */
    public void joinServerSlot() {
        if (UsersInSlot.size() == MAX_PLAYERS) {
            HintManager.createHint(Hints.ServerSlotFull);
        } else {
            UserInSlotId = UsersInSlot.size();
            UsersInSlot.add(Methods.Username);
            titleLabels[UserInSlotId].setText(Methods.Username);
            display6.setText("Leave Channel");
            //TODO replace list with putting people into database
            // ^ usernames will need to be generated beforehand. -> local session user ids?
        }
    }
    
    /** Removes the current user from the server slot. */
    public void leaveServerSlot() {
        if (UserInSlotId > -1) {
            titleLabels[UserInSlotId].setText(EMPTY_TEXT);
            UsersInSlot.remove(UserInSlotId);
            display6.setText("Join Channel");
            UserInSlotId = -1;
            //TODO remove user from database
            // remove local session user id from database
        }
    }
    
    /**
     * The unique ID that is attached to the server slot.
     * @return ID of the server.
     */
    public int getServerSlotID() {
        return ServerId;
    }
    
    @Override
    public void updateThemeEvent(GraphicUpdater updater) {
        updater.applyPanelThemes(new JPanel[]{ this, back1, back2, back3, back4, back5, back6, back7, color1, color2, color3, color4, color5, color6, color7 }, null);
        updater.applyTextThemes(new JLabel[]{ display1, display2, display3, display4, display5, display6, display7 , playerListTitle, serverIdTitle, channelOptionsTitle }, null);
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
        back2 = new javax.swing.JPanel();
        color2 = new javax.swing.JPanel();
        display2 = new javax.swing.JLabel();
        back3 = new javax.swing.JPanel();
        color3 = new javax.swing.JPanel();
        display3 = new javax.swing.JLabel();
        back4 = new javax.swing.JPanel();
        color4 = new javax.swing.JPanel();
        display4 = new javax.swing.JLabel();
        jSeparator2 = new javax.swing.JSeparator();
        channelOptionsTitle = new javax.swing.JLabel();
        back5 = new javax.swing.JPanel();
        color5 = new javax.swing.JPanel();
        display5 = new javax.swing.JLabel();
        jSeparator3 = new javax.swing.JSeparator();
        back6 = new javax.swing.JPanel();
        color6 = new javax.swing.JPanel();
        display6 = new javax.swing.JLabel();
        back7 = new javax.swing.JPanel();
        color7 = new javax.swing.JPanel();
        display7 = new javax.swing.JLabel();
        jSeparator4 = new javax.swing.JSeparator();

        setBackground(new java.awt.Color(255, 255, 255));

        serverIdTitle.setFont(new java.awt.Font("Agency FB", 0, 48)); // NOI18N
        serverIdTitle.setForeground(new java.awt.Color(204, 0, 0));
        serverIdTitle.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        serverIdTitle.setText("PARTY [id]");

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

        back2.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

        color2.setBackground(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout color2Layout = new javax.swing.GroupLayout(color2);
        color2.setLayout(color2Layout);
        color2Layout.setHorizontalGroup(
            color2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 40, Short.MAX_VALUE)
        );
        color2Layout.setVerticalGroup(
            color2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        display2.setFont(new java.awt.Font("Agency FB", 0, 30)); // NOI18N
        display2.setForeground(new java.awt.Color(204, 0, 0));
        display2.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        display2.setText("#empty#");

        javax.swing.GroupLayout back2Layout = new javax.swing.GroupLayout(back2);
        back2.setLayout(back2Layout);
        back2Layout.setHorizontalGroup(
            back2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(back2Layout.createSequentialGroup()
                .addComponent(color2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(display2, javax.swing.GroupLayout.DEFAULT_SIZE, 184, Short.MAX_VALUE)
                .addContainerGap())
        );
        back2Layout.setVerticalGroup(
            back2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(color2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, back2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(display2, javax.swing.GroupLayout.DEFAULT_SIZE, 48, Short.MAX_VALUE)
                .addContainerGap())
        );

        back3.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

        color3.setBackground(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout color3Layout = new javax.swing.GroupLayout(color3);
        color3.setLayout(color3Layout);
        color3Layout.setHorizontalGroup(
            color3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 40, Short.MAX_VALUE)
        );
        color3Layout.setVerticalGroup(
            color3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        display3.setFont(new java.awt.Font("Agency FB", 0, 30)); // NOI18N
        display3.setForeground(new java.awt.Color(204, 0, 0));
        display3.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        display3.setText("#empty#");

        javax.swing.GroupLayout back3Layout = new javax.swing.GroupLayout(back3);
        back3.setLayout(back3Layout);
        back3Layout.setHorizontalGroup(
            back3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(back3Layout.createSequentialGroup()
                .addComponent(color3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(display3, javax.swing.GroupLayout.DEFAULT_SIZE, 184, Short.MAX_VALUE)
                .addContainerGap())
        );
        back3Layout.setVerticalGroup(
            back3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(color3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, back3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(display3, javax.swing.GroupLayout.DEFAULT_SIZE, 48, Short.MAX_VALUE)
                .addContainerGap())
        );

        back4.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

        color4.setBackground(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout color4Layout = new javax.swing.GroupLayout(color4);
        color4.setLayout(color4Layout);
        color4Layout.setHorizontalGroup(
            color4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 40, Short.MAX_VALUE)
        );
        color4Layout.setVerticalGroup(
            color4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        display4.setFont(new java.awt.Font("Agency FB", 0, 30)); // NOI18N
        display4.setForeground(new java.awt.Color(204, 0, 0));
        display4.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        display4.setText("#empty#");

        javax.swing.GroupLayout back4Layout = new javax.swing.GroupLayout(back4);
        back4.setLayout(back4Layout);
        back4Layout.setHorizontalGroup(
            back4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(back4Layout.createSequentialGroup()
                .addComponent(color4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(display4, javax.swing.GroupLayout.DEFAULT_SIZE, 184, Short.MAX_VALUE)
                .addContainerGap())
        );
        back4Layout.setVerticalGroup(
            back4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(color4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, back4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(display4, javax.swing.GroupLayout.DEFAULT_SIZE, 48, Short.MAX_VALUE)
                .addContainerGap())
        );

        channelOptionsTitle.setFont(new java.awt.Font("Agency FB", 0, 24)); // NOI18N
        channelOptionsTitle.setForeground(new java.awt.Color(204, 0, 0));
        channelOptionsTitle.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        channelOptionsTitle.setText("Party Options");

        back5.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        back5.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                back5MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                back5MouseExited(evt);
            }
        });

        color5.setBackground(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout color5Layout = new javax.swing.GroupLayout(color5);
        color5.setLayout(color5Layout);
        color5Layout.setHorizontalGroup(
            color5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 40, Short.MAX_VALUE)
        );
        color5Layout.setVerticalGroup(
            color5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        display5.setFont(new java.awt.Font("Agency FB", 0, 30)); // NOI18N
        display5.setForeground(new java.awt.Color(204, 0, 0));
        display5.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        display5.setText("Status");

        javax.swing.GroupLayout back5Layout = new javax.swing.GroupLayout(back5);
        back5.setLayout(back5Layout);
        back5Layout.setHorizontalGroup(
            back5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(back5Layout.createSequentialGroup()
                .addComponent(color5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(display5, javax.swing.GroupLayout.DEFAULT_SIZE, 210, Short.MAX_VALUE)
                .addContainerGap())
        );
        back5Layout.setVerticalGroup(
            back5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(color5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, back5Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(display5, javax.swing.GroupLayout.DEFAULT_SIZE, 48, Short.MAX_VALUE)
                .addContainerGap())
        );

        back6.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        back6.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                back6MouseClicked(evt);
            }
        });

        color6.setBackground(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout color6Layout = new javax.swing.GroupLayout(color6);
        color6.setLayout(color6Layout);
        color6Layout.setHorizontalGroup(
            color6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 40, Short.MAX_VALUE)
        );
        color6Layout.setVerticalGroup(
            color6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        display6.setFont(new java.awt.Font("Agency FB", 0, 30)); // NOI18N
        display6.setForeground(new java.awt.Color(204, 0, 0));
        display6.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        display6.setText("Join Channel");

        javax.swing.GroupLayout back6Layout = new javax.swing.GroupLayout(back6);
        back6.setLayout(back6Layout);
        back6Layout.setHorizontalGroup(
            back6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(back6Layout.createSequentialGroup()
                .addComponent(color6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(display6, javax.swing.GroupLayout.DEFAULT_SIZE, 213, Short.MAX_VALUE)
                .addContainerGap())
        );
        back6Layout.setVerticalGroup(
            back6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(color6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, back6Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(display6, javax.swing.GroupLayout.DEFAULT_SIZE, 48, Short.MAX_VALUE)
                .addContainerGap())
        );

        back7.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        back7.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                back7MouseClicked(evt);
            }
        });

        color7.setBackground(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout color7Layout = new javax.swing.GroupLayout(color7);
        color7.setLayout(color7Layout);
        color7Layout.setHorizontalGroup(
            color7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 40, Short.MAX_VALUE)
        );
        color7Layout.setVerticalGroup(
            color7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        display7.setFont(new java.awt.Font("Agency FB", 0, 30)); // NOI18N
        display7.setForeground(new java.awt.Color(204, 0, 0));
        display7.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        display7.setText("Launch Simulation");

        javax.swing.GroupLayout back7Layout = new javax.swing.GroupLayout(back7);
        back7.setLayout(back7Layout);
        back7Layout.setHorizontalGroup(
            back7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(back7Layout.createSequentialGroup()
                .addComponent(color7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(display7, javax.swing.GroupLayout.DEFAULT_SIZE, 213, Short.MAX_VALUE)
                .addContainerGap())
        );
        back7Layout.setVerticalGroup(
            back7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(color7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, back7Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(display7, javax.swing.GroupLayout.DEFAULT_SIZE, 48, Short.MAX_VALUE)
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
                    .addComponent(playerListTitle, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(0, 29, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(back1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(back2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(back3, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(back4, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(back5, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(back6, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(back7, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jSeparator3)
                            .addComponent(jSeparator1)
                            .addComponent(jSeparator2, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(channelOptionsTitle, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jSeparator4, javax.swing.GroupLayout.Alignment.TRAILING))
                        .addContainerGap())))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(serverIdTitle)
                .addGap(10, 10, 10)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(5, 5, 5)
                .addComponent(back5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(14, 14, 14)
                .addComponent(jSeparator3, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(playerListTitle)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(back1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(back2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(back3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(back4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(20, 20, 20)
                .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(channelOptionsTitle)
                .addGap(18, 18, 18)
                .addComponent(back6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(back7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 23, Short.MAX_VALUE)
                .addComponent(jSeparator4, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    private void back6MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_back6MouseClicked
        if (UserInSlotId == -1) {
            joinServerSlot();
        } else {
            leaveServerSlot();
        }
    }//GEN-LAST:event_back6MouseClicked

    private void back5MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_back5MouseEntered
        try {
            display5.setText(Methods.StorageConnection.getServerState(ServerId).getTextState());
        } catch (SQLException ex) {
            HintManager.createHint(Hints.NotConnected);
            ex.printStackTrace();
        }
    }//GEN-LAST:event_back5MouseEntered

    private void back5MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_back5MouseExited
        display5.setText(SERVER_STATES[0]);
    }//GEN-LAST:event_back5MouseExited

    private void back7MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_back7MouseClicked
        if (UserInSlotId == 0) {
            Methods.MPServerSlot = ServerId;
            Methods.UserInSlot = UserInSlotId;
            //TODO may need to develop socket system to connect users
            //signal to other players to start simulation
            
            Methods.FrameDisplay.addToMainFrame(new PreSetup());
        } else {
            HintManager.createHint(Hints.NotPartyLeader);
        }
    }//GEN-LAST:event_back7MouseClicked


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel back1;
    private javax.swing.JPanel back2;
    private javax.swing.JPanel back3;
    private javax.swing.JPanel back4;
    private javax.swing.JPanel back5;
    private javax.swing.JPanel back6;
    private javax.swing.JPanel back7;
    private javax.swing.JLabel channelOptionsTitle;
    private javax.swing.JPanel color1;
    private javax.swing.JPanel color2;
    private javax.swing.JPanel color3;
    private javax.swing.JPanel color4;
    private javax.swing.JPanel color5;
    private javax.swing.JPanel color6;
    private javax.swing.JPanel color7;
    private javax.swing.JLabel display1;
    private javax.swing.JLabel display2;
    private javax.swing.JLabel display3;
    private javax.swing.JLabel display4;
    private javax.swing.JLabel display5;
    private javax.swing.JLabel display6;
    private javax.swing.JLabel display7;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JSeparator jSeparator3;
    private javax.swing.JSeparator jSeparator4;
    private javax.swing.JLabel playerListTitle;
    private javax.swing.JLabel serverIdTitle;
    // End of variables declaration//GEN-END:variables
}
