package economysimulation.classes.gui.startup;

import economysimulation.classes.economy.sectors.Sector;
import economysimulation.classes.global.Methods;
import static economysimulation.classes.global.Methods.ThemeManager;
import economysimulation.classes.gui.coop.PlayerSearch;
import economysimulation.classes.gui.mainpanels.extra.leaderboard.Leaderboard;
import economysimulation.classes.gui.subpanels.TaxRevenueList;
import economysimulation.classes.managers.animation.StockGraph;
import economysimulation.classes.managers.extcon.Connection;
import economysimulation.classes.managers.extcon.DatabaseConnector;
import economysimulation.classes.managers.extcon.GameData;
import economysimulation.classes.managers.extcon.UserData;
import economysimulation.classes.managers.ui.Format;
import economysimulation.classes.managers.theme.GraphicUpdater;
import economysimulation.classes.managers.theme.ThemeUpdateEvent;
import economysimulation.classes.mode.Mode;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.SQLException;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.SwingWorker;

/**
 *
 * @author Max Carter
 */
public class WelcomePanel extends javax.swing.JPanel implements ThemeUpdateEvent {

    /**
    * List of panels that change colour when hovered over.
    */
    private static JPanel[]
            colorPanels,
            backPanels;
    
    /**
     * List of labels used for the buttons.
     */
    private static JLabel[]
            titleLabels;
    
    /**
    * Ghost text applied to text field.
    */
    private static final String
            USERNAME_GHOST_TEXT = "Username";
    
    /**
     * List of titles and descriptions for buttons.
     */
    private static final String[]
            TITLES = new String[]{
        "Solo", "Cooperative", "Tutorial", "Leaderboards" },
            
            DESCS = new String[]{
        "Model an economy on your own",
        "Model an economy with other people. You must be connected to the server for this to work",
        "Get a quick demonstration of how to run the simulation",
        "Check out the highest scores in solo/cooperative simulations"
    };
  
    //<editor-fold defaultstate="collapsed" desc="Constructor."> 
    /**
     * Creates new starter panel.
     */
    public WelcomePanel() {
        initComponents();
        
        backPanels = new JPanel[]{ back1, back2, back3, back4, back5, back6 };
        colorPanels = new JPanel[]{ co1, co2, co3, co4, co5, co6 };
        titleLabels = new JLabel[]{ title1, title2, title3, title4, connectionState, leave };
        
        for (int i = 0; i < backPanels.length; i++) {
            if (i < backPanels.length-2) addPanelHoverEvent(i);
            
            if (i < backPanels.length-4) addButtonSimStartEvent(i);

            Format.addButtonFormat(backPanels[i], colorPanels[i]);
        }
        
        Methods.resetCurrentUserData();
        Format.addGhostText(enterUsername, USERNAME_GHOST_TEXT);
        
        JRadioButton btn = new JRadioButton("removes automatic text box focus");
        sideBarLeft.add(btn);
        
        ThemeManager.addThemeUpdateListener(this);
        Methods.addDraggablePanel(new JPanel[]{ animBack, sideBarLeft });
        Methods.AnimationGraph = new StockGraph(animBack);
        runConnectionTest();
    }//</editor-fold>
    
    private void runConnectionTest() {
        SwingWorker<Void, String> worker = new SwingWorker<Void, String>(){
            @Override
            protected Void doInBackground() throws Exception {
                connectionState.setText("Testing...");
                Thread.sleep(10);
                establishConnection();
                return null;
            }
            
        };
        worker.execute();
    }
    
