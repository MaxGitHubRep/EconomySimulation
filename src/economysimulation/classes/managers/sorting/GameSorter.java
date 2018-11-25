package economysimulation.classes.managers.sorting;

import economysimulation.classes.gui.mainpanels.extra.leaderboard.DisplayType;
import economysimulation.classes.managers.extcon.GamePackage;
import economysimulation.classes.managers.sorting.conditions.MergeSort;
import economysimulation.classes.managers.sorting.conditions.SearchComponent;
import economysimulation.classes.managers.sorting.conditions.SearchCondition;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Max Carter
 */
public class GameSorter {
    
    /** List of games that are ready to be sorted. */
    private List<GamePackage> gamePackageList = null;
    
    /** The condition that will manipulate the output of the sort. */
    private SearchCondition searchCondition = null;
    
    /** The condition that will manipulate the output of the sort. */
    private SearchComponent searchComponent = null;
    
    
    /** The display type, multiplayer, singleplayer etc. */
    private DisplayType displayType = null;
    
    /**
     * Creates a new game sorter that will sort
     * a list of games for the leaderboards.
     * 
     * @param gamePackageList List of games.
     */
    public GameSorter(List<GamePackage> gamePackageList) {
        setList(gamePackageList);
    }
    
    /** Sort the game list in the instance. */
    public void sort() {
        if (gamePackageList == null) return;
        
        if (displayType != DisplayType.COMBINED) {
            List<GamePackage> removeList = new ArrayList<>();

            if (displayType == DisplayType.SINGLE_PLAYER) {
                for (GamePackage pkg : gamePackageList) {
                    if (pkg.getPlayers().length > 1) {
                        removeList.add(pkg);
                    }
                }
            } else if (displayType == DisplayType.MULTI_PLAYER) {
                for (GamePackage pkg : gamePackageList) {
                    if (pkg.getPlayers().length == 1) {
                        removeList.add(pkg);
                    }
                }
            }

            for (GamePackage pkg : removeList) {
                if (gamePackageList.contains(pkg)) {
                    gamePackageList.remove(pkg);
                }
            }
        }
        
        // Checks that the list being sorted has more than one item.
        if (gamePackageList.isEmpty() || gamePackageList.size() == 1) return;
        
        MergeSort mergeSort = new MergeSort();
        
        GamePackage[] gamePkgStaticList = new GamePackage[gamePackageList.size()-1];
        
        for (int i = 0; i < gamePkgStaticList.length; i++) {
            gamePkgStaticList[i] = gamePackageList.get(i);
        }
        
        setSearchComponent(SearchComponent.FIRM_PROFITS);
        
        mergeSort.sort(gamePkgStaticList, 0, gamePkgStaticList.length-1, this);
        
        gamePackageList.clear();
        for (int i = 0; i < gamePkgStaticList.length; i++) {
            gamePackageList.add(gamePkgStaticList[i]);
        }
        
    }
    
    /**
     * Sets the list of the instance.
     * 
     * @param gamePackageList List of games.
     */
    public void setList(List<GamePackage> gamePackageList) {
        this.gamePackageList = gamePackageList;
    }
    
    /**
     * Retrieves the list of game packages.
     * 
     * @return The list of games.
     */
    public List<GamePackage> getList() {
        return gamePackageList;
    }
    
    /**
     * Specifies the {@code DisplayType} of the
     * search, multiplayer, singleplayer etc.
     * 
     * @param displayType The display type.
     */
    public void setDisplayType(DisplayType displayType) {
        if (displayType == null) {
            displayType = DisplayType.COMBINED;
        } else {
            this.displayType = displayType;
        }
    }
    
    public DisplayType getDisplayType() {
        return displayType;
    }
    
    /**
     * Specifies the search condition for the instance.
     * 
     * @param searchCondition Condition of the sort.
     */
    public void setSearchCondition(SearchCondition searchCondition) {
        if (searchCondition == null) {
            this.searchCondition = SearchCondition.HIGH_TO_LOW;
        } else {
            this.searchCondition = searchCondition;
        }
    }
    
    public SearchCondition geSearchtCondition() {
        return searchCondition;
    }
    
    public void setSearchComponent(SearchComponent searchComponent) {
        if (searchComponent == null) {
            this.searchComponent = SearchComponent.GDP;
        } else {
            this.searchComponent = searchComponent;
        }
    }
    
    public SearchComponent getSearchComponent() {
        return searchComponent;
    }
    
}
