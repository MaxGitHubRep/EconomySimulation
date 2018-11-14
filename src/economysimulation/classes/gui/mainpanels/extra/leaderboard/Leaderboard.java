package economysimulation.classes.gui.mainpanels.extra.leaderboard;

import static economysimulation.classes.global.Methods.DBGames;
import static economysimulation.classes.global.Methods.ThemeManager;
import economysimulation.classes.managers.extcon.Connection;
import economysimulation.classes.managers.extcon.GamePackage;
import economysimulation.classes.managers.sorting.SortArray;
import economysimulation.classes.managers.theme.GraphicUpdater;
import economysimulation.classes.managers.theme.ThemeUpdateEvent;
import economysimulation.classes.managers.ui.Format;
import java.awt.EventQueue;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 *
 * @author Max Carter
 */
public class Leaderboard extends javax.swing.JPanel implements ThemeUpdateEvent {

    private GamePackage selectedPackage = null;
    
    private LoadingError DummyErrorDisplay = null;
    
    protected final DisplayType[] DisplayOrder = new DisplayType[]{
        DisplayType.COMBINED, DisplayType.SINGLE_PLAYER, DisplayType.MULTI_PLAYER
    };
    
    private String[]
        ComponentList = new String[]{
            "GDP",
            "Ticks",
            "Consumption",
            "Savings",
            "Population",
            "Unemployment",
            "People Support",
            "Investment",
            "Total Taxation",
            "Firm Profits",
            "Firm Support",
        };
    
    public List<Score> ScoreList;
    private int frontPointer = 0, totalPages = 0, viewSelection = 0, selectedPlayer = -1;
    private final int SCORES_PER_PAGE = 10;
    
    private JPanel[] infoPanels;
    private JLabel[] infoLabels;
    
    private ScoreDisplay[] scoreDisplays = new ScoreDisplay[SCORES_PER_PAGE];
    
    /**
     * Creates new form Leader board.
     */
    public Leaderboard() {
        initComponents();
        sendErrorMessage("No games loaded.");
        
        for (int i = 0; i < SCORES_PER_PAGE; i++) {
            scoreDisplays[i] = new ScoreDisplay();
        }
        
        infoPanels = new JPanel[]{ gback1, gback2 };
        infoLabels = new JLabel[]{ gtitle1, gtitle2 };
        
        for (int i = 0; i < infoPanels.length; i++) {
            Format.addButtonFormat(infoPanels[i], null);
        }
        
        gameInfoHoverEvent(0, Data.GAME_SCORE);
        gameInfoHoverEvent(1, Data.GAME_TICKS);
        
        configLeaderboard(DisplayType.COMBINED);

        ThemeManager.addThemeUpdateListener(this);
        applyScroller(changeArrow1, false, Scroll.MODE);
        applyScroller(changeArrow2, true, Scroll.MODE);
        applyScroller(changeArrow3, true, Scroll.PAGE);
        applyScroller(changeArrow4, false, Scroll.PAGE);
        applyScroller(teammateRight, true, Scroll.TEAMMATE);
        applyScroller(teammateLeft, false, Scroll.TEAMMATE);
        
        Format.addButtonFormat(back1, col1);
    }
    
    /** Type of data which will be shown in the individual displays. */
    private enum Data {
        GAME_SCORE,
        GAME_TICKS,
        GAME_COMPONENTS;
    }
    
    public void configLeaderboard() {
        configLeaderboard(DisplayOrder[viewSelection]);
    }
    
    public void configLeaderboard(DisplayType dt) {
        backScore.removeAll();
        playerTypeDisplay.setText("");
        pageReference.setText("");
        if (Connection.isConnected) {
            pullLeaderboardData(dt);
            
        } else {
            sendErrorMessage("No connection to the server.");
        }
    }
    
