package economysimulation.classes.gui.mainpanels.extra.settings;

import java.awt.Font;
import java.awt.Graphics;
import javax.swing.JPanel;
import static economysimulation.classes.global.Methods.ThemeHandler;

/**
 * @author Max Carter
 */
public class ThemeMiddle extends JPanel {
    
    /** Which sector is hovered on. */
    public int sector = 4;
    
    /** Creates new ThemeMiddle. */
    public ThemeMiddle() {
        super.setSize(360, 180);
    }
    
    @Override
    protected void paintComponent(Graphics g) {
        //paints the semi circle.
        for (int i = 0; i < 4; i++) {
            g.setColor(sector == i ? ThemeHandler.getTheme().getPrimaryColor() : ThemeHandler.getTheme().getPrimaryTextColor());
            g.fillArc(0, 0, 360, 360, (47*i), 40);
        }
        
        g.setColor(ThemeHandler.getTheme().getPrimaryColor());
        g.fillArc(50, 50, 260, 260, 0, 180);
        
        //draw string in the middle of the selector.
        g.setColor(ThemeHandler.getTheme().getPrimaryTextColor());
        g.setFont(new Font("Agency FB", Font.PLAIN, 70));
        g.drawString("THEME", 115, 130);
        
        g.setFont(new Font("Agency FB", Font.PLAIN, 40));
        g.drawString("SELECTOR", 125, 170);
    }
    
}
