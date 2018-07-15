package economysimulation.classes.managers.themes;

import java.awt.Color;

/**
 *
 * @author Max Carter
 */
public class ThemeTypes {
    
    public static final Color[] THEME_WHITE = new Color[]{
        new Color(255, 255, 255), //primaryBack
        new Color(180, 180, 180), //secondaryBack

        new Color(204, 0, 0), //primaryText
        new Color(255, 255, 255), //secondaryText
        new Color(153, 0, 0), //ghostText

        new Color(240, 240, 240), //primaryHover
        new Color(204, 0, 0) //secondaryHover
    
    };
    
    public static final Color[] THEME_DARK = new Color[]{
        new Color(51, 51, 51), //primaryBack
        new Color(41, 41, 41), //secondaryBack

        new Color(255, 255, 255), //primaryText
        new Color(255, 255, 255), //secondaryText
        new Color(153, 153, 153), //ghostText

        new Color(153, 153, 153), //primaryHover
        new Color(102, 102, 102) //secondaryHover
        
    };
    
    public static final Color[] THEME_OCEAN = new Color[]{
        new Color(0, 102, 153), //primaryBack
        new Color(0, 153, 204), //secondaryBack

        new Color(255, 255, 255), //primaryText
        new Color(255, 255, 255), //secondaryText
        new Color(240, 240, 240), //ghostText

        new Color(0, 153, 204), //primaryHover
        new Color(0, 140, 255) //secondaryHover
        
    };
    
}
