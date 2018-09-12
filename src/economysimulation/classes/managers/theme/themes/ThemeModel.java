package economysimulation.classes.managers.theme.themes;

import java.awt.Color;

/**
 *
 * @author Max Carter
 */
public abstract class ThemeModel {

    protected Color
        primaryBack,
        secondaryBack,
        primaryText,
        secondaryText,
        ghostText,
        primaryHover,
        secondaryHover;
    
    public String name;
    
    public abstract void setName(String name);
    public abstract String getName(String name);
    
    public abstract void setPrimaryColor(Color color);
    public abstract Color getPrimaryColor();
    
    public abstract void setSecondaryColor(Color color);
    public abstract Color getSecondaryColor();
    
    public abstract void setPrimaryTextColor(Color color);
    public abstract Color getPrimaryTextColor();
    
    public abstract void setSecondaryTextColor(Color color);
    public abstract Color getSecondaryTextColor();
    
    public abstract void setPrimaryHoverColor(Color color);
    public abstract Color getPrimaryHoverColor();
    
    public abstract void setSecondaryHoverColor(Color color);
    public abstract Color getSecondaryHoverColor();
    
    public abstract void setGhostTextColor(Color color);
    public abstract Color getGhostTextColor();
    
}
