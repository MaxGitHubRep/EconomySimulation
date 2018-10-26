package economysimulation.classes.gui.mainpanels.extra.leaderboard;

import static economysimulation.classes.global.Methods.DBGames;
import static economysimulation.classes.global.Methods.ThemeManager;
import economysimulation.classes.managers.extcon.Connection;
import economysimulation.classes.managers.extcon.GamePackage;
import economysimulation.classes.managers.sorting.SortArray;
import economysimulation.classes.managers.theme.GraphicUpdater;
import economysimulation.classes.managers.theme.ThemeUpdateEvent;
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

    private LoadingError DummyErrorDisplay = null;
    
    private String[] displayType = new String[]{ "Combined", "Single Player", "Multiplayer" };
    public List<Score> ScoreList;
    private int frontPointer = 0, totalPages = 0, viewSelection = 0;
    private final int SCORES_PER_PAGE = 10;
    
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
        
        configLeaderboard();

        ThemeManager.addThemeUpdateListener(this);
        applyModeScroller(changeArrow1, false);
        applyModeScroller(changeArrow2, true);
        applyPagesScroller(changeArrow3, true);
        applyPagesScroller(changeArrow4, false);
    }
    
    public void configLeaderboard() {
        backScore.removeAll();
        if (Connection.isConnected) {
            pullLeaderboardData();
            
        } else {
            sendErrorMessage("No connection to the server.");
        }
    }
    
    private void pullLeaderboardData() {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                ScoreList = new ArrayList<>();
                int gamesPlayed = DBGames.getGamesPlayed(true);
                if (gamesPlayed == 0) {
                    sendErrorMessage("No games found.");
                } else {
                    
                    totalPages = (int) Math.floor(gamesPlayed/SCORES_PER_PAGE)+1;

                    List<GamePackage> gameData = SortArray.sortGameData(DBGames.getAllGameData());

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
    }
    
    private void applyModeScroller(JLabel label, boolean forward) {
        label.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (forward) {
                    viewSelection = (viewSelection == displayType.length-1) ? 0 : viewSelection+1;
                } else {
                    viewSelection = (viewSelection == 0) ? displayType.length-1 : viewSelection-1;
                }
                playerTypeDisplay.setText(displayType[viewSelection]);
            }
        });
    }
    
    private void applyPagesScroller(JLabel label, boolean forward) {
        label.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (forward) {
                    if (frontPointer+SCORES_PER_PAGE < ScoreList.size()) {
                        updateFrontPointer(SCORES_PER_PAGE);
                    }
                } else {
                    if (frontPointer >= 10) {
                        updateFrontPointer(-SCORES_PER_PAGE);
                    }
                } 
            }
        });
    }
    
    private void updateFrontPointer(int value) {
        frontPointer+=value;
        updatePageNumberedDisplay();
        refreshDisplayList();
    }
    
    public void addScore(int rank, GamePackage pkg) {
        ScoreList.add(new Score(rank, pkg));
    }
    
    private void refreshDisplayList() {
        backScore.removeAll();
        for (int i = 0; i < SCORES_PER_PAGE; i++) {
            backScore.add(scoreDisplays[i]);
            scoreDisplays[i].setLocation(0, (i*80));
            scoreDisplays[i].setVisible(true);
            scoreDisplays[i].setSize(900, 80);
            
            if (ScoreList.size() <= i + frontPointer) {
                scoreDisplays[i].setDisplayData(0, null);
                
            } else {
                Score score = ScoreList.get(i + frontPointer);
                scoreDisplays[i].setDisplayData(score.getRank(), score.getGamePackage());
            }
            
        }
    }
    
    public void onScoreHoverListener(int gameId, GamePackage pkg) {
        gameIndexDisplay.setText("Game Data: #" + new DecimalFormat("0").format(gameId));
    }

    @Override
    public void updateThemeEvent(GraphicUpdater updater) {
        updater.applyPanelThemes(new JPanel[]{ this }, new JPanel[]{ topBar, leftBar });
        updater.applyTextThemes(new JLabel[]{ changeArrow1, changeArrow2, changeArrow3, changeArrow4, playerTypeDisplay, pageReference }, 
                new JLabel[]{ rankTitle, scoreTitle, playersTitle, gameIndexDisplay });
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
        changeArrow4 = new javax.swing.JLabel();
        changeArrow3 = new javax.swing.JLabel();
        pageReference = new javax.swing.JLabel();
        jSeparator4 = new javax.swing.JSeparator();

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
        scoreTitle.setText("Score (£)");

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
            .addGap(0, 814, Short.MAX_VALUE)
        );

        leftBar.setBackground(new java.awt.Color(153, 153, 153));

        javax.swing.GroupLayout leftBarLayout = new javax.swing.GroupLayout(leftBar);
        leftBar.setLayout(leftBarLayout);
        leftBarLayout.setHorizontalGroup(
            leftBarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        leftBarLayout.setVerticalGroup(
            leftBarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 814, Short.MAX_VALUE)
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
                .addGap(102, 102, 102)
                .addComponent(jSeparator4, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
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
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(playerTypeDisplay, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(changeArrow1, javax.swing.GroupLayout.PREFERRED_SIZE, 74, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(changeArrow2, javax.swing.GroupLayout.PREFERRED_SIZE, 74, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(pageReference, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(changeArrow4, javax.swing.GroupLayout.PREFERRED_SIZE, 74, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(changeArrow3, javax.swing.GroupLayout.PREFERRED_SIZE, 74, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jSeparator4))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(topBar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(leftBar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(backScore, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(0, 0, 0))
        );
    }// </editor-fold>//GEN-END:initComponents

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel backScore;
    private javax.swing.JLabel changeArrow1;
    private javax.swing.JLabel changeArrow2;
    private javax.swing.JLabel changeArrow3;
    private javax.swing.JLabel changeArrow4;
    private javax.swing.JLabel gameIndexDisplay;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JSeparator jSeparator3;
    private javax.swing.JSeparator jSeparator4;
    private javax.swing.JPanel leftBar;
    private javax.swing.JLabel pageReference;
    private javax.swing.JLabel playerTypeDisplay;
    private javax.swing.JLabel playersTitle;
    private javax.swing.JLabel rankTitle;
    private javax.swing.JLabel scoreTitle;
    private javax.swing.JPanel topBar;
    // End of variables declaration//GEN-END:variables
}