    private void establishConnection() {
        try {
            Methods.DBConnector = new DatabaseConnector();
            Methods.DBUsers = new UserData();
            Methods.DBGames = new GameData();
            Connection.isConnected = true;
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        connectionState.setText("O" + (Connection.isConnected ? "n" : "ff") + "line");
    }
    
    @Override
    public void updateThemeEvent(GraphicUpdater updater) {
        updater.applyPanelThemes(new JPanel[]{ sideBarLeft, back1, back2, back3, back4, back5, back6, co1, co2, co3, co4, co5, co6 }, new JPanel[]{ animBack });
        updater.applyTextThemes(titleLabels, null);
    }

    private void addButtonSimStartEvent(int id) {
        backPanels[id].addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (enterUsername.getText().equals(USERNAME_GHOST_TEXT)) {
                    setLabelText(id, USERNAME_GHOST_TEXT + " is not a valid username");

                } else if (enterUsername.getText().contains("#")) {    
                    setLabelText(id, "Username cannot contain a #");
                    
                } else if (enterUsername.getText().length() > 10) {
                    setLabelText(id, "Username must be less than " + 10 + " characters to proceed");

                } else if (enterUsername.getText().length() < 3) {
                    setLabelText(id, "Username must be more than " + 3 + " characters to proceed");

                } else if ((id + 1 == Mode.COOP) && !Connection.isConnected) {   
                    setLabelText(id, "A connection cannot be established to the server meaning online play is disabled at this time");
                    
                } else {
                    Methods.Username = enterUsername.getText();
                    Mode.setMode(id+1);
                    Methods.AnimationGraph.stop();
                    Methods.SectorInstance = new Sector();
                    Methods.TaxRevenueDisplay = new TaxRevenueList();
                    
                    if (Mode.isMode(Mode.COOP)) {
                        if (Methods.PlayerSearchDisplay == null) Methods.PlayerSearchDisplay = new PlayerSearch();
                        Methods.FrameDisplay.addToMainFrame(Methods.PlayerSearchDisplay);
                    } else {
                        Methods.FrameDisplay.addToMainFrame(new PreSetup());
                    }
                    
                }
            }

        });
    }
    
    //<editor-fold defaultstate="collapsed" desc="Panel hover event to display button descritpion."> 
    /**
    * Changes the text and font size of the button
    * to display a description of it's purpose.
    * 
    * @param id index of the panel list
    */
    private void addPanelHoverEvent(int id) {
        backPanels[id].addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                titleLabels[id].setText("<html>" + DESCS[id] + ".</html>");
                titleLabels[id].setFont(new Font("Agency FB", 0, 24));
            }

            @Override
            public void mouseExited(MouseEvent e) {
                titleLabels[id].setText(TITLES[id]);
                titleLabels[id].setFont(new Font("Agency FB", 0, 48));
            }

        });
    }//</editor-fold>

    private void setLabelText(int id, String text) {
        titleLabels[id].setText("<html>" + text + ".</html>");
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
        back2 = new javax.swing.JPanel();
        co2 = new javax.swing.JPanel();
        title2 = new javax.swing.JLabel();
        back3 = new javax.swing.JPanel();
        co3 = new javax.swing.JPanel();
        title3 = new javax.swing.JLabel();
        back4 = new javax.swing.JPanel();
        co4 = new javax.swing.JPanel();
        title4 = new javax.swing.JLabel();
        back5 = new javax.swing.JPanel();
        co5 = new javax.swing.JPanel();
        connectionState = new javax.swing.JLabel();
        back6 = new javax.swing.JPanel();
        co6 = new javax.swing.JPanel();
        leave = new javax.swing.JLabel();
        animBack = new javax.swing.JPanel();

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
        enterUsername.setCursor(new java.awt.Cursor(java.awt.Cursor.TEXT_CURSOR));
        enterUsername.setOpaque(false);

        back1.setBackground(new java.awt.Color(255, 255, 255));
        back1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

        co1.setBackground(new java.awt.Color(255, 255, 255));

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
        title1.setText("Solo");
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

        back2.setBackground(new java.awt.Color(255, 255, 255));
        back2.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

        co2.setBackground(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout co2Layout = new javax.swing.GroupLayout(co2);
        co2.setLayout(co2Layout);
        co2Layout.setHorizontalGroup(
            co2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 40, Short.MAX_VALUE)
        );
        co2Layout.setVerticalGroup(
            co2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        title2.setFont(new java.awt.Font("Agency FB", 0, 48)); // NOI18N
        title2.setForeground(new java.awt.Color(204, 0, 0));
        title2.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        title2.setText("Cooperative");
        title2.setVerticalAlignment(javax.swing.SwingConstants.TOP);

        javax.swing.GroupLayout back2Layout = new javax.swing.GroupLayout(back2);
        back2.setLayout(back2Layout);
        back2Layout.setHorizontalGroup(
            back2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(back2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(title2, javax.swing.GroupLayout.DEFAULT_SIZE, 383, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(co2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        back2Layout.setVerticalGroup(
            back2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, back2Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addGroup(back2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(title2, javax.swing.GroupLayout.DEFAULT_SIZE, 60, Short.MAX_VALUE)
                    .addComponent(co2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
        );

        back3.setBackground(new java.awt.Color(255, 255, 255));
        back3.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        back3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                back3MouseClicked(evt);
            }
        });

        co3.setBackground(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout co3Layout = new javax.swing.GroupLayout(co3);
        co3.setLayout(co3Layout);
        co3Layout.setHorizontalGroup(
            co3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 40, Short.MAX_VALUE)
        );
        co3Layout.setVerticalGroup(
            co3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 60, Short.MAX_VALUE)
        );

        title3.setFont(new java.awt.Font("Agency FB", 0, 48)); // NOI18N
        title3.setForeground(new java.awt.Color(204, 0, 0));
        title3.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        title3.setText("Tutorial");
        title3.setVerticalAlignment(javax.swing.SwingConstants.TOP);

        javax.swing.GroupLayout back3Layout = new javax.swing.GroupLayout(back3);
        back3.setLayout(back3Layout);
        back3Layout.setHorizontalGroup(
            back3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(back3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(title3, javax.swing.GroupLayout.DEFAULT_SIZE, 383, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(co3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        back3Layout.setVerticalGroup(
            back3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(back3Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(co3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addComponent(title3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        back4.setBackground(new java.awt.Color(255, 255, 255));
        back4.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        back4.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                back4MouseClicked(evt);
            }
        });

        co4.setBackground(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout co4Layout = new javax.swing.GroupLayout(co4);
        co4.setLayout(co4Layout);
        co4Layout.setHorizontalGroup(
            co4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 40, Short.MAX_VALUE)
        );
        co4Layout.setVerticalGroup(
            co4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 60, Short.MAX_VALUE)
        );

        title4.setFont(new java.awt.Font("Agency FB", 0, 48)); // NOI18N
        title4.setForeground(new java.awt.Color(204, 0, 0));
        title4.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        title4.setText("Leaderboards");
        title4.setVerticalAlignment(javax.swing.SwingConstants.TOP);

        javax.swing.GroupLayout back4Layout = new javax.swing.GroupLayout(back4);
        back4.setLayout(back4Layout);
        back4Layout.setHorizontalGroup(
            back4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(back4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(title4, javax.swing.GroupLayout.DEFAULT_SIZE, 383, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(co4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        back4Layout.setVerticalGroup(
            back4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(back4Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(co4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addComponent(title4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        back5.setBackground(new java.awt.Color(255, 255, 255));
        back5.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        back5.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                back5MouseClicked(evt);
            }
        });

        co5.setBackground(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout co5Layout = new javax.swing.GroupLayout(co5);
        co5.setLayout(co5Layout);
        co5Layout.setHorizontalGroup(
            co5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 40, Short.MAX_VALUE)
        );
        co5Layout.setVerticalGroup(
            co5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 60, Short.MAX_VALUE)
        );

        connectionState.setFont(new java.awt.Font("Agency FB", 0, 36)); // NOI18N
        connectionState.setForeground(new java.awt.Color(204, 0, 0));
        connectionState.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        connectionState.setText("Testing...");

        javax.swing.GroupLayout back5Layout = new javax.swing.GroupLayout(back5);
        back5.setLayout(back5Layout);
        back5Layout.setHorizontalGroup(
            back5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(back5Layout.createSequentialGroup()
                .addComponent(co5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(connectionState, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 7, Short.MAX_VALUE))
        );
        back5Layout.setVerticalGroup(
            back5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(back5Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(co5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addComponent(connectionState, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        back6.setBackground(new java.awt.Color(255, 255, 255));
        back6.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        back6.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                back6MouseClicked(evt);
            }
        });

        co6.setBackground(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout co6Layout = new javax.swing.GroupLayout(co6);
        co6.setLayout(co6Layout);
        co6Layout.setHorizontalGroup(
            co6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 40, Short.MAX_VALUE)
        );
        co6Layout.setVerticalGroup(
            co6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 60, Short.MAX_VALUE)
        );

        leave.setFont(new java.awt.Font("Agency FB", 0, 36)); // NOI18N
        leave.setForeground(new java.awt.Color(204, 0, 0));
        leave.setText("Leave");

        javax.swing.GroupLayout back6Layout = new javax.swing.GroupLayout(back6);
        back6.setLayout(back6Layout);
        back6Layout.setHorizontalGroup(
            back6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(back6Layout.createSequentialGroup()
                .addGap(7, 7, 7)
                .addComponent(leave, javax.swing.GroupLayout.DEFAULT_SIZE, 125, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(co6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        back6Layout.setVerticalGroup(
            back6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(back6Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(co6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addComponent(leave, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
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
                    .addComponent(back1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(back2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(back3, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(back4, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
            .addGroup(sideBarLeftLayout.createSequentialGroup()
                .addComponent(back5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(back6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
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
                .addGap(18, 18, 18)
                .addComponent(back2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(back3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(back4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 52, Short.MAX_VALUE)
                .addGroup(sideBarLeftLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(back5, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(back6, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        animBack.setBackground(new java.awt.Color(204, 204, 204));
        animBack.setPreferredSize(new java.awt.Dimension(1300, 1000));

        javax.swing.GroupLayout animBackLayout = new javax.swing.GroupLayout(animBack);
        animBack.setLayout(animBackLayout);
        animBackLayout.setHorizontalGroup(
            animBackLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1300, Short.MAX_VALUE)
        );
        animBackLayout.setVerticalGroup(
            animBackLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1000, Short.MAX_VALUE)
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

    private void back6MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_back6MouseClicked
        System.exit(0);
    }//GEN-LAST:event_back6MouseClicked

    private void back5MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_back5MouseClicked
        runConnectionTest();
    }//GEN-LAST:event_back5MouseClicked

    private void back3MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_back3MouseClicked
        Methods.AnimationGraph.stop();
        if (Methods.TutorialDisplay == null) Methods.TutorialDisplay = new Tutorial();
        Methods.addToFrontPanel(animBack, Methods.TutorialDisplay, false);
    }//GEN-LAST:event_back3MouseClicked

    private void back4MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_back4MouseClicked
        Methods.AnimationGraph.stop();
        if (Methods.LBDisplay == null) Methods.LBDisplay = new Leaderboard();
        Methods.addToFrontPanel(animBack, Methods.LBDisplay, false);
    }//GEN-LAST:event_back4MouseClicked


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel animBack;
    public static javax.swing.JPanel back1;
    public static javax.swing.JPanel back2;
    public static javax.swing.JPanel back3;
    public static javax.swing.JPanel back4;
    public static javax.swing.JPanel back5;
    private javax.swing.JPanel back6;
    private javax.swing.JPanel co1;
    private javax.swing.JPanel co2;
    private javax.swing.JPanel co3;
    private javax.swing.JPanel co4;
    private javax.swing.JPanel co5;
    private javax.swing.JPanel co6;
    private javax.swing.JLabel connectionState;
    public static javax.swing.JTextField enterUsername;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JLabel leave;
    public static javax.swing.JPanel sideBarLeft;
    private javax.swing.JLabel title1;
    private javax.swing.JLabel title2;
    private javax.swing.JLabel title3;
    private javax.swing.JLabel title4;
    // End of variables declaration//GEN-END:variables
}
