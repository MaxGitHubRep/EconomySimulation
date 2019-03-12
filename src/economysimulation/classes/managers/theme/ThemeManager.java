package economysimulation.classes.managers.theme;

import economysimulation.classes.global.Methods;
import economysimulation.classes.managers.theme.themes.Theme;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Max
 */
public class ThemeManager {
    
    /** List of themes. */
    public Theme[] Themes;
    
    /** Default theme. */
    private Theme DEFAULT_THEME;
    
    /** Current theme. */
    private Theme Theme;
    
    /** Theme update event listeners. */
    protected List<ThemeUpdateEvent> listeners;
    
    /** Creates a new ThemeManager. */
    public ThemeManager() {
        this.DEFAULT_THEME = Theme.White;
        this.Theme = DEFAULT_THEME;
        
        //sets the list of 
        this.Themes = new Theme[]{
            Theme.White, Theme.Dark, Theme.Ocean, Theme.Pink
        };
        
        Methods.Updater = new GraphicUpdater();
        listeners = new ArrayList<>();
    }
    
    /**
     * Adds a new theme update listener.
     * @param event Event listener.
     */
    public void addThemeUpdateListener(ThemeUpdateEvent event) {
        listeners.add(event);
        event.onThemeUpdate(Methods.Updater);
    }
    
    /**
     * Updates the current theme.
     * @param theme New theme.
     */
    public void setTheme(Theme theme) {
        Theme = theme;
        //updates all the listeners.
        listeners.forEach((listener) -> {
            listener.onThemeUpdate(Methods.Updater);
        });
    }
    
    /**
     * Gets the current theme.
     * @return Current theme.
     */
    public Theme getTheme() {
        return Theme;
    }

}
