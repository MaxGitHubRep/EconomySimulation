package economysimulation.classes;

/**
 *
 * @author Max Carter
 */
public class Mode {
    
    public static boolean SINGLE_CLASSIC, COOP_CLASSIC, SINGLE_COMP, COOP_COMP;
    
    public Mode(int number) {
        switch (number) {
            case 0:
                SINGLE_CLASSIC = true;
                break;
            case 1:
                COOP_CLASSIC = true;
                break;
            case 2:
                SINGLE_COMP = true;
                break;
            case 3:
                COOP_COMP = true;
                break;
                
            default:
                SINGLE_CLASSIC = true;
        }
    }
    
}
