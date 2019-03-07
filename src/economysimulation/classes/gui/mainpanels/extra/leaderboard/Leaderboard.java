package economysimulation.classes.gui.mainpanels.extra.leaderboard;

import static economysimulation.classes.global.Methods.DBGames;
import economysimulation.classes.managers.extcon.Connection;
import economysimulation.classes.managers.extcon.GamePackage;
import economysimulation.classes.managers.popup.frame.PopUpFrame;
import economysimulation.classes.managers.sorting.GameSorter;
import economysimulation.classes.managers.sorting.conditions.SearchComponent;
import economysimulation.classes.managers.sorting.conditions.SearchCondition;
import economysimulation.classes.managers.theme.GraphicUpdater;
import economysimulation.classes.managers.theme.ThemeUpdateEvent;
import economysimulation.classes.managers.ui.Format;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JLabel;
import javax.swing.JPanel;
import static economysimulation.classes.global.Methods.ThemeHandler;

/**
 * @author Max Carter
 */
public class Leaderboard extends javax.swing.JPanel implements ThemeUpdateEvent {

    /** The {@code GamePackage} which is selected to be displayed.
     * Defaults to {@code null}. */
    private GamePackage selectedPackage = null;
    
    /** Error screen instance. */
    private LoadingError DummyErrorDisplay = null;
    
    /** List of game display types. */
    protected final DisplayType[] DisplayOrder = new DisplayType[]{
        DisplayType.COMBINED, DisplayType.SINGLE_PLAYER, DisplayType.MULTI_PLAYER
    };
    
    /** List of all the game scores. */
    public List<Score> ScoreList;
    
    /** Front index of the list. */
    private int frontPointer = 0;
    
    /** Amount of pages in total. */
    private int totalPages = 0;
    
    /** Selected view. */
    private int viewSelection = 0;
    
    /** Amount of scores that are displayed on each page. */
    private final int SCORES_PER_PAGE = 10;
    
    /** List of score slots that display the game scores. */
    private ScoreDisplay[] scoreDisplays = new ScoreDisplay[SCORES_PER_PAGE];
    
    /** List of all the game data which is displayed upon inspection. */
    private final GameData[] dataList;
    
    /** Leaderboard sorter instance. */
    private GameSorter gameSorter;
    
    /** Instance of the {@code PopUpFrame} used to inflate the sort menu. */
    private PopUpFrame popUpFrame = null;
    
    /** Instance of the leaderboard list sorter. */
    private SortPopUp sortPopUp = null;
    
    /** Title of the leaderboard sorter which opens in a new window */
    public final String SORT_TITLE = "Custom Sort";
    
    private Thread connectionThread = null;
    
