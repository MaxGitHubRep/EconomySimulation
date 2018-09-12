package economysimulation.classes.managers.theme.themes;

import java.awt.Color;

/**
 *
 * @author Max Carter
 */
public final class ThemeBase extends ThemeModel {

    public ThemeBase(String name, Color primaryBack, Color secondaryBack, Color primaryText, Color secondaryText, Color primaryHover, Color secondaryHover, Color ghostText) {
       this.setName(name);
       this.setPrimaryColor(primaryBack);
       this.setSecondaryColor(secondaryBack);
       this.setPrimaryTextColor(primaryText);
       this.setSecondaryTextColor(secondaryText);
       this.setPrimaryHoverColor(primaryHover);
       this.setSecondaryHoverColor(secondaryHover);
       this.setGhostTextColor(ghostText);
       
    }
    
    @Override
    public void setName(String name) {
        super.name = name;
    }

    @Override
    public String getName(String name) {
        return super.name;
    }
    
    @Override
    public void setPrimaryColor(Color color) {
        super.primaryBack = color;
    }

    @Override
    public Color getPrimaryColor() {
        return super.primaryBack;
    }

    @Override
    public void setSecondaryColor(Color color) {
        super.secondaryBack = color;
    }

    @Override
    public Color getSecondaryColor() {
        return super.secondaryBack;
    }

    @Override
    public void setPrimaryTextColor(Color color) {
        super.primaryText = color;
    }

    @Override
    public Color getPrimaryTextColor() {
        return super.primaryText;
    }

    @Override
    public void setSecondaryTextColor(Color color) {
        super.secondaryText = color;
    }

    @Override
    public Color getSecondaryTextColor() {
        return super.secondaryText;
    }

    @Override
    public void setPrimaryHoverColor(Color color) {
        super.primaryHover = color;
    }

    @Override
    public Color getPrimaryHoverColor() {
        return super.primaryHover;
    }

    @Override
    public void setSecondaryHoverColor(Color color) {
        super.secondaryHover = color;
    }

    @Override
    public Color getSecondaryHoverColor() {
        return super.secondaryHover;
    }

    @Override
    public void setGhostTextColor(Color color) {
        super.ghostText = color;
    }

    @Override
    public Color getGhostTextColor() {
        return super.ghostText;
    }

}
