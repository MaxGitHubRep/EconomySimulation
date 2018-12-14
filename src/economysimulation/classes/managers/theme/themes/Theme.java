package economysimulation.classes.managers.theme.themes;

import java.awt.Color;

/**
 *
 * @author Max Carter
 */
public enum Theme {

    White("White",
        new Color(255, 255, 255),
        new Color(180, 180, 180),
        new Color(204, 0, 0),
        new Color(255, 255, 255),
        new Color(240, 240, 240),
        new Color(204, 0, 0),
        new Color(153, 0, 0)
    ),

    Dark("Dark",
        new Color(51, 51, 51),
        new Color(41, 41, 41),
        new Color(235, 235, 235),
        new Color(235, 235, 235),
        new Color(90, 90, 90),
        new Color(102, 102, 102),
        new Color(153, 153, 153)
    ),

    Ocean("Ocean",
        new Color(0, 102, 153),
        new Color(0, 153, 204),
        new Color(255, 255, 255),
        new Color(255, 255, 255),
        new Color(0, 153, 204),
        new Color(0, 140, 255),
        new Color(240, 240, 240)
    ),

    Pink("Pink",
        new Color(170, 0, 170),
        new Color(150, 0, 150),
        new Color(235, 235, 235),
        new Color(235, 235, 235),
        new Color(180, 0, 180),
        new Color(160, 0, 160),
        new Color(153, 153, 153)
    );
    
    private Color
        primaryBack,
        secondaryBack,
        primaryText,
        secondaryText,
        ghostText,
        primaryHover,
        secondaryHover;
    
    private String name;
    
    Theme(String name, Color primaryBack, Color secondaryBack, Color primaryText, Color secondaryText, Color primaryHover, Color secondaryHover, Color ghostText) {
       setName(name);
       setPrimaryColor(primaryBack);
       setSecondaryColor(secondaryBack);
       setPrimaryTextColor(primaryText);
       setSecondaryTextColor(secondaryText);
       setPrimaryHoverColor(primaryHover);
       setSecondaryHoverColor(secondaryHover);
       setGhostTextColor(ghostText);
       
    }
    
    public void setName(String name) {
        this.name = name;
    }

    public String getName(String name) {
        return this.name;
    }
    
    public void setPrimaryColor(Color color) {
        this.primaryBack = color;
    }

    public Color getPrimaryColor() {
        return this.primaryBack;
    }

    public void setSecondaryColor(Color color) {
        this.secondaryBack = color;
    }

    public Color getSecondaryColor() {
        return this.secondaryBack;
    }

    public void setPrimaryTextColor(Color color) {
        this.primaryText = color;
    }

    public Color getPrimaryTextColor() {
        return this.primaryText;
    }
    
    public void setSecondaryTextColor(Color color) {
        this.secondaryText = color;
    }

    public Color getSecondaryTextColor() {
        return this.secondaryText;
    }

    public void setPrimaryHoverColor(Color color) {
        this.primaryHover = color;
    }

    public Color getPrimaryHoverColor() {
        return this.primaryHover;
    }

    public void setSecondaryHoverColor(Color color) {
        this.secondaryHover = color;
    }

    public Color getSecondaryHoverColor() {
        return this.secondaryHover;
    }

    public void setGhostTextColor(Color color) {
        this.ghostText = color;
    }

    public Color getGhostTextColor() {
        return this.ghostText;
    }

}
