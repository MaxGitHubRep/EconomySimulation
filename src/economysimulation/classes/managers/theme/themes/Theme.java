package economysimulation.classes.managers.theme.themes;

import java.awt.Color;

/**
 * @author Max Carter
 */
public enum Theme {

    White(
        new Color(255, 255, 255),
        new Color(180, 180, 180),
        new Color(204, 0, 0),
        new Color(255, 255, 255),
        new Color(240, 240, 240),
        new Color(204, 0, 0),
        new Color(153, 0, 0)
    ),

    Dark(
        new Color(51, 51, 51),
        new Color(41, 41, 41),
        new Color(235, 235, 235),
        new Color(235, 235, 235),
        new Color(90, 90, 90),
        new Color(102, 102, 102),
        new Color(153, 153, 153)
    ),

    Ocean(
        new Color(0, 102, 153),
        new Color(0, 153, 204),
        new Color(255, 255, 255),
        new Color(255, 255, 255),
        new Color(0, 153, 204),
        new Color(0, 140, 255),
        new Color(240, 240, 240)
    ),

    Pink(
        new Color(170, 0, 170),
        new Color(150, 0, 150),
        new Color(235, 235, 235),
        new Color(235, 235, 235),
        new Color(180, 0, 180),
        new Color(160, 0, 160),
        new Color(153, 153, 153)
    );
    
    //Local colour variables.
    private Color
        primaryBack,
        secondaryBack,
        primaryText,
        secondaryText,
        ghostText,
        primaryHover,
        secondaryHover;
    
    //Creates a new Theme using the parameters.
    Theme(Color primaryBack, Color secondaryBack, Color primaryText, Color secondaryText, Color primaryHover, Color secondaryHover, Color ghostText) {
       setPrimaryColor(primaryBack);
       setSecondaryColor(secondaryBack);
       setPrimaryTextColor(primaryText);
       setSecondaryTextColor(secondaryText);
       setPrimaryHoverColor(primaryHover);
       setSecondaryHoverColor(secondaryHover);
       setGhostTextColor(ghostText);
       
    }

    /**
     * Sets the primary colour.
     * @param color New colour.
     */
    public void setPrimaryColor(Color color) {
        this.primaryBack = color;
    }

    /**
     * Gets the primary colour.
     * @return The primary colour.
     */
    public Color getPrimaryColor() {
        return this.primaryBack;
    }

    /**
     * Sets the secondary colour.
     * @param color New colour.
     */
    public void setSecondaryColor(Color color) {
        this.secondaryBack = color;
    }

    /**
     * Gets the secondary colour.
     * @return The secondary colour.
     */
    public Color getSecondaryColor() {
        return this.secondaryBack;
    }

    /**
     * Sets the primary text colour.
     * @param color New colour.
     */
    public void setPrimaryTextColor(Color color) {
        this.primaryText = color;
    }

    /**
     * Gets the primary text colour.
     * @return The primary text colour.
     */
    public Color getPrimaryTextColor() {
        return this.primaryText;
    }
    
    /**
     * Sets the secondary text colour.
     * @param color New colour.
     */
    public void setSecondaryTextColor(Color color) {
        this.secondaryText = color;
    }

    /**
     * Gets the secondary text hover colour.
     * @return The secondary text colour.
     */
    public Color getSecondaryTextColor() {
        return this.secondaryText;
    }

    /**
     * Sets the primary hover colour.
     * @param color New colour.
     */
    public void setPrimaryHoverColor(Color color) {
        this.primaryHover = color;
    }

    /**
     * Gets the primary hover colour.
     * @return The primary hover colour.
     */
    public Color getPrimaryHoverColor() {
        return this.primaryHover;
    }

    /**
     * Sets the secondary hover colour.
     * @param color New colour.
     */
    public void setSecondaryHoverColor(Color color) {
        this.secondaryHover = color;
    }

    /**
     * Gets the secondary hover colour.
     * @return The secondary hover colour.
     */
    public Color getSecondaryHoverColor() {
        return this.secondaryHover;
    }

    /**
     * Sets the ghost text colour.
     * @param color New colour.
     */
    public void setGhostTextColor(Color color) {
        this.ghostText = color;
    }

    /**
     * Gets the ghost text colour.
     * @return The ghost text colour.
     */
    public Color getGhostTextColor() {
        return this.ghostText;
    }

}
