package economysimulation.classes.managers.extcon;

import economysimulation.classes.global.User;
import java.util.List;

/**
 * @author Max Carter
 */
public class GamePackage {

    private int id, score, gameTicks;
    private double[] components;
    private List<User> players = null;
    
    /**
     * Creates a new GamePackage.
     * @param id          Index of the game.
     * @param score       GDP score of the game.
     * @param gameTicks   Amount of ticks in the game.
     * @param players     Players in the game.
     * @param components  The component values of the game.
     */
    public GamePackage(int id, int score, int gameTicks, List<User> players, double[] components) {
        this.id = id;
        this.score = score;
        this.gameTicks = gameTicks;
        this.players = players;
        this.components = components;
    }
    
    /**
     * Gets the index of the game package.
     * @return game index.
     */
    public int getID() {
        return this.id;
    }
    
    /**
     * Gets the game score.
     * @return Game score.
     */
    public int getScore() {
        return this.score;
    }
    
    /**
     * Gets the game ticks.
     * @return Game ticks.
     */
    public int getTicks() {
        return this.gameTicks;
    }
    
    /**
     * Gets the player list.
     * @return List of players.
     */
    public List<User> getPlayers() {
        return this.players;
    }
    
    /**
     * Gets a component at a specific index.
     * @param id Index of component.
     * @return Component an index.
     */
    public double getComponentFromId(int id) {
        return this.components[id];
    }
    
    /**
     * Gets all components in the list.
     * @return Every component.
     */
    public double[] getComponents() {
        return this.components;
    }
    
}