    private void pullLeaderboardData(DisplayType dt) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                ScoreList = new ArrayList<>();
                int gamesPlayed = DBGames.getGamesPlayed(true);
                if (gamesPlayed == 0) {
                    sendErrorMessage("No games found.");
                } else {
                    
                    totalPages = (int) Math.floor(gamesPlayed/SCORES_PER_PAGE)+1;

                    List<GamePackage> gameData = SortArray.sortGameData(DBGames.getAllGameData(), dt);
                    frontPointer = 0;

                    for (int i = 0; i < gameData.size(); i++) {
                        GamePackage pkg = gameData.get(i);
                        addScore(i+1, pkg);
                    }

                    refreshDisplayList();
                    updatePageNumberedDisplay();
                }
            }
        });
    }

    private void sendErrorMessage(String message) {
        backScore.removeAll();
        DummyErrorDisplay = new LoadingError(message);
        backScore.add(DummyErrorDisplay);
        DummyErrorDisplay.setSize(900, 800);
    }
    
    private void updatePageNumberedDisplay() {
        pageReference.setText("Page: " + ((int) Math.floor(frontPointer/SCORES_PER_PAGE)+1) + "/" + totalPages);
        playerTypeDisplay.setText(DisplayOrder[viewSelection].getTitle());
    }
    
    enum Scroll {
        MODE,
        PAGE,
        TEAMMATE
    }
    
    private void applyScroller(JLabel label, boolean forward, Scroll scroll) {
        label.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (scroll == Scroll.MODE) {
                    if (forward) {
                        viewSelection = (viewSelection == DisplayOrder.length-1) ? 0 : viewSelection+1;
                    } else {
                        viewSelection = (viewSelection == 0) ? DisplayOrder.length-1 : viewSelection-1;
                    }
                    playerTypeDisplay.setText(DisplayOrder[viewSelection].getTitle());
                    
                } else if (scroll == Scroll.PAGE) {
                    if (forward) {
                        if (frontPointer+SCORES_PER_PAGE < ScoreList.size()) {
                            updateFrontPointer(SCORES_PER_PAGE);
                        }
                    } else {
                        if (frontPointer >= 10) {
                            updateFrontPointer(-SCORES_PER_PAGE);
                        }
                    }
                    
                } else if (scroll == Scroll.TEAMMATE && selectedPackage != null) {
                    int players = selectedPackage.getPlayers().length;
                    if (selectedPlayer == -1 || players <= 1) {
                        return;
                    }
                    
                    if (forward) {
                        if (selectedPlayer == players-1) {
                            selectedPlayer = 0;
                        } else {
                            selectedPlayer++;
                        }
                    } else {
                        if (selectedPlayer == 0) {
                            selectedPlayer = players-1;
                        } else {
                            selectedPlayer--;
                        }
                    }
                    user.setText(selectedPackage.getPlayers()[selectedPlayer]);
                }
            }
        });
    }
    
    private void updateFrontPointer(int value) {
        frontPointer+=value;
        refreshDisplayList();
        updatePageNumberedDisplay();
    }
    
    public void addScore(int rank, GamePackage pkg) {
        ScoreList.add(new Score(rank, pkg));
    }
    
    private void refreshDisplayList() {
        backScore.removeAll();
        for (int i = 0; i < SCORES_PER_PAGE; i++) {
            backScore.add(scoreDisplays[i]);
            scoreDisplays[i].setLocation(0, (i*78));
            scoreDisplays[i].setVisible(true);
            scoreDisplays[i].setSize(900, 76);
            
            if (ScoreList.size() <= i + frontPointer) {
                scoreDisplays[i].setDisplayData(0, null);
                
            } else {
                Score score = ScoreList.get(i + frontPointer);
                scoreDisplays[i].setDisplayData(score.getRank(), score.getGamePackage());
            }
            
        }
    }
    
    /**
     * Displays data about the current game package.
     * 
     * @param id   Index of the game package.
     * @param data The actual game package.
     */
    private void gameInfoHoverEvent(int id, Data data) {
        infoPanels[id].addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                if (selectedPackage == null) {
                    infoLabels[id].setText("N/A");
                    return;
                }
                
                String toDisplay = null;
                switch (data) {
                    case GAME_SCORE:
                        toDisplay = "£" + selectedPackage.getScore() + "m";
                        break;
                    case GAME_TICKS:
                        toDisplay = selectedPackage.getTicks() + "";
                        break;
                    case GAME_COMPONENTS:
                        toDisplay = "" + selectedPackage.getComponentFromId(id);
                        break;
                }
                infoLabels[id].setText(toDisplay);
            }
            
            @Override
            public void mouseExited(MouseEvent e) {
                infoLabels[id].setText(ComponentList[id]);
            }
            
        });
    }
    
    /**
     * Event for when a score is clicked in the leaderboards.
     * 
     * @param gameId Index of the game in the game package list.
     * @param pkg    The actual game package with the information in.
     */
    public void onScoreHoverListener(int gameId, GamePackage pkg) {
        selectedPackage = pkg;
        gameIndexDisplay.setText("Game Data: #" + new DecimalFormat("0").format(gameId));
        user.setText(pkg.getPlayers()[0]);
    }

    @Override
    public void updateThemeEvent(GraphicUpdater updater) {
        updater.applyPanelThemes(new JPanel[]{ this, back1, col1, gback1, gback2 }, new JPanel[]{ topBar, leftBar });
        updater.applyTextThemes(new JLabel[]{ changeArrow1, changeArrow2, changeArrow3, changeArrow4, playerTypeDisplay, pageReference, gtitle1, gtitle2 }, 
                new JLabel[]{ rankTitle, scoreTitle, playersTitle, gameIndexDisplay, teammateLeft, teammateRight, user });
    }
    
    class Score {
        private int rank;
        private GamePackage pkg;

        /**
         * Creates a new entry of a score for the leader board.
         * 
         * @param rank Game rank.
         * @param pkg  Game package.
         */
        public Score(int rank, GamePackage pkg) {
            this.rank = rank;
            this.pkg = pkg;
            initComponents();
        }

        public int getRank() {
            return this.rank;
        }

        public GamePackage getGamePackage() {
            return this.pkg;
        }
        
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        topBar = new javax.swing.JPanel();
        gameIndexDisplay = new javax.swing.JLabel();
        rankTitle = new javax.swing.JLabel();
        jSeparator2 = new javax.swing.JSeparator();
        scoreTitle = new javax.swing.JLabel();
        jSeparator3 = new javax.swing.JSeparator();
        playersTitle = new javax.swing.JLabel();
        playerTypeDisplay = new javax.swing.JLabel();
        changeArrow1 = new javax.swing.JLabel();
        changeArrow2 = new javax.swing.JLabel();
        backScore = new javax.swing.JPanel();
        leftBar = new javax.swing.JPanel();
        gback1 = new javax.swing.JPanel();
        gtitle1 = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        gback2 = new javax.swing.JPanel();
        gtitle2 = new javax.swing.JLabel();
        jSeparator6 = new javax.swing.JSeparator();
        teammateLeft = new javax.swing.JLabel();
        teammateRight = new javax.swing.JLabel();
        user = new javax.swing.JLabel();
        jSeparator7 = new javax.swing.JSeparator();
        jSeparator8 = new javax.swing.JSeparator();
        hoverComponent = new javax.swing.JLabel();
        changeArrow4 = new javax.swing.JLabel();
        changeArrow3 = new javax.swing.JLabel();
        pageReference = new javax.swing.JLabel();
        jSeparator4 = new javax.swing.JSeparator();
        jSeparator5 = new javax.swing.JSeparator();
        back1 = new javax.swing.JPanel();
        col1 = new javax.swing.JPanel();
        title1 = new javax.swing.JLabel();

        setBackground(new java.awt.Color(255, 255, 255));

        topBar.setBackground(new java.awt.Color(153, 153, 153));

        gameIndexDisplay.setFont(new java.awt.Font("Agency FB", 0, 42)); // NOI18N
        gameIndexDisplay.setForeground(new java.awt.Color(255, 255, 255));
        gameIndexDisplay.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        gameIndexDisplay.setText("Game Data: #99999");

        rankTitle.setFont(new java.awt.Font("Agency FB", 0, 42)); // NOI18N
        rankTitle.setForeground(new java.awt.Color(255, 255, 255));
        rankTitle.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        rankTitle.setText("Rank");

        jSeparator2.setOrientation(javax.swing.SwingConstants.VERTICAL);

        scoreTitle.setFont(new java.awt.Font("Agency FB", 0, 42)); // NOI18N
        scoreTitle.setForeground(new java.awt.Color(255, 255, 255));
        scoreTitle.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        scoreTitle.setText("Score (£m)");

        jSeparator3.setOrientation(javax.swing.SwingConstants.VERTICAL);

        playersTitle.setFont(new java.awt.Font("Agency FB", 0, 42)); // NOI18N
        playersTitle.setForeground(new java.awt.Color(255, 255, 255));
        playersTitle.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        playersTitle.setText("Players");

        javax.swing.GroupLayout topBarLayout = new javax.swing.GroupLayout(topBar);
        topBar.setLayout(topBarLayout);
        topBarLayout.setHorizontalGroup(
            topBarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, topBarLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(rankTitle, javax.swing.GroupLayout.PREFERRED_SIZE, 102, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, 15, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(scoreTitle, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator3, javax.swing.GroupLayout.PREFERRED_SIZE, 2, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(playersTitle, javax.swing.GroupLayout.PREFERRED_SIZE, 483, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(26, 26, 26)
                .addComponent(gameIndexDisplay, javax.swing.GroupLayout.PREFERRED_SIZE, 374, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        topBarLayout.setVerticalGroup(
            topBarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(topBarLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(topBarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(playersTitle, javax.swing.GroupLayout.DEFAULT_SIZE, 88, Short.MAX_VALUE)
                    .addComponent(jSeparator3, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(scoreTitle, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 88, Short.MAX_VALUE)
                    .addComponent(jSeparator2)
                    .addComponent(rankTitle, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(gameIndexDisplay, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        playerTypeDisplay.setFont(new java.awt.Font("Agency FB", 0, 48)); // NOI18N
        playerTypeDisplay.setForeground(new java.awt.Color(204, 0, 0));
        playerTypeDisplay.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

        changeArrow1.setFont(new java.awt.Font("Agency FB", 0, 72)); // NOI18N
        changeArrow1.setForeground(new java.awt.Color(204, 0, 0));
        changeArrow1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        changeArrow1.setText("<");
        changeArrow1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

        changeArrow2.setFont(new java.awt.Font("Agency FB", 0, 72)); // NOI18N
        changeArrow2.setForeground(new java.awt.Color(204, 0, 0));
        changeArrow2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        changeArrow2.setText(">");
        changeArrow2.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

        backScore.setBackground(new java.awt.Color(255, 255, 255));
        backScore.setOpaque(false);

        javax.swing.GroupLayout backScoreLayout = new javax.swing.GroupLayout(backScore);
        backScore.setLayout(backScoreLayout);
        backScoreLayout.setHorizontalGroup(
            backScoreLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 900, Short.MAX_VALUE)
        );
        backScoreLayout.setVerticalGroup(
            backScoreLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        leftBar.setBackground(new java.awt.Color(153, 153, 153));

        gtitle1.setFont(new java.awt.Font("Agency FB", 0, 36)); // NOI18N
        gtitle1.setForeground(new java.awt.Color(204, 0, 0));
        gtitle1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        gtitle1.setText("GDP (£m)");

        javax.swing.GroupLayout gback1Layout = new javax.swing.GroupLayout(gback1);
        gback1.setLayout(gback1Layout);
        gback1Layout.setHorizontalGroup(
            gback1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(gback1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(gtitle1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        gback1Layout.setVerticalGroup(
            gback1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(gback1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(gtitle1, javax.swing.GroupLayout.DEFAULT_SIZE, 58, Short.MAX_VALUE)
                .addContainerGap())
        );

        gtitle2.setFont(new java.awt.Font("Agency FB", 0, 36)); // NOI18N
        gtitle2.setForeground(new java.awt.Color(204, 0, 0));
        gtitle2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        gtitle2.setText("Game Ticks");

        javax.swing.GroupLayout gback2Layout = new javax.swing.GroupLayout(gback2);
        gback2.setLayout(gback2Layout);
        gback2Layout.setHorizontalGroup(
            gback2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(gback2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(gtitle2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        gback2Layout.setVerticalGroup(
            gback2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(gback2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(gtitle2, javax.swing.GroupLayout.DEFAULT_SIZE, 58, Short.MAX_VALUE)
                .addContainerGap())
        );

        teammateLeft.setFont(new java.awt.Font("Agency FB", 0, 48)); // NOI18N
        teammateLeft.setForeground(new java.awt.Color(255, 255, 255));
        teammateLeft.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        teammateLeft.setText("<");
        teammateLeft.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

        teammateRight.setFont(new java.awt.Font("Agency FB", 0, 48)); // NOI18N
        teammateRight.setForeground(new java.awt.Color(255, 255, 255));
        teammateRight.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        teammateRight.setText(">");
        teammateRight.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

        user.setFont(new java.awt.Font("Agency FB", 0, 36)); // NOI18N
        user.setForeground(new java.awt.Color(255, 255, 255));
        user.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        user.setText("<player 1>");

        hoverComponent.setFont(new java.awt.Font("Agency FB", 0, 40)); // NOI18N
        hoverComponent.setForeground(new java.awt.Color(255, 255, 255));
        hoverComponent.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        hoverComponent.setText("N/A");

        javax.swing.GroupLayout leftBarLayout = new javax.swing.GroupLayout(leftBar);
        leftBar.setLayout(leftBarLayout);
        leftBarLayout.setHorizontalGroup(
            leftBarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(leftBarLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(leftBarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(leftBarLayout.createSequentialGroup()
                        .addGap(6, 6, 6)
                        .addComponent(hoverComponent, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addComponent(jSeparator7)
                    .addComponent(jSeparator1)
                    .addComponent(gback1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(gback2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jSeparator6)
                    .addGroup(leftBarLayout.createSequentialGroup()
                        .addComponent(teammateLeft, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(user, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(teammateRight, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jSeparator8, javax.swing.GroupLayout.Alignment.TRAILING))
                .addContainerGap())
        );
        leftBarLayout.setVerticalGroup(
            leftBarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(leftBarLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(gback1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(gback2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jSeparator6, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(leftBarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(leftBarLayout.createSequentialGroup()
                        .addGap(12, 12, 12)
                        .addComponent(user, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addComponent(teammateLeft, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(teammateRight, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jSeparator7, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(440, 440, 440)
                .addComponent(jSeparator8, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(hoverComponent, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        changeArrow4.setFont(new java.awt.Font("Agency FB", 0, 72)); // NOI18N
        changeArrow4.setForeground(new java.awt.Color(204, 0, 0));
        changeArrow4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        changeArrow4.setText("<");
        changeArrow4.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

        changeArrow3.setFont(new java.awt.Font("Agency FB", 0, 72)); // NOI18N
        changeArrow3.setForeground(new java.awt.Color(204, 0, 0));
        changeArrow3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        changeArrow3.setText(">");
        changeArrow3.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

        pageReference.setFont(new java.awt.Font("Agency FB", 0, 48)); // NOI18N
        pageReference.setForeground(new java.awt.Color(204, 0, 0));
        pageReference.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

        jSeparator4.setOrientation(javax.swing.SwingConstants.VERTICAL);

        jSeparator5.setOrientation(javax.swing.SwingConstants.VERTICAL);

        back1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        back1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                back1MouseClicked(evt);
            }
        });

        col1.setBackground(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout col1Layout = new javax.swing.GroupLayout(col1);
        col1.setLayout(col1Layout);
        col1Layout.setHorizontalGroup(
            col1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        col1Layout.setVerticalGroup(
            col1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 12, Short.MAX_VALUE)
        );

        title1.setFont(new java.awt.Font("Agency FB", 0, 24)); // NOI18N
        title1.setForeground(new java.awt.Color(204, 0, 0));
        title1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        title1.setText("Refresh");

        javax.swing.GroupLayout back1Layout = new javax.swing.GroupLayout(back1);
        back1.setLayout(back1Layout);
        back1Layout.setHorizontalGroup(
            back1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(col1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(back1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(title1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        back1Layout.setVerticalGroup(
            back1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(back1Layout.createSequentialGroup()
                .addComponent(col1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(title1, javax.swing.GroupLayout.DEFAULT_SIZE, 46, Short.MAX_VALUE)
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(topBar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addComponent(backScore, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(leftBar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(changeArrow1, javax.swing.GroupLayout.PREFERRED_SIZE, 76, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(playerTypeDisplay, javax.swing.GroupLayout.PREFERRED_SIZE, 360, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(changeArrow2, javax.swing.GroupLayout.PREFERRED_SIZE, 76, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(34, 34, 34)
                .addComponent(jSeparator5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(back1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(18, 18, 18)
                .addComponent(jSeparator4, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(changeArrow4, javax.swing.GroupLayout.PREFERRED_SIZE, 76, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(pageReference, javax.swing.GroupLayout.PREFERRED_SIZE, 360, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(changeArrow3, javax.swing.GroupLayout.PREFERRED_SIZE, 76, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(playerTypeDisplay, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(changeArrow1, javax.swing.GroupLayout.PREFERRED_SIZE, 74, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(changeArrow2, javax.swing.GroupLayout.PREFERRED_SIZE, 74, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(pageReference, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(changeArrow4, javax.swing.GroupLayout.PREFERRED_SIZE, 74, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(changeArrow3, javax.swing.GroupLayout.PREFERRED_SIZE, 74, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jSeparator4))
                            .addComponent(jSeparator5)))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(back1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(topBar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(leftBar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(backScore, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(0, 0, 0))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void back1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_back1MouseClicked
        configLeaderboard(DisplayOrder[viewSelection]);
    }//GEN-LAST:event_back1MouseClicked

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel back1;
    private javax.swing.JPanel backScore;
    private javax.swing.JLabel changeArrow1;
    private javax.swing.JLabel changeArrow2;
    private javax.swing.JLabel changeArrow3;
    private javax.swing.JLabel changeArrow4;
    private javax.swing.JPanel col1;
    private javax.swing.JLabel gameIndexDisplay;
    private javax.swing.JPanel gback1;
    private javax.swing.JPanel gback2;
    private javax.swing.JLabel gtitle1;
    private javax.swing.JLabel gtitle2;
    private javax.swing.JLabel hoverComponent;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JSeparator jSeparator3;
    private javax.swing.JSeparator jSeparator4;
    private javax.swing.JSeparator jSeparator5;
    private javax.swing.JSeparator jSeparator6;
    private javax.swing.JSeparator jSeparator7;
    private javax.swing.JSeparator jSeparator8;
    private javax.swing.JPanel leftBar;
    private javax.swing.JLabel pageReference;
    private javax.swing.JLabel playerTypeDisplay;
    private javax.swing.JLabel playersTitle;
    private javax.swing.JLabel rankTitle;
    private javax.swing.JLabel scoreTitle;
    private javax.swing.JLabel teammateLeft;
    private javax.swing.JLabel teammateRight;
    private javax.swing.JLabel title1;
    private javax.swing.JPanel topBar;
    private javax.swing.JLabel user;
    // End of variables declaration//GEN-END:variables
}
