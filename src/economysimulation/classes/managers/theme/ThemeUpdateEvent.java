package economysimulation.classes.managers.theme;

/**
 * @author Max
 */
public interface ThemeUpdateEvent {
    
    /**
     * When the colour them is updated.
     * @param updater GraphicUpdater instance.
     */
    public void onThemeUpdate(GraphicUpdater updater);
    
}
