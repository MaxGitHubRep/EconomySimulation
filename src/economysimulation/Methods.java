package economysimulation;

import java.util.Random;

/**
 *
 * @author Max Carter
 */
public class Methods {

    public static int randomInt(int min, int max) {
        return new Random().nextInt((max-min)+1)+min;
    }
    
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
    }
    
}
