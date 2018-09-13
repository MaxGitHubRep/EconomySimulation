package economysimulation.classes.managers.theme;

import economysimulation.classes.global.Methods;
import economysimulation.classes.managers.theme.themes.ThemeBase;
import economysimulation.classes.managers.theme.themes.ThemeModel;
import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Max
 */
public class Theme {
    
    public final ThemeModel
        White = new ThemeBase("White",
            new Color(255, 255, 255),
            new Color(180, 180, 180),
            new Color(204, 0, 0),
            new Color(255, 255, 255),
            new Color(240, 240, 240),
            new Color(204, 0, 0),
            new Color(153, 0, 0)
        ),

        Dark = new ThemeBase("Dark",
            new Color(51, 51, 51),
            new Color(41, 41, 41),
            new Color(235, 235, 235),
            new Color(235, 235, 235),
            new Color(90, 90, 90),
            new Color(102, 102, 102),
            new Color(153, 153, 153)
        ),

        Ocean = new ThemeBase("Ocean",
            new Color(0, 102, 153),
            new Color(0, 153, 204),
            new Color(255, 255, 255),
            new Color(255, 255, 255),
            new Color(0, 153, 204),
            new Color(0, 140, 255),
            new Color(240, 240, 240)
        );
    
    public ThemeModel[] Themes = new ThemeModel[]{
        White, Dark, Ocean
    };
    
    public final ThemeModel DEFAULT_THEME = White;
    public ThemeModel Theme = DEFAULT_THEME;
    
    protected List<ThemeUpdateEvent> listeners;
    
    public Theme() {
        Methods.Updater = new GraphicUpdater();
        listeners = new ArrayList<>();
    }
    
    public void addThemeUpdateListener(ThemeUpdateEvent event) {
        listeners.add(event);
        event.updateThemeEvent(Methods.Updater);
    }
    
    public void setTheme(ThemeModel theme) {
        Theme = theme;
        listeners.forEach((listener) -> {
            listener.updateThemeEvent(Methods.Updater);
        });
    }

}
