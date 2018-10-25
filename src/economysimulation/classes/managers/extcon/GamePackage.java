package economysimulation.classes.managers.extcon;

/**
 *
 * @author Max Carter
 */
public class GamePackage {

    private int id, score, gameTicks;
    private double[] components;
    private String[] players;
    
    public GamePackage(int id, int score, int gameTicks, String[] players, double[] components) {
        this.id = id;
        this.score = score;
        this.gameTicks = gameTicks;
        this.players = players;
        this.components = components;
    }
    
    public int getID() {
        return this.id;
    }
    
    public int getScore() {
        return this.score;
    }
    
    public int getTicks() {
        return this.gameTicks;
    }
    
    public String[] getPlayers() {
        return this.players;
    }
    
    public double getComponentFromId(int id) {
        return this.components[id];
    }
    
    public double[] getComponents() {
        return this.components;
    }
    
}
