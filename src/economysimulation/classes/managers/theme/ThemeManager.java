package economysimulation.classes.managers.theme;

import economysimulation.classes.global.Methods;
import economysimulation.classes.managers.theme.themes.Theme;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Max
 */
public class ThemeManager {
    
    public Theme[] Themes;
    
    private Theme DEFAULT_THEME;
    private Theme Theme;
    
    protected List<ThemeUpdateEvent> listeners;
    
    public ThemeManager() {
        this.DEFAULT_THEME = Theme.White;
        this.Theme = DEFAULT_THEME;
        
        this.Themes = new Theme[]{
            Theme.White, Theme.Dark, Theme.Ocean, Theme.Pink
        };
        
        Methods.Updater = new GraphicUpdater();
        listeners = new ArrayList<>();
    }
    
    public void addThemeUpdateListener(ThemeUpdateEvent event) {
        listeners.add(event);
        event.onThemeUpdate(Methods.Updater);
    }
    
    public void setTheme(Theme theme) {
        Theme = theme;
        listeners.forEach((listener) -> {
            listener.onThemeUpdate(Methods.Updater);
        });
    }
    
    public Theme getTheme() {
        return Theme;
    }

}