    /** Creates new form Leader board. */
    public Leaderboard() {
        initComponents();
        
        //load a default no display message.
        sendErrorMessage("No games loaded.");
        
        //initiate the score displays.
        for (int i = 0; i < SCORES_PER_PAGE; i++) {
            scoreDisplays[i] = new ScoreDisplay();
        }
        
        //list of all the sortable data types.
        dataList = new GameData[]{
            GameData.GDP, GameData.TICKS, GameData.CONSUMPTION, GameData.SAVINGS, GameData.POPULATION,
            GameData.UNEMPLOYMENT, GameData.PEOPLE_SUPPORT, GameData.INVESTMENT, GameData.TAXATION,
            GameData.FIRM_PROFITS, GameData.FIRM_SUPPORT
        };
        
        for (GameData data : dataList) {
            onGameInfoHoverEvent(data);
        }
        
        try {
            //configurates the leaderboard with default sort patterns.
            configLeaderboard(DisplayType.COMBINED, SearchComponent.GDP, SearchCondition.HIGH_TO_LOW); 
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        //load the theme.
        ThemeHandler.addThemeUpdateListener(this);
        
        //adda the page and mode scroller.
        applyScroller(changeArrow1, false, Scroll.MODE);
        applyScroller(changeArrow2, true, Scroll.MODE);
        applyScroller(changeArrow3, true, Scroll.PAGE);
        applyScroller(changeArrow4, false, Scroll.PAGE);
        
        //formats the buttons that change colour when hovered over.
        Format.addButtonFormat(back1, col1);
        Format.addButtonFormat(back2, col2);
    }
    
    //** Configurates the leaderboard with default options. */
    public void configLeaderboard() {
        configLeaderboard(DisplayOrder[viewSelection], null, null);
    }
    
    /**
     * Configurates the leaderboard with arguments
     * defining the default sorting patterns.
     * @param displayType      Whether the leaderboard will display single/multiplayer, or both. 
     * @param searchComponent  Which component is used to sort the entries. 
     * @param searchCondition  Whether the data will be sorted from low-to-high or high-to-low.
     */
    public void configLeaderboard(DisplayType displayType, SearchComponent searchComponent, SearchCondition searchCondition) {
        //clear all the existing labels.
        backScore.removeAll();
        playerTypeDisplay.setText("");
        pageReference.setText("");
        extraLabel.setText("");
        //checks connection.
        if (Connection.isConnected) {
            //pulls data from the leaderboards.
            pullLeaderboardData(displayType, searchComponent, searchCondition);
        } else {
            //error message if no connection is established.
            sendErrorMessage("No connection to the server.");
        }
    }
    
    /**
     * Gets all the data from the leaderboard.
     * @param displayType
     * @param searchComponent
     * @param searchCondition 
     */
    private synchronized void pullLeaderboardData(DisplayType displayType, SearchComponent searchComponent, SearchCondition searchCondition) {
        //new event queue when pulling data.
        connectionThread = new Thread(new Runnable() {
            public void run() {
                ScoreList = new ArrayList<>();
                //makes sure there are games in the database to use.
                int gamesPlayed = DBGames.getGamesPlayed(true);
                if (gamesPlayed == 0) {
                    sendErrorMessage("No games found.");
                    
                } else {
                    //calculates total pages required.
                    totalPages = (int) (Math.floor(gamesPlayed/SCORES_PER_PAGE)+1);

                    //sorts the game data.
                    gameSorter = new GameSorter(DBGames.getAllGameData());
                    gameSorter.setDisplayType(displayType);
                    gameSorter.setSearchComponent(searchComponent);
                    gameSorter.setSearchCondition(searchCondition);
                    gameSorter.sort();
                    
                    List<GamePackage> gameData = gameSorter.getList();
                    
                    frontPointer = 0;

                    //formats the top scores into game packages for the score display.
                    for (int i = 0; i < gameData.size(); i++) {
                        addScore(i+1, gameData.get(i));
                    }

                    //update display of leaderboard data.
                    refreshDisplayList();
                    updatePageNumberedDisplay();
                }
            }
        });
        connectionThread.start();
    }

    /**
     * Sets the leaderboard score list to an error message of {@code message}.
     * @param message 
     */
    private void sendErrorMessage(String message) {
        backScore.removeAll();
        DummyErrorDisplay = new LoadingError(message);
        backScore.add(DummyErrorDisplay);
        DummyErrorDisplay.setSize(900, 800);
    }
    
    /** Updates the text that indicates how many pages there are in total. */
    private void updatePageNumberedDisplay() {
        pageReference.setText("Page: " + ((int) Math.floor(frontPointer/SCORES_PER_PAGE)+1) + "/" + totalPages);
        playerTypeDisplay.setText(DisplayOrder[viewSelection].getTitle());
    }
    
    /** Scroll mode can either change the page number or the team size display type. */
    enum Scroll {
        MODE,
        PAGE
    }
    
    /**
     * Adds a scroll option to a button which can move a list back and forth.
     * @param label    Label button to click.
     * @param forward  Whether the button will move backwards of forwards on the list.
     * @param scroll   The Scroll type of the scroller, either
     *                 {@code Scroll.MODE} or {@code Scroll.PAGE}.
     */
    private void applyScroller(JLabel label, boolean forward, Scroll scroll) {
        label.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                //determines the type of scrolling occurring.
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
                }
            }
        });
    }
    
    /**
     * Adds {@code value} to the front pointer value.
     * @param value New value of the front pointer.
     */
    private void updateFrontPointer(int value) {
        frontPointer+=value;
        //validation to make sure the pointer is always positive.
        if (frontPointer < 0) frontPointer = 0;
        refreshDisplayList();
        updatePageNumberedDisplay();
    }
    
    /**
     * Creates a new {@code Score} instance and adds it to the score list.
     * @param rank  The ranking index of the score.
     * @param pkg   The {@code GamePackage} containing data about that score.
     */
    public void addScore(int rank, GamePackage pkg) {
        ScoreList.add(new Score(rank, pkg));
    }
    
    /** Refreshes the display list of the leaderboard list data. */
    private void refreshDisplayList() {
        //clears the list.
        backScore.removeAll();
        //loops through all the scores and adds a score.
        for (int i = 0; i < SCORES_PER_PAGE; i++) {
            backScore.add(scoreDisplays[i]);
            scoreDisplays[i].setLocation(0, (i*78));
            scoreDisplays[i].setVisible(true);
            scoreDisplays[i].setSize(900, 76);
            
            //check to see if a score at the loop-index exists.
            if (ScoreList.size() <= i + frontPointer) {
                scoreDisplays[i].setDisplayData(0, null);
                
            } else {
                //adds the score to the leaderboard list.
                Score score = ScoreList.get(i + frontPointer);
                int rank = score.getRank();
                if (gameSorter.getSearchCondition() == SearchCondition.LOW_TO_HIGH) {
                    rank = ScoreList.size() - rank + 1;
                }
                scoreDisplays[i].setDisplayData(rank, score.getGamePackage());
            }
            
        }
    }
    
    /** The type of game data that can be compared. */
    enum GameData {
        GDP("GDP", gback1, var1, "£%sm", -1, null),
        TICKS("Ticks", gback2, var2, "%s", -2, null),
        PEOPLE_SUPPORT("Consumer Support", gback3, var3, "%s" + "%%", 4, extraLabel),
        UNEMPLOYMENT("Unemployment", gback4, var4, "%s" + "%%", 3, extraLabel),
        FIRM_SUPPORT("Firm Support", gback5, var5, "%s" + "%%", 8, extraLabel),
        CONSUMPTION("Consumption", gback6, var6, "£%sm", 0, null),
        SAVINGS("Savings", gback7, var7, "£%sm", 1, null),
        INVESTMENT("Investment", gback8, var8, "£%sm", 5, null),
        TAXATION("Taxation", gback9, var9, "£%sm", 6, null),
        FIRM_PROFITS("Firm Profits", gback10, var10, "£%sm", 7, null),
        POPULATION("Population", gback11, var11, "%s", 2, null);
        
        private String title = null, format = null;
        private JPanel back;
        private JLabel label, secondary;
        private int id;
        
        /**
         * Creates new GameData.
         * @param title     Title of the game data.
         * @param back      Panel to use.
         * @param label     Label to use.
         * @param format    Pattern format of variable.
         * @param id        Index of the game data.
         * @param secondary If it uses a secondary label.
         */
        GameData(String title, JPanel back, JLabel label, String format, int id, JLabel secondary) {
            this.title = title;
            this.format = format;
            this.back = back;
            this.label = label;
            this.id = id;
            this.secondary = secondary;
        }
        
        /**
         * Gets the title of the game data.
         * @return Title of the game data.
         */
        public String getTitle() {
            return title;
        }
        
        /**
         * Gets the format of the game type.
         * @return Format of the game type.
         */
        public String getFormat() {
            return format;
        }
        
        /**
         * Gets the back panel of the game type.
         * @return Panel of the game type.
         */
        public JPanel getPanel() {
            return back;
        }
        
        /**
         * Gets the label of the game type.
         * @return Label of the game type.
         */
        public JLabel getLabel() {
            return label;
        }
        
        /**
         * Whether the game type uses a secondary label.
         * @return Returns true if the game type uses a secondary label.
         */
        public boolean usesSecondaryLabel() {
            return secondary != null;
        }
        
        /**
         * Gets the secondary label of the game type.
         * @return Secondary label of the game type.
         */
        public JLabel getSecondaryLabel() {
            return secondary;
        }   
        
        /**
         * Gets the index of the game type.
         * @return Index of the game type.
         */
        public int getIndex() {
            return id;
        }
        
    }
    
    /**
     * When a user hovers over a specific label to display game data.
     * @param data The label and action of the event data.
     */
    private void onGameInfoHoverEvent(GameData data) {
        data.getPanel().addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                //if nothing is loaded, set default text to N/A
                if (selectedPackage == null) {
                    data.getLabel().setText("N/A");
                    return;
                }
                
                //load data onto side bar.
                JLabel label = null;
                if (data.usesSecondaryLabel()) {
                    label = data.getSecondaryLabel();
                } else {
                    label = data.getLabel();
                }
                
                label.setText(data.getTitle());
            }
            
            @Override
            public void mouseExited(MouseEvent e) {
                if (data.usesSecondaryLabel()) extraLabel.setText("");
                updateDisplayInfo(data);
            }
            
        });
    }
    
    /**
     * When the display information needs an update.
     * @param data The label and action of the event data.
     */
    private void updateDisplayInfo(GameData data) {
        extraLabel.setText("");
        if (selectedPackage == null || data == null) {
            data.getLabel().setText("N/A");
            return;
        } else if (data == GameData.GDP) gameIndexDisplay.setText("Game Data: #" + new DecimalFormat("0").format(selectedPackage.getID()));
            
        double component;

        //switch/case to get id of the component.
        switch (data.getIndex()) {
            case -1:
                component = selectedPackage.getScore();
                break;
            case -2:
                component = selectedPackage.getTicks();
                break;
            default:
                component = selectedPackage.getComponentFromId(data.getIndex());
                break;
        }
        data.getLabel().setText(String.format(data.getFormat(), new DecimalFormat("0").format(component)));
    }
    
    /**
     * Event for when a score is clicked in the leaderboards.
     * 
     * @param gameId Index of the game in the game package list.
     * @param pkg    The actual game package with the information in.
     */
    public void onScoreClickListener(int gameId, GamePackage pkg) {
        selectedPackage = pkg;
        for (GameData data : dataList) {
            updateDisplayInfo(data);
        }
    }

    /**
     * When the user has chosen to edit the search parameters.
     * @param searchCondition Conditions of the search.
     * @param searchComponent Type of component to measure.
     */
    public void onSortCustomClose(SearchCondition searchCondition, SearchComponent searchComponent) {
        popUpFrame.dispose();
        if (searchCondition != null && searchComponent != null){
            configLeaderboard(DisplayOrder[viewSelection], searchComponent, searchCondition);
        }
    }
    
    @Override
    public void onThemeUpdate(GraphicUpdater updater) {
        updater.applyPanelThemes(new JPanel[]{ this, back1, back2, col1, col2, gback1, gback2 }, new JPanel[]{ topBar, leftBar, gback1, gback2, gback3, gback4, gback5, gback6, gback7, gback8, gback9, gback10, gback11 });
        updater.applyTextThemes(new JLabel[]{ changeArrow1, changeArrow2, changeArrow3, changeArrow4, playerTypeDisplay, pageReference, title1, title2 }, 
                new JLabel[]{ rankTitle, scoreTitle, playersTitle, gameIndexDisplay, extraLabel, var1, var2, var3, var4, var5, var6, var7, var8, var9, var10, var11 });
    }
    
    class Score {
        private int rank;
        private GamePackage pkg;

        /**
         * Creates a new entry of a score for the leader board.
         * @param rank Game rank.
         * @param pkg  Game package.
         */
        public Score(int rank, GamePackage pkg) {
            this.rank = rank;
            this.pkg = pkg;
            initComponents();
        }

        /**
         * Gets the ranking of the score.
         * @return Rank of score.
         */
        public int getRank() {
            return this.rank;
        }

        /**
         * Gets the GamePackage of the score.
         * @return GamePackage of the score.
         */
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
        gback7 = new javax.swing.JPanel();
        var7 = new javax.swing.JLabel();
        gback6 = new javax.swing.JPanel();
        var6 = new javax.swing.JLabel();
        gback8 = new javax.swing.JPanel();
        var8 = new javax.swing.JLabel();
        gback9 = new javax.swing.JPanel();
        var9 = new javax.swing.JLabel();
        gback10 = new javax.swing.JPanel();
        var10 = new javax.swing.JLabel();
        gback1 = new javax.swing.JPanel();
        var1 = new javax.swing.JLabel();
        gback2 = new javax.swing.JPanel();
        var2 = new javax.swing.JLabel();
        gback11 = new javax.swing.JPanel();
        var11 = new javax.swing.JLabel();
        extraLabel = new javax.swing.JLabel();
        gback5 = new javax.swing.JPanel();
        var5 = new javax.swing.JLabel();
        gback4 = new javax.swing.JPanel();
        var4 = new javax.swing.JLabel();
        gback3 = new javax.swing.JPanel();
        var3 = new javax.swing.JLabel();
        changeArrow4 = new javax.swing.JLabel();
        changeArrow3 = new javax.swing.JLabel();
        pageReference = new javax.swing.JLabel();
        back1 = new javax.swing.JPanel();
        col1 = new javax.swing.JPanel();
        title1 = new javax.swing.JLabel();
        back2 = new javax.swing.JPanel();
        col2 = new javax.swing.JPanel();
        title2 = new javax.swing.JLabel();

        setBackground(new java.awt.Color(255, 255, 255));

        topBar.setBackground(new java.awt.Color(153, 153, 153));

        gameIndexDisplay.setFont(new java.awt.Font("Agency FB", 0, 42)); // NOI18N
        gameIndexDisplay.setForeground(new java.awt.Color(255, 255, 255));
        gameIndexDisplay.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        gameIndexDisplay.setText("Game Data: N/A");

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
                .addComponent(gameIndexDisplay, javax.swing.GroupLayout.PREFERRED_SIZE, 360, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(16, Short.MAX_VALUE))
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
            .addGap(0, 792, Short.MAX_VALUE)
        );

        leftBar.setBackground(new java.awt.Color(153, 153, 153));

        gback7.setBackground(new java.awt.Color(153, 153, 153));

        var7.setFont(new java.awt.Font("Agency FB", 0, 42)); // NOI18N
        var7.setForeground(new java.awt.Color(255, 255, 255));
        var7.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        var7.setText("N/A");

        javax.swing.GroupLayout gback7Layout = new javax.swing.GroupLayout(gback7);
        gback7.setLayout(gback7Layout);
        gback7Layout.setHorizontalGroup(
            gback7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(gback7Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(var7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        gback7Layout.setVerticalGroup(
            gback7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, gback7Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(var7)
                .addContainerGap())
        );

        gback6.setBackground(new java.awt.Color(153, 153, 153));

        var6.setFont(new java.awt.Font("Agency FB", 0, 42)); // NOI18N
        var6.setForeground(new java.awt.Color(255, 255, 255));
        var6.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        var6.setText("N/A");

        javax.swing.GroupLayout gback6Layout = new javax.swing.GroupLayout(gback6);
        gback6.setLayout(gback6Layout);
        gback6Layout.setHorizontalGroup(
            gback6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(gback6Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(var6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        gback6Layout.setVerticalGroup(
            gback6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, gback6Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(var6)
                .addGap(13, 13, 13))
        );

        gback8.setBackground(new java.awt.Color(153, 153, 153));

        var8.setFont(new java.awt.Font("Agency FB", 0, 42)); // NOI18N
        var8.setForeground(new java.awt.Color(255, 255, 255));
        var8.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        var8.setText("N/A");

        javax.swing.GroupLayout gback8Layout = new javax.swing.GroupLayout(gback8);
        gback8.setLayout(gback8Layout);
        gback8Layout.setHorizontalGroup(
            gback8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(gback8Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(var8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        gback8Layout.setVerticalGroup(
            gback8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, gback8Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(var8)
                .addContainerGap())
        );

        gback9.setBackground(new java.awt.Color(153, 153, 153));

        var9.setFont(new java.awt.Font("Agency FB", 0, 42)); // NOI18N
        var9.setForeground(new java.awt.Color(255, 255, 255));
        var9.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        var9.setText("N/A");

        javax.swing.GroupLayout gback9Layout = new javax.swing.GroupLayout(gback9);
        gback9.setLayout(gback9Layout);
        gback9Layout.setHorizontalGroup(
            gback9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(gback9Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(var9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        gback9Layout.setVerticalGroup(
            gback9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, gback9Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(var9)
                .addContainerGap())
        );

        gback10.setBackground(new java.awt.Color(153, 153, 153));

        var10.setFont(new java.awt.Font("Agency FB", 0, 42)); // NOI18N
        var10.setForeground(new java.awt.Color(255, 255, 255));
        var10.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        var10.setText("N/A");

        javax.swing.GroupLayout gback10Layout = new javax.swing.GroupLayout(gback10);
        gback10.setLayout(gback10Layout);
        gback10Layout.setHorizontalGroup(
            gback10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(gback10Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(var10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        gback10Layout.setVerticalGroup(
            gback10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, gback10Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(var10)
                .addContainerGap())
        );

        gback1.setBackground(new java.awt.Color(153, 153, 153));

        var1.setFont(new java.awt.Font("Agency FB", 0, 42)); // NOI18N
        var1.setForeground(new java.awt.Color(255, 255, 255));
        var1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        var1.setText("N/A");

        javax.swing.GroupLayout gback1Layout = new javax.swing.GroupLayout(gback1);
        gback1.setLayout(gback1Layout);
        gback1Layout.setHorizontalGroup(
            gback1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(gback1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(var1, javax.swing.GroupLayout.DEFAULT_SIZE, 354, Short.MAX_VALUE)
                .addContainerGap())
        );
        gback1Layout.setVerticalGroup(
            gback1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, gback1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(var1)
                .addGap(13, 13, 13))
        );

        gback2.setBackground(new java.awt.Color(153, 153, 153));

        var2.setFont(new java.awt.Font("Agency FB", 0, 42)); // NOI18N
        var2.setForeground(new java.awt.Color(255, 255, 255));
        var2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        var2.setText("N/A");

        javax.swing.GroupLayout gback2Layout = new javax.swing.GroupLayout(gback2);
        gback2.setLayout(gback2Layout);
        gback2Layout.setHorizontalGroup(
            gback2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(gback2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(var2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        gback2Layout.setVerticalGroup(
            gback2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, gback2Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(var2)
                .addGap(13, 13, 13))
        );

        gback11.setBackground(new java.awt.Color(153, 153, 153));

        var11.setFont(new java.awt.Font("Agency FB", 0, 42)); // NOI18N
        var11.setForeground(new java.awt.Color(255, 255, 255));
        var11.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        var11.setText("N/A");

        javax.swing.GroupLayout gback11Layout = new javax.swing.GroupLayout(gback11);
        gback11.setLayout(gback11Layout);
        gback11Layout.setHorizontalGroup(
            gback11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(gback11Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(var11, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        gback11Layout.setVerticalGroup(
            gback11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, gback11Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(var11)
                .addContainerGap())
        );

        extraLabel.setFont(new java.awt.Font("Agency FB", 0, 36)); // NOI18N
        extraLabel.setForeground(new java.awt.Color(255, 255, 255));
        extraLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        extraLabel.setText("N/A");

        gback5.setBackground(new java.awt.Color(153, 153, 153));

        var5.setFont(new java.awt.Font("Agency FB", 0, 36)); // NOI18N
        var5.setForeground(new java.awt.Color(255, 255, 255));
        var5.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        var5.setText("N/A");

        javax.swing.GroupLayout gback5Layout = new javax.swing.GroupLayout(gback5);
        gback5.setLayout(gback5Layout);
        gback5Layout.setHorizontalGroup(
            gback5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, gback5Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(var5, javax.swing.GroupLayout.DEFAULT_SIZE, 88, Short.MAX_VALUE)
                .addContainerGap())
        );
        gback5Layout.setVerticalGroup(
            gback5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(gback5Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(var5)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        gback4.setBackground(new java.awt.Color(153, 153, 153));

        var4.setFont(new java.awt.Font("Agency FB", 0, 36)); // NOI18N
        var4.setForeground(new java.awt.Color(255, 255, 255));
        var4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        var4.setText("N/A");

        javax.swing.GroupLayout gback4Layout = new javax.swing.GroupLayout(gback4);
        gback4.setLayout(gback4Layout);
        gback4Layout.setHorizontalGroup(
            gback4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, gback4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(var4, javax.swing.GroupLayout.DEFAULT_SIZE, 88, Short.MAX_VALUE)
                .addContainerGap())
        );
        gback4Layout.setVerticalGroup(
            gback4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(gback4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(var4)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        gback3.setBackground(new java.awt.Color(153, 153, 153));

        var3.setFont(new java.awt.Font("Agency FB", 0, 36)); // NOI18N
        var3.setForeground(new java.awt.Color(255, 255, 255));
        var3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        var3.setText("N/A");

        javax.swing.GroupLayout gback3Layout = new javax.swing.GroupLayout(gback3);
        gback3.setLayout(gback3Layout);
        gback3Layout.setHorizontalGroup(
            gback3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(gback3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(var3, javax.swing.GroupLayout.DEFAULT_SIZE, 88, Short.MAX_VALUE)
                .addContainerGap())
        );
        gback3Layout.setVerticalGroup(
            gback3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(gback3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(var3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        javax.swing.GroupLayout leftBarLayout = new javax.swing.GroupLayout(leftBar);
        leftBar.setLayout(leftBarLayout);
        leftBarLayout.setHorizontalGroup(
            leftBarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(leftBarLayout.createSequentialGroup()
                .addGroup(leftBarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(leftBarLayout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(leftBarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(gback1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(gback2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(gback6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(gback7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(gback8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(gback9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(gback10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(gback11, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(extraLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addGroup(leftBarLayout.createSequentialGroup()
                        .addGap(25, 25, 25)
                        .addComponent(gback3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(gback4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(gback5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        leftBarLayout.setVerticalGroup(
            leftBarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(leftBarLayout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addComponent(gback1, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(gback2, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(gback6, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(gback7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(gback8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(gback9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(gback10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(gback11, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(leftBarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(gback4, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(gback5, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(gback3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(extraLabel)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
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

        back1.setBackground(new java.awt.Color(255, 255, 255));
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

        back2.setBackground(new java.awt.Color(255, 255, 255));
        back2.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        back2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                back2MouseClicked(evt);
            }
        });

        col2.setBackground(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout col2Layout = new javax.swing.GroupLayout(col2);
        col2.setLayout(col2Layout);
        col2Layout.setHorizontalGroup(
            col2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        col2Layout.setVerticalGroup(
            col2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 12, Short.MAX_VALUE)
        );

        title2.setFont(new java.awt.Font("Agency FB", 0, 24)); // NOI18N
        title2.setForeground(new java.awt.Color(204, 0, 0));
        title2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        title2.setText("Edit Sort");

        javax.swing.GroupLayout back2Layout = new javax.swing.GroupLayout(back2);
        back2.setLayout(back2Layout);
        back2Layout.setHorizontalGroup(
            back2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(col2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(back2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(title2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        back2Layout.setVerticalGroup(
            back2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(back2Layout.createSequentialGroup()
                .addComponent(col2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(title2, javax.swing.GroupLayout.DEFAULT_SIZE, 46, Short.MAX_VALUE)
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(backScore, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(leftBar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(changeArrow1, javax.swing.GroupLayout.PREFERRED_SIZE, 76, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(playerTypeDisplay, javax.swing.GroupLayout.PREFERRED_SIZE, 328, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(changeArrow2, javax.swing.GroupLayout.PREFERRED_SIZE, 76, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(26, 26, 26)
                .addComponent(back1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(22, 22, 22)
                .addComponent(back2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(48, 48, 48)
                .addComponent(changeArrow4, javax.swing.GroupLayout.PREFERRED_SIZE, 76, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(pageReference, javax.swing.GroupLayout.PREFERRED_SIZE, 264, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(changeArrow3, javax.swing.GroupLayout.PREFERRED_SIZE, 76, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addComponent(topBar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(playerTypeDisplay, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(changeArrow1, javax.swing.GroupLayout.PREFERRED_SIZE, 74, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(changeArrow2, javax.swing.GroupLayout.PREFERRED_SIZE, 74, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(pageReference, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(changeArrow4, javax.swing.GroupLayout.PREFERRED_SIZE, 74, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(changeArrow3, javax.swing.GroupLayout.PREFERRED_SIZE, 74, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(back1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(back2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 13, Short.MAX_VALUE)
                .addComponent(topBar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(leftBar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(backScore, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(0, 0, 0))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void back1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_back1MouseClicked
        configLeaderboard(DisplayOrder[viewSelection], null, null);
    }//GEN-LAST:event_back1MouseClicked

    private void back2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_back2MouseClicked
        //creates a new sort editor.
        if (sortPopUp == null) {
            sortPopUp = new SortPopUp();
        }
        
        //inflates the sort editor.
        if (popUpFrame == null) {
            popUpFrame = new PopUpFrame(sortPopUp, SORT_TITLE);
        }
        popUpFrame.createPopUpFrame();
        
    }//GEN-LAST:event_back2MouseClicked

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel back1;
    private javax.swing.JPanel back2;
    private javax.swing.JPanel backScore;
    private javax.swing.JLabel changeArrow1;
    private javax.swing.JLabel changeArrow2;
    private javax.swing.JLabel changeArrow3;
    private javax.swing.JLabel changeArrow4;
    private javax.swing.JPanel col1;
    private javax.swing.JPanel col2;
    private static javax.swing.JLabel extraLabel;
    private javax.swing.JLabel gameIndexDisplay;
    private static javax.swing.JPanel gback1;
    private static javax.swing.JPanel gback10;
    private static javax.swing.JPanel gback11;
    private static javax.swing.JPanel gback2;
    private static javax.swing.JPanel gback3;
    private static javax.swing.JPanel gback4;
    private static javax.swing.JPanel gback5;
    private static javax.swing.JPanel gback6;
    private static javax.swing.JPanel gback7;
    private static javax.swing.JPanel gback8;
    private static javax.swing.JPanel gback9;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JSeparator jSeparator3;
    private static javax.swing.JPanel leftBar;
    private javax.swing.JLabel pageReference;
    private javax.swing.JLabel playerTypeDisplay;
    private javax.swing.JLabel playersTitle;
    private javax.swing.JLabel rankTitle;
    private javax.swing.JLabel scoreTitle;
    private javax.swing.JLabel title1;
    private javax.swing.JLabel title2;
    private javax.swing.JPanel topBar;
    private static javax.swing.JLabel var1;
    private static javax.swing.JLabel var10;
    private static javax.swing.JLabel var11;
    private static javax.swing.JLabel var2;
    private static javax.swing.JLabel var3;
    private static javax.swing.JLabel var4;
    private static javax.swing.JLabel var5;
    private static javax.swing.JLabel var6;
    private static javax.swing.JLabel var7;
    private static javax.swing.JLabel var8;
    private static javax.swing.JLabel var9;
    // End of variables declaration//GEN-END:variables
}
