package economysimulation.classes.gui.coop;

import economysimulation.classes.global.Methods;
import economysimulation.classes.global.User;
import economysimulation.classes.managers.extcon.lobby.LobbyConnector;
import economysimulation.classes.managers.extcon.lobby.UserHold;
import economysimulation.classes.managers.theme.GraphicUpdater;
import economysimulation.classes.managers.theme.ThemeUpdateEvent;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JPanel;

/**
 * @author Max Carter
 */
public class TeammateFinder extends javax.swing.JPanel implements ThemeUpdateEvent {

    private ControlPanel teammateController = null;
    
    public final int HEIGHT = 600, SPEED = 1;
    public int START = 0, plus = 0;
    
    private volatile boolean ready = false;
    
    private Thread animationThread;
    
    /**
     * Creates new form TeammateFinder
     */
    public TeammateFinder() {
        initComponents();
        START = 870;
        
        teammateController = new ControlPanel(this);
        Methods.LobbyHandler = new LobbyConnector(teammateController, this);
        
        Methods.LobbyHandler.addCoopUser(Methods.getUser().getID());
        
        Methods.LobbyHandler.startLoop();
        
        add(teammateController);
        teammateController.setSize(1800, 600);
        teammateController.setLocation(0, START);
        
        Methods.LobbyHandler.startLoop();
        
    }
    
    /** Displays all users that are not in a party */
    public void onLobbyUpdateEvent() {
        List<User> freshLonelyUsers = Methods.LobbyHandler.getUsersNotInParty(), toAddList = new ArrayList<>();
        for (User user : freshLonelyUsers) {
            if (user.getID() != Methods.getUser().getID()) toAddList.add(user);
        }
        addUsersToLobby(toAddList);
    }
    
    private void addUsersToLobby(List<User> users) {
        for (User user : users) {
            UserHold hold = new UserHold(user);
            add(hold);
            hold.setLocation(Methods.randomInt(0, 500), Methods.randomInt(0, 500));
        }
    }
    
    public void openControlPanel() {
        teammateController.arrowState(false);
        moveControlPanel(true);
    }
    
    public void closeControlPanel() {
        teammateController.arrowState(true);
        moveControlPanel(false);
    }
    
    private void moveControlPanel(boolean up) {
        if (Methods.MemorySaver) {
            teammateController.setLocation(0, teammateController.getY() + (up ? - 470 : 470));
            return;
        }
        
        animationThread = new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 470; i++) {
                    teammateController.setLocation(0, teammateController.getY() + (up ? -1 : 1));
                    
                    try {
                        Thread.sleep(SPEED);
                    } catch (InterruptedException ex) {
                        ex.printStackTrace();
                    }
                }
            }
        });
        animationThread.start();
    }
    
    public ControlPanel getTeammateController() {
        return teammateController;
    }
    
    public synchronized boolean isReady() {
        return ready;
    }
    
    public void setGameReadyState(boolean state) {
        if (ready == state) return;
        ready = state;
        Methods.LobbyHandler.setGameReadyState(Methods.getUser().getID(), state);
    }

    @Override
    public void onThemeUpdate(GraphicUpdater updater) {
        updater.applyPanelThemes(new JPanel[]{}, null);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        countdownPanel = new javax.swing.JPanel();

        setBackground(new java.awt.Color(204, 204, 204));

        javax.swing.GroupLayout countdownPanelLayout = new javax.swing.GroupLayout(countdownPanel);
        countdownPanel.setLayout(countdownPanelLayout);
        countdownPanelLayout.setHorizontalGroup(
            countdownPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 200, Short.MAX_VALUE)
        );
        countdownPanelLayout.setVerticalGroup(
            countdownPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 200, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(0, 1600, Short.MAX_VALUE)
                .addComponent(countdownPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(countdownPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 700, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel countdownPanel;
    // End of variables declaration//GEN-END:variables
}
